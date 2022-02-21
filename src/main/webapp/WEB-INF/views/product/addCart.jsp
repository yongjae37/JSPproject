<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page import="java.util.*"%>

<%@ page import="com.javaex.vo.*"%>



<!DOCTYPE HTML>

<html>
<head>
<script type="text/javascript"
	src="//cdnjs.cloudflare.com/ajax/libs/jquery/1.9.0/jquery.js"></script>
<script type="text/javascript">
$(function(){ 
	//전체선택 체크박스 클릭 
	$("#0").click(function(){ 
		//만약 전체 선택 체크박스가 체크된상태일경우 
		if($("#0").prop("checked")) { 
			//해당화면에 전체 checkbox들을 체크해준다 
			$("input[type=checkbox]").prop("checked",true); 
			// 전체선택 체크박스가 해제된 경우 
			} else { 
				//해당화면에 모든 checkbox들의 체크를해제시킨다. 
				$("input[type=checkbox]").prop("checked",false); 
				} 
		}) 
	}) 
</script>
<script type="text/javascript">
</script>

<script src="http://code.jquery.com/jquery-latest.js"></script>
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
						<h1>장바구니</h1>
					</header>
					<form method="post" action="/mysiteB/orderInfo">
					<div class="col-12">
										<ul class="actions">
										
											
											<li><button class="button" type="submit" name="a" value="removeCart" onclick="return checkbox()">선택 삭제</li>
											<li><button class="button" type="submit" name="a" value="deleteCart">전체 삭제</li>
											
										</ul>
										</div>
					

					<div class="table-wrapper">
						
						<table>
							<thead>
								<tr>
									<th style="text-align:center"><input type="checkbox" id="0" name="0"><label for="0" style=""></label></th>
									<th>[상품 목록]</th>
									<th>이미지</th>
									<th>수량</th>
									<th>적립금</th>
									<th>가격</th>

								</tr>
							</thead>
						
							<tbody>
							
								<c:forEach items="${cartlist}" var="pvo">
								<tr>
								<td><input type="checkbox" value="${pvo.proNo}" id="${pvo.proNo}" name="RowCheck" ><label for="${pvo.proNo }" style=""></label></td>
								<td>${pvo.proName}</td>
								<td><img src="./upload/${pvo.proFileName}" style="width:200px;height:200px;"> </td>
								<td>${pvo.amount}</td>
								<td><c:set var="pages" value="${pvo.proPrice * pvo.amount / 100 }" />
								<fmt:formatNumber type="number" maxFractionDigits="0"  value="${pages+(1-(pages%1))%1}"/></td>
								<td><c:out value="${pvo.proPrice * pvo.amount}"></c:out></td>
								</tr>
								</c:forEach>
								
							</tbody>
						
						</table>
						
					</div>
						<table>
							<tbody>
								<c:choose>
								<c:when test="${empty cartlist}">
								<tr>
								<td colspan="6"></td>
								<td>총상품 금액[0]원 + 배송비 [0]원 = [0]원</td>
								</tr>
								</c:when>
								<c:otherwise>
								<tr>
								<td colspan="6"></td>
								<td>총상품 금액[${total}]원 + 배송비 [2000]원 = [${total + 2000 }]원</td>
								</tr>
								</c:otherwise>
								</c:choose>
							</tbody>					
						</table>
						</form>	
						<form action="/mysiteB/orderInfo" method="post">	
						<div class="col-12">
										<ul class="actions fit" style = "">
											
											<li><button type="submit" style="display: inline-block; width: 250px; float: right" value="orderAll" name="a">전체상품 주문</button></li>
											<li><a href = "/mysiteB/orderInfo?a=orderform" style="display: inline-block; width: 250px" ><input type="button" value="선택 상품 주문"></a></li>
											<li><a href = "/mysiteB/product?a=productList" style="display: inline-block; width: 250px" ><input type="button" value="전체상품 보기"></a></li>
										</ul>
										</div>
										</form>
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