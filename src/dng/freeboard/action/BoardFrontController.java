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

// doProcess �޼ҵ带 �����Ͽ� ��û�� GET ������� ���۵Ǿ� ���� POST ������� ���۵Ǿ� ���� ����
// �޼ҵ忡�� ��û�� ó���� �� �ֵ��� �Ͽ���.�� �޼ҵ�� doGet�̳� goPost �޼ҵ忡�� ȣ���ϰ� �ִ�.
// ��, ��û�� post ������� ���۵Ǿ� ���� get ������� ���۵Ǿ� ���� ���������� ȣ��ȴٴ� �ǹ��̴�.
	protected void doProcess(HttpServletRequest request, 
			HttpServletResponse response)
		throws ServletException, IOException {
		// �� �κ��� ��Ʈ�ѷ��� ���۵Ǿ� �� ��û�� �ľ��ϴ� �κ��̴�.Ȯ���ڰ� .bo�� ���۵Ǿ� ���� ��û��
		// ��� BoardFrontController�� ���۵Ǿ� �� ���̱� ������ �۾��� ��û, �� ���� ���� ��û, �� ����
		// ��û ��û �� �Խ��ǿ� ���� ��� ��û�� �ش� ��Ʈ�ѷ��� ���۵ȴ�. ���� ��û�� ���۵Ǿ��� �� �� ��û��
		// ���� ��û �� � ��û�� ���۵Ǿ����� �ľ��� �ʿ䰡 �ִ�.
		// �켱, String RequestURI=request.getRequestURI(); �κп����� ��û�� ��ü URL ��
		// ��Ʈ ��ȣ �������� ������ ���ڿ������� ��ȯ�ȴ�. ��, ���� ��ü URL�� http://localhost:8088
		// /Model2-Board/BoardList.bo��� /Model2-Board/BoardList.bo�� ��ȯ�ȴ�.
		// String contextPath=request.getContextPath(); �κп����� ���ؽ�Ʈ ��ΰ� ��ȯ�ȴ�.
		// ������ ���õ� ��ü URL�߿��� /Model2-Board�� ��ȯ�ȴ�.
		// String command=RequestURI.substring(contextPath.length()); �κ��� ����������
		// ��û ���Ḧ ��ȯ�޴� �κ��̴�. ��ü URI ���ڿ�(/Model2-Board/BoardList.bo)���� ���ؽ�Ʈ
		// ��� ���ڿ��� ��� ���������� ����Ǵ� ���ڿ��� /BoardList.bo�� �ȴ�.
		String RequestURI=request.getRequestURI();
		String contextPath=request.getContextPath();
		
		String command=RequestURI.substring(contextPath.length());
		// ���������� �� 2���� ����Ͻ� ��û��ó���� �� �� �������� �������ϴ� �۾��� �����ϰ� �Ǵµ� ������ ����
		// (������ �� �� ������ URL, ������ ���)�� �����ϴ� Ŭ������ ActionForward �̴�.
		// �� ActionForward ���� ���� �ʱ�ȭ �� �κ��̴�.
		ActionForward forward=null;
		// �� ��û���� ���������� ����Ͻ� ������ ó���ϴ� �κ��� ������ Action Ŭ�������ε� �� Action Ŭ����
		// ���� Action  �������̽��� �����ϰ� ó���ϸ鼭 ����Ͻ� ������ �����ϴ� Ŭ�������� �޼ҵ带 
		// �԰�ȭ ���־���. ������ Action ��ü�� �����ؼ� ���۷��� �� ���� �������� �̿��ؼ� ������ Action
		// Ŭ���� Ÿ�Ժ��� ������ �����ϴ� ���� �ƴϰ�, Action �������̽� Ÿ������ �����ϱ� ���� Action
		// �������̽� Ÿ���� ������ �ʱ�ȭ�Ͽ���.
		Action action=null;
		// ��ܿ��� ��� �� ���� command�� �ش��ϴ� Action ��ü�� �����Ͽ� ����Ͻ� ������ ������ �� (����Ͻ�
		// ������ execute �޼ҵ带 ȣ���Ͽ� �����ϰ� �ִ�.) ���� ������ ������ ActionForward ��ü�� ��ȯ
		// �޴� �κ��̴�.
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
				// ActionForward�� ���۵Ǿ� �� ������ ��Ŀ� ���� isRedirect() ���� flase�̸�
				// dispatch �������, isRedirect() ���� true�̸� redirect ������� �� ��������
				// ������ URL�� ������ ó���ϴ� �κ��̴�.
		}if (forward != null) {
			if (forward.isRedirect()) {
				response.sendRedirect(forward.getPath());
		}else{
			RequestDispatcher dispatcher=request.getRequestDispatcher(forward.getPath());
			dispatcher.forward(request, response);
				}
			}
		}
	
	
		// Ŭ���̾�Ʈ���� ��û�� GET ������� ���۵Ǿ� ���� POST ������� ���۵Ǿ� ���� �����ϰ� doProcess
		// �޼ҵ带 ȣ���ϴ� �κ��̴�. �̷��� �����ϰ� doProcess �޼ҵ带 ȣ�������ν� ������ �޼ҵ忡��
		// ������ ������ �ߺ��ǰ� ������ �ʿ� ���� doProcess �޼ҵ� �ϳ����� �� ��û�� ���������� ó���� �� �ִ�.
		protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			doProcess(request, response);
		}
		
		protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			doProcess(request, response);
		}
	}


