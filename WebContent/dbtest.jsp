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
			
			out.println("�����ͺ��̽� ���� ����");
		}catch(Exception e){
			out.println("�����ͺ��̽� ���� ����");
			e.printStackTrace();
		}
%>