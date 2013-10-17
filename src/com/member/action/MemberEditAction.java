package com.member.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.member.MemberBean;
import com.member.MemberDAO;
import com.mtory.action.Action;
import com.mtory.action.ActionForward;

public class MemberEditAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
	    response.setContentType("text/html;charset=UTF-8");
	    PrintWriter out=response.getWriter();
	    
		HttpSession session=request.getSession();
	    //세션이 객체를 생성
	    String id=(String)session.getAttribute("id");
	    //세션 아이디 값을 취득
	    
	    if(id == null){
	    	out.println("<script>");
	    	out.println("alert('다시 로그인 해 주세요!')");
	    	out.println("location='member_Login.do'");
	    	out.println("</script>");
	    }else{
	    	MemberDAO md=new MemberDAO();
	    	MemberBean m=md.getMember(id);
	    	//아이디에 해당하는 디비 회원정보를 가져온다.
	    	request.setAttribute("member", m);
	    	//member 키값에 디비 회원정보를 저장
	    	
	    	ActionForward forward=new ActionForward();
	    	forward.setRedirect(false);
	    	forward.setPath("./member/member_edit.jsp");
	    	return forward;
	    }
		return null;
	}
}
