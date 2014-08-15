package com.hexor.dao;

import java.util.List;

import org.springframework.stereotype.Service;

import com.hexor.repo.Accode;

/** 
 * @author  hexd
 * 创建时间：2014-5-29 下午2:19:26 
 * 类说明 
 */
@Service("com.hexor.dao.IAccodeMapper")
public interface IAccodeMapper {
	public List<Accode> getAllCode();
	
	public void insertCode(Accode accode);
	
	public Accode getAcCode(Accode accode);
	
	public void deleteById(Accode accode);
}
