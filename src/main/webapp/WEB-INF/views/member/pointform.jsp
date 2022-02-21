<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="java.util.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page import="com.javaex.vo.*"%>


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
						<h1>포인트 적립 내역</h1>
					</header>

					<hr class="major" />
					
					<div class="row">
						<c:import url="/WEB-INF/views/includes/navigation3.jsp"></c:import>
					
					
					<div class="col-9 col-12-medium">
					

					<div class="table-wrapper">
						<table>
						<h4>적립포인트 : ${pvo.point }  </h4>
						
						
						
						</table>
						<table>
							<thead>
								<tr>
									<th>번호</th>
									<th>설명</th>
									<th>적립 및 사용 내역</th>
									<th>잔액</th>
									<th>이용 날짜</th>

								</tr>
							</thead>
							<tbody>
								<c:forEach items="${list}" var="vo">
									<tr>
										<td>${vo.pNo}</td>
										<td>${vo.pDesc}</td>
									    <td>
									    <c:if test="${vo.history > 0}">
									    +${vo.history}
									    </c:if>
									    <c:if test="${vo.history < 0}">
									    ${vo.history}
									    </c:if></td>
									    <td>${vo.point}</td>
										<td>${vo.pDate}</td>

									</tr>
								</c:forEach>

							</tbody>
						</table>
						<div class="pager">
				<c:set var="page" value="${(empty param.p)?1:param.p }" />
				<c:set var="startNum" value="${page-(page-1)%5}"/> 
				<c:set var="lastNum" value="${fn:substringBefore(Math.ceil(count/5),'.') }"/>
								
				<div class="indexer margin-top " >
					<h6 class="hidden">현재 페이지</h3>
					<div>${(empty param.p)?1:param.p }/${lastNum} pages</div>
				
				</div>
				
				<ul class="-list- center">
					
						<c:if test="${startNum>1 }">
							<a class="btn btn-prev" href="/mysiteB/user?a=pointform&p=${startNum-1 }}">◀</a>
						</c:if>
						<c:forEach var="i" begin="0" end="4">
						<c:if test="${(startNum+i) <= lastNum}">	
							<a class="${(param.p==(startNum+i))?'selected':'' }"></a><a href="/mysiteB/user?a=pointform&p=${startNum+i}">${startNum+i}</a>						
						</c:if>
						</c:forEach>
						
						<c:if test="${startNum+4<lastNum }">
							<a href="/mysiteB/user?a=pointform&p=${startNum+5}"class="btn btn-next">▶</a>
						</c:if>
				</ul>
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