package com.board.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mtory.action.Action;
import com.mtory.action.ActionForward;
import com.board.action.BoardDAO;

public class BoardDelOkAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		// �������� �ѱ�ó���� ���� ���ڵ� ����
		response.setContentType("text/html; charset=UTF-8");
		// ��û�� �������� �ѱ�ó���� ���� ���ڵ� ����
		request.setCharacterEncoding("utf-8");
		// ��� ��Ʈ�� ��ü ��
		PrintWriter out = response.getWriter();
	
		// �������� �Ѿ�� �۹�ȣ�� �н����带 �޾Ƽ� ������ �����Ѵ�.
		int dng_board_id = Integer.parseInt(request.getParameter("dng_board_id"));
		
		BoardDAO bd = new BoardDAO();
		bd.deleteBoard(dng_board_id);	// BoardDAO���� �޼ҵ� �ۼ�
		// ���� �� board_list.jsp �������� �̵�
		/*response.sendRedirect("./deongeori.do");*/
		HttpSession session = request.getSession();
		String dng_id = (String)session.getAttribute("dng_id");
		response.sendRedirect("./deongeori_main.do?dng_id="+dng_id);

		return null;
	}

}
