package com.hexor.util;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

public class Springfactory implements BeanFactoryAware {  
	  
    private static BeanFactory beanFactory;  
  
    public void setBeanFactory(BeanFactory factory) throws BeansException {  
        this.beanFactory = factory;  
    }  
  
    /** 
     * 根据beanName名字取得bean 
     *  
     * @param beanName 
     * @return 
     */  
    public static <T> T getBean(String beanName) {  
        if (null != beanFactory) {  
            return (T) beanFactory.getBean(beanName);  
        }  
        return null;  
    }  
  
}  
