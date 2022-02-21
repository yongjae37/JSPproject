<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="java.util.*"%>
<%@ page import="com.javaex.vo.*"%>
<!DOCTYPE HTML>
<%
int totalPoint = ((Integer) request.getAttribute("point")).intValue();
int totalPay = ((Integer) request.getAttribute("total")).intValue();
request.setAttribute("totalPoint", totalPoint);
request.setAttribute("totalPay", totalPoint);
%>
<html>
<head>
<title>Elements - Forty by HTML5 UP</title>
<meta charset="utf-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, user-scalable=no" />
<link rel="stylesheet" href="assets/css/main.css" />
<link rel="stylesheet" href="assets/css/noscript.css" />
<link rel="icon" href="images/favicon.ico" type="image/x-icon">
<script src="http://code.jquery.com/jquery-latest.js"></script>
<script>
	$(function() {
		$('#r2').click(function() {

			$('#noneDiv2').show();
			$('#noneDiv1').hide();

		});
		$('#r1').click(function() {

			$('#noneDiv1').show();
			$('#noneDiv2').hide();

		});

	});
</script>
<script type="text/javascript">
function chkPoint(amt,pnt,min,unit) {
	//input값을 전체 마일리지로 설정 > minusPoint 
	//amt : 최초 결제 금액 / pnt : 사용가능,남은 포인트 / min : 사용 가능 최소 포인트 / unit : 사용단위
	var v_point = 0; //사용할 포인트 (input 입력값)

	if (document.getElementById("chk_use").checked)  
	{
		if (pnt < min)  //최소 사용 단위보다 작을 때
		{
			v_point = 0; 
		}else {
			v_point = pnt - pnt%unit; //사용할 포인트 = 전체 마일리지 중 최소단위 이하 마일리지를 뺀 포인트
		}

		if(pnt > amt ){ //결제금액보다 포인트가 더 클 때
			v_point = amt; //사용할 포인트는 결제금액과 동일하게 설정
		}
		
	}
	document.getElementById("use_pnt").value = v_point; //input 값 설정
	
	changePoint(amt,pnt,min,unit);
}

function changePoint(amt,pnt,min,unit){
	//input값을 불러옴 > left_pnt 변경 > 최종결제 변경
	//amt : 최초 결제 금액 / pnt : 사용가능,남은 포인트 / min : 사용 가능 최소 포인트 / unit : 사용단위
	var v_point = parseInt(document.getElementById("use_pnt").value); //사용할 포인트 (input 입력값)
	
	if(isNaN(v_point)){
		v_point = 0;
	}
	if (v_point > pnt) //입력값이 사용가능 포인트보다 클때
	{
		v_point = pnt;
		document.getElementById("use_pnt").value = v_point; //input 값 재설정
	}

	if(v_point > amt ){ //결제금액보다 포인트가 더 클 때
		v_point = amt; //사용할 포인트는 결제금액과 동일하게 설정
		document.getElementById("use_pnt").value = v_point; //input 값 재설정
	}

	if (v_point < min)  //최소 사용 단위보다 작을 때
	{
		v_point = 0; 
		document.getElementById("use_pnt").value = v_point; //input 값 재설정
	}else {
		v_point = v_point - v_point%unit; //사용할 포인트 = 사용할 마일리지 중 최소단위 이하 마일리지를 뺀 포인트
	}

	var v_left = document.getElementsByName("left_pnt"); //사용가능 마일리지, 남은 포인트 값 설정
	for (var i = 0; i < v_left.length; i++) {

		v_left[i].innerHTML = pnt - v_point; //= 전체 포인트 중에 사용할 포인트빼고 남은 포인트

	}
	
	document.getElementById("result_pnt").innerHTML = amt - v_point; //최종 결제금액 = 결제금액 - 사용할 포인트
}</script>
<script
	src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>


<script>
	//본 예제에서는 도로명 주소 표기 방식에 대한 법령에 따라, 내려오는 데이터를 조합하여 올바른 주소를 구성하는 방법을 설명합니다.
	function sample4_execDaumPostcode() {
		new daum.Postcode(
				{
					oncomplete : function(data) {
						// 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

						// 도로명 주소의 노출 규칙에 따라 주소를 표시한다.
						// 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
						var roadAddr = data.roadAddress; // 도로명 주소 변수
						var extraRoadAddr = ''; // 참고 항목 변수

						// 법정동명이 있을 경우 추가한다. (법정리는 제외)
						// 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
						if (data.bname !== '' && /[동|로|가]$/g.test(data.bname)) {
							extraRoadAddr += data.bname;
						}
						// 건물명이 있고, 공동주택일 경우 추가한다.
						if (data.buildingName !== '' && data.apartment === 'Y') {
							extraRoadAddr += (extraRoadAddr !== '' ? ', '
									+ data.buildingName : data.buildingName);
						}
						// 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
						if (extraRoadAddr !== '') {
							extraRoadAddr = ' (' + extraRoadAddr + ')';
						}

						// 우편번호와 주소 정보를 해당 필드에 넣는다.
						document.getElementById('Postc').value = data.zonecode;
						document.getElementById("Doro").value = roadAddr;
						document.getElementById("Jibun").value = data.jibunAddress;

						// 참고항목 문자열이 있을 경우 해당 필드에 넣는다.
<%--   
                if(roadAddr !== ''){
                    document.getElementById("sample4_extraAddress").value = extraRoadAddr;
                } else {
                    document.getElementById("sample4_extraAddress").value = '';
                }
--%>
	var guideTextBox = document.getElementById("guide");
						// 사용자가 '선택 안함'을 클릭한 경우, 예상 주소라는 표시를 해준다.
						if (data.autoRoadAddress) {
							var expRoadAddr = data.autoRoadAddress
									+ extraRoadAddr;
							guideTextBox.innerHTML = '(예상 도로명 주소 : '
									+ expRoadAddr + ')';
							guideTextBox.style.display = 'block';

						} else if (data.autoJibunAddress) {
							var expJibunAddr = data.autoJibunAddress;
							guideTextBox.innerHTML = '(예상 지번 주소 : '
									+ expJibunAddr + ')';
							guideTextBox.style.display = 'block';
						} else {
							guideTextBox.innerHTML = '';
							guideTextBox.style.display = 'none';
						}
					}
				}).open();
	}
</script>

</head>
<body class="is-preload">
	<form class="form-signin" id="mypage-form" name="modifyform"
		method="post" action="/mysiteB/orderInfo?proNo=${proVo.proNo}">
		<input type="hidden" name="a" value="insert" />
		<input type="hidden" name="total" value = "${total}" />
		<input type="hidden" name="amount" value = "${amount}" />
		<!-- Wrapper -->
		<div id="wrapper">

			<c:import url="/WEB-INF/views/includes/header.jsp"></c:import>

			<!-- Main -->
			<div id="main" class="alt">

				<!-- One -->
				<section id="one">

					<div class="inner">

						<header class="major">
							<h1>주문</h1>
						</header>



						 <input type="hidden" name="memNo" value="${authUser.memNo}" /> 



						<div class="table-wrapper">
							<table>
								<thead>
									<tr>

										<th>이미지</th>
										<th>상품 정보</th>
										<th>수량</th>
										<th>상품가격</th>
										
										


									</tr>
								</thead>
								<tbody>
								
									<tr>

										<th>${proVo.proName}</th>
										<th>${proVo.proName}</th>
										<th>${amount}</th>
										<th><c:out value="${price}"></c:out></th>
										


									</tr>
								

									<!--<c:forEach items="${list}" var="vo">
									<tr>
										<th></th>
										<th>~</th>
										<th>1개</th>
										<th>100원</th>
										<th>2000천원</th>
										<th>18000원</th>
										<th><input type="button"  value="작성하기"></th>

									</tr>
								</c:forEach>-->
								</tbody>

							</table>

							<table style="text-align: right;">
								<thead>
									<tr>
										<td colspan="6"></td>
										<td>상품 구매 금액[${price}] + 배송비 [2000] = [${total}]원</td>
									</tr>
								</thead>

							</table>
						</div>


						<div class="row gtr-200">

							<div class="col-6 col-12-medium">

								<h3>주문 정보</h3>

								<input type="hidden" name="a" value="modify" /> <input
									type="hidden" name="no" value="${authUser.memId}" />

								<div class="row gtr-uniform">



									<div class="col-12">
										<h5>주문하시는 분*</h5>
										<input type="text" name="" id="demo-email"
											value="${authUser.memName}" placeholder="${authUser.memName}"
											readonly="readonly" />
									</div>

									<div class="col-12">
										<h5>휴대전화*</h5>
										<input type="text" name="" id="demo-email"
											value="${authUser.memPhone}"
											placeholder="${authUser.memName}" readonly="readonly" />
									</div>

									<div class="col-12">
										<h5>이메일*</h5>
										<input type="text" name="" id="demo-email"
											value="${authUser.memId}" placeholder="${authUser.memName}"
											readonly="readonly" />
									</div>


								</div>
							</div>











							<div class="col-6 col-12-medium">

								<h3>배송 정보</h3>




								<div class="col-12">
									<h5>배송지 선택*</h5>
									<div class="col-6 col-12-small">
										<input TYPE='radio' id='r1' name='addCheck' value='0'
											checked="checked" /> <label for='r1' id="onDisplay1">기본
											배송지</label> <input TYPE="radio" id='r2' name='addCheck' value='1' />
										<label for='r2'>신규 배송지</label>
									</div>
								</div>
								<div id="noneDiv1" style="display:;">
									<div class="col-12">
										<h5>받으시는 분*</h5>
										<input type="text" name="memName" id="demo-email"
											value="${authUser.memName}" placeholder="${authUser.memName}"
											readonly="readonly" />
									</div>

									<div class="col-12">
										<h5>주소</h5>
										<ul class="actions">
											<li><input type="text" id="memPostc1" name="memPostc1" placeholder="우편번호"
												readonly="readonly" value="${authUser.memPostc}"></li>
											<li><input type="button"
												onclick="sample4_execDaumPostcode()" value="우편번호 찾기"
												readonly="readonly"><br></li>

										</ul>
										<ul class="actions">

											<li><input type="text" name="memDoro" id="memDoro"
												value="${authUser.memDoro}" placeholder="도로명주소"
												readonly="readonly"></li>
											<li><input type="text" name="memJibun" id="memJibun"
												value="${authUser.memJibun}" placeholder="지번주소"
												readonly="readonly"></li>
											<li><span id="guide" name="memadd"
												style="color: #999; display: none"></span></li>
											<li><input type="text" name="memAdd" id="memAdd"
												value="${authUser.memAdd}" placeholder="상세주소"
												readonly="readonly"></li>
											<li><input type="hidden" name="add"
												id="satraAddress" value="" placeholder="참고항목"></li>

mple4_ex
										</ul>
									</div>

									<div class="col-12">
										<h5>휴대전화*</h5>
										<input type="text" name="memPhone" id="demo-email"
											value="${authUser.memPhone}"
											placeholder="${authUser.memName}" readonly="readonly" />
									</div>
									<div class="col-12">
										<h5>배송 메세지</h5>
										<input type="text" name="memPs" id="demo-email" value=""
											placeholder="배송 메세지를 입력해주세요!" />
									</div>

								</div>




								<div id="noneDiv2" style="display: none;">
									<div class="col-12">
										<h5>받으시는 분*</h5>
										<input type="text" name="name" id="demo-email" value=""
											placeholder="받으시는 분" />
									</div>

									<div class="col-12">
										<h5>주소</h5>
										<ul class="actions">
											<li><input type="text" id="Postc" name="postc" placeholder="우편번호"></li>
											<li><input type="button"
												onclick="sample4_execDaumPostcode()" value="우편번호 찾기"><br></li>

										</ul>
										<ul class="actions">

											<li><input type="text" name="doro" id="Doro" value=""
												placeholder="도로명주소"></li>
											<li><input type="text" name="jibun" id="Jibun"
												value="" placeholder="지번주소"></li>
											<li><span id="guide" 
												style="color: #999; display: none"></span></li>
											<li><input type="text" name="add" id="memAdd" value=""
												placeholder="상세주소"></li>
											<li><input type="hidden" name="memadd"
												id="sample4_extraAddress" value="" placeholder="참고항목"></li>


										</ul>
									</div>

									<div class="col-12">
										<h5>휴대전화*</h5>
										<input type="text" name="phone" id="demo-email" value=""
											placeholder="010-XXXX-XXXX" />
									</div>
									<div class="col-12">
										<h5>배송 메세지</h5>
										<input type="text" name="ps" id="demo-email" value=""
											placeholder="배송 메세지를 입력해주세요!" />
									</div>
								</div>




							</div>





						</div>

					</div>

					<div id="main" class="alt">
						<section id="one">

							<div class="inner">

								<div class="table-wrapper">
									<table class=alt>
										<tbody>
											<tr>
												<td
													style="font-size: 14px; text-align: center; vertical-align: middle"><b>결제금액</b></td>
												<td style="font-size: 12px; vertical-align: middle"><%=totalPay%>원</td>

											</tr>
											<tr>
												<td
													style="font-size: 14px; width: 100px; text-align: center; vertical-align: middle"><b>포인트
														사용</b></td>
												<td style="font-size: 12px; vertical-align: middle">사용가능
													포인트 : <span name="left_pnt"><%=totalPoint %></span>
													<span style="float: right">포인트는 100p단위로 사용 가능합니다.</span>
												</td>
											</tr>
											<tr>
												<td
													style="font-size: 14px; width: 100px; text-align: center; vertical-align: middle">
													<span> <input type="number" name="use_pnt"
														id="use_pnt" min="0" max="<%=totalPay%>"
														onchange="changePoint(<%=totalPay%>,<%=totalPoint%>,0,100)"
														style="color: black;"></span> p <span> ( 남은포인트 : </span><span
													name="left_pnt" id="left_pnt"><%=totalPoint %></span>p )
												</td>
												<td
													style="font-size: 12px; vertical-align: middle; padding: 0;">
													<p class="bold txt_red">
														최종 결제 금액 : <span class="bold txt_red" id="result_pnt"><%=totalPay%></span>
														원
													</p>
												</td>
											</tr>
										</tbody>
									</table>
								</div>

							</div>
							<div class="inner">
					
								<h5>결제 수단*</h5>

								<div class="row gtr-uniform">

									<div class="col-4 col-12-small">
										<input type="radio" id="demo-priority-low"
											name="demo-priority" checked> <label
											for="demo-priority-low">신용카드</label>
									</div>
									<div class="col-4 col-12-small">
										<input type="radio" id="demo-priority-normal"
											name="demo-priority"> <label
											for="demo-priority-normal">무통장 입금</label>
									</div>
									<div class="col-4 col-12-small">
										<input type="radio" id="demo-priority-high"
											name="demo-priority"> <label for="demo-priority-high">카카오페이</label>
									</div>
								</div>


							


									<input style="text-align: center; display: block; margin: 0 auto;" type="submit" value="결제" class="button icon solid fa-download"></input>
						



							</div>
						</section>
					</div>


				</section>



			</div>





		</div>
	</form>






	<!-- Contact -->
	<section id="contact">
		<div class="inner">
			<section class="split">
				<section>
					<div class="contact-method">
						<span class="icon solid alt fa-envelope"></span>
						<h3>Email</h3>
						<a href="#">information@untitled.tld</a>
					</div>
				</section>
				<section>
					<div class="contact-method">
						<span class="icon solid alt fa-phone"></span>
						<h3>Phone</h3>
						<span>(000) 000-0000 x12387</span>
					</div>
				</section>
				<section>
					<div class="contact-method">
						<span class="icon solid alt fa-home"></span>
						<h3>Address</h3>
						<span>1234 Somewhere Road #5432<br /> Nashville, TN 00000<br />
							United States of America
						</span>
					</div>
				</section>
			</section>
		</div>
	</section>

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
