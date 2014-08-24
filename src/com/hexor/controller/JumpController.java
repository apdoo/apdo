package com.hexor.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
/**
 *用来页面控制跳转 
 * */
@Controller
public class JumpController {
	
	/**
	 * @param session
	 * @param model
	 * @return 
	 * 首页跳转
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
