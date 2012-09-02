<?xml version="1.0" encoding="UTF-8" ?>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Person Deleted</title>
</head>
<body>
<h3>Person was deleted successfully.</h3>

<p>This person was deleted: <s:property value="person" /></p>

<p><a href="index.jsp">Find another employee</a></p>
<p><a href="<s:url action='allPersonsFinder'/>">Find all employees</a>.</p>


</body>
</html>