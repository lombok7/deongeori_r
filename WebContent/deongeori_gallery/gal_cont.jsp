<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="com.gallery.dao.*" %>
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
			<td height="80px"><div id="gal_content"><%=gc.getDng_gal_content()%></div></td>
		<tr style="font-family:Tahoma;font-size:8pt;">
		<td>
		<%=gc.getDng_gal_writer() %>&nbsp; &nbsp;<%=gc.getDng_gal_regdate() %>
		</td>
		
		</tr>
		
		<tr>
		<td>
   	 	<a href="./GalLikeAction.do?num=<%=gc.getDng_gal_id() %>">
   	 	<input type="image" src="./images/ilikeit.jpg" width="30" height="30" id="best" >
   	 	</a> 
   	 	<%=gc.getDng_gal_likecount() %> 
   	  	</td>
   	  	</tr>
   	  	
		<tr>
		 <form name="gal_f method="post" action="GalRepOkAction.do?num">
		<td> 
		<input name="dng_gal_rep" id="dng_gal_rep" size="45" class="input_box"/>
        <input type="submit" value="등록"/>
        </td>
        </form>
        </tr>
        
       
   	  	<tr>
   	  	<td>
   	  	a
   	  	</td>
  		</tr>
  		<% } %>
  	</table>
 </div>
  	
  	

 
  
<!-- 이하 footer 내용 -->
<div class="clear"></div>
