<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML>
<!--
	Forty by HTML5 UP
	html5up.net | @ajlkn
	Free for personal and commercial use under the CCA 3.0 license (html5up.net/license)
-->
<html>
<head>
<title>Forty by HTML5 UP</title>
<meta charset="utf-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, user-scalable=no" />
<link rel="stylesheet" href="assets/css/main.css" />
<link rel="stylesheet" href="assets/css/noscript.css" />
<!-- 제이쿼리 불러오기 -->
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/jquery/2.2.4/jquery.min.js"></script>

<!-- Slick 불러오기 -->
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/slick-carousel/1.9.0/slick.min.js"></script>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/slick-carousel/1.9.0/slick.min.css">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/slick-carousel/1.9.0/slick-theme.min.css">

<link rel="icon" href="images/favicon.ico" type="image/x-icon">


<script type="text/javascript">
	function getCookie(name) {
		var cookie = document.cookie;
		if (document.cookie != "") {
			var cookie_array = cookie.split("; ");
			for ( var index in cookie_array) {
				var cookie_name = cookie_array[index].split("=");
				if (cookie_name[0] == "popupYN") {
					return cookie_name[1];
				}
			}
		}
		return;
	}

	function openwin() {
		var cookieCheck = getCookie("popupYN");
		if (cookieCheck != "N") {
			window.open("/mysiteB/user?a=mainup", 'a',
					"width=400, height=500, left=100, top=50")
		}
	}

	var videoArray = new Array();
	videoArray[0] = "images/dragon.mp4";
	videoArray[1] = "images/이터널스.mp4";
	videoArray[2] = "images/스파이더맨.mp4"
	videoArray[3] = "images/매트릭스.mp4";

	function showVideo() {
		var videoNum = Math.round(Math.random() * 3);
		var objVideo = document.getElementById("introVideo");
		objVideo.src = videoArray[videoNum];
	}
</script>


<style>
.jb-box {
	padding: 0px;
	margin: 0px;
	width: 100%;
	height: 500px;
	overflow: hidden;
	margin: 0px 0px;
	position: relative;
}

.jb-box {
	width: 100%;
	height: 100%;
	overflow: hidden;
	position: relative;
}

video {
	width: 100%;
	object-fit: cover;
}

.jb-text {
	position: absolute;
	top: 50%;
	width: 100%;
}

.jb-text p {
	margin-top: -24px;
	text-align: center;
	font-size: 48px;
	color: #ffffff;
}
</style>






</head>
<body onload="openwin(); showVideo()" class="is-preload">

	<!-- Wrapper -->
	<div id="wrapper">
		<c:import url="/WEB-INF/views/includes/header.jsp"></c:import>
		<!-- Banner -->
		<div class="jb-box">
			<video id="introVideo" muted autoplay loop controls style="">
				<source type="video/mp4">
			</video>
		</div>




		<!-- 이 예제에서는 필요한 js, css 를 링크걸어 사용 -->
		<link rel="stylesheet"
			href="https://cdnjs.cloudflare.com/ajax/libs/Swiper/4.5.1/css/swiper.min.css">
		<script
			src="https://cdnjs.cloudflare.com/ajax/libs/Swiper/4.5.1/js/swiper.min.js"></script>

		<style type="text/css">
.swiper-container {
	height: 200px;
}

.swiper-slide {
	text-align: center;
	display: flex; /* 내용을 중앙정렬 하기위해 flex 사용 */
	align-items: center; /* 위아래 기준 중앙정렬 */
	justify-content: center; /* 좌우 기준 중앙정렬 */
}

.swiper-slide img {
	box-shadow: 0 0 5px #555;
	max-width: 100%; /* 이미지 최대너비를 제한, 슬라이드에 이미지가 여러개가 보여질때 필요 */
	/* 이 예제에서 필요해서 설정했습니다. 상황에따라 다를 수 있습니다. */
}
</style>

		<!-- 클래스명은 변경하면 안 됨 -->
		<div class="swiper-container">
			<div class="swiper-wrapper">
				<c:forEach items="${list}" var="vo">
					<div class="swiper-slide">
						<img style = "height:200px;" src="/mysiteB/upload/${vo.proFileName }">
					</div>
				</c:forEach>
			</div>

			<!-- 네비게이션 -->
			<div class="swiper-button-next"></div>
			<!-- 다음 버튼 (오른쪽에 있는 버튼) -->
			<div class="swiper-button-prev"></div>
			<!-- 이전 버튼 -->

			<!-- 페이징 -->
			<div class="swiper-pagination"></div>
		</div>

<c:if test="${authUser != null }">

<br>
			
<h4>나를 위한 추천작</h4>
				<div class="swiper-container">
					<div class="swiper-wrapper">
					
						<c:forEach items="${list2}" var="vo">
							<c:if test="${vo.proCateg != '' }">
							<div class="swiper-slide">
						<img style = "height:200px;" src="/mysiteB/upload/${vo.proFileName }">
					</div>

							</c:if>
						</c:forEach>
						</div>
						
						<div class="swiper-button-next"></div>
			<!-- 다음 버튼 (오른쪽에 있는 버튼) -->
			<div class="swiper-button-prev"></div>
			<!-- 이전 버튼 -->

			<!-- 페이징 -->
			<div class="swiper-pagination"></div>
						</div>
<br>

</c:if>




		<script>
			new Swiper('.swiper-container', {

				slidesPerView : 6, // 동시에 보여줄 슬라이드 갯수
				spaceBetween : 10, // 슬라이드간 간격
				slidesPerGroup : 6, // 그룹으로 묶을 수, slidesPerView 와 같은 값을 지정하는게 좋음

				// 그룹수가 맞지 않을 경우 빈칸으로 메우기
				// 3개가 나와야 되는데 1개만 있다면 2개는 빈칸으로 채워서 3개를 만듬
				loopFillGroupWithBlank : true,

				loop : true, // 무한 반복

				pagination : { // 페이징
					el : '.swiper-pagination',
					clickable : true, // 페이징을 클릭하면 해당 영역으로 이동, 필요시 지정해 줘야 기능 작동
				},
				navigation : { // 네비게이션
					nextEl : '.swiper-button-next', // 다음 버튼 클래스명
					prevEl : '.swiper-button-prev', // 이번 버튼 클래스명
				},
			});
		</script>

















		<!-- Main -->
		<div id="main">

			<!-- One -->
			<section id="one" class="tiles">
				<article>
					<span class="image"> <img src="images/pic01.jpg" alt="" />
					</span>
					<header class="major">
						<h3>
							<a href="/mysiteB/product?a=productList" class="link">category</a>
						</h3>
						<p>상품 목록</p>
					</header>
				</article>
				<article>
					<span class="image"> <img src="images/pic04.jpg" alt="" />
					</span>
					<header class="major">
						<h3>
							<a href="/mysiteB/bboard?a=list" class="link">Announcement</a>
						</h3>
						<p>공지사항</p>
					</header>
				</article>
				
			</section>

			<!-- Two -->
			<section id="two">
				<div class="inner">
					<header class="major">
						<h2>신규 회원 이벤트</h2>
					</header>
					<p>지금 회원 가입하시면 즉시 2000원 할인 쿠폰 지급!</p>
					<ul class="actions">
						<li><a href="/mysiteB/user?a=joinform" class="button next">회원
								가입 바로가기</a></li>
					</ul>
				</div>
			</section>

			<section id="two">
				<div class="inner">
					<header class="major">
						<h2>문의하기</h2>
					</header>
					<p>질문하고 싶은 모든 것</p>
					<ul class="actions">
						<li><a href="/mysiteB/qna?a=list" class="button next">문의 바로가기</a></li>
					</ul>
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
	<script src="assets/js/slick.js"></script>

</body>
</html>