package com.hexor.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.hexor.dao.IAccodeMapper;
import com.hexor.repo.Accode;
import com.hexor.service.IAcCodeService;

/** 
 * @author  hexd
 * 创建时间：2014-5-29 下午3:29:40 
 * 类说明 
 */
@Service("com.hexor.service.impl.AcCodeService")
public class AcCodeService implements IAcCodeService{
	@Autowired
	@Qualifier("com.hexor.dao.IAccodeMapper")
    private IAccodeMapper mapper;
    public void setMapper(IAccodeMapper delegate) {
    	this.mapper = delegate;
    }
	@Override
	public List<Accode> getAllCode() {
		// TODO Auto-generated method stub
		return mapper.getAllCode();
	}

	@Override
	public void insertCode(Accode accode) {
		// TODO Auto-generated method stub
		mapper.insertCode(accode);
	}

	@Override
	public Accode getAcCode(Accode accode) {
		// TODO Auto-generated method stub
		return mapper.getAcCode(accode);
	}
	@Override
	public void deleteById(Accode accode) {
		// TODO Auto-generated method stub
		mapper.deleteById(accode);
	}

}
