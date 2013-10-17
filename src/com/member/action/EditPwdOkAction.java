package com.member.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.member.MemberBean;
import com.member.MemberDAO;
import com.mtory.action.Action;
import com.mtory.action.ActionForward;

public class EditPwdOkAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		// 로그인 인증 아이디를 저장하기 위해서 세션 객체를 생성한다.
		
		HttpSession session=request.getSession();
		MemberDAO md = new MemberDAO();
		
		//세션이 객체를 생성
		System.out.println(session.getAttribute("id"));
		System.out.println(request.getParameter("edit_pwd").trim());
		
	    String id=(String)session.getAttribute("id");
		String pass = request.getParameter("edit_pwd").trim();
		
		// 사용자가 회원으로 가입되었는가를 체크하는 메소드
		// MemberDAO 클래스에 해당 메소드를 작성해야 한다.
		int result=md.editPwdCheck(id, pass);
		
		if(result==1){ // 비번이 같은 경우
			System.out.println("s : " + session.getAttribute("nickname"));
			request.setAttribute("dng_mem_nickname", session.getAttribute("nickname"));
			System.out.println("s : " + request.getAttribute("dng_mem_nickname"));
			
			ActionForward forward = new ActionForward();
			forward.setRedirect(false);
			forward.setPath("./member_edit.do");
			return forward;
		}else if(result==0){
			out.println("<script>");
			out.println("alert('비번이 일치하지 않습니다.!!')");
			out.println("history.back()");
			out.println("</script>");
		}
		return null;
	}

}
