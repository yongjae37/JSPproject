<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<% pageContext.setAttribute( "newLine", "\n" ); %>
<!DOCTYPE HTML>
<html>
<head>
<title>내가 쓴 후기</title>
<meta charset="utf-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, user-scalable=no" />
<link rel="stylesheet" href="assets/css/main.css" />
<link rel="stylesheet" href="assets/css/noscript.css" />
<link rel="icon" href="images/favicon.ico" type="image/x-icon">
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
						<h1>내가 쓴 후기</h1>
					</header>

				<form>

						<h3>제목	${reviewVo.title }</h3>
					
							<div class="col-6 col-12-small">
								<ul class="actions stacked">
									<li style="text-align: right"><a href="/mysiteB/user?a=review"
										class="button small">목록으로</a></li>
								</ul>
							</div>
	
						<h3>작성일자${reviewVo.revDate}</h3>
						<h3>조회수${reviewVo.revHit}</h3>
						<h3>영화제목${reviewVo.proName}</h3>

						<div class="box">
							<p>${fn:replace(reviewVo.content, newLine,"<br>") }</p>
						</div>
						
					

						<div class="col-6 col-12-xsmall">
						<form name="modifyform" method="post" action="/mysiteB/product">
							<ul class="actions stacked">
						
									<input type="hidden" name="revNo" value="${reviewVo.revNo }"/>
									<li><button class="button" type="submit"  name="a" value="updateRevform" >리뷰 수정</button></li>
									<li><button class="button primary" type="submit"  name="a" value="deleteRevform" >리뷰 삭제</button></li>
								
							</ul>
						</form>
								</div>
						
						</form>
						</div>					
					<!-- Elements -->

					<div class="row gtr-200">
						<div class="col-6 col-12-medium"></div>
						<div class="col-6 col-12-medium"></div>
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