package com.board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mtory.action.Action;
import com.mtory.action.ActionForward;

public class BoardDelAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// get ������� �Ѿ�� �۹�ȣ�� �Ķ���ͷ� �޾Ƽ� id�� ����
		int num = Integer.parseInt(request.getParameter("num"));
		
		BoardDAO  bd = new BoardDAO();
		BoardBean board =bd.getcont(num);
		
		// b Ű���� ��� ������ �����Ѵ�.
		request.setAttribute("b", board);
		// ActionForward Ŭ������ ��ü�� �����Ѵ�.
		ActionForward forward = new ActionForward();
		// ó������� ����ڿ��� ������ view �������� �������Ѵ�. 
		forward.setRedirect(false);		
		forward.setPath("./board/board_del.jsp");
		
		return forward;  // ���� ������ ����
	}

}
