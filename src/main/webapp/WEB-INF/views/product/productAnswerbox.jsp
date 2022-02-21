<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
  int qNo = ((Integer) request.getAttribute("qNo")).intValue();
	
%>

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
<meta name="viewport"
	content="width=device-width, initial-scale=1, user-scalable=no" />
<link rel="stylesheet" href="assets/css/main.css" />
<link rel="stylesheet" href="assets/css/noscript.css" />
</head>
<body class="is-preload">

	<!-- Wrapper -->
	<div id="wrapper">


		<form class="form-signin" id="insert-form" name="insertform" method="post" action="/mysiteB/product?a=insertAnswer&qNo=${qNo }" >
			<input type="hidden" name="qNo" value="${qNo}"/>
			<section id="one">
				<div class="inner">
					<div class="table-wrapper">
						<table class=alt>
							<tbody>
								<tr>
									<td style="font-size: 14px; text-align: center; vertical-align: middle"><b>질문 작성자</b></td>
									<td style="font-size: 12px; vertical-align: middle">${qvo.memId}</td>
									
								</tr>
								<tr>
									<td style="font-size: 14px; text-align: center; vertical-align: middle"><b>상품명</b></td>
									<td style="font-size: 12px; vertical-align: middle">${qvo.proName}</td>
									
								</tr>
								<tr>
									<td style="font-size: 14px; width: 100px; text-align: center; vertical-align: middle"><b>질문 내용</b></td>
									<td style="font-size: 12px; vertical-align: middle">${qvo.qcontent }</td>
								</tr>
								<tr>
									<td style="font-size: 14px; width: 100px; text-align: center; vertical-align: middle"><b>답변내용</b></td>
									<td style="font-size: 12px; vertical-align: middle;  padding: 0;">
										<textarea name="answerContent" id="demo-message" placeholder="Enter your message" rows="6" style="font-size:12px;" ></textarea>
									</td>
								</tr>
						</tbody>
						</table>
					</div>

				<div class="row" style="text-align:center">
					
					<input style="display: block; margin: 0 auto;"
                    type="submit" value="등록" class="button primary icon solid fa-search" />
				</div>		
				

			</div>

			</section>
			</form>	
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