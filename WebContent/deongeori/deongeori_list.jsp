<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>덩어리 메인화면</title>
<link rel="stylesheet" type="text/css" href="./css/d_main.css" />
</head>
<body>

	<div id="main_wrap">
	 <table>	
		<tr>
		 <td colspan="2">
		 <div id="header">
		 	<ul>
		 	<li><a href="#" onfocus="this.blur()"> 새로운 덩어리 생성 </a></li>
		 	<li><a href="#" onfocus="this.blur()"> QnA </a></li>
			</ul>
		  <div id="img">이미지</div>
		  <div id="info"><br>메세지 <br>정보 수정<br>로그아웃</div>
		 </div>
		 </td>
		</tr>		
		<tr>
		 <td>
		  <div id="button_1">
			Board
		  </div>
		  <div id="button_2">
			덩어리 이미지
		  </div>
		  <div id="button_3">
			<a href="gal_list_action.do">gallery</a>
		  </div>
		 </td>
		</tr>		
		<tr>
		 <td colspan="2">
		  <div id="d_boarder" align="center">
			<%@ include file="../deongeori_gallery/gal_list.jsp" %>
		  </div>
		 </td>
		</tr>
	 </table>
	</div>

</body>
</html>