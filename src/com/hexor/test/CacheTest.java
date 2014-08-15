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
    
    //模拟测试从数据库中查询放入缓存
	@RequestMapping("/c")
	@ResponseBody              //不返回视图
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
	@ResponseBody        //不返回视图
	public void getC(HttpSession session, ModelMap model){
		Cache a=getCache("find");
		//找到缓存find  然后把key=h2的缓存放入find的这个缓存中
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
	@ResponseBody        //不返回视图
	public void cachetest(HttpSession session, ModelMap model){

		//创建一个新的缓存 此种不是通过spring创建的缓存，不在spring容器作用里，所以用@Cachexxx的标记找不到相应缓存
		Cache a=CacheUtil.getInstance().creatCache("user");
		//获得初始化的时候创建缓存 a  是自己创建的缓存 使用spring 不在spring容器的作用范围 
		//spitter-servlet.xml 中配置的缓存
		Cache data=CacheUtil.getInstance().getCacheByname("data");
		
		//
		
		System.out.println("----------插入数据-------*");
		//此data缓存不是通过spring容器获得的缓存 插入后 在fuck方法中的data缓存获取不到？
		data.put("h1", new User("h1","1"));
		data.put("h2", new User("h2","2"));
		data.put("h3", new User("h2","2"));
		System.out.println("----------插入数据结束-------*");
		System.out.println("a缓存名字:"+a.getName());
		System.out.println("spring创建缓存名字:"+data.getName());
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
