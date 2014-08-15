//package com.hexor.util;
//
//import org.apache.http.impl.client.BasicCookieStore;
//import org.apache.http.impl.client.CloseableHttpClient;
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStream;
//import java.io.InputStreamReader;
//import java.net.URI;
//import java.nio.charset.Charset;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.regex.Matcher;
//import java.util.regex.Pattern;
//
//import org.apache.http.Header;
//import org.apache.http.HttpHost;
//import org.apache.http.HttpResponse;
//import org.apache.http.HttpStatus;
//import org.apache.http.NameValuePair;
//import org.apache.http.ParseException;
//import org.apache.http.auth.AuthScope;
//import org.apache.http.auth.UsernamePasswordCredentials;
//import org.apache.http.client.CredentialsProvider;
//import org.apache.http.client.entity.UrlEncodedFormEntity;
//import org.apache.http.client.methods.HttpGet;
//import org.apache.http.client.methods.HttpPost;
//import org.apache.http.client.protocol.HttpClientContext;
//import org.apache.http.conn.params.ConnRoutePNames;
//import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
//import org.apache.http.conn.ssl.SSLContexts;
//import org.apache.http.impl.client.BasicCookieStore;
//import org.apache.http.impl.client.BasicCredentialsProvider;
//import org.apache.http.impl.client.CloseableHttpClient;
//import org.apache.http.impl.client.DefaultHttpClient;
//import org.apache.http.impl.client.HttpClientBuilder;
//import org.apache.http.message.BasicHeader;
//import org.apache.http.message.BasicNameValuePair;
//import org.apache.http.params.BasicHttpParams;
//import org.apache.http.params.HttpParams;
//import org.apache.http.util.EntityUtils;
//import org.jsoup.Jsoup;
//import org.jsoup.nodes.Document;
//import org.jsoup.select.Elements;
///*
// * 构造一个Httpclient的类
//   使用的版本为httpclient4.3 新浪的sae只支持4.1.x的 所以此文件不使用
// * 默认的url为登录百度
// * */
//public class ClientUtil {
//	//获得token
//	private String TOKEN_GET_URL = "https://passport.baidu.com/v2/api/?getapi&tpl=mn&apiver=v3&class=login&logintype=dialogLogin";
//	private String LOGIN_POST_URL = "https://passport.baidu.com/v2/api/?login";
//	private final String CHARSET = "GBK";
//	private BasicCookieStore cookieStore = new BasicCookieStore();
//	private String username;
//	private String password;
//	private String token;
//	//不使用CloseableHttpClient。使用Default做代理的client
//	private CloseableHttpClient client;
//	public String getUsername() {
//		return username;
//	}
//	public void setUsername(String username) {
//		this.username = username;
//	}
//	public String getPassword() {
//		return password;
//	}
//	public void setPassword(String password) {
//		this.password = password;
//	}
//	public ClientUtil(String username,String password) {
//		// TODO Auto-generated constructor stub
//		this.username=username;
//		this.password=password;
//	}
//	/**
//	 * 设置头文件信息
//	 * @param Host 
//	 * Host地址为服务器地址
//	 * @param refURL
//	 * 关联的URL
//	 * @author Jony Chang
//	 * @return List<Header>
//	 * */
//	public List<Header> setHead(String Host,String refURL){
//		List<Header>  list  = new ArrayList<Header>();
//		String HEADER_HOST = Host;
//		String HEADER_CONNECTION = "keep-alive";
//		String HEADER_ACCEPT = "*/*";
//		String HEADER_USER_AGENT = "Mozilla/5.0 (Windows NT 6.3; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/33.0.1750.146 Safari/537.36";
//		String HEADER_REFERER = refURL;
//		String HEADER_ACCEPT_ENCODING = "Accept-Encoding";
//		String HEADER_ACCEPT_LANGUAGE = "Accept-Language";
//		list.add(new BasicHeader("Host",Host));
//		list.add(new BasicHeader("Connection", HEADER_CONNECTION));
//		list.add(new BasicHeader("Accept", HEADER_ACCEPT));
//		list.add(new BasicHeader("User-Agent", HEADER_USER_AGENT));
//		list.add(new BasicHeader("Referer", HEADER_REFERER));
//		list.add(new BasicHeader("Accept-Encoding", HEADER_ACCEPT_ENCODING));
//		list.add(new BasicHeader("Accept-Language", HEADER_ACCEPT_LANGUAGE));
//		return list;
//	}
//	/**
//	 * 创建一个closeablehttpclient
//	 * @param host
//	 * 设置服务器地址
//	 * @param refURL
//	 * 设置相关地址
//	 * @author Jony Chang
//	 * 
//	 * */           
//	public void clientCreate(String host,String refURL)
//	{
//		//设置ssl安全证书
//		SSLConnectionSocketFactory sslFactory = new SSLConnectionSocketFactory(SSLContexts.createDefault(), SSLConnectionSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
//		client = HttpClientBuilder.create()
//				.setDefaultCookieStore(cookieStore)
//				.setDefaultHeaders(setHead(host,refURL))
// 				.setSSLSocketFactory(sslFactory)
//				.build();
//		//设置代理
//		
//	}
//	
//	/**
//	 * 获取baidu的token
//	 * @author Jony Chang
//	 * */
//	public void getToken(){
//		//System.out.println("正在设置token参数...");
//		HttpGet get = new HttpGet(TOKEN_GET_URL);
//		HttpResponse response = null;
//		try {
//			response = client.execute(get);
//			String str = EntityUtils.toString(response.getEntity());
//			Pattern pattern = Pattern.compile("token\" : \"(.*?)\"");
//			Matcher matcher = pattern.matcher(str);
//			//System.out.println(str);
//			if(matcher.find()){
//				token = matcher.group(1);
//			}
//			//System.out.println("token ： "+token);
//		} catch (Exception e) {
//			// TODO: handle exception
//			e.printStackTrace();
//		} finally{
//			try {
//				EntityUtils.consume(response.getEntity());
//			} catch (Exception e2) {
//				// TODO: handle exception
//				e2.printStackTrace();
//			}
//		}
//	}
//	/**
//	 * 用于get的函数
//	 * @param URL
//	 * 用于get的URL
//	 * @author Jony Chang
//	 * 
//	 * */
//	public void get(String URL){
//		HttpGet get = new HttpGet(URL);
//		HttpResponse response = null;
//		try {
//			response=client.execute(get);
//			//释放资源
//			get.abort();
//		} catch (Exception e) {
//			e.printStackTrace();
//			// TODO: handle exception
//			}
//	}
//	
//	/**
//	 * 用于get的函数,并且输出返回的信息。
//	 * @param URL
//	 * 用于get的URL
//	 * @author Jony Chang
//	 * 
//	 * */
//	public void getShow(String URL){
//		HttpGet get = new HttpGet(URL);
//		HttpResponse response = null;
//		try {
//			response = client.execute(get);
//			//用于解决中文乱码问题
//			InputStream ins = response.getEntity().getContent();
//			BufferedReader br = new BufferedReader(new InputStreamReader(ins,"GBK"));
//			StringBuffer sbf = new StringBuffer();
//			String content = null;
//			while ((content = br.readLine()) != null)
//			{
//			sbf.append(content+"\n\t");
//			}
//			content = sbf.toString();
//			br.close();
//			//System.out.println(content);
//		} catch (Exception e) {
//			e.printStackTrace();
//			// TODO: handle exception
//			}
//	}
//	/**
//	 * 用于get的函数,并且以document方式输出返回的信息。
//	 * @param URL
//	 * 用于get的URL
//	 * @return Document
//	 * @author Jony Chang
//	 * 
//	 * */
//	public Document getDocumet(String URL,String Charset){
//		Document doc = null;
//		HttpGet get = new HttpGet(URL);
//		HttpResponse response = null;
//		try {
//			response = client.execute(get);
//			int statusCode = response.getStatusLine().getStatusCode(); 
//			//System.out.println(statusCode);
//			if (statusCode == HttpStatus.SC_OK) {
//			//用于解决中文乱码问题
//			InputStream ins = response.getEntity().getContent();
//			doc = Jsoup.parse(ins, Charset, URL);
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//			// TODO: handle exception
//			}
//		return doc;
//	}
//	
//	/**
//	 * 获取新的地址，用途地址重新定向。
//	 * @param URL
//	 * 用于get的URL
//	 * @return String
//	 * @author Jony Chang
//	 * 
//	 * */
//	public String getRedirectLocations(String URL){
//		String reURL = null;
//		Header headers[];
//		HttpGet get = new HttpGet(URL);
//		HttpResponse response = null;
//		try {
//			HttpParams params = new BasicHttpParams();
//			params.setParameter("http.protocol.handle-redirects", false); // 默认不让重定向
//			get.setParams(params);
//			response = client.execute(get);
//			//获取从新的地址
//			headers = response.getAllHeaders();
//			for(Header header: headers){
//				System.out.println(header.getName() + ":  " + header.getValue());
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//			// TODO: handle exception
//			}
//		return reURL;
//	}
//	/**
//	 * 用于验证当前输入的账号密码是否是正确的
//	 * @param URL
//	 * @param entity
//	 * @return
//	 */
//	public String LoginPost(String URL,UrlEncodedFormEntity entity){
//		//返回提示信息
//		String msg="";
//		HttpPost post = new HttpPost(URL);
//		//从新设置post的内容
//		post.setEntity(entity);
//		HttpResponse response = null;
//		try {
//			response = client.execute(post);
//			String t=EntityUtils.toString(response.getEntity());
//			System.out.println(t);
//			//error=0 表示此ip正常 error=257 则ip被禁止  error=4 表示错误的账户名或密码
//			if(t.contains("error=257")){
//				System.out.println("ip被禁止");
//				msg="服务器当前IP被禁止，请隔一段时间后再试!";
//			}else if(t.contains("error=4")){
//				System.out.println("账号名密码输入错误");
//				msg="百度账号名密码输入错误，请检查重新输入!";
//			}else if(t.contains("error=0")){
//				System.out.println("这回真的登录成功");
//				msg="检测成功";
//			}
//		} catch (Exception e) {
//		// TODO: handle exception
//			e.printStackTrace();
//		} finally{
//			try {
//				EntityUtils.consume(response.getEntity());
//			} catch (Exception e2) {
//				// TODO: handle exception
//				e2.printStackTrace();
//			}
//		}
//		return msg;
//	}
//	
//	/**
//	 * 专门用于post的函数
//	 * @param URL
//	 * post的URL
//	 * @param entity
//	 * post的内容
//	 * @author Jony Chang
//	 * 
//	 * */
//	public void post(String URL,UrlEncodedFormEntity entity)
//	{
//		HttpPost post = new HttpPost(URL);
//		//从新设置post的内容
//		post.setEntity(entity);
//		HttpResponse response = null;
//		try {
//			response = client.execute(post);
//			String str = EntityUtils.toString(response.getEntity());
//			System.out.println(str);
//		} catch (Exception e) {
//		// TODO: handle exception
//			e.printStackTrace();
//		} finally{
//			try {
//				EntityUtils.consume(response.getEntity());
//			} catch (Exception e2) {
//				// TODO: handle exception
//				e2.printStackTrace();
//			}
//		}
//	}
//	
//	/**
//	 * 专门用于post的函数,且显示出返回的信息。
//	 * @param URL
//	 * post的URL
//	 * @param entity
//	 * post的内容
//	 * @author Jony Chang
//	 * 
//	 * */
//	public void showPost(String URL,UrlEncodedFormEntity entity)
//	{
//		HttpPost post = new HttpPost(URL);
//		//从新设置post的内容
//		post.setEntity(entity);
//		HttpResponse response = null;
//		try {
//			response = client.execute(post);
//			//用于解决中文乱码问题
//			InputStream ins = response.getEntity().getContent();
//			BufferedReader br = new BufferedReader(new InputStreamReader(ins,"GBK"));
//			StringBuffer sbf = new StringBuffer();
//			String content = null;
//			while ((content = br.readLine()) != null)
//			{
//				sbf.append(content+"\n\t");
//			}
//			content = sbf.toString();
//			br.close();
//			System.out.println(content);
//		} catch (Exception e) {
//			// TODO: handle exception
//			e.printStackTrace();
//		} finally{
//			try {
//				EntityUtils.consume(response.getEntity());
//			} catch (Exception e2) {
//				// TODO: handle exception
//				e2.printStackTrace();
//			}
//		}
//	}
//	
//	/**
//	 * 专门用于post的函数,且以document出返回的信息。
//	 * @param URL
//	 * post的URL
//	 * @param entity
//	 * post的内容
//	 * @author Jony Chang
//	 * 
//	 * */
//	public Document postDocument(String URL,UrlEncodedFormEntity entity)
//	{
//		Document doc = null;
//		HttpPost post = new HttpPost(URL);
//		//从新设置post的内容
//		post.setEntity(entity);
//		HttpResponse response = null;
//		try {
//			response = client.execute(post);
//			//用于解决中文乱码问题
//			InputStream ins = response.getEntity().getContent();
//			doc = Jsoup.parse(ins, "GBK", URL);
//		} catch (Exception e) {
//			// TODO: handle exception
//			e.printStackTrace();
//		} finally{
//			try {
//				EntityUtils.consume(response.getEntity());
//			} catch (Exception e2) {
//				// TODO: handle exception
//				e2.printStackTrace();
//			}
//		}
//		return doc;
//	}
//	
//	
//	
//	/**
//	 * 设置post表单的内容
//	 * @return UrlEncodedFormEntity
//	 * @author Jony Chang
//	 * 
//	 * */
//	public UrlEncodedFormEntity produceFormEntity(){
//		List<NameValuePair> list = new ArrayList<NameValuePair>();
//		list.add(new BasicNameValuePair("tt", ""+System.currentTimeMillis()));
//		list.add(new BasicNameValuePair("tpl", "mn"));
//		list.add(new BasicNameValuePair("token", token));
//		list.add(new BasicNameValuePair("isPhone", ""));
//		list.add(new BasicNameValuePair("username", username));
//		list.add(new BasicNameValuePair("password", password));
//		UrlEncodedFormEntity encodedFormEntity = null;
//		try {
//			encodedFormEntity = new UrlEncodedFormEntity(list,"utf-8");
//		} catch (Exception e) {
//			// TODO: handle exception
//			e.printStackTrace();
//			return null;
//		}
//		return encodedFormEntity;
//	}
//	/**
//	 * 检验是否是登录上baidu
//	 * @return boolean
//	 * @author Jony Chang
//	 * 
//	 * */
//	public boolean checkLogin(){
//		HttpGet get = new HttpGet("http://www.baidu.com");
//		HttpResponse response = null;
//		boolean res = false;
//		try {
//			response = client.execute(get);
//			String content = EntityUtils.toString(response.getEntity());
// 			// System.out.println(content);
//			// System.out.println("Cookie:"+cookieStore.getCookies());
//			if(!content.contains("登录")){
//				res = true;
//			}
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} finally{
//			try {
//				EntityUtils.consume(response.getEntity());
//			} catch (Exception e2) {
//				// TODO: handle exception
//			}
//		}
//		return res;
//	}
//	/**
//	 * 关闭Httpclient
//	 * @author Jony Chang
//	 * */
//	public void closeClient()
//	{
//		//释放client
//				try {
//					client.close();
//				} catch (IOException e1) {
//					// TODO Auto-generated catch block
//					e1.printStackTrace();
//				}
//	}
//	
//	/**
//	 * 用于登录贴吧
//	 * */
//	public String signTieba(List<String> listURLs){
//		//遍历各个URL
//		String tiebaName =null;
//		String tiebaID =null;
//		for(String url:listURLs){
//			clientCreate("tieba.baidu.com","http://tieba.baidu.com/f/like/mylike");
//			tiebaName = getDocumet(url, "GBK").select("meta").attr("fname").toString();
//			Elements scripts = getDocumet(url, "GBK").select("script");
//			for(int i = 0;i<scripts.size();i++)
//			{
//				//获取页面中的"PageData.tbs"
//				if(scripts.get(i).data().contains("PageData.tbs")){
//					tiebaID=scripts.get(i).data().split("\"")[1];
//					break;
//				}
//			}
//			closeClient();
//			System.out.println("正在签到："+tiebaName);
//			clientCreate("tieba.baidu.com",url);
//			List<NameValuePair> list1 = new ArrayList<NameValuePair>();
//			list1.add(new BasicNameValuePair("ie", "utf-8"));
//			list1.add(new BasicNameValuePair("kw", tiebaName));
//			list1.add(new BasicNameValuePair("tbs", tiebaID));
//			UrlEncodedFormEntity encodedFormEntity = null;
//			try {
//				encodedFormEntity = new UrlEncodedFormEntity(list1,"utf-8");
//			} catch (Exception e) {
//				// TODO: handle exception
//				e.printStackTrace();
//			}
//			post("http://tieba.baidu.com/sign/add", encodedFormEntity);
//			closeClient();
//		}
//		return "签到成功";
//	}
//}
