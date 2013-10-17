package com.member.action;

import java.io.File;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.member.MemberBean;
import com.member.MemberDAO;
import com.mtory.action.Action;
import com.mtory.action.ActionForward;
import com.oreilly.servlet.MultipartRequest;

public class MemberEditOkAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
	    request.setCharacterEncoding("UTF-8");
	    //method=post방식일때 한글을 안깨지게 함
	    MemberDAO md=new MemberDAO();
	    response.setContentType("text/html;charset=UTF-8");
	    
	    String saveFolder=request.getSession().getServletContext().getRealPath("upload/mbimages") + "/";
	    //이진파일 업로드 서버 경로,자바에서 경로 구분할때 역슬래쉬 2개 또는 슬래쉬 1개
	    int fileSize=5*1024*1024;//이진파일 업로드 최대 크기(5MB)
	          
	    MultipartRequest multi=null;
	    multi=new MultipartRequest(request,saveFolder,fileSize,"UTF-8");
	    
	    PrintWriter out=response.getWriter();//출력스트림 객체 생성
	    HttpSession session=request.getSession();//세션 객체 생성
	    
	    String id=(String)session.getAttribute("id");
	    MemberBean m=new MemberBean();
	    //세션키에 저장된 아이디값을  Object 형으로 반환한다. 이것을 문자열
	    //변수에 저장하기 위해서 강제 다운캐스팅함
	    //세션은 서버에서 실행되고 로그인 인증을 하기 위해서 사용.보안이 좋다
	    //세션은 브라우저당 한개씩 발생
		  if(id != null){  
		  String dng_mem_email=multi.getParameter("dng_mem_email").trim();
		  String dng_mem_nickname=multi.getParameter("dng_mem_nickname").trim();
		  
		  
		  File UpFile=multi.getFile("dng_mem_profileimgs");
	        //첨부한 이진파일을 가져옴
	   
	        if(UpFile != null){//첨부한 이진파일이 있다면
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
			     String refileName=id+"." + fileExtension;//새로운 파일명을 저장
			     //디비에 저장될 레코드 값
			     File UpFile2=new File(saveFolder+refileName);
			     if(UpFile2.exists()){
			    	 UpFile2.delete();
				 }
			     UpFile.renameTo(new File(saveFolder+refileName));
			     //바뀐 이진파일명으로 업로드
			     System.out.println(refileName);
			     m.setDng_mem_img(refileName);
			     session.setAttribute("profileimg", refileName);
	        }
		  		  
		  m.setDng_mem_email(dng_mem_email); 
		  m.setDng_mem_nickname(dng_mem_nickname);
		  m.setDng_mem_id((String)session.getAttribute("id"));
		  int re=md.updateMember(m);//수정 메서드 호출
		  
	  	  
	  	  session.setAttribute("email",dng_mem_email);
		  session.setAttribute("nickname", dng_mem_nickname);
		  
		  
		 	
		  if(re==1){
			  out.println("<script>");
			  out.println("alert('수정에 성공했습니다!')");
			  out.println("location='main.do'");
			  out.println("</script>");
		  }else{//수정 실패시
			  out.println("<script>");
			  out.println("alert('수정에 실패했습니다!')");
			  out.println("history.back()");
			  out.println("</script>");
		  }
		  }else{
			  out.println("<script>");
			  out.println("alert('로그인후 이용이 가능합니다.')");
			  out.println("location='index.do'");
			  out.println("</script>");
		}  
				return null;
	}
}
