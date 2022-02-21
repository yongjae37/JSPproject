package com.javaex.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.javaex.dao.ProductDao;
import com.javaex.dao.ProductDaoImpl;
import com.javaex.util.WebUtil;
import com.javaex.vo.MemberVo;
import com.javaex.vo.ProductVo;

@WebServlet("/main")
public class MainServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		System.out.println("ProductServlet.doGet() 호출");
		
		MemberVo authUser = getAuthUser(request);
		List<ProductVo> productList = new ArrayList<ProductVo>();
		
		ProductDao dao = ProductDaoImpl.getInstance();
		
		productList = dao.getList();
		
		if(authUser == null) {
			request.setAttribute("list", productList);
			
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/main/index.jsp");
			rd.forward(request, response);	
			
		} else {
			
			int no = authUser.getMemNo();
			
			List<ProductVo> productList2 = new ArrayList<ProductVo>();
			ProductDao dao11 = ProductDaoImpl.getInstance();
			productList2 = dao11.recommend(no);
			
			request.setAttribute("list", productList);
			
			request.setAttribute("list2", productList2);
			
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/main/index.jsp");
			rd.forward(request, response);
			
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	protected MemberVo getAuthUser(HttpServletRequest request) {
		HttpSession session = request.getSession();
		MemberVo authUser = (MemberVo) session.getAttribute("authUser");

		return authUser;
	}

	

	
}