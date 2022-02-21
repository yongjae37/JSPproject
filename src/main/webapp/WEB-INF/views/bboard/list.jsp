<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page import="java.util.*"%>
<%@ page import="com.javaex.vo.*"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="com.javaex.vo.OrderInfoVo"%>
<%
List<OrderInfoVo> list = (List<OrderInfoVo>) request.getAttribute("list");
%>


<!DOCTYPE HTML>
<html>
<head>
<title>공지사항 게시판</title>
<meta charset="UTF-8" />

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
					<h1>공지사항</h1>
				</header>

				<hr class="major" />

				<!-- Elements -->
				<div class="col-6 col-12-medium">
					<!-- Lists -->
					
					<div class="row">
					<c:if test="${authUser.adminCk == 1}">
						<c:import url="/WEB-INF/views/includes/mnavigation.jsp"></c:import>
					</c:if>
						<div class="col-9 col-12-medium">
							<div class="table-wrapper">
								<div class="row">

									<div class="col-6 col-12-small">
										<h3>
											<a href="/mysiteB/bboard?a=list">공지사항</a>
										</h3>
									</div>

									<div class="col-6 col-12-small">
										<ul class="actions stacked">
										<c:choose>
										<c:when test="${authUser.adminCk == 1}">
										<li style="text-align: right"><a
												href="/mysiteB/bboard?a=writeform" class="button small">작성</a></li>
										</c:when>
										<c:otherwise>
										<li style="text-align: right; display: none;"><a
												href="/mysiteB/bboard?a=writeform" class="button small">작성</a></li>
										</c:otherwise>
										</c:choose>
											
										</ul>
									</div>

								</div>

								<table>
									<thead>
										<tr>
											<th>번호</th>
											<th>제목</th>
											<th>작성일</th>
										</tr>
									</thead>
							<tbody>

								<c:forEach items="${bboardList }" var="vo">
									<tr>
										<td>${vo.bNo }</td>
										
										<td><a href="/mysiteB/bboard?a=read&bNo=${vo.bNo }">${vo.title }</a></td>
										
										<td>${vo.regDate }</td>
									</tr>
								</c:forEach>
							</tbody>
							
						</table>
									
						<ul class="pagination" style="text-align: center">
									<c:set var="pageNum" value="${bpageNum}" />
									<c:forEach var="i" begin="1" end="${btotal_page}">
										<a href="/mysiteB/bboard?a=list&pageNum=${i}"> 
										<c:choose>
												<c:when test="${bpageNum==i}">
													<font color='4C5317'><b> [${i}]</b></font>
												</c:when>
												<c:otherwise>
													<font color='4C5317'> [${i}]</font>
												</c:otherwise>
											</c:choose>
										</a>
									</c:forEach>

								</ul>

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