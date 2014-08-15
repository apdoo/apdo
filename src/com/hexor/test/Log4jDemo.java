package com.hexor.test;


import org.apache.log4j.Logger;
/**
 * Log4j实例;
 */
public class Log4jDemo {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Logger logger = Logger.getLogger(Log4jDemo.class);
		
		//记录日志,从最低到最高的次序为：debug->info->warn->error
		logger.debug("debug leave!");
		logger.info("info leave!");
		logger.warn("warn leave!");
		logger.error("error leave!");
		
	}
}

