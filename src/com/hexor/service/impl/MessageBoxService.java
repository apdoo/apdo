package com.hexor.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.hexor.dao.IMessageBoxMapper;
import com.hexor.repo.MessageBox;
import com.hexor.repo.Pager;
import com.hexor.service.IMessageBoxService;

/** 
 * @author  hexd
 * 创建时间：2014-6-5 下午1:44:43 
 * 类说明 
 */
@Service("com.hexor.service.impl.MessageBoxService")
public class MessageBoxService implements IMessageBoxService{
	@Autowired
	@Qualifier("com.hexor.dao.IMessageBoxMapper")
	private IMessageBoxMapper mapper;

	public void setMapper(IMessageBoxMapper delegate) {
		this.mapper = delegate;
	}
	
	
	@Override
	public List<MessageBox> getAllMessage() {
		// TODO Auto-generated method stub
		return mapper.getAllMessage();
	}

	@Override
	public void insertMessage(MessageBox messageBox) {
		// TODO Auto-generated method stub
		mapper.insertMessage(messageBox);
	}

	@Override
	public void deleteById(String id) {
		// TODO Auto-generated method stub
		mapper.deleteById(id);
	}


	@Override
	public long getMessageCount() {
		// TODO Auto-generated method stub
		return mapper.getMessageCount();
	}


	@Override
	public List<MessageBox> limit(Pager pager) {
		// TODO Auto-generated method stub
		return mapper.limit(pager);
	}

}
