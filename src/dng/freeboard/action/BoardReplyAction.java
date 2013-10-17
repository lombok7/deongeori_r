package dng.freeboard.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mtory.action.Action;
import com.mtory.action.ActionForward;

import dng.freeboard.FreeBoardBean;
import dng.freeboard.FreeBoardDAO;

public class BoardReplyAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		ActionForward forward = new ActionForward();
		
		FreeBoardDAO boarddao = new FreeBoardDAO();
		FreeBoardBean boarddata=new FreeBoardBean();
		int result = 0;
		
		boarddata.setBOARD_NUM(Integer.parseInt(request.getParameter("BOARD_NUM").trim()));
		boarddata.setBOARD_NAME(request.getParameter("BOARD_NAME"));
		boarddata.setBOARD_PASS(request.getParameter("BOARD_PASS"));
		boarddata.setBOARD_SUBJECT(request.getParameter("BOARD_SUBJECT"));
		boarddata.setBOARD_CONTENT(request.getParameter("BOARD_CONTENT"));
		
		System.out.println(request.getParameter("BOARD_CONTENT"));
		
		boarddata.setBOARD_RE_REF(Integer.parseInt(request.getParameter("BOARD_RE_REF").trim()));
		boarddata.setBOARD_RE_SEQ(Integer.parseInt(request.getParameter("BOARD_RE_SEQ").trim()));
		
		result=boarddao.boardReply(boarddata);
		if (result==0){
			System.out.println("답변 실패");
			return null;
		}
		System.out.println("답변 완료");
		forward.setRedirect(true);
		forward.setPath("./BoardDetailAction.do?num=" + result);
		return forward;
	}
}
