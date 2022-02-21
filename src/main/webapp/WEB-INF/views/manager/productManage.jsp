<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="java.util.*"%>
<%
	List manlist = (List) request.getAttribute("manlist");
	int manpageNum = ((Integer) request.getAttribute("manpageNum")).intValue();
	int mantotal_page = ((Integer) request.getAttribute("mantotal_page")).intValue();
	int mantotal_record = ((Integer) request.getAttribute("mantotal_record")).intValue();

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

<link rel="stylesheet" href="assets/css/main.css" />
<link rel="stylesheet" href="assets/css/noscript.css" />


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



<style>
#checkboxTestTbl {
	border-collapse: collapse;
}

#checkboxTestTbl td, #checkboxTestTbl th {
	padding: 20px;
}

#checkboxTestTbl th {
	background-color: #ccc;
}

#checkboxTestTbl tr.selected {
	background-color: navy;
	color: #fff;
	font-weight: bold;
}




</style>



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
						<h1>상품관리</h1>
					</header>

					<hr class="major" />

					<!-- Elements -->
					<div class="col-6 col-12-medium">

						<!-- Lists -->
						<div class="row">
							<c:import url="/WEB-INF/views/includes/mnavigation.jsp"></c:import>
							<div class="col-9 col-12-medium">
							<form action = "/mysiteB/product" method = "post">
								<div class="alt">
									
									<input type="hidden" name = "a" value = "manageProduct">
									<table class="alt">
										<tbody>
											<tr>
												
											</tr>
											<tr>
												<td style="background-color: rgba(212, 212, 255, 0.035)">상품명</td>
												<td>
													<input type="text" name="name" id="demo-name"
														value="" placeholder="" />

												</td>
												<td style="background-color: rgba(212, 212, 255, 0.035)">판매여부</td>
												<td><select name="onSale" id="onSale">
														<option value="">- 선택 -</option>
														<option value="1">有</option>
														<option value="0">無</option>
												</select></td>
											</tr>
											<tr>
												<td>가격</td>
												<td style="background-color: #242943;">
													
													<div class="row">
														<div class="col-6 col-12-small" >
															
															<input type="text" name="startPrice" id="startPrice"
																value="" placeholder="-원 이상" />
															
														</div>
														<div class="col-6 col-12-small" >
															
															<input type="text" name="endPrice" id="endPrice"
																value="" placeholder="-원 이하" />
															
														</div>
													</div>
													
												</td>
												<td>장르</td>
												<td style="background-color: #242943;">
												
												<select name="categ" id = "categ" class="txt" style = "width: auto">
														<option value="" selected>- 선택 -</option>
														<option value="/">/</option>
														<option value="클래식">클래식</option>
														<option value="팝">팝</option>
														<option value="락">락</option>
														<option value="재즈">재즈</option>
														<option value="OST">OST</option>
											</select></td>
											</tr>
										</tbody>
									</table>
									
								</div>

								<ul class="actions"
									style="display: inline-block; float: right; margin: 0">
									<li><input type="submit" value="검색" class="primary"/></li>
								</ul>
								</form>
								<br>

								<hr class="major" />
	
								<p>검색 상품수 : ${mantotal_record }</p>
								<div class="table-wrapper">
									
								
								<table class="alt">
										<thead>
											<tr>
												<th style="text-align:center">상품코드</th>
												<th style="text-align:center">상품명</th>
												<th style="text-align:center">상품가격</th>
												<th style="text-align:center">재고</th>
												<th style="text-align:center">판매여부</th>
												<th style="text-align:center"></th>
											</tr>
										</thead>
										<tbody style="text-align:center">
											<c:forEach items="${manlist}" var = "advvo">
											
											
											<tr>
												<td>${advvo.proNo}</td>
												<td>${advvo.proName }</td>
												<td>${advvo.proPrice }</td>
												<td>${advvo.proStock }</td>
												<td>${advvo.proOnSale }</td>
												<td>
													<a href="/mysiteB/product?a=updateform&proNo=${advvo.proNo}"><button class="button" value="수정" >수정</button></a> 
													<a onclick="window.open('/mysiteB/product?a=deleteform&proNo=${advvo.proNo}', 'win', 'width=400,height=200')"><button class="button" value="삭제" >삭제</button></a> 
													
												</td>
											</tr>
											
											
											</c:forEach>
										</tbody>
									</table>
								
								
								<c:set var="pageNum" value="${manpageNum}" />
								<c:forEach var="i" begin="1" end="${mantotal_page}">
									<a
										href="/mysiteB/product?a=manageProduct&pageNum=${i}">
										<c:choose>
											<c:when test="${manpageNum==i}">
												<font color='4C5317'><b> [${i}]</b></font>
											</c:when>
											<c:otherwise>
												<font color='4C5317'> [${i}]</font>
											</c:otherwise>
										</c:choose>
									</a>
								</c:forEach>
								
								</div>

								<ul class="actions">
									<li><input type="reset" value="상품등록" onclick="location.href='/mysiteB/product?a=insertform';"/></li>
								</ul>


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