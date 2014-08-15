package com.hexor.dao;

import java.util.List;

import org.springframework.stereotype.Service;

import com.hexor.repo.MessageBox;
import com.hexor.repo.Pager;

/** 
 * @author  hexd
 * ����ʱ�䣺2014-6-5 ����1:41:59 
 * ��˵�� 
 */
@Service("com.hexor.dao.IMessageBoxMapper")
public interface IMessageBoxMapper {
	public List<MessageBox> getAllMessage();
	public void insertMessage(MessageBox messageBox);
	public void deleteById(String id);
	public long getMessageCount();
	public List<MessageBox> limit(Pager pager);
}

