package com.hexor.util;
/** 
 * @author  hexd
 */
public class TipMsgUtil {
	//前端返回码
	public static final String MSG_ERROR_VCODE="验证码输入错误，请检查重新输入";
	public static final String MSG_SUCCESS="已经添加用户到自动签到系统队列";
	public static final String MSG_SUCCESS_TRY="已经添加用户到自动签到系统队列，试用签到日期为一周";
	public static final String MSG_ERROR_ICODE="添加用户到自动签到系统队列失败，请确认邀请码是否正确";
	//状态码
	public static final String NOMAL_FLAG="1";//普通标志
	public static final String TRY_FLAG="0";//试用标志
	public static final String TRY_CODE="666666";//试用码
	//ClientUtil
	public static final String LOGIN_UNUSUAL="登录不正常";
	public static final String LOGIN_IP_BAN="ip被禁止";
	public static final String LOGIN_ACC_ERROR="百度账号名密码输入错误";
	public static final String LOGIN_SUCCESS="检测成功";
	public static final String SIGN_SUCCESS="签到成功";
	public static final String SIGN_FALL="签到失败";
}
