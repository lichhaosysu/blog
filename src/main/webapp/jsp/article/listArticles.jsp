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
		<style type="text/css">
body {
	background-color: #005094;
}
</style>
		<link href="<%=contextPath%>/img/favicon.png" rel="shortcut icon" />
		<title>Let's Blog!</title>
	</head>
	<body>
		<div class="navbar navbar-inverse navbar-fixed-top">
			<div class="navbar-inner">
				<div class="container">
					<a class="brand" href="#">我的文章</a>
					<div class="nav-collapse">
						<ul class="nav">
							<li class="active">
								<a href="#">日志</a>
							</li>
							<li class="">
								<a href="#">草稿箱</a>
							</li>
							<li class="">
								<a href="#">留言板</a>
							</li>
							<li class="divider-vertical"></li>
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
							<font style="font-weight: bold; font-size: 1.5em;">文章列表</font>
							<div style="float: right; margin-bottom: 1em;">
								<a href="jsp/index.jsp">再写一篇</a>
							</div>
						</div>
					</div>
					<div style="padding: 0em 1.5em 1.5em 1.5em;">


					<c:forEach items="${articles}" var="article">
						<p>
							<a
								href="<%=contextPath%>/viewArticle.action?article.articleId=${article.articleId}">${article.title}</a>
						</p>
					</c:forEach>
					</div>
				</div>
			</div>
			<div id="J_2Top" class="back2Top"></div>
		</div>
		<%@ include file="/jsp/includes/base-js.inc"%>
		<%@ include file="/jsp/includes/bootstrap-js.inc"%>
		<script src="<%=contextPath%>/js/util.js"></script>
		<script src="<%=contextPath%>/jsp/article/listArticles.js"></script>
	</body>
</html>