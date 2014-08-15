package com.hexor.util;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

/** 
 * @author  hexd
 * 创建时间：2014-5-29 下午4:09:13 
 * 类说明 
 */
public class ResponseUtil {

	public static void outWriteJson(HttpServletResponse response,Map  map) throws IOException{
		response.setContentType("application/json");  
        response.setCharacterEncoding("utf-8");  
        //拼装json
        JSONObject jsonObject = new JSONObject(); 
        jsonObject.put("msg", map.get("msg"));
        jsonObject.put("count", map.get("count"));
        jsonObject.put("username", map.get("username"));  
        jsonObject.put("datetime", map.get("datetime"));  
        PrintWriter out = response.getWriter(); 
        out.write(jsonObject.toString());  
	}
	public static void outWriteJsonMessage(HttpServletResponse response,Map  map) throws IOException{
		response.setContentType("application/json");  
        response.setCharacterEncoding("utf-8");  
        //拼装json
        JSONObject jsonObject = new JSONObject(); 
        jsonObject.put("msg", map.get("msg"));
        PrintWriter out = response.getWriter(); 
        out.write(jsonObject.toString());  
		
	} 
	
	public static void outWriteErrorVcode() throws IOException{
		
		
	}
}
