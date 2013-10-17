<%@ page language="java" contentType="text/html; charset=EUC-KR"%>
<%@ page import="java.sql.*"%>
<%@ page import="javax.sql.*"%>
<%@ page import="javax.naming.*"%>
<%		
		Connection con = null;
		try{
			Context init=new InitialContext();
			DataSource ds = (DataSource)init.lookup("java:comp/env/jdbc/orcl");
			con = ds.getConnection();
			
			out.println("데이터베이스 연결 성공");
		}catch(Exception e){
			out.println("데이터베이스 연결 실패");
			e.printStackTrace();
		}
%>