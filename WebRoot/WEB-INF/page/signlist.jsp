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
	//alert(eval(data).length);  data为json数组长度 var data=${list};
	$(function() {
		pagecreat();
		
	});
	//分页和内容生成
	function pagecreat(){
		var list=${list};
		 //循环处理数组生成table
		 $.each(list, function(key, val) {      
			 //数组　  
			 console.info(val.username);
			 //列表
			 $("#mytable").append("<tr><td>"+(key+1)+"</td><td>"+val.username+"</td><td>"+val.datetime+"</td><td>"+val.signdate+"</td><td>"+val.flag+"</td></tr>");
	 　　});  
		//当前页数
		var current=${pager.currentPage};
		//总页数   
		var pageCount=${pager.pageCount};
		//处理object数组
		var list=${list};
		//生成上一页箭头
		$("#pagenumber").append("<a>«上一页</a>");
		//处理长度 生成页数
		if(pageCount>9){
			//当页数大于9页的时候。创建首页
			if(current!=1){
				 $("#pagenumber").append("<a class='page-numbers'  >"+1+"</a><span>...</span>");
			} 
			 $("#pagenumber").append("<a class='current'  >"+current+"</a><span class='divider'>/</span>");
			for(var i=0;i<3;i++){
			 //循环当前页后三页
			 	if(current<(pageCount-2)){
				 	$("#pagenumber").append("<a class='page-numbers'  >"+(current+1+i)+"</a><span class='divider'>/</span>");	
			 	}
			}
			//末页
			if(current!=pageCount){
				 $("#pagenumber").append("<span>...</span><a class='page-numbers'  >"+pageCount+"</a>");
			} 
			
		}else{
			//小于9页的时候 直接生成
			for(var i=0;i<pageCount;i++){
				 //判断当前页则修改为current样式
				 if((i+1)==current){
					 $("#pagenumber").append("<a class='current'  >"+(i+1)+"</a><span class='divider'>/</span>");
				 	 
				 }else{
					 $("#pagenumber").append("<a class='page-numbers' >"+(i+1)+"</a><span class='divider'>/</span>");
				 }
			}
		}
		//生成上=下一页箭头
		$("#pagenumber").append("<a >下一页»</a>");
		 //选择pagernumber下所有的子元素<a> 遍历添加href
			 $("#pagenumber").children("a").each(function(){
				 var j=$(this).html();
				 if(j=="«上一页"){
					 if(current==1){
						 $(this).attr("href","${pageContext.request.contextPath}/sign/signlist?wpage="+1);
					 }else{
						 $(this).attr("href","${pageContext.request.contextPath}/sign/signlist?wpage="+(current-1));
					 }
					 
				 }else if(j=="下一页»"){
					 if(current==pageCount){
						 $(this).attr("href","${pageContext.request.contextPath}/sign/signlist?wpage="+pageCount);
					 }else{
						 $(this).attr("href","${pageContext.request.contextPath}/sign/signlist?wpage="+(current+1));
					 }
					 
				 }else{
					 $(this).attr("href","${pageContext.request.contextPath}/sign/signlist?wpage="+j);
				 }
			 });
	}
	</script>
  </head>
  
  <body>
  	<jsp:include page="header.jsp"></jsp:include>
	<div id="content" class="clearfix">
		  <table id="mytable" cellspacing="0" style="width: 940px;">
		   <tr>
	    	<th scope="col" >编号</th>
	    	<th scope="col" >账号</th>
	   		 <th scope="col"  >登记时间</th>
			<th scope="col"    >执行时间</th>
			<th scope="col"  >结果</th>
 		  </tr>
	</table>
		</div>
		
	<div class="navigation page-navigation" style="margin: auto;"
		id="pagenumber"></div>
	<jsp:include page="bottom.jsp"></jsp:include>
  </body>
</html>
