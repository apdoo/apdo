package com.hexor.repo;
/** 
 * @author  hexd
 * 创建时间：2014-5-29 下午2:15:19 
 * 类说明 激活码 
 */
public class Accode {
	private int id;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	//激活码
	private String code;
	/**
	 * 激活码类型
	 * 0--试用激活码 暂定666666
	 * 1--普通激活码
	 * 默认值为普通激活码
	 * */
	private String type="1";
}
