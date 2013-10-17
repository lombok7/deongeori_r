package com.gallery.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.gallery.dao.GalleryBean;
import com.gallery.dao.GalleryDAO;
import com.mtory.action.Action;
import com.mtory.action.ActionForward;

public class GalListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		HttpSession session = request.getSession();
		String dng_id = (String)session.getAttribute("dng_id");
		
		GalleryDAO gd=new GalleryDAO(); //DB 연동 객체 생성
		List<GalleryBean> galleryList = gd.getGalleryList(dng_id);		
		
		request.setAttribute("gd", galleryList);
		
		ActionForward forward = new ActionForward();
		forward.setRedirect(false);
		forward.setPath("./deongeori/deongeori_main_g.jsp?dng_id="+dng_id);
		
		return forward;
	}

}
