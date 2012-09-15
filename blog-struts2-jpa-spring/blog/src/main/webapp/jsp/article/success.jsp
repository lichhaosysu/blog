<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@include file="/jsp/includes/common.inc"%>
<!DOCTYPE html>
<html lang="zh">
	<head>
		<meta charset="utf-8">
		<%@ include file="/jsp/includes/bootstrap-css.inc"%>
		<%@ include file="/jsp/includes/ueditor-css.inc"%>
		<link rel="stylesheet" href="<%=contextPath%>/css/index.css">
		<link href="<%=contextPath%>/img/favicon.png" rel="shortcut icon" />
		<title>Let's Blog!</title>
	</head>
	<body>
		<div class="navbar navbar-inverse navbar-fixed-top">
			<div class="navbar-inner">
				<div class="container">
					<button type="button" class="btn btn-navbar" data-toggle="collapse"
						data-target=".nav-collapse">
						<span class="icon-bar"></span>
						<span class="icon-bar"></span>
						<span class="icon-bar"></span>
					</button>
					<a class="brand" href="./index.html">我的小窝</a>
					<div class="nav-collapse collapse">
						<ul class="nav">
							<li class="active">
								<a href="#">博文目录</a>
							</li>
							<li class="">
								<a href="#">草稿箱</a>
							</li>
							<li class="">
								<a href="#">留言板</a>
							</li>							
							<li class="">
								<a href="#">关于我</a>
							</li>							
						</ul>
					</div>
				</div>
			</div>
		</div>
		<div class="container">
			<div class="row">
				<div class="span10 offset1 content">
					<div style="padding: 1.5em;">
						<div style="border-bottom: 1px solid #666; padding-bottom: 16px;">
							<font style="font-weight: bold; font-size: 1.5em;">发表成功！</font>
							<div style="float: right; margin-bottom: 1em;">
								<a href="<%=contextPath%>/listArticles.action"
									style="margin-right: 10px;">文章列表</a>
								<a href="jsp/index.jsp">再写一篇</a>
							</div>
						</div>
					</div>
					<div class="row">
						<div class="span12" style="padding-left: 1.5em">
							<h2>
								${article.title}
							</h2>
						</div>
					</div>
					<div style="padding: 0em 1.5em 1.5em 1.5em;">
						<p>
							${article.content}
						</p>
					</div>
				</div>
			</div>
			<div id="J_2Top" class="back2Top"></div>
		</div>
		<%@ include file="/jsp/includes/base-js.inc"%>
		<%@ include file="/jsp/includes/bootstrap-js.inc"%>
		<script src="<%=contextPath%>/js/util.js"></script>
		<script src="<%=contextPath%>/jsp/article/success.js"></script>
	</body>
</html>