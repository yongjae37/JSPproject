<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import = "java.util.ArrayList" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="java.util.*"%>
<%@ page import="com.javaex.vo.*" %>
<%@ page import="com.javaex.dao.*" %>
<%
	List orderInfoList = (List) request.getAttribute("list");
	int total_record = ((Integer) request.getAttribute("total_record")).intValue();
	int pageNum = ((Integer) request.getAttribute("pageNum")).intValue();
	int total_page = ((Integer) request.getAttribute("total_page")).intValue();
	OrderInfoDao dao = new OrderInfoDaoImpl();
	
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel = "stylesheet" href = "./resources/css/bootstrap.min.css" />
<title>주문 정보</title>
<!-- Bootstrap core JavaScript -->
<script src="vendor/jquery/jquery.min.js"></script>
<script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

<!-- Bootstrap core CSS -->
<link href="vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">

<!-- Custom styles for this template -->
<link href="css/shop-homepage.css" rel="stylesheet">


</head>
<body>
	<c:import url="/WEB-INF/views/includes/header.jsp"></c:import>
	
	<div class = "col-lg-9">
	<c:import url="/WEB-INF/views/includes/navigation.jsp"></c:import>
		<div class = "container">
			<h1 class = "display-3">주문 내역</h1>
		</div>
	</div>
	
	
	<div>
		<table class = "table table-hover">
			<tr>
				<th class = "text-center">주문번호</th>
				<th class = "text-center">제품명</th>
				<th class = "text-center">주문일자</th>
				<th class = "text-center">소계</th>
			</tr>
			
			
			<c:forEach items="${list }" var="vo">>
		
				<tr>
					<td class="text-center">${vo.orderNo }</td>
					<td class="text-center">${vo.proName }</td>
					<td class="text-center">${vo.orderDate }</td>
					<td class="text-center">${vo.totalPrice }</td>
				</tr>


			</c:forEach>
			
			
		</table>
		
	</div>
	<div class="pager" style="text-align: center;">
				<c:set var="pageNum" value="<%=pageNum%>" />
				<c:forEach var="i" begin="1" end="<%=total_page%>">
					<a href="/mysiteB/orderInfo?a=list&pageNum=${i}&memNo=1">  
						<c:choose>
							<c:when test="${pageNum==i}">
								<font color='4C5317'><b> [${i}]</b></font>
							</c:when>
							<c:otherwise>
								<font color='4C5317'> [${i}]</font>

							</c:otherwise>
						</c:choose>
					</a>
				</c:forEach>
		</div>
	<c:import url="/WEB-INF/views/includes/footer.jsp"></c:import>


	
	
</body>
</html>