package com.javaex.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.mail.Session;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.javaex.dao.BBoardDao;
import com.javaex.dao.BBoardDaoImpl;
import com.javaex.dao.MemberDao;
import com.javaex.dao.MemberDaoImpl;
import com.javaex.dao.OrderInfoDao;
import com.javaex.dao.OrderInfoDaoImpl;
import com.javaex.dao.PointDao;
import com.javaex.dao.PointDaoImpl;
import com.javaex.dao.QnaBoardDao;
import com.javaex.dao.QnaBoardDaoImpl;
import com.javaex.dao.QuestionDao;
import com.javaex.dao.QuestionDaoImpl;
import com.javaex.dao.ReviewDao;
import com.javaex.dao.ReviewDaoImpl;
import com.javaex.dao.ShippingDao;
import com.javaex.dao.ShippingDaoImpl;
import com.javaex.util.WebUtil;
import com.javaex.vo.BBoardVo;
import com.javaex.vo.MemberVo;
import com.javaex.vo.OrderInfoVo;
import com.javaex.vo.PointVo;
import com.javaex.vo.ProductVo;
import com.javaex.vo.QnaBoardVo;
import com.javaex.vo.QuestionVo;
import com.javaex.vo.ReviewVo;
import com.javaex.vo.ShippingVo;

@WebServlet("/user")
public class MemberServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		System.out.println("UserServlet.doGet() 호출");
		request.setCharacterEncoding("utf-8");

		String actionName = request.getParameter("a");

		System.out.println("a->" + actionName);
		response.setContentType("text/html; charset = UTF-8");
		PrintWriter out = response.getWriter();
		
		// 회원가입폼
		if ("joinform".equals(actionName)) {
			WebUtil.forward(request, response, "/WEB-INF/views/member/joinform.jsp");

		} else if ("join".equals(actionName)) {
			System.out.println("actionName => " + actionName);

			String memname = request.getParameter("memName");
			String memid1 = request.getParameter("memId1");
			String memid2 = request.getParameter("memId2");
			String memid = memid1 + "@" + memid2;
			String mempass = request.getParameter("memPass");
			String memphone1 = request.getParameter("demo-category");
			String memphone2 = request.getParameter("memPhone2");
			String memphone3 = request.getParameter("memPhone3");
			String memphone = memphone1 +"-" + memphone2 + "-"+memphone3;
			String mempostc = request.getParameter("memPostc");
			String memdoro = request.getParameter("memDoro");
			String memjibun = request.getParameter("memJibun");
			String memadd = request.getParameter("memAdd");

			MemberVo vo = new MemberVo(memname, memphone, mempostc, memdoro, memjibun, memadd, memid, mempass);

			MemberDao dao = new MemberDaoImpl();
			PointDao dao2 = new PointDaoImpl();
			dao.insert(vo);
			dao2.insert(dao.getMember(memid, mempass).getMemNo());
			System.out.println(dao.getMember(memid, mempass).getMemNo());
			System.out.println("회원가입성공" + vo);
			WebUtil.forward(request, response, "/WEB-INF/views/main/index.jsp");
		} else if ("loginform".equals(actionName)) {
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/member/loginform.jsp");
			rd.forward(request, response);

		} else if ("login".equals(actionName)) {
			String memid = request.getParameter("memId");
			String password = request.getParameter("memPass");

			MemberDao dao = new MemberDaoImpl();
			MemberVo vo = dao.getMember(memid, password);

			System.out.println(vo);

			if (vo == null) {
				response.sendRedirect("/mysiteB/user?a=loginform&result=fail");

			} else {
				System.out.println("성공");
				HttpSession session = request.getSession(true);
				session.setAttribute("authUser", vo);
				ArrayList<ProductVo> list = new ArrayList<ProductVo>();
				session.setAttribute("cartlist", list);
				response.sendRedirect("/mysiteB/main");
			}

		

		} else if ("idcheck".equals(actionName)) {
			
			String memId = request.getParameter("memId");
			MemberDao dao = new MemberDaoImpl();

			Enumeration e = request.getParameterNames();
			while (e.hasMoreElements()) {
				String name = (String) e.nextElement();
				String[] values = request.getParameterValues(name);
				System.out.println(name);
				System.out.println(values);
				for (String value : values) {
					System.out.println("na=" + name + ",value=" + value);
				}
			}
		} else if ("validation".equals(actionName)) {

			request.setCharacterEncoding("UTF-8");

			request.getParameter("memId");
			request.getParameter("memPass");
			request.getParameter("memName");

		}

		else if ("logout".equals(actionName)) {

			System.out.println("로그아웃");

			HttpSession session = request.getSession();
			session.removeAttribute("authUser");
			session.invalidate();

			WebUtil.redirect(request, response, "/mysiteB/main");

		} else if ("admin".equals(actionName)) {
			WebUtil.forward(request, response, "/WEB-INF/views/admin/adminMain.jsp");

		} else if ("mypage".equals(actionName)) {
			
			
			MemberVo authUser = getAuthUser(request);
			int no = authUser.getMemNo();
			
			OrderInfoDao dao = new OrderInfoDaoImpl();
			
			PointDao dao3 = new PointDaoImpl();
			OrderInfoVo vo = dao.recent(no);
			
			ShippingDao dao2 = new ShippingDaoImpl();
			request.setAttribute("count1", dao2.count1(no));
			request.setAttribute("count2", dao2.count2(no));
			request.setAttribute("count3", dao2.count3(no));
			request.setAttribute("point", dao3.check(no));
			System.out.println("no : " + no);

			if (authUser.getAdminCk().equals("1")) {
				QnaBoardDao qdao = new QnaBoardDaoImpl();
				int count = qdao.getNoAnswerCount();
				System.out.println(count);
				int newCount = qdao.getNewOrderCount();
				ArrayList<QnaBoardVo> qblist = qdao.getNoAnswerL();
				
				QuestionDao pqdao = new QuestionDaoImpl();
				int pcount = pqdao.getNoAnsCount();
				
				BBoardDao bbdao = new BBoardDaoImpl();
				List<BBoardVo> bblist = bbdao.getList2();

				request.setAttribute("qblist", qblist);
				request.setAttribute("noanswercount", count);
				request.setAttribute("pcount", pcount);
				request.setAttribute("newCount", newCount);
				request.setAttribute("bblist", bblist);
				WebUtil.forward(request, response, "/WEB-INF/views/manager/manage.jsp");

			} else {
				OrderInfoDao odao = new OrderInfoDaoImpl();
				ArrayList<OrderInfoVo> olist = odao.getRecentList(no);
				request.setAttribute("olist", olist);
				request.setAttribute("recent", vo);
				WebUtil.forward(request, response, "/WEB-INF/views/member/mypageform.jsp");

			}

		} else if ("orderlist".equals(actionName)) {
			// orderlist 로

			MemberVo authUser = getAuthUser(request);
			int no = Integer.parseInt(request.getParameter("memNo"));
			MemberDao dao = new MemberDaoImpl();
			ArrayList<OrderInfoVo> list = dao.getList(no);
			System.out.println("no : " + no);

			// 리스트 화면에 보내기
			request.setAttribute("list", list);
			WebUtil.forward(request, response, "/WEB-INF/views/member/orderlistform.jsp");

		} else if ("modifyform".equals(actionName)) {

			PointDao dao3 = new PointDaoImpl();
			request.setAttribute("point", dao3.check(getAuthUser(request).getMemNo()));
			WebUtil.forward(request, response, "/WEB-INF/views/member/modifyform.jsp");

		} else if ("modify".equals(actionName)) {

			System.out.println("actionName => " + actionName);
			// 멤버 수정 부분
			String memName = request.getParameter("memName");
			String memPhone = request.getParameter("memPhone");

			String memPostc = request.getParameter("memPostc");
			String memDoro = request.getParameter("memDoro");
			String memJibun = request.getParameter("memJibun");

			String memAdd = request.getParameter("memAdd");
			String memPass = request.getParameter("memPass");

			MemberVo vo = new MemberVo();
			vo.setMemName(memName);
			vo.setMemPhone(memPhone);

			vo.setMemPostc(memPostc);
			vo.setMemDoro(memDoro);
			vo.setMemJibun(memJibun);

			vo.setMemAdd(memAdd);
			vo.setMemPass(memPass);

			HttpSession session = request.getSession();
			MemberVo authUser = (MemberVo) session.getAttribute("authUser");

			int no = authUser.getMemNo();
			vo.setMemNo(no);

			MemberDao dao = new MemberDaoImpl();
			dao.update(vo);

			authUser.setMemName(memName);

			WebUtil.forward(request, response, "/WEB-INF/views/main/index.jsp");

		} else if ("deleteform".equals(actionName)) {
			
			PointDao dao3 = new PointDaoImpl();
			request.setAttribute("point", dao3.check(getAuthUser(request).getMemNo()));
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/member/deleteform.jsp");
			rd.forward(request, response);

		} else if ("delete".equals(actionName)) {
			String memid = request.getParameter("memId");
			String mempass = request.getParameter("memPass");

			MemberDao dao = new MemberDaoImpl();
			MemberVo vo = dao.getMember(memid, mempass);

			System.out.println(vo);

			if (vo == null) {
				System.out.println("실패");
				response.sendRedirect("/mysiteB/user?a=deleteform&result=fail");
			} else {
				System.out.println("탈퇴 성공");
				HttpSession session = request.getSession(true);

				MemberVo authUser = (MemberVo) session.getAttribute("authUser");

				int no = authUser.getMemNo();
				vo.setMemNo(no);
				dao.delete(vo);
				session.removeAttribute("authUser");
				session.invalidate();

				response.sendRedirect("/mysiteB/main");
				return;
			}

		} else if ("wishform".equals(actionName)) {
			// 비밀번호 찾을 때 임시 비밀번호 이메일로 보내는 기능

			String name1 = request.getParameter("name");
			String memId1 = request.getParameter("memId");
			String number = request.getParameter("number");

			System.out.println("임시 비밀번호는: " + number);
			MemberDao dao = new MemberDaoImpl();
			dao.chanegepw(number, name1, memId1);

			WebUtil.forward(request, response, "/WEB-INF/views/member/password.jsp");

		} else if ("review".equals(actionName)) {
			MemberVo authUser = getAuthUser(request);
			int no = authUser.getMemNo();
			
			ReviewDao dao = new ReviewDaoImpl();
			List<ReviewVo> list = dao.getPrivateList(1, 5, no);
			PointDao dao3 = new PointDaoImpl();
			request.setAttribute("point", dao3.check(getAuthUser(request).getMemNo()));
            request.setAttribute("revlist", list);
		
			WebUtil.forward(request, response, "/WEB-INF/views/member/review.jsp");

		} else if ("pointform".equals(actionName)) {

			String pag = request.getParameter("p");

			int page = 1;
			if (pag != null && !pag.equals("")) {
				page = Integer.parseInt(pag);
			}

			MemberVo authUser = getAuthUser(request);
			int no = authUser.getMemNo();
			PointDao dao = new PointDaoImpl();
			ArrayList<PointVo> list = dao.getList(no, page);
			System.out.println("no : " + no);
			PointVo pvo = dao.getPoint(no);
			int count = dao.getPointCount(no);
			System.out.println(count);

			PointDao dao3 = new PointDaoImpl();
			request.setAttribute("point", dao3.check(getAuthUser(request).getMemNo()));
			// 리스트 화면에 보내기
			request.setAttribute("list", list);
			request.setAttribute("pvo", pvo);
			request.setAttribute("count", count);

			WebUtil.forward(request, response, "/WEB-INF/views/member/pointform.jsp");

			
		} else if ("mainup".equals(actionName)) {
			// 처음 서버 접속하고 뜨는 이벤트 팝업
			WebUtil.forward(request, response, "/WEB-INF/views/main/event.jsp");

		} else if ("findid".equals(actionName)) {
			// 로그인 창에서 아이디/비밀 번호 찾기로 이동하면 보이는 페이지로 가는거! 보이는 건 아이디 찾기 내용이 보입니당
			WebUtil.forward(request, response, "/WEB-INF/views/member/findId.jsp");

		} else if ("findpass".equals(actionName)) {
			// 이건 아이디/비밀번호 창이 디폴트가 아이디 찾기 인데 거기서 비밀 번호 찾기 누르면 가는 비밀번호 찾기 페이지 가는거!
			WebUtil.forward(request, response, "/WEB-INF/views/member/findPass.jsp");

		} else if ("id".equals(actionName)) {
			// 이건 아이디 찾는 기능하는 거!
			String name2 = request.getParameter("name");
			String phone2 = request.getParameter("phone");

			System.out.println("이름은: " + name2);
			MemberDao dao = new MemberDaoImpl();

			String id = dao.FindMember(name2, phone2);
			System.out.println("이건 서블릿에서 찍는 아이디: " + id);

			if (id == null) {
		 		out.println("<script>alert('잘못된 정보');</script>");		
		 		out.flush();
				WebUtil.forward(request, response, "/WEB-INF/views/member/findId.jsp");

			} else {
				request.setAttribute("id", id);
				WebUtil.forward(request, response, "/WEB-INF/views/member/id.jsp");
			}

		// 내가 쓴 문의글 보기
		} else if ("readmyq".equals(actionName)) {

			MemberVo authUser = getAuthUser(request);
			int no = authUser.getMemNo();

			QnaBoardDao dao = new QnaBoardDaoImpl();
			ArrayList<QnaBoardVo> qlist = dao.getMyQList(no, 1);

			QuestionDao pdao = new QuestionDaoImpl();
			List<QuestionVo> pqlist = pdao.getMypqList(no, 1);

			PointDao dao3 = new PointDaoImpl();
			request.setAttribute("point", dao3.check(getAuthUser(request).getMemNo()));
			request.setAttribute("qlist", qlist);
			request.setAttribute("pqlist", pqlist);

			WebUtil.forward(request, response, "/WEB-INF/views/member/myqna.jsp");
		} else {
			WebUtil.forward(request, response, "/WEB-INF/views/main/index.jsp");

		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	// 로그인 되어 있는 정보를 가져온다
	protected MemberVo getAuthUser(HttpServletRequest request) {
		HttpSession session = request.getSession();
		MemberVo authUser = (MemberVo) session.getAttribute("authUser");

		return authUser;
	}

}
