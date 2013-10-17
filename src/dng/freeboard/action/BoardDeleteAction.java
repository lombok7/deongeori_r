package dng.freeboard.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mtory.action.Action;
import com.mtory.action.ActionForward;

import dng.freeboard.FreeBoardDAO;

public class BoardDeleteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		ActionForward forward = new ActionForward();
		request.setCharacterEncoding("UTF-8");
		
		boolean result=false;
		boolean usercheck=false;
		int num=Integer.parseInt(request.getParameter("num"));
		
		FreeBoardDAO boarddao=new FreeBoardDAO();
		
		System.out.println(num + ":" + request.getParameter("BOARD_PASS"));
		usercheck=boarddao.isBoardWriter(num, request.getParameter("BOARD_PASS"));
		
		if (usercheck==false) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out=response.getWriter();
			out.println("<script>");
			out.println("alert('������ ������ �����ϴ�.');");
			out.println("location.href='./BoardListAction.do';");
			out.println("</script>");
			out.close();
			return null;
		}
		
		result = boarddao.boardDelete(num);
		if (result==false) {
			System.out.println("�Խ��� ���� ����");
			return null;
		}
		
		System.out.println("�Խ��� ���� ����");
		forward.setRedirect(true);
		forward.setPath("./BoardListAction.do");
		return forward;
		}
	}

