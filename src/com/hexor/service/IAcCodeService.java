package com.hexor.service;

import java.util.List;

import com.hexor.repo.Accode;

/** 
 * @author  hexd
 * ����ʱ�䣺2014-5-29 ����3:29:29 
 * ��˵�� 
 */
public interface IAcCodeService {
	public List<Accode> getAllCode();
	
	public void insertCode(Accode accode);
	
	public Accode getAcCode(Accode accode);
	
	public void deleteById(Accode accode);
}
