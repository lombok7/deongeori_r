<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String uid = (String)session.getAttribute("id");
 	if(uid == null){
%>
 <jsp:forward page="../index.jsp" />
 <% } %>
 <%
 String nickname=(String)session.getAttribute("nickname");
 String name=(String)session.getAttribute("name");

 %>
<!DOCTYPE html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<script type="text/javascript">
		// 화면 중앙의 초대 메세지 팝업 띄우기.
		// popupUrl : 페이지 url.
		// popupName : 페이지 제목.
		// w : 넓이.
		// h : 높이.
		function openPopup(popupUrl, popupName, w, h) {
			// screen.width : 전체 화면의 넓이.
			// screen.height : 전체 화면의 높이.
			leftPosition = (screen.width) ? (screen.width-w)/2 : 0;
			topPosition = (screen.height) ? (screen.height-h)/2 : 0;
			option = 'height='+h+',width='+w+',top='+topPosition+',left='+leftPosition;
			
			// window.open(); 팝업 창 띄우는 함수.
			window.open(popupUrl, popupName, option);
		}
		</script>
</head>
<body>
	<ul>
		<li><a href="./make_main.do?dng_mem_id=${ id}" class="main" onfocus="this.blue()">Home</a></li>
		<li><a href="./BoardListAction.do" class="free_b" onfocus="this.blur()"> 자유게시판 </a></li>
		<li><a href="#" class="m" onfocus="this.blur()" onclick="javascript:openPopup('./deongeori_invmsg.do?dng_id=${dngbean.dng_id}', '덩어리 초대하기', '450', '180');">초대하기</a></li>
	</ul>
	<!-- <div id="img">이미지</div> -->
	<div id="info">
		<%@include file= "../log_st.jsp" %>
	</div>
</body>

