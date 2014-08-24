package com.hexor.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.Cache;
import org.springframework.cache.Cache.ValueWrapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.hexor.repo.Accode;
import com.hexor.repo.Pager;
import com.hexor.repo.User;
import com.hexor.service.impl.AcCodeService;
import com.hexor.service.impl.CoreService;
import com.hexor.service.impl.UserService;
import com.hexor.test.Log4jDemo;
import com.hexor.util.CacheUtil;
import com.hexor.util.CustomizedPropertyPlaceholderConfigurer;
import com.hexor.util.DateUtil;
import com.hexor.util.PagerUtil;
import com.hexor.util.ResponseUtil;
import com.hexor.util.TipMsgUtil;
import com.hexor.util.ValidateUtils;

@Controller
@RequestMapping(value = "sign")
public class SignController {
	Logger logger = Logger.getLogger(SignController.class);
	// ????????
	@Autowired
	@Qualifier("com.hexor.service.impl.CoreService")
	private CoreService coreService = null;

	public void setCoreService(CoreService coreService) {
		this.coreService = coreService;
	}

	// ????????
	@Autowired
	@Qualifier("com.hexor.service.impl.UserService")
	private UserService userService = null;

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	// ??????????
	@Autowired
	@Qualifier("com.hexor.service.impl.AcCodeService")
	private AcCodeService acCodeService = null;

	public void setUserService(AcCodeService acCodeService) {
		this.acCodeService = acCodeService;
	}
	/**???????
	 * @param session
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "signlist")
	public ModelAndView signlist(HttpSession session, 
			HttpServletRequest request, HttpServletResponse response){
		//??????????????
		String wpage=request.getParameter("wpage");
		if(wpage==null){
			wpage="1";
		}
		//????????
		long count=userService.getUsersCount();
		 Pager p=new Pager();
		 //??????¦Â???20??
		 p.setData(20);
			try{
			    p=PagerUtil.build(PagerUtil.SetPager(p,count, Integer.parseInt(wpage.trim())));
			}catch(Exception e){
				p=PagerUtil.build(PagerUtil.SetPager(p,count, 1));
			}
		 Map modelMap =new HashMap();
		 List<User> list=userService.limit(p);
		 if(list!=null){
			 //??????????????????? ????????????? ???????
			 for(int i=0;i<list.size();i++){
				 User temp=list.get(i);
				 temp.setPassword("");
				 //????????
				 temp.setUsername(trans(temp.getUsername()));
			 }
				//list????json???ú“?????
			 JSONArray jsonArray = JSONArray.fromObject(list);  
		     String result = jsonArray.toString();
		     //System.out.println(result);
		 	//??????
		     modelMap.put("pager",p);
		     //json???????
		     modelMap.put("list", result);
		 }
		return new ModelAndView("signlist",modelMap);
	}
	
	/**
	 * ?????????
	 */
	@RequestMapping(value = "people")
	public void people(HttpSession session, 
			HttpServletRequest request, HttpServletResponse response){
		long number=userService.getUsersCount();
		Map map=new HashMap();
		map.put("msg", ""+number);
		try {
			ResponseUtil.outWriteJsonMessage(response, map);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * ????????????????????
	 * 
	 * @throws CloneNotSupportedException
	 * @throws NoSuchAlgorithmException
	 * @throws KeyManagementException
	 * @throws IOException
	 * */
	@RequestMapping(value = "verification")
	public void sign(User user, HttpSession session, ModelMap model,
			HttpServletRequest request, HttpServletResponse response)
			throws KeyManagementException, NoSuchAlgorithmException,
			CloneNotSupportedException, IOException {
		// session?§Ö??????
		String vcode = session.getAttribute("validation_code").toString()
				.toLowerCase();
		// ?????????????
		String ucode = user.getVcode().toLowerCase();
		Map map = new HashMap();
		// ?????????
		String msg = "";
		//System.out.println("vcode:" + vcode + "|ucode:" + ucode);
		// ???????????????
		if (!vcode.equals(ucode)) {
			msg = TipMsgUtil.MSG_ERROR_VCODE;
			map.put("msg", msg);
			map.put("username", user.getUsername());
			map.put("datetime", DateUtil.getStrOfDateMinute());
			ResponseUtil.outWriteJson(response, map);
			return;
		}
//		Accode accode = new Accode();
		// ??????????§³§Õ
//		accode.setCode(user.getIcode());
		// ?????????
//		accode.setType(TipMsgUtil.NOMAL_FLAG);
		// ???????????§Ø????????????
//		Accode recode = acCodeService.getAcCode(accode);
//		if (user.getIcode().equals(TipMsgUtil.TRY_CODE) || recode != null) {
			// ??????
			// ??????????????????
			User temp = userService.checkUser(user.getUsername());
			//??????????????????
			user.setDatetime(DateUtil.getStrOfDateMinute());
			// ????????????????????
//			if (user.getIcode().equals(TipMsgUtil.TRY_CODE)) {
//				//???????
//				msg = TipMsgUtil.MSG_SUCCESS_TRY;
//				user.setType(TipMsgUtil.TRY_FLAG);
//			} else {
//				//??????
//				msg = TipMsgUtil.MSG_SUCCESS;
//				user.setType(TipMsgUtil.NOMAL_FLAG);
//			}
			msg = TipMsgUtil.MSG_SUCCESS;
			user.setType(TipMsgUtil.NOMAL_FLAG);
			if (temp == null) {
				// ?????????
				userService.insert(user);
			} 
		  else {
				userService.updateUser(user);
				//System.out.println(user.getDatetime()+user.getUsername()+msg);
			}
			// ?????????????????????
//			if (recode != null) {
//				acCodeService.deleteById(recode);
//			}
//		}
		long count=userService.getUnSignCount(DateUtil.getCurrentDay());
		map.put("count", ""+count);
	//	System.out.println(count+"aaa");
		map.put("msg", msg);
		map.put("username", user.getUsername());
		map.put("datetime", user.getDatetime());
		ResponseUtil.outWriteJson(response, map);
	}
	//????????
	public  String trans(String n){
		String result="";
		//???
		if(ValidateUtils.Mobile(n)){
			result=n.substring(0,3)+"****"+n.substring(7,11);
		}//????
		else if(ValidateUtils.Email(n)){
			//
			String headd=n.substring(0,n.indexOf("@"));
			result=n.substring(0,n.indexOf("@"));
			String bottom=n.substring(n.indexOf("@"),n.length());
			int head=headd.length();
			result=headd.substring(0,head/2)+"****"+bottom;
		}//????
		else{
			result=n.substring(0,n.length()/2)+"****";
		}
		return result;
	}
	//
//	@RequestMapping(value = "getuser")
//	public void getuser(HttpSession session, ModelMap model,
//			HttpServletRequest request, HttpServletResponse response)
//			throws IOException {
//		List<User> userlist = userService.getAllUser();
//		User user = userlist.get(0);
//		// ?????????
//		String result = "{\"msg\":\"" + user.getUsername() + "\"}";
//		PrintWriter out = null;
//		response.setCharacterEncoding("utf-8");
//		out = response.getWriter();
//		System.out.println(result);
//		out.write(result);
//	}
//
//	@RequestMapping(value = "insertuser")
//	public void insertuser(HttpSession session, ModelMap model,
//			HttpServletRequest request, HttpServletResponse response)
//			throws IOException {
//		User u = new User();
//		u.setUsername("insertName");
//		u.setPassword("ppppp");
//		userService.insert(u);
//		List<User> userlist = userService.getAllUser();
//		int l = userlist.size();
//		User user = userlist.get(l - 1);
//		// ?????????
//		String result = "{\"msg\":\"" + user.getUsername() + "\"}";
//		PrintWriter out = null;
//		response.setCharacterEncoding("utf-8");
//		out = response.getWriter();
//		System.out.println(result);
//		out.write(result);
//	}
}
