<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="com.javaex.vo.OrderInfoVo"%>
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
<script type='text/javascript' charset='utf-8'
	src='https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js'></script>



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
					<h1>관리자</h1>
				</header>



				<hr class="major" />

				<!-- Elements -->
				<div class="col-6 col-12-medium">


					<!-- Lists -->
					<div class="row">


						<c:import url="/WEB-INF/views/includes/mnavigation.jsp"></c:import>

						<div class="col-9 col-12-medium">



							<div class="table-wrapper">
								<div class="col-12">
									
									<p>작성자 <b>${QnaboardVo.memName}</b></p>
									


								</div>
								
								<div class="col-12">
								
									<p>제목 <b>${QnaboardVo.title }</b></p>
									


								</div>
								
								<div class="col-12">
								
									<p>질문 유형 <b>${QnaboardVo.type}</b></p>
									


								</div>




								<div class="box" style = "border: 5px solid white;">
									<p>${fn:replace(QnaboardVo.content, newLine,"<br>") }</p>
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
						
						<c:forEach items="${list }" var="vo">
							<br>
						<tr>	
							<c:if test="${authUser.adminCk != 1 }">
							<td></td>
							</c:if>
							
							<c:if test="${authUser.adminCk == 1 }">
							<td>
							<form name="deleteanswerform" method="post" action="/mysiteB/qna">
							<button class="button" type="submit" name="a" value="deleteAns" >삭제</button>
							<input type="hidden" name="qnaNo" value="${QnaboardVo.qnaNo }"/>
							<input type="hidden" name="ansNo" value="${vo.ansNo }"/>
							</form>
							</td>
							</c:if>
							
							
							<td>판매자</td>
							<td>${vo.answer }</td>
							<td>${vo.regDate }</td>
						</tr>
						</c:forEach>
						
						</tbody>
						
						<c:if test="${authUser.adminCk == 1 }">
						<form name="answerform" method="post" action="/mysiteB/qna">
						<div class="field">
							<label for="answer">답변</label>
									<textarea name="answer" id="content" rows="6"></textarea>
							<input type="hidden" name="qnaNo" value="${QnaboardVo.qnaNo }"/>
							<input type="hidden" name="memNo" value="${authUser.memNo }"/>
						</div>
						
						
							<ul class="actions stacked">
									
									<li><button class="button" type="submit"  name="a" value="managerwriteAns" >답변달기</button></li>
									</div>			
							</ul>
						</form>
						</c:if>
				
			</table>



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