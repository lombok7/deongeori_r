package com.mtory.action;

import java.io.*;
//import com.bbs.action.*;
//import com.member.action.*;
//import com.board.action.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServlet;
import java.util.*;
public class MtoryFrontController extends HttpServlet {
	protected void service(HttpServletRequest request, HttpServletResponse response) 
		throws ServletException, IOException {
		String RequestURI=request.getRequestURI();
		String contextPath=request.getContextPath();
		String command=RequestURI.substring(contextPath.length());
		ActionForward forward=null;
		Action action=null;
		
		/* 경로 구하기. */
		// Server 를 더블 클릭하면 나오는 창에서 하단에 Serve modules without publishing 체크박스에 체크가 되어야 합니다.
		// 체크되어 있지 않으면 임시 폴더 비슷하게 만들어 서버가 가동되고, 이 폴더가 루트가 됩니다.
		// JSP/Servlet 웹 루트					getServletContext().getRealPath("/");
		// JSP/Servlet 특정 페이지 경로				getServletContext().getRealPath("경로");
		// JSP/Servlet 현재 페이지 경로				getServletContext().getRealPath(request.getRequestURI());
		// JAVA - 현재 클래스 경로					this.getClass().getResource("").getPath(); 
		// JAVA - 클래스 디렉토리 경로 (classes)		this.getClass().getResource("/").getPath();
		// JAVA - 패키지 경로						this.getClass().getResource("/com/aaa/bbb/ccc").getPath();
	
		String classesPath = this.getClass().getResource("/").getPath();
		String mappingFilename = "mtory.properties";
			
		Properties prop = new Properties();
		FileInputStream fis = 
			new FileInputStream(classesPath + mappingFilename);
		//프로퍼티화일로드, 자바 경로 구분은 \\ or /
		prop.load(fis);
		fis.close();
				
		String value = prop.getProperty(command);
        
		if(value.substring(0,7).equals("execute")){
			try{
				StringTokenizer st = new StringTokenizer(value,"|");
				String url_1 = st.nextToken();
				String url_2 = st.nextToken();
				Class url = Class.forName(url_2);//문자열로 읽어온 클래스 파일명을 객체화 한다.
				//객체화한 클래스는 Object형이다.그러므로 강제 다운캐스팅해야 한다. 
				action  = (Action)url.newInstance();
				try {
					forward=action.execute(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}catch(ClassNotFoundException ex){
				ex.printStackTrace();
			}catch(InstantiationException ex){
				ex.printStackTrace();
			}catch(IllegalAccessException ex){
				ex.printStackTrace();
			}
		}else{
			forward=new ActionForward();
			forward.setRedirect(false);
			forward.setPath(value);
		}

		if(forward != null){
			if(forward.isRedirect()){
				response.sendRedirect(forward.getPath());
			}else{
				RequestDispatcher dispatcher = request.getRequestDispatcher(forward.getPath());
				dispatcher.forward(request, response);
			}
		}
	} 	    
}
