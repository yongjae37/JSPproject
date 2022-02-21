<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page import="java.util.*"%>
<%@ page import="com.javaex.vo.*"%>

<!DOCTYPE HTML>
<html>
<head>
<title>공지사항 게시판</title>
<meta charset="UTF-8" />

<link rel="stylesheet" href="assets/css/main.css" />
<link rel="stylesheet" href="assets/css/noscript.css" />
</head>
<body class="is-preload">

	<!-- Wrapper -->
	<div id="wrapper">

		<c:import url="/WEB-INF/views/includes/header.jsp"></c:import>

		<!-- Main -->
		<div id="main" class="alt">

			<!-- One -->
			<section id="one">
				<div class="inner">
					<header class="major">
						<h1>공지사항</h1>
					</header>
					
					
					
					<!-- 
					
					<form id="search_form" action="/mysiteB/qna" method="post">
					<input type = "hidden" name = "a" value="list">
					<label class="hidden">검색 분류</label>
					<select name="t" style="width:200px;height:50px;" >
						<option ${param.t == "전체 문의"?"selected":"" } value="전체 문의">전체 문의</option>
						<option ${param.t == "회원 문의"?"selected":"" } value="회원 문의">회원 문의</option>
						<option ${param.t == "결제 문의"?"selected":"" } value="결제 문의">결제 문의</option>
						<option ${param.t == "배송 문의"?"selected":"" } value="배송 문의">배송 문의</option>
						<option ${param.t == "기타 문의"?"selected":"" } value="기타 문의">기타 문의</option>
					</select>				
						<input type="submit" value="검색">
					</form>
					
					 -->



					<div class="table-wrapper">
						<table style="vertical-align:middle">
							<thead>
								<tr>
									<th>제목</th>
									<th>${bboardVo.title }</th>
									<th></th>
								</tr>
							</thead>
							<thead>
								<tr>
									<th>작성자</th>
									<th>관리자</th>
									<th>${bboardVo.regDate }</th>
								</tr>
							</thead>
							<tbody>
								<tr style="height: 400px">
									<td colspan="3">${fn:replace(bboardVo.content, newLine, "<br>")}</td>
									<!-- 
									<td>Ante turpis integer aliquet porttitor.</td>
									<td>29.99</td>
									 -->
									
								</tr>
							</tbody>
							<thead>
								<tr>
									<th>▲ &nbsp 이전글</th>
									<c:choose>
										<c:when test="${bboardVo.bNo == '1' }">
											<th>이전글이 없습니다.</th>
											<th></th>
										</c:when>
										<c:otherwise>
											<th><a href="/mysiteB/bboard?a=read&bNo=${bboardVo2.bNo }">${bboardVo2.title }</a></th>
											<th>${bboardVo2.regDate }</th>
										</c:otherwise>
									</c:choose>
									
								</tr>
							</thead>
							<thead>
								<tr>
									<th><a href="">▼ &nbsp 다음글</a></th>
									<c:choose>
										<c:when test="${bboardVo3.bNo == '0' }">
											<th>다음글이 없습니다.</th>
											<th></th>
										</c:when>
										<c:otherwise>
											<th><a href="/mysiteB/bboard?a=read&bNo=${bboardVo3.bNo }">${bboardVo3.title }</a></th>
											<th>${bboardVo3.regDate }</th>
										</c:otherwise>
									</c:choose>
								</tr>
							</thead>
						</table>
					</div>

					
					<c:if test="${authUser.adminCk == '1'}" >
						<label class="actions" style="text-align:right"> <a
						href="/mysiteB/bboard?a=delete&bNo=${bboardVo.bNo}" class="button">삭제</a>
						
						<a
						href="/mysiteB/bboard?a=list" class="button primary">목록</a>
						</label>
					</c:if>
					
					
					
					

					<!-- Elements -->

					<div class="row gtr-200">
						<div class="col-6 col-12-medium"></div>
						<div class="col-6 col-12-medium"></div>
					</div>

				</div>
			</section>

		</div>


		<c:import url="/WEB-INF/views/includes/footer.jsp"></c:import>

	</div>

	<!-- Scripts -->
	<script src="assets/js/jquery.min.js"></script>
	<script src="assets/js/jquery.scrolly.min.js"></script>
	<script src="assets/js/jquery.scrollex.min.js"></script>
	<script src="assets/js/browser.min.js"></script>
	<script src="assets/js/breakpoints.min.js"></script>
	<script src="assets/js/util.js"></script>
	<script src="assets/js/main.js"></script>

</body>
</html>