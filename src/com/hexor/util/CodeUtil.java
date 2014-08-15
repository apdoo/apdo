package com.hexor.util;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

/** 
 * @author  hexd
 * 创建时间：2014-5-29 下午2:33:42 
 * 类说明 
 * 用来生成6位激活码
 */
public class CodeUtil {
	 //激活码
	  private static String codeChars = "0123456789abcdefghkmnpqrstuvwxyzABCDEFGHKLMNPQRSTUVWXYZ"; 
	  //插入
	  private static String head="insert into accode (code,type) values('";
	  private static String bottom=",'1');";
	  public static void main(String[] args) {
	      Random random = new Random(); 
	      int charsLength=codeChars.length();
	      //使用hashSet来保证都是不同的随机数
	      Set<String> set = new HashSet<String>();
	      Random rand = new Random();
	      for(int i=0;i<1000;i++){
	  		StringBuilder validationCode = new StringBuilder();
			// 六位
			for (int j = 0; j < 6; j++) {
				// 随机获得当前验证码的字符
				char codeChar = codeChars.charAt(random.nextInt(charsLength));
				validationCode.append(codeChar);
			}
			set.add(validationCode.toString());
	      }
	      for (String str : set) {  
	          System.out.println(head+str+"'"+bottom);  
	    }  

	}
}
