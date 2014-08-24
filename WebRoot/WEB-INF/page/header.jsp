<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>百度贴吧自动签到v1.0</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="百度贴吧,签到,贴吧签到">
<meta http-equiv="description" content="百度贴吧自动签到">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/resources/css/style.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-2.1.0.min.js"></script>
<script type="text/javascript">
	$(function() {

	});
</script>
<style type="text/css">
	#logo{scrollbar-arrow-color:buttontext}
</style>
</head>
<!-- 头部 -->
<div id="header">
	<div id="topbar">
		<div class="inner clearfix">
			<p id="tagline" style="font-size: 25px;">百度贴吧自动签到系统v1.0</p>
			<div class="pull-right">
			
				<div id="social-counts">
				  <img
					src="${pageContext.request.contextPath}/resources/img/poweredby-117x12px.gif"
					alt="Premium Pixels"> 
					 <span class="divider">/</span> 
					hexd <span class="divider">/</span> 
					<!-- <a href="http://shop111353665.taobao.com/" target="_blank">作者的淘宝小店</a> -->
				</div>
			</div>
		</div>
	</div>
	<div class="inner">
		<div id="logo-nav" class="clearfix">
			<div id="logo">
			 <img
					src="${pageContext.request.contextPath}/resources/img/6532b430a0.png"
					alt="Premium Pixels"> 
			</div>
			<div id="primary-nav">
				<div class="menu-primary-container">
					<ul id="menu-primary" class="menu sf-js-enabled">
						<li id="menu-item-2271"
							class="menu-item menu-item-type-post_type menu-item-object-page menu-item-2271"><a
							href="${pageContext.request.contextPath}/home"  >首页</a><span class="divider">/</span></li>
						<li id="menu-item-2194" class="menu-item menu-item-type-custom menu-item-object-custom menu-item-2194"
						><a   href="${pageContext.request.contextPath}/sign/signlist">查看签到队列</a><span class="divider">/</span></li>	
						<li id="menu-item-2193" class="menu-item menu-item-type-post_type menu-item-object-page menu-item-2193"><a href="${pageContext.request.contextPath}/single">客户端版签到程序</a><span class="divider">/</span></li>
						<li id="menu-item-2195" class="menu-item menu-item-type-post_type menu-item-object-page menu-item-2193"><a href="${pageContext.request.contextPath}/message/home">留言板</a><span class="divider">/</span></li>	
							<li id="menu-item-2270"
							class="menu-item menu-item-type-post_type menu-item-object-page menu-item-2270"><a
							href="http://tieba.baidu.com/" target="_blank">跳转贴吧</a><span class="divider">/</span></li>
                        <li id="menu-item-2272"
                            class="menu-item menu-item-type-post_type menu-item-object-page menu-item-2272"><a
                                href="${pageContext.request.contextPath}/video/home" target="_blank">播放列表测试</a><span class="divider">/</span></li>
                        <li id="menu-item-2273"
                            class="menu-item menu-item-type-post_type menu-item-object-page menu-item-2273"><a
                                href="${pageContext.request.contextPath}/video/test" target="_blank">播放视频测试</a><span class="divider">/</span></li>
					</ul>
				</div>
			</div>
		</div>
	</div>
</div>