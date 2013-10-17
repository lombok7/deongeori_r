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

public class DeongeoriListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		
		String dng_id = request.getParameter("dng_id");
		
		DeongeoriDAO dngdao = new DeongeoriDAO();
		DeongeoriBean dngbean = dngdao.selectDeongeori(dng_id);
		
		GalleryDAO gd=new GalleryDAO();
		
		List<GalleryBean> galleryList = gd.getGalleryList(dng_id);
		GalleryBean gallery = gd.getGalMostLiked(dng_id);
		
		HttpSession session = request.getSession();
		session.setAttribute("dng_id", dngbean.getDng_id());
		
		request.setAttribute("dngbean", dngbean);
		request.setAttribute("gd", galleryList);
		/*if((galleryList != null)&&(galleryList.size()>0)){
			request.setAttribute("gc", galleryList.get(0));
			}*/
		if(gallery != null){
		request.setAttribute("gc", gallery);
		request.setAttribute("gm", gallery);
		}
		
		ActionForward forward = new ActionForward();
		forward.setRedirect(false);
		forward.setPath("./deongeori/deongeori_main_g2.jsp?dng_id="+dng_id);
		
		return forward;
	}

}
