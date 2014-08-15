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
	//alert(eval(data).length);  data为json数组长度 var data="${list}";
	$(function() {
		pagecreat();
		//留言事件
		$("#mbsend").click(function(){
			var username=$("#mbname").val();
			var message=$("#mbmessage").val();
			var ulength=$("#mbname").val().length;
			var mlength=$("#mbmessage").val().length;
			if(username==""||message==""){
				alert("用户昵称或留言内容不能为空！");
				return false;
			}if(ulength>8){
				alert("用户昵称最多八个字！");
				return false;
			}if(mlength>120){
				alert("请输入少于120字的留言！");
				return false;
			}
			$.ajax({
				type : "POST",
				url : "${pageContext.request.contextPath}/message/send",
				data : {
					username : username,
					message : message
				},
			    dataType:"json",
				success : function(data) {
					alert(data.msg);
					//跳转页内标记
					location.reload();
				},
				//请求出错的处理
	              error:function(){
                     alert("请求出错");
            }
			});
		});
	});
	
	//分页和内容生成
	function pagecreat(){
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
						 $(this).attr("href","${pageContext.request.contextPath}/message/home?wpage="+1);
					 }else{
						 $(this).attr("href","${pageContext.request.contextPath}/message/home?wpage="+(current-1));
					 }
					 
				 }else if(j=="下一页»"){
					 if(current==pageCount){
						 $(this).attr("href","${pageContext.request.contextPath}/message/home?wpage="+pageCount);
					 }else{
						 $(this).attr("href","${pageContext.request.contextPath}/message/home?wpage="+(current+1));
					 }
					 
				 }else{
					 $(this).attr("href","${pageContext.request.contextPath}/message/home?wpage="+j);
				 }
			 });
		 //循环处理数组
		 $.each(list, function(key, val) {      
			 //数组　  
			 $("#message").append("<div class='ds-comment-body'><div class='user-name'>"+val.username+"</div><p>"+val.message+"</p>"+"<div class='ds-foot'>"+"<span class='ds-time'>"+val.datetime+"</span></div></div><br>");
	 　　});  
	}
	</script>
  </head>

<body>
	<jsp:include page="header.jsp"></jsp:include>
	<div id="message"></div>
	<div class='ds-comment-body'>
		<form method="post">
			<span class='user-name'>用户昵称:</span> <input name="username"
				id="mbname" value="匿名用户">
			<textarea name="message" id="mbmessage" placeholder="说点什么吧…"></textarea>
			<div class="ds-post-toolbar">
				<button class="ds-post-button" id="mbsend" type="button">留言</button>
			</div>
		</form>
	</div>

	<div class="navigation page-navigation" style="margin: auto;"
		id="pagenumber"></div>
	<jsp:include page="bottom.jsp"></jsp:include>
</body>
</html>
