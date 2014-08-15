package com.hexor.service;

import java.util.List;

import com.hexor.repo.MessageBox;
import com.hexor.repo.Pager;

/** 
 * @author  hexd
 * ����ʱ�䣺2014-6-5 ����1:44:19 
 * ��˵�� 
 */
public interface IMessageBoxService {
	public List<MessageBox> getAllMessage();
	public void insertMessage(MessageBox messageBox);
	public void deleteById(String id);
	public long getMessageCount();
	public List<MessageBox> limit(Pager pager);
}
