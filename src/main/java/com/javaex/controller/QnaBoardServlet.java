package com.javaex.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.javaex.vo.QnaBoardVo;
import com.javaex.dao.QnaBoardDao;
import com.javaex.dao.QnaBoardDaoImpl;
import com.javaex.util.WebUtil;
import com.javaex.vo.MemberVo;
import com.javaex.vo.QnaAnswerVo;
import com.javaex.dao.QnaAnswerDao;
import com.javaex.dao.QnaAnswerDaoImpl;


@WebServlet("/qna")
public class QnaBoardServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String actionName = request.getParameter("a");
		System.out.println("qna:" + actionName);
		
		if ("list".equals(actionName)) {

			String typ = request.getParameter("t");
			String pag = request.getParameter("p");

			String type = "전체 문의";
			if (typ != null && !typ.equals("")) {
				type = typ;
			}

			int page = 1;
			if (pag != null && !pag.equals("")) {
				page = Integer.parseInt(pag);
			}

			QnaBoardDao dao = new QnaBoardDaoImpl();
			List<QnaBoardVo> list = dao.getList(type, page);
			int count = dao.getBoardCount(type);
			System.out.println(count);
			System.out.println(list.toString());
			
			request.setAttribute("list", list);
			request.setAttribute("count", count);
			WebUtil.forward(request, response, "/WEB-INF/views/qnaboard/list.jsp");
			
		} else if("listNoAnswer".equals(actionName)) {
			
			
			String pag = request.getParameter("p");

			int page = 1;
			if (pag != null && !pag.equals("")) {
				page = Integer.parseInt(pag);
			}

			QnaBoardDao dao = new QnaBoardDaoImpl();
			List<QnaBoardVo> list = dao.getNoAnswerList(page);
			int count = dao.getNoAnswerCount();
			System.out.println(count);
			System.out.println(list.toString());
			
			request.setAttribute("list", list);
			request.setAttribute("count", count);
			WebUtil.forward(request, response, "/WEB-INF/views/manager/noansqnalist.jsp");
			
			
			
		} else if ("listmyqna".equals(actionName)){
			
			MemberVo authUser = getAuthUser(request);
			int memno = authUser.getMemNo();
			String pag = request.getParameter("p");
			
			int page = 1;
			if(pag != null && !pag.equals("")) {
				page = Integer.parseInt(pag);
			}
			System.out.println(pag);
			System.out.println(page);
			QnaBoardDao dao = new QnaBoardDaoImpl();
			List<QnaBoardVo> list = dao.getMyQList(memno, page);
			int count = dao.getMyQCount(memno);
			
			request.setAttribute("list", list);
			request.setAttribute("count", count);
			WebUtil.forward(request, response, "/WEB-INF/views/member/myqnalist.jsp");
			
			

		} else if ("read".equals(actionName)) {
			// 게시물 가져오기
			
			int no = Integer.parseInt(request.getParameter("qnaNo"));
			QnaBoardDao dao = new QnaBoardDaoImpl();
			QnaBoardVo qnaboardVo = dao.getBoard(no);
			QnaBoardVo prevVo = dao.prevQna(no);
			QnaBoardVo nextVo = dao.nextQna(no);
			
			QnaAnswerDao ansDao = new QnaAnswerDaoImpl();
			List<QnaAnswerVo> list = ansDao.getAnsList(no);
			
			
			System.out.println(list.toString());

			request.setAttribute("QnaboardVo", qnaboardVo);
			request.setAttribute("prevVo", prevVo);
			request.setAttribute("nextVo", nextVo);
			request.setAttribute("list",list);
			WebUtil.forward(request, response, "/WEB-INF/views/qnaboard/view.jsp");
			
		} else if ("readNoAnswer".equals(actionName)) {
			// 게시물 가져오기
			
			int no = Integer.parseInt(request.getParameter("qnaNo"));
			QnaBoardDao dao = new QnaBoardDaoImpl();
			QnaBoardVo qnaboardVo = dao.getBoard(no);
			QnaBoardVo prevVo = dao.prevNoAnsQna(no);
			QnaBoardVo nextVo = dao.nextNoAnsQna(no);
			
			QnaAnswerDao ansDao = new QnaAnswerDaoImpl();
			List<QnaAnswerVo> list = ansDao.getAnsList(no);
			
			
			System.out.println(list.toString());

			request.setAttribute("QnaboardVo", qnaboardVo);
			request.setAttribute("prevVo", prevVo);
			request.setAttribute("nextVo", nextVo);
			request.setAttribute("list",list);
			WebUtil.forward(request, response, "/WEB-INF/views/manager/noansqnaview.jsp");	
			

		} else if ("checkpassform".equals(actionName)) {

			int no = Integer.parseInt(request.getParameter("qnaNo"));
			QnaBoardDao dao = new QnaBoardDaoImpl();
			QnaBoardVo qnaboardVo = dao.getBoard(no);
			System.out.println(qnaboardVo.toString());
			request.setAttribute("QnaboardVo", qnaboardVo);

			WebUtil.forward(request, response, "/WEB-INF/views/qnaboard/checkpass.jsp");

		} else if ("checkpass".equals(actionName)) {

			String password = request.getParameter("password");
			String qnapass = request.getParameter("qnapass");
			System.out.println(password);
			System.out.println(qnapass);
			int qnano = Integer.parseInt(request.getParameter("qnaNo"));
			String qnaNo = request.getParameter("qnaNo");

			if (!qnapass.equals(password)) {
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter writer = response.getWriter();
				writer.println("<script>alert('비밀번호를 확인해주세요.'); history.back();</script>");
				writer.close();
				return;
			}
			QnaBoardDao dao = new QnaBoardDaoImpl();
			QnaBoardVo vo = dao.getBoard(qnano);

			System.out.println(vo);
			System.out.println("성공");
			request.setAttribute("QnaboardVo", vo);

			WebUtil.forward(request, response, "WEB-INF/views/qnaboard/view.jsp");

		} else if ("write".equals(actionName)) {

			MemberVo authUser = getAuthUser(request);
			if (authUser == null) {

				String nickname = request.getParameter("nickname");
				String password = request.getParameter("password");
				String type = request.getParameter("type");
				String title = request.getParameter("title");
				String content = request.getParameter("content");
				int priv = Integer.parseInt(request.getParameter("private"));

				QnaBoardVo vo = new QnaBoardVo(nickname, password, title, type, content, priv);
				QnaBoardDao dao = new QnaBoardDaoImpl();
				dao.insert(vo);
				System.out.println(vo.toString());

			} else {
				int memNo = authUser.getMemNo();
				// System.out.println("memNo : ["+memNo+"]");
				String type = request.getParameter("type");
				String title = request.getParameter("title");
				String content = request.getParameter("content");
				int priv = Integer.parseInt(request.getParameter("private"));

				QnaBoardVo vo = new QnaBoardVo(memNo, title, type, content, priv);
				QnaBoardDao dao = new QnaBoardDaoImpl();
				dao.insert(vo);
				System.out.println(vo.toString());

			}

			WebUtil.redirect(request, response, "/mysiteB/qna?a=list");

		} else if ("modifyform".equals(actionName)) {
			// 게시물 가져오기
			int no = Integer.parseInt(request.getParameter("qnaNo"));
			QnaBoardDao dao = new QnaBoardDaoImpl();
			QnaBoardVo qnaboardVo = dao.getBoard(no);

			// 게시물 화면에 보내기
			request.setAttribute("QnaboardVo", qnaboardVo);
			WebUtil.forward(request, response, "/WEB-INF/views/qnaboard/modify.jsp");

		} else if ("modifypass".equals(actionName)) {

			String password = request.getParameter("password");
			String qnapass = request.getParameter("qnapass");
			System.out.println(password);
			System.out.println(qnapass);
			int qnano = Integer.parseInt(request.getParameter("qnaNo"));
			String qnaNo = request.getParameter("qnaNo");

			if (!qnapass.equals(password)) {
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter writer = response.getWriter();
				writer.println("<script>alert('비밀번호를 확인해주세요.'); history.back();</script>");
				writer.close();
				return;
			}
			QnaBoardDao dao = new QnaBoardDaoImpl();
			QnaBoardVo vo = dao.getBoard(qnano);
			
			
			request.setAttribute("QnaboardVo", vo);

			WebUtil.forward(request, response, "/WEB-INF/views/qnaboard/modify.jsp");

		} else if ("modify".equals(actionName)) {
			// 게시물 가져오기
			String title = request.getParameter("title");
			String type = request.getParameter("type");
			String content = request.getParameter("content");
			String pass = request.getParameter("password");
			int qnano = Integer.parseInt(request.getParameter("qnaNo"));
			int priv = Integer.parseInt(request.getParameter("private"));

			QnaBoardVo vo = new QnaBoardVo(title, type, content, pass, qnano, priv);
			QnaBoardDao dao = new QnaBoardDaoImpl();

			dao.update(vo);

			WebUtil.redirect(request, response, "/mysiteB/qna?a=list");
		} else if ("writeform".equals(actionName)) {

			WebUtil.forward(request, response, "/WEB-INF/views/qnaboard/write.jsp");
			
		} else if ("deletepass".equals(actionName)) {
			
			String password = request.getParameter("password");
			String qnapass = request.getParameter("qnapass");
			System.out.println(password);
			System.out.println(qnapass);
			int qnano = Integer.parseInt(request.getParameter("qnaNo"));
			String qnaNo = request.getParameter("qnaNo");

			if (!qnapass.equals(password)) {
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter writer = response.getWriter();
				writer.println("<script>alert('비밀번호를 확인해주세요.'); history.back();</script>");
				writer.close();
				return;
			}
			QnaBoardDao dao = new QnaBoardDaoImpl();
			dao.delete(qnano);

			WebUtil.redirect(request, response, "/mysiteB/qna?a=list");


		} else if ("delete".equals(actionName)) {
			int qnano = Integer.parseInt(request.getParameter("qnaNo"));

			QnaBoardDao dao = new QnaBoardDaoImpl();
			dao.delete(qnano);

			WebUtil.redirect(request, response, "/mysiteB/qna?a=list");
			
		} else if ("writeAns".equals(actionName)) {
			
			int qnano = Integer.parseInt(request.getParameter("qnaNo"));
			int memno = Integer.parseInt(request.getParameter("memNo"));
			String answer = request.getParameter("answer");
			
			QnaAnswerVo vo = new QnaAnswerVo(qnano, memno, answer);
			QnaAnswerDao dao = new QnaAnswerDaoImpl();
			System.out.println(vo.toString());
			
			dao.insertAns(vo);
			
			WebUtil.redirect(request, response, "/mysiteB/qna?a=read&qnaNo="+qnano);
			
		} else if ("deleteAns".equals(actionName)) {
			int ansNo = Integer.parseInt(request.getParameter("ansNo"));
			int qnano = Integer.parseInt(request.getParameter("qnaNo"));
			
			QnaAnswerDao dao = new QnaAnswerDaoImpl();
			dao.deleteAns(ansNo);
			
			WebUtil.redirect(request, response, "/mysiteB/qna?a=read&qnaNo="+qnano);

		} else {
			WebUtil.redirect(request, response, "/mysiteB/qna?a=list");
		}

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

}
