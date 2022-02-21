<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
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
<title>관리자페이지 - 일반문의</title>
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

		/* 클릭 클릭시 클릭을 클릭한 위치 근처에 레이어가 나타난다. */
		$('.imgSelect').click(function(e) {
			var sWidth = window.innerWidth;
			var sHeight = window.innerHeight;

			var oWidth = $('.popupLayer').width();
			var oHeight = $('.popupLayer').height();

			// 레이어가 나타날 위치를 셋팅한다.
			var divLeft = e.clientX + 10;
			var divTop = e.clientY + 5;

			// 레이어가 화면 크기를 벗어나면 위치를 바꾸어 배치한다.
			if (divLeft + oWidth > sWidth)
				divLeft -= oWidth;
			if (divTop + oHeight > sHeight)
				divTop -= oHeight;

			// 레이어 위치를 바꾸었더니 상단기준점(0,0) 밖으로 벗어난다면 상단기준점(0,0)에 배치하자.
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
					<h1>일반문의</h1>
				</header>



				<hr class="major" />

				<!-- Elements -->
				<div class="col-6 col-12-medium">


					<!-- Lists -->
					<div class="row">


						<c:import url="/WEB-INF/views/includes/mnavigation.jsp"></c:import>


						<div class="col-9 col-12-medium">



							<div class="table-wrapper">
								<ul class="actions">
								<form id="search_form" action="/mysiteB/manager" method="post">
								<input type = "hidden" name = "a" value="inquiry">
								<li><select name="t" id="demo-category"style="width:200px;height:50px;">
									<option ${param.t == "전체 문의"?"selected":"" } value="전체 문의">전체 문의</option>
									<option ${param.t == "회원 문의"?"selected":"" } value="회원 문의">회원 문의</option>
									<option ${param.t == "결제 문의"?"selected":"" } value="결제 문의">결제 문의</option>
									<option ${param.t == "배송 문의"?"selected":"" } value="배송 문의">배송 문의</option>
									<option ${param.t == "기타 문의"?"selected":"" } value="기타 문의">기타 문의</option>
								</select></li>			
									<li><input type="submit" value="검색"></li>
								</form>
								</ul>
								<table>
									<thead>
										<tr>
											<th>번호</th>
											<th>제목</th>
											<th>작성자</th>
											<th>질문유형</th>
											<th>작성일자</th>
											<th>답변</th>
										</tr>
									</thead>
									


									<tbody>
										<c:forEach items="${list}" var="vo">
									<tr>
										<td>${vo.qnaNo}</td>
										
										<td><a href="/mysiteB/manager?a=specificinquiry&qnaNo=${vo.qnaNo}">${vo.title}
										</a></td>
										
										<c:if test="${vo.memNo != 0 }">
										<td>${vo.memName}</td>
										</c:if>
										<c:if test="${vo.memNo == 0 }">
										<td>${vo.nickname}</td>
										</c:if>
										<td>${vo.type}</td>
										<td>${vo.regDate}</td>
										
										<c:if test="${ vo.ansCnt!=0 && vo.ansCnt!=null }">
										<td><span>Y</span></td>
										</c:if>
										<c:if test="${ vo.ansCnt==0 || vo.ansCnt == null }">
										<td><span>N</span></td>
										</c:if>
										
										
									</tr>
								</c:forEach>
									</tbody>

								</table>
								
								<div class="show" title="미답변 목록">
									<a href="/mysiteB/manager?a=listNoAnswer" class="button primary fit"
										style="display: inline-block; width:100px; float: right">미답변</a>
								</div>
								
								<div class="pager">
									<c:set var="page" value="${(empty param.p)?1:param.p }" />
									<c:set var="startNum" value="${page-(page-1)%5}"/> 
									<c:set var="lastNum" value="${fn:substringBefore(Math.ceil(count/5),'.') }"/>
													
									<div class="indexer margin-top " >
										<h6 class="hidden">현재 페이지</h3>
										<div>${(empty param.p)?1:param.p }/${lastNum} pages</div>
									
									</div>
									
									<ul class="-list- center">
										
											<c:if test="${startNum>1 }">
												<a class="btn btn-prev" href="/mysiteB/manager?a=inquiry&p=${startNum-1 }&t=${param.t}}">◀</a>
											</c:if>
											<c:forEach var="i" begin="0" end="4">
											<c:if test="${(startNum+i) <= lastNum}">	
												<a class="${(param.p==(startNum+i))?'selected':'' }"></a><a href="/mysiteB/manager?a=inquiry&p=${startNum+i}&t=${param.t}">${startNum+i}</a>						
											</c:if>
											</c:forEach>
											
											<c:if test="${startNum+4<lastNum }">
												<a href="/mysiteB/manager?a=inquiry&p=${startNum+5}&t=${param.t}"class="btn btn-next">▶</a>
											</c:if>
									</ul>
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