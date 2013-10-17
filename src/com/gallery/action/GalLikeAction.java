package com.gallery.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.gallery.dao.GalleryBean;
import com.gallery.dao.GalleryDAO;
import com.mtory.action.Action;
import com.mtory.action.ActionForward;

public class GalLikeAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		response.setContentType("text/html;charset=UTF-8");
		// ���� ���̴� �ѱ��� ���ڵ��Ѵ�.
		HttpSession session = request.getSession();
		String dng_id = (String)session.getAttribute("dng_id");
		String show = "1";
		// ��� ��Ʈ�� ��ü�� ���Ѵ�.
		
		int num = Integer.parseInt(request.getParameter("num"));
		// get ������� �Ѿ�� no(�۹�ȣ)���������� ��ȯ�Ͽ� �����Ѵ�.
		
		GalleryDAO gd = new GalleryDAO(); // ��񿬵� ��ü ��
		// ��ȸ���� ������Ű�� �޼ҵ� ȣ��(BoardDAO�� �߰�)
		gd.dng_likecount(num); 
		
		// ��ȣ�� �������� ��� ������ �������� �޼ҵ� ȣ��(BoardDAO�� �߰�)
		GalleryBean gb = gd.getcont(num); 
	
		request.setAttribute("gc", gb);
		// ������ "board"Ű�� ���ڵ� ���� �����Ѵ�.
		
		ActionForward forward = new ActionForward();
		forward.setRedirect(true);
		forward.setPath("./deongeori_list3.do?dng_id="+dng_id+"&dng_gal_id="+num+"&show="+show);
		
		return forward; // ���� �ش� �������� �̵�
	

	}

}
