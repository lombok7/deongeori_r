package dng.freeboard;
//DAO 클래스는 실제로 데이터베이스와 연동하여 레코드의 추가, 수정, 삭제 작업이 이루어지는 클래스
//이다. 어떤 Action 클래스가 호출되더라도 그에 해당하는 데이터베이스 연동 처리는 DAO 클래스에서
//이루어지게 된다.
// SQL과 관련된 객체와 List 객체, JNDI 관련 객체를 사용하기 위해 import 한다.
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
// 데이터베이스 작업에 필요한 인터페이스들의 레퍼런스 변수를 선언한다.(con, pstmt, rs)
public class FreeBoardDAO {
	Connection con;
	PreparedStatement pstmt;
	ResultSet rs;
	// 생성자에서 JNDI 리소스를 참조하여 Connection 객체를 얻어 온다.
	public FreeBoardDAO() {
		try {
			Context init = new InitialContext();
			DataSource ds = (DataSource)init.lookup("java:comp/env/jdbc/orcl");
			con = ds.getConnection();
		}catch(Exception e) {
			System.out.println("DB연결 실패: " + e);
			return;
		}
	}
	
	// 글의 개수 구하기
	public int getListCount() {
		int x = 0;
		// 게시판 테이블의 레코드 수를 확인하여, x 변수에 전체 레코드 수를 할당한다.
		try {
			pstmt=con.prepareStatement("select count(*) from free_board");
			rs=pstmt.executeQuery();
			
			if (rs.next()){
				x = rs.getInt(1);
			}
		}catch(Exception e) {
			System.out.println("getListCount 에러: " + e);
		}finally {
			if (rs!=null) try { rs.close();}catch(SQLException e){}
			if (pstmt!=null) try { pstmt.close();}catch(SQLException e){}
		}
		// 레코드 수가 저장된 x 변수의 값을 리턴한다.
		return x;
	}
	
	// 글 목록 보기
	public List getBoardList(int page, int limit) {
		// 이 SQL문은 특정 번째의 레코드부터 특정 번째의 레코드까지의 값을 얻어 오는 기능을 한다. DBMS를
		// MYSQL로 사용할 경우 LIMIT 기능을 사용하면 되지만, 오라클은 rownum 함수를 이용해야 한다.
		// 제일 뒷부분의 where 절의 조건이 몇 번째의 레코드 값을 가져올지를 지정할 수 있다.
		// 이 부분이 이해가 가려면 오라클의 서브쿼리를 이해하고 있어야 한다.
		String board_list_sql="select * from" +
		"(select rownum rnum, BOARD_NUM, BOARD_NAME, BOARD_SUBJECT," +
		"BOARD_CONTENT, BOARD_FILE, BOARD_RE_REF, BOARD_RE_LEV," +
		"BOARD_RE_SEQ, BOARD_READCOUNT, BOARD_DATE from" +
		"(select * from free_board order by BOARD_RE_REF desc, BOARD_RE_SEQ asc))" +
		"where rnum >= ? and rnum <= ?";
		
		List list=new ArrayList();
		// 시작 행의 번호와 끝 행의 번호를 계산한다. 이 코드가 있어야만 각 페이지마다 출력될 레코드를 정확하게
		// 얻어올 수 있다.
		int startrow=(page-1)*10+1;	// 읽기 시작할 row 번호
		int endrow=startrow+limit-1;	// 읽을 마지막 row 번호
		
		try {
			// 지정된 행 번호 영역에 해당하는 레코드를 가져온 후 rs 변수에 저장한다.
			pstmt = con.prepareStatement(board_list_sql);
			pstmt.setInt(1, startrow);
			pstmt.setInt(2, endrow);
			rs = pstmt.executeQuery();
			// 레코드의 각 필드 값을 객체에 담는다.
			while (rs.next()) {
				FreeBoardBean board = new FreeBoardBean();
				board.setBOARD_NUM(rs.getInt("BOARD_NUM"));
				board.setBOARD_NAME(rs.getString("BOARD_NAME"));
				board.setBOARD_SUBJECT(rs.getString("BOARD_SUBJECT"));
				board.setBOARD_CONTENT(rs.getString("BOARD_CONTENT"));
				board.setBOARD_FILE(rs.getString("BOARD_FILE"));
				board.setBOARD_RE_REF(rs.getInt("BOARD_RE_REF"));
				board.setBOARD_RE_LEV(rs.getInt("BOARD_RE_LEV"));
				board.setBOARD_RE_SEQ(rs.getInt("BOARD_RE_SEQ"));
				board.setBOARD_READCOUNT(rs.getInt("BOARD_READCOUNT"));
				board.setBOARD_DATE(rs.getDate("BOARD_DATE"));
				// 값을 담은 객체를 리스트에 저장한다. 레코드 하나 당 하나의 글을 의미하므로, 레코드 당
				// 객체 하나를 생성하여 속성 값을 할당하고 각각의 객체를 리스트 객체에 추가하는 부분이다.
				list.add(board);
			}
			
			return list;
		}catch(Exception e) {
			System.out.println("getBoardList 에러: " + e);
		}finally {
			if (rs!=null) try{rs.close();}catch(SQLException e){}
			if (pstmt!=null)try{pstmt.close();}catch(SQLException e){}
		}
		return null;
	}
	// 글 내용 보기
	public FreeBoardBean getDetail(int num) throws Exception {
		FreeBoardBean board = null;
		// 해당 글 번호에 해당하는 글의 정보를 얻어 오는 부분이다.
		try {
			pstmt = con.prepareStatement("select * from free_board where BOARD_NUM = ?");
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();
			// BoardBean 객체에 레코드의 값을 모두 각 속성에 할당한다.이 객체는 리턴되어 View 페이지에서
			// 각 데이터를 출력할 때 사용된다.
			if (rs.next()){
				board=new FreeBoardBean();
				
				board.setBOARD_NUM(rs.getInt("BOARD_NUM"));
				board.setBOARD_NAME(rs.getString("BOARD_NAME"));
				board.setBOARD_SUBJECT(rs.getString("BOARD_SUBJECT"));
				board.setBOARD_CONTENT(rs.getString("BOARD_CONTENT"));
				board.setBOARD_FILE(rs.getString("BOARD_FILE"));
				board.setBOARD_RE_REF(rs.getInt("BOARD_RE_REF"));
				board.setBOARD_RE_LEV(rs.getInt("BOARD_RE_LEV"));
				board.setBOARD_RE_SEQ(rs.getInt("BOARD_RE_SEQ"));
				board.setBOARD_READCOUNT(rs.getInt("BOARD_READCOUNT"));
				board.setBOARD_DATE(rs.getDate("BOARD_DATE"));
			}
			return board;
		}catch(Exception e) {
			System.out.println("getDetail 에러: " + e);
		}finally {
			if (rs!=null)try{rs.close();} catch(SQLException e){}
			if (pstmt!=null)try{pstmt.close();}catch(SQLException e){}
		}
		return null;
	}
	// 글 등록
	public boolean boardInsert(FreeBoardBean board) {
		int num = 0;
		String sql = "";
		int result = 0;
		// board 테이블의 board_num 필드의 최대값을 얻어 온다. 이것은 글을 등록할 때 글 번호를 순차적으로
		// 지정하기 위함이다. 그런데 max 함수로 최대 값을 얻는 대신 count 함수로 레코드 수를 얻어서 1을
		// 더하는 방법도 생각해 볼 수 있다. 하지만 그럴 경우 10개의 레코드가 있을 때 5번 글을 삭제할 경우 9개의
		// 레코드가 되는데, count 함수로 글 번호를 계산하게 되면 10번 레코드를 등록하게 되어 무결성 제약
		// 조건에 위해된다. 이런 이유로 반드시 max 함수를 사용하여 글 번호를 계산해서 지정하는 것이 정확하다.
		try {
			pstmt=con.prepareStatement("select max(board_num) from free_board");
			rs = pstmt.executeQuery();
			if (rs.next())
				num = rs.getInt(1)+1;
			else
				num = 1;
			
			// 새로운 글을 등록하는 코드이다. 글쓰기는 답변과는 다르므로, 답변과 관련된 필드를 모두 0으로 주었다.
			// BOARD_RE_REF필드의 값만 새로운 값으로 지정하는데, 이 값은 글을 정렬하거나 그룹을 묶어줄 때
			// 사용된다. BOARD_RE_REF필드는 같은 글에 대한 답변들은 모두 값이 같다. 특정 글에 답변글이
			// 여러개일 경우 글을 작성한 날짜와 상관없이 답변 글들이 하나의 그룹으로 묶여 정렬되게 된다.
			sql = 
			"insert into free_board(BOARD_NUM, BOARD_NAME, BOARD_PASS, BOARD_SUBJECT,";
			sql += "BOARD_CONTENT, BOARD_FILE, BOARD_RE_REF," +
			"BOARD_RE_LEV, BOARD_RE_SEQ, BOARD_READCOUNT," +
			"BOARD_DATE) values(?,?,?,?,?,?,?,?,?,?,sysdate)";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, num);
			pstmt.setString(2, board.getBOARD_NAME());
			pstmt.setString(3, board.getBOARD_PASS());
			pstmt.setString(4, board.getBOARD_SUBJECT());
			pstmt.setString(5, board.getBOARD_CONTENT());
			pstmt.setString(6, board.getBOARD_FILE());
			pstmt.setInt(7, 0);
			pstmt.setInt(8, 0);
			pstmt.setInt(9, 0);
			pstmt.setInt(10, 0);
			
			result = pstmt.executeUpdate();
			if (result == 0) return false;
			
			return true;
		}catch(Exception e) {
			System.out.println("boardInsert 에러: " + e);
		}finally {
			if (rs!=null) try {rs.close(); } catch(SQLException e){}
			if (pstmt!=null) try {pstmt.close();}catch(SQLException e){}
		}
		return false;
	}
	// 글 답변
	public int boardReply(FreeBoardBean board) {
		String board_max_sql = "select max(board_num) from free_board";
		String sql = "";
		int num = 0;
		int result = 0;
		
		// 답변을 할 원본 글 그룹 번호이다. 답변을 달게 되면 글은 이 번호와 같은 관련글 번호를 갖게 처리되면서 같은 그룹에
		// 속하게 된다. 따라서, 글 목록이 출력될 때 하나의 그룹으로 묶여서 출력된다.
		int re_ref = board.getBOARD_RE_REF();
		// 답변 글의 깊이를 의미한다. 즉, 답글 레벨이다. 원 글에 대한 답글이 출력될 때 한 번 들여쓰기 처리가 되고,
		// 답글에 대한 답글은 들여쓰기가 두 번 처리되게 된다.
		int re_lev = board.getBOARD_RE_LEV();
		// 같은 관련 글 중에서 해당 글이 출력되는 순서이다. 원 글인 경우에는 이 값이 0이 되고, 원 글의 답글은 1,
		// 그리고 답글의 답글은 2가 된다.
		int re_seq = board.getBOARD_RE_SEQ();
		
		// 답변 글 등록 처리를 한다. SQL문의 조건절에는 BOARD_RE_REF 값과 BOARD_RE_SEQ 값을
		// 확인하여 원본 글에 다른 답변 글이 있으면, 현재 답변을 대상 글보다 BOARD_RE_SEQ 값이 큰 글이
		// 있으면 해당 글들의 BOARD_RE_SEQ 값을 모두 1씩 증가시켜서 다른 답변 글들을 뒤에 출력되게 처리한다.
		// 그 이유는 현재 글을 답변 대상 글 바로 아래에 출력되게 처리하기 위해서이다.
		try {
			pstmt=con.prepareStatement(board_max_sql);
			rs = pstmt.executeQuery();
			if (rs.next()) num = rs.getInt(1)+1;
			else num = 1;
			
			sql="update free_board set BOARD_RE_SEQ = BOARD_RE_SEQ+1 " +
			"where BOARD_RE_REF = ? and BOARD_RE_SEQ>?";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, re_ref);
			pstmt.setInt(2, re_seq);
			
			result=pstmt.executeUpdate();
			// 현재 답변글의 BOARD_RE_SEQ 값은 답변 대상 글의 값에 1을 더한다. 현재 답변한 답변 글이 답변 
			// 대상글 바로 다음 라인에 출력되게 처리하기 위해서이다.
			re_seq = re_seq + 1;
			// 답변을 다는 것이므로 현재 답변 레벨 단계에서 1을 증가시킨다. 예를 들어 원본 글에 답변을 달 경우,
			// 원본 글은 답변 레벨이 0이므로 답볁 레벨은 1이 된다.
			re_lev = re_lev + 1;
			
			// 실제로 답변 글을 등록하는 단계이다. INSERT INTO문을 사용하여 답변 글을 등록하며, 지금까지 설정하였던
			// re_ref, re_lev, re_seq 값을 파라미터로 전달한다.
			sql = "insert into free_board(BOARD_NUM, BOARD_NAME, BOARD_PASS," +
			"BOARD_SUBJECT, BOARD_CONTENT, BOARD_FILE, BOARD_RE_REF," +
			"BOARD_RE_LEV, BOARD_RE_SEQ, BOARD_READCOUNT," +
			"BOARD_DATE) values(?,?,?,?,?,?,?,?,?,?,sysdate)";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, num);
			pstmt.setString(2, board.getBOARD_NAME());
			pstmt.setString(3, board.getBOARD_PASS());
			pstmt.setString(4, board.getBOARD_SUBJECT());
			pstmt.setString(5, board.getBOARD_CONTENT());
			pstmt.setString(6, "");	// 답장에는 파일을 업로드 하지 않음
			pstmt.setInt(7, re_ref);
			pstmt.setInt(8, re_lev);
			pstmt.setInt(9, re_seq);
			pstmt.setInt(10,  0);
			pstmt.executeUpdate();
			return num;
		}catch(SQLException e) {
			System.out.println("boardReply 에러: " + e);
		}finally{
			if (rs!=null)try{rs.close();}catch(SQLException e){}
			if(pstmt!=null)try{pstmt.close();}catch(SQLException e){}
		}
		return 0;
	}
	// 글 수정
	public boolean boardModify(FreeBoardBean modifyboard) throws Exception {
		// 글을 수정하는 코드이다. 수정이 필요한 부분은 제목과 내용뿐이므로 그 부분만 UPDATE 문을 사용하여 수정 처리한다.
		String sql="update free_board set BOARD_SUBJECT=?, BOARD_CONTENT=?" +
					"where BOARD_NUM=?";
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, modifyboard.getBOARD_SUBJECT());
			pstmt.setString(2, modifyboard.getBOARD_CONTENT());
			pstmt.setInt(3, modifyboard.getBOARD_NUM());
			pstmt.executeUpdate();
			return true;
		}catch(Exception e) {
			System.out.println("boardModify 에러:" + e);
		}finally{
			if (rs!=null)try{rs.close();}catch(SQLException e){}
			if (pstmt!=null)try{pstmt.close();}catch(SQLException e){}
		}
		return false;
	}
	
	// 글 삭제
	public boolean boardDelete(int num) {
		// 글을 삭제하는 코드이다. 삭제와 관련된 Action 클래스에서 비밀번호 일치가 확인되면 이 코드들이 수행된다.
		// 글 번호를 조건으로 사용하여 글을 삭제 처리한다.
		String board_delete_sql = "delete from free_board where BOARD_num=?";
		int result = 0;
		
		try {
			pstmt=con.prepareStatement(board_delete_sql);
			pstmt.setInt(1, num);
			result = pstmt.executeUpdate();
			// 글이 삭제되면 삭제된 로우(행) 수가 반환되는데, 0이 반환되면 어떤 것도 삭제되지 않은 것이므로
			// false를 반환한다.
			if (result == 0) return false;
			
			// 올바르게 글이 삭제되었다면 true를 반환한다.
			return true;
		}catch(Exception e){
			System.out.println("boardDelete 에러: " + e);
		}finally{
			try {
				if (pstmt!=null) pstmt.close();
			}catch(Exception e) {}
		}
		return false;
	}
	// 조회수 업데이트
	// 조회수 업데이트 메소드는 글 내용을 확인하는 순간 호출된다.
	public void setReadCountUpdate(int num) throws Exception {
		// 조회수 필드인 BOARD_READCOUNT를 1 증가시킨다.
		String sql="update free_board set BOARD_READCOUNT = " +
				"BOARD_READCOUNT + 1 where BOARD_NUM = " + num;
		try {
			pstmt=con.prepareStatement(sql);
			pstmt.executeUpdate();
		}catch(SQLException e) {
			System.out.println("setReadCountUpdate 에러: " + e);
		}
	}
	// 글쓴이인지 확인
	public boolean isBoardWriter(int num, String pass) {
		// 글쓴이를 확인할 글의 정보(비밀번호)를 얻는다.
		String board_sql = "select * from free_board where BOARD_NUM=?";
		
		try {
			pstmt = con.prepareStatement(board_sql);
			pstmt.setInt(1, num);
			rs=pstmt.executeQuery();
			rs.next();
			
			// 수정할 때 입력한 비밀번호와 글의 번호가 일치하면 true를 반환하여 글쓴이로 인식하도록 한다.
			System.out.println(pass + "=" + rs.getString("BOARD_PASS"));
			if (pass.equals(rs.getString("BOARD_PASS"))) {
				return true;
			}
		}catch(SQLException e){
			System.out.println("isBoardWriter 에러: " + e);
		}
		return false;
	}
}

