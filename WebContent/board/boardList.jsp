<%@page import="dng.common.CommonUtil"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ page import = "com.board.action.*" %>
<%@ page import = "com.board_rep.action.*" %>
<%@ page import = "java.util.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	String dng_id = request.getParameter("dng_id"); 
	session.setAttribute("dng_id", dng_id);
%>


<link rel="stylesheet" type="text/css" href="./css/boardCSS.css" />

 <div id="article_c">
  
  <div id="write_box">
  <form method="post" action="./board_write_ok.do" >
  <table id="write_t">      
   	<tr>
  		<td>
   		<div id="write_cont"> <textarea name="dng_board_content" id="dng_board_cont" 
   			class="input_box" rows="3" cols="70"></textarea> </div>
   		</td>
   	</tr>
  	<tr>
  		<td>
  		<div id = "boardlist_menu">
  		<input type="submit" value="글쓰기" class="input_b" />
 		</div>
 		</td>
 	</tr>
  </table></form></div>
 
   	<% BoardDAO bd=new BoardDAO();
  	 List<BoardBean> boardList = bd.getBoardList(dng_id); 
  	 
  	 if((boardList!=null) && (boardList.size()>0)){
   		for(int i=0; i<boardList.size(); i++){
   			BoardBean bb = boardList.get(i);
   	%> 
   	<table id = "boardlist_t">
   	<tr>
   		<td>
   		<div id="view_writer"> <%=CommonUtil.idToNickname(bb.getDng_board_writer()) %> </div>
   		<div id="date"> <%=bb.getDng_board_regdate().substring(0,10) %> </div>
   		</td>
   	</tr>
   	<tr>
   		<td>
   		<div id="cont" style="width:600px;"> 
   		<%  bb.getDng_board_content().replaceAll("`","'");
   			bb.getDng_board_content().replaceAll("\r\n","<br>");
   			bb.getDng_board_content().replaceAll("\u0020","&nbsp;"); %>
   		<%=bb.getDng_board_content() %></div>
   		</td>
   	</tr>
	   	
   	<tr>
  	 <td>
   	  <div id="lkie"><a href="./BoardLikeAction.do?num=<%=bb.getDng_board_id() %>">
   	  <input type="image" src="./images/ilikeit.jpg" 
   	  				 	id="best"></a> <%=bb.getDng_board_likecount() %> </div>
   	  <div id ="view_boardlist_menu">
   	  <form method="post" action="./board_del_ok.do" >
   	  	<!-- board_id를 히든으로 넘깁니다. -->
   	  	<input type="hidden" name="dng_board_id" id="dng_board_id" 
   	  					value="<%= bb.getDng_board_id() %>" /> 
  	  	<% if(bb.getDng_board_writer().equals(session.getAttribute("id"))){%>
  	  	<input type="submit" value="삭제 " class="input_b" />
  	  	<%} %>
      </form>
      </div>
     </td>
    </tr>
     <tr>
  		 <td>
  		 <div id="com_wrap">
  		 <form method="post" action="./board_rep_ok.do" >
  		 <div id="com_writer"> <%=session.getAttribute("nickname")%> </div>
  		 <div id="com_cont"> <textarea name="DNG_BOARD_RE_CONTENT" id="DNG_BOARD_RE_CONTENT" 
   			class="input_box" rows="1" cols="55"></textarea> </div>
   		 <input type="hidden" name="dng_board_id" id="dng_board_id" value="<%= bb.getDng_board_id() %>" />	
   		 <input type="submit" id="b" value="입력" class="input_b"/>
   		 
   		 </form>
   		 </div>
   		</td>
   	</tr>
   	
   	 <% Board_repDAO brd=new Board_repDAO();
  			 List<Board_repBean> board_repList = brd.getBoard_repList(bb.getDng_board_id()); %>
  		 <% if((board_repList != null) && (board_repList.size()>0)){
   			for(int j=0; j<board_repList.size(); j++){
   			Board_repBean brb = board_repList.get(j);	%>
   	<tr>
   		<td>
  	 	<div id="rep">
   		 
   			<div id="rep_w"><%=CommonUtil.idToNickname(brb.getDng_board_re_writer()) %></div>
   			<div id="rep_c" style="width: 380px;">
   			<%  brb.getDng_board_re_content().replaceAll("`","'");
   				brb.getDng_board_re_content().replaceAll("\r\n","<br>");
   				brb.getDng_board_re_content().replaceAll("\u0020","&nbsp;"); %>
   			<%=brb.getDng_board_re_content() %></div>
   			<div id="rep_date"><%=brb.getDng_board_re_regdate().substring(0,10) %></div>
   			
   		 </div>
   		</td>
   	</tr>

   	 <%}}} %>
   	      
    <tr><td><div id="null"></div></td></tr>
     
   	
   </table>
   <%
   		/* } */
   	}else{
   	 %>
   	 <div id="no"> 게시물이 없습니다~ </div>
   <% } %>
 </div>