package com.hexor.service.impl;

import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.hexor.dao.IUserMapper;
import com.hexor.repo.User;
import com.hexor.util.ClientUtil4_1;
import com.hexor.util.DateUtil;
import com.hexor.util.TipMsgUtil;

@Service("com.hexor.service.impl.CoreService")
public class CoreService {

	@Autowired
	@Qualifier("com.hexor.dao.IUserMapper")
	private IUserMapper mapper;

	public void setMapper(IUserMapper delegate) {
		this.mapper = delegate;
	}

	// 百度连接工具
	private ClientUtil4_1 client;

	// 验证登录百度的账号密码是否正确,正确则进行签到。
	public String validForLogin(String username, String password)
			throws KeyManagementException, NoSuchAlgorithmException,
			CloneNotSupportedException {
		// 设置账号，密码
		ClientUtil4_1 client = new ClientUtil4_1(username, password);
		//System.err.println("登录账户。。。。。。");
		// 创建一个Httpclient,并且设置头文件。
		//System.err.println("设置用户的头文件。。。。。。");
		client.clientCreate("passport.baidu.com", "http://www.baidu.com/");
		// get提交，获取到cookie
		//System.err.println("获取用户的Cookie。。。。。。");
		// get 访问百度后 获得服务器发过来的cookie 储存于cookie中 参考
		// http://sb33060418.iteye.com/blog/2020007 使用cookie保持会话
		client.get("http://www.baidu.com");
		// 获取到token
		//System.err.println("获取用户的Token。。。。。。");
		client.getToken();
		// 使用post提交表单，登录baidu
		// System.err.println("登录。。。。。。");
		String msg = client.LoginPost(
				"https://passport.baidu.com/v2/api/?login",
				client.produceFormEntity());
		// 关闭Httpclient
		client.closeClient();
		if (msg.equals(TipMsgUtil.LOGIN_SUCCESS)) {
			// 登录账号成功进行签到
			//System.err.println("登录百度个人中心。。。。。。");
			client.clientCreate("passport.baidu.com", "http://www.baidu.com");
			//System.err.println("获取用户的贴吧中心地址。。。。。。");
			String tiebaURL = client
					.getDocumet("http://i.baidu.com", "UTF-8")
					.getElementsByAttributeValue("data-click",
							"{\"act\":\"tieba\"}").attr("href");
			// 关闭Httpclient
			client.closeClient();
			//System.err.println("获取用户贴吧中心当中的用户关注的贴吧的地址。。。。。。");
			client.clientCreate("tieba.baidu.com", "http://i.baidu.com");
			String tiebaURLconcern = "http://tieba.baidu.com"
					+ client.getDocumet(tiebaURL, "GBK")
							.getElementsByAttributeValue("class",
									"nav_icon nav_concern").attr("href");
			// 关闭Httpclient
			client.closeClient();
			// 查看关注的贴吧，设置新的头文件
			/*
			 * 本来打算通过网页一步一步的来找出用户关注的贴吧， 结果在访问用户关注的贴吧的网页时，无法抓取到内容
			 * 于是通过抓包，发现统一的地址为“http://tieba.baidu.com/f/like/mylike”
			 * 于是，直接访问给地址就是。
			 */
			//System.err.println("访问用户关注贴吧的地址，获取用户关注的贴吧。。。。。。");
			client.clientCreate("tieba.baidu.com", tiebaURLconcern);
			//System.out.println(tiebaURLconcern);
			client.getShow(tiebaURLconcern);
			// 定义一个list，用来存储用户关注贴吧的的地址
			List<String> listURLs = new ArrayList<String>();
			// 获取各行
			Elements trs = client
					.getDocumet("http://tieba.baidu.com/f/like/mylike", "GBK")
					.select("table").select("tr");
			// 遍历各行，找出地址.记录地址的是在每行的第一列，所以get(0);
			for (int i = 0; i < trs.size(); i++) {
				Elements tds = trs.get(i).select("td");
				for (int j = 0; j < tds.size(); j++) {
					listURLs.add("http://tieba.baidu.com"
							+ tds.get(0).childNode(0).attr("href"));
					break;
				}
			}
			client.closeClient();
			// 签到结果
			msg = client.signTieba(listURLs);
			
		} 
		return msg;
	}
}
