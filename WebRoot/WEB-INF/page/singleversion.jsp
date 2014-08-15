<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>百度贴吧自动签到v1.0</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
		<link rel="shortcut icon" href="${pageContext.request.contextPath}/resources/img/favicon.ico"> 	
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/resources/css/style.css">
	<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/resources/css/forms.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/resources/css/button.css">
	<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/resources/css/messagebox.css">
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-2.1.0.min.js"></script>
	<script type="text/javascript">
	//可以直接在js中写el表达式的：var username = "${username}";
	//alert(eval(data).length);  data为object数组var data=${list};
	$(function() {
   
	});
	</script>
  </head>
  
  <body>
  	<jsp:include page="header.jsp"></jsp:include>
  	<div id="content" class="clearfix" >
  		<h5>客户端版介绍</h5>
  		客户端版本支持百度贴吧与百度文库的每日签到功能，百度文库签到能够获取每日财富，方便在百度文库下载各种文件资料！
 		<div style="margin: auto;">
 			<p style=" text-align: center;"> 
  		<img   alt="图片读取失败" src="${pageContext.request.contextPath}/resources/img/2.PNG">
  		</p>
  			<p style=" text-align: center;">
  		<img   alt="图片读取失败" src="${pageContext.request.contextPath}/resources/img/3.PNG">
  		</p>
 		</div>
 		<p>
 			由于百度官方的登录接口，贴吧登录签到接口以及文库签到接口会不定时的更新变动，
 			作者会一直更新客户端版的最近版本。有需要的同学可以联系作者或直接点击进入
 			<a href="http://item.taobao.com/item.htm?spm=a1z10.1.w4004-7500619784.2.yNlscZ&id=39404974728" target="_blank">客户端版本</a>
 		</p>
  	</div>
 		
  	<jsp:include page="bottom.jsp"></jsp:include>
  </body>
</html>
