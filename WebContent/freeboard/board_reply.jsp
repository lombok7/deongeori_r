<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ page import="dng.freeboard.*" %>
<%
	FreeBoardBean board=(FreeBoardBean)request.getAttribute("boarddata");
%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>덩어리 메인화면</title>
<link rel="stylesheet" type="text/css" href="./css/d_main.css" />
<link rel="stylesheet" type="text/css" href="./css/main.css" />
<link rel="stylesheet" type="text/css" href="./css/freeboard.css" />
<script src="./js/jquery-1.10.1.js"></script>
	<script type="text/javascript">
	function replyboard() {
		boardform.submit();
	}
	</script>
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
	<!-- 게시판 답변 -->
<form action="./BoardReplyAction.do" method="post" name="boardform">
<input type="hidden" name="BOARD_NUM" value="<%=board.getBOARD_NUM() %> ">
<input type="hidden" name="BOARD_RE_REF" value="<%=board.getBOARD_RE_REF() %> ">
<input type="hidden" name="BOARD_RE_LEV" value="<%=board.getBOARD_RE_LEV() %> ">
<input type="hidden" name="BOARD_RE_SEQ" value="<%=board.getBOARD_RE_SEQ() %> ">

<h2 class="boardwrite_title">자유 게시판</h2>
<table id="boardwrite_t">

	<tr>
		<th style="font-family:돋음; font-size:12" height="16">
			<div align="center">제목</div>
		</th>
		<td>
			<input name="BOARD_SUBJECT" type="text" size="50" maxlength="100"
					value="Re: <%=board.getBOARD_SUBJECT() %> required="required""/>
		</td>
	</tr>
	<tr>
		<th style="font-family:돋음; font-size:12">
			<div align="center"> 내용 </div>
		</th>
		<td>
			<textarea name="BOARD_CONTENT" cols="67" rows="15" required="required"></textarea>
		</td>
	</tr>
	<tr>
		<th style="font-family:돋음; font-size:12">
			<div align="center">비밀번호</div>
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
		<a href="javascript:replyboard()">[등록]</a>&nbsp;&nbsp;
		<a href="javascript:history.go(-1)">[뒤로]</a>
		</td>
	</tr>
</TABLE>
	<input type="hidden" name="BOARD_NAME" id="BOARD_NAME" value="${id }" />
</form>
<!-- 게시판 답변 -->
</td>
</tr>
</table>
</div>	

</body>
</html>