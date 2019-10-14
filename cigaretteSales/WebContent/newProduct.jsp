<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="song.*" %>
<%

%>
<html>
<body>
	<h1>신규 상품 추가</h1>
		<form id="frm" action="newproduct" method="post" >
			<table>
				<tr>
					<td>항목</td>
					<td>값</td>
				</tr>
				<tr>
					<th>상품 이름</th>
					<td><input type="text" name="p_name"></td>
				</tr>
				<tr>
					<th>상품 단가</th>
					<td><input type="number" name="p_price" ></td>
				</tr>
			</table>
			<div><input type="submit" value="신규 상품 추가"></div>
		</form>
		<div><a href="productManagement"><button>취소</button></a></div>
</body>
</html>