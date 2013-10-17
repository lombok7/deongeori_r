package com.board_rep.action;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;


public class Board_repDAO {
	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;;
	DataSource ds = null;
	String sql = null;
	
	public Board_repDAO() { 
		try {
			Context ctx = new InitialContext();  
			ds = (DataSource)ctx.lookup("java:comp/env/jdbc/orcl"); 
		} catch(NamingException e) {
			e.printStackTrace();
		}
	}

	public List<Board_repBean> getBoard_repList(int num) {
		List<Board_repBean> board_repList = new
			     ArrayList<Board_repBean>();
	
		try {
			con = ds.getConnection();
	
			sql = "select * from DEONGEORI_BOARD_REPLY where dng_board_id=? order by dng_board_re_id desc";
			
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, num);
			rs=pstmt.executeQuery(); 
			
			while(rs.next()){
 
				Board_repBean rep = new Board_repBean();
	
				rep.setDng_board_re_id(rs.getInt("dng_board_re_id"));
				rep.setDng_id(rs.getString("dng_id"));
				rep.setDng_board_id(rs.getInt("dng_board_id"));
				rep.setDng_board_re_writer(rs.getString("dng_board_re_writer"));
				rep.setDng_board_re_content(rs.getString("dng_board_re_content"));
				rep.setDng_board_re_regdate(rs.getString("dng_board_re_regdate"));
	
				board_repList.add(rep);
			}
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			try { rs.close();    } catch (SQLException e) {e.printStackTrace(); }
			try { pstmt.close(); } catch (SQLException e) {e.printStackTrace(); }
			try { con.close();   } catch (SQLException e) {e.printStackTrace(); } 			
		}
	
		return board_repList;
	}
}