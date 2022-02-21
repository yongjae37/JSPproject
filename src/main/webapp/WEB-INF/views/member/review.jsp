<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="java.util.*"%>
<!DOCTYPE HTML>
<!--
	Forty by HTML5 UP
	html5up.net | @ajlkn
	Free for personal and commercial use under the CCA 3.0 license (html5up.net/license)
-->
<html>
<head>
<title>Elements - Forty by HTML5 UP</title>
<meta charset="utf-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, user-scalable=no" />
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
						<h1>내가 쓴 후기</h1>
					</header>

					<hr class="major" />
					
					<div class="row">
						<c:import url="/WEB-INF/views/includes/navigation3.jsp"></c:import>
					
					<div class="col-9 col-12-medium">

					<div class="table-wrapper">
						<table>
							<thead>
								<tr>
									<th>번호</th>
									<th>제목</th>
									<th>상품</th>
									<th>작성일</th>
									<th>조회수</th>
								</tr>
							</thead>
							
							<tbody>
								<c:forEach items="${revlist}" var="revlist">
								<tr>
									<td>${revlist.revNo}</td>
									<td><a href="/mysiteB/product?a=readmyreview&revNo=${revlist.revNo}">${revlist.title}</a></td>
									<td>${revlist.proName}</td>
									<td>${revlist.revDate}</td>
									<td>${revlist.revHit}</td>
									

								</tr>
								</c:forEach>
								
								<tr>
									<form class="form-signin" id="orderlist-form" name="reviewform"
										method="get" action="/mysiteB/user">
										<input type="hidden" name="a" value="review" /> 
										<input type="hidden" name="no" value="${authUser.memId}" />


									</form>
								</tr>
							</tbody>
						</table>
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