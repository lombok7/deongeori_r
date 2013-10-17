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
					
			System.out.println("데이터베이스 연결 성공");
			
		}catch(Exception e){
			
			connect = false;
			
			System.out.println("데이터베이스 연결 실패");
			e.printStackTrace();
		}

%>
<html>
<head>
<title> db 연동 </title>
</head>
<body>
<% if(connect==true){ %>
	연결
<% }else{ %>
	실패
<% } %>
</body>
</body>
</html>