package com.board.action;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.board.action.BoardBean;

public class BoardDAO {
	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;;
	DataSource ds = null;
	String sql = null;

	public BoardDAO(){ // ����
		try{
			Context ctx = new InitialContext();  // 1��
			ds=(DataSource)ctx.lookup
					  ("java:comp/env/jdbc/orcl"); // 2��
		    con = ds.getConnection(); // 3��
			}catch(Exception e){
				e.printStackTrace();
			}
		}
	/* �Խù� ��� ���� : getBoardList() */ 
	public List<BoardBean> getBoardList(){
		// List�÷��ǿ� ���׸�(BoardBean Ÿ��)�� ����Ͽ� ��ü ��
		List<BoardBean> boardList = new
				     ArrayList<BoardBean>();
		try{
			sql = "select * from SCOTT.DEONGEORI_BOARD order by dng_board_id desc";
			pstmt=con.prepareStatement(sql);
			rs=pstmt.executeQuery(); 
			while(rs.next()){

				BoardBean board = new BoardBean();

				board.setDng_id(rs.getString("dng_id"));
				board.setDng_board_id(rs.getInt("dng_board_id"));
				board.setDng_board_writer(rs.getString("dng_board_writer"));
				board.setDng_board_content(rs.getString("dng_board_content"));
				board.setDng_board_pwd(rs.getString("Dng_board_pwd"));
				board.setDng_board_likecount(rs.getString("dng_board_likecount"));
				board.setDng_board_regdate(rs.getString("dng_board_regdate"));

				boardList.add(board);
			}
			rs.close(); pstmt.close(); con.close();			
			}catch(Exception e){
				e.printStackTrace();
		}finally {
		     if ( rs != null ) try{rs.close();}catch(Exception e){} 
		     if ( pstmt != null ) try{pstmt.close();}catch(Exception e){}
		     if ( con != null ) try{con.close();}catch(Exception e){}

	 }
		return boardList;
	}
	
	public List<BoardBean> getRecentBoardList(){
		// List�÷��ǿ� ���׸�(BoardBean Ÿ��)�� ����Ͽ� ��ü ��
		List<BoardBean> boardList = new
				     ArrayList<BoardBean>();
		try{
			con = ds.getConnection();			
			
			sql = "select * from SCOTT.DEONGEORI_BOARD order by dng_board_id desc";
			pstmt=con.prepareStatement(sql);
			rs=pstmt.executeQuery(); 
			while(rs.next()){

				BoardBean board = new BoardBean();

				board.setDng_id(rs.getString("dng_id"));
				board.setDng_board_id(rs.getInt("dng_board_id"));
				board.setDng_board_writer(rs.getString("dng_board_writer"));
				board.setDng_board_content(rs.getString("dng_board_content"));
				board.setDng_board_pwd(rs.getString("Dng_board_pwd"));
				board.setDng_board_likecount(rs.getString("dng_board_likecount"));
				board.setDng_board_regdate(rs.getString("dng_board_regdate"));

				boardList.add(board);
			}
			rs.close(); pstmt.close(); con.close();			
			}catch(Exception e){
				e.printStackTrace();
		}
		return boardList;
	}
	
	/* ��ȸ�� ���� */
	public void dng_likecount(int num) {
		sql="update SCOTT.DEONGEORI_BOARD set dng_board_likecount=dng_board_likecount+1 where dng_board_id=?";
		try{
		   con = ds.getConnection();
           pstmt = con.prepareStatement(sql);
           pstmt.setInt(1, num);
           pstmt.executeUpdate(); //update�� executeUpdate()���
           // ������ ����� ��ȯ���� 1�̴�.
           pstmt.close(); con.close();           
		}catch(Exception e){
			e.printStackTrace();
		}finally {
		     if ( rs != null ) try{rs.close();}catch(Exception e){} 
		     if ( pstmt != null ) try{pstmt.close();}catch(Exception e){}
		     if ( con != null ) try{con.close();}catch(Exception e){}

	 }		
	}
		
		/* �Խù� ���� */
		public void deleteBoard(int num) {
			try{
				con=ds.getConnection();

				sql = "delete from SCOTT.DEONGEORI_BOARD where dng_board_id=?";
				pstmt=con.prepareStatement(sql);
				pstmt.setInt(1, num);
				pstmt.executeUpdate();  
				pstmt.close(); con.close();
			}catch(Exception e){
				e.printStackTrace();
			}finally {
			     if ( rs != null ) try{rs.close();}catch(Exception e){} 
			     if ( pstmt != null ) try{pstmt.close();}catch(Exception e){}
			     if ( con != null ) try{con.close();}catch(Exception e){}

		 }		
		}
		
		/* �Խù� ���� */
		public BoardBean getcont(int num) {
			BoardBean board=null;
			try{
				con=ds.getConnection(); // ��� ���� ��ü ��
				sql = "select * from SCOTT.DEONGEORI_BOARD where dng_board_id=?";
				pstmt=con.prepareStatement(sql);
				pstmt.setInt(1, num);
				rs=pstmt.executeQuery(); 
				// select���� �����ϰ� executeQuery() �޼ҵ� ���
				if(rs.next()){// ��� ���ڵ尪�� ������ true
					board=new BoardBean();
				board.setDng_id(rs.getString("dng_id"));
				board.setDng_board_id(rs.getInt("dng_board_id"));
				board.setDng_board_writer(rs.getString("dng_board_writer"));
				board.setDng_board_content(rs.getString("dng_board_content"));
				board.setDng_board_pwd(rs.getString("Dng_board_pwd"));
				board.setDng_board_likecount(rs.getString("dng_board_likecount"));
				board.setDng_board_regdate(rs.getString("dng_board_regdate"));
				}
				rs.close(); pstmt.close(); con.close();
			}catch(Exception e){
				e.printStackTrace();

			}finally {
			     if ( rs != null ) try{rs.close();}catch(Exception e){} 
			     if ( pstmt != null ) try{pstmt.close();}catch(Exception e){}
			     if ( con != null ) try{con.close();}catch(Exception e){}

		 }		
			return null;  // board ���̺? �ִ� ��ü ���ڵ� ����

		}	
		
/*		 ��� ����(dng_id) 
		public BoardBean getid(int num) {
			BoardBean board=null;
			try{
				con=ds.getConnection(); // ��� ���� ��ü ��
				sql = "select dng_board_id from SCOTT.DEONGEORI_BOARD where dng_board_id=?";
				pstmt=con.prepareStatement(sql);
				pstmt.setInt(1, num);
				rs=pstmt.executeQuery(); 
				// select���� �����ϰ� executeQuery() �޼ҵ� ���
				if(rs.next()){// ��� ���ڵ尪�� ������ true
					board=new BoardBean();
				board.setDng_board_id(rs.getInt("dng_board_id"));
				}
				rs.close(); pstmt.close(); con.close();
			}catch(Exception e){
				e.printStackTrace();
			}		
			return null;  // board ���̺? �ִ� ��ü ���ڵ� ����
		}	
		*/
		
		/* �ش� ��� �Խù� ��� ���� : getBoardList() */ 
		public List<BoardBean> getBoardList(String num){
			// List�÷��ǿ� ���׸�(BoardBean Ÿ��)�� ����Ͽ� ��ü ��
			List<BoardBean> boardList = new
					     ArrayList<BoardBean>();
			try{
				sql = "select * from DEONGEORI_BOARD where dng_id=? order by dng_board_id desc";
				pstmt=con.prepareStatement(sql);
				pstmt.setString(1, num);
				rs=pstmt.executeQuery(); 
				while(rs.next()){

					BoardBean board = new BoardBean();
					
					board.setDng_id(rs.getString("dng_id"));
					board.setDng_board_id(rs.getInt("dng_board_id"));
					board.setDng_board_writer(rs.getString("dng_board_writer"));
					board.setDng_board_content(rs.getString("dng_board_content"));
					board.setDng_board_pwd(rs.getString("Dng_board_pwd"));
					board.setDng_board_likecount(rs.getString("dng_board_likecount"));
					board.setDng_board_regdate(rs.getString("dng_board_regdate"));

					boardList.add(board);
				}
				rs.close(); pstmt.close(); con.close();			
				}catch(Exception e){
					e.printStackTrace();
			}finally {
			     if ( rs != null ) try{rs.close();}catch(Exception e){} 
			     if ( pstmt != null ) try{pstmt.close();}catch(Exception e){}
			     if ( con != null ) try{con.close();}catch(Exception e){}

		 }
			return boardList;
		}
		
		public String getWriterImg(String dng_mem_id) {
			String writerimg = null;
			
			try {
				con=ds.getConnection();
				sql = "SELECT dng_mem_img FROM deongeori_member WHERE dng_mem_id = ?";
				
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, dng_mem_id);
				
				rs = pstmt.executeQuery();
				
				if (rs.next()) {
					writerimg = rs.getString("dng_mem_img");
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try { rs.close();    } catch (SQLException e) {e.printStackTrace(); }
				try { pstmt.close(); } catch (SQLException e) {e.printStackTrace(); }
				try { con.close();   } catch (SQLException e) {e.printStackTrace(); } 
			}
			return writerimg;
		}
	}
