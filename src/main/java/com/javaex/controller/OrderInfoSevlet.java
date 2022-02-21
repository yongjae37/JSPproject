package com.javaex.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import org.apache.commons.fileupload.*;
import org.apache.commons.fileupload.disk.*;
import org.apache.commons.fileupload.servlet.*;
import org.apache.commons.io.output.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.javaex.dao.OrderHasProductDao;
import com.javaex.dao.OrderHasProductDaoImpl;
import com.javaex.dao.OrderInfoDao;
import com.javaex.dao.OrderInfoDaoImpl;
import com.javaex.dao.PointDao;
import com.javaex.dao.PointDaoImpl;
import com.javaex.dao.ProductDao;
import com.javaex.dao.ProductDaoImpl;
import com.javaex.dao.ShippingDao;
import com.javaex.dao.ShippingDaoImpl;
import com.javaex.util.WebUtil;
import com.javaex.vo.*;

@WebServlet("/orderInfo")
public class OrderInfoSevlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	static final int LISTCOUNT = 5;
	private static final String ATTACHES_DIR = "/usr/local/tomcat8.5/webapps/mysiteB/upload";
	private static final int LIMIT_SIZE_BYTES = 5 * 1024 * 1024;
	private static final String CHARSET = "utf-8";

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String actionName = request.getParameter("a");
		System.out.println("board:" + actionName);
		String pageNum = request.getParameter("pageNum");
		System.out.println("pageNum:" + pageNum);
		
		
		if ("list".equals(actionName)) {

			// 리스트 화면에 보내기
			requestOrderInfoList(request);

			WebUtil.forward(request, response, "/WEB-INF/views/member/orderlistform.jsp");
		} else if ("read".equals(actionName)) {
			// 게시물 가져오기

			int orderNo = Integer.parseInt(request.getParameter("orderNo"));
			OrderInfoDao dao = new OrderInfoDaoImpl();
			List<OrderInfoVo> detailList =  dao.getDetail(orderNo);

			// 게시물 화면에 보내기
			request.setAttribute("orderInfoVo", detailList);
			WebUtil.forward(request, response, "/WEB-INF/views/board/read.jsp");
		} else if ("delete".equals(actionName)) {
			// 게시물 가져오기
			int orderNo = Integer.parseInt(request.getParameter("orderNo"));
			OrderInfoDao dao = new OrderInfoDaoImpl();
			dao.delete(orderNo);

			
			WebUtil.forward(request, response, "/WEB-INF/views/board/modifyform.jsp");
		} else if ("insert".equals(actionName)) { //단품 결제
				int pnt = 0;
				if(request.getParameter("use_pnt") == "") {
					pnt = 0;
				}else {
					pnt = Integer.parseInt(request.getParameter("use_pnt"));
				}
			   
				int proNo = Integer.parseInt(request.getParameter("proNo"));
				int total = Integer.parseInt(request.getParameter("total"));
				int memNo = Integer.parseInt(request.getParameter("memNo"));
				int amount = Integer.parseInt(request.getParameter("amount"));
				ProductDao dao3 = new ProductDaoImpl();
				if(dao3.stockCheck(proNo) < amount) {
					response.setContentType("text/html; charset=UTF-8");
					PrintWriter writer = response.getWriter();
					writer.println("<script>alert('재고가 부족합니다! 현재 재고는" + dao3.stockCheck(proNo) + "개 입니다');window.close();history.back();</script>");
					writer.close();
					return;
				}
				
				
				String addCheck = request.getParameter("addCheck");
				System.out.println(request.getParameter("memPostc1") + "우편번호값을 받아와 봅시다");
				String name = null;
				String postc  = null;
				String doro = null;
				String jibun = null;
				String add = null;
				String phone = null;
				String ps = null;
				if(addCheck.equals("0")) {
					System.out.println(addCheck + "에드체크 일로 들어옴");
					name = request.getParameter("memName");
					postc = request.getParameter("memPostc1");
					doro = request.getParameter("memDoro");
					jibun = request.getParameter("memJibun");
					add = request.getParameter("memAdd");
					phone = request.getParameter("memPhone");
					ps = request.getParameter("memPs");
				}else if(addCheck.equals("1")) {
					name = request.getParameter("name");
					postc = request.getParameter("postc");
					doro = request.getParameter("doro");
					jibun = request.getParameter("jibun");
					add = request.getParameter("add");
					phone = request.getParameter("phone");
					ps = request.getParameter("ps");
				}
				if(pnt > 0) {
					PointDaoImpl pDao = new PointDaoImpl();
					pDao.insertProcess(memNo, pnt, "포인트로 결제");
					System.out.println("시발시발시발");
				}
				OrderInfoVo vo = new OrderInfoVo(total, memNo);
				OrderInfoDao dao = new OrderInfoDaoImpl();
				OrderHasProductDao dao2 = new OrderHasProductDaoImpl();
				dao.insert(vo);
				int orderNo = dao.getOrderNo(memNo);
				dao2.insert(orderNo, proNo, amount);
				ShippingVo shippingVo = new ShippingVo(orderNo, name, postc, doro, jibun, add, phone, ps);
				ShippingDao shippingDao = new ShippingDaoImpl();
				shippingDao.insert(shippingVo);
				request.setAttribute("orderNo", orderNo);
				System.out.println(orderNo + "오더넘버");
				WebUtil.forward(request, response, "/WEB-INF/views/order/orderComplete.jsp");
		} else if ("single".equals(actionName)) {
			// 게시물 가져오기
			
			int proNo = Integer.parseInt(request.getParameter("proNo"));
			int amount = Integer.parseInt(request.getParameter("amount"));
			MemberVo authUser = getAuthUser(request);
			int memNo = authUser.getMemNo();
			PointDao dao2 = new PointDaoImpl();
			ProductDao dao = new ProductDaoImpl();
			if(dao.stockCheck(proNo) > amount) {
				ProductVo vo = dao.getProduct(proNo);
				int point = dao2.check(memNo);
			    request.setAttribute("proVo", vo);
			    request.setAttribute("amount", amount);
			    request.setAttribute("price", vo.getProPrice() * amount);
			    request.setAttribute("total", vo.getProPrice() * amount + 2000);
			    request.setAttribute("point", point);
				WebUtil.forward(request, response, "/WEB-INF/views/order/orderform.jsp");
			}else {
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter writer = response.getWriter();
				writer.println("<script>alert('재고가 부족합니다! 현재 재고는" + dao.stockCheck(proNo) + "개 입니다');window.close();history.back();</script>");
				writer.close();
				return;
				
			}

		} else if("cartform".equals(actionName)) {

			// 리스트 화면에 보내기
			//requestOrderInfoList(request);

			WebUtil.forward(request, response, "/WEB-INF/views/member/cartform.jsp");
		} else if("orderform".equals(actionName)) {

			// 리스트 화면에 보내기
			//requestOrderInfoList(request);
			WebUtil.forward(request, response, "/WEB-INF/views/order/orderform.jsp");
			
		} else if("managelist".equals(actionName)) {
			// 미현이가 한 부분!! 기본 관리자 페이지에서 주문 내역 관리로 넘어갈 때!!!!
			OrderInfoDao dao = new OrderInfoDaoImpl();
			ArrayList<OrderInfoVo> list = dao.getList();
			System.out.println("리스트 가져옴");
			// 리스트 화면에 보내기
			request.setAttribute("list", list);

			WebUtil.forward(request, response, "/WEB-INF/views/manager/orderinfomanage.jsp");
		} else if("before".equals(actionName)) {
			// 미현이가 한 부분!! 기본 관리자 페이지에서 주문 내역 관리로 넘어갈 때!!!!
			OrderInfoDao dao = new OrderInfoDaoImpl();
			ArrayList<OrderInfoVo> list = dao.getList1();
			System.out.println("리스트 가져옴");
			System.out.println(list.toString());

			// 리스트 화면에 보내기
			request.setAttribute("list", list);

			WebUtil.forward(request, response, "/WEB-INF/views/manager/orderinfomanage2.jsp");
		} else if("after".equals(actionName)) {
			// 미현이가 한 부분!! 기본 관리자 페이지에서 주문 내역 관리로 넘어갈 때!!!!
			OrderInfoDao dao = new OrderInfoDaoImpl();
			ArrayList<OrderInfoVo> list = dao.getList2();
			System.out.println("리스트 가져옴");
			System.out.println(list.toString());

			// 리스트 화면에 보내기
			request.setAttribute("list", list);

			WebUtil.forward(request, response, "/WEB-INF/views/manager/orderinfomanage3.jsp");
		} else if("change".equals(actionName)) {
			// 이건 위에랑 같은 페이지인데 미배송 -> 배송 완료로 바꾸는 역할!!
			System.out.println("돌아감");
			int orderNo = Integer.parseInt(request.getParameter("orderNo"));
			int memNo = Integer.parseInt(request.getParameter("memNo"));
			
			OrderInfoDao dao = new OrderInfoDaoImpl();
			
			System.out.println("member번호: " + memNo);
			
			System.out.println("주문 번호: " + orderNo);
			dao.change(orderNo);
			System.out.println("바꿈");

			if(dao.change(orderNo)==1) {
				PointDao ppdao = new PointDaoImpl();
				ppdao.orderPoint(orderNo, memNo);	
			}
			
			ArrayList<OrderInfoVo> list = dao.getList();
			System.out.println("리스트 가져옴");
			System.out.println(list.toString());

			// 리스트 화면에 보내기
			request.setAttribute("list", list);
			

			WebUtil.forward(request, response, "/WEB-INF/views/manager/orderinfomanage.jsp");
		}else if("specific".equals(actionName)) {
			// 이건 위에랑 같은 페이지인데 미배송 -> 배송 완료로 바꾸는 역할!!
			int orderNo = Integer.parseInt(request.getParameter("orderNo"));

			WebUtil.forward(request, response, "/WEB-INF/views/manager/specificinfo.jsp");
		
	}else if("orderAll".equals(actionName))

	{
		HttpSession session = request.getSession();
		ArrayList<ProductVo> list = (ArrayList<ProductVo>) session.getAttribute("cartlist");
		MemberVo authUser = getAuthUser(request);
		int memNo = authUser.getMemNo();
		PointDao dao2 = new PointDaoImpl();
		int point = dao2.check(memNo);
		int price = 0;
		for (int i = 0; i < list.size(); i++) {
			price += list.get(i).getProPrice() * list.get(i).getAmount();
		}
		request.setAttribute("point", point);
		request.setAttribute("price", price);
		request.setAttribute("total", price + 2000);
		WebUtil.forward(request, response, "/WEB-INF/views/order/orderMulti.jsp");
	} else if("multiInsert".equals(actionName))
		{
		HttpSession session = request.getSession();
		ArrayList<ProductVo> list = (ArrayList<ProductVo>) session.getAttribute("cartlist");
		int pnt = 0;
		if (request.getParameter("use_pnt") == "") {
			pnt = 0;
		} else {
			pnt = Integer.parseInt(request.getParameter("use_pnt"));
		}
		int total = Integer.parseInt(request.getParameter("total"));
		int memNo = Integer.parseInt(request.getParameter("memNo"));
		String addCheck = request.getParameter("addCheck");
		String name = null;
		String postc = null;
		String doro = null;
		String jibun = null;
		String add = null;
		String phone = null;
		String ps = null;
		if (addCheck.equals("0")) {
			System.out.println(addCheck + "에드체크 일로 들어옴");
			name = request.getParameter("memName");
			postc = request.getParameter("memPostc1");
			doro = request.getParameter("memDoro");
			jibun = request.getParameter("memJibun");
			add = request.getParameter("memAdd");
			phone = request.getParameter("memPhone");
			ps = request.getParameter("memPs");
		} else if (addCheck.equals("1")) {
			name = request.getParameter("name");
			postc = request.getParameter("postc");
			doro = request.getParameter("doro");
			jibun = request.getParameter("jibun");
			add = request.getParameter("add");
			phone = request.getParameter("phone");
			ps = request.getParameter("ps");
		}
		if (pnt > 0) {
			PointDaoImpl pDao = new PointDaoImpl();
			pDao.insertProcess(memNo, pnt, "포인트로 결제");
			System.out.println("시발시발시발");
		}
		OrderInfoVo vo = new OrderInfoVo(total, memNo);
		OrderInfoDao dao = new OrderInfoDaoImpl();
		OrderHasProductDao dao2 = new OrderHasProductDaoImpl();
		dao.insert(vo);
		int orderNo = dao.getOrderNo(memNo);
		for (int i = 0; i < list.size(); i++) {
			dao2.insert(orderNo, list.get(i).getProNo(), list.get(i).getAmount());
		}
		ShippingVo shippingVo = new ShippingVo(orderNo, name, postc, doro, jibun, add, phone, ps);
		ShippingDao shippingDao = new ShippingDaoImpl();
		shippingDao.insert(shippingVo);
		request.setAttribute("orderNo", orderNo);
		System.out.println(orderNo + "오더넘버");
		WebUtil.forward(request, response, "/WEB-INF/views/order/orderComplete.jsp");
		
	}	else if ("addCart".equals(actionName)) {
		HttpSession session = request.getSession();
		int total = 0;
		ArrayList<ProductVo> list = (ArrayList<ProductVo>) session.getAttribute("cartlist");
		int proNo = Integer.parseInt(request.getParameter("proNo"));
		
		int itemNo = -1;
		for(int i = 0; i < list.size(); i++) {
			if(list.get(i).getProNo() == proNo) {
				itemNo = i;
				break;
			}
		}
		if(itemNo == -1) {
			ProductDao dao = new ProductDaoImpl();
			ProductVo vo = dao.getProduct(proNo);
			if(vo.getProStock() > Integer.parseInt(request.getParameter("amount"))) {
			vo.setAmount(Integer.parseInt(request.getParameter("amount")));
			list.add(vo);
			
			}
			else {
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter writer = response.getWriter();
				writer.println("<script>alert('재고가 부족합니다! 현재 재고는" + vo.getProStock() + "개 입니다');window.close();history.back();</script>");
				writer.close();
				return;
				
			}
		} else {
			list.get(itemNo).setAmount(list.get(itemNo).getAmount()+Integer.parseInt(request.getParameter("amount")));				
		}
		for(int i = 0; i < list.size() ; i++) {
			total += list.get(i).getAmount() * list.get(i).getProPrice();
		}
		session.setAttribute("cartlist", list);
		request.setAttribute("total", total);
		WebUtil.forward(request, response, "/WEB-INF/views/product/addCart.jsp");
	}
	else if("deleteCart".equals(actionName)) {	//장바구니 전체 비우기
		HttpSession session = request.getSession();
		session.removeAttribute("cartlist");
		ArrayList<ProductVo> list = new ArrayList<ProductVo>();
		session.setAttribute("cartlist", list);
		WebUtil.forward(request, response, "/WEB-INF/views/product/addCart.jsp");
	}
	else if ("removeCart".equals(actionName)) {	//장바구니에서 선택상품 삭제
		HttpSession session = request.getSession();
		ArrayList<ProductVo> list = (ArrayList<ProductVo>) session.getAttribute("cartlist");
		String[] value = request.getParameterValues("RowCheck");
		int[] temp = new int[value.length];
		int total = 0;
		for(int j=0; j<value.length;j++) {
			temp[j]=Integer.parseInt(value[j]);
		}			
		for(int i=0; i <list.size() ;i++) {
			for(int z=0;z<temp.length;z++) {
				if(list.get(i).getProNo()==temp[z]) {
					list.remove(i);
				}
			}			
		}
		
		for(int i = 0; i < list.size() ; i++) {
			total += list.get(i).getAmount() * list.get(i).getProPrice();
		}
		request.setAttribute("total", total);
		
		WebUtil.forward(request, response, "/WEB-INF/views/product/addCart.jsp");
	} else if ("showCart".equals(actionName)) {

		WebUtil.forward(request, response,  "/WEB-INF/views/product/addCart.jsp");
	}
	else if ("orderAll".equals(actionName)) {
		HttpSession session = request.getSession();
		ArrayList<ProductVo> list = (ArrayList<ProductVo>) session.getAttribute("cartlist");
		MemberVo authUser = getAuthUser(request);
		int memNo = authUser.getMemNo();
		PointDao dao2 = new PointDaoImpl();
		int point = dao2.check(memNo);
		int price = 0;
		for(int i = 0; i <list.size(); i++) {
			price += list.get(i).getProPrice() * list.get(i).getAmount();
		}
		request.setAttribute("point", point);
		request.setAttribute("price", price);
	    request.setAttribute("total", price + 2000);
		WebUtil.forward(request, response, "/WEB-INF/views/order/orderMulti.jsp");
	}
	else if ("multiInsert".equals(actionName)) {
		HttpSession session = request.getSession();
		ArrayList<ProductVo> list = (ArrayList<ProductVo>) session.getAttribute("cartlist");
		int pnt = 0;
		if(request.getParameter("use_pnt") == "") {
			pnt = 0;
		}else {
			pnt = Integer.parseInt(request.getParameter("use_pnt"));
		}
		int total = Integer.parseInt(request.getParameter("total"));
		int memNo = Integer.parseInt(request.getParameter("memNo"));
		String addCheck = request.getParameter("addCheck");
		String name = null;
		String postc  = null;
		String doro = null;
		String jibun = null;
		String add = null;
		String phone = null;
		String ps = null;
		if(addCheck.equals("0")) {
			System.out.println(addCheck + "에드체크 일로 들어옴");
			name = request.getParameter("memName");
			postc = request.getParameter("memPostc1");
			doro = request.getParameter("memDoro");
			jibun = request.getParameter("memJibun");
			add = request.getParameter("memAdd");
			phone = request.getParameter("memPhone");
			ps = request.getParameter("memPs");
		}else if(addCheck.equals("1")) {
			name = request.getParameter("name");
			postc = request.getParameter("postc");
			doro = request.getParameter("doro");
			jibun = request.getParameter("jibun");
			add = request.getParameter("add");
			phone = request.getParameter("phone");
			ps = request.getParameter("ps");
		}
		if(pnt > 0) {
			PointDaoImpl pDao = new PointDaoImpl();
			pDao.insertProcess(memNo, pnt, "포인트로 결제");
			System.out.println("시발시발시발");
		}
		OrderInfoVo vo = new OrderInfoVo(total, memNo);
		OrderInfoDao dao = new OrderInfoDaoImpl();
		OrderHasProductDao dao2 = new OrderHasProductDaoImpl();
		dao.insert(vo);
		int orderNo = dao.getOrderNo(memNo);
		for(int i = 0; i < list.size(); i++) {
			dao2.insert(orderNo, list.get(i).getProNo(), list.get(i).getAmount());
		}
		ShippingVo shippingVo = new ShippingVo(orderNo, name, postc, doro, jibun, add, phone, ps);
		ShippingDao shippingDao = new ShippingDaoImpl();
		shippingDao.insert(shippingVo);
		request.setAttribute("orderNo", orderNo);
		System.out.println(orderNo + "오더넘버");
		WebUtil.forward(request, response, "/WEB-INF/views/order/orderComplete.jsp");
	}
		
		
		else {
			requestOrderInfoList(request);
			WebUtil.redirect(request, response, "/WEB-INF/views/member/orderConfirmation.jsp");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}


	// 페이징 처리한 게시물들을 받아옴
	public void requestOrderInfoList(HttpServletRequest request) {
		OrderInfoDao dao = OrderInfoDaoImpl.getInstance();
		List<OrderInfoVo> boardlist = new ArrayList<OrderInfoVo>();
		
		PointDao dao3 = new PointDaoImpl();
		request.setAttribute("point", dao3.check(getAuthUser(request).getMemNo()));
		
		int pageNum = 1;
		int limit = LISTCOUNT;
		if (request.getParameter("pageNum") != null) {
			pageNum = Integer.parseInt(request.getParameter("pageNum"));}
		
		String searchDate1 = request.getParameter("searchDate1");
		String searchDate2 = request.getParameter("searchDate2");
		
		int total_record = dao.getListCount(searchDate1 , searchDate2 , getAuthUser(request).getMemNo());
		boardlist = dao.getList(pageNum, limit, getAuthUser(request).getMemNo() , searchDate1, searchDate2 );
		
		int total_page;
		if (total_record % limit == 0) {
			total_page = total_record / limit;
			Math.floor(total_page);
		} else {
			total_page = total_record / limit;
			Math.floor(total_page);
			total_page = total_page + 1;
		}
		
		request.setAttribute("alist", boardlist);
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("total_page", total_page);
		request.setAttribute("total_record", total_record);
		
		
	}

	protected MemberVo getAuthUser(HttpServletRequest request) {
		HttpSession session = request.getSession();
		MemberVo  authUser = (MemberVo) session.getAttribute("authUser");
		return authUser;
	}

}
