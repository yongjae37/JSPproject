<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML>
<!--
	Forty by HTML5 UP
	html5up.net | @ajlkn
	Free for personal and commercial use under the CCA 3.0 license (html5up.net/license)
-->
<html>
<head>
<title>문의글 남기기</title>
<meta charset="utf-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, user-scalable=no" />
<link rel="stylesheet" href="assets/css/main.css" />
<link rel="stylesheet" href="assets/css/noscript.css" />

<script>
	function check_title() {
		var f = document.form;
		if (f.title.value == "") {
			alert("제목을 입력해주세요");
			f.title.focus();
			return false;
		}
		f.submit();
	}
</script>
</head>
<body class="is-preload">

	<!-- Wrapper -->
	<div id="wrapper">

		<c:import url="/WEB-INF/views/includes/header.jsp"></c:import>


		<!-- Contact -->
		<section id="contact">
			<div class="inner">
				<section>
					<form name="form" method="post" action="/mysiteB/bboard">
						<input type="hidden" name="a" value="write" />

						<div class="fields">

							<div class="field">
								<label for="title">제목</label> <input type="text" name="title"
									id="title" />
							</div>
							<div class="field">
								<label for="content">내용</label>
								<textarea name="content" id="content" rows="6"></textarea>
							</div>


							<div class="field" style="text-align: right">
								<ul class="actions">
									<li><input type="button" value="등록" class="primary" onclick="check_title()"/></li>
								</ul>
							</div>


						</div>
					</form>
				</section>
				
			</div>
		</section>

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