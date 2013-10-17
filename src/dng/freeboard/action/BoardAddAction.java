package dng.freeboard.action;

import java.io.File;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mtory.action.Action;
import com.mtory.action.ActionForward;
import com.oreilly.servlet.MultipartRequest;

import dng.common.CommonUtil;
import dng.freeboard.FreeBoardBean;
import dng.freeboard.FreeBoardDAO;

public class BoardAddAction implements Action {
	// ����� �����ϰ� ActionForward Ÿ������ ��ȯ�ϵ��� �Ѵ�.
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		FreeBoardDAO boarddao=new FreeBoardDAO();
		FreeBoardBean boarddata=new FreeBoardBean();
		// ������ ������ ��ȯ�ϱ� ���� ActionForward Ŭ������ ���Ѵ�.
		ActionForward forward=new ActionForward();
		
		// ������ ���ε�Ǵ� ����� �����Ѵ�. �� ������ ��� boardupload ����� ���ε� �� ���̴�.
		String saveFolder=request.getSession().getServletContext().getRealPath("upload/fbfiles") + "\\";
		// ���ε� �� ������ �ִ� ����� �����Ѵ�. ����Ʈ ������ �Է��ؾ� �ϸ�, ������ ������� 5MB�� �ǹ��Ѵ�.
		int fileSize = 5 * 1024 * 1024;
		// ���� ���� ��θ� �����Ѵ�. ��Ŭ������ ���� ��Ĺ ������ ���۵Ǹ�, ��Ŭ���� ������Ʈ�� ��ο� ��Ĺ ������ �ö󰡴� ��ΰ� �ٸ���.
		// �׷��� ������ ���� �������� �ʰ� Request ��ü�� getRealPath() �޼ҵ带 ����Ͽ� ������ �ö� �ִ� ��θ� ���Ͽ� boardupload
		// ����� ������ �� �ִ� ���̴�.
		
		boolean result = false;
		
		try {
			MultipartRequest multi = null;
			
			// ���� ���ε� ó���� �Ѵ�. ���ε��� ��ο� ������ �ִ� ũ��, ���ڵ� ��İ� �ߺ� �̸� ó���� ���� �Ķ���͸� ����Ѵ�.
			multi = new MultipartRequest(request, saveFolder, fileSize, "UTF-8");
		 	// BoardBean ��ü�� �� ��� �� �Է¹��� �������� �����Ѵ�. ���ε� ���ϸ��� ���� ���ε��� ������ ��ü ��ο��� 
			// ���� �̸��� �Ľ��Ͽ� �����Ѵ�.
			boarddata.setBOARD_NAME(multi.getParameter("BOARD_NAME"));
		 	boarddata.setBOARD_PASS(multi.getParameter("BOARD_PASS"));
		 	boarddata.setBOARD_SUBJECT(multi.getParameter("BOARD_SUBJECT"));
		 	boarddata.setBOARD_CONTENT(multi.getParameter("BOARD_CONTENT"));
		 	
		 	File UpFile=multi.getFile("BOARD_FILE");
			
			if(UpFile !=null){
				String fileName=UpFile.getName();//�������ϸ��� ����
				 
	       	 	//****Ȯ���� ���ϱ� ���� ****//*
				int index = UpFile.getName().lastIndexOf(".");
			    //File Ŭ������ getName() �޼���� �������ϸ��� �޾ƿ´�.
			    //lastIndexOf("����")��  StringŬ������ �޼���� �ش繮��
			    //�� ���ڿ� �� �� ���� ��Ʒ� ������ ��ġ��ȣ�� ��ȯ�Ѵ�.
			    String fileExtension = 
			        UpFile.getName().substring(index + 1);
			     //������ Ȯ���ڸ� ���Ѵ�.
			     //****Ȯ���� ���ϱ� �� ***//*
			     String refileName=CommonUtil.getId("f")+"." + fileExtension;//���ο� ���ϸ��� ����
			     //��� ����� ���ڵ� ��
			     File UpFile2=new File(saveFolder+refileName);
			     if(UpFile2.exists()){
			    	 UpFile2.delete();
				 }
			     UpFile.renameTo(new File(saveFolder+refileName));
			     //�ٲ� �������ϸ����� ����
			     boarddata.setBOARD_FILE(refileName);
			}
		 	
		 	// �� ��� ó���� ���� DAO�� boardInsert() �޼ҵ带 ȣ���Ѵ�. �� ��� �� �Է��� ������ ����Ǿ� �ִ�
		 	// boarddata ��ü�� ����Ͽ����Ƿ�, DAO������ �� ��ü�� �Ķ���ͷ� �޾Ƽ� ����ϰ� �ȴ�. �� �ڵ尡 �� Action Ŭ������
		 	// ���� �߿��� �κ��̶�� �� �� �ִ�.
		 	result = boarddao.boardInsert(boarddata);
		 	
		 	
		 	// �� ��Ͽ� ������ ��� null�� ��ȯ�Ѵ�.
		 	if (result == false) {
		 		System.out.println("�Խ��� ��� ����");
		 		return null;
		 	}
		 	System.out.println("�Խ��� ��� �Ϸ�");
		 	// �� ����� �Ϸ�Ǹ�, �� ����� �ܼ��� �����ֱ⸸ �� ���̹Ƿ�, Redirect ���θ� true�� �����Ѵ�.
		 	forward.setRedirect(true);
		 	// �� ��� �������� �������� ��θ� �����Ѵ�.
		 	forward.setPath("./BoardListAction.do");
		 	return forward;
		 	
		}catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}

