<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Error</title>
	</head>
	<body>
		<h3>
			Application Error
		</h3>

		<p>
			The application encountered a problem and your request was not
			completed successfully.
		</p>
		<%--
		<div style="border: 1px solid black; padding: 5px; margin: 5px;">
			<p>
				<s:property value="exception" />
			</p>
		</div>

		--%>
		<div style="border: 1px solid red; padding: 5px; margin: 5px">
			<p>
				<s:property value="exceptionStack" />
			</p>
		</div>

		<p>
			<a href="redirect.jsp">Return Home</a>
		</p>
	</body>
</html>