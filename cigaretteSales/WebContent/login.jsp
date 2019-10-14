<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="song.*" %>

<html>
<body>
	<h1>관리자 로그인</h1>
	<form id="frm" action="login" method="post">
		<div>아이디 : <input type="text" name="a_id"></div>
		<div>비밀번호 : <input type="password" name="a_pw"></div>
		<div><input type="submit" value="로그인"></div>
		<div><a href="home.jsp" ><button type="button">취소</button></a></div>
	</form>
</body>
</html>