<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<% pageContext.setAttribute( "newLine", "\n" ); %>
<!DOCTYPE HTML>
<html>
<head>
<title>내가 쓴 상품문의</title>
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
						<h1>내가 쓴 상품문의</h1>
					</header>

					<form>

								<h3>영화제목	${qVo.proName }</h3>
					
							<div class="col-6 col-12-small">
								<ul class="actions stacked">
									<li style="text-align: right"><a href="/mysiteB/product?a=listmypqna"
										class="button small">목록으로</a></li>
								</ul>
							</div>
						<h3>작성자 아이디 ${qVo.memId}</h3>
				
						<div class="box">
							<p>${fn:replace(qVo.qcontent, newLine,"<br>") }</p>
						</div>
							
						</div>			
				</div>
				
			<table>
				<tbody>
						<tr>
							<td style="font-size: 14px; width: 100px; text-align: center; vertical-align: middle"><b>↳답변</b></td>
							<td style="font-size: 12px; width: 120px; text-align: center; vertical-align: middle"><b>작성자</b></td>
							<td style="font-size: 12px; width: 200px; text-align: center; vertical-align: middle"><b>답변 내용</b></td>
							<td style="font-size: 12px; width: 120px; text-align: center; vertical-align: middle"><b>날짜</b></td>
						</tr>
							<br>
						<tr>	
							<td></td>
							<td>판매자</td>
							<td>${aVo.aContent }</td>
							<td>${aVo.aDate }</td>
						</tr>
						
						</tbody>
						
			</table>
			
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