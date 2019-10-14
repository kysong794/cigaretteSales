<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="song.*" %>
<%

%>
<html>
<body>
	<h1>신규 매출</h1>
		<form id="frm" action="newSale" method="post" onsubmit='return che();'>
			<table>
				<tr>
					<td>항목</td>
					<td>값</td>
				</tr>
				<tr>
					<th>상품 번호</th>
					<td><input type="number" name="s_no"></td>
				</tr>
				<tr>
					<th>판매 수량</th>
					<td><input type="number" name="s_q"></td>
				</tr>
			</table>
			<div><a href="SalesManagement"><button type="button">취소</button></a></div>
			<div><input type="submit" value="신규매출 추가"></div>
		</form>
</body>
</html>
<script>
	function che(){
		if(frm.s_q.value<1){
			alert('1보다 적을수 없습니다.');
			return false;
		}
		return true;
	}

</script>