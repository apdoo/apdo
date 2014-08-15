package com.hexor.repo;
/** 
 * @author  hexd
 * 创建时间：2014-5-23 上午11:01:59 
 * 类说明 
 */
public class User {
	private int id;
	private String username="";
	private String password="";
	private String vcode="";//此条数据不存数据库，只为了接受前端传入的数据
	private String icode="";//此条数据不存数据库，只为了接受前端传入的数据
	/**
	 * 用户账户类型
	 * 0:试用用户 通过试用激活码登记的用户
	 * 1:永久用户
	 * */
	private String type="";
	//登记时间
	private String datetime="";
	//签到时间 加上默认值 第一次用户添加的时候
	private String signdate="-";
	//当天的签到标志 fall失败 success 成功
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
