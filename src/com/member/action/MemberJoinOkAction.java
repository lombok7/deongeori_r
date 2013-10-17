package com.member.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.member.MemberBean;
import com.member.MemberDAO;
import com.mtory.action.Action;
import com.mtory.action.ActionForward;

import dng.common.CommonUtil;
import dng.deongeori.DeongeoriInviteMessageDAO;

public class MemberJoinOkAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		
		MemberDAO md=new MemberDAO();
		MemberBean m=new MemberBean();
		
		HttpSession session = request.getSession();
			    
        String dng_mem_email=request.getParameter("email").trim();
        String dng_mem_pwd=request.getParameter("join-pass").trim();
        String dng_mem_name=request.getParameter("name").trim();
        String dng_mem_nickname=request.getParameter("nick").trim();
           
        m.setDng_mem_id(CommonUtil.getId("m"));
        m.setDng_mem_email(dng_mem_email);
		m.setDng_mem_pwd(dng_mem_pwd);
		m.setDng_mem_name(dng_mem_name);
		m.setDng_mem_nickname(dng_mem_nickname);
		 
		md.insertMember(m);
		
		session.setAttribute("id", m.getDng_mem_id());
		session.setAttribute("email", m.getDng_mem_email());
		session.setAttribute("name", m.getDng_mem_name());
		session.setAttribute("nickname", m.getDng_mem_nickname());
		session.setAttribute("profileimg", m.getDng_mem_img());
		
		// 받은 초대 갯수 가져오기.
		DeongeoriInviteMessageDAO dnginvmsgdao = new DeongeoriInviteMessageDAO();
		int invmsgcnt = dnginvmsgdao.getDngInvMessageCount(m.getDng_mem_id()); 
		
		session.setAttribute("invmsgcnt", invmsgcnt);
		 		
		response.sendRedirect("./main.do");
		
		return null;
	}
}
