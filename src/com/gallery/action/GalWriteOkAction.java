package com.gallery.action;

import java.io.File;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.gallery.dao.GalleryBean;
import com.gallery.dao.GalleryDAO;
import com.mtory.action.Action;
import com.mtory.action.ActionForward;
import com.oreilly.servlet.MultipartRequest;

import dng.common.CommonUtil;

public class GalWriteOkAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		GalleryBean gb=new GalleryBean(); //자바빈 객체 생성
		String saveFolder=request.getSession().getServletContext().getRealPath("upload/galimages") + "/";
		
		HttpSession session = request.getSession();
		String dng_id = (String)session.getAttribute("dng_id");
		System.out.println(dng_id);
		// 자료실 파일 업로드 경로 
		int fileSize=5*1024*1024; //이진파일 업로드 최대 크기
		
		
	
		// 이진 파일을 업로드하기 위한 처리 내용
		MultipartRequest multi=null;
		multi=new MultipartRequest(request, saveFolder,fileSize,"utf-8");
		// 이진파일 업로드를 위한 MultipartRequest 매개변수
		// 1번째 전달인자 : 사용자 폼에서 입력한 정보를 서버에 가져온다.
		// 2번째 전달인자 : 이진파일 업로드 서버 경로
		// 3번째 전달인자 : 이진파일 업로드 최대 크기
		// 4번째 전달인자 : 한글 인코딩 방식
		
		// 자료실 입력폼에서 사용자가 입력한 데이터를 가져와서 변수에 저장
		String dng_gal_writer=multi.getParameter("dng_gal_writer").trim();
		String dng_gal_content =multi.getParameter("dng_gal_content").trim();
		String dng_gal_pwd =multi.getParameter("dng_gal_pwd").trim();
		File UpFile=multi.getFile("dng_gal_img");
						
		if(UpFile !=null){
			String fileName=UpFile.getName();//이진파일명을 저장
			 
       	 //****확장자 구하기 시작 ****//*
		     int index = UpFile.getName().lastIndexOf(".");
		     //File 클래스의 getName() 메서드는 이진파일명을 받아온다.
		     //lastIndexOf("문자")는  String클래스의 메서드로 해당문자
		     //를 문자열 끝 즉 우측에서 헤아려 문자의 위치번호를 반환한다.
		     String fileExtension = 
		        UpFile.getName().substring(index + 1);
		     //파일의 확장자를 구한다.
		     //****확장자 구하기 끝 ***//*
		     String refileName=CommonUtil.getId("g")+"." + fileExtension;//새로운 파일명을 저장
		     //디비에 저장될 레코드 값
		     File UpFile2=new File(saveFolder+refileName);
		     if(UpFile2.exists()){
		    	 UpFile2.delete();
			 }
		     UpFile.renameTo(new File(saveFolder+refileName));
		     //바뀐 이진파일명으로 업로
		     gb.setDng_gal_img(refileName);
		}
		// 업로드할 파일을 지정하지 않은 경우-이름,비번,내용만 저장
		gb.setDng_gal_writer(dng_gal_writer);
		gb.setDng_gal_content(dng_gal_content);
		gb.setDng_gal_pwd(dng_gal_pwd);
		gb.setDng_id(dng_id);
		
		GalleryDAO gd=new GalleryDAO(); // DB 연동 객체 생성
		
		// BbsDAO 클래스에서 insertBbs() 메소드를 추가한다.
		gd.insertGal(gb); // 저장 메소드 호출
		
		response.sendRedirect("./deongeori_list.do?dng_id="+dng_id);
		// 게시물 목록으로 이동하여 업로드한 결과를 보여준다.
		return null;
	}

}
