<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%
	int num=Integer.parseInt(request.getParameter("num"));
%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>덩어리 메인화면</title>
<link rel="stylesheet" type="text/css" href="./css/d_main.css" />
<link rel="stylesheet" type="text/css" href="./css/main.css" />
<link rel="stylesheet" type="text/css" href="./css/freeboard.css" />
<script src="./js/jquery-1.10.1.js"></script>
</head>
<body>

	<div id="main_wrap">
		<table>	
			<tr>
				<td colspan="2">
					<div id="header">
						<%@ include file = "../include/main_header.jsp" %>
			 		</div>
		 		</td>
		 	</tr>
		 		<tr>
		 		<td>
		 			<div id="article_c">
						<!-- 게시판 내용 보기 -->
						<h2 class="boardcont_title">자유 게시판</h2>
		<form name="deleteForm" action="./BoardDeleteAction.do?num=<%=num%>" method="post">
<table id="boardwrite_t">
<tr>
	<th>
		<font size=5>글 비밀번호:</font>
	</th>
	<td>
		<input name="BOARD_PASS" type="password">
	</td>
</tr>
<tr>
	<td colspan=2 align=center>
		<a href="javascript:deleteForm.submit()">삭제</a>
		&nbsp;&nbsp;
		<a href="javascript:history.go(-1)">돌아가기</a>
	</td>
</tr>
</table>
</form>
					
					</div>
<!-- article_c -->
</td>
</tr>
</table>
</div>	
</body>
</html>