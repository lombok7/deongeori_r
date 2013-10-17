package com.gal_rep.action;

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

import com.gal_rep.action.Gal_repBean;


public class Gal_repDAO{
	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;;
	DataSource ds = null;
	String sql = null;
	
	public Gal_repDAO(){
		try {
			Context ctx = new InitialContext();  
			ds = (DataSource)ctx.lookup("java:comp/env/jdbc/orcl"); 
		} catch(NamingException e) {
			e.printStackTrace();
		}
	}

	public List<Gal_repBean> getGal_repList(int num) {
		List<Gal_repBean> gal_repList = new
			     ArrayList<Gal_repBean>();
	
		try {
			con = ds.getConnection();
	
			sql = "select * from DEONGEORI_GALLERY_REPLY where dng_gal_id=? order by dng_gal_re_id desc";
			
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, num);
			rs=pstmt.executeQuery(); 
			
			while(rs.next()){
 
				Gal_repBean rep = new Gal_repBean();
	
				rep.setDng_gal_re_id(rs.getInt("dng_gal_re_id"));
				rep.setDng_id(rs.getString("dng_id"));
				rep.setDng_gal_id(rs.getInt("dng_gal_id"));
				rep.setDng_gal_re_writer(rs.getString("dng_gal_re_writer"));
				rep.setDng_gal_re_content(rs.getString("dng_gal_re_content"));
				rep.setDng_gal_re_regdate(rs.getString("dng_gal_re_regdate"));
	
				gal_repList.add(rep);
			}
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			try { rs.close();    } catch (SQLException e) {e.printStackTrace(); }
			try { pstmt.close(); } catch (SQLException e) {e.printStackTrace(); }
			try { con.close();   } catch (SQLException e) {e.printStackTrace(); } 			
		}
	
		return gal_repList;
	}
}