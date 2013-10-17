package dng.deongeori.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.member.MemberDAO;
import com.mtory.action.Action;
import com.mtory.action.ActionForward;

import dng.deongeori.DeongeoriBean;
import dng.deongeori.DeongeoriInviteMessageDAO;

public class MakeMainAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		
		// 회원 아이디를 받아 메인 페이지에 보여줄 데이터를 셋팅하고 포워딩.
		// MemberLoginOkAction과 유사하나 세션은 만들지 않고, 셋팅만 합니다. 
		String dng_mem_id = request.getParameter("dng_mem_id");
		
		HttpSession session = request.getSession();
		MemberDAO md = new MemberDAO();
		
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
	}
}
