package dng.deongeori;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;


public class DeongeoriInviteMessageDAO {
	
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	DataSource ds = null;
	StringBuffer sb = null;
	
	public DeongeoriInviteMessageDAO() {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource)ctx.lookup("java:comp/env/jdbc/orcl");
			sb = new StringBuffer();
		} catch(Exception e) {
			e.printStackTrace();
		}		
	}
	
	/**
	 * 나에게 온 수락하지 않은 덩어리 초대 메세지 갯수를 리턴.
	 * @param dng_mem_id, 내 아이디
	 * @return cnt, 초대 메세지의 갯수
	 */
	public int getDngInvMessageCount(String dng_mem_id) {
		// TODO Auto-generated method stub
		int cnt = 0;
		
		try {
			conn = ds.getConnection();
			
			sb.setLength(0);
			
			sb.append("SELECT COUNT(dng_inv_to)        ");
			sb.append("FROM   deongeori_invite_message ");
			sb.append("WHERE  dng_inv_isallow = 'N'    ");
			sb.append("AND    dng_inv_to = ?           ");
			
			
			pstmt = conn.prepareStatement(sb.toString());
			
			pstmt.setString(1, dng_mem_id);
			
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				cnt = Integer.parseInt(rs.getString(1));
			}			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try { rs.close();    } catch (SQLException e) {e.printStackTrace(); }
			try { pstmt.close(); } catch (SQLException e) {e.printStackTrace(); }
			try { conn.close();   } catch (SQLException e) {e.printStackTrace(); } 
		}
		
		System.out.println("cnt = " + dng_mem_id);
		
		return cnt;
	}
	
	/**
	 * 나에게 온 수락하지 않은 덩어리 초대 메세지 목록을 리턴
	 * @param dng_mem_id
	 * @return dnginvmsglist, 초대 메세지 목록
	 */
	public List<DeongeoriInviteMessageBean> getDngInvMessageList(String dng_mem_id) {
		// TODO Auto-generated method stub
		List<DeongeoriInviteMessageBean> dnginvmsglist = new ArrayList<DeongeoriInviteMessageBean>();
		
		try {
			conn = ds.getConnection();
			
			sb.setLength(0);
			
			// 멤버 아이디, 덩어리 아이디를 실제 닉네임과 덩어리 이름으로 맵핑해서 한 번에 가져오는 쿼리.
			sb.append("SELECT dng_inv_id, dng_inv_isallow, dng_inv_regdate,                                        ");
			sb.append("	(SELECT dng_name                                                                           ");
			sb.append("	 FROM   deongeori                                                                          ");
			sb.append("	 WHERE  deongeori.dng_id = deongeori_invite_message.dng_id) dng_idx,                       ");
			sb.append("	(SELECT dng_mem_nickname                                                                   ");
			sb.append("	 FROM   deongeori_member                                                                   ");
			sb.append("	 WHERE  deongeori_member.dng_mem_id = deongeori_invite_message.dng_inv_to) dng_inv_tox,    ");
			sb.append("	(SELECT dng_mem_nickname                                                                   ");
			sb.append("	 FROM   deongeori_member                                                                   ");
			sb.append("	 WHERE  deongeori_member.dng_mem_id = deongeori_invite_message.dng_inv_from) dng_inv_fromx ");
			sb.append("FROM   deongeori_invite_message                                                             ");
			sb.append("WHERE  dng_inv_isallow = 'N'                                                                ");
			sb.append("AND    dng_inv_to = ?                                                                       ");
						
			pstmt = conn.prepareStatement(sb.toString());
			
			pstmt.setString(1, dng_mem_id);
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				DeongeoriInviteMessageBean dnginvmsgbean = new DeongeoriInviteMessageBean();
				
				dnginvmsgbean.setDng_inv_id(rs.getInt("dng_inv_id"));
				dnginvmsgbean.setDng_id(rs.getString("dng_idx"));
				dnginvmsgbean.setDng_inv_from(rs.getString("dng_inv_fromx"));
				dnginvmsgbean.setDng_inv_to(rs.getString("dng_inv_tox"));
				dnginvmsgbean.setDng_inv_isallow(rs.getString("dng_inv_isallow").charAt(0));
				dnginvmsgbean.setDng_inv_regdate(rs.getString("dng_inv_regdate"));
				
				dnginvmsglist.add(dnginvmsgbean);				
			}			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try { rs.close();    } catch (SQLException e) {e.printStackTrace(); }
			try { pstmt.close(); } catch (SQLException e) {e.printStackTrace(); }
			try { conn.close();   } catch (SQLException e) {e.printStackTrace(); } 
		}		
		return dnginvmsglist;
	}
	
	/**
	 * 해당 덩어리에 초대된 회원 여부 리턴.
	 * @param dng_id
	 * @param dng_inv_to
	 * @return 초대 여부 true||false
	 */
	public boolean isInviteDeongeori(String dng_id, String dng_inv_to) {
		boolean isInviteDeongeori = false;
		
		try {
			conn = ds.getConnection();
			
			sb.setLength(0);
			
			sb.append("SELECT dng_inv_id, dng_inv_to    ");
			sb.append("FROM   deongeori_invite_message  ");
			sb.append("WHERE  dng_inv_isallow = 'N'     ");
			sb.append("AND    dng_id = ?                ");
			sb.append("AND    dng_inv_to = ?            ");
						
			pstmt = conn.prepareStatement(sb.toString());
			
			pstmt.setString(1, dng_id);
			pstmt.setString(2, dng_inv_to);
			
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				isInviteDeongeori = true;
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try { rs.close();    } catch (SQLException e) {e.printStackTrace(); }
			try { pstmt.close(); } catch (SQLException e) {e.printStackTrace(); }
			try { conn.close();   } catch (SQLException e) {e.printStackTrace(); } 
		}				
		
		return isInviteDeongeori;
	}

	/**
	 * 초대 메세지 정보 데이터베이스에 저장.(실시간 아님).
	 * @param dng_id
	 * @param dng_inv_to
	 * @param dng_inv_from
	 * @return 초대 메세지 정보 저장 성공 여부. true||false
	 */
	public boolean sendInviteMessage(String dng_id, String dng_inv_from,
			String dng_inv_to) {
		// TODO Auto-generated method stub
		boolean isInvmsgSendOk = false;
		
		try {
			conn = ds.getConnection();
			
			sb.setLength(0);
			
			sb.append("INSERT INTO deongeori_invite_message                                             ");                    
			sb.append("(dng_inv_id, dng_id, dng_inv_from, dng_inv_to, dng_inv_isallow, dng_inv_regdate) ");
			sb.append("VALUES (dng_inv_seq.nextval, ?, ?, ?, default, sysdate)                          ");
			
			pstmt = conn.prepareStatement(sb.toString());
			
			pstmt.setString(1, dng_id);
			pstmt.setString(2, dng_inv_from);
			pstmt.setString(3, dng_inv_to);
			
			
			if (pstmt.executeUpdate() > 0) {
				isInvmsgSendOk = true;
			}
			
			pstmt.close();
			conn.close();			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try { pstmt.close(); } catch (SQLException e) {e.printStackTrace(); }
			try { conn.close();   } catch (SQLException e) {e.printStackTrace(); } 
		}
		
		return isInvmsgSendOk;
	}
	
	/**
	 * 덩어리 초대에 수락하면 해당 메세지의 isallow 컬럼 항목 값을 'Y'로 변경.
	 * @param dng_inv_id
	 * @return isallow 컬럼 항목 값을 'Y'로 변경 성공 여부, true||false
	 */
	public boolean JoinDeongeori(int dng_inv_id) {
		// TODO Auto-generated method stub
		boolean isJoinOk = false;
		
		try {
			conn = ds.getConnection();
			
			sb.setLength(0);
			
			sb.append("UPDATE deongeori_invite_message ");                    
			sb.append("SET    dng_inv_isallow = 'Y'    ");
			sb.append("WHERE  dng_inv_id = ?           ");
			
			pstmt = conn.prepareStatement(sb.toString());
			
			pstmt.setInt(1, dng_inv_id);
			
			if (pstmt.executeUpdate() > 0) {
				isJoinOk = true;
			}
			
			pstmt.close();
			conn.close();			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {

			try { pstmt.close(); } catch (SQLException e) {e.printStackTrace(); }
			try { conn.close();   } catch (SQLException e) {e.printStackTrace(); } 
		}
		
		return isJoinOk;
	}
	
}
