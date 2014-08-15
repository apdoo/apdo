package com.hexor.service;

import java.util.List;
import java.util.Map;

import com.hexor.repo.Pager;
import com.hexor.repo.User;

/** 
 * @author  hexd
 * ����ʱ�䣺2014-5-27 ����3:27:44 
 * ��˵�� 
 */
public interface IUserService {
	//����û����û���
	public void insert(User user);
	//������ͬ�û�
	public User checkUser(String username);
	//�����û�
	public void updateUser(User user);
	//��ѯ�����û�
	public List<User> getAllUser();
	public void updateSigndate(Map map);
	
	public User getOneUserByDate(String date);
	public long getUsersCount();
	public long getUnSignCount(String date);
	public List<User> limit(Pager pager);
}
