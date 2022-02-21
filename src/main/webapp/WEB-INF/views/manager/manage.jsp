<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
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


<script>

</script>

<style>
</style>
</head>
<body class="is-preload">

	<!-- Wrapper -->
	<div id="wrapper">


		<c:import url="/WEB-INF/views/includes/header.jsp"></c:import>
		<!-- Main -->
		<div id="main" class="alt">

			<!-- One -->
			<div class="inner">
				<header class="major">
					<h1>관리자</h1>
				</header>



				<hr class="major" />

				<!-- Elements -->
				<div class="col-6 col-12-medium">


					<!-- Lists -->
					<div class="row">


						<c:import url="/WEB-INF/views/includes/mnavigation.jsp"></c:import>


						<div class="col-9 col-12-medium">

							<div class="table-wrapper">
								<h4>오늘의 할 일 &nbsp 5</h4>
								<table>
									<tbody>
										<tr>
											<td><a href="">신규주문 &nbsp ${newCount}</a></td>
											<td><a href="/mysiteB/manager?a=pinquiry">답변대기 상품문의 &nbsp ${pcount }</a></td>
											<td><a href="/mysiteB/qna?a=listNoAnswer">답변대기 일반문의 &nbsp ${noanswercount }</a></td>
										</tr>
									</tbody>
								</table>
							</div>

							<div class="row">
								<div class="col-6 col-12-small">
									<h4>일반 문의</h4>
									<section class="split">
									<c:forEach items="${qblist }" var="vo">
										<section>
											<div class="contact-method" style="margin:0">
												<span class="icon solid alt"><img src="images/사람.png"
													alt="" style="max-width: 2em; height: auto;" /></span>
												<h6 style="margin:0">
													[${vo.type}]<a href="/mysiteB/qna?a=read&qnaNo=${vo.qnaNo}">${vo.title }</a><br>${vo.memName }&nbsp&nbsp${vo.regDate }
												</h6>
											</div>
										</section>
										<hr class="major" style="margin: 20px 0"/>
									</c:forEach>	
									</section>
								</div>
								
								<div class="col-6 col-12-small">
									<h4>공지사항</h4>
									<section class="split">
										<c:forEach items="${bblist }" var="vo">
											<section>
												<div class="contact-method" style="margin:0; padding:0">
													<h6 style="margin:0">
														<a href="/mysiteB/bboard?a=read&bNo=${vo.bNo }">${vo.title }</a>
														<br>관리자 &nbsp&nbsp ${vo.regDate }
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

				</div>

			</div>


		</div>

	</div>





	<c:import url="/WEB-INF/views/includes/footer.jsp"></c:import>


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