package com.board_rep.action;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mtory.action.Action;
import com.mtory.action.ActionForward;

public class BoardRepOkAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		 Connection con = null; 
		 String sql = null; 
		 PreparedStatement pstmt = null;

		 HttpSession session = request.getSession();
		 
		 request.setCharacterEncoding("UTF-8");
		 
		 String dng_id = (String)session.getAttribute("dng_id");
		 int dng_board_id = Integer.parseInt(request.getParameter("dng_board_id"));
		 String DNG_BOARD_RE_CONTENT = request.getParameter("DNG_BOARD_RE_CONTENT");
		 String dng_board_re_writer = (String)session.getAttribute("id");
		 
		// 특수문자, 공백, 줄바꿈 처리
		 DNG_BOARD_RE_CONTENT = DNG_BOARD_RE_CONTENT.replaceAll("'","`");
		 DNG_BOARD_RE_CONTENT = DNG_BOARD_RE_CONTENT.replaceAll("\n", "<br>");

		 String driver = "oracle.jdbc.driver.OracleDriver";
		 String url = "jdbc:oracle:thin:@110.12.75.91:1521:orcl"; 
		 String uid = "scott"; 
		 String pwd = "tiger"; 	 
		   
		 try{
			 Class.forName(driver);
			 con = DriverManager.getConnection(url, uid, pwd);

			 sql = "insert into SCOTT.DEONGEORI_BOARD_REPLY values(dng_board_re_seq.nextval, ?, ?, ?, '"+DNG_BOARD_RE_CONTENT+"', sysdate)";
			 
			 pstmt = con.prepareStatement(sql);
			 pstmt.setString(1, dng_id);
			 pstmt.setInt(2, dng_board_id);
			 pstmt.setString(3, dng_board_re_writer);
			 pstmt.executeUpdate();

			response.sendRedirect("./deongeori_main.do?dng_id="+dng_id);
			  
			 con.close(); pstmt.close();
		 }catch(Exception e){
			 e.printStackTrace();
		 }
		
		return null;
	}

}
