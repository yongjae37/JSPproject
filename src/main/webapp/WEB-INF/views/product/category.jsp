<%@page import="com.javaex.dao.ReviewDaoImpl"%>
<%@page import="com.javaex.dao.ReviewDao"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="java.util.*"%>
<%
List productList = (List) request.getAttribute("list");
int total_record = ((Integer) request.getAttribute("total_record")).intValue();
int pageNum = ((Integer) request.getAttribute("pageNum")).intValue();
int total_page = ((Integer) request.getAttribute("total_page")).intValue();


%>
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

<link rel="icon" href="images/favicon.ico" type="image/x-icon">

<script>
	var index = 0; //이미지에 접근하는 인덱스
	window.onload = function() {
		slideShow();
	}

	function slideShow() {
		var i;
		var x = document.getElementsByClassName("slide1"); //slide1에 대한 dom 참조
		for (i = 0; i < x.length; i++) {
			x[i].style.display = "none"; //처음에 전부 display를 none으로 한다.
		}
		index++;
		if (index > x.length) {
			index = 1; //인덱스가 초과되면 1로 변경
		}
		x[index - 1].style.display = "block"; //해당 인덱스는 block으로
		setTimeout(slideShow, 4000); //함수를 4초마다 호출

	}
</script>

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
						<h1>카테고리</h1>
					</header>

					<hr class="major" />

					<!-- Elements -->
					<div class="col-6 col-12-medium">

						<!-- Lists -->
						<div class="row">
							<c:import url="/WEB-INF/views/includes/navigation.jsp"></c:import>
							<div class="col-9 col-12-medium">
							
							
								<h4>신규 상품</h4>
								<span class="image fit">
								
									<img class="slide1" src="images/공각기동대.jpg" data-transition=“slide”> 
									<img class="slide1" src="images/엣지.jpg" data-transition=“slide”> 
									<img class="slide1" src="images/이퀼리브리엄.jpg" data-transition=“slide”>
									<img class="slide1" src="images/퍼시픽림.jpg" data-transition=“slide”>
								
								</span>
								<div class="box alt">

									<form method="post" action="/mysiteB/product">
										<input type="hidden" name="a" value="productList" />

										<div class="row gtr-50 gtr-uniform">
											<c:forEach items="${list}" var="vo">
												<div class="col-4">
													<span class="image fit"><img
														src="./upload/${vo.proFileName }" alt="" />
														<h4>
															<a href="/mysiteB/product?a=readProduct&proNo=${vo.proNo}">${vo.proName }</a>
														</h4> 
														
														<c:choose>
															<c:when test="${vo.proRate >= 0 && vo.proRate < 1}">
																☆☆☆☆☆
															</c:when>
															<c:when test="${vo.proRate >= 1 && vo.proRate < 2}">
																★☆☆☆☆
															</c:when>
															<c:when test="${vo.proRate >= 2 && vo.proRate < 3}">
																★★☆☆☆
															</c:when>
															<c:when test="${vo.proRate >= 3 && vo.proRate < 4}">
																★★★☆☆
															</c:when>
															<c:when test="${vo.proRate >= 4 && vo.proRate < 5}">
																★★★★☆
															</c:when>
															<c:otherwise>
																★★★★★
															</c:otherwise>
														</c:choose>
														
														<h5>${vo.proPrice }원</h5></span>

												</div>
											</c:forEach>
										</div>

									</form>
								
								<c:set var="pageNum" value="<%=pageNum%>" />
								<c:forEach var="i" begin="1" end="<%=total_page%>">
									<a href="/mysiteB/product?a=productList&pageNum=${i}">
										<c:choose>
											<c:when test="${pageNum==i}">
												<font color='4C5317'><b> [${i}]</b></font>
											</c:when>
											<c:otherwise>
												<font color='4C5317'> [${i}]</font>
											</c:otherwise>
										</c:choose>
									</a>
								</c:forEach>
								</div>


							</div>
						</div>
					</div>
				</div>
			</section>
		</div>
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