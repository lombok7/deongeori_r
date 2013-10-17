<%@page import="dng.common.CommonUtil"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="com.board.action.BoardDAO"%>
<%@ page import="com.board.action.BoardBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("UTF-8");
	
	StringBuffer sb = new StringBuffer();
	
	String dng_id = request.getParameter("dng_id");
	
	BoardDAO bd = new BoardDAO();
	
	List<BoardBean> boardList = new ArrayList<BoardBean>();
	boardList = bd.getBoardList(dng_id);
		
	// sb.append("<?xml version='1.0' encoding='UTF-8'?>\n");
	sb.append("<board>\n");

	if ((boardList != null) && (boardList.size() > 0)) {
		
		for(int i=0; i<boardList.size(); i++) {

			BoardBean bb = boardList.get(i);
			
			sb.append("<item>\n");
			sb.append("<id>" + bb.getDng_board_id() + "</id>\n");
			sb.append("<writer>" + CommonUtil.idToNickname(bb.getDng_board_writer()) + "</writer>\n");
			sb.append("<writerimg>" + bd.getWriterImg(bb.getDng_board_writer()) + "</writerimg>\n");
			sb.append("<content>" + bb.getDng_board_content() + "</content>\n");
			sb.append("<pwd>" + bb.getDng_board_pwd() + "</pwd>\n");
			sb.append("<likecount>" + bb.getDng_board_likecount() + "</likecount>\n");
			sb.append("<regdate>" + bb.getDng_board_regdate().substring(0,10) + "</regdate>\n");
			sb.append("</item>\n");
		}
	}
	
	sb.append("</board>");		
	
	out.println(sb.toString());
%>