package com.bigdata2019.mysite.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bigdata2019.mysite.repository.BoardDao;
import com.bigdata2019.mysite.vo.BoardVo;
import com.bigdata2019.mysite.web.util.WebUtil;


public class BoardController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		WebUtil.forward(request, response, "/WEB-INF/views/board/list.jsp");
		String action = request.getParameter("a");
		
		if("write".equals(action)) {
			request.setCharacterEncoding("UTF-8");
			String title = request.getParameter("title");		
			String contents = request.getParameter("contents");		
		
			BoardVo vo = new BoardVo();
			vo.setTitle(title);
			vo.setContents(contents);
			
			new BoardDao().insert(vo);
			
			response.sendRedirect("/mysite2/board");
		}else if("modify".equals(action)) {
			HttpSession session = request.getSession();
			if(session == null) {
				WebUtil.redirect(request, response, request.getContextPath());
				return;
			}
			
			BoardVo authUser = (BoardVo)session.getAttribute("authUser");
			if(authUser == null) {
				WebUtil.redirect(request, response, request.getContextPath());
				return;
			}
			Long no = authUser.getNo();
			BoardVo boardVo = new BoardDao().find(no);
			
			request.setAttribute("BoardVo", boardVo);
			WebUtil.forward(request, response, "/WEB-INF/views/board/modify.jsp");
			
			
		}else if("view".equals(action)) {
			request.setCharacterEncoding("UTF-8");
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/board/view.jsp");
			rd.forward(request, response);
			
		}else if("delete".equals(action)) {
			String no = request.getParameter("no");
	    	
	    	new BoardDao().delete(Long.parseLong(no));
	    	response.sendRedirect("/mysite2/board");
			
		}else {
			request.setCharacterEncoding("UTF-8");
			BoardDao dao = new BoardDao();
			List<BoardVo> list = dao.findAll();
			
			request.setAttribute("list", list);
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/board/list.jsp");
			rd.forward(request, response);
		}
		
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
