<%@page import="dng.common.CommonUtil"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="com.gallery.dao.*" %>
<%@ page import = "com.gallery.action.*" %>
<%@ page import = "com.gal_rep.action.*" %>

<!-- com.naver.dao 패키지의 모든 클래스를 임포트한다. -->
<%@ page import="java.util.*" %>
<link rel="stylesheet" type="text/css" href="./css/gal.css" />
<!-- List 클래스를 사용하기 위해서 java.util 패키지의 모든 클래스를 임포트한다. -->
<%
	GalleryBean gc=(GalleryBean)request.getAttribute("gc");
    //setAttribute()에 의해서 Object형으로 업캐스팅 되면서
    //키값에 저장된다. 반환되는 값도 다운캐스팅을 해서 저장한다.
    //다운캐스팅은 업캐스팅이 된것에 한해서 다운캐스팅이 된다.
    //레퍼런스간의 캐스팅 즉 다운(업)캐스팅은 상속이 된것에 한에서만 허용된다.
   
%>

<div class="clear"></div>
  
  <div id="article">
  
  	<table border="1" id="boardcont_t">
  	<%if(gc==null){ %>
  		<tr>
  		<td>
  		사진이 없습니다.
  		</td>
  		</tr> 		
  	<% }else{ %>
  	
	<tr>
	
		<td rowspan="5" >
			<div align="center" ><img src="./upload/galimages/<%=gc.getDng_gal_img() %>" width="300" height="300" ></div>
		</td>
		<!-- <tr style="font-family:Tahoma;font-size:12pt;"> -->
			<td height="80px"><div id="gal_content"><%gc.getDng_gal_content().replaceAll("`","'");
			gc.getDng_gal_content().replaceAll("\r\n","<br>");
			gc.getDng_gal_content().replaceAll("\u0020","&nbsp;"); %>
   			<%=gc.getDng_gal_content()%></div></td>
		<tr style="font-family:Tahoma;font-size:8pt;">
		<td>
		<%=CommonUtil.idToNickname(gc.getDng_gal_writer())%>&nbsp;&nbsp;<%=gc.getDng_gal_regdate() %>
		</td>
		
		</tr>
		
		<tr>
		<td>
   	 	<a href="./GalLikeAction.do?num=<%=gc.getDng_gal_id() %>">
   	 	<input type="image" src="./images/ilikeit.png" width="30" height="30" id="best" >
   	 	</a> 
   	 	<%=gc.getDng_gal_likecount() %> 
   	  	</td>
   	  	</tr>
   	  	
		<tr>
		 <form name="gal_f" method="post" action="./gal_rep_ok.do?num=<%=gc.getDng_gal_id() %>">
		<td> 
		<div id="com_writer"> <%=session.getAttribute("nickname")%> </div>
  		 <div id="com_cont"> <textarea name="DNG_GAL_RE_CONTENT" id="DNG_GAL_RE_CONTENT" 
   			class="input_box" rows="1" cols="55"></textarea> </div>
   		<input type="hidden" name="dng_gal_id" id="dng_gal_id" value="<%= gc.getDng_gal_id() %>" />	
   		<input type="submit" id="b" value="입력" class="input_b"/>
        </td>
        </form>
        </tr>
        <% } %>
        <tr>
        <td height="150">
        <div id="rep_w">
        <table id="repp" >
        <% Gal_repDAO grd=new Gal_repDAO();
  			List<Gal_repBean> gal_repList = grd.getGal_repList(gc.getDng_gal_id()); %>
  		<% if((gal_repList != null) && (gal_repList.size()>0)){
   			for(int j=0; j<gal_repList.size(); j++){
   			Gal_repBean grb = gal_repList.get(j);	%>
   			<tr>
   	  		<td>
   			 			
   			<%=CommonUtil.idToNickname(grb.getDng_gal_re_writer()) %>&nbsp; &nbsp;
   			<%  grb.getDng_gal_re_content().replaceAll("`","'");
   				grb.getDng_gal_re_content().replaceAll("\r\n","<br>");
   				grb.getDng_gal_re_content().replaceAll("\u0020","&nbsp;"); %>
   			<%=grb.getDng_gal_re_content() %>&nbsp; &nbsp; &nbsp; &nbsp;
   			<%=grb.getDng_gal_re_regdate().substring(0,10) %>
   	  		</td>
   	  		</tr>
   	  	<%} %>
  		</table>
  		</div>
        </td>
        </tr>
   	  	
   	  	
   	  	<%-- <div id="rep"style="font-family:Tahoma;font-size:8pt;">
   	  	<% Gal_repDAO grd=new Gal_repDAO();
  			List<Gal_repBean> gal_repList = grd.getGal_repList(gc.getDng_gal_id()); %>
  		<% if((gal_repList != null) && (gal_repList.size()>0)){
   			for(int j=0; j<gal_repList.size(); j++){
   			Gal_repBean grb = gal_repList.get(j);	%>
   			<tr>
   	  		<td>
   			<div id="rep_w">
   			
   			<%=CommonUtil.idToNickname(grb.getDng_gal_re_writer()) %>&nbsp; &nbsp;
   			<%  grb.getDng_gal_re_content().replaceAll("`","'");
   				grb.getDng_gal_re_content().replaceAll("\r\n","<br>");
   				grb.getDng_gal_re_content().replaceAll("\u0020","&nbsp;"); %>
   			<%=grb.getDng_gal_re_content() %>&nbsp; &nbsp; &nbsp; &nbsp;
   			<%=grb.getDng_gal_re_regdate().substring(0,10) %></div>
   	  	</td>
  		</tr> --%>
  		<%} %>
  	</table>
 </div>
  	
  	

 
  
<!-- 이하 footer 내용 -->
<div class="clear"></div>
