package dng.deongeori.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mtory.action.Action;
import com.mtory.action.ActionForward;

import dng.common.CommonUtil;
import dng.deongeori.DeongeoriDAO;
import dng.deongeori.DeongeoriInviteMessageDAO;
import dng.deongeori.DeongeoriJoinMemberBean;

public class DeongeoriJoinOkAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		
		PrintWriter out=response.getWriter();		// 출력스트림 객체 생성.
		
		int dng_inv_id = Integer.parseInt(request.getParameter("dng_inv_id"));
		String dng_name = request.getParameter("dng_name");
		String dng_mem_id = request.getParameter("dng_mem_id");
		
		boolean isJoinOk = false;
		boolean isInsertDjm = false;
		
		System.out.println(dng_inv_id + "-" + dng_name + "-" + dng_mem_id);
		
		// Atomic 하게?
		// 덩어리 가입 수락을 하면,
		// 1. '덩어리 초대 메세지' 테이블에 수락 여부를 'Y'로 바꾼다.
		
		DeongeoriInviteMessageDAO dnginvmsgdao = new DeongeoriInviteMessageDAO();
		isJoinOk = dnginvmsgdao.JoinDeongeori(dng_inv_id);
		
		if (isJoinOk) {
		
			// 2. '덩어리 가입 정보' 테이블에 정보 저장.
			DeongeoriDAO dngdao = new DeongeoriDAO();
			DeongeoriJoinMemberBean djmbean = new DeongeoriJoinMemberBean();
	    	
	    	djmbean.setDng_id(CommonUtil.dngnameTodngid(dng_name));
	    	djmbean.setDng_mem_id(dng_mem_id);
	    	
	    	isInsertDjm = dngdao.insertDeongeoriJoinMember(djmbean);
	    	
		} else {
			
			out.println("<script>");
        	out.println("alert('덩어리 초대 메세지 수락에 실패하였습니다!')");
        	out.println("history.back();");
        	out.println("</script>");
        	
		}
		
		if (isInsertDjm) {
				
			// 3. '/deongeori_invmsg_list.do' 로 돌아감. (수락한 초대 메세지는 보이지 않아야 함.)
	    	ActionForward forward = new ActionForward();
			
	    	forward.setRedirect(true);
			forward.setPath("./deongeori_invmsg_list.do?dng_mem_id=" + dng_mem_id);
			
			return forward;
					
		} else {
			
			out.println("<script>");
        	out.println("alert('덩어리 가입에 실패하였습니다!')");
        	out.println("history.back();");
        	out.println("</script>");
			
		}
		return null;
	}
}
