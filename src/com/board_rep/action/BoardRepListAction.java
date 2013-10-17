package com.board_rep.action;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mtory.action.Action;
import com.mtory.action.ActionForward;

public class BoardRepListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
			
			Board_repBean brd = null;

			Connection con = null;
			String sql = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;;
			
			int num = Integer.parseInt(request.getParameter("num"));
			
			String driver = "oracle.jdbc.driver.OracleDriver";
			String url = "jdbc:oracle:thin:@110.12.75.91:1521:orcl"; 
			String uid = "scott"; 
		    String pwd = "tiger"; 
			
			try{
				Class.forName(driver); // jdbc ����̹� �ε�
				con = DriverManager.getConnection(url, uid, pwd);
				sql = "select * from DEONGEORI_BOARD_REPLY where dng_board_id=?";
				pstmt=con.prepareStatement(sql);
				pstmt.setInt(1, num);
				rs=pstmt.executeQuery(); 
				// select���� �����ϰ� executeQuery() �޼ҵ� ���
				if(rs.next()){// ��� ���ڵ尪�� ������ true
					brd=new Board_repBean();
				brd.setDng_id(rs.getString("dng_id"));
				brd.setDng_board_id(rs.getInt("dng_board_id"));
				brd.setDng_board_re_writer(rs.getString("dng_board_re_writer"));
				brd.setDng_board_re_content(rs.getString("dng_board_re_content"));
				brd.setDng_board_re_regdate(rs.getString("dng_board_re_regdate"));
				}
				response.sendRedirect("./deongeori_main.do");
				rs.close(); pstmt.close(); con.close();
			}catch(Exception e){
				e.printStackTrace();
			}		
			return null;  // board ���̺? �ִ� ��ü ���ڵ� ����
		}	
	}
