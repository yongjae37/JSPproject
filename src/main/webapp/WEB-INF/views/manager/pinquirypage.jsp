<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="com.javaex.vo.OrderInfoVo"%>


<!DOCTYPE HTML>
<!--
	Forty by HTML5 UP
	html5up.net | @ajlkn
	Free for personal and commercial use under the CCA 3.0 license (html5up.net/license)
-->
<html>
<head>
<title>관리자페이지 - 상품문의</title>
<meta charset="utf-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, user-scalable=no" />
<link rel="stylesheet" href="assets/css/main.css" />
<link rel="stylesheet" href="assets/css/noscript.css" />
<script type='text/javascript' charset='utf-8'
	src='https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js'></script>



<script type="text/javascript">
	function closeLayer(obj) {
		$(obj).parent().parent().hide();
	}
	function closeLayer1(obj) {
		var frm = document.form;
		frm.submit();
		$(obj).parent().parent().hide();

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
					<h1>상품문의</h1>
				</header>

				<hr class="major" />

				<!-- Elements -->
				<div class="col-6 col-12-medium">

					<!-- Lists -->
					<div class="row">

						<c:import url="/WEB-INF/views/includes/mnavigation.jsp"></c:import>

						<div class="col-9 col-12-medium">

								<table>
									<thead>
										<tr>
											<th>문의번호</th>
											<th>상품명</th>
											<th>작성자</th>
											<th>날짜</th>
											<th>답변여부</th>
										</tr>
									</thead>

									<tbody>
									<c:forEach items="${qnaList}" var="qnaList">
										<tr>
										<th>${qnaList.qno}</th>
										<td><a href="/mysiteB/manager?a=specificpinquiry&qno=${qnaList.qno}">${qnaList.proName}</a></td>
										
										<td>${qnaList.memId}</td>
										<td>${qnaList.qdate}</td>
										
										<c:choose>
											<c:when test="${qnaList.alist != '[]'}">
   	 											<td>답변 완료</td>
   	 											
  											</c:when>
											<c:otherwise>
    											<td>미답변</td>
  											</c:otherwise>
										</c:choose>
										</tr>
									</c:forEach>
									</tbody>

								</table>
								<c:set var="qnapageNum" value="${qnapageNum}" />
							<c:forEach var="i" begin="1" end="${qnatotal_page}">
								<a
									href="/mysiteB/manager?a=pinquiry&qnapageNum=${i}">
									<c:choose>
										<c:when test="${qnapageNum==i}">
											<font color='4C5317'><b> [${i}]</b></font>
										</c:when>
										<c:otherwise>
											<font color='4C5317'> [${i}]</font>
										</c:otherwise>
									</c:choose>
								</a>
							</c:forEach>
								<div class="show" title="미답변 정렬">
									<a href="#" class="button primary fit"
										style="display: inline-block; width: 100px; float: right">답변</a>
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