package com.hexor.quartz.imp;

import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.Cache;
import org.springframework.cache.Cache.ValueWrapper;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.hexor.quartz.QtimeInterface;
import com.hexor.repo.User;
import com.hexor.service.impl.CoreService;
import com.hexor.service.impl.UserService;
import com.hexor.test.Log4jDemo;
import com.hexor.util.CacheUtil;
import com.hexor.util.DateUtil;
import com.hexor.util.TipMsgUtil;
@Component
public class QtimeImp implements QtimeInterface{
	Logger logger = Logger.getLogger(QtimeImp.class);
	@Autowired
    @Qualifier("com.hexor.service.impl.CoreService")
	private CoreService coreService=null; 
	public void setCoreService(CoreService coreService) {
		this.coreService = coreService;
	}
	//用户服务层
	@Autowired
    @Qualifier("com.hexor.service.impl.UserService")
	private UserService userService=null; 
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	
  // @Scheduled(cron="0 0/1 * * * ? ")   //每1分钟执行一次
    public void test() {
		 logger.warn("logger当前定时器执行时间:"+DateUtil.getStrOfDateTime());
		 //根据id排序查找用户表 查找一条今天没有签到的用户信息
//		 User user=userService.getOneUserByDate(DateUtil.getCurrentDay());
//		 String msg="";
//		 //存在此条用户
//		 if(user!=null){
//			 //System.out.println("今天没签到的:"+user.getUsername()+"日期:"+user.getSigndate());
//			 //签到操作
//			 try {
//				msg=coreService.validForLogin(user.getUsername(), user.getPassword());
//			} catch (KeyManagementException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			} catch (NoSuchAlgorithmException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			} catch (CloneNotSupportedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			 System.out.println(msg);
//			 //签到成功
//			 Map map=new HashMap();
//			 map.put("username", user.getUsername());
//			 map.put("signdate", DateUtil.getStrOfDateMinute());
//			 if(msg.equals(TipMsgUtil.SIGN_SUCCESS)){
//				 map.put("flag", msg);
//			 }else{
//				 map.put("flag", msg);
//			 }
//			 userService.updateSigndate(map);
//		 }
	}

}
