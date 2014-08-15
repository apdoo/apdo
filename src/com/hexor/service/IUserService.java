package com.hexor.service;

import java.util.List;
import java.util.Map;

import com.hexor.repo.Pager;
import com.hexor.repo.User;

/** 
 * @author  hexd
 * 创建时间：2014-5-27 下午3:27:44 
 * 类说明 
 */
public interface IUserService {
	//添加用户到用户表
	public void insert(User user);
	//查找相同用户
	public User checkUser(String username);
	//更新用户
	public void updateUser(User user);
	//查询所有用户
	public List<User> getAllUser();
	public void updateSigndate(Map map);
	
	public User getOneUserByDate(String date);
	public long getUsersCount();
	public long getUnSignCount(String date);
	public List<User> limit(Pager pager);
}
