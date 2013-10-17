package dng.deongeori.action;

import java.io.File;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mtory.action.Action;
import com.mtory.action.ActionForward;
import com.oreilly.servlet.MultipartRequest;

import dng.common.CommonUtil;
import dng.deongeori.DeongeoriBean;
import dng.deongeori.DeongeoriDAO;
import dng.deongeori.DeongeoriJoinMemberBean;



public class DeongeoriCreateOkAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		
		// 파일 업로드 서버 경로, 자바에서 경로 구분할때 역슬래쉬 2개 또는 슬래쉬 1개.

		/*String saveFolder="C:/workspace(web)/deong/WebContent/upload/dgimages/";*/

		String saveFolder=request.getSession().getServletContext().getRealPath("upload/dgimages") + "/";

	    
	    int fileSize = 5*1024*1024;	// 파일 업로드 최대 크기 (5MB)
	          
	    MultipartRequest multi=null;
	    multi=new MultipartRequest(request, saveFolder, fileSize, "UTF-8");
	    
	    PrintWriter out=response.getWriter();		// 출력스트림 객체 생성.
	    HttpSession session=request.getSession();	// 세션 객체 생성.
	    
	    // 세션키에 저장된 아이디값을  Object 형으로 반환한다.
	    // 이것을 문자열 변수에 저장하기 위해서 강제 다운 캐스팅 함.
	    // 세션은 서버에서 실행되고 로그인 인증을 하기 위해서 사용. 보안이 좋다.
	    // 세션은 브라우저당 한개씩 발생.
	    String s_mem_id = (String)session.getAttribute("id");
	    String refileName = null;
	    File tempFile = null;
	    
	    if (s_mem_id != null) {
	    	
		    DeongeoriBean dngbean = new DeongeoriBean();
		    DeongeoriDAO dngdao = new DeongeoriDAO();
	    	
	    	// 덩어리 아이디 발급.
	    	String dng_id = CommonUtil.getId("d");
	    	
	    	String dng_name = multi.getParameter("dng_name").trim();
	   		  
	    	File dngImgFile=multi.getFile("dng_img");
	   
	        if (dngImgFile != null) { // 첨부 파일이 있다면
	        	
	        	String fileName=dngImgFile.getName();//이진파일명을 저장
	        
	        	//***** 확장자 구하기 시작 *****//*
	        	int index = fileName.lastIndexOf(".");
	        	String fileExtension = fileName.substring(index + 1);
	        	//***** 확장자 구하기 끝 *****//*
	        	
	        	// 새로운 파일명. (dng_id.확장자)
	        	refileName = dng_id +"." + fileExtension;
 
	        	// 디비에 저장될 파일 이름. (경로 미포함.)
	        	tempFile = new File(saveFolder + refileName);
	        	
	        	// 같은 파일이 이미 존재한다면 삭제.
	        	if (tempFile.exists()) {
	        		tempFile.delete();
	        	}
	        	
	        	// 파일명 변경.
	        	dngImgFile.renameTo(new File(saveFolder + refileName));
	        }	
	        
	        dngbean.setDng_id(dng_id);
	        dngbean.setDng_name(dng_name);
	        dngbean.setDng_img(refileName);
	        dngbean.setDng_admin(s_mem_id);
	        
	        boolean isCreateDeongeori = dngdao.createDeongeori(dngbean);
	        
	        // 덩어리 생성에 성공했다면, 덩어리 가입 정보 저장.
	        if (isCreateDeongeori) {
	        	DeongeoriJoinMemberBean djmbean = new DeongeoriJoinMemberBean();
	        	
	        	djmbean.setDng_id(dng_id);
	        	djmbean.setDng_mem_id(s_mem_id);
	        	
	        	boolean isInsertDjm = dngdao.insertDeongeoriJoinMember(djmbean);
	  
	        	// 덩어리 가입 정보 저장에 성공했으면...
	        	if (isInsertDjm) {
	        		
	       	       	request.setAttribute("dng_id", dng_id);
	        		request.setAttribute("dng_img", refileName);
	        		
		        	ActionForward forward = new ActionForward();
					forward.setRedirect(true);
					forward.setPath("./deongeori_main.do?dng_id=" + dng_id);
					return forward;
					
	        	} else {
	        	
	        		out.println("<script>");
		        	out.println("alert('덩어리 가입에 실패하였습니다!')");
		        	out.println("history.back();");
		        	out.println("</script>");
	        	}
	        	
	        } else {
	        	
	        	// 생성 실패 시, 업 로드된 덩어리 로고 이미지 삭제.
	        	if (tempFile.exists()) {
	        		tempFile.delete();
	        	}
	        	
	        	out.println("<script>");
	        	out.println("alert('덩어리 생성에 실패하였습니다!')");
	        	out.println("history.back();");
	        	out.println("</script>");
	        }	        
	    }	 
	    
	    return null;
	}	
}
