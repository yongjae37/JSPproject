<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="com.javaex.vo.OrderInfoVo"%>
<%
List<OrderInfoVo> list = (List<OrderInfoVo>) request.getAttribute("list");
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
<script type='text/javascript' charset='utf-8'
	src='https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js'></script>



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
								<div class="col-12">
									<h3>[주문자]</h3>
									<h3>[주문 번호]</h3>
									

								</div>
								<div class="col-12">
									<button type="button">배송 상태</button>
									<br>

								</div>

								<div class="col-12">
									<h3>주문 내역</h3>

								</div>

								<div class="table-wrapper">
									<table class="alt">
										<tbody>
											<tr>
												<td>가격</td>
												<td>영화 이름</td>
											</tr>
											<tr>
												<td>가격2</td>
												<td>영화 이름</td>
											</tr>

										</tbody>
										<tfoot>
											<tr>
												<td colspan="1"></td>
												<td>총 가격</td>
											</tr>
										</tfoot>
									</table>
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