package com.member;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import dng.deongeori.DeongeoriBean;

public class MemberDAO {
	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	DataSource ds = null;
	String sql = null;
	
	public MemberDAO(){ // 생성자
		try{
			// 커넥션 풀 설정
			Context ctx = new InitialContext();
			ds = (DataSource)ctx.lookup("java:comp/env/jdbc/orcl");
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
	
	/* 닉네임 중복 체크 */
	public int checkMemberNick(String nick){
		int re = -1;	// 중복아이디가 아닌 경우 -1값 리턴
		try{
			con = ds.getConnection();	// 디비 연동 객체 생서
			sql = "select dng_mem_nickname from deongeori_member where dng_mem_nickname=?";	// 쿼리문
			pstmt = con.prepareStatement(sql);	// sql문 선처리
			pstmt.setString(1, nick);	// 1번 물음표에 id값 셋팅
			rs = pstmt.executeQuery();	// select문 실행
			if(rs.next()){	// 중복아이디가 있을 경우
				re = 1;		// 1을 리턴한다.
			} // 중복아이디가 아닌 경우 -1값이 리턴된다.
			rs.close(); pstmt.close(); con.close();
		}catch(Exception e){
			e.printStackTrace();
		}finally {
		     if ( rs != null ) try{rs.close();}catch(Exception e){} 
		     if ( pstmt != null ) try{pstmt.close();}catch(Exception e){}
		     if ( con != null ) try{con.close();}catch(Exception e){}

	 }
		return re;	// 중복아이디 있는 경우 1, 아니면 -1 리턴
	}
	public int checkMemberEmail(String email){
		int re = -1;	// 중복아이디가 아닌 경우 -1값 리턴
		try{
			con = ds.getConnection();	// 디비 연동 객체 생서
			sql = "select dng_mem_email from deongeori_member where dng_mem_email=?";	// 쿼리문
			pstmt = con.prepareStatement(sql);	// sql문 선처리
			pstmt.setString(1, email);	// 1번 물음표에 id값 셋팅
			rs = pstmt.executeQuery();	// select문 실행
			if(rs.next()){	// 중복아이디가 있을 경우
				re = 1;		// 1을 리턴한다.
			} // 중복아이디가 아닌 경우 -1값이 리턴된다.
			rs.close(); pstmt.close(); con.close();
		}catch(Exception e){
			e.printStackTrace();
		}finally {
		     if ( rs != null ) try{rs.close();}catch(Exception e){} 
		     if ( pstmt != null ) try{pstmt.close();}catch(Exception e){}
		     if ( con != null ) try{con.close();}catch(Exception e){}

	 }
		return re;	// 중복아이디 있는 경우 1, 아니면 -1 리턴
	}
	
	/* 비번찾기 */
	public MemberBean findpwd(String pwd_id, String pwd_name) {
		MemberBean member = null;
		try{
			con=ds.getConnection();
			sql="select mjjang_pass from mjjang_member where mjjang_id=? and mjjang_name=?";
			// 아이디와 회원이름을 기준으로 비번을 검색
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, pwd_id);	// 첫 물음표에 id 셋팅
			pstmt.setString(2, pwd_name);	// 두번째 물음표에 name 셋팅
			rs=pstmt.executeQuery();	// select문 실행
			if(rs.next()){	// 검색 결곽가 있는 경우
				member = new MemberBean();
				member.setDng_mem_pwd(rs.getString("dng_mem_pwd"));
				// 디비에서 해당하는 비번을 가져와서 빈의 Setter로 저장한다.
			}
			rs.close(); pstmt.close(); con.close();
		}catch(Exception e){
			e.printStackTrace();
		}finally {
		     if ( rs != null ) try{rs.close();}catch(Exception e){} 
		     if ( pstmt != null ) try{pstmt.close();}catch(Exception e){}
		     if ( con != null ) try{con.close();}catch(Exception e){}

	 }
		return member;
	}
	/* 회원 저장 */
	public void insertMember(MemberBean m) {
		try{
			con=ds.getConnection();
			sql="insert into deongeori_member (dng_mem_id, dng_mem_pwd, dng_mem_name, dng_mem_nickname," +
					"dng_mem_img, dng_mem_email, dng_mem_regdate) values(?,?,?,?,?,?,sysdate)";
			// 오라클에서 sysdate는 현재 날짜와 시간을 값으로 저장해준다.
			
			pstmt = con.prepareStatement(sql);
			// 물음표 순서대로 값 셋팅
			pstmt.setString(1, m.getDng_mem_id());
			pstmt.setString(2, m.getDng_mem_pwd());
			pstmt.setString(3, m.getDng_mem_name());
			pstmt.setString(4, m.getDng_mem_nickname());
			pstmt.setString(5, m.getDng_mem_img());
			pstmt.setString(6, m.getDng_mem_email());
			pstmt.executeUpdate();	// insert문 실행
			pstmt.close(); con.close();
		}catch(Exception e){
			e.printStackTrace();
		}finally {
		     if ( rs != null ) try{rs.close();}catch(Exception e){} 
		     if ( pstmt != null ) try{pstmt.close();}catch(Exception e){}
		     if ( con != null ) try{con.close();}catch(Exception e){}

	 }
		
	}
	
	/* 회원(사용자) 체크 */
	public int userCheck(String id, String pass) {
		int re = -1;
		try{
			con = ds.getConnection();			
			sql = "select dng_mem_pwd from deongeori_member where dng_mem_email=?";
			// mjjang_state=1은 가입회원인 경우에만 로그인 하도록 함
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1,id);
			rs=pstmt.executeQuery();
			if(rs.next()){
				if(rs.getString("dng_mem_pwd").equals(pass)){
					re=1;  // 비번이 같은 경우 1
				}else{
					re=0;  // 비번이 다른 경우 0
				}
			}
			rs.close(); pstmt.close(); con.close();
		}catch(Exception e){
			e.printStackTrace();
		}finally {
		     if ( rs != null ) try{rs.close();}catch(Exception e){} 
		     if ( pstmt != null ) try{pstmt.close();}catch(Exception e){}
		     if ( con != null ) try{con.close();}catch(Exception e){}

	 }
		return re; // re값을 리턴한다. 
		           // re=1이면 비번이 같은 경우
		           // re=0이면 비번이 다른경우
		           // re=-1이면 회원이 아닌 경우 
	}
	
	public MemberBean getMember(String id) {
		MemberBean m = null;
		try{
			con=ds.getConnection();
			sql="select * from deongeori_member where dng_mem_email=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, id);
			rs=pstmt.executeQuery();
			if(rs.next()){
				m = new MemberBean();
				m.setDng_mem_id(rs.getString("dng_mem_id"));
				m.setDng_mem_pwd(rs.getString("dng_mem_pwd"));
				m.setDng_mem_name(rs.getString("dng_mem_name"));
				m.setDng_mem_nickname(rs.getString("dng_mem_nickname"));
				m.setDng_mem_email(rs.getString("dng_mem_email"));
				m.setDng_mem_img(rs.getString("dng_mem_img"));
			}
			rs.close(); pstmt.close(); con.close();
			
		}catch(Exception e){
			e.printStackTrace();
		}finally {
		     if ( rs != null ) try{rs.close();}catch(Exception e){} 
		     if ( pstmt != null ) try{pstmt.close();}catch(Exception e){}
		     if ( con != null ) try{con.close();}catch(Exception e){}

	 }
		return m;
	}
	/* 회원 정보 수정 */	
	public int updateMember(MemberBean m) {
		int re= -1; // 리턴 변수를 -1로 초기화한다.
		try{
			con=ds.getConnection(); // 디비 연동
			sql="update deongeori_member set dng_mem_email=?, "
			    + " dng_mem_nickname=?, dng_mem_img=? "
			    + " where dng_mem_id=?";
			// 사용자 입력한 아이디와 일치한 회원의 정보를 수정한다. 
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, m.getDng_mem_email()); //1째 물음표
			pstmt.setString(2, m.getDng_mem_nickname()); //2째 물음표
			pstmt.setString(3, m.getDng_mem_img()); //2째 물음표
			pstmt.setString(4, m.getDng_mem_id());   //3째 물음표
			
			re=pstmt.executeUpdate(); // update 쿼리 실행
			// 쿼리가 성공하면 re에는 1이 저장된다.
			
			pstmt.close(); con.close();		
		}catch(Exception e){
			e.printStackTrace();
		}finally {
		     if ( rs != null ) try{rs.close();}catch(Exception e){} 
		     if ( pstmt != null ) try{pstmt.close();}catch(Exception e){}
		     if ( con != null ) try{con.close();}catch(Exception e){}

	 }
		return re; // re값을 리턴한다.(re=1이면 db가 수정되었다는 의미) 
	}
	
	/* 회원 탈퇴 */
	public int deleteMember(String id, String pass, String del_cont) {
		int re = -1;
		try{
			con=ds.getConnection();
			sql="select mjjang_pass from mjjang_member where mjjang_id=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, id);
			rs=pstmt.executeQuery();
			if(rs.next()){
				if(rs.getString("mjjang_pass").equals(pass)){
					// 비번이 일치한 경우
					sql="update mjjang_member set mjjang_delcont=?, mjjang_state=2, mjjang_deldate=sysdate where mjjang_id=?";
					// 해당 id의 탈퇴사유, 회원상태(2:탈퇴회원), 탈퇴날짜
					pstmt=con.prepareStatement(sql);
					pstmt.setString(1, del_cont);
					pstmt.setString(2, id);
					re=pstmt.executeUpdate();
				}else{
					re=0;
				}
			}
			rs.close(); pstmt.close(); con.close();
		}catch(Exception e){
			e.printStackTrace();
		}finally {
		     if ( rs != null ) try{rs.close();}catch(Exception e){} 
		     if ( pstmt != null ) try{pstmt.close();}catch(Exception e){}
		     if ( con != null ) try{con.close();}catch(Exception e){}

	 }
		return re;
	}
	
	public int editPwdCheck(String id, String pass) {
		int re = -1;
		try{
			con = ds.getConnection();			
			sql = "select dng_mem_pwd from deongeori_member where dng_mem_id=?";
			// mjjang_state=1은 가입회원인 경우에만 로그인 하도록 함
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1,id);
			rs=pstmt.executeQuery();
			if(rs.next()){
				if(rs.getString("dng_mem_pwd").equals(pass)){
					re=1;  // 비번이 같은 경우 1
				}else{
					re=0;  // 비번이 다른 경우 0
				}
			}
			rs.close(); pstmt.close(); con.close();
		}catch(Exception e){
			e.printStackTrace();
		}finally {
		     if ( rs != null ) try{rs.close();}catch(Exception e){} 
		     if ( pstmt != null ) try{pstmt.close();}catch(Exception e){}
		     if ( con != null ) try{con.close();}catch(Exception e){}

	 }
		return re; // re값을 리턴한다. 
		           // re=1이면 비번이 같은 경우
		           // re=0이면 비번이 다른경우
		           // re=-1이면 회원이 아닌 경우 
	}
	
	/* 수정창 */
	public String getEditf(String id, String pwd){
		String email=null;
		String nickname=null;
		String profileimg=null;
		String re=null;
		try{
			con = ds.getConnection();			
			sql = "select dng_mem_email, dng_mem_nickname, dng_mem_img from deongeori_member where dng_mem_id=? and dng_mem_pwd=?";
			// mjjang_state=1은 가입회원인 경우에만 로그인 하도록 함
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1,id);
			pstmt.setString(2,pwd);
			rs=pstmt.executeQuery();
			
			if(rs.next()){
				email=rs.getString("dng_mem_email");
				nickname=rs.getString("dng_mem_nickname");
				profileimg=rs.getString("dng_mem_img");
			}
			else{
				return re;
			}
			rs.close(); pstmt.close(); con.close();
		}catch(Exception e){
			e.printStackTrace();
		}finally {
		     if ( rs != null ) try{rs.close();}catch(Exception e){} 
		     if ( pstmt != null ) try{pstmt.close();}catch(Exception e){}
		     if ( con != null ) try{con.close();}catch(Exception e){}

	 }
		re = "{'member':[";
		re += "{'email':'" + email + "',";
		re += "'nickname':'" + nickname + "',";
		re += "'profileimg':'" + profileimg + "'}]}"; 
		System.out.println(re);
		return re;
	}
	
	public List<DeongeoriBean> getDng(String dng_mem_id){
		List<DeongeoriBean> dnglist = new ArrayList<DeongeoriBean>();
		DeongeoriBean d = null;
		try{
			con=ds.getConnection();
			sql="select dng_id, dng_name, dng_img from deongeori where dng_id in(select dng_id from deongeori_join_member where dng_mem_id =?)";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, dng_mem_id);
			rs=pstmt.executeQuery();
			while(rs.next())			
			{
				d = new DeongeoriBean();
				
				System.out.println(rs.getString("dng_id") + "-" + rs.getString("dng_name") + "-" + rs.getString("dng_img"));
				
				d.setDng_id(rs.getString("dng_id"));
				d.setDng_name(rs.getString("dng_name"));
				d.setDng_img(rs.getString("dng_img"));
				dnglist.add(d);
			}
			rs.close(); pstmt.close(); con.close();
			
		}catch(Exception e){
			e.printStackTrace();
		}finally {
		     if ( rs != null ) try{rs.close();}catch(Exception e){} 
		     if ( pstmt != null ) try{pstmt.close();}catch(Exception e){}
		     if ( con != null ) try{con.close();}catch(Exception e){}

	 }
		return dnglist;
	}

}