package dng.common;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

// 자주 사용되는 사용자 정의 함수 모음 클래스.
public class CommonUtil {
	
	/**
	 * prefix와 System.currentTimeMillis() 값을  결합하여 ID를 리턴한다.
	 * System.currentTimeMillis() : the difference, measured in milliseconds,
	 * between the current time and midnight, January 1, 1970 UTC.
	 * 회원 ID prefix : mem => mxxxxxxxxxxxxx
	 * 덩어리 ID prefix : dng => dxxxxxxxxxxxxxx
	 * 갤러리 이미지 prefix : gal => gxxxxxxxxxxxxxx
	 * @param prefix 
	 * @return 14자리 id (prefix + System.currentTimeMillis());
	 */
	public static synchronized String getId(String prefix) {
		String id = null;
		
		long ctm = System.currentTimeMillis();
		
		id = prefix + ctm;

		return id;
	}
	
	/**
	 * 회원 아이디에 대응하는 회원 닉네임 리턴. 
	 * @param dng_mem_id
	 * @return dng_mem_nickname
	 */
	public static String idToNickname(String dng_mem_id) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		DataSource ds = null;
		StringBuffer sb = null;	

		String dng_mem_nickname = null;
		
		try {

			Context ctx = new InitialContext(); 
			ds = (DataSource)ctx.lookup("java:comp/env/jdbc/orcl"); 
			conn = ds.getConnection(); 
			
			sb = new StringBuffer();
			
			sb.append("SELECT dng_mem_nickname ");
			sb.append("FROM   deongeori_member ");
			sb.append("WHERE  dng_mem_id = ?   ");
			
			pstmt = conn.prepareStatement(sb.toString());
			
			pstmt.setString(1, dng_mem_id);
			
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				dng_mem_nickname = rs.getString("dng_mem_nickname");
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		}		

		return dng_mem_nickname;
	}
	
	/**
	 * 회원 닉네임에 대응하는 회원 아이디를 리턴. 
	 * @param dng_mem_nickname
	 * @return dng_mem_id
	 */
	public static String nicknameToid(String dng_mem_nickname) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		DataSource ds = null;
		StringBuffer sb = null;	

		String dng_mem_id = null;
		
		try {

			Context ctx = new InitialContext(); 
			ds = (DataSource)ctx.lookup("java:comp/env/jdbc/orcl"); 
			conn = ds.getConnection(); 
			
			sb = new StringBuffer();
			
			sb.append("SELECT dng_mem_id          ");
			sb.append("FROM   deongeori_member    ");
			sb.append("WHERE  dng_mem_nickname = ?");
			
			pstmt = conn.prepareStatement(sb.toString());
			
			pstmt.setString(1, dng_mem_nickname);
			
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				dng_mem_id = rs.getString("dng_mem_id");
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		}		

		return dng_mem_id;
	}
	
	/**
	 * 회원 이메일에 대응하는 회원 아이디를 리턴. 
	 * @param dng_mem_email
	 * @return dng_mem_id
	 */
	public static String emailToid(String dng_mem_email) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		DataSource ds = null;
		StringBuffer sb = null;	

		String dng_mem_id = null;
		
		try {
			System.out.println(dng_mem_email);
			
			Context ctx = new InitialContext(); 
			ds = (DataSource)ctx.lookup("java:comp/env/jdbc/orcl"); 
			conn = ds.getConnection(); 
			
			sb = new StringBuffer();
			
			sb.append("SELECT dng_mem_id       ");
			sb.append("FROM   deongeori_member ");
			sb.append("WHERE  dng_mem_email = ?");
			
			pstmt = conn.prepareStatement(sb.toString());
			
			pstmt.setString(1, dng_mem_email);
			
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				dng_mem_id = rs.getString("dng_mem_id");
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		}		

		return dng_mem_id;
	}

	/**
	 * 덩어리 이름에 대응하는 덩어리 아이디를 리턴. 
	 * @param dng_mem_email
	 * @return dng_mem_id
	 */
	public static String dngnameTodngid(String dng_name) {
		// TODO Auto-generated method stub
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		DataSource ds = null;
		StringBuffer sb = null;	

		String dng_id = null;
		
		try {
			Context ctx = new InitialContext(); 
			ds = (DataSource)ctx.lookup("java:comp/env/jdbc/orcl"); 
			conn = ds.getConnection(); 
			
			sb = new StringBuffer();
			
			sb.append("SELECT dng_id      ");
			sb.append("FROM   deongeori   ");
			sb.append("WHERE  dng_name = ?");
			
			pstmt = conn.prepareStatement(sb.toString());
			
			pstmt.setString(1, dng_name);
			
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				dng_id = rs.getString("dng_id");
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		}		

		return dng_id;
	}
	
}