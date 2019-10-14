<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="song.*" %>
<%
	List<CigaretteVo> saleList = (List<CigaretteVo>) request.getAttribute("saleList");
%>
<html>
<body>
	<h1>판매 상품 관리</h1>
	<form id="frm" action="prodcutManagement" method="post" onsubmit='return che();'>
		<table>
			<tr>
				<th>상품 번호</th>
				<th>상품 이름</th>
				<th>누적 입고</th>
				<th>잔존 수량</th>
				<th>상품 단가</th>
				<th>동작</th>
			</tr>
			<%for(CigaretteVo vo : saleList){ %>
			<tr>
				<td><%=vo.getP_no() %></td>
				<td><%=vo.getP_name() %></td>
				<td><%=vo.getP_cnt_sum() %></td>
				<td><%=vo.getP_cnt_rem() %></td>
				<td><%=vo.getP_price() %></td>
				<td><a href="mod?p_no=<%=vo.getP_no() %>&p_name=<%=vo.getP_name() %>&p_price=<%=vo.getP_price() %>"><input type="button" value="정정"></a>
				<a href="imput?p_no=<%=vo.getP_no() %>&p_name=<%=vo.getP_name() %>"><input type="button" value="입고"></a>				
				<a href="productManagement?p_no=<%=vo.getP_no() %>"><button type="button" name="del" onclick="return click();">삭제</button></a>
				</td>
			<%} %>
			</tr>			
		</table>
		<div><a href="newproduct"><button type="button">신규상품 추가</button></a></div>
	</form>
</body>
</html>
<script>
	function click(){
		if(confirm('정말 삭제합니까?')){
			alert('삭제되었습니다.');
			return true;
		}else{
			alert('삭제가 취소되었습니다.');
			return false;
		}
	}

</script>