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
					<form class="form-horizontal" id="mainForm"
						action="<%=contextPath%>/saveArticle.action" method="post">
						<input type="hidden" name="article.articleId" value="${article.articleId}" />
						<fieldset>
							<div style="border-bottom: 1px solid #666; padding-bottom: 1em;">
								<font style="font-weight: bold; font-size: 1.5em;">发表博文</font>
								<div style="float: right; margin-bottom: 1em;">
									<a href="<%=contextPath%>/listArticles.action"
										style="margin-right: 10px;">文章列表</a>
									<button type="submit" id="publish" class="btn btn-primary">
										<i class="icon-envelope icon-white"></i> 立即发表
									</button>
									<button type="button" class="btn btn-success disabled">
										<i class="icon-file icon-white"></i> 存为草稿
									</button>
								</div>
							</div>
							<p>
								标题（必填）：
							</p>
							<input class="input-xxlarge focused" type="text"
								name="article.title" placeholder="这里填入你文章的标题..." value="${article.title}"/>

							<p>
								内容（必填）：
							</p>
							<script type="text/plain" id="myEditor" name="article.content">${article.content}</script>
						</fieldset>
					</form>
					<div id="J_2Top" class="back2Top"></div>
				</div>
			</div>
		</div>
		<%@ include file="/jsp/includes/base-js.inc"%>
		<%@ include file="/jsp/includes/bootstrap-js.inc"%>
		<%@ include file="/jsp/includes/ueditor-js.inc"%>
		<script src="<%=contextPath%>/js/util.js"></script>
		<script src="<%=contextPath%>/jsp/index.js"></script>
	</body>
</html>