<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="song.*" %>
<%
	String title = (String) request.getAttribute("title");
	String view = (String) request.getAttribute("view");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title><%=title %></title>
</head>
<body>
	<header><h1>매출관리 집계 프로그램</h1></header>
	<nav>
		<a href="home.jsp">처음으로</a>
		<a href="productManagement">판매 상품</a>
		<a href="SalesManagement">매출</a>
		<a href="login">관리자 로그인</a>
	</nav>
	<main>
		<div><jsp:include page="<%=view %>"></jsp:include></div>
	</main>
	<footer>&copy; 2019 YHQuant.</footer>
</body>
</html>