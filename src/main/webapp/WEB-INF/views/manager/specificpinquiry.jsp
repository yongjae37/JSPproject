<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<% pageContext.setAttribute( "newLine", "\n" ); %>
<!DOCTYPE HTML>
<html>
<head>
<title>상품 문의글</title>
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
						<h1>상품 문의글</h1>
					</header>
					<hr class="major" />
					<div class="col-6 col-12-medium">
					<div class="row">
					<c:import url="/WEB-INF/views/includes/mnavigation.jsp"></c:import>
					<div class="col-9 col-12-medium">
					
					
					<form>

								<h3>문의 번호	${qVo.qno }</h3>
					
							<div class="col-6 col-12-small">
								<ul class="actions stacked">
									<li style="text-align: right"><a href="/mysiteB/manager?a=pinquiry"
										class="button small">목록으로</a></li>
								</ul>
							</div>
					</form>
					
						
						
						<h3>작성자 아이디 ${qVo.memId}</h3>
						
						
						<h3>작품 이름 ${qVo.proName}</h3>
						<h3>작성 일자 ${qVo.qdate}</h3>

						<div class="box">
							<p>${fn:replace(qVo.qcontent, newLine,"<br>") }</p>
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
							<c:forEach items="${alist }" var="aVo">
						<tr>	
							<td>
							<form name="deleteanswerform" method="post" action="/mysiteB/manager">
							<button class="button" type="submit" name="a" value="deletepAnswer" >삭제</button>
							<input type="hidden" name="qNo" value="${aVo.qNo }"/>
							<input type="hidden" name="aNo" value="${aVo.aNo }"/>
							</form>
							</td>
							<td>판매자</td>
							<td>${aVo.aContent }</td>
							<td>${aVo.aDate }</td>
						</tr>
						
							</c:forEach>
						</tbody>
						
						<c:if test="${authUser.adminCk == 1 }">
						<form name="answerform" method="post" action="/mysiteB/manager">
						<div class="field">
							<label for="answer">답변</label>
									<textarea name="answerContent" id="content" rows="6"></textarea>
							<input type="hidden" name="qNo" value="${qVo.qno }"/>
							<input type="hidden" name="memNo" value="${authUser.memNo }"/>
						</div>
						
						
							<ul class="actions stacked">
									
									<li><button class="button" type="submit" name="a" value="insertAnswer" >답변달기</button></li>
										
							</ul>
						</form>
						</c:if>
						
							
			</table>
			
						
			</div>
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