package com.hexor.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.hexor.repo.User;


@Controller
@RequestMapping("/test")
public class TestC {

 
	@RequestMapping("/fuck")
	public ModelAndView testf(HttpSession session){
		 Map modelMap =new HashMap();
		 User user =new User();
		 user.setUsername("hh");
		 User user2=new User();
		 user2.setUsername("hh2");
		 modelMap.put("test", user);
		 //传list
		 List<User> list=new ArrayList<User>();
		 list.add(user);
		 list.add(user2);
		 System.out.println(list.size());
	    JSONArray jsonArray = JSONArray.fromObject(list);  
	     String result = jsonArray.toString();  
	      System.out.println("************************ Json:\n"+result);  
	      //json格式数组
	      modelMap.put("list", result);
		return new ModelAndView("test",modelMap);
	}
 
}
