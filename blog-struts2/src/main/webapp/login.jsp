<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<base href="<%=basePath%>">

		<title>登录八度空间</title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

	</head>

	<body>
		<%
		Object errorMsg = request.getAttribute("errorMsg");
		if(errorMsg!=null){
		%>
		<label><font color="red">${errorMsg}</font></label>
		<%
		}
		%>
		<form action="admin.action" method="post">
			<table>
				<tbody>
					<tr>
						<td><label>用户名：</label></td>
						<td><input type="text" name="user.userName" value="admin"/></td>
					</tr>
					<tr>
						<td><label>密码：</label></td>
						<td><input type="password"" name="user.userPassword" value="admin"/></td>
					</tr>
					<tr>
						<td></td>
						<td><input type="submit"" value="登录" /></td>
					</tr>
				</tbody>
			</table>
		</form>
	</body>
</html>
