package com.hexor.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.hexor.repo.Pager;
import com.hexor.repo.User;

 
@Service("com.hexor.dao.IUserMapper")
public interface IUserMapper {
	public List<User> getAllUser();
	
	public User checkUser(String username);

	public void insertUser(User user);
	
	public void updateUser(User user);
	//mysql��ҳ��ѯ
	public List<User> limit(Pager pager);
	//��ѯ������
	public long getUsersCount();
	
	public void updateSigndate(Map map);
	
	public User getOneUserByDate(String date);
	
	public long getUnSignCount(String date);
}
