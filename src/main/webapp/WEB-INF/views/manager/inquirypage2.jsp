<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="com.javaex.vo.OrderInfoVo"%>
<%
List<OrderInfoVo> list = (List<OrderInfoVo>) request.getAttribute("list");
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
<script type='text/javascript' charset='utf-8'
	src='https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js'></script>



<script type="text/javascript">
	function closeLayer(obj) {
		$(obj).parent().parent().hide();
	}
	function closeLayer1(obj) {
		var frm = document.form;
		frm.submit();
		$(obj).parent().parent().hide();

	}

	$(function() {

		/* Ŭ�� Ŭ���� Ŭ���� Ŭ���� ��ġ ��ó�� ���̾ ��Ÿ����. */
		$('.imgSelect').click(function(e) {
			var sWidth = window.innerWidth;
			var sHeight = window.innerHeight;

			var oWidth = $('.popupLayer').width();
			var oHeight = $('.popupLayer').height();

			// ���̾ ��Ÿ�� ��ġ�� �����Ѵ�.
			var divLeft = e.clientX + 10;
			var divTop = e.clientY + 5;

			// ���̾ ȭ�� ũ�⸦ ����� ��ġ�� �ٲپ� ��ġ�Ѵ�.
			if (divLeft + oWidth > sWidth)
				divLeft -= oWidth;
			if (divTop + oHeight > sHeight)
				divTop -= oHeight;

			// ���̾� ��ġ�� �ٲپ����� ��ܱ�����(0,0) ������ ����ٸ� ��ܱ�����(0,0)�� ��ġ����.
			if (divLeft < 0)
				divLeft = 0;
			if (divTop < 0)
				divTop = 0;

			$('.popupLayer').css({
				"top" : divTop,
				"left" : divLeft,
				"position" : "absolute"
			}).show();
		});

	});
</script>

<style>
.imgSelect {
	cursor: pointer;
	font-family: Helvetica Neue, Helvetica, Arial, sans-serif;
	letter-spacing: 2px;
	text-align: center;
	color: #f35626;
	background-image: -webkit-linear-gradient(92deg, #f35626, #feab3a);
	-webkit-background-clip: text;
	-webkit-text-fill-color: transparent;
	-webkit-animation: hue 10s infinite linear;
}

@
-webkit-keyframes hue {from { -webkit-filter:hue-rotate(0deg);
	
}

to {
	-webkit-filter: hue-rotate(-360deg);
}

}
.popupLayer {
	display: none;
	position: absolute;
	width: 350px;
	height: 150px;
	left: 448px;
	bottom: 62px;
	color: black;
	background-color: black;
	border-radius: 5px;
	padding: 12px 12.8px;
}

.popupLayer form {
	line-height: 100px;
	display: block;
	text-align: center;
}

.popupLayer div {
	position: absolute;
	top: 10px;
	right: 5px
}

.dropdown {
	position: relative;
	display: inline-block;
}

.dropdown-content {
	display: none;
	position: absolute;
	background-color: #f1f1f1;
	min-width: 160px;
	box-shadow: 0px 8px 16px 0px rgba(0, 0, 0, 0.2);
	z-index: 1;
}

.dropdown-content a {
	color: black;
	padding: 12px 16px;
	text-decoration: none;
	display: block;
}

.dropdown-content a:hover {
	background-color: #ddd;
}

.dropdown:hover .dropdown-content {
	display: block;
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
					<h1>������</h1>
				</header>



				<hr class="major" />

				<!-- Elements -->
				<div class="col-6 col-12-medium">


					<!-- Lists -->
					<div class="row">


						<c:import url="/WEB-INF/views/includes/mnavigation.jsp"></c:import>


						<div class="col-9 col-12-medium">



							<div class="table-wrapper">
								<div class="dropdown">
									<h3 class="dropbtn">
										<a href="/mysiteB/user?a=inquiry2">�Ϲ� ����</a>
									</h3>
									<div class="dropdown-content">
										<a href="/mysiteB/user?a=inquiry">��ü ����</a> <a
											href="/mysiteB/user?a=inquiry3">��ǰ ����</a> <a
											href="/mysiteB/user?a=inquiryR">���� ��</a>
									</div>
								</div>

								<div>
									<ul class="actions">


										<li><select name="demo-category" id="demo-category"
											style="margin-left: 5px">
												<option value="" name="memPhone1" id="memPhone">-
													��ü �з� -</option>
												<option value="1" name="memPhone1" id="memPhone">��ǰ</option>
												<option value="1" name="memPhone1" id="memPhone">�Ϲ�</option>
										</select></li>
										<li><input style="width: 200px;" type="text"></li>

										<li><input type="button" value="�˻�"></li>
									</ul>
								</div>
								<table>
									<thead>
										<tr>
											<th>��ǰ��</th>
											<th>����</th>
											<th>�̸�</th>
											<th>�亯</th>
											<th>����</th>
										</tr>
									</thead>
									<tr>

										<td>���Ѵ�</td>
										<td><a href="/mysiteB/user?a=specificinquiry">�Ϲ�</a></td>
										<td>����</td>
										<td>N</td>
										<td>���� ��</td>

									</tr>

									<tbody>
										<!-- 
									�̷� ��
										<c:forEach items="${list}" var="vo">

											<tr>
												<td>${vo.orderDate}</td>
												<td><a
													href="/mysiteB/orderInfo?a=specific&orderNo=">${vo.memId}</a></td>
												<td>${vo.proName}</td>
												<td>${vo.totalPrice}</td>
												<c:choose>
													<c:when test="${vo.orderComplete == '0'}">
														<td><button type="button" class="imgSelect">�̹��</button></td>
													</c:when>
													<c:when test="${vo.orderComplete == '1'}">
														<td><button type="button" class="imgSelect">�����</button></td>
													</c:when>
													<c:when test="${vo.orderComplete == '2'}">
														<td><button type="button">��� �Ϸ�</button></td>
													</c:when>
												</c:choose>


											</tr>
										</c:forEach>  -->
									</tbody>

								</table>
								<div class="show" title="�̴亯 ����">
									<a href="#" class="button primary fit"
										style="display: inline-block; width: 100px; float: right">�亯</a>
								</div>
							</div>



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