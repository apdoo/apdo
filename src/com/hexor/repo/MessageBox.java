package com.hexor.repo;
/** 
 * @author  hexd
 * 创建时间：2014-6-5 下午1:31:52 
 * 类说明 
 */
public class MessageBox {

	private int id;
	private String username="";
	private String datetime="";
	private String message="";
	
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
	public String getDatetime() {
		return datetime;
	}
	public void setDatetime(String datetime) {
		this.datetime = datetime;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
}
