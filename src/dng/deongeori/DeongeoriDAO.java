package dng.deongeori;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class DeongeoriDAO {
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	DataSource ds = null;
	StringBuffer sb = null;
	
	public DeongeoriDAO() {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource)ctx.lookup("java:comp/env/jdbc/orcl");
			sb = new StringBuffer();
		} catch(Exception e) {
			e.printStackTrace();
		}		
	}
	
	/**
	 * 덩어리 테이블에 입력된 정보를 저장.
	 * @param dngbean
	 * @return 덩어리 생성 여부 true||false
	 */
	public boolean createDeongeori(DeongeoriBean dngbean) {
		// TODO Auto-generated method stub
		boolean isCreateDeongeori = false;
		
		try {
			conn = ds.getConnection();	
			
			sb.setLength(0);

			sb.append("INSERT INTO DEONGEORI(dng_id, dng_name, dng_img, dng_admin, dng_regdate) ");
			sb.append("VALUES (?, ?, ?, ?, SYSDATE)                                             ");
			
			pstmt = conn.prepareStatement(sb.toString());
			
			pstmt.setString(1, dngbean.getDng_id());
			pstmt.setString(2, dngbean.getDng_name());
			pstmt.setString(3, dngbean.getDng_img());
			pstmt.setString(4, dngbean.getDng_admin());
			
			if (pstmt.executeUpdate() > 0) {
				isCreateDeongeori = true;
			}			
			pstmt.close(); conn.close();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		return isCreateDeongeori;
	}
	
	/**
	 * 덩어리 가입 정보를 테이블에 저장.
	 * @param djmbean
	 * @return 덩어리 가입 정보 저장 성공 여부 true||false
	 */
	public boolean insertDeongeoriJoinMember(DeongeoriJoinMemberBean djmbean) {
		// TODO Auto-generated method stub
		boolean isInsertDjm = false;
				
		try {
			conn = ds.getConnection();	
			
			sb.setLength(0);

			System.out.println(djmbean.getDng_id() + "-" + djmbean.getDng_mem_id());
			
			sb.append("INSERT INTO DEONGEORI_JOIN_MEMBER(dng_id, dng_mem_id, dng_mem_joindate) ");
			sb.append("VALUES (?, ?, SYSDATE)                                                  ");
			
			pstmt = conn.prepareStatement(sb.toString());
			
			pstmt.setString(1, djmbean.getDng_id());
			pstmt.setString(2, djmbean.getDng_mem_id());
	
			if (pstmt.executeUpdate() > 0) {
				isInsertDjm = true;
			}			
			pstmt.close(); conn.close();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		return isInsertDjm;
	}

	/**
	 * 덩어리 아이디에 해당하는 덩어리에 정보를 빈에 담아 리턴.
	 * @param dng_id
	 * @return DeongeoriBean
	 */
	public DeongeoriBean selectDeongeori(String dng_id) {
		// TODO Auto-generated method stub
		try {
			conn = ds.getConnection();
			
			sb.setLength(0);
			
			sb.append("SELECT dng_id, dng_name, dng_img, dng_admin, dng_regdate ");
			sb.append("FROM   DEONGEORI                                         ");
			sb.append("WHERE  dng_id = ?                                        ");
			
			pstmt = conn.prepareStatement(sb.toString());
			
			pstmt.setString(1, dng_id);
			
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				DeongeoriBean dngbean = new DeongeoriBean();
				
				dngbean.setDng_id(rs.getString("dng_id"));
				dngbean.setDng_name(rs.getString("dng_name"));
				dngbean.setDng_img(rs.getString("dng_img"));
				dngbean.setDng_admin(rs.getString("dng_admin"));
				dngbean.setDng_regdate(rs.getString("dng_regdate"));
			
				return dngbean;
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 해당 회원이 해당 덩어리의 멤버인지 리턴.
	 * @param dng_id
	 * @param dng_mem_id
	 * @return 해당 덩어리의 멤버 여부 true||false
	 */
	public boolean isDeongeoriMember(String dng_id, String dng_mem_id) {
		boolean isdeongeoriMember = false;
		
		try {
			conn = ds.getConnection();
			
			sb.setLength(0);
			
			sb.append("SELECT dng_id, dng_mem_id     ");
			sb.append("FROM   deongeori_join_member  ");
			sb.append("WHERE  dng_id = ?             ");
			sb.append("AND    dng_mem_id = ?         ");
			
			pstmt = conn.prepareStatement(sb.toString());
			
			pstmt.setString(1, dng_id);
			pstmt.setString(2, dng_mem_id);
			
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				isdeongeoriMember = true;
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return isdeongeoriMember;
	}
}
