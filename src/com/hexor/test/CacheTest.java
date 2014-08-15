package com.hexor.test;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.Cache;
import org.springframework.cache.Cache.ValueWrapper;
import org.springframework.cache.CacheManager;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hexor.util.CacheUtil;
import com.hexor.util.Springfactory;


@Controller
@RequestMapping("/cache")
public class CacheTest extends ConcurrentMapCacheManager{
	@Autowired
    @Qualifier("test.cache")
    private CacheDao cd;
    public void setCd(CacheDao cd) {
		this.cd = cd;
	}
    
    //ģ����Դ����ݿ��в�ѯ���뻺��
	@RequestMapping("/c")
	@ResponseBody              //��������ͼ
	public void test1(HttpSession session, ModelMap model){
		System.out.println("ttttt");
		cd.add(new User("h1","1"));
		cd.add(new User("h2","2"));
		cd.add(new User("h3","3"));
	    Collection<User> dataCollection = cd.getAll();  
	    System.out.println(dataCollection.size());  
        dataCollection = cd.getAll();  
        User data = cd.find("h1");  
        System.out.println(data.getName());
	}
	@RequestMapping("/gc")
	@ResponseBody        //��������ͼ
	public void getC(HttpSession session, ModelMap model){
		Cache a=getCache("find");
		//�ҵ�����find  Ȼ���key=h2�Ļ������find�����������
		a.put("h2", new User("h2name","pwd"));
		System.out.println(a.getName()+".."+a.toString());
		ValueWrapper v=	a.get("h2");
		if(v==null){
				System.out.println("null");
			}else{
				User u=(User) v.get();
				System.out.println(u.getName());
			}
		}
	@RequestMapping("/ct")
	@ResponseBody        //��������ͼ
	public void cachetest(HttpSession session, ModelMap model){

		//����һ���µĻ��� ���ֲ���ͨ��spring�����Ļ��棬����spring���������������@Cachexxx�ı���Ҳ�����Ӧ����
		Cache a=CacheUtil.getInstance().creatCache("user");
		//��ó�ʼ����ʱ�򴴽����� a  ���Լ������Ļ��� ʹ��spring ����spring���������÷�Χ 
		//spitter-servlet.xml �����õĻ���
		Cache data=CacheUtil.getInstance().getCacheByname("data");
		
		//
		
		System.out.println("----------��������-------*");
		//��data���治��ͨ��spring������õĻ��� ����� ��fuck�����е�data�����ȡ������
		data.put("h1", new User("h1","1"));
		data.put("h2", new User("h2","2"));
		data.put("h3", new User("h2","2"));
		System.out.println("----------�������ݽ���-------*");
		System.out.println("a��������:"+a.getName());
		System.out.println("spring������������:"+data.getName());
		System.out.println("------------------");
		ValueWrapper v=	data.get("h2");
		if(v==null){
				System.out.println("null");
			}else{
				User u=(User) v.get();
				System.out.println("cccccc.."+u.getName());
			}
			User u=(User)cd.fuck("h2");
			if(u==null){
				System.out.println("uuunull");
			}else {
				System.out.println(u.getPwd());
			}
			
		
	}

}
