<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>



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
			<div class="inner">
			<!-- One -->
			<section id="one">
				<div class="inner">
					<header class="major">
						<h1>내 정보</h1>
					</header>

					<hr class="major" />

					<!-- Elements -->

					<div class="row">
						<c:import url="/WEB-INF/views/includes/navigation3.jsp"></c:import>
								
						<div class="col-6 col-12-medium">
						
							<h4>최근 주문 내역</h4>
							<div class="row" style="margin-bottom: 64px">
								<div class="col-3 col-3-small">
										<c:if test="${not empty fn:trim(recent.proFileName)}">
											<img src="./upload/${recent.proFileName }"
												style="width:100%"/>
										</c:if>
									</div>
								<div class="col-9 col-12-medium">
									<div class="table-wrapper">
								<table>
									<thead>
										<tr>
											<th>주문일</th>
											<th>결제금액</th>
											<th>배송현황</th>
										</tr>
									</thead>
									<tbody>
										<tr>
											<form class="form-signin" id="mypage-form" name="mypageform"
												method="get" action="/mysiteB/user">
												<input type="hidden" name="a" value="mypage" /> 
												<input type="hidden" name="no" value="${authUser.memId}" />
								

												<c:forEach var="item" items="${olist}">
													<tr>
														<td><c:out value="${item.orderDate}" /></td>
														<td><c:out value="${item.totalPrice}" /></td>
													<c:choose>
														<c:when test="${item.orderComplete == 0}">
															<td>배송준비</td>
														</c:when>
														<c:when test="${item.orderComplete == 1}">
															<td>배송중</td>
														</c:when>
														<c:when test="${item.orderComplete == 2}">
															<td>배송완료</td>
														</c:when>
													</c:choose>
														<td><c:out value="" /></td>
													</tr>
											</c:forEach>
										</form>
										</tr>
										</tbody>
								</table>
								
							
							
							
							
							</div>

						</div>
					</div>

							<div class="row">
											
										</div>

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