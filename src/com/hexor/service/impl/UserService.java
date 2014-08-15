package com.hexor.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.hexor.dao.IUserMapper;
import com.hexor.repo.Pager;
import com.hexor.repo.User;
import com.hexor.service.IUserService;

/** 
 * @author  hexd
 * 创建时间：2014-5-27 下午3:27:59 
 * 类说明 
 */
@Service("com.hexor.service.impl.UserService")
public class UserService implements IUserService{
	@Autowired
	@Qualifier("com.hexor.dao.IUserMapper")
    private IUserMapper mapper;
    public void setMapper(IUserMapper delegate) {
    	this.mapper = delegate;
    }
    
	//添加用户到用户表
	public void insert(User user){
		mapper.insertUser(user);
	}

	@Override
	public User checkUser(String username) {
		return mapper.checkUser(username);
	}

	@Override
	public void updateUser(User user) {
		mapper.updateUser(user);
	}

	@Override
	public List<User> getAllUser() {
		// TODO Auto-generated method stub
		return mapper.getAllUser();
	}

	@Override
	public void updateSigndate(Map map) {
		// TODO Auto-generated method stub
		mapper.updateSigndate(map);
	}

	@Override
	public User getOneUserByDate(String date) {
		// TODO Auto-generated method stub
		return mapper.getOneUserByDate(date);
	}

	@Override
	public long getUsersCount() {
		// TODO Auto-generated method stub
		return mapper.getUsersCount();
	}

	@Override
	public long getUnSignCount(String date) {
		// TODO Auto-generated method stub
		return mapper.getUnSignCount(date);
	}

	@Override
	public List<User> limit(Pager pager) {
		// TODO Auto-generated method stub
		return mapper.limit(pager);
	}
}
