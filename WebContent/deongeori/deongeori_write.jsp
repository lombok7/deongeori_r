<%@ page language="java" contentType="text/html; charset=UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>덩어리 메인화면</title>
<link rel="stylesheet" type="text/css" href="./css/d_main.css" />
<link rel="stylesheet" type="text/css" href="./css/main.css" />
</head>
<body>

	<div id="main_wrap">
	 <table>	
		<tr>
		 <td colspan="2">
		 <div id="header">
		 	<!-- <ul>
		 	<li><a href="#" onfocus="this.blur()"> 새로운 덩어리 생성 </a></li>
		 	<li><a href="#" onfocus="this.blur()"> QnA </a></li>
			</ul>
		  <div id="img">이미지</div>
		  <div id="info"><br>메세지 <br>정보 수정<br>로그아웃</div> -->
		  <%@ include file = "../include/dngmain_header.jsp" %>
		 </div>
		 </td>
		</tr>		
		<tr>
		 <td>
		  <div id="button_1">
			<a href="deongeori_main.do?dng_id=${dng_id}"><img src="./images/Board1.png"></a>
		  </div>
		  <div id="button_2">
			<img src="./upload/dgimages/${dngbean.dng_img }" width="200" height="120"/>
		  </div>
		  <div id="button_3">
			<a href="deongeori_list.do?dng_id=${dng_id}"><img src="./images/Gallery1.png"></a>
		  </div>
		 </td>
		</tr>		
		<tr>
		 <td colspan="2">
		  <div id="d_boarder" align="center">
			<%@ include file="../deongeori_gallery/gal_write.jsp" %>
		  </div>
		 </td>
		</tr>
	 </table>
	</div>

</body>
</html>