package com.gal_rep.action;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mtory.action.Action;
import com.mtory.action.ActionForward;

public class GalRepOkAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		 Connection con = null; 
		 String sql = null; 
		 PreparedStatement pstmt = null;

		 HttpSession session = request.getSession();
		 
		 request.setCharacterEncoding("UTF-8");	 
		 String dng_id = (String)session.getAttribute("dng_id");		 
		 int dng_gal_id = Integer.parseInt(request.getParameter("dng_gal_id"));
		 String DNG_GAL_RE_CONTENT = request.getParameter("DNG_GAL_RE_CONTENT");
		 String dng_gal_re_writer = (String)session.getAttribute("id");
		 String show="1";
		 
		// 특수문자, 공백, 줄바꿈 처리
		 DNG_GAL_RE_CONTENT = DNG_GAL_RE_CONTENT.replaceAll("'","`");
		 DNG_GAL_RE_CONTENT = DNG_GAL_RE_CONTENT.replaceAll("\n", "<br>");

		 String driver = "oracle.jdbc.driver.OracleDriver";
		 String url = "jdbc:oracle:thin:@110.12.75.91:1521:orcl"; 
		 String uid = "scott"; 
		 String pwd = "tiger"; 	 
		   
		 try{
			 Class.forName(driver);
			 con = DriverManager.getConnection(url, uid, pwd);

			 sql = "insert into SCOTT.DEONGEORI_GALLERY_REPLY values(dng_gal_re_seq.nextval, ?, ?, ?, '"+DNG_GAL_RE_CONTENT+"', sysdate)";
			 
			 pstmt = con.prepareStatement(sql);
			 pstmt.setString(1, dng_id);
			 pstmt.setInt(2, dng_gal_id);
			 pstmt.setString(3, dng_gal_re_writer);
			 pstmt.executeUpdate();

			response.sendRedirect("./deongeori_list3.do?dng_id="+dng_id+"&dng_gal_id="+dng_gal_id+"&show="+show);
			  
			 con.close(); pstmt.close();
		 }catch(Exception e){
			 e.printStackTrace();
		 }
		
		return null;
	}

}
