<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<% pageContext.setAttribute( "newLine", "\n" ); %>


<!DOCTYPE HTML>
<html>
<head>
<title>비공개 글</title>
<meta charset="utf-8" />

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
					</header>

					
						<h3>비공개 글입니다. 비밀번호를 입력하세요</h3>
						
						<form name="form" id="pass_form" action="/mysiteB/qna" method="post" >
						<input type="hidden" name="a" value="checkpass"/>
						<input type="hidden" name="qnapass" value="${QnaboardVo.pass }"/>
						<input type="password" name="password" value=""/>
						<input type="hidden" name="qnaNo" value="${QnaboardVo.qnaNo }"/>
						<input type="submit" id=enter value="확인" class="primary" />            
						</form>
						
						
						<div class="col-6 col-12-small">
								<ul class="actions stacked">
									<li style="text-align: right"><a href="/mysiteB/qna?a=list"
										class="button small">목록으로</a></li>
								</ul>
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