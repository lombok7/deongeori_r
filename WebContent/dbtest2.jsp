<%@ page language="java" contentType="text/html; charset=EUC-KR"%>
<%@ page import="java.sql.*"%>

<%		
		Connection conn = null;

		String driver = "oracle.jdbc.driver.OracleDriver";
		String url = "jdbc:oracle:thin:@110.12.75.159:1521:xe";
		
		Boolean connect=false;

		try{
			Class.forName(driver);
			conn=DriverManager.getConnection(url,"scott","tiger");
			
			connect = true;
			
			conn.close();
					
			System.out.println("�����ͺ��̽� ���� ����");
			
		}catch(Exception e){
			
			connect = false;
			
			System.out.println("�����ͺ��̽� ���� ����");
			e.printStackTrace();
		}

%>
<html>
<head>
<title> db ���� </title>
</head>
<body>
<% if(connect==true){ %>
	����
<% }else{ %>
	����
<% } %>
</body>
</body>
</html>