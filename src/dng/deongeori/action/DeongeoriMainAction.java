package dng.deongeori.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.board.action.BoardBean;
import com.board.action.BoardDAO;
import com.mtory.action.Action;
import com.mtory.action.ActionForward;

import dng.deongeori.DeongeoriBean;
import dng.deongeori.DeongeoriDAO;

public class DeongeoriMainAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		
		String dng_id = request.getParameter("dng_id");
				
		DeongeoriDAO dngdao = new DeongeoriDAO();
		DeongeoriBean dngbean = dngdao.selectDeongeori(dng_id);
		
		BoardDAO bd=new BoardDAO();
	  	List<BoardBean> boardList = bd.getBoardList();
	  	 
		request.setAttribute("dngbean", dngbean);
		request.setAttribute("bd", boardList);
		
		// 덩어리 아이디 세션화.		
		HttpSession session = request.getSession();
		session.setAttribute("dng_id", dng_id);
			
		ActionForward forward = new ActionForward();
		forward.setRedirect(false);
		forward.setPath("./deongeori/deongeori_main.jsp?dng_id="+dng_id);
		
		return forward;
	}

}
