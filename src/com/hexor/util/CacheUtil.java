package com.hexor.util;

import org.springframework.cache.Cache;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;

public class CacheUtil extends ConcurrentMapCacheManager{
	
	/**
	 * ˽�л����캯��
	 * */
	private CacheUtil(){
	}
	private static CacheUtil cacheUtil=null;
	//����ģʽ
	public static CacheUtil getInstance(){
		if(cacheUtil==null){
			cacheUtil=new CacheUtil();
		}
		return cacheUtil;
	}
	/**
	 * ��û���
	 * */
	public   Cache getCacheByname(String name){
		return this.getCache(name);
	}
	/**
	 * ���������ػ���
	 * */
	public Cache creatCache(String name){
		return this.createConcurrentMapCache(name);
	}
}
