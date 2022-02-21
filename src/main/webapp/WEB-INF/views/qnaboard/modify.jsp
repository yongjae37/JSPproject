<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML>
<!--
	Forty by HTML5 UP
	html5up.net | @ajlkn
	Free for personal and commercial use under the CCA 3.0 license (html5up.net/license)
-->
<html>
	<head>
		<title>문의글 남기기</title>
		<meta charset="utf-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no" />
		<link rel="stylesheet" href="assets/css/main.css" />
		<link rel="stylesheet" href="assets/css/noscript.css" />
		
		<script>
		function check_pw(){
			var f = document.form;
			if(f.password.value == ""){
				alert("비밀번호를 입력해주세요");
				f.password.focus();
				return false;
			}
			f.submit();
		}
		
		</script>
		
	</head>
	<body class="is-preload">

		<!-- Wrapper -->
			<div id="wrapper">

				<c:import url="/WEB-INF/views/includes/header.jsp"></c:import>


				<!-- Contact -->
					<section id="contact">
						<div class="inner">
							<section>
								<form name="form" method="post" action="/mysiteB/qna">
								<input type="hidden" name="a" value="modify" />
								<input type="hidden" name="qnaNo" value="${QnaboardVo.qnaNo }"/>
								
								 
								<div class="fields">
									
										<c:if test="${QnaboardVo.pass != null }">
										<div class="field half">
											<label for="name">닉네임</label>
											<input type="text" name="nickname" id="nickname" value="${QnaboardVo.nickname}" readonly/>
											
										</div>
										</c:if>
										
										<c:if test="${QnaboardVo.pass == null }">
										<div class="field half">
											<label for="name">작성자</label>
											<input type="text" name="memName" id="memName" value= "${authUser.memName}" readonly/>
										</div>
										</c:if>
										
										<div class="field half">
											<label for="type">질문 유형</label>
											<select name="type">
												<option ${QnaboardVo.type == "회원 문의"?"selected":"" } value="회원 문의">회원 문의</option>
												<option ${QnaboardVo.type == "결제 문의"?"selected":"" } value="결제 문의">결제 문의</option>
												<option ${QnaboardVo.type == "배송 문의"?"selected":"" } value="배송 문의">배송 문의</option>
												<option ${QnaboardVo.type == "기타 문의"?"selected":"" } value="기타 문의">기타 문의</option>
											</select>
										</div>
										
										<div class="field">
											<label for="title">제목</label>
											<input type="text" name="title" id="title" value="${QnaboardVo.title}" />
										</div>
										<div class="field">
											<label for="content">내용</label>
											<textarea name="content" id="content" rows="6">${QnaboardVo.content}</textarea>
										</div>
										
										<c:if test="${QnaboardVo.pass != null }">
										<div class="field half">
											<label for="pass">비밀번호</label>
											<input type="password" name="password" id="pass" value="" placeholder="비밀번호를 입력해주세요"/>
											<ul class="actions">
											<li><input type="button" value="수정" class="primary" onclick="check_pw()" /></li>
											<li><input type="reset" value="Clear" /></li>
											<li><input type="checkbox" id="private" name="private" value="1">
											<label for="private">비공개</label>
											<input type="hidden" id="public" name="private" value="0">
											</li>
									</ul>
										</div>
										</c:if>
									
									
										<c:if test="${QnaboardVo.pass == null }">
										<ul class="actions">
										<li><input type="submit" value="수정" class="primary" /></li>
										<li><input type="reset" value="Clear" /></li>
										<li><input type="checkbox" id="private" name="private" value="1">
										<label for="private">비공개</label>
										<input type="hidden" id="public" name="private" value="0">
										</li>
										</ul>
										</c:if>		
									</div>	
								</form>
							</section>
							
						</div>
					</section>

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