package com.bigdata2019.mysite.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bigdata2019.mysite.repository.GuestbookDao;
import com.bigdata2019.mysite.vo.GuestbookVo;
import com.bigdata2019.mysite.web.util.WebUtil;




public class GuestbookController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		WebUtil.forward(request, response, "/WEB-INF/views/guestbook/list.jsp");
		String action = request.getParameter("a");
		
		if("insert".equals(action)) {
			request.setCharacterEncoding("UTF-8");
			String name = request.getParameter("name");
			String password = request.getParameter("password");		
			String contents = request.getParameter("contents");		
		
			GuestbookVo vo = new GuestbookVo();
			vo.setName(name);
			vo.setPassword(password);
			vo.setContents(contents);
			
			new GuestbookDao().insert(vo);
			
			response.sendRedirect("/mysite2/");
		}else if("deleteform".equals(action)) {
			request.setCharacterEncoding("UTF-8");
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/guestbook/deleteform.jsp");
			rd.forward(request, response);
			
		}else if("delete".equals(action)){
			request.setCharacterEncoding("UTF-8");
			String no = request.getParameter("no");
	    	String password = request.getParameter("password");
	    	
	    	new GuestbookDao().delete(Long.parseLong(no), password);
	    	
	    	response.sendRedirect("/mysite2/");
		}else {
			request.setCharacterEncoding("UTF-8");
			GuestbookDao dao = new GuestbookDao();
			List<GuestbookVo> list = dao.findAll();
			
			request.setAttribute("list", list);
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/guestbook/index.jsp");
			rd.forward(request, response);
		}
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}