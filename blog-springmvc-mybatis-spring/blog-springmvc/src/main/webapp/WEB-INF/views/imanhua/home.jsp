<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<%@ page import="java.net.*"%>
<%@ page import="java.io.*"%>
<%@ page trimDirectiveWhitespaces="true"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>漫画目录</title>
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
		<%
			Writer writer = response.getWriter();
			String realpath = config.getServletContext().getRealPath(
					"resources/imanhua");
			File dir = new File(realpath);
			for (File file : dir.listFiles()) {
				if (file.isDirectory()) {
					writer.write("<a href='imanhua/" + file.getName() + "'>"
							+ file.getName() + "</a>");
				}
			}
		%>
	</body>
</html>
