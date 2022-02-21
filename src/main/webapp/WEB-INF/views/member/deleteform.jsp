<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML>
<!--
	Forty by HTML5 UP
	html5up.net | @ajlkn
	Free for personal and commercial use under the CCA 3.0 license (html5up.net/license)
-->
<html>
	<head>
		<title>Generic - Forty by HTML5 UP</title>
		<meta charset="utf-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no" />
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
										<h1>회원탈퇴</h1>
										<h4>아이디와 비밀번호를 입력해주세요.</h4>
									</header>
									
									<hr class="major" />
					
					<div class="row">
						<c:import url="/WEB-INF/views/includes/navigation3.jsp"></c:import>
					
					
					<div class="col-9 col-12-medium">
							<div class="box" >
								
								<form method="post" action="/mysiteB/user" style="padding: 50px"> 
								<h4 style="text-align:center">아이디와 비밀번호를 입력해주세요.</h4>
								<input type="hidden" name="a" value="delete" />
									<div class="fields">
										<div class="field half">
											<label for="name">아이디</label>
											<input type="text" class="form-control" placeholder="ID" id="id" name="memId" value="">
										</div>
										
										<div class="field">
											<label for="password">비밀번호</label>
											<input type="password" placeholder="Password" value="" name="memPass" />
										</div>
										
										<c:if test="${param.result eq 'fail' }"> 
									<P>아이디와 비밀번호를 다시 확인해주세요.</P>
										</c:if>
									
										
									</div>
									
									<div class="field" style="text-align:center">
											<button  type="submit" value="회원탈퇴">회원탈퇴</button>
										</div>
									
								</form>
								
									</div>
				</div>
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