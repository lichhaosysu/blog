<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@include file="/jsp/includes/common.inc"%>
<!DOCTYPE html>
<html lang="zh">
	<head>
		<meta charset="utf-8">
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<%@ include file="/jsp/includes/bootstrap-css.inc"%>
		<link rel="stylesheet" href="<%=contextPath%>/css/index.css">
		<link rel="stylesheet"
			href="<%=contextPath%>/js/ueditor/themes/default/ueditor.css">
		<title>Let's Blog!</title>
	</head>
	<body>
		<div id="header">

		</div>
		
		<div class="container-fluid">
			<div class="row-fluid">
				<div class="span8 offset2" id="contentContainer">
					<div id="myEditor"></div>
				</div>
			</div>
		</div>

		<%@ include file="/jsp/includes/bootstrap-js.inc"%>
		<script type="text/javascript">
	window.UEDITOR_HOME_URL = contextPath + "/js/ueditor/";
</script>
		<script type="text/javascript"
			src="<%=contextPath%>/js/ueditor/editor_config.js"></script>
		<script type="text/javascript"
			src="<%=contextPath%>/js/ueditor/editor_all.js"></script>
		<script type="text/javascript" src="<%=contextPath%>/jsp/index.js"></script>
	</body>
</html>