<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="java.util.*"%>
<%@ page import="com.javaex.vo.*"%>
<!DOCTYPE HTML>

<html>
<head>
<title>Elements - Forty by HTML5 UP</title>
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
						<h1>로그인</h1>
					</header>

					<hr class="major" />
					
					<div class="box" style="text-align: center">
					
						
								<form method="post" action="/mysiteB/user" style="padding: 50px">
								<h2 style="text-align:center">회원 로그인</h2>
								<input type="hidden" name="a" value="login" />
									<div class="fields">
										<div class="field half">
											<label for="name" style="text-align:left">아이디</label>
											<input type="text" class="form-control" placeholder="ID" id="id" name="memId" value="">
										</div>
										<div class="field">
											<label for="password" style="text-align:left">비밀번호</label>
											<input type="password" placeholder="Password" value="" name="memPass" />
										</div>
										
										<c:if test="${param.result eq 'fail' }"> 
									<P>로그인이 실패했습니다. 다시입력해주세요</P>
										</c:if>
										<c:if test="${param.result eq 'out' }"> 
									<P>탈퇴한 회원 입니다.</P>
										</c:if>
										<div class="field">
											<button  type="submit" value="로그인">로그인</button>
											<a href="/mysiteB/user?a=findid" style="text-align:center">아이디/비밀번호 찾기</a>
										</div>
									</div>
										
									
								</form>
							</div>
							
						





						<ul class="actions fit">
							<li><br><b style="font-size: 32px">아직도 회원이 아니세요?</b><br> 회원가입을 하시면 회원에게만 제공되는 다양한 혜택과 이벤트에 참여하실 수
								있습니다. mysiteB 회원만의 특별한 혜택을 만나보세요</li>
							<li><br><a href="#" class="button primary fit" style="display: inline-block; width: 250px; float: right">회원가입</a></li>
						</ul>

						

					




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