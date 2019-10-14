<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="song.*" %>
<%
	String p_no = (String) request.getAttribute("p_no");
	String p_name = (String) request.getAttribute("p_name");

%>
<html>
<body>
	<h1>판매 상품 입고</h1>
		<form id="frm" action="imput" method="post" >
			<table>
				<tr>
					<td>항목</td>
					<td>값</td>
				</tr>
				<tr>
					<th>상품  번호</th>
					<td><input value="<%=p_no %>" name="p_no" readonly></td>
				</tr>
				<tr>
					<th>상품 이름</th>
					<td><input value="<%=p_name %>" name="p_name" readonly></td>
				</tr>
				<tr>
					<th>입고 수량</th>
					<td><input type="number" name="i_cnt" ></td>
				</tr>
			</table>
			<div><input type="submit" value="상품 입고"></div>
		</form>
		<div><a href="productManagement"><button>취소</button></a></div>
</body>
</html>