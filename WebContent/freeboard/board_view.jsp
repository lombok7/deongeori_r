<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ page import="dng.freeboard.*"%>
<%
	FreeBoardBean board=(FreeBoardBean)request.getAttribute("boarddata");
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
						<table id="boardcont_t">
					<tr>
						<td style="font-family:돋음; font-size:12" height="16">
							<div align="center">제목&nbsp;&nbsp;</div>
						</td>
						<td style="font-family:돋음; font-size:12">
							<%=board.getBOARD_SUBJECT() %>
						</td>
				</tr>
	
	<tr bgcolor="cccccc">
		<td colspan="2" style="height:1px;"></td>
	</tr>
	
	<tr>
		<td style="font-family:돋음; font-size:12" colspan="2">
			<table border=0 width=490 height=250 style="table-layout:fixed">
				<tr>
					<td valign=top style="font-family:돋음;font-size:12">
					<%=board.getBOARD_CONTENT() %>
					</td>
				</tr>
			</table>
		</td>
	</tr>
	<tr>
		<td style="font-family:돋음; font-size:12">
			<div align="center">첨부파일</div>
		</td>
		<td style="font-family:돋음; font-size:12">
		<%if(!(board.getBOARD_FILE()==null)){ %>
		<a href="./upload/fbfiles/<%=board.getBOARD_FILE() %>">
			<%=board.getBOARD_FILE() %>
		</a>
		<%} %>
		</td>
	</tr>
	
	<tr bgcolor="cccccc">
		<td colspan="2" style="height:1px;"></td>
	</tr>
	<tr><td colspan="2">&nbsp;</td></tr>
	
	<tr align="center" valign="middle">
		<td colspan="5">
			<font size=2>
			<a href="./BoardReplyView.do?num=<%=board.getBOARD_NUM() %>">
			[답변]
			</a>&nbsp;&nbsp;
			<a href="./BoardModifyView.do?num=<%=board.getBOARD_NUM()%>">
			[수정]
			</a>&nbsp;&nbsp;
			<a href="./BoardDelete.do?num=<%=board.getBOARD_NUM() %>">
			[삭제]
			</a>&nbsp;&nbsp;
			<a href="./BoardListAction.do">[목록]</a>&nbsp;&nbsp;
			</font>
		</td>
	</tr>
</table>
</div>
<!-- article_c -->
</td>
</tr>
</table>
</div>	
</body>
</html>