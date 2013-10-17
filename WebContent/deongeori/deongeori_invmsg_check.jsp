<%@ page import="dng.deongeori.DeongeoriDAO" %>
<%@ page import="dng.deongeori.DeongeoriInviteMessageDAO" %>
<%@ page import="dng.common.CommonUtil" %>
<%@ page import="com.member.*" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>


<%
	request.setCharacterEncoding("UTF-8");
	
	int re = 0;
			
	String dng_id = request.getParameter("dng_id");
	
	// 이메일을 받아와서 저장.
	String dng_mem_email = request.getParameter("dng_inv_to");
	
	System.out.println("dng_id = " + dng_id);
	
	// 닉네임(dng_mem_nickname)에 대응하는 회원 아이디(dng_mem_id)를 가져온다.
	String dng_inv_to = CommonUtil.emailToid(dng_mem_email);
	
	// re = 0  : case 1 ~ 3의 예외 상황에 해당하지 않으면, 초대 메세지 테이블 저장 성공.
	// re = -1 : 초대 메세지 테이블 저장 실패.
	// re = 1  : 해당 닉네임이 덩어리 서비스에 가입되어 있지 않은 경우.
	// re = 2  : 어느 누군가가 이미 해당 덩어리에 초대 메세지를 보낸 경우.
	// re = 3  : 이미 해당 덩어리에 가입 되어있는 회원인 경우.
	
	// re = 1
	// 해당 닉네임에 대응하는 멤버 아이디가 존재하지 않으면, 서비스 회원이 아니므로
	// 별도의 처리 없이 dng_inv_to 값이 null 이면 re = 1로 셋팅.
	
	boolean iscase2 = false;
	boolean iscase3 = false;
	
	if (dng_inv_to == null) {
		
		System.out.println("re1 = " + re);

		re = 1;
	
	}
			
	// re = 2
	// 회원 초대 메세지 테이블을 검색하여 초대 메세지를 받았는지 확인.
	DeongeoriInviteMessageDAO dnginvmsgdao = new DeongeoriInviteMessageDAO();
	
	iscase2 = dnginvmsgdao.isInviteDeongeori(dng_id, dng_inv_to);
		
	if (iscase2) {
		re = 2;
	}			
		
	// re = 3
	// 덩어리 가입 정보 테이블을 검색하여, 해당 덩어리의 멤버 인지 확인.
	DeongeoriDAO dngdao = new DeongeoriDAO();
	
	iscase3 = dngdao.isDeongeoriMember(dng_id, dng_inv_to);
	
	if (iscase3) {
		re = 3;
	}
			
	if (re == 0) {
		
		String dng_inv_from = (String)session.getAttribute("id");
		
		dnginvmsgdao = new DeongeoriInviteMessageDAO();
		boolean isInvmsgSendOk = dnginvmsgdao.sendInviteMessage(dng_id, dng_inv_from, dng_inv_to);
		
		if (!isInvmsgSendOk) {
			re = -1;	// 초대 실패.
		}
	}
	
	// 출력문이 아닌 return 값.
	out.println(re);
	
%>	