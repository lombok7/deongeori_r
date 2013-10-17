package dng.deongeori.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mtory.action.Action;
import com.mtory.action.ActionForward;

import dng.common.CommonUtil;

public class DeongeoriInvmsgOkAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		
		HttpSession session = request.getSession();
				
		String dng_id = request.getParameter("dng_id");
		String dng_inv_to = CommonUtil.emailToid(request.getParameter("dng_inv_to"));
		String dng_inv_from = (String)session.getAttribute("id");
		
		System.out.println(dng_id + " - " + dng_inv_to + " - " + dng_inv_from);
		
		
		
				
		
		return null;
	}

}
