package com.hexor.util;

import org.springframework.cache.Cache;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;

public class CacheUtil extends ConcurrentMapCacheManager{
	
	/**
	 * 私有化构造函数
	 * */
	private CacheUtil(){
	}
	private static CacheUtil cacheUtil=null;
	//单例模式
	public static CacheUtil getInstance(){
		if(cacheUtil==null){
			cacheUtil=new CacheUtil();
		}
		return cacheUtil;
	}
	/**
	 * 获得缓存
	 * */
	public   Cache getCacheByname(String name){
		return this.getCache(name);
	}
	/**
	 * 创建并返回缓存
	 * */
	public Cache creatCache(String name){
		return this.createConcurrentMapCache(name);
	}
}
