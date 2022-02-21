<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="java.util.*"%>
<%@ page import="com.javaex.vo.*"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.util.Enumeration"%>
<%@page import="java.util.Random"%>
<%@ page import="javax.servlet.http.HttpServletResponse" %>

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
<script src="http://code.jquery.com/jquery-latest.js"></script>

<script>
	$(function() {	

	     $("#btn-checkid").on("click", function(){	 
	    	 var frm = document.form;

	   	  if (frm.name.value.length < 1) {
	   	   alert("이름을 입력해주세요");
	   	   frm.name.focus();
	          return frm = false;
	   	  }

	   	  if (frm.memId.value.length <2 ) {
	   	   alert("이메일(아이디)를 입력해주세요");
	   	   frm.phone.focus();
	          return frm = false;
	   	  }
	    	 
	    	 var str = "";
	    	 var i = 0;
	  	     for(i=0;i<10;i++)
	  	     {
	  	         var rIndex = Math.floor(Math.random()*3); 
	  	         switch (rIndex) {
	  	         case 0:
	  	             // a-z
	  	             str += String.fromCharCode(Math.floor(Math.random()*26) + 97);
	  	             break;
	  	         case 1:
	  	             // A-Z
	  	             str += String.fromCharCode(Math.floor(Math.random()*26) + 65);
	  	             break;
	  	         case 2:
	  	             // 0-9
	  	             str += Math.floor(Math.random()*10);
	  	             break;
	  	         }
	  	     }

	  	   document.getElementById("number").value = str;
	  	   
	       // json 형식으로 데이터 set
	       var params = {
	                 a      : "Findpass"
	               , name : $("[name=name]").val()
	,               memId  : $("[name=memId]").val()
					, number : $("[name=number]").val()
	       }
	    
	       $.ajax({
	         url : "api/Findpass.jsp",
	         type : "post",
	         data : params,
	         dataType : "json",
	         success : function(isExist) {
	           console.log(isExist);
	           if(isExist == true){
	             alert("임시 비밀 번호가 발송되었습니다");
	             $('#noneDiv').show();
	             
	             
	           }else {
	             alert("잘못된 정보 입력")
	           }
	         },
	         error : function(XHR, status, error) {
	             console.error(status + " : " + error);
	           }
	         });
	       });

	});
	
	

	
</script>

<script type="text/javascript">
	//호출 후 결과 확인
	function emailAuthentication() {
		if (!emailValCheck()) {
			return false;
		}
		var url = "confirmEmail.four?email=" + document.signUpForm.email.value;
		open(
				url,
				"confirm",
				"toolbar=no, location=no,menubar=no,scrollbars=no,resizable=no,width=300,height=200");
	}

	//입력한 값이 유효한지 검사
	const form = document.signUpForm;

	function emailValCheck() {
		var emailPattern = /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i;
		var email = form.email.value;
		if (!check(emailPattern, email, "유효하지 않은 이메일 주소입니다.")) {
			return false;
		}
		return true;
	}

	//
	function check(pattern, taget, message) {
		if (pattern.test(taget)) {
			return true;
		}
		alert(message);
		taget.focus();
		return false;
	}
	
	function mailcheck() {
		var f = document.form; 
		f.submit();
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
						<table class=alt style="margin-bottom: 0;">
							<tbody>


								<tr>
									<td
										style="font-size: 15px; width: 50%; text-align: center; vertical-align: middle; border-bottom: none;"><a
										href="/mysiteB/user?a=findid" style="font-weight: bold">아이디
											찾기</a></td>
									<td
										style="font-size: 15px; width: 50%; text-align: center; vertical-align: middle; border-bottom: none;"><a
										href="/mysiteB/user?a=findpass" style="font-weight: bold">비밀번호
											찾기</a></td>
								</tr>
							</tbody>
						</table>
					</div>
					





					<div class="box" style="text-align: center; padding: 5% 25%">









						<h3 style="text-align: left">비밀번호 찾기</h3>
						<form method="post" action="/mysiteB/user" name = "form">
						<input type="hidden" name="a" value="wishform" />
							<div class="fields">
								<div class="field half">
									<label for="name" style="text-align: left">이름</label> <input
										type="text" class="form-control" placeholder="Name" id="name"
										name="name" value="">
								</div>
								<div class="field">
									<label for="password" style="text-align: left">아이디(이메일)</label>
									<input type="text" placeholder="ID(Email)" value=""
										name='memId' id = "memId"/>
								</div>
								
								<div class="field">
									<button type="button" class="btnChk" id="btn-checkid"
										name="btn-checkid" style="font-weight: bold">임시 비밀번호 발송</button>
								</div>


								<div class="field" id="noneDiv" style="display: none;">
									<button onclick="mailcheck()" id="authCodeCheckBtn"
										type="button" class="btnChk">확인</button>
										<input type="hidden" name="number" id = "number" value = "" />
										

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