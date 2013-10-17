package dng.freeboard.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mtory.action.Action;
import com.mtory.action.ActionForward;

import dng.freeboard.FreeBoardBean;
import dng.freeboard.FreeBoardDAO;

public class BoardModifyView implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		ActionForward forward = new ActionForward();
		request.setCharacterEncoding("UTF-8");
		
		FreeBoardDAO boarddao=new FreeBoardDAO();
		FreeBoardBean boarddata=new FreeBoardBean();
		
		int num=Integer.parseInt(request.getParameter("num"));
		boarddata=boarddao.getDetail(num);
		
		if (boarddata==null) {
			System.out.println("(수정)상세보기 실패");
			return null;
		}
		System.out.println("(수정)상세보기 성공");
		
		request.setAttribute("boarddata",  boarddata);
		forward.setRedirect(false);
		forward.setPath("./freeboard/board_modify.jsp");
		return forward;
	}
}
