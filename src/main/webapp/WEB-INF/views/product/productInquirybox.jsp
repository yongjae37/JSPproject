<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
  int proNo = ((Integer) request.getAttribute("proNo")).intValue();
	
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


		<form class="form-signin" id="insert-form" name="insertform" method="post" action="/mysiteB/product?a=insertQuestion&proNo=<%=proNo %>" >
			<input type="hidden" name="proNo" value="${proNo}"/>
			<section id="one">
				<div class="inner">
					<div class="table-wrapper">
						<table class=alt>
							<tbody>
								<tr>
									<td style="font-size: 14px; text-align: center; vertical-align: middle"><b>카테고리</b></td>
									<td style="font-size: 12px; vertical-align: middle">${pvo.proCateg }</td>
									
								</tr>
								<tr>
									<td style="font-size: 14px; width: 100px; text-align: center; vertical-align: middle"><b>상품 이름</b></td>
									<td style="font-size: 12px; vertical-align: middle">${pvo.proName }</td>
								</tr>
								<tr>
									<td style="font-size: 14px; width: 100px; text-align: center; vertical-align: middle"><b>문의내용</b></td>
									<td style="font-size: 12px; vertical-align: middle;  padding: 0;">
										<textarea name="askContent" id="demo-message" placeholder="Enter your message" rows="6" style="font-size:12px;" ></textarea>
									</td>
								</tr>
						</tbody>
						</table>
					</div>


				<ul>
					<li style="font-size: 10px">구매한 상품의 취소/반품은 마이쿠팡 구매내역에서 신청 가능합니다.</li>
				</ul>
				
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