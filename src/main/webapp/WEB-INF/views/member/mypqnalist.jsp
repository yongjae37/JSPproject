<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page import="java.util.*"%>
<%@ page import="com.javaex.vo.*"%>

<!DOCTYPE HTML>
<html>
<head>
<title>내 문의글 목록</title>
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
						<h1>내가 작성한 상품 문의</h1>
					</header>
					
					<div class="table-wrapper">
						<table>
							<thead>
								<tr>
									<th>번호</th>
									<th>영화이름</th>
									<th>작성일</th>
								</tr>
							</thead>
							<tbody>

								<c:forEach items="${list}" var="vo">
									<tr>
										<td>${vo.qno}</td>
										<td><a href="/mysiteB/product?a=readmypqna&qno=${vo.qno}">${vo.proName}</a>
										<td>${vo.qdate}</td>
										
									</tr>
								</c:forEach>
							</tbody>
							
						</table>
						
			<div class="pager">
				<c:set var="page" value="${(empty param.p)?1:param.p }" />
				<c:set var="startNum" value="${page-(page-1)%5}"/> 
				<c:set var="lastNum" value="${fn:substringBefore(Math.ceil(count/5),'.') }"/>
								
				<div class="indexer margin-top " >
					<h6 class="hidden">현재 페이지</h3>
					<div>${(empty param.p)?1:param.p }/${lastNum} pages</div>
				
				</div>
				
				<ul class="-list- center">
					
						<c:if test="${startNum>1 }">
							<a class="btn btn-prev" href="/mysiteB/product?a=readmypqna&p=${startNum-1 }}">◀</a>
						</c:if>
						<c:forEach var="i" begin="0" end="4">
						<c:if test="${(startNum+i) <= lastNum}">	
							<a class="${(param.p==(startNum+i))?'selected':'' }"></a><a href="/mysiteB/product?a=readmypqna&p=${startNum+i}">${startNum+i}</a>						
						</c:if>
						</c:forEach>
						
						<c:if test="${startNum+4<lastNum }">
							<a href="/mysiteB/product?a=readmypqna&p=${startNum+5}"class="btn btn-next">▶</a>
						</c:if>
				</ul>
			</div>
				
						
						
					</div>
					
					


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