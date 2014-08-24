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

      <p style=" text-align: center;">
          96年丁字裤小骚逼说她好痛<br/>
          <video width="320" height="240" controls="controls">
              <source src="${pageContext.request.contextPath}/resources/video/t4.mp4" type="video/mp4" >示例视频1</source>
              您的浏览器不支持video标签
          </video>
      </p>
      <p style=" text-align: center;">
          3P第一次<br/>
          <video width="320" height="240" controls="controls">
              <source src="${pageContext.request.contextPath}/resources/video/t1.mp4" type="video/mp4" >示例视频1</source>
              您的浏览器不支持video标签
          </video>
      </p>
      <p style=" text-align: center;">
          后入感受美臀的后座力<br>
          <video width="320" height="240" controls="controls">
              <source src="${pageContext.request.contextPath}/resources/video/t2.mp4" type="video/mp4" >示例视频1</source>
              您的浏览器不支持video标签
          </video>
      </p>
      <p style=" text-align: center;">
          <video width="320" height="240" controls="controls">
              騒妻2.mp4
              <source src="${pageContext.request.contextPath}/resources/video/t3.mp4" type="video/mp4" >示例视频1</source>
              您的浏览器不支持video标签
          </video>
      </p>

  	<jsp:include page="bottom.jsp"></jsp:include>
  </body>
</html>
