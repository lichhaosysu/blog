<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@include file="/jsp/includes/common.inc"%>
<!DOCTYPE html>
<html lang="zh">
	<head>
		<meta charset="utf-8">
		<%@ include file="/jsp/includes/bootstrap-css.inc"%>
		<link rel="stylesheet" href="<%=contextPath%>/css/index.css">
		<link rel="stylesheet"
			href="<%=contextPath%>/js/ueditor/themes/default/ueditor.css">
		<title>Let's Blog!</title>
	</head>
	<body>
		<div id="header">
		</div>
		<div class="container">
			<div class="row">
				<div class="span10 offset1 content">
					<form class="form-horizontal">
						<fieldset>
							<div style="border-bottom: 1px solid #666; padding-bottom: 1em;">
								<font style="font-weight: bold; font-size: 1.5em;">发表博文</font>
								<div style="float: right; margin-bottom: 1em;">
									<button type="button" class="btn btn-primary">
										<i class="icon-envelope icon-white"></i> 立即发表
									</button>
									<button type="button" class="btn btn-success">
										<i class="icon-file icon-white"></i> 存为草稿
									</button>
								</div>

							</div>
							<p>
								标题（必填）：
							</p>
							<input class="input-xxlarge focused" type="text"
								placeholder="这里填入你文章的标题...">

							<p>
								内容（必填）：
							</p>
							<div id="myEditor"></div>
						</fieldset>
					</form>
					<div id="J_2Top" class="back2Top"></div>
				</div>
			</div>
		</div>

		<%@ include file="/jsp/includes/bootstrap-js.inc"%>
		<script type="text/javascript">
	window.UEDITOR_HOME_URL = contextPath + "/js/ueditor/";
</script>
		<script type="text/javascript"
			src="<%=contextPath%>/js/util.js"></script>		
		<script type="text/javascript"
			src="<%=contextPath%>/js/ueditor/editor_config.js"></script>
		<script type="text/javascript"
			src="<%=contextPath%>/js/ueditor/editor_all.js"></script>
		<script type="text/javascript" src="<%=contextPath%>/jsp/index.js"></script>
	</body>
</html>