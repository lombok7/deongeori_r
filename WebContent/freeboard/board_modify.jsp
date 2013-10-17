<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ page import="dng.freeboard.*"%>
<%
	FreeBoardBean board=(FreeBoardBean)request.getAttribute("boarddata");
%>

<html>
<head>
	<title>답변 게시판</title>
	<script type="text/javascript">
	function modifyboard() {
		modifyform.submit();
	}
	</script>
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
	 <table border="1">	
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
<!-- 게시판 수정 -->
<form action="BoardModifyAction.do" method="post" name="modifyform">
<input type="hidden" name="BOARD_NUM" value=<%=board.getBOARD_NUM() %>>

<h2 class="boardwrite_title">자유 게시판</h2>
<table id="boardwrite_t">
	<tr>
		<th height="16" style="font-family:돋음; font-size:12">
			<div align="center">제목</div>
		</th>
		<td>
			<input name="BOARD_SUBJECT" size="50" maxlength="100"
				value="<%=board.getBOARD_SUBJECT() %>" required="required">
		</td>
	</tr>
	<tr>
		<th style="font-family:돋음: font-size:12">
			<div align="center"> 내용 </div>
		</th>
		<td>
			<textarea name="BOARD_CONTENT" cols="67" rows="15" required="required">
				<%=board.getBOARD_CONTENT() %>
			</textarea>
		</td>
	</tr>
	<%if(board.getBOARD_FILE()!=null){ %>
	<tr>
		<th style="font-family:돋음; font-size:12">
			<div align="center">파일 첨부</div>
		</th>
		<td>
			&nbsp;&bnsp;<%=board.getBOARD_FILE() %>
		</td>
	</tr>
	<%} %>
	<tr>
		<th height="16" style="font-family:돋음; font-size:12">
			<div align=center">비밀번호</div>
		</th>
		<td>
			<input name="BOARD_PASS" type="password" required="required">
		</td>
	</tr>
	
	<tr bgcolor="cccccc">
		<td colspan="2" style="height:1px;">
		</td>
	</tr>
	<tr><td colspan="2">&nbsp;</td></tr>
	
	<tr align="center" valign="middle">
		<td colspan="5">
			<font size=2>
			<a href="javascript:modifyboard()">[수정]</a>&nbsp;&nbsp;
			<a href="javascript:history.go(-1)">[뒤로]</a>&nbsp;&nbsp;
			</font>
		</td>
	</tr>
</table>
</form>
</div>
<!-- 게시판 수정 -->
</td>
</tr>
</table>
</div>	

</body>
</html>