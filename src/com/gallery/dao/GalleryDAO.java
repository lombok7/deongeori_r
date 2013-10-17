package com.gallery.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class GalleryDAO { //디비 연동 클래스
	// 디비 연결과 SQL쿼리 실행에 관련한 참조변수
	Connection con=null;
	PreparedStatement pstmt=null;
	ResultSet rs=null;
	DataSource ds=null;
	String sql=null;
	
	public GalleryDAO(){
		/*
		 * 커넥션 풀(Connection Pool)이란?
		 *  - 데이터베이스와 연결되는 Connection 객체를 미리 생성하여
		 *   Pool 속에 저장해 두고 필요할 때 접근하여 객체를 사용한다.
		 *   또한 사용이 종료되면 반환한다.
		 *  - 장점 : 커넥션 객체 생성에 소요되는 시간이 절약되어 db 접근이 빠르고,
		 *           시스템의 과부하를 줄일 수 있다.
		 */
        try{
        	/*
        	 *  커넥션 풀 작업순서
        	 *  1. JNDI에 접근하기 위한 객체를 생성한다.
        	 *     JNDI : Java Naming Directory Interface
        	 *     context.xml 파일이 있는 디렉토리에 접근
        	 *  2. context.xml에 설정된 리소스를 참조하여 데이터소스 
        	 *     객체를 생성한다.
        	 *  3. 생성된 객체를 이용하여 DB에 연결한다.
        	 *   
        	 */
        	Context ctx=new InitialContext(); //1번
        	ds=(DataSource)ctx.lookup("java:comp/env/jdbc/orcl"); //2번
        	con=ds.getConnection(); //3번
        	
        }catch(Exception e){
        	e.printStackTrace();
        }

	}
	// 게시물 목록 보기
	public List<GalleryBean> getGalleryList(int page, int limit){
		// List컬렉션에 제네릭(BoardBean 타입)을 적용하여 객체 생성
		List<GalleryBean> GalleryList = new ArrayList<GalleryBean>();
		
		try{
			//게시판번호를 기준으로 내림차순으로 검색하는 쿼리문
			sql="select * from deongeori_gallery order by dng_gal_id desc";
			//sql문 선 실행
			pstmt=con.prepareStatement(sql);
			//select문의 실행은 excutequery()메소드 이용
			//insert, update, delete문은 executeUpdate()메소드 이용
			rs=pstmt.executeQuery(); // 쿼리문 실행결과를 rs에 리턴
			while(rs.next()){
				//자바 빈 객체 생성
				GalleryBean gb=new GalleryBean();
				//자바 빈 객체를 이용하여 setter/getter()메소드로 필드값 지정
				gb.setDng_gal_id(rs.getInt("dng_gal_id"));
				//rs.getInt("board_no")는 DB에서 board_no를 정수값으로 
				//가져오는 역할
				gb.setDng_gal_writer(rs.getString("dng_gal_writer"));
				gb.setDng_gal_content(rs.getString("dng_gal_content"));
				gb.setDng_gal_pwd(rs.getString("dng_gal_pwd"));
				gb.setDng_gal_likecount(rs.getInt("dng_gal_likecount"));
				gb.setDng_gal_img(rs.getString("dng_gal_img"));
				gb.setDng_gal_regdate(rs.getString("dng_gal_regdate"));
				
				GalleryList.add(gb);
				
			}
			rs.close(); pstmt.close(); con.close();
		}catch(Exception e){
			e.printStackTrace();
		}finally {
		     if ( rs != null ) try{rs.close();}catch(Exception e){} 
		     if ( pstmt != null ) try{pstmt.close();}catch(Exception e){}
		     if ( con != null ) try{con.close();}catch(Exception e){}

	 }		

		return GalleryList;
	}
	/*조회수 증가
	public void boardHit(int no) {
		try{
			con=ds.getConnection();
			sql="update board set board_hit=board_hit+1 where board_no=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, no);
			pstmt.executeUpdate(); //update는 executeUpdate()사용
			// 쿼리문 성공시 반환값은 1이다
			pstmt.close(); con.close();
   		}catch(Exception e){
			e.printStackTrace();
		}
		
	}*/
	
	public List<GalleryBean> getRecentGalleryList(String dng_id) {
		List<GalleryBean> galleryList = new
			     ArrayList<GalleryBean>();
	try{
		con=ds.getConnection();
		sql = "select * from deongeori_gallery where dng_id=? AND ROWNUM <=9 order by dng_gal_id desc";
		pstmt = con.prepareStatement(sql);
		pstmt.setString(1, dng_id);
		rs=pstmt.executeQuery(); 
		while(rs.next()){

			GalleryBean gallery = new GalleryBean();

			gallery.setDng_id(rs.getString("dng_id"));
			gallery.setDng_gal_id(rs.getInt("dng_gal_id"));
			gallery.setDng_gal_writer(rs.getString("dng_gal_writer"));
			gallery.setDng_gal_content(rs.getString("dng_gal_content"));
			gallery.setDng_gal_pwd(rs.getString("dng_gal_pwd"));
			gallery.setDng_gal_likecount(rs.getInt("dng_gal_likecount"));
			gallery.setDng_gal_img(rs.getString("dng_gal_img"));
			gallery.setDng_gal_regdate(rs.getString("dng_gal_regdate"));

			galleryList.add(gallery);
		}
			
		}catch(Exception e){
			e.printStackTrace();
	}finally {
	     if ( rs != null ) try{rs.close();}catch(Exception e){} 
	     if ( pstmt != null ) try{pstmt.close();}catch(Exception e){}
	     if ( con != null ) try{con.close();}catch(Exception e){}
		}		
	return galleryList;
	}
	
	// 게시물 내용보기
	public GalleryBean getCont(int no) {
		GalleryBean gallery=null;
		try{
			con=ds.getConnection(); //디비 연동 객체 생성
			sql="select * from deongeori_gallery where dng_gal_id=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, no);
			rs=pstmt.executeQuery();
			// select 문만 유일하게 executeQuery() 메소드 사용
			if(rs.next()){
				gallery=new GalleryBean();
				gallery.setDng_gal_id(rs.getInt("dng_gal_id"));
				// 디비에서 글번호를 가져와서 빈에 저장한다.
				// 데이터 타입이 정수이면 getInt()메소드를
				// 문자열이면 getString()메소드를 이용한다.
				gallery.setDng_gal_writer(rs.getString("dng_gal_writer"));
				gallery.setDng_gal_content(rs.getString("dng_gal_content"));
				gallery.setDng_gal_pwd(rs.getString("dng_gal_pwd"));
				gallery.setDng_gal_likecount(rs.getInt("dng_gal_likecount"));
				gallery.setDng_gal_img(rs.getString("dng_gal_img"));
				gallery.setDng_gal_regdate(rs.getString("dng_gal_regdate"));
			}
			rs.close(); pstmt.close(); con.close();
		}catch(Exception e){
			e.printStackTrace();
		}finally {
		     if ( rs != null ) try{rs.close();}catch(Exception e){} 
		     if ( pstmt != null ) try{pstmt.close();}catch(Exception e){}
		     if ( con != null ) try{con.close();}catch(Exception e){}

	 }		

		return gallery;
	}

	
	//  게시물 저장!
	 public void insertGal(GalleryBean gb) {
		   try{
		     con=ds.getConnection();
		     /*sql="select max(dng_gal_id) from deongeori_gallery";
		     //max 함수를 사용하여 최대 글번호값을 가져온다.
		     pstmt=con.prepareStatement(sql);
		     rs=pstmt.executeQuery();
		     int num=0;
		     if(rs.next()){
		      num=rs.getInt(1)+1;//최대글번호+1
		     }else{
		      num=1;
		     }*/
		  
		 sql="insert into deongeori_gallery (dng_id, dng_gal_id, dng_gal_writer, dng_gal_content," +
		   " dng_gal_pwd, dng_gal_likecount, dng_gal_img, dng_gal_regdate) values(?,dng_gal_seq.nextval,?,?,?,?,?,sysdate)";
		 //now()함수를 사용하여 등록날짜를 저장
		           pstmt=con.prepareStatement(sql);
		           pstmt.setString(1,gb.getDng_id());
		           pstmt.setString(2,gb.getDng_gal_writer());
		           pstmt.setString(3,gb.getDng_gal_content());
		           pstmt.setString(4,gb.getDng_gal_pwd());
		           pstmt.setInt(5,gb.getDng_gal_likecount());
		           pstmt.setString(6,gb.getDng_gal_img());
		         
		           pstmt.executeUpdate();//삽입문 실행
		           
		       }catch(Exception e){
		     e.printStackTrace();
		    }finally {
		        if ( pstmt != null ) try{pstmt.close();}catch(Exception e){}
		        if ( con != null ) try{con.close();}catch(Exception e){}

		   }  

		  }
	 
	public int getListCount() {
		int count=0;
        try{
        	con=ds.getConnection();
        	sql="select count(*) from deongeori_gallery";
        	pstmt=con.prepareStatement(sql);
        	rs=pstmt.executeQuery();//쿼리문 실행
        	if(rs.next()){//레코드가 있다면
        		count=rs.getInt(1);//총 레코드 수를 저장
        	}
        	rs.close(); pstmt.close(); con.close();
        }catch(Exception e){
        	e.printStackTrace();
        }finally {
		     if ( rs != null ) try{rs.close();}catch(Exception e){} 
		     if ( pstmt != null ) try{pstmt.close();}catch(Exception e){}
		     if ( con != null ) try{con.close();}catch(Exception e){}

	 }		

		return count;
	}
	
	public List<GalleryBean> getGalleryList(String dng_id) {
		List<GalleryBean> galleryList = new
			     ArrayList<GalleryBean>();
	try{
		con=ds.getConnection();
		sql = "select * from deongeori_gallery where dng_id=? order by dng_gal_id desc";
		pstmt = con.prepareStatement(sql);
		pstmt.setString(1, dng_id);
		rs=pstmt.executeQuery(); 
		while(rs.next()){

			GalleryBean gallery = new GalleryBean();

			gallery.setDng_id(rs.getString("dng_id"));
			gallery.setDng_gal_id(rs.getInt("dng_gal_id"));
			gallery.setDng_gal_writer(rs.getString("dng_gal_writer"));
			gallery.setDng_gal_content(rs.getString("dng_gal_content"));
			gallery.setDng_gal_pwd(rs.getString("dng_gal_pwd"));
			gallery.setDng_gal_likecount(rs.getInt("dng_gal_likecount"));
			gallery.setDng_gal_img(rs.getString("dng_gal_img"));
			gallery.setDng_gal_regdate(rs.getString("dng_gal_regdate"));

			galleryList.add(gallery);
		}
		rs.close(); pstmt.close(); con.close();			
		}catch(Exception e){
			e.printStackTrace();
	}finally {
	     if ( rs != null ) try{rs.close();}catch(Exception e){} 
	     if ( pstmt != null ) try{pstmt.close();}catch(Exception e){}
	     if ( con != null ) try{con.close();}catch(Exception e){}
		}		
	return galleryList;
	}
	
	public void dng_likecount(int num) {
		sql="update deongeori_gallery set dng_gal_likecount=dng_gal_likecount+1 where dng_gal_id=?";
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
	public GalleryBean getcont(int num) {
		GalleryBean gallery=null;
		try{
			con=ds.getConnection(); // ��� ���� ��ü ��
			sql = "select * from deongeori_gallery where dng_gal_id=? order by dng_gal_id desc";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, num);
			rs=pstmt.executeQuery(); 
			// select���� �����ϰ� executeQuery() �޼ҵ� ���
			if(rs.next()){// ��� ���ڵ尪�� ������ true
				gallery=new GalleryBean();
			gallery.setDng_id(rs.getString("dng_id"));
			gallery.setDng_gal_id(rs.getInt("dng_gal_id"));
			gallery.setDng_gal_img(rs.getString("dng_gal_img"));
			gallery.setDng_gal_writer(rs.getString("dng_gal_writer"));
			gallery.setDng_gal_content(rs.getString("dng_gal_content"));
			gallery.setDng_gal_pwd(rs.getString("dng_gal_pwd"));
			gallery.setDng_gal_likecount(rs.getInt("dng_gal_likecount"));
			gallery.setDng_gal_regdate(rs.getString("dng_gal_regdate"));
			}
			rs.close(); pstmt.close(); con.close();
		}catch(Exception e){
			e.printStackTrace();
		}finally {
		     if ( rs != null ) try{rs.close();}catch(Exception e){} 
		     if ( pstmt != null ) try{pstmt.close();}catch(Exception e){}
		     if ( con != null ) try{con.close();}catch(Exception e){}

	 }		
		
		return gallery;  // board ���̺? �ִ� ��ü ���ڵ� ����
	}
	
	public GalleryBean getGalMostLiked(String dng_id) {
		GalleryBean gallery=null;
		try{
			con=ds.getConnection(); // ��� ���� ��ü ��
			sql = "select * from deongeori_gallery where dng_id=? order by dng_gal_likecount desc";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, dng_id);
			rs=pstmt.executeQuery(); 
			// select���� �����ϰ� executeQuery() �޼ҵ� ���
			if(rs.next()){// ��� ���ڵ尪�� ������ true
				gallery=new GalleryBean();
			gallery.setDng_id(rs.getString("dng_id"));
			gallery.setDng_gal_id(rs.getInt("dng_gal_id"));
			gallery.setDng_gal_img(rs.getString("dng_gal_img"));
			gallery.setDng_gal_writer(rs.getString("dng_gal_writer"));
			gallery.setDng_gal_content(rs.getString("dng_gal_content"));
			gallery.setDng_gal_pwd(rs.getString("dng_gal_pwd"));
			gallery.setDng_gal_likecount(rs.getInt("dng_gal_likecount"));
			gallery.setDng_gal_regdate(rs.getString("dng_gal_regdate"));
			}
			rs.close(); pstmt.close(); con.close();
		}catch(Exception e){
			e.printStackTrace();
		}finally {
		     if ( rs != null ) try{rs.close();}catch(Exception e){} 
		     if ( pstmt != null ) try{pstmt.close();}catch(Exception e){}
		     if ( con != null ) try{con.close();}catch(Exception e){}
		}		
		return gallery;  // board ���̺? �ִ� ��ü ���ڵ� ����
		}
	
	}

