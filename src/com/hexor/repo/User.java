package com.hexor.repo;
/** 
 * @author  hexd
 * ����ʱ�䣺2014-5-23 ����11:01:59 
 * ��˵�� 
 */
public class User {
	private int id;
	private String username="";
	private String password="";
	private String vcode="";//�������ݲ������ݿ⣬ֻΪ�˽���ǰ�˴��������
	private String icode="";//�������ݲ������ݿ⣬ֻΪ�˽���ǰ�˴��������
	/**
	 * �û��˻�����
	 * 0:�����û� ͨ�����ü�����Ǽǵ��û�
	 * 1:�����û�
	 * */
	private String type="";
	//�Ǽ�ʱ��
	private String datetime="";
	//ǩ��ʱ�� ����Ĭ��ֵ ��һ���û���ӵ�ʱ��
	private String signdate="-";
	//�����ǩ����־ fallʧ�� success �ɹ�
	private String flag="-";
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
	public String getSigndate() {
		return signdate;
	}
	public void setSigndate(String signdate) {
		this.signdate = signdate;
	}
	public void setDatetime(String datetime) {
		this.datetime = datetime;
	}
	public String getDatetime() {
		return datetime;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getVcode() {
		return vcode;
	}
	public void setVcode(String vcode) {
		this.vcode = vcode;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getIcode() {
		return icode;
	}
	public void setIcode(String icode) {
		this.icode = icode;
	}
	
}
