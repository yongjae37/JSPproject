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

<script type="text/javascript">
function id_search1() { //이름,핸드폰으로 '찾기' 버튼

	  var frm = document.form;

	  if (frm.name.value.length < 1) {
	   alert("이름을 입력해주세요");
	   frm.name.focus();
       return frm = false;
	  }

	  if (frm.phone.value.length <2 ) {
	   alert("핸드폰번호를 정확하게 입력해주세요");
	   frm.phone.focus();
       return frm = false;
	  }
	  
	  frm.submit()

}

</script>


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
						<h1>계정정보 찾기</h1>
					</header>


					<div class="table-wrapper">
						<table class=alt style="margin-bottom:0; ">
							<tbody>


								<tr>
									<td
										style="font-size: 15px; width: 50%; text-align: center; vertical-align: middle; border-bottom: none;"><a href="/mysiteB/user?a=findid" style="font-weight:bold">아이디 찾기</a></td>
									<td
										style="font-size: 15px; width: 50%; text-align: center; vertical-align: middle; border-bottom: none;"><a href="/mysiteB/user?a=findpass" style="font-weight:bold">비밀번호 찾기</a></td>
								</tr>
							</tbody>
						</table>
					</div>


					<div class="box" style="text-align: center; padding: 5% 25%">





						
						
							 
							
							<h3 style="text-align: left">아이디 찾기</h3>
								<form method="post" action="/mysiteB/user" name = "form">
									<input type="hidden" name="a" value="id" />
									<div class="fields">
										<div class="field half">
											<label for="name" style="text-align: left">이름</label> <input
												type="text" class="form-control" placeholder="Name" id="name"
												name="name" value="">
										</div>
										<div class="field">
											<label for="password" style="text-align: left">등록한
												휴대폰 번호</label> <input type="text" placeholder="Phonenumber"
												value="" name="phone" />
										</div>
										<div class="field">
											<button type="button" onclick="id_search1()" value="로그인">아이디 찾기</button>
										</div>



									</div>
								</form>
							
							
							 
								
						
						
								
								
					</div>











					<!-- 
						
						<ul class="actions fit">
							<li><br><b style="font-size: 32px">아직도 회원이 아니세요?</b><br> 회원가입을 하시면 회원에게만 제공되는 다양한 혜택과 이벤트에 참여하실 수
								있습니다. mysiteB 회원만의 특별한 혜택을 만나보세요</li>
							<li><br><a href="#" class="button primary fit" style="display: inline-block; width: 250px; float: right">회원가입</a></li>
						</ul>
						
						 -->









				</div>




			</section>



		</div>





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