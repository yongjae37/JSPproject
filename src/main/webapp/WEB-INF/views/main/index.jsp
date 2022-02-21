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
<!-- �������� �ҷ����� -->
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/jquery/2.2.4/jquery.min.js"></script>

<!-- Slick �ҷ����� -->
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
	videoArray[1] = "images/���ͳν�.mp4";
	videoArray[2] = "images/�����̴���.mp4"
	videoArray[3] = "images/��Ʈ����.mp4";

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




		<!-- �� ���������� �ʿ��� js, css �� ��ũ�ɾ� ��� -->
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
	display: flex; /* ������ �߾����� �ϱ����� flex ��� */
	align-items: center; /* ���Ʒ� ���� �߾����� */
	justify-content: center; /* �¿� ���� �߾����� */
}

.swiper-slide img {
	box-shadow: 0 0 5px #555;
	max-width: 100%; /* �̹��� �ִ�ʺ� ����, �����̵忡 �̹����� �������� �������� �ʿ� */
	/* �� �������� �ʿ��ؼ� �����߽��ϴ�. ��Ȳ������ �ٸ� �� �ֽ��ϴ�. */
}
</style>

		<!-- Ŭ�������� �����ϸ� �� �� -->
		<div class="swiper-container">
			<div class="swiper-wrapper">
				<c:forEach items="${list}" var="vo">
					<div class="swiper-slide">
						<img style = "height:200px;" src="/mysiteB/upload/${vo.proFileName }">
					</div>
				</c:forEach>
			</div>

			<!-- �׺���̼� -->
			<div class="swiper-button-next"></div>
			<!-- ���� ��ư (�����ʿ� �ִ� ��ư) -->
			<div class="swiper-button-prev"></div>
			<!-- ���� ��ư -->

			<!-- ����¡ -->
			<div class="swiper-pagination"></div>
		</div>

<c:if test="${authUser != null }">

<br>
			
<h4>���� ���� ��õ��</h4>
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
			<!-- ���� ��ư (�����ʿ� �ִ� ��ư) -->
			<div class="swiper-button-prev"></div>
			<!-- ���� ��ư -->

			<!-- ����¡ -->
			<div class="swiper-pagination"></div>
						</div>
<br>

</c:if>




		<script>
			new Swiper('.swiper-container', {

				slidesPerView : 6, // ���ÿ� ������ �����̵� ����
				spaceBetween : 10, // �����̵尣 ����
				slidesPerGroup : 6, // �׷����� ���� ��, slidesPerView �� ���� ���� �����ϴ°� ����

				// �׷���� ���� ���� ��� ��ĭ���� �޿��
				// 3���� ���;� �Ǵµ� 1���� �ִٸ� 2���� ��ĭ���� ä���� 3���� ����
				loopFillGroupWithBlank : true,

				loop : true, // ���� �ݺ�

				pagination : { // ����¡
					el : '.swiper-pagination',
					clickable : true, // ����¡�� Ŭ���ϸ� �ش� �������� �̵�, �ʿ�� ������ ��� ��� �۵�
				},
				navigation : { // �׺���̼�
					nextEl : '.swiper-button-next', // ���� ��ư Ŭ������
					prevEl : '.swiper-button-prev', // �̹� ��ư Ŭ������
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
						<p>��ǰ ���</p>
					</header>
				</article>
				<article>
					<span class="image"> <img src="images/pic04.jpg" alt="" />
					</span>
					<header class="major">
						<h3>
							<a href="/mysiteB/bboard?a=list" class="link">Announcement</a>
						</h3>
						<p>��������</p>
					</header>
				</article>
				
			</section>

			<!-- Two -->
			<section id="two">
				<div class="inner">
					<header class="major">
						<h2>�ű� ȸ�� �̺�Ʈ</h2>
					</header>
					<p>���� ȸ�� �����Ͻø� ��� 2000�� ���� ���� ����!</p>
					<ul class="actions">
						<li><a href="/mysiteB/user?a=joinform" class="button next">ȸ��
								���� �ٷΰ���</a></li>
					</ul>
				</div>
			</section>

			<section id="two">
				<div class="inner">
					<header class="major">
						<h2>�����ϱ�</h2>
					</header>
					<p>�����ϰ� ���� ��� ��</p>
					<ul class="actions">
						<li><a href="/mysiteB/qna?a=list" class="button next">���� �ٷΰ���</a></li>
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