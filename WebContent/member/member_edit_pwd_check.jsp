<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.member.*" %>

<%
	request.setCharacterEncoding("UTF-8");
	// method="post" 방식으로 전송된 한글을 인코딩한다.
	
	String pwd = request.getParameter("pwd");
	// 입력아이디 값을 저장한다.
	String id = request.getParameter("id");
	
	MemberDAO md = new MemberDAO();
	// 오라클 디비 연동 위한 객체 생성
	
	String editf = md.getEditf(id,pwd);
	// 중복아이디 체크 메소드 호출
	
	out.println(editf);
	// ajax로 값이 넘어간다.(출력문이 아님)
	
%>	