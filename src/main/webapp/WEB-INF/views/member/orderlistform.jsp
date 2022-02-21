<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="java.util.*"%>
<%@ page import="com.javaex.vo.*"%>
<!DOCTYPE HTML>
<%
List boardList = (List) request.getAttribute("list");
int total_record = ((Integer) request.getAttribute("total_record")).intValue();
int pageNum = ((Integer) request.getAttribute("pageNum")).intValue();
int total_page = ((Integer) request.getAttribute("total_page")).intValue();

%>
<html>
<head>
<title>Elements - Forty by HTML5 UP</title>
<meta charset="utf-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, user-scalable=no" />
<link rel="stylesheet" href="assets/css/main.css" />
<link rel="stylesheet" href="assets/css/noscript.css" />
<script src="https://code.jquery.com/jquery-1.12.4.js"></script> 
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script> 
<script> 
$(function() {
	$('#onDisplay0').click(function() {
		if ($("#noneDiv0").css("display") == "none") {
			$('#noneDiv0').show();
		} else if ($("onDisplay0").css("display") != "none") {
			$('#noneDiv0').hide();
		}
	});
	$('#onDisplay1').click(function() {
		if ($("#noneDiv1").css("display") == "none") {
			$('#noneDiv1').show();
		} else if ($("onDisplay1").css("display") != "none") {
			$('#noneDiv1').hide();
		}
	});
	$('#onDisplay2').click(function() {
		if ($("#noneDiv2").css("display") == "none") {
			$('#noneDiv2').show();
		} else if ($("onDisplay2").css("display") != "none") {
			$('#noneDiv2').hide();
		}
	});
	$('#onDisplay3').click(function() {
		if ($("#noneDiv3").css("display") == "none") {
			$('#noneDiv3').show();
		} else if ($("onDisplay3").css("display") != "none") {
			$('#noneDiv3').hide();
		}
	});
	$('#onDisplay4').click(function() {
		if ($("#noneDiv4").css("display") == "none") {
			$('#noneDiv4').show();
		} else if ($("onDisplay4").css("display") != "none") {
			$('#noneDiv4').hide();
		}
	});
});

 </script>


<script type="text/javascript">
 var x = parseInt(${vo.count});
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
						<h1>주문 조회</h1>
					</header>

					<hr class="major" />
					
					<div class="row">
						<c:import url="/WEB-INF/views/includes/navigation3.jsp"></c:import>
					
					
					<div class="col-9 col-12-medium">
					<ul class="actions">
						<li><a href="#" class="button primary">주문내역조회</a></li>
						<li><a href="#" class="button">취소/반품/교환 내역</a></li>
						


					</ul>


					<div class="table-wrapper">
						<table>
							<thead>
								<tr>
									<th>주문 번호</th>
									<th>상품 정보</th>
									<th>수량</th>
									<th>주문 처리 상태</th>
									<th>주문 일자</th>
									<th>가격</th>

								</tr>
							</thead>
							

								<c:forEach items="${alist}" var="vo" varStatus="status">
								<tbody>
									<tr>
										<td>${vo.orderNo}<c:if test="${vo.count == '1'}"><c:if test="${vo.orderComplete == '2'}"><input type="button" value="후기 작성"
										onclick="window.open('/mysiteB/product?a=reviewform&proNo=${vo.list[0].proNo}', 'win', 'width=400,height=500')" ></c:if></c:if></td>
										<c:choose>
										<c:when test="${vo.count == '1'}">
										<td>${vo.proName}</td>
										</c:when>
										<c:when test="${vo.count != '1'}">
										<td><a id="onDisplay${status.index}">${vo.proName} 외 <fmt:formatNumber value="${vo.count - 1}"  type="number" pattern="####,####"/></a>
										</td>
										</c:when>
										</c:choose>
										<td>${vo.amount}</td>
										<c:choose>
										<c:when test="${vo.orderComplete == '0'}">
										<td>배송 준비</td>
										</c:when>
										<c:when test="${vo.orderComplete == '1'}">
										<td>배송중</td>
										</c:when>
										<c:when test="${vo.orderComplete == '2'}">
										<td>배송 완료</td>
										</c:when>
										</c:choose>
										<td>${vo.orderDate}</td>
										<td><fmt:formatNumber value="${vo.proPrice}"  type="number" pattern="#,###,###,###"/>원</td>
										<td></td>
									</tr>
									
									</tbody>
								


									
									<tbody id="noneDiv${status.index}" style="display: none; background-color: rgba(212, 212, 255, 0.035)">
									<c:forEach items="${vo.list}" var="aa">
									
								
									
									<tr style="background-color: rgba(212, 212, 255, 0.035)">
									<th>
									<c:if test="${vo.orderComplete == '2'}">
									<input type="button" value="후기 작성"
										onclick="window.open('/mysiteB/product?a=reviewform&proNo=${aa.proNo}', 'win', 'width=400,height=500')" >
										</c:if></th>
									<td>${aa.proName}</td>
									<td>${aa.count}</td>
									<c:choose>
										<c:when test="${aa.orderComplete == '0'}">
										<td>배송 준비</td>
										</c:when>
										<c:when test="${aa.orderComplete == '1'}">
										<td>배송중</td>
										</c:when>
										<c:when test="${aa.orderComplete == '2'}">
										<td>배송 완료</td>
										</c:when>
										</c:choose>
									<td></td>
									<td><fmt:formatNumber value="${aa.proPrice}"  type="number" pattern="#,###,###,###"/>원</td>
									
									
									</tr>
									
									
									</c:forEach>
									
									</tbody>
									
								</tbody>
								</c:forEach>
								
							
						</table>
						</div>
						
					
					<ul class="pagination" style="text-align: center">
					<li><span class="button small">Prev</span></li>
					<c:set var="pageNum" value="<%=pageNum%>" />
					<c:forEach var="i" begin="1" end="<%=total_page%>">
						<c:choose>
							<c:when test="${pageNum==i}">
								<li><a class="page active" href="/mysiteB/orderInfo?a=list&pageNum=${i}">${i}</a></li>
							</c:when>
							<c:otherwise>
								<li><a class="page" href="/mysiteB/orderInfo?a=list&pageNum=${i}">${i}</a></li>
							</c:otherwise>
						</c:choose>
				</c:forEach>
				<li><a href="#" class="button small">Next</a></li>
				</ul>
		</div>

		</div>
						</div>
					<!-- Elements -->

					<div class="row gtr-200">
						<div class="col-6 col-12-medium"></div>
						<div class="col-6 col-12-medium"></div>
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