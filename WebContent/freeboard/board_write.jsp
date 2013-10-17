<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%@ page import="dng.freeboard.*"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript">
	function addboard() {
		boardform.submit();
	}
	</script>
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
					<!-- 게시판 등록 -->
						<form action="./BoardAddAction.do" method="post" 
								enctype="multipart/form-data" name="boardform">
						<h2 class="boardwrite_title">자유 게시판</h2>
						<table id="boardwrite_t">
							<tr>
								<th style="font-family:돋음;font-size:12" height="16">
									<div align="center">비밀번호</div>
								</th>
								<td>
									<input name="BOARD_PASS" type="password" size="10" maxlength="10"
										value="" required="required"/>
								</td>
							</tr>
							<tr>
								<th style="font-family:돋음; font-size:12" height="16">
									<div align="center">제목</div>
								</th>
								<td>
									<input name="BOARD_SUBJECT" type="text" size="50" maxlength="100"
										value="" required="required"/>
								</td>
							</tr>
							<tr>
								<th style="font-family:돋음; font-size:12">
									<div align="center">내용</div>
								</th>
								<td><textarea name="BOARD_CONTENT" cols="67" rows="15" required="required"></textarea></td>
							</td>
							<tr>
								<th style="font-family:돋음;font-size:12">
									<div align="center">파일첨부</div>
								</th>
								<td>
									<input name="BOARD_FILE" type="file"/>
								</td>
							</tr>
							<tr bgcolor="cccccc">
								<td colspan="2" style="height:1px;">
								</td>
							</tr>
							<tr><td colspan="2">&nbsp;</td></tr>
							<tr align="center" valign="middle">
								<td colspan="5">
									<a href="javascript:addboard()">[등록]</a>&nbsp;&nbsp;
									<a href="javascript:history.go(-1)">[뒤로]</a>
								</td>
							</tr>
						</table>
						<input type="hidden" name="BOARD_NAME" id="BOARD_NAME" value="${id }" />
					</form>
				</div>
			</td>
		</tr>
	</table>
	</div>
</html>