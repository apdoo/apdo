package com.hexor.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.hexor.repo.MessageBox;
import com.hexor.repo.Pager;
import com.hexor.repo.User;
import com.hexor.service.impl.MessageBoxService;
import com.hexor.util.DateUtil;
import com.hexor.util.PagerUtil;
import com.hexor.util.ResponseUtil;


/** 
 * @author  hexd
 * ����ʱ�䣺2014-6-5 ����1:15:35 
 * ��˵�� 
 */
@Controller
@RequestMapping(value = "message")
public class MessageBoxController {
	// ���԰�����
	@Autowired
	@Qualifier("com.hexor.service.impl.MessageBoxService")
	private MessageBoxService messageBoxService = null;

	public void setMessageBoxService(MessageBoxService messageBoxService) {
		this.messageBoxService = messageBoxService;
	}
	/**
	 * �û�����
	 */
	@RequestMapping(value = "send")
	public void messageLeave(MessageBox message, HttpSession session, ModelMap model,
			HttpServletRequest request, HttpServletResponse response){
		message.setDatetime(DateUtil.getStrOfDateMinute());
		//System.out.println(message.getUsername()+message.getMessage());
		messageBoxService.insertMessage(message);
		Map map = new HashMap();
		map.put("msg", "���Գɹ���лл���ı��������");
		try {
			ResponseUtil.outWriteJson(response, map);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	/**��ҳ����
	 * @param session
	 * @param request
	 * @return
	 */
	@RequestMapping(value="home")
	public ModelAndView messageboxhome(HttpSession session, HttpServletRequest request){
		 Map modelMap =new HashMap();
		//�����Ҫ��ת��ҳ��
		String wpage=request.getParameter("wpage");
		//��������
		long count=messageBoxService.getMessageCount();
		if(wpage==null){
			wpage="1";
		}
		Pager p=new Pager();
		try{
		    p=PagerUtil.build(PagerUtil.SetPager(count, Integer.parseInt(wpage.trim())));
		}catch(Exception e){
			p=PagerUtil.build(PagerUtil.SetPager(count, 1));
		}
		//��������Ҫ��ҳ��������
		List<MessageBox> list=messageBoxService.limit(p);
		//listת��Ϊjson���鷵��ǰ��
		 JSONArray jsonArray = JSONArray.fromObject(list);  
	     String result = jsonArray.toString();
	 	//��ҳ���
		modelMap.put("pager",p);
	     //json��ʽ����
	     modelMap.put("list", result);
		return new ModelAndView("messagebox",modelMap);
	}
	
}
