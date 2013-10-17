<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
	
	<%
		session.invalidate();	// 세션을 만료시킨다.
	%>
	<script>
	alert('로그아웃 되었습니다.');
	location = "./index.jsp";
	// 자바스크립트에서 location 객체는 원하는 주소로 이동한다.
	
	</script>
	