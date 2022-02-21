<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="java.util.*"%>
<%@ page import="com.javaex.vo.*"%>
<!DOCTYPE HTML>

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
						<h1>주문완료</h1>
					</header>


					<div class="box" style="text-align: center">
						<img src="images/check.png" alt="" style="width: 100px; height: auto;" />
						<br>
						<br>
						<h3>주문이 성공적으로 완료되었습니다.</h3>
						<h5>주문하신 주문번호는 <i>${orderNo}</i>입니다.<br>주문하신 내역은 <i>마이페이지 > 주문내역</i>에서 확인하실 수 있습니다.</h5>
					</div>




					<div>

						<ul class="actions fit">
							<li><a href="/mysiteB/main" class="button primary fit" style="display: inline-block; width: 250px; float: right">홈으로</a></li>
							<li><a href="/mysiteB/orderInfo?a=list&memNo=${authUser.memNo}" class="button fit" style="display: inline-block; width: 250px">주문내역보기</a></li>
						</ul>

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