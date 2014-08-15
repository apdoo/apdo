package com.hexor.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.jms.Session;
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
	// 核心服务层
	@Autowired
	@Qualifier("com.hexor.service.impl.CoreService")
	private CoreService coreService = null;

	public void setCoreService(CoreService coreService) {
		this.coreService = coreService;
	}

	// 用户服务层
	@Autowired
	@Qualifier("com.hexor.service.impl.UserService")
	private UserService userService = null;

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	// 激活码服务层
	@Autowired
	@Qualifier("com.hexor.service.impl.AcCodeService")
	private AcCodeService acCodeService = null;

	public void setUserService(AcCodeService acCodeService) {
		this.acCodeService = acCodeService;
	}
	/**签到队列
	 * @param session
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "signlist")
	public ModelAndView signlist(HttpSession session, 
			HttpServletRequest request, HttpServletResponse response){
		//获得想要跳转的页数
		String wpage=request.getParameter("wpage");
		if(wpage==null){
			wpage="1";
		}
		//留言总数
		long count=userService.getUsersCount();
		 Pager p=new Pager();
		 //设置一次查找20条
		 p.setData(20);
			try{
			    p=PagerUtil.build(PagerUtil.SetPager(p,count, Integer.parseInt(wpage.trim())));
			}catch(Exception e){
				p=PagerUtil.build(PagerUtil.SetPager(p,count, 1));
			}
		 Map modelMap =new HashMap();
		 List<User> list=userService.limit(p);
		 if(list!=null){
			 //注意此处服务端把用户名加密 用户密码给设置空 否则传到前台
			 for(int i=0;i<list.size();i++){
				 User temp=list.get(i);
				 temp.setPassword("");
				 //用户名加密
				 temp.setUsername(trans(temp.getUsername()));
			 }
				//list转换为json数组返回前端
			 JSONArray jsonArray = JSONArray.fromObject(list);  
		     String result = jsonArray.toString();
		     //System.out.println(result);
		 	//分页情况
		     modelMap.put("pager",p);
		     //json格式数组
		     modelMap.put("list", result);
		 }
		return new ModelAndView("signlist",modelMap);
	}
	
	/**
	 * 统计登记人数
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
	 * 在此检测百度账号密码是否正确
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
		// session中的验证码
		String vcode = session.getAttribute("validation_code").toString()
				.toLowerCase();
		// 用户输入的验证码
		String ucode = user.getVcode().toLowerCase();
		Map map = new HashMap();
		// 默认的返回结果
		String msg = "";
		//System.out.println("vcode:" + vcode + "|ucode:" + ucode);
		// 验证码输入是否正确
		if (!vcode.equals(ucode)) {
			msg = TipMsgUtil.MSG_ERROR_VCODE;
			map.put("msg", msg);
			map.put("username", user.getUsername());
			map.put("datetime", DateUtil.getStrOfDateMinute());
			ResponseUtil.outWriteJson(response, map);
			return;
		}
//		Accode accode = new Accode();
		// 激活码区分大小写
//		accode.setCode(user.getIcode());
		// 普通激活码
//		accode.setType(TipMsgUtil.NOMAL_FLAG);
		// 获得的激活码表中对应的激活码对象
//		Accode recode = acCodeService.getAcCode(accode);
//		if (user.getIcode().equals(TipMsgUtil.TRY_CODE) || recode != null) {
			// 验证通过
			// 检查用户是否存在数据库中
			User temp = userService.checkUser(user.getUsername());
			//设置该用户最近更新的时间
			user.setDatetime(DateUtil.getStrOfDateMinute());
			// 根据激活码设置为用户类型
//			if (user.getIcode().equals(TipMsgUtil.TRY_CODE)) {
//				//试用用户
//				msg = TipMsgUtil.MSG_SUCCESS_TRY;
//				user.setType(TipMsgUtil.TRY_FLAG);
//			} else {
//				//普通用户
//				msg = TipMsgUtil.MSG_SUCCESS;
//				user.setType(TipMsgUtil.NOMAL_FLAG);
//			}
			msg = TipMsgUtil.MSG_SUCCESS;
			user.setType(TipMsgUtil.NOMAL_FLAG);
			if (temp == null) {
				// 插入用户表
				userService.insert(user);
			} 
		  else {
				userService.updateUser(user);
				//System.out.println(user.getDatetime()+user.getUsername()+msg);
			}
			// 从激活码表删除该普通激活码
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
	//用户名加密
	public  String trans(String n){
		String result="";
		//手机
		if(ValidateUtils.Mobile(n)){
			result=n.substring(0,3)+"****"+n.substring(7,11);
		}//邮箱
		else if(ValidateUtils.Email(n)){
			//
			String headd=n.substring(0,n.indexOf("@"));
			result=n.substring(0,n.indexOf("@"));
			String bottom=n.substring(n.indexOf("@"),n.length());
			int head=headd.length();
			result=headd.substring(0,head/2)+"****"+bottom;
		}//其他
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
//		// 默认的返回结果
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
//		// 默认的返回结果
//		String result = "{\"msg\":\"" + user.getUsername() + "\"}";
//		PrintWriter out = null;
//		response.setCharacterEncoding("utf-8");
//		out = response.getWriter();
//		System.out.println(result);
//		out.write(result);
//	}
}
