<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<%@ page import="java.net.*"%>
<%@ page import="java.io.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"></c:set>
<%@ page trimDirectiveWhitespaces="true"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>${commicName}</title>
		<meta http-equiv="Content-Type" content="text/html;charset=utf-8">
		<style type="text/css">
a {
	margin: 10px;
	text-decoration: underline;
}

a:hover {
	text-decoration: none;
}
</style>
	</head>
	<body>
		<p><a href='${ctx}/imanhua'>首页</a></p>
		<%
			String commicName = (String) request.getAttribute("commicName");
			String realpath = config.getServletContext().getRealPath(
					"resources/imanhua/" + commicName);
			File dir = new File(realpath);
			boolean exists = false;
			for (File file : dir.listFiles()) {
				if (file.isDirectory()) {
					File indexHtml = new File(file, "index.html");
					if (indexHtml.exists()) {
						if (!exists) {
							exists = true;
						}
		%>
		<a href="${ctx}/resources/imanhua/<%=commicName%>/<%=file.getName()%>/index.html"><%=file.getName()%></a>
		<%						
					}
				}
			}
			if (!exists) {
		%>
				<p>还没有任何漫画</p>
		<%
			}
		%>
	</body>
</html>
