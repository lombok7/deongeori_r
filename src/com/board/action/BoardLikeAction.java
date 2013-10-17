package com.board.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mtory.action.Action;
import com.mtory.action.ActionForward;

public class BoardLikeAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		response.setContentType("text/html;charset=UTF-8");
		// ���� ���̴� �ѱ��� ���ڵ��Ѵ�.
		PrintWriter out = response.getWriter();
		// ��� ��Ʈ�� ��ü�� ���Ѵ�.
		
		int num = Integer.parseInt(request.getParameter("num"));
		// get ������� �Ѿ�� no(�۹�ȣ)���������� ��ȯ�Ͽ� �����Ѵ�.
		
		BoardDAO bd = new BoardDAO(); // ��񿬵� ��ü ��
		// ��ȸ���� ������Ű�� �޼ҵ� ȣ��(BoardDAO�� �߰�)
		bd.dng_likecount(num); 
		
		// ��ȣ�� �������� ��� ������ �������� �޼ҵ� ȣ��(BoardDAO�� �߰�)
		BoardBean bb = bd.getcont(num); 
	
		request.setAttribute("bb", bb);
		// ������ "board"Ű�� ���ڵ� ���� �����Ѵ�.
		
		ActionForward forward = new ActionForward();
		forward.setRedirect(true);
		HttpSession session = request.getSession();
		String dng_id = (String)session.getAttribute("dng_id");
		forward.setPath("./deongeori_main.do?dng_id="+dng_id);
		
		return forward; // ���� �ش� �������� �̵�
	}

}
