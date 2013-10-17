package dng.deongeori.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.board.action.BoardBean;
import com.board.action.BoardDAO;
import com.mtory.action.Action;
import com.mtory.action.ActionForward;

import dng.deongeori.DeongeoriInviteMessageBean;
import dng.deongeori.DeongeoriInviteMessageDAO;

public class DeongeoriInvmsgListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		
		String dng_mem_id = request.getParameter("dng_mem_id");
		
		System.out.println("invmsg : " + dng_mem_id);
		
		DeongeoriInviteMessageDAO dnginvmsgdao = new DeongeoriInviteMessageDAO();
		
		List<DeongeoriInviteMessageBean> dnginvmsglist = dnginvmsgdao.getDngInvMessageList(dng_mem_id);
		
		request.setAttribute("dnginvmsglist", dnginvmsglist);
		
		ActionForward forward = new ActionForward();
		forward.setRedirect(false);
		forward.setPath("./deongeori/deongeori_invmsg_list.jsp");
		
		return forward;
	}
}
