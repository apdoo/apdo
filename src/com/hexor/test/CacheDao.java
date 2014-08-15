package com.hexor.test;

import java.util.Collection;

import java.util.HashMap;
import java.util.Map;

import org.springframework.cache.Cache;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service("test.cache")
public class CacheDao {
	private Map<String, User> dataMap = new HashMap<String, User>();  
	 @Cacheable(value = "data")  
    public void add(User data) {  
        System.out.println("Execute：public void add(Data data)");  
        dataMap.put(data.getName(), data);  
    }  
  
  
    @Cacheable(value = "data")  
    public User find(String id) {  
        System.out.println("Execute：public Data find(Integer id)");  
        return dataMap.get(id);  
    }  
 
  
    @CacheEvict(value = "data", allEntries = true)  
    public void delete(String id) {  
        System.out.println("Execute：public void delete(Integer id)");  
        dataMap.remove(id);  
    }  
  
    @Cacheable(value = "data")  
    public Collection<User> getAll() {  
        System.out.println("Execute：public Collection<Data> getAll()");  
        return dataMap.values();  
    }  
    /**
     * 查找方法
     * */
    @Cacheable(value="data",key="#name")
    public User fuck( String name){
    	System.out.println("缓存中没有, 进入方法进行查找" + name);  
    	return dataMap.get(name);
    }
    /**
     * 放入缓存
     * */
    @CachePut(value="data",key="#user.name")
    public User putCache(User user){
    	   dataMap.put(user.getName(), user);  
    	   return dataMap.get(user.getName());
    }
    
    
}

class User{
	public User(String name, String pwd) {
		super();
		this.name = name;
		this.pwd = pwd;
	}
	private String name;
	private String pwd;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	
}
