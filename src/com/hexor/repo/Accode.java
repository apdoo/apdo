package com.hexor.repo;
/** 
 * @author  hexd
 * ����ʱ�䣺2014-5-29 ����2:15:19 
 * ��˵�� ������ 
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
	//������
	private String code;
	/**
	 * ����������
	 * 0--���ü����� �ݶ�666666
	 * 1--��ͨ������
	 * Ĭ��ֵΪ��ͨ������
	 * */
	private String type="1";
}
