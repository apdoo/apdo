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

	// �ٶ����ӹ���
	private ClientUtil4_1 client;

	// ��֤��¼�ٶȵ��˺������Ƿ���ȷ,��ȷ�����ǩ����
	public String validForLogin(String username, String password)
			throws KeyManagementException, NoSuchAlgorithmException,
			CloneNotSupportedException {
		// �����˺ţ�����
		ClientUtil4_1 client = new ClientUtil4_1(username, password);
		//System.err.println("��¼�˻�������������");
		// ����һ��Httpclient,��������ͷ�ļ���
		//System.err.println("�����û���ͷ�ļ�������������");
		client.clientCreate("passport.baidu.com", "http://www.baidu.com/");
		// get�ύ����ȡ��cookie
		//System.err.println("��ȡ�û���Cookie������������");
		// get ���ʰٶȺ� ��÷�������������cookie ������cookie�� �ο�
		// http://sb33060418.iteye.com/blog/2020007 ʹ��cookie���ֻỰ
		client.get("http://www.baidu.com");
		// ��ȡ��token
		//System.err.println("��ȡ�û���Token������������");
		client.getToken();
		// ʹ��post�ύ������¼baidu
		// System.err.println("��¼������������");
		String msg = client.LoginPost(
				"https://passport.baidu.com/v2/api/?login",
				client.produceFormEntity());
		// �ر�Httpclient
		client.closeClient();
		if (msg.equals(TipMsgUtil.LOGIN_SUCCESS)) {
			// ��¼�˺ųɹ�����ǩ��
			//System.err.println("��¼�ٶȸ������ġ�����������");
			client.clientCreate("passport.baidu.com", "http://www.baidu.com");
			//System.err.println("��ȡ�û����������ĵ�ַ������������");
			String tiebaURL = client
					.getDocumet("http://i.baidu.com", "UTF-8")
					.getElementsByAttributeValue("data-click",
							"{\"act\":\"tieba\"}").attr("href");
			// �ر�Httpclient
			client.closeClient();
			//System.err.println("��ȡ�û��������ĵ��е��û���ע�����ɵĵ�ַ������������");
			client.clientCreate("tieba.baidu.com", "http://i.baidu.com");
			String tiebaURLconcern = "http://tieba.baidu.com"
					+ client.getDocumet(tiebaURL, "GBK")
							.getElementsByAttributeValue("class",
									"nav_icon nav_concern").attr("href");
			// �ر�Httpclient
			client.closeClient();
			// �鿴��ע�����ɣ������µ�ͷ�ļ�
			/*
			 * ��������ͨ����ҳһ��һ�������ҳ��û���ע�����ɣ� ����ڷ����û���ע�����ɵ���ҳʱ���޷�ץȡ������
			 * ����ͨ��ץ��������ͳһ�ĵ�ַΪ��http://tieba.baidu.com/f/like/mylike��
			 * ���ǣ�ֱ�ӷ��ʸ���ַ���ǡ�
			 */
			//System.err.println("�����û���ע���ɵĵ�ַ����ȡ�û���ע�����ɡ�����������");
			client.clientCreate("tieba.baidu.com", tiebaURLconcern);
			//System.out.println(tiebaURLconcern);
			client.getShow(tiebaURLconcern);
			// ����һ��list�������洢�û���ע���ɵĵĵ�ַ
			List<String> listURLs = new ArrayList<String>();
			// ��ȡ����
			Elements trs = client
					.getDocumet("http://tieba.baidu.com/f/like/mylike", "GBK")
					.select("table").select("tr");
			// �������У��ҳ���ַ.��¼��ַ������ÿ�еĵ�һ�У�����get(0);
			for (int i = 0; i < trs.size(); i++) {
				Elements tds = trs.get(i).select("td");
				for (int j = 0; j < tds.size(); j++) {
					listURLs.add("http://tieba.baidu.com"
							+ tds.get(0).childNode(0).attr("href"));
					break;
				}
			}
			client.closeClient();
			// ǩ�����
			msg = client.signTieba(listURLs);
			
		} 
		return msg;
	}
}
