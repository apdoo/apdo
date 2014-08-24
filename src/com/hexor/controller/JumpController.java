package com.hexor.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
/**
 *����ҳ�������ת 
 * */
@Controller
public class JumpController {
	
	/**
	 * @param session
	 * @param model
	 * @return 
	 * ��ҳ��ת
	 */
	@RequestMapping(value="home")
	public String home(HttpSession session, ModelMap model){
		return "index";
	}
	
	@RequestMapping(value="single")
	public String single(HttpSession session, ModelMap model){
		return "singleversion";
	}


}
