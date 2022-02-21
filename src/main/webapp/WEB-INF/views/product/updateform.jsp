<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
</head>
<script type="text/javascript">
	function checkInput() {
		var form = document.updateform;
		if (form.proName.value == "") {
			alert("이름을 입력해주세요.");
			form.proName.focus();
			return false;
		} else if (form.proStock.value == "") {
			alert("재고를 입력해주세요.");
			form.proStock.focus();
			return false;
		} else if (form.proPrice.value == "") {
			alert("가격을 입력해주세요.");
			form.proPrice.focus();
			return false;
		} else if (form.proDesc.value == "") {
			alert("설명을 입력해주세요.");
			form.proDesc.focus();
			return false;
		}
		form.submit();
	}
</script>

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
						<h1>상품 수정</h1>
					</header>

					<hr class="major" />


					<div class="row gtr-200">
						<div class="col-6 col-12-medium">

							<form class="form-signin" id="insert-form" name="updateform" method="post" action="/mysiteB/product?a=updateProduct&proNo=${proNo}" enctype="multipart/form-data">

								<div class="row gtr-uniform">
						
									<h3>상품 이름</h3>
									<div class="col-6 col-12-xsmall">
										<input type="text" name="proName" id="demo-name" value="${pvo.proName }"/>
									</div>
									
									<div></div>
									<h3>카테고리</h3>
									<div class="col-6 col-12-xsmall">
									
										<select name="proCateg" class="txt" style = "width: auto">
											<option ${pvo.proCateg == "/"?"selected":"" } value="/">/</option>
											<option ${pvo.proCateg == "클래식"?"selected":"" } value="클래식">클래식</option>
											<option ${pvo.proCateg == "팝"?"selected":"" } value="팝">팝</option>
											<option ${pvo.proCateg == "락"?"selected":"" } value="락">락</option>
											<option ${pvo.proCateg == "재즈"?"selected":"" } value="재즈">재즈</option>
											<option ${pvo.proCateg == "OST"?"selected":"" } value="OST">OST</option>
											
										</select>
									</div>
									
									<div></div>
									<h3>상품 재고</h3>
									<div class="col-6 col-12-xsmall">
									
										<input type="text" name="proStock" id="demo-email" value="${pvo.proStock }" />
									</div>
									
									<div></div>
									<h3>상품 가격</h3>
									<div class="col-6 col-12-xsmall">
									
										<input type="text" name="proPrice" id="demo-email" value="${pvo.proPrice }" />
									</div>
									
									<div></div>
									<h3>상품 설명</h3>
									<div class="col-6 col-12-xsmall">
									
										<input type="text" name="proDesc" id="demo-email" value="${pvo.proDesc }" />
									</div>
									
									<div></div>
									
									<h3>판매여부</h3>
									<div class="col-6 col-12-xsmall">
										<c:if test="${pvo.proOnSale == 1}">
										<input type="radio" id="yes" name="proOnSale" value="1" checked="checked" >
  										<label for="yes">Yes</label>
  										<input type="radio" id="no" name="proOnSale" value="0">
  										<label for="no">No</label><br>
										</c:if>
										<c:if test="${pvo.proOnSale == 0}">
										<input type="radio" id="yes" name="proOnSale" value="1" >
  										<label for="yes">Yes</label>
  										<input type="radio" id="no" name="proOnSale" value="0" checked="checked">
  										<label for="no">No</label><br>
										</c:if>
  										
									</div>
									
									<div></div>
									<h3>상품사진</h3>
									<div class="col-6 col-12-xsmall">
									
										<input type="file" name="proFileName" id="demo-email" value="${pvo.proFileName }"/>
										<input type="hidden" name="original" value="${pvo.proFileName}"/>
									</div>
									
									<div class="col-12">
										<ul class="actions">
											<li><input type="submit" value="등록"
												class="primary" /></li>
										</ul>
									</div>
									
								</div>
							</form>
						</div>
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