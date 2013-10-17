package dng.freeboard.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mtory.action.Action;
import com.mtory.action.ActionForward;


public class BoardFrontController extends javax.servlet.http.HttpServlet
	implements javax.servlet.Servlet {

// doProcess 메소드를 구현하여 요청이 GET 방식으로 전송되어 오든 POST 방식으로 전송되어 오든 같은
// 메소드에서 요청을 처리할 수 있도록 하였다.이 메소드는 doGet이나 goPost 메소드에서 호출하고 있다.
// 즉, 요청이 post 방식으로 전송되어 오나 get 방식으로 전송되어 오나 공통적으로 호출된다는 의미이다.
	protected void doProcess(HttpServletRequest request, 
			HttpServletResponse response)
		throws ServletException, IOException {
		// 이 부분은 컨트롤러로 전송되어 온 요청을 파악하는 부분이다.확장자가 .bo로 전송되어 오는 요청은
		// 모두 BoardFrontController로 전송되어 올 것이기 때문에 글쓰기 요청, 글 내용 보기 요청, 글 수정
		// 요청 요청 등 게시판에 관한 모든 요청이 해당 컨트롤러로 전송된다. 따라서 요청이 전송되었을 때 이 요청이
		// 상위 요청 중 어떤 요청이 전송되었는지 파악할 필요가 있다.
		// 우선, String RequestURI=request.getRequestURI(); 부분에서는 요청된 전체 URL 중
		// 포트 번호 다음부터 마지막 문자열까지가 반환된다. 즉, 만약 전체 URL이 http://localhost:8088
		// /Model2-Board/BoardList.bo라면 /Model2-Board/BoardList.bo가 반환된다.
		// String contextPath=request.getContextPath(); 부분에서는 컨텍스트 경로가 반환된다.
		// 상위에 제시된 전체 URL중에는 /Model2-Board가 반환된다.
		// String command=RequestURI.substring(contextPath.length()); 부분은 마지막으로
		// 요청 종료를 반환받는 부분이다. 전체 URI 문자열(/Model2-Board/BoardList.bo)에서 컨텍스트
		// 경로 문자열의 경우 최종적으로 추출되는 문자열은 /BoardList.bo가 된다.
		String RequestURI=request.getRequestURI();
		String contextPath=request.getContextPath();
		
		String command=RequestURI.substring(contextPath.length());
		// 최종적으로 모델 2에서 비즈니스 요청을처리한 후 뷰 페이지로 포워딩하는 작업을 진행하게 되는데 포워딩 정보
		// (포워딩 될 뷰 페이지 URL, 포워딩 방식)를 저장하는 클래스가 ActionForward 이다.
		// 이 ActionForward 변수 값을 초기화 한 부분이다.
		ActionForward forward=null;
		// 각 요청에서 실질적으로 비즈니스 로직을 처리하는 부분이 각각의 Action 클래스들인데 이 Action 클래스
		// 들은 Action  인터페이스를 구현하게 처리하면서 비즈니스 로직을 수행하는 클래스들의 메소드를 
		// 규격화 해주었다. 각각의 Action 객체를 생성해서 레퍼런스 할 때도 다형성을 이용해서 각각의 Action
		// 클래스 타입별로 변수를 선언하는 것이 아니고, Action 인터페이스 타입으로 참조하기 위해 Action
		// 인터페이스 타입의 변수를 초기화하였다.
		Action action=null;
		// 상단에서 얻어 온 각각 command에 해당하는 Action 객체를 실행하여 비즈니스 로직을 실행한 후 (비즈니스
		// 로직은 execute 메소드를 호출하여 실행하고 있다.) 리턴 값으로 각각의 ActionForward 객체를 반환
		// 받는 부분이다.
		if (command.equals("/BoardWrite.bo")){
			forward=new ActionForward();
			forward.setRedirect(false);
			forward.setPath("./board/qna_board_write.jsp");
		}else if (command.equals("/BoardReplyAction.bo")){
			action = new BoardReplyAction();
			try {
				forward = action.execute(request, response);
			}catch(Exception e) {
				e.printStackTrace();
			}
		}else if (command.equals("/BoardDelete.bo")) {
			forward=new ActionForward();
			forward.setRedirect(false);
			forward.setPath("./board/qna_board_delete.jsp");
		}else if (command.equals("/BoardModify.bo")){
			action = new BoardModifyView();
			try {
				forward=action.execute(request, response);
			}catch(Exception e) {
				e.printStackTrace();
			}
		}else if (command.equals("/BoardAddAction.bo")) {
			action = new BoardAddAction();
			try {
				forward=action.execute(request, response);
			}catch(Exception e) {
				e.printStackTrace();
			}
		}else if (command.equals("/BoardReplyView.bo")) {
			action = new BoardReplyView();
			try {
				forward=action.execute(request, response);
			}catch(Exception e) {
				e.printStackTrace();
			}
		}else if (command.equals("/BoardModifyAction.bo")){
			action=new BoardModifyAction();
			try {
				forward=action.execute(request, response);
			}catch(Exception e) {
				e.printStackTrace();
			}
		}else if (command.equals("/BoardDeleteAction.bo")){
			action = new BoardDeleteAction();
			try {
				forward=action.execute(request, response);
			}catch(Exception e){
				e.printStackTrace();
			}
		}else if (command.equals("/BoardList.bo")){
				action = new BoardListAction();
				try {
					forward=action.execute(request, response);
				}catch(Exception e) {
					e.printStackTrace();
				}
			}else if (command.equals("/BoardDetailAction.bo")) {
				action = new BoardDetailAction();
				try {
					forward=action.execute(request, response);
				}catch(Exception e) {
					e.printStackTrace();
				}
				// ActionForward에 전송되어 온 포워딩 방식에 따라 isRedirect() 값이 flase이면
				// dispatch 방식으로, isRedirect() 값이 true이면 redirect 방식으로 뷰 페이지로
				// 지정된 URL로 포워딩 처리하는 부분이다.
		}if (forward != null) {
			if (forward.isRedirect()) {
				response.sendRedirect(forward.getPath());
		}else{
			RequestDispatcher dispatcher=request.getRequestDispatcher(forward.getPath());
			dispatcher.forward(request, response);
				}
			}
		}
	
	
		// 클라이언트에서 요청이 GET 방식으로 전송되어 오든 POST 방식으로 전송되어 오든 동일하게 doProcess
		// 메소드를 호출하는 부분이다. 이렇게 동일하게 doProcess 메소드를 호출함으로써 각각의 메소드에서
		// 동일한 로직을 중복되게 실행할 필요 없이 doProcess 메소드 하나에서 두 요청을 공통적으로 처리할 수 있다.
		protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			doProcess(request, response);
		}
		
		protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			doProcess(request, response);
		}
	}


