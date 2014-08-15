package com.hexor.util;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import javax.xml.ws.Response;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

/** 
 * @author  hexd
 * 创建时间：2014-5-26 下午2:32:49 
 * 类说明 
 * 使用HTTPclient4.1版本 为了兼容新浪sae
 */
public class ClientUtil4_1 {
	//获得token
		private String TOKEN_GET_URL = "https://passport.baidu.com/v2/api/?getapi&tpl=mn&apiver=v3&class=login&logintype=dialogLogin";
		private String LOGIN_POST_URL = "https://passport.baidu.com/v2/api/?login";
		private final String CHARSET = "GBK";
		private BasicCookieStore cookieStore = new BasicCookieStore();
		private String username;
		private String password;
		private String token;
		private DefaultHttpClient client;
		private HttpPost post=new HttpPost();
		private HttpGet get=new HttpGet();
		/**
		 * 构造函数，输入用户信息(email)和密码(password)
		 * 
		 * @param email
		 * 用户的用户名，必须为email登录
		 * @param password
		 * 用户的密码
		 * @throws NoSuchAlgorithmException 
		 * @throws KeyManagementException 
		 * 
		 */
		public ClientUtil4_1(String username,String password) throws KeyManagementException, NoSuchAlgorithmException {
			// TODO Auto-generated constructor stub
			this.username=username;
			this.password=password;
		}
	    private static X509TrustManager tm = new X509TrustManager() {  
	        public void checkClientTrusted(X509Certificate[] xcs, String string)  
	                throws CertificateException {  
	        }  
	        public void checkServerTrusted(X509Certificate[] xcs, String string)  
	                throws CertificateException {  
	        }  
	        public X509Certificate[] getAcceptedIssuers() {  
	            return null;  
	        }  
	    };  
	  
	    public  DefaultHttpClient getInstance() throws KeyManagementException,  
	            NoSuchAlgorithmException {  
	    	DefaultHttpClient client = new DefaultHttpClient();  
	        SSLContext ctx = SSLContext.getInstance("TLS");  
	        ctx.init(null, new TrustManager[] { tm }, null);  
	        SSLSocketFactory ssf = new SSLSocketFactory(ctx);  
	        ssf.setHostnameVerifier(SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);  
	        ClientConnectionManager ccm = client.getConnectionManager();  
	        SchemeRegistry sr = ccm.getSchemeRegistry();  
	        sr.register(new Scheme("https", ssf, 443));  
	        client = new DefaultHttpClient(ccm, client.getParams());  
	        client.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT,50000);
	        return client;  
	    }  
	    
		public void clientCreate(String host,String refURL) throws KeyManagementException, NoSuchAlgorithmException
		{
			//初始化client
			client=getInstance();
			//设置cookie
			client.setCookieStore(cookieStore);
			//设置post和get请求头
			String HEADER_HOST = host;
			String HEADER_CONNECTION = "keep-alive";
			String HEADER_ACCEPT = "*/*";
			String HEADER_USER_AGENT = "Mozilla/5.0 (Windows NT 6.3; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/33.0.1750.146 Safari/537.36";
			String HEADER_REFERER = refURL;
			String HEADER_ACCEPT_ENCODING = "Accept-Encoding";
			String HEADER_ACCEPT_LANGUAGE = "Accept-Language";
			//-
			post.setHeader("Host",host);
			post.setHeader("Connection", HEADER_CONNECTION);
			post.setHeader("Accept", HEADER_ACCEPT);
			post.setHeader("User-Agent", HEADER_USER_AGENT);
			post.setHeader("Referer", HEADER_REFERER);
			post.setHeader("Accept-Encoding", HEADER_ACCEPT_ENCODING);
			post.setHeader("Accept-Language", HEADER_ACCEPT_LANGUAGE);
			//-
			get.setHeader("Host",host);
			get.setHeader("Connection", HEADER_CONNECTION);
			get.setHeader("Accept", HEADER_ACCEPT);
			get.setHeader("User-Agent", HEADER_USER_AGENT);
			get.setHeader("Referer", HEADER_REFERER);
			get.setHeader("Accept-Encoding", HEADER_ACCEPT_ENCODING);
			get.setHeader("Accept-Language", HEADER_ACCEPT_LANGUAGE);
		}
		/**
		 * 用于get的函数
		 * @param URL
		 * 用于get的URL
		 * @throws CloneNotSupportedException 
		 * 
		 * */
		public void get(String URL) throws CloneNotSupportedException{
			HttpGet get =getGetInstance(URL);
			try {
//				HttpResponse r=client.execute(get);
//				HttpEntity entity = r.getEntity();
//				System.out.println(EntityUtils.toString(entity));
				client.execute(get);
				//释放资源
				get.abort();
			} catch (Exception e) {
				e.printStackTrace();
				// TODO: handle exception
				}
		}
		/**
		 * 获取baidu的token
		 * @throws CloneNotSupportedException 
		 * */
		public void getToken() throws CloneNotSupportedException{
			//System.out.println("正在设置token参数...");
			HttpGet get = getGetInstance(TOKEN_GET_URL);
			HttpResponse response = null;
			try {
				response = client.execute(get);
				String str = EntityUtils.toString(response.getEntity());
				Pattern pattern = Pattern.compile("token\" : \"(.*?)\"");
				Matcher matcher = pattern.matcher(str);
				//System.out.println(str);
				if(matcher.find()){
					token = matcher.group(1);
				}
				 //System.out.println("token ： "+token);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			} finally{
				try {
					EntityUtils.consume(response.getEntity());
				} catch (Exception e2) {
					// TODO: handle exception
					e2.printStackTrace();
				}
			}
		}
		/**
		 * 设置post表单的内容
		 * @return UrlEncodedFormEntity
		 * 
		 * */
		public UrlEncodedFormEntity produceFormEntity(){
			List<NameValuePair> list = new ArrayList<NameValuePair>();
			list.add(new BasicNameValuePair("tt", ""+System.currentTimeMillis()));
			list.add(new BasicNameValuePair("tpl", "mn"));
			list.add(new BasicNameValuePair("token", token));
			list.add(new BasicNameValuePair("isPhone", ""));
			list.add(new BasicNameValuePair("username", username));
			list.add(new BasicNameValuePair("password", password));
			UrlEncodedFormEntity encodedFormEntity = null;
			try {
				encodedFormEntity = new UrlEncodedFormEntity(list,"utf-8");
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				return null;
			}
			return encodedFormEntity;
		}
		/**
		 * 用于验证当前输入的账号密码是否是正确的
		 * @param URL
		 * @param entity
		 * @return
		 * @throws CloneNotSupportedException 
		 */
		public String LoginPost(String URL,UrlEncodedFormEntity entity) throws CloneNotSupportedException{
			//返回提示信息
			String msg=TipMsgUtil.LOGIN_UNUSUAL;
			HttpPost post = getPostInstance(URL);
			//从新设置post的内容
			post.setEntity(entity);
			HttpResponse response = null;
			try {
				response = client.execute(post);
				String t=EntityUtils.toString(response.getEntity());
				//System.out.println(t);
				//error=0 表示此ip正常 error=257 则ip被禁止  error=4 表示错误的账户名或密码
				if(t.contains("error=257")){
					msg=TipMsgUtil.LOGIN_IP_BAN;
				}else if(t.contains("error=4")){
					//System.out.println("账号名密码输入错误");
					msg=TipMsgUtil.LOGIN_ACC_ERROR;
				}else if(t.contains("error=0")){
					//System.out.println("这回真的登录成功");
					msg=TipMsgUtil.LOGIN_SUCCESS;
				}else{
					//System.out.println("其他情况");
					msg=TipMsgUtil.LOGIN_UNUSUAL;
				}
			} catch (Exception e) {
			// TODO: handle exception
				e.printStackTrace();
			} finally{
				try {
					EntityUtils.consume(response.getEntity());
				} catch (Exception e2) {
					// TODO: handle exception
					msg=TipMsgUtil.LOGIN_UNUSUAL;
					e2.printStackTrace();
					return msg;
				}
			}
			return msg;
		}
		/**
		 * 专门用于post的函数
		 * @param URL
		 * post的URL
		 * @param entity
		 * post的内容
		 * 
		 * */
		public void post(String URL,UrlEncodedFormEntity entity)
		{
			HttpPost post = new HttpPost(URL);
			//从新设置post的内容
			post.setEntity(entity);
			HttpResponse response = null;
			try {
				response = client.execute(post);
				String str = EntityUtils.toString(response.getEntity());
				//System.out.println("post"+str);
			} catch (Exception e) {
			// TODO: handle exception
				e.printStackTrace();
			} finally{
				try {
					EntityUtils.consume(response.getEntity());
				} catch (Exception e2) {
					// TODO: handle exception
					e2.printStackTrace();
				}
			}
		}
		/**
		 * 关闭Httpclient
		 * */
		public void closeClient()
		{
			client.getConnectionManager().shutdown();
		}
		/**
		 * 用于get的函数,并且以document方式输出返回的信息。
		 * @param URL
		 * 用于get的URL
		 * @return Document
		 * 
		 * */
		public Document getDocumet(String URL,String Charset){
			Document doc = null;
			HttpGet get = new HttpGet(URL);
			HttpResponse response = null;
			try {
				response = client.execute(get);
				int statusCode = response.getStatusLine().getStatusCode(); 
				//System.out.println(statusCode);
				if (statusCode == HttpStatus.SC_OK) {
				//用于解决中文乱码问题
				InputStream ins = response.getEntity().getContent();
				doc = Jsoup.parse(ins, Charset, URL);
				}
			} catch (Exception e) {
				e.printStackTrace();
				// TODO: handle exception
				}
			return doc;
		}
		/**
		 * 用于get的函数,并且输出返回的信息。
		 * @param URL
		 * 用于get的URL
		 * 
		 * */
		public void getShow(String URL){
			HttpGet get = new HttpGet(URL);
			HttpResponse response = null;
			try {
				response = client.execute(get);
				//用于解决中文乱码问题
				InputStream ins = response.getEntity().getContent();
				BufferedReader br = new BufferedReader(new InputStreamReader(ins,"GBK"));
				StringBuffer sbf = new StringBuffer();
				String content = null;
				while ((content = br.readLine()) != null)
				{
				sbf.append(content+"\n\t");
				}
				content = sbf.toString();
				br.close();
				//System.out.println(content);
			} catch (Exception e) {
				e.printStackTrace();
				// TODO: handle exception
				}
		}
		/**
		 * 用于登录贴吧
		 * @throws NoSuchAlgorithmException 
		 * @throws KeyManagementException 
		 * */
		public String signTieba(List<String> listURLs) throws KeyManagementException, NoSuchAlgorithmException{
			//遍历各个URL
			String tiebaName =null;
			String tiebaID =null;
			for(String url:listURLs){
				clientCreate("tieba.baidu.com","http://tieba.baidu.com/f/like/mylike");
				//System.out.println(url+"&fr=index"); 访问错误的时候主要贴吧url 然后添加
				tiebaName = getDocumet(url, "GBK").select("meta").attr("fname").toString();
				Elements scripts = getDocumet(url, "GBK").select("script");
				for(int i = 0;i<scripts.size();i++)
				{
					//获取页面中的"PageData.tbs"
					if(scripts.get(i).data().contains("PageData.tbs")){
						tiebaID=scripts.get(i).data().split("\"")[1];
						break;
					}
				}
				closeClient();
				//System.out.println("正在签到："+tiebaName);
				clientCreate("tieba.baidu.com",url);
				List<NameValuePair> list1 = new ArrayList<NameValuePair>();
				list1.add(new BasicNameValuePair("ie", "utf-8"));
				list1.add(new BasicNameValuePair("kw", tiebaName));
				list1.add(new BasicNameValuePair("tbs", tiebaID));
				UrlEncodedFormEntity encodedFormEntity = null;
				try {
					encodedFormEntity = new UrlEncodedFormEntity(list1,"utf-8");
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
				post("http://tieba.baidu.com/sign/add", encodedFormEntity);
				closeClient();
			}
			return TipMsgUtil.SIGN_SUCCESS;
		}
		public HttpPost getPostInstance(String URL) throws CloneNotSupportedException{
			HttpPost p=new HttpPost();
			p=(HttpPost) this.post.clone();
			p.setURI(URI.create(URL));
			return p;
		}
		public HttpGet getGetInstance(String URL) throws CloneNotSupportedException{
			HttpGet g=new HttpGet();
			//克隆get的header给新的HttpGet
			g=(HttpGet) this.get.clone();
			g.setURI(URI.create(URL));
			return g;
		}
}
