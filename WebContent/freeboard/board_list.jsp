<%@page import="dng.common.CommonUtil"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%@ page import="dng.freeboard.*"%>

<%
	List<FreeBoardBean> boardList=(List)request.getAttribute("boardlist");
	int listcount=((Integer)request.getAttribute("listcount")).intValue();
	int nowpage=((Integer)request.getAttribute("page")).intValue();
	int maxpage=((Integer)request.getAttribute("maxpage")).intValue();
	int startpage=((Integer)request.getAttribute("startpage")).intValue();
	int endpage=((Integer)request.getAttribute("endpage")).intValue();
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
		  				<!-- 게시판 리스트 -->
		  					<h2 class="boardlist_title">자유 게시판</h2>
							<table id="boardlist_t">
							<% if (listcount > 0) { %>
								<tr align="center" valign="middle">
									<td align="right" colspan="5">
										<font size="2">글 개수:${listcount}</font>
									</td>
								</tr>
	
								<tr align="center" valign="middle" bordercolor="#333333">
									<td style="font-family:Tahoma;font-size:8pt;"  height="26">
										<div align="center">번호</div>
									</td>
									<td style="font-family:Tahoma;font-size:8pt;">
										<div align="center">제목</div>
									</td>
									<td style="font-family:Tahoma;font-size:8pt;">
										<div align="center">작성자</div>
									</td>
									<td style="font-family:Tahoma;font-size:8pt;">
										<div align="center">날짜</div>
									</td>
									<td style="font-family:Tahoma;font-size:8pt;">
										<div align="center">조회수</div>
									</td>
								</tr>
	
								<%
									for (int i = 0; i < boardList.size(); i++) {
										FreeBoardBean bl=(FreeBoardBean)boardList.get(i);
								%>		
								<tr align="center" valign="middle" bordercolor="#333333"
								onmouseover="this.style.backgroundColor='F8F8F8'"
								onmouseout="this.style.backgroundColor=''">
									<td height="23" style="font-family:Tahoma;font-size:10pt;">
										<%= bl.getBOARD_NUM() %>
									</td>
		
									<td style="font-family:Tahoma;font-size:10pt;">
										<div align="left">
										<%
											if (bl.getBOARD_RE_LEV()!=0) {
												for (int a=0;a<=bl.getBOARD_RE_LEV()*2;a++) { 
										%>
													&nbsp;
												<%
												} 
												%>
												▶
										<%	} else { %>
												▶
										<%	} %>
											<a href="./BoardDetailAction.do?num=<%=bl.getBOARD_NUM()%>">
												<%=bl.getBOARD_SUBJECT() %>
											</a>
										</div>
									</td>
									<td style="font-family:Tahoma;font-size:10pt;">
										<div align="center"><%=CommonUtil.idToNickname(bl.getBOARD_NAME()) %></div>
									</td>
									<td style="font-family:Tahoma;font-size:10pt;">
										<div align="center"><%=bl.getBOARD_DATE() %></div>
									</td>
									<td style="font-family:Tahoma;font-size:10pt;">
										<div align="center"><%=bl.getBOARD_READCOUNT() %></div>
									</td>
								</tr>
								<% 
								}
								%>
								<tr align=center height=20>
									<td colspan=7 style=font-family:Tahoma;font-size:10pt;>
										<% if(nowpage <= 1){ %>
										&nbsp;
										<%}else{%>
										<a href="./BoardListAction.do?page=<%=nowpage-1 %>">[이전]</a>&nbsp;
										<%} %>
										
										<% if(nowpage>=maxpage){ %>
										
										<%}else{ %>
										<a href="./BoardListAction.do?page=<%=nowpage+1 %>">[다음]</a>
										<%} %>
									</td>
								</tr>
							<%
							}
							else
							{
							%>	
							<tr align="center" valign="middle">
								<td align=right colspan="5">
									<font size=2>등록된 글이 없습니다.</font>
								</td>
							</tr>
							<%
							}
							%>
		<tr align = "right">
			<td colspan="5">
				<a href="./BoardWrite.do">[글쓰기]</a>
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