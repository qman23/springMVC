<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page import="java.lang.Exception" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<h1>
		OPS, you got exception...
	</h1>
	
	<p style="color:red">
		<%
			Exception  exp = (Exception)request.getAttribute("exception");
			System.out.println(exp.getMessage());
		%>
	</p>
</body>
</html>