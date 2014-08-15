package com.hexor.service;

import java.util.List;

import com.hexor.repo.Accode;

/** 
 * @author  hexd
 * 创建时间：2014-5-29 下午3:29:29 
 * 类说明 
 */
public interface IAcCodeService {
	public List<Accode> getAllCode();
	
	public void insertCode(Accode accode);
	
	public Accode getAcCode(Accode accode);
	
	public void deleteById(Accode accode);
}
