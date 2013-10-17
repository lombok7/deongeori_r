<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="java.sql.*" %>
<!-- jsp에서 외부 패키지를 임포하는 방법.
     java.sql 패키지의 모든 클래스를 사용할 수 있도록 한다. -->
     
<%   // 이부분은 jsp의 스크립틀 릿 이라고 한다. 
      // 자바문법을 사용할 수 있는 영역이다.
 Connection con = null; // 디비 연결 참조변수 선언
 Statement stmt = null; // 쿼리문 실행
 String sql = null; // 쿼리문을 저장하는 참조변수 선언

 request.setCharacterEncoding("UTF-8");
 // 1. request 내장객체는 사용자 폼에서 입력한 정보를 서버로 가져오는
 //    역할을 한다.
 // 2. 이 부분은 form 태그에서 method="post" 방식일 때 사용자가 
 //    입력한 한글을 인코딩하여 깨지지 않게 처리하는 부분이다.
 // <input type="text" name="board_name"/>에서 입력한 글쓴이
 // 데이터를 가져와서 board_name 변수에 저장한다.
 // 여기서 trim() 메소드는 사용자가 실수로 입력한 앞뒤 공백을 제거하는 메소드이다.
 String dng_gal_content = request.getParameter("dng_gal_content");
 String dng_gal_img = request.getParameter("dng_gal_img");
 String dng_gal_writer = request.getParameter("dng_gal_writer");
 String dng_gal_pwd = request.getParameter("dng_gal_pwd");
 
 // 데이터베이스 기본 속성 
 String driver = "oracle.jdbc.driver.OracleDriver";
 String url = "jdbc:oracle:thin:@127.0.0.1:1521:orcl";
 // 오라클 포트번호 : 1521, 사용할 데이터베이스 : orcl  
 String uid = "scott";  // 디비 접속 사용자
 String pwd = "tiger";  // 디비 접속 비밀번호
   
 try{
	 Class.forName(driver); // jdbc 드라이버 로딩
	 con = DriverManager.getConnection(url, uid, pwd);
	 // 디비 접속(주소, 사용자, 비번)
	 sql = "insert into deongeori_gallery values('1', dng_gal_seq.nextval, '테스터','"+dng_gal_content+"', '"+dng_gal_pwd+"','2','"+dng_gal_img+"', sysdate)";
	 // board_seq.nextval : 스퀀스 객체를 이용하여 자동으로 번호 삽입
	 // default : 조회수를 0으로 저장
	 // sysdate : 등록날짜를 오늘 날짜로 저장
	 stmt = con.createStatement(); //쿼리문 실행 객체 생성
	 int re = stmt.executeUpdate(sql); // 쿼리문 실행	 
	 // 삽입문이 성공시 정수형 1을 반환한다.
	 // insert, update, delete 쿼리문은 executeUpdate()메소드 사용
	 // select 쿼리문은 executeQuery()메소드 사용
	 
	 if(re==1){ // insert문이 성공한 경우
		 response.sendRedirect("../deongeori/deongeori_main.jsp");
	     // sendRedirect("이동할 주소 또는 파일명") : 원하는 곳으로 이동	     
	 }else{  // 저장에 실패한 경우 
		 out.println("<script>");  // 자바스크립트 시작
		 out.println("alert('게시물 저장에 실패했습니다.')");
		 out.println("</script>");  // 자바스크립트 끝
	 }
	 stmt.close(); con.close();
 }catch(Exception e){
	 e.printStackTrace();
 }
%>     