package com.board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mtory.action.Action;
import com.mtory.action.ActionForward;

public class BoardDelAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// get 방식으로 넘어온 글번호를 파라미터로 받아서 id에 저장
		int num = Integer.parseInt(request.getParameter("num"));
		
		BoardDAO  bd = new BoardDAO();
		BoardBean board =bd.getcont(num);
		
		// b 키값에 디비 내용을 저장한다.
		request.setAttribute("b", board);
		// ActionForward 클래스의 객체를 생성한다.
		ActionForward forward = new ActionForward();
		// 처리결과를 사용자에게 보여줄 view 페이지를 포워딩한다. 
		forward.setRedirect(false);		
		forward.setPath("./board/board_del.jsp");
		
		return forward;  // 실제 포워딩 실행
	}

}
