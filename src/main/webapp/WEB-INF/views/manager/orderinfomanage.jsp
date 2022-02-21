<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
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

<script type="text/javascript">
	var orderNo = -1;
	var memNo = -1;
	function closeLayer(obj) {
		$(obj).parent().parent().hide();
	}
	function closeLayer1(obj) {
		var frm = document.form;
		$(obj).parent().parent().hide();
		formClick();
		frm.submit();
	}
	
	function runMyFunction(id , no) {
		orderNo = id;
		memNo = no;
		console.log(orderNo);
		console.log(memNo);
	}
	
	function formClick() {
		url = "/mysiteB/manager?a=change&orderNo=" + orderNo + "&memNo=" + memNo;
		document.getElementById("form_id").action = url;
		console.log(orderNo);
		console.log(memNo)
	}
	
	$(function() {

		/* 클릭 클릭시 클릭을 클릭한 위치 근처에 레이어가 나타난다. */
		$('.imgSelect').click(function(e) {
			var sWidth = window.innerWidth;
			var sHeight = window.innerHeight;

			var oWidth = $('.popupLayer').width();
			var oHeight = $('.popupLayer').height();

			// 레이어가 나타날 위치를 셋팅한다.
			var divLeft = e.clientX + 10;
			var divTop = e.clientY + 5;

			// 레이어가 화면 크기를 벗어나면 위치를 바꾸어 배치한다.
			if (divLeft + oWidth > sWidth)
				divLeft -= oWidth;
			if (divTop + oHeight > sHeight)
				divTop -= oHeight;

			// 레이어 위치를 바꾸었더니 상단기준점(0,0) 밖으로 벗어난다면 상단기준점(0,0)에 배치하자.
			if (divLeft < 0)
				divLeft = 0;
			if (divTop < 0)
				divTop = 0;

			$('.popupLayer').css({
				"top" : divTop,
				"left" : divLeft,
				"position" : "absolute"
			}).show();
		});

	});
</script>

<style>
.imgSelect {
	cursor: pointer;
	font-family: Helvetica Neue, Helvetica, Arial, sans-serif;
	letter-spacing: 2px;
	text-align: center;
	color: #f35626;
	background-image: -webkit-linear-gradient(92deg, #f35626, #feab3a);
	-webkit-background-clip: text;
	-webkit-text-fill-color: transparent;
	-webkit-animation: hue 10s infinite linear;
}

@
-webkit-keyframes hue {from { -webkit-filter:hue-rotate(0deg);
	
}

to {
	-webkit-filter: hue-rotate(-360deg);
}

}
.popupLayer {
	display: none;
	position: absolute;
	width: 350px;
	height: 150px;
	left: 448px;
	bottom: 62px;
	color: black;
	background-color: black;
	border-radius: 5px;
	padding: 12px 12.8px;
}

.popupLayer form {
	line-height: 100px;
	display: block;
	text-align: center;
}

.popupLayer div {
	position: absolute;
	top: 10px;
	right: 5px
}

.dropdown {
	position: relative;
	display: inline-block;
}

.dropdown-content {
	display: none;
	position: absolute;
	background-color: #f1f1f1;
	min-width: 160px;
	box-shadow: 0px 8px 16px 0px rgba(0, 0, 0, 0.2);
	z-index: 1;
}

.dropdown-content a {
	color: black;
	padding: 12px 16px;
	text-decoration: none;
	display: block;
}

.dropdown-content a:hover {
	background-color: #ddd;
}

.dropdown:hover .dropdown-content {
	display: block;
}
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
					<h1>주문관리</h1>
				</header>



				<hr class="major" />

				<!-- Elements -->
				<div class="col-6 col-12-medium">


					<!-- Lists -->
					<div class="row">


						<c:import url="/WEB-INF/views/includes/mnavigation.jsp"></c:import>

						<div class="col-9 col-12-medium">



							<div class="table-wrapper">
								<div class="dropdown">
									<h3 class="dropbtn">
										<a href="/mysiteB/manager?a=managelist">실시간 주문 내역</a>
									</h3>
									<div class="dropdown-content">
										<a href="/mysiteB/manager?a=before">배송 처리 전 내역</a> <a
											href="/mysiteB/manager?a=after">배송 완료 내역</a>
									</div>
								</div>
								<table>
									<thead>
										<tr>
											<th>시간</th>
											<th>주문자</th>
											<th>주문 내역</th>
											<th>주문 금액</th>
											<th>배송처리</th>
										</tr>
									</thead> 
									<tbody>
										<c:forEach items="${list}" var="vo">
											<tr>
												<td>${vo.orderDate}</td>
												<td><a
													href="/mysiteB/manager?a=specific&orderNo=${vo.orderNo}&memNo=${vo.memNo}">${vo.memId}</a></td>
												<td>${vo.proName}</td>
												<td>${vo.totalPrice}</td>
												<c:choose>
													<c:when test="${vo.orderComplete == '0'}">
														<td><button type="button" class="imgSelect"  onClick="runMyFunction(${vo.orderNo}, ${vo.memNo});">미배송</button></td>
													</c:when>
													<c:when test="${vo.orderComplete == '2'}">
														<td><button type="button">배송 완료</button></td>
													</c:when>
												</c:choose>


											</tr>


										</c:forEach>
									</tbody>

								</table>



							</div>



						</div>

					</div>

				</div>


			</div>
		</div>
	</div>
	<!--  //////////////////////////////////////  -->


	<div class="popupLayer">
		<div>
			<span onClick="closeLayer(this)"
				style="cursor: pointer; font-size: 1.5em; color: white" title="닫기">X</span>
		</div>
		<form method="post" 
			name="form" id="form_id">
			<h5>배송완료 처리 하시겠습니까?</h5>
			<button type="button" onClick="closeLayer1(this)">완료</button>
		</form>
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