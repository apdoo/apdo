<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/resources/css/style.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-2.1.0.min.js"></script>
<script type="text/javascript">
	<%--$(function() {--%>
		<%--$.ajax({--%>
			<%--type : "POST",--%>
			<%--url : "${pageContext.request.contextPath}/sign/people",--%>
		    <%--dataType:"json",--%>
			<%--success : function(data) {--%>
				<%--//字符串--%>
				<%--var msg=data.msg;--%>
				<%--var p=(data.msg).length;--%>
				<%----%>
				<%--for(var i=0;i<(7-p);i++){--%>
					<%--$("#total-downloads").append("</span><span class='number'>0</span>");--%>
				<%--}--%>
				<%--for(var j=0;j<p;j++){--%>
					<%--$("#total-downloads").append("<span class='number'>"+msg.charAt(j)+"</span>");--%>
				<%--}--%>
			<%--},--%>
			<%--//请求出错的处理--%>
              <%--error:function(){--%>
        <%--}--%>
		<%--});--%>
	<%--});--%>
	
</script>
</head>
<!-- 底部 -->
	<div id="footer">
		<div class="inner">
			<div class="footer-top clearfix">
				<%--<div class="col">--%>
					<%--<div class="footer-post">--%>
						<%--<p style="font-family: Microsoft YaHei;">--%>
							<%--作者的联系方式<br>--%>
							<%--QQ:837484691--%>
							<%--<br>--%>
							<%--E-mail:837484691@qq.com--%>
							<%--<br>--%>
						<%--</p>--%>
					<%--</div>--%>
				<%--</div>--%>
			<!-- 	<div class="col" style="font-family: Microsoft YaHei;">
				作者的<a href="http://shop111353665.taobao.com/" target="_blank" class="read-more">淘宝小店链接</a>
				<p>
					致力于j2ee编程，提供包括j2ee系统开发，微信平台服务号、订阅号开发，网络爬虫开发，毕业设计代写等内容，提供定制服务，有需要的可以联系作者。
					<br>
					另出售本系统全部源码，以及百度签到的核心源码(附注释与讲解)。有需要的可以与作者联系或者进入作者淘宝店。
				</p>
				<p>
					给妈妈买件衣服，请到
					<a href="http://shop106277186.taobao.com/" target="_blank" class="read-more">达妈秀</a> 
				</p>
				</div> -->
				<div class="col last">
					<p id="total-downloads" class="download-count">
					</p>
					<p style="font-family: Microsoft YaHei;">登记人数</p>
				</div>
			</div>
			<div class="footer-bottom clearfix">
				<p class="left"></p>
				</p>
			</div>
		</div>
	</div>
