package com.member.action;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.member.MemberBean;
import com.member.MemberDAO;
import com.mtory.action.Action;
import com.mtory.action.ActionForward;

import dng.deongeori.DeongeoriBean;
import dng.deongeori.DeongeoriInviteMessageDAO;

public class MemberLoginOkAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		// 로그인 인증 아이디를 저장하기 위해서 세션 객체를 생성한다.
		HttpSession session = request.getSession();
		MemberDAO md = new MemberDAO();
		
		String id = request.getParameter("logid").trim();
		String pass = request.getParameter("pass").trim();
		
		// 사용자가 회원으로 가입되었는가를 체크하는 메소드
		// MemberDAO 클래스에 해당 메소드를 작성해야 한다.
		int result=md.userCheck(id, pass);
		
		if(result==1){ // 비번이 같은 경우
			// 회원의 정보를 가져오는 메소드를 호출한다.
			// MemberDAO 클래스에 해당 메소드를 작성해야 한다.
			MemberBean m = md.getMember(id);
			String dng_mem_id = m.getDng_mem_id();
			String dng_mem_email = m.getDng_mem_email();
			String dng_mem_name = m.getDng_mem_name();
			String dng_mem_nickname = m.getDng_mem_nickname();
			String dng_mem_profileimg= m.getDng_mem_img();
			
			// 로그인 사용자의 세션 값을 저장한다.
			
			session.setAttribute("id", dng_mem_id);
			session.setAttribute("email", dng_mem_email);
			session.setAttribute("name", dng_mem_name);
			session.setAttribute("nickname", dng_mem_nickname);
			session.setAttribute("profileimg", dng_mem_profileimg);
			
			List<DeongeoriBean> dnglist = md.getDng(dng_mem_id);
			session.setAttribute("dnglist", dnglist);
			session.setAttribute("dngsize", dnglist.size());
			
			// 받은 초대 갯수 가져오기.
			DeongeoriInviteMessageDAO dnginvmsgdao = new DeongeoriInviteMessageDAO();
			int invmsgcnt = dnginvmsgdao.getDngInvMessageCount(dng_mem_id); 
			
			System.out.println(invmsgcnt);
			
			session.setAttribute("invmsgcnt", invmsgcnt);
			
			// 처리결과를 보여주는 view 페이지 포워딩
			ActionForward forward = new ActionForward();
			forward.setRedirect(true);
			forward.setPath("./main.do");
			return forward;
		}else if(result==0){
			out.println("<script>");
			out.println("alert('비번이 일치하지 않습니다.!!')");
			out.println("history.back()");
			out.println("</script>");
		}else if(result==-1){ // 등록된 회원이 아닌 경우
			out.println("<script>");
			out.println("alert('등록되지 않은 아이디 입니다!!')");
			out.println("history.go(-1)");
			out.println("</script>");			
		}
		return null;
	}
}
