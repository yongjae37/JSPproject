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
import javax.swing.JOptionPane;

import com.javaex.dao.AnswerDao;
import com.javaex.dao.AnswerDaoImpl;
import com.javaex.dao.PointDao;
import com.javaex.dao.PointDaoImpl;
import com.javaex.dao.ProductDao;
import com.javaex.dao.ProductDaoImpl;
import com.javaex.dao.QuestionDao;
import com.javaex.dao.QuestionDaoImpl;
import com.javaex.dao.ReviewDao;
import com.javaex.dao.ReviewDaoImpl;
import com.javaex.util.WebUtil;
import com.javaex.vo.AnswerVo;
import com.javaex.vo.MemberVo;
import com.javaex.vo.ProductVo;
import com.javaex.vo.QuestionVo;
import com.javaex.vo.ReviewVo;

@WebServlet("/product")
public class ProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String ATTACHES_DIR = "/usr/local/tomcat8.5/webapps/mysiteB/upload";
	private static final int LIMIT_SIZE_BYTES = 5 * 1024 * 1024;
	private static final String CHARSET = "utf-8";
	private static final int LISTCOUNT = 9;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		System.out.println("ProductServlet.doGet() 호출");

		String actionName = request.getParameter("a");

		System.out.println("a -> " + actionName);

		// 등록하기 (manage)
		if ("insertProduct".equals(actionName)) {
			
			String proName = "";
			String proCateg = "";
			int proStock = 0;
			int proPrice = 0;
			String proDesc = "";
			int proOnSale = 0;
			String proFileName = "";
			String temp ="";
			
			response.setContentType("text/html; charset=UTF-8");
			
			File attachesDir = new File(ATTACHES_DIR);
			DiskFileItemFactory fileItemFactory = new DiskFileItemFactory();
			fileItemFactory.setRepository(attachesDir);
			fileItemFactory.setSizeThreshold(LIMIT_SIZE_BYTES);
			ServletFileUpload fileUpload = new ServletFileUpload(fileItemFactory);
			
			try {
				List<FileItem> items = fileUpload.parseRequest(request);
				for (FileItem item : items) {
					if (item.isFormField()) {
						System.out.printf("파라미터 명 : %s, 파라미터 값 :  %s \n", item.getFieldName(), item.getString(CHARSET));
						if ("proName".equals(item.getFieldName())) {
							proName = item.getString(CHARSET);
						} else if ("proCateg".equals(item.getFieldName())) {
							proCateg = item.getString(CHARSET);
						} else if ("proStock".equals(item.getFieldName())) {
							proStock = Integer.parseInt(item.getString(CHARSET));
						} else if ("proPrice".equals(item.getFieldName())) {
							proPrice = Integer.parseInt(item.getString(CHARSET));
						} else if ("proDesc".equals(item.getFieldName())) {
							proDesc = item.getString(CHARSET);
						} else if ("proOnSale".equals(item.getFieldName())) {
							proOnSale = Integer.parseInt(item.getString(CHARSET));
						}
					} else {
						System.out.printf("파라미터 명 : %s, 파일 명 : %s,  파일 크기 : %s bytes \n", item.getFieldName(),
								item.getName(), item.getSize());
						if (item.getSize() > 0) {
							String separator = File.separator;
							int index = item.getName().lastIndexOf(separator);
							proFileName = item.getName().substring(index + 1);
							File uploadFile = new File(ATTACHES_DIR + separator + proFileName);
							if (uploadFile.exists()) {
								int index2 = proFileName.lastIndexOf('.');
								proFileName = proFileName.substring(0, index2) + "(1)"
										+ proFileName.substring(index2, proFileName.length());
								uploadFile = new File(ATTACHES_DIR + separator + proFileName);
								
							}
							item.write(uploadFile);
							temp = uploadFile.getName();
							System.out.println(uploadFile.getName() + "로 파일 업로드 완료");

						}
					}
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
			ProductVo vo = new ProductVo(proName, proCateg, proStock, proPrice, proDesc, proOnSale, temp);

			ProductDao dao = new ProductDaoImpl();
			dao.insert(vo);
			WebUtil.forward(request, response, "/product?a=manageProduct");
		
		} 
		//상품 등록 양식 (manage)
		else if ("insertform".equals(actionName)) {
			
			WebUtil.forward(request, response, "/WEB-INF/views/product/insertform.jsp");
		}
		
		//상품 조회, 검색
		else if ("productList".equals(actionName)) {
			
			requestProductList(request);
			WebUtil.forward(request, response, "/WEB-INF/views/product/category.jsp");
		}
		//상품 상세보기
		else if ("readProduct".equals(actionName)) {
			int proNo = Integer.parseInt(request.getParameter("proNo"));
			//int revNo = Integer.parseInt(request.getParameter("revNo"));
			
			ProductDao dao = new ProductDaoImpl();
			ProductVo vo = dao.getProduct(proNo);
			dao.updateHit(proNo);
			
			int wavail = 10;
			MemberVo authUser = getAuthUser(request);
			ReviewDao rdao = new ReviewDaoImpl();
			if (authUser != null) {
				
				wavail = rdao.writeAvail(proNo, authUser.getMemNo());
				request.setAttribute("wavail", wavail);
				
			} else {
				request.setAttribute("wavail", 0);
				
			}
			
			int uavail = 10;
			
			if (authUser != null) {
				uavail = rdao.updateAvail(proNo, authUser.getMemNo());
				request.setAttribute("uavail", uavail);
				
			} else {
				request.setAttribute("uavail", 0);
				
			}
			System.out.println("uavail"+uavail);
			// 게시물 화면에 보내기
			request.setAttribute("ProductVo", vo);
			request.setAttribute("proNo", proNo);
			requestReviewList(request);
			requestQnaList(request);
			
			WebUtil.forward(request, response, "/WEB-INF/views/product/productInfo.jsp");
		}
		//삭제 확인 양식 (manage)
		else if ("deleteform".equals(actionName)) {
			int num = Integer.parseInt(request.getParameter("proNo"));
						
			ProductDao dao = new ProductDaoImpl();
			ProductVo vo = dao.getProduct(num);
			request.setAttribute("ProductVo", vo);
			request.setAttribute("proNo", num);
			System.out.println(vo.getProNo());
			WebUtil.forward(request, response, "/WEB-INF/views/product/productDeletebox.jsp");
		}
		// 삭제 (manage)
		else if ("deleteProduct".equals(actionName)) {
			int num = Integer.parseInt(request.getParameter("proNo"));
			ProductDao dao = new ProductDaoImpl();
			dao.delete(num);
			System.out.println("삭제확인");
			WebUtil.forward(request, response, "/WEB-INF/views/product/productDeleteboxSubmitted.jsp");
		}
		
		//상품 관리 페이지 (manage)
		else if ("manageProduct".equals(actionName)) {
			requestAdvProductList(request);
			WebUtil.forward(request, response, "/WEB-INF/views/manager/productManage.jsp");
		}
		
		//상품 수정 양식 (manage)
		else if ("updateform".equals(actionName)) {
			int proNo = Integer.parseInt(request.getParameter("proNo"));
			ProductDao pdao = new ProductDaoImpl();
			ProductVo pvo = pdao.getProduct(proNo);
			
			request.setAttribute("pvo", pvo);
			request.setAttribute("proNo", proNo);
			
			WebUtil.forward(request, response, "/WEB-INF/views/product/updateform.jsp");
		}
		// 상품 수정 (manage)
		else if ("updateProduct".equals(actionName)) {
			
			int proNo = Integer.parseInt(request.getParameter("proNo"));
			
			String proName = "";
			String proCateg = "";
			int proStock = 0;
			int proPrice = 0;
			String proDesc = "";
			int proOnSale = 0;
			String proFileName = "";
			String temp ="";
			String a = "";
			
			response.setContentType("text/html; charset=UTF-8");
			
			File attachesDir = new File(ATTACHES_DIR);
			DiskFileItemFactory fileItemFactory = new DiskFileItemFactory();
			fileItemFactory.setRepository(attachesDir);
			fileItemFactory.setSizeThreshold(LIMIT_SIZE_BYTES);
			ServletFileUpload fileUpload = new ServletFileUpload(fileItemFactory);
			
			try {
				List<FileItem> items = fileUpload.parseRequest(request);
				for (FileItem item : items) {
					if (item.isFormField()) {
						System.out.printf("파라미터 명 : %s, 파라미터 값 :  %s \n", item.getFieldName(), item.getString(CHARSET));
						if ("proName".equals(item.getFieldName())) {
							proName = item.getString(CHARSET);
						} else if ("proCateg".equals(item.getFieldName())) {
							proCateg = item.getString(CHARSET);
						} else if ("proStock".equals(item.getFieldName())) {
							proStock = Integer.parseInt(item.getString(CHARSET));
						} else if ("proPrice".equals(item.getFieldName())) {
							proPrice = Integer.parseInt(item.getString(CHARSET));
						} else if ("proDesc".equals(item.getFieldName())) {
							proDesc = item.getString(CHARSET);
						} else if ("proOnSale".equals(item.getFieldName())) {
							proOnSale = Integer.parseInt(item.getString(CHARSET));
						} else if ("original".equals(item.getFieldName())) {
							a = item.getString(CHARSET);
						}
					} else {
						System.out.printf("파라미터 명 : %s, 파일 명 : %s,  파일 크기 : %s bytes \n", item.getFieldName(),
								item.getName(), item.getSize());
						if (item.getSize() > 0) {
							String separator = File.separator;
							int index = item.getName().lastIndexOf(separator);
							proFileName = item.getName().substring(index + 1);
							File uploadFile = new File(ATTACHES_DIR + separator + proFileName);
							if (uploadFile.exists()) {
								int index2 = proFileName.lastIndexOf('.');
								proFileName = proFileName.substring(0, index2) + "(1)"
										+ proFileName.substring(index2, proFileName.length());
								uploadFile = new File(ATTACHES_DIR + separator + proFileName);
								
							}
							item.write(uploadFile);
							temp = uploadFile.getName();
							System.out.println(uploadFile.getName() + "로 파일 업로드 완료");

						}
						
					}
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
			if(temp == null || temp.equals("")) {
				temp = a;
			}
			System.out.println(a + "a 뿌리기 직전");
			ProductVo vo = new ProductVo(proNo, proName, proCateg, proStock, proPrice, proDesc, proOnSale, temp);

			ProductDao dao = new ProductDaoImpl();
			dao.update(vo);
			WebUtil.forward(request, response, "/product?a=manageProduct");

		}
		//문의 팝업창
		else if ("inquiryBox".equals(actionName)) {
			int num = Integer.parseInt(request.getParameter("proNo"));
			
			ProductDao pdao = new ProductDaoImpl();
			ProductVo pvo = pdao.getProduct(num);
			
			request.setAttribute("pvo", pvo);
			request.setAttribute("proNo", num);
			
			WebUtil.forward(request, response, "/WEB-INF/views/product/productInquirybox.jsp");
		}
		
		//문의 등록
		else if ("insertQuestion".equals(actionName)) {
			
			int num = Integer.parseInt(request.getParameter("proNo"));
			MemberVo authUser = getAuthUser(request);
			if (authUser == null) {
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter writer = response.getWriter();
				writer.println("<script>alert('로그인 후 이용 가능합니다!');window.close();</script>");
				writer.close();
				return;
			}
			int memNo = authUser.getMemNo();
			System.out.println(memNo + "memNo result");
			String qcontent = request.getParameter("askContent");
			if (qcontent == null || qcontent == "") {
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter writer = response.getWriter();
				writer.println("<script>alert('내용을 입력하세요'); history.back();</script>");
				writer.close();
				return;
			}
			try {
				//String qcontent = request.getParameter("askContent");
				System.out.println(qcontent+"qcontent");
				QuestionDao qdao = new QuestionDaoImpl();
				QuestionVo qvo = new QuestionVo(num, memNo, qcontent);
				qdao.insert(qvo);
 
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			response.setContentType("text/html; charset=UTF-8");
			// 게시물 화면에 보내기
			
			request.setAttribute("proNo", num);
			WebUtil.forward(request, response, "/WEB-INF/views/product/productInquiryboxSubmitted.jsp");
		}
		//답변 팝업창
		else if ("answerBox".equals(actionName)) {
			int qnum = Integer.parseInt(request.getParameter("qNo"));
					
			QuestionDao qdao = new QuestionDaoImpl();
			QuestionVo qvo = qdao.getQuestion(qnum);
			
			request.setAttribute("qvo", qvo);
			request.setAttribute("qNo", qnum);
					
			WebUtil.forward(request, response, "/WEB-INF/views/product/productAnswerbox.jsp");
		}

		// 답변 등록
		else if ("insertAnswer".equals(actionName)) {
			
			int qnum = Integer.parseInt(request.getParameter("qNo"));
			MemberVo authUser = getAuthUser(request);
			if (!authUser.getAdminCk().equals("1")) {
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter writer = response.getWriter();
				writer.println("<script>alert('관리자만 이용 가능합니다!');window.close();</script>");
				writer.close();
				return;
			}
			int memNo = authUser.getMemNo();
			System.out.println(memNo + "memNo result");
			String acontent = request.getParameter("answerContent");
			if (acontent == null || acontent == "") {
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter writer = response.getWriter();
				writer.println("<script>alert('내용을 입력하세요'); history.back();</script>");
				writer.close();
				return;
			}
			try {
				//String qcontent = request.getParameter("askContent");
				System.out.println(acontent+"acontent");
				AnswerDao adao = new AnswerDaoImpl();
				AnswerVo avo = new AnswerVo(qnum, memNo, acontent);
				System.out.println(memNo);
				adao.insert(avo);
					
 
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			response.setContentType("text/html; charset=UTF-8");
			// 게시물 화면에 보내기
			
			request.setAttribute("qNo", qnum);
			WebUtil.forward(request, response, "/WEB-INF/views/product/productAnswerboxSubmitted.jsp");
		
		//내가 쓴 상품문의 리스트
		} else if("listmypqna".equals(actionName)) {
					
			MemberVo authUser = getAuthUser(request);
			int memno = authUser.getMemNo();
			String pag = request.getParameter("p");
			
			int page = 1;
			if(pag != null && !pag.equals("")) {
				page = Integer.parseInt(pag);
			}
					
			QuestionDao dao = new QuestionDaoImpl();
			List<QuestionVo> list = dao.getMypqList(memno, page);
			int count = dao.getMypqCount(memno);
			
			request.setAttribute("list",list);
			request.setAttribute("count", count);
			WebUtil.forward(request, response, "/WEB-INF/views/member/mypqnalist.jsp");
					
		//내가 쓴 상품문의글과 답변 상세보기
		} else if("readmypqna".equals(actionName)){
				
			int no = Integer.parseInt(request.getParameter("qno"));
			QuestionDao dao = new QuestionDaoImpl();
			QuestionVo questionVo = dao.getQuestion(no);
			
			AnswerDao ansDao = new AnswerDaoImpl();
			AnswerVo ansVo = ansDao.getAnswer(no);
			
			request.setAttribute("qVo", questionVo);
			request.setAttribute("aVo", ansVo);
			WebUtil.forward(request, response, "/WEB-INF/views/member/mypqnaview.jsp");
			
					
		//내가 작성한 리뷰읽기
		} else if("readmyreview".equals(actionName)) {
			
			int revno = Integer.parseInt(request.getParameter("revNo"));
			ReviewDao dao = new ReviewDaoImpl();
			ReviewVo reviewVo = dao.getReview(revno);
		
			request.setAttribute("reviewVo",reviewVo);
					
			WebUtil.forward(request, response, "/WEB-INF/views/member/myreview.jsp");
		//리뷰 등록 양식
		} else if ("reviewform".equals(actionName)) {
			
			MemberVo authUser = getAuthUser(request);
			int memNo = authUser.getMemNo();
			int proNo = Integer.parseInt(request.getParameter("proNo"));
						
			ProductDao pdao = new ProductDaoImpl();
			ProductVo pvo = pdao.getProduct(proNo);
			
			request.setAttribute("pvo", pvo);
			request.setAttribute("proNo", proNo);
			request.setAttribute("memNo", memNo);

			WebUtil.forward(request, response, "/WEB-INF/views/product/reviewform.jsp");
		
		}
		//리뷰 등록
		else if ("insertReview".equals(actionName)) {
			MemberVo authUser = getAuthUser(request);
			int memNo = authUser.getMemNo();
			int proNo = Integer.parseInt(request.getParameter("proNo"));
			
			PointDao ppdao = new PointDaoImpl();
			
			ReviewDao rdao = new ReviewDaoImpl();
			String title = "";
			String content = "";
			int rate = 0;
			
			String fileName = "";
			String[] file = new String[2];
			String location = "";
			int i = 0;
			if (rdao.writeAvail(proNo, memNo) == 1) {

				response.setContentType("text/html; charset=UTF-8");
				
				File attachesDir = new File(ATTACHES_DIR);
				DiskFileItemFactory fileItemFactory = new DiskFileItemFactory();
				fileItemFactory.setRepository(attachesDir);
				fileItemFactory.setSizeThreshold(LIMIT_SIZE_BYTES);
				ServletFileUpload fileUpload = new ServletFileUpload(fileItemFactory);
				
				try {
					List<FileItem> items = fileUpload.parseRequest(request);
					for (FileItem item : items) {
						if (item.isFormField()) {
							System.out.printf("파라미터 명 : %s, 파라미터 값 :  %s \n", item.getFieldName(), item.getString(CHARSET));
							if ("title".equals(item.getFieldName())) {
								title = item.getString(CHARSET);
							} else if ("content".equals(item.getFieldName())) {
								content = item.getString(CHARSET);
							} else if ("rate".equals(item.getFieldName())) {
								if(rate <Integer.parseInt(item.getString(CHARSET))) {
								rate = Integer.parseInt(item.getString(CHARSET));}
							}
						} else {
							System.out.printf("파라미터 명 : %s, 파일 명 : %s,  파일 크기 : %s bytes \n", item.getFieldName(),
									item.getName(), item.getSize());
							if (item.getSize() > 0) {
								String separator = File.separator;
								int index = item.getName().lastIndexOf(separator);
								fileName = item.getName().substring(index + 1);
								File uploadFile = new File(ATTACHES_DIR + separator + fileName);
								if(uploadFile.exists()) {
									int index2 = fileName.lastIndexOf('.');
									fileName = fileName.substring(0, index2) + "(1)" + fileName.substring(index2,fileName.length());
									uploadFile = new File(ATTACHES_DIR + separator + fileName);
								}
								item.write(uploadFile);
								System.out.println(uploadFile.getName() + "로 파일 업로드 완료");
								location = uploadFile.getName();
								file[i++] = location;
							
							}
							
						}
					}
					
				} catch (Exception e) {
					e.printStackTrace();
				}
				
			}
						
			else {
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter writer = response.getWriter();
				writer.println("<script>alert('리뷰를 작성할 수 없습니다.');window.close();</script>");
				writer.close();
				return;
			}
			ReviewVo rvo = new ReviewVo(memNo, proNo, title, content, rate, file[0], file[1]);
			
			if (rdao.insert(rvo) >0) {
				ppdao.pointAfterReview(memNo);
			}
			
			WebUtil.forward(request, response, "/WEB-INF/views/product/reviewSubmitted.jsp");
		} 
		
		//리뷰 삭제 확인 양식
		else if ("deleteRevform".equals(actionName)) {
			int revNo = Integer.parseInt(request.getParameter("revNo"));
			int proNo = Integer.parseInt(request.getParameter("proNo"));
			int memNo = Integer.parseInt(request.getParameter("memNo"));
			ProductDao pdao = new ProductDaoImpl();
			ProductVo pvo = pdao.getProduct(proNo);
			
			request.setAttribute("revNo", revNo);
			request.setAttribute("proName", pvo.getProName());
			request.setAttribute("memNo", memNo);
			WebUtil.forward(request, response, "/WEB-INF/views/product/reviewDeletebox.jsp");
		}
		//리뷰 삭제
		else if ("deleteReview".equals(actionName)) {
			int revNo = Integer.parseInt(request.getParameter("revNo"));
			int memNo = Integer.parseInt(request.getParameter("memNo"));
			ReviewDao dao = new ReviewDaoImpl();
			PointDao ppdao = new PointDaoImpl();
			dao.delete(revNo);	
			ppdao.pointAfterDelete(memNo);
			WebUtil.forward(request, response, "/WEB-INF/views/product/reviewDeleteboxSubmitted.jsp");
		}
		
		//리뷰 수정 양식
		else if ("updateRevform".equals(actionName)) {
			int revNo = Integer.parseInt(request.getParameter("revNo"));
			ReviewDao rdao = new ReviewDaoImpl();
			ReviewVo rvo = rdao.getReview(revNo);

//			MemberVo authUser = getAuthUser(request);
//			if (authUser.getMemNo() != rvo.getMemNo()) {
//				response.setContentType("text/html; charset=UTF-8");
//				PrintWriter writer = response.getWriter();
//				writer.println("<script>alert('작성자만 이용 가능합니다!');window.close();</script>");
//				writer.close();
//				return;
//			}
			ProductDao pdao = new ProductDaoImpl();
			request.setAttribute("pvo", pdao.getProduct(rvo.getProNo()));
			
			request.setAttribute("rvo", rvo);
			request.setAttribute("revNo", revNo);
			
			WebUtil.forward(request, response, "/WEB-INF/views/product/reviewUpdatebox.jsp");
		}
		
		// 리뷰 수정
		else if ("updateReview".equals(actionName)) {
			int revNo = Integer.parseInt(request.getParameter("revNo"));
			int proNo = Integer.parseInt(request.getParameter("proNo"));
			MemberVo authUser = getAuthUser(request);
			int memNo = authUser.getMemNo();
			
			ReviewDao rdao = new ReviewDaoImpl();
			if (rdao.updateAvail(memNo , revNo) > 0) {
				
				String title = "";
				String content = "";
				int rate = 0;
				
				String fileName = "";
				String[] file = new String[2];
				String location = "";
				int i = 0;
				
				response.setContentType("text/html; charset=UTF-8");
				
				File attachesDir = new File(ATTACHES_DIR);
				DiskFileItemFactory fileItemFactory = new DiskFileItemFactory();
				fileItemFactory.setRepository(attachesDir);
				fileItemFactory.setSizeThreshold(LIMIT_SIZE_BYTES);
				ServletFileUpload fileUpload = new ServletFileUpload(fileItemFactory);
				
				try {
					List<FileItem> items = fileUpload.parseRequest(request);
					for (FileItem item : items) {
						if (item.isFormField()) {
							System.out.printf("파라미터 명 : %s, 파라미터 값 :  %s \n", item.getFieldName(), item.getString(CHARSET));
							if ("title".equals(item.getFieldName())) {
								title = item.getString(CHARSET);
							} else if ("content".equals(item.getFieldName())) {
								content = item.getString(CHARSET);
							} else if ("rate".equals(item.getFieldName())) {
								rate = Integer.parseInt(item.getString(CHARSET));
							}
						} else {
							System.out.printf("파라미터 명 : %s, 파일 명 : %s,  파일 크기 : %s bytes \n", item.getFieldName(),
									item.getName(), item.getSize());
							if (item.getSize() > 0) {
								String separator = File.separator;
								int index = item.getName().lastIndexOf(separator);
								fileName = item.getName().substring(index + 1);
								File uploadFile = new File(ATTACHES_DIR + separator + fileName);
								if(uploadFile.exists()) {
									int index2 = fileName.lastIndexOf('.');
									fileName = fileName.substring(0, index2) + "(1)" + fileName.substring(index2,fileName.length());
									uploadFile = new File(ATTACHES_DIR + separator + fileName);
								}
								item.write(uploadFile);
								System.out.println(uploadFile.getName() + "로 파일 업로드 완료");
								location = uploadFile.getName();
								file[i++] = location;
							
							}
							
						}
					}
					
				} catch (Exception e) {
					e.printStackTrace();
				}
				ReviewDao rdao2 = new ReviewDaoImpl();
				ReviewVo rvo = new ReviewVo(revNo, memNo, proNo, title, content, rate, file[0], file[1]);
				rdao2.update(rvo);
				WebUtil.forward(request, response, "/WEB-INF/views/product/reviewUpdateboxSubmitted.jsp");
			}
						
			else {
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter writer = response.getWriter();
				writer.println("<script>alert('리뷰를 수정할 수 없습니다.');window.close();</script>");
				writer.close();
				return;
			}
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	public void requestProductList(HttpServletRequest request) {
		ProductDao dao = ProductDaoImpl.getInstance();
		List<ProductVo> productList = new ArrayList<ProductVo>();

		int pageNum = 1;
		int limit = LISTCOUNT;
		
		if(request.getParameter("pageNum") != null)
			pageNum = Integer.parseInt(request.getParameter("pageNum"));
		
		String searchFrom = request.getParameter("searchFrom");
		String kwd = request.getParameter("kwd");
		String orderBy = request.getParameter("orderBy");
		String categ = request.getParameter("proCateg");
				
		int total_record = dao.getListCount(searchFrom, kwd, categ);
		productList = dao.getList(pageNum, limit, searchFrom, kwd, orderBy, categ);
		
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

		request.setAttribute("list", productList);
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("total_page", total_page);
		request.setAttribute("total_record", total_record);
		request.setAttribute("orderBy", orderBy);
		request.setAttribute("kwd", kwd);
		
	}
	
	public void requestAdvProductList(HttpServletRequest request) { //for "advancedSearch"
		ProductDao dao = ProductDaoImpl.getInstance();
		List<ProductVo> productList = new ArrayList<ProductVo>();

		int pageNum = 1;
		int limit = LISTCOUNT;

		String onSale = "";
		String categ = "";
		String name = "";
		String startPrice = "0";
		String endPrice = "9999999";
		
		if(request.getParameter("pageNum") != null)
			pageNum = Integer.parseInt(request.getParameter("pageNum"));
		if(request.getParameter("onSale") != null)
			onSale = request.getParameter("onSale");
		if(request.getParameter("categ") != null)
			categ = request.getParameter("categ");
		if(request.getParameter("name") != null)
			name = request.getParameter("name");
		if(request.getParameter("startPrice") != null)
			startPrice = request.getParameter("startPrice");
		if(request.getParameter("endPrice") != null)
			endPrice = request.getParameter("endPrice");
		

		int total_record = dao.getAdvListCount(onSale, categ, name, startPrice, endPrice);
		productList = dao.getAdvList(pageNum, limit, onSale, categ, name, startPrice, endPrice);
		
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

		request.setAttribute("manlist", productList);
		request.setAttribute("manpageNum", pageNum);
		request.setAttribute("mantotal_page", total_page);
		request.setAttribute("mantotal_record", total_record);
		
	}
	
	public void requestReviewList(HttpServletRequest request) {
		ReviewDao dao = ReviewDaoImpl.getInstance();
		List<ReviewVo> reviewList = new ArrayList<ReviewVo>();
		
		int pageNum = 1;
		int limit = LISTCOUNT;
		
		if(request.getParameter("pageNum") != null)
			pageNum = Integer.parseInt(request.getParameter("pageNum"));
		
		int proNo =Integer.parseInt(request.getParameter("proNo"));
		String orderBy = request.getParameter("revOrder");
				
		int total_record = dao.getListCount(proNo);
		
		reviewList = dao.getList(pageNum, limit, proNo, orderBy);
		
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
		MemberVo authUser = getAuthUser(request);
		if(authUser != null) {
		for(int i = 0 ; i <reviewList.size() ; i++) {
			if(dao.updateAvail(authUser.getMemNo(), reviewList.get(i).getRevNo()) == 1) {
				reviewList.get(i).setUavail(1);
			} else {
				reviewList.get(i).setUavail(0);
			}
		}
		}
		request.setAttribute("revlist", reviewList);
		request.setAttribute("revpageNum", pageNum);
		request.setAttribute("revtotal_page", total_page);
		request.setAttribute("revtotal_record", total_record);
		request.setAttribute("revOrder", orderBy);
		
	}
	
	public void requestQnaList(HttpServletRequest request) {
		QuestionDao qdao = QuestionDaoImpl.getInstance();
		List<QuestionVo> questionList = new ArrayList<QuestionVo>();
		
		int qnapageNum = 1;
		int qnalimit = LISTCOUNT;  
		
		if(request.getParameter("qnapageNum") != null)
			qnapageNum = Integer.parseInt(request.getParameter("qnapageNum"));
		
		int proNo =Integer.parseInt(request.getParameter("proNo"));
		
		int qnatotal_record = qdao.getListCount(proNo);
		questionList = qdao.getList(qnapageNum, qnalimit, proNo);

		int qnatotal_page;
		
		if(qnatotal_record % qnalimit == 0) {
			qnatotal_page = qnatotal_record / qnalimit;
			Math.floor(qnatotal_page);
		}
		else {
			qnatotal_page = qnatotal_record / qnalimit;
			Math.floor(qnatotal_page);
			qnatotal_page = qnatotal_page + 1;
		}

		request.setAttribute("questionList", questionList);
		request.setAttribute("qnapageNum", qnapageNum);
		request.setAttribute("qnatotal_page", qnatotal_page);
		request.setAttribute("qnatotal_record", qnatotal_record);
		request.setAttribute("qNo", questionList);
		
		
	}
	
	// 로그인 되어 있는 정보를 가져온다
	protected MemberVo getAuthUser(HttpServletRequest request) {
		HttpSession session = request.getSession();
		MemberVo authUser = (MemberVo) session.getAttribute("authUser");

		return authUser;
	}
}
	
