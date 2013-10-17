<%@page import="com.gallery.dao.GalleryBean"%>
<%@page import="com.gallery.dao.GalleryDAO"%>
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
	
	GalleryDAO gd = new GalleryDAO();
	
	List<GalleryBean> GalleryList = new ArrayList<GalleryBean>();
	GalleryList = gd.getRecentGalleryList(dng_id);
		
	// sb.append("<?xml version='1.0' encoding='UTF-8'?>\n");
	sb.append("<gallery>\n");

	if ((GalleryList != null) && (GalleryList.size() > 0)) {
		
		for(int i=0; i<GalleryList.size(); i++) {

			GalleryBean gb = GalleryList.get(i);
			
			sb.append("<item>\n");
			sb.append("<id>" + gb.getDng_gal_id() + "</id>\n");
			sb.append("<writer>" + CommonUtil.idToNickname(gb.getDng_gal_writer()) + "</writer>\n");
			sb.append("<content>" + gb.getDng_gal_content() + "</content>\n");
			sb.append("<pwd>" + gb.getDng_gal_pwd() + "</pwd>\n");
			sb.append("<likecount>" + gb.getDng_gal_likecount() + "</likecount>\n");
			sb.append("<img>" + gb.getDng_gal_img() + "</img>\n");
			sb.append("<regdate>" + gb.getDng_gal_regdate().substring(0,10) + "</regdate>\n");
			sb.append("</item>\n");
		}
	}
	
	sb.append("</gallery>");		
	
	out.println(sb.toString());
%>