package com.board.action;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mtory.action.Action;
import com.mtory.action.ActionForward;

public class BoardWriteOkAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		 Connection con = null; 
		 String sql = null; 
		 PreparedStatement pstmt = null; 	 
		 
		 request.setCharacterEncoding("UTF-8");
		 
		 HttpSession session = request.getSession();

		 String dng_id = (String)session.getAttribute("dng_id");
		 String dng_board_content = request.getParameter("dng_board_content");
		 String dng_board_writer = (String)session.getAttribute("id");
		 
		 // 특수문자, 공백, 줄바꿈 처리
		 dng_board_content = dng_board_content.replaceAll("'","`");
		 dng_board_content = dng_board_content.replaceAll("\n", "<br>");
		 
		 String driver = "oracle.jdbc.driver.OracleDriver";
		 String url = "jdbc:oracle:thin:@110.12.75.91:1521:orcl"; 
		 String uid = "scott"; 
		 String pwd = "tiger"; 
		   
		 try{
			 Class.forName(driver);
			 con = DriverManager.getConnection(url, uid, pwd);

			 sql = "insert into SCOTT.DEONGEORI_BOARD values(?, dng_board_seq.nextval, ?, '"+dng_board_content+"','1111', default, sysdate)";

			 pstmt = con.prepareStatement(sql);
			 pstmt.setString(1, dng_id);
			 pstmt.setString(2, dng_board_writer);
			 pstmt.executeUpdate();
			 
			 response.sendRedirect("./deongeori_main.do?dng_id="+dng_id);
			 con.close(); pstmt.close();
		 }catch(Exception e){
			 e.printStackTrace();
		 }
		
		return null;
	}

}
