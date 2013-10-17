package dng.deongeori.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.gallery.dao.GalleryBean;
import com.gallery.dao.GalleryDAO;
import com.mtory.action.Action;
import com.mtory.action.ActionForward;

import dng.deongeori.DeongeoriBean;
import dng.deongeori.DeongeoriDAO;

public class DeongeoriList2Action implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		
		HttpSession session = request.getSession();
		String dng_id = (String)session.getAttribute("dng_id");
		String dng_gal_id = request.getParameter("dng_gal_id");
		int dng_gal_idint = Integer.parseInt(dng_gal_id);
		
		System.out.println(dng_id);
		System.out.println(dng_gal_idint);
		
		DeongeoriDAO dngdao = new DeongeoriDAO();
		DeongeoriBean dngbean = dngdao.selectDeongeori(dng_id);
		
		GalleryDAO gd=new GalleryDAO();
		List<GalleryBean> galleryList = gd.getGalleryList(dng_id);
		
		System.out.println("dgi =" + dng_gal_idint);
		
	  	GalleryBean gallery = gd.getcont(dng_gal_idint);
	  	
		session.setAttribute("dng_id", dngbean.getDng_id());
		request.setAttribute("dngbean", dngbean);
		request.setAttribute("gd", galleryList);
		request.setAttribute("gc", gallery);
		
		ActionForward forward = new ActionForward();
		forward.setRedirect(false);
		forward.setPath("./deongeori/deongeori_main_g.jsp?dng_id="+dng_id);
		
		return forward;
	}

}
