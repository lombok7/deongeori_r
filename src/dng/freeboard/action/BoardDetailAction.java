package dng.freeboard.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mtory.action.Action;
import com.mtory.action.ActionForward;

import dng.freeboard.FreeBoardBean;
import dng.freeboard.FreeBoardDAO;

public class BoardDetailAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		
		FreeBoardDAO boarddao=new FreeBoardDAO();
		FreeBoardBean boarddata=new FreeBoardBean();
		
		int num=Integer.parseInt(request.getParameter("num"));
		boarddao.setReadCountUpdate(num);
		boarddata=boarddao.getDetail(num);
		
		if (boarddata==null) {
			System.out.println("�󼼺��� ����");
			return null;
		}
		System.out.println("�󼼺��� ����");
		
		request.setAttribute("boarddata", boarddata);
		ActionForward forward=new ActionForward();
		forward.setRedirect(false);
		forward.setPath("./freeboard/board_view.jsp");
		return forward;
	}
}
