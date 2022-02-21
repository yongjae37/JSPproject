<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
						<h1>내가 쓴 문의</h1>
					</header>

					<hr class="major" />

					<div class="row">
						<c:import url="/WEB-INF/views/includes/navigation3.jsp"></c:import>
						
					<div class="col-9 col-12-medium">
					<div class="row">
								<div class="col-6 col-12-small">
									<h4><a href="/mysiteB/qna?a=listmyqna">일반 문의</a></h4>
									<section class="split">
									<c:forEach items="${qlist }" var="vo">
										<section>
											<div class="contact-method" style="margin:0">
												<span class="icon solid alt"><img src="images/사람.png"
													alt="" style="max-width: 2em; height: auto;" /></span>
												<h6 style="margin:0">
													[${vo.type}]<a href="/mysiteB/qna?a=read&qnaNo=${vo.qnaNo}">${vo.title }</a><br>&nbsp&nbsp${vo.regDate }
												</h6>
											</div>
										</section>
										<hr class="major" style="margin: 20px 0"/>
									</c:forEach>	
									</section>
								</div>
								
								
								
								
								<div class="col-6 col-12-small">
									<h4><a href="/mysiteB/product?a=listmypqna">상품 문의</a></h4>
									<section class="split">
									<c:forEach items="${pqlist }" var="pvo">
										<section>
											<div class="contact-method" style="margin:0">
												<span class="icon solid alt"><img src="images/사람.png"
													alt="" style="max-width: 2em; height: auto;" /></span>
												<h6 style="margin:0">
													<a href="/mysiteB/product?a=readmypqna&qno=${pvo.qno}">${pvo.proName }</a><br>&nbsp&nbsp${pvo.qdate }
												</h6>
											</div>
										</section>
										<hr class="major" style="margin: 20px 0"/>
									</c:forEach>	
									</section>
								</div>

							</div>
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