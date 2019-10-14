<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="song.*" %>
<%
	List<CigaretteVo> list = (List<CigaretteVo>) request.getAttribute("list");
%>
<html>
<body>
	<h1>매출 관리</h1>
	<form id="frm" action="SalesManagement" method="post" onsubmit='return che();'>
		<table>
			<tr>
				<th>일자</th>
				<th>구분</th>
				<th>구분번호</th>
				<th>상품번호</th>
				<th>상품 명</th>
				<th>수량</th>
				<th>총액</th>
			</tr>
			<%for(CigaretteVo vo : list){ %>
			<tr>
				<td><%=vo.getI_date() %></td>
				<td><%=vo.getI_no() %></td>
				<%if(vo.getTyp().equals("1") ){ %>
				<td>입고</td>
				<%}else{ %>
				<td>출고</td>
				<%} %>
				<td><%=vo.getP_no() %></td>
				<td><%=vo.getP_name() %></td>
				<td><%=vo.getP_cnt_sum() %></td>
				<td><%=vo.getTotalsum() %></td>
			<%} %>
			</tr>			
		</table>
		<div><a href="newSale"><button type="button">신규매출</button></a></div>
	</form>
</body>
</html>
