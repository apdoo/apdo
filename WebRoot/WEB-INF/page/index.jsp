<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>百度贴吧自动签到v1.0</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="百度贴吧,签到,贴吧签到">
<meta http-equiv="description" content="百度贴吧自动签到">
	<link rel="shortcut icon" href="${pageContext.request.contextPath}/resources/img/favicon.ico"> 	
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/resources/css/style.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/resources/css/forms.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/resources/css/button.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-2.1.0.min.js"></script>
<script type="text/javascript">
	$(function() {
		$("#vimg").attr("src","${pageContext.request.contextPath}/validation/one");
		//提交
		$("#sign").click(function() {
			var username = $("#input_3_1").val();
			var password = $("#input_3_2").val();
			var enpassword = $("#input_3_3").val();
			var vcode = $("#input_3_4").val();
			$("#ac").empty(); 
			//客户端验证
			if(password!=enpassword){
				$("#ac").css("display", "");
				//跳转页内标记
				$("html,body").animate({scrollTop: $("#contentR").offset().top}, 1000);
				$("#ac").append("<p>请确认两次密码输入一致</p>");
				return false;
			}
			var phone=/1[3-8]+\d{9}/;//手机号
			var email=/^w+((-w+)|(.w+))*@[a-za-z0-9]+((.|-)[a-za-z0-9]+)*.[a-za-z0-9]+$/;//邮箱账号
			if(!(phone.test(username)||email.test(username)||username.length>3)){
				alert("请输入符合规则的账号！");
				return false;
			}
			
			$("#sign").attr("disabled","true");//添加disabled属性 
			$.ajax({
				type : "POST",
				url : "${pageContext.request.contextPath}/sign/verification",
				data : {
					username : username,
					vcode:vcode,
					password : password
				},
			    dataType:"json",
				success : function(data) {
					$("#sign").removeAttr("disabled");// 
					$("#vimg").attr("src","${pageContext.request.contextPath}/validation/one");
					$("#ac").css("display", "");
					//跳转页内标记
					$("html,body").animate({scrollTop: $("#contentR").offset().top}, 1000);
					//alert(data.msg+data.username+data.datetime);
					$("#ac").append("<p>"+"用户:"+data.username+"</p>");
					$("#ac").append("<p>"+"执行时间:"+data.datetime+"</p>");
					$("#ac").append("<p>"+"执行结果:"+data.msg+"</p>");
					if(typeof(data.count) != "undefined"){
						$("#ac").append("<p>"+"签到队列还有:"+data.count+"位用户未签到，预计您的签到时间为"+data.count+"分钟以后</p>");
					}
				},
				//请求出错的处理
	              error:function(){
	            	 $("#sign").removeAttr("disabled");// 
                     alert("请求出错");
            }
			});
		});
		//点击验证码换
		$("#vimg").click(function() {
			$("#vimg").attr("src","${pageContext.request.contextPath}/validation/one");
		});
		
		//$("#testConnect").click(function() {	
		//		$.ajax({
		//			type : "POST",
		//			url : "${pageContext.request.contextPath}/sign/getuser",
		//			 dataType:"json",
		//			success : function(data) {
		//				alert(data.msg);
		//			},
		//			//请求出错的处理
		//              error:function(){
	  //                   alert("请求出错");
	  //          }
		//		});
		//	});
		//	$("#testInsert").click(function() {	
		//		$.ajax({
		//			type : "POST",
		//			url : "${pageContext.request.contextPath}/sign/insertuser",
		//			 dataType:"json",
		//			success : function(data) {
		//				alert(data.msg);
		//			},
		//			//请求出错的处理
		//              error:function(){
	  //                   alert("请求出错");
	  //          }
		//		});
		//	});
	})
</script>
</head>

<body class="home blog paged paged-4 safari zilla"
	screen_capture_injected="true">
	<jsp:include page="header.jsp"></jsp:include>
	<!-- 内容  ${sessionScope.validation_code} -->
	<div id="content" class="clearfix">

		<div class="entry-content">
			<div class="fifty-fifty">
				<h1 class="contribute-heading">
					一些介绍...
				</h1>
				<%--<p style="font-family: Microsoft YaHei;">作者本人也是贴吧爱好者，一直想做一个自动签到的软件，--%>
					<%--由于平时工作比较忙加上作者自控力比较弱，再加上放假经常lol，陆陆续续做了几个礼拜，终于做完了。--%>
				<%--</p>--%>
				<p style="font-family: Microsoft YaHei;">
					 网上存在很多客户端版本的贴吧一键签到系统，抛开安全问题不谈，客户端版的还是需要每天打开打开软件，在个人电脑联网情况下进行签到。
					 使用本自动签到系统，只要第一次将你的正确的百度账号密码录入进系统后，系统会每天定时把签到队列中的账号进行自动的签到，免去每天人还要在电脑前进行签到操作。
					 当然你的账号密码会保存在系统中。
					 如果你的密码更换了，请在本系统重新登记，以免每日签到失败..
					 <br>
					 如果您有好的建议或意见，欢迎通过留言板留言或者给作者发邮件
				<%--<p style="font-family: Microsoft YaHei;">--%>
					<%--<strong>--%>
						 <%--作者承诺不会泄露或者使用你的账号，如果你想使用客户端版的话，作者也提供一个(客户端版本的签到支持贴吧签到与百度文库每日签到)--%>
					 <%--</strong>--%>
				<%--</p>--%>
			</div>
			<div class="fifty-fifty">
				<div class="gf_browser_chrome gform_wrapper" id="gform_wrapper_3">
					<form method="post" enctype="multipart/form-data" id="gform_3">
						<div class="gform_heading">
							<h3 class="gform_title">Contribute</h3>
							<span class="gform_description"></span>
						</div>
						<div class="gform_body">
							<ul id="gform_fields_3"
								class="gform_fields top_label description_above">
								<li id="field_3_1" class="gfield gfield_contains_required">
									<label class="gfield_label" for="input_3_1">百度账号 <span
										class="gfield_required">*</span></label>
									<div class="gfield_description">请尽量使用邮箱或手机号作为登记到本系统！</div>
									<div class="ginput_container">
										<input name="account" id="input_3_1" type="text" value=""
											class="medium" tabindex="1">
									</div>
								</li>
								<li id="field_3_2" class="gfield gfield_contains_required"><label
									class="gfield_label" for="input_3_2">密码<span
										class="gfield_required">*</span></label>
									<div class="gfield_description">请保证百度账号密码输入正确，登记错误的账号密码将会导致该账号自动签到失败！</div>
									<div class="ginput_container">
										<input name="password" id="input_3_2" type="password" value=""
											class="medium" tabindex="2">
									</div></li>
									
										<li id="field_3_3" class="gfield gfield_contains_required"><label
									class="gfield_label" for="input_3_3">确认密码<span
										class="gfield_required">*</span></label>
									<div class="gfield_description">请再次确认密码</div>
									<div class="ginput_container">
										<input name="enpassword" id="input_3_3" type="password" value=""
											class="medium" tabindex="3">
									</div></li>
								<li id="field_3_4" class="gfield gfield_contains_required">
									<label class="gfield_label" for="input_3_4">验证码<span
										class="gfield_required">*</span></label>
									<div class="gfield_description">
										<input name="vcode" id="input_3_4" type="text" value=""
											tabindex="4"> <img id="vimg"
											 />
									</div>
						
							</ul>
						</div>
						<div class="gform_footer top_label">
							<input type="button" id="sign" class="button white" value=" 登 记"
								tabindex="5">
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
	<!-- 内容 -->
	<div id="contentR"   class="clearfix" name="contentR" style="margin: auto;">
		<div id="ac" align="center" style="margin: auto;background-color: #272727;color: #BEBEBE;width:940px; height: 300px;display:none">
		</div>
	</div>
	<br>
	<jsp:include page="bottom.jsp"></jsp:include>
</body>
</html>
