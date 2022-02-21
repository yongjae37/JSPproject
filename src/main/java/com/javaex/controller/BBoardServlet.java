package com.javaex.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.javaex.dao.BBoardDao;
import com.javaex.dao.BBoardDaoImpl;
import com.javaex.dao.ReviewDao;
import com.javaex.dao.ReviewDaoImpl;
import com.javaex.util.WebUtil;
import com.javaex.vo.BBoardVo;
import com.javaex.vo.MemberVo;
import com.javaex.vo.ReviewVo;

@WebServlet("/bboard")
public class BBoardServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final int LISTCOUNT = 10;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String actionName = request.getParameter("a");
		System.out.println("bboard:" + actionName);

		if ("list".equals(actionName)) {
			requestBboardList(request);
			WebUtil.forward(request, response, "/WEB-INF/views/bboard/list.jsp");
		} 
		else if ("read".equals(actionName)) {
			// 게시물 가져오기
			int no = Integer.parseInt(request.getParameter("bNo"));
			BBoardDao dao = new BBoardDaoImpl();
			BBoardVo bboardVo = dao.getBoard(no);
			
			
			BBoardVo bboardVo2 = dao.prev(no);
			System.out.println(bboardVo2.toString());
			request.setAttribute("bboardVo2", bboardVo2);
			
			BBoardVo bboardVo3 = dao.after(no);
			System.out.println(bboardVo3.toString());
			request.setAttribute("bboardVo3", bboardVo3);
			

			// 게시물 화면에 보내기
			request.setAttribute("bboardVo", bboardVo);
			WebUtil.forward(request, response, "/WEB-INF/views/bboard/read.jsp");
		} 
		
		else if ("writeform".equals(actionName)) {
			WebUtil.forward(request, response, "/WEB-INF/views/bboard/writeform.jsp");
		}
			

		else if ("write".equals(actionName)) {
			String title = request.getParameter("title");
			String content = request.getParameter("content");
			
			System.out.println("title : ["+title+"]");
			System.out.println("content : ["+content+"]");

			BBoardVo vo = new BBoardVo(title, content);
			BBoardDao dao = new BBoardDaoImpl();
			dao.insert(vo);
			
			WebUtil.redirect(request, response, "/mysiteB/bboard?a=list");

		} 
		
		else if ("delete".equals(actionName)) {
			int bNo = Integer.parseInt(request.getParameter("bNo"));

			BBoardDao dao = new BBoardDaoImpl();
			dao.delete(bNo);

			WebUtil.redirect(request, response, "/mysiteB/bboard?a=list");

		} 
		
//		else {
//			WebUtil.redirect(request, response, "/mysite/board?a=list");
//		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	// 로그인 되어 있는 정보를 가져온다.
	protected MemberVo getAuthUser(HttpServletRequest request) {
		HttpSession session = request.getSession();
		MemberVo authUser = (MemberVo) session.getAttribute("authUser");

		return authUser;
	}

	public void requestBboardList(HttpServletRequest request) {
		BBoardDao dao = BBoardDaoImpl.getInstance();
		List<BBoardVo> bboardList = new ArrayList<BBoardVo>();
		
		int pageNum = 1;
		int limit = LISTCOUNT;
		
		if(request.getParameter("pageNum") != null)
			pageNum = Integer.parseInt(request.getParameter("pageNum"));
		
		int total_record = dao.getListCount();
		
		bboardList = dao.getList(pageNum, limit);
		
		int total_page;
		
		if(total_record % limit == 0) {
			total_page = total_record / limit;
			Math.floor(total_page);
		}
		else {
			total_page = total_record / limit;
			Math.floor(total_page);
			total_page = total_page + 1;
		}
		
		request.setAttribute("bboardList", bboardList);
		request.setAttribute("bpageNum", pageNum);
		request.setAttribute("btotal_page", total_page);
		request.setAttribute("btotal_record", total_record);
		
	}
}
