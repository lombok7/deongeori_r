package dng.freeboard.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mtory.action.Action;
import com.mtory.action.ActionForward;

import dng.freeboard.FreeBoardBean;
import dng.freeboard.FreeBoardDAO;

public class BoardModifyAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		ActionForward forward=new ActionForward();
		boolean result = false;
		
		int num=Integer.parseInt(request.getParameter("BOARD_NUM"));
		
		FreeBoardDAO boarddao=new FreeBoardDAO();
		FreeBoardBean boarddata=new FreeBoardBean();
		
		boolean usercheck = boarddao.isBoardWriter(num, request.getParameter("BOARD_PASS"));
		if (usercheck==false) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out=response.getWriter();
			out.println("<script>");
			out.println("alert('수정할 권한이 없습니다.');");
			out.println("location.href='./BoardListAction.do'");
			out.println("</script>");
			out.close();
			return null;
		}
		
		try {
			boarddata.setBOARD_NUM(num);
			boarddata.setBOARD_SUBJECT(request.getParameter("BOARD_SUBJECT"));
			boarddata.setBOARD_CONTENT(request.getParameter("BOARD_CONTENT"));
			result = boarddao.boardModify(boarddata);
			if (result==false) {
				System.out.println("게시판 수정 실패");
				return null;
			}
			System.out.println("게시판 수정 완료");
			
			forward.setRedirect(true);
			forward.setPath("./BoardDetailAction.do?num=" + boarddata.getBOARD_NUM());
			return forward;
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}

}
