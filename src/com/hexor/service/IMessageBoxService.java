package com.hexor.service;

import java.util.List;

import com.hexor.repo.MessageBox;
import com.hexor.repo.Pager;

/** 
 * @author  hexd
 * 创建时间：2014-6-5 下午1:44:19 
 * 类说明 
 */
public interface IMessageBoxService {
	public List<MessageBox> getAllMessage();
	public void insertMessage(MessageBox messageBox);
	public void deleteById(String id);
	public long getMessageCount();
	public List<MessageBox> limit(Pager pager);
}
