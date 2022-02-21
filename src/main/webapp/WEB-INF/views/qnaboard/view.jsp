<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<% pageContext.setAttribute( "newLine", "\n" ); %>
<!DOCTYPE HTML>
<html>
<head>
<title>문의글</title>
<meta charset="utf-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, user-scalable=no" />
<link rel="stylesheet" href="assets/css/main.css" />
<link rel="stylesheet" href="assets/css/noscript.css" />
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
						<h1>문의하세요</h1>
					</header>

					<form>

								<h3>제목	${QnaboardVo.title }</h3>
					
							<div class="col-6 col-12-small">
								<ul class="actions stacked">
									<li style="text-align: right"><a href="/mysiteB/qna?a=list"
										class="button small">목록으로</a></li>
								</ul>
							</div>

						
						<c:if test="${QnaboardVo.memNo == 0}">
						<h3>작성자 ${QnaboardVo.nickname}</h3>
						</c:if>
						
						<c:if test="${QnaboardVo.nickname == null}">
						<h3>작성자 ${QnaboardVo.memName}</h3>
						</c:if>
						
						<h3>질문 유형 ${QnaboardVo.type}</h3>

						<div class="box">
							<p>${fn:replace(QnaboardVo.content, newLine,"<br>") }</p>
						</div>
						
						<table>
							<tr>
									<th>▲ &nbsp 다음글</th>
									<c:choose>
										<c:when test="${nextVo.qnaNo == '0' }">
											<th>다음글이 없습니다.</th>
											<th></th>
											<th></th>
										</c:when>
										<c:otherwise>
											<th><a href="/mysiteB/qna?a=read&qnaNo=${nextVo.qnaNo }">${nextVo.title }</a></th>
											<th>${nextVo.type }</th>
											<th>${nextVo.regDate }</th>
										</c:otherwise>
									</c:choose>
								</tr>
						
							<tr>
								<th>▼ &nbsp 이전글 </th>
								<c:choose>
										<c:when test="${prevVo.qnaNo == '0' }">
											<th>이전글이 없습니다.</th>
											<th></th>
											<th></th>
										</c:when>
										<c:otherwise>
											<th><a href="/mysiteB/qna?a=read&qnaNo=${prevVo.qnaNo }">${prevVo.title }</a></th>
											<th>${prevVo.type }</th>
											<th>${prevVo.regDate }</th>
										</c:otherwise>
									</c:choose>
							</tr>
							
							
							
						</table>
						

						<div class="col-6 col-12-xsmall">
						<form name="modifyform" method="post" action="/mysiteB/qna">
							<ul class="actions stacked">
								<c:if test="${(QnaboardVo.memNo != 0 && authUser.memNo == QnaboardVo.memNo) } ">
									
									<input type="hidden" name="qnaNo" value="${QnaboardVo.qnaNo }"/>
									<li><button class="button" type="submit"  name="a" value="modifyform" >글수정</button></li>
									<li><button class="button primary" type="submit"  name="a" value="delete" >글삭제</button></li>
								</c:if>
							</ul>
						</form>
						
						<form name="modifyform" method="post" action="/mysiteB/qna">
							<ul class="actions stacked">
								<c:if test="${QnaboardVo.memNo == 0 }">
									<div class="field half">
										<label for="pass">비밀번호</label>
										<input type="password" name="password" value="" id="pass" placeholder="비밀번호를 입력해주세요"/>
									
									<input type="hidden" name="qnaNo" value="${QnaboardVo.qnaNo }"/>
									<input type="hidden" name="qnapass" value="${QnaboardVo.pass }"/>
									<li><button class="button" type="submit" name="a" value="modifypass" >글수정</button></li>
									<li><button class="button" type="submit"  name="a" value="deletepass" >글삭제</button></li>
									</div>
								</c:if>
							</ul>
						</form>					
						
						
						</div>					
						</div>			
				</div>
				
			<table>
				<tbody>
						<th>
						<tr>
							<td style="font-size: 14px; width: 100px; text-align: center; vertical-align: middle"><b>↳답변</b></td>
							<td style="font-size: 12px; width: 120px; text-align: center; vertical-align: middle"><b>작성자</b></td>
							<td style="font-size: 12px; width: 200px; text-align: center; vertical-align: middle"><b>답변 내용</b></td>
							<td style="font-size: 12px; width: 120px; text-align: center; vertical-align: middle"><b>날짜</b></td>
						</tr>
						
						<c:forEach items="${list }" var="vo">
							<br>
						<tr>	
							<c:if test="${authUser.adminCk != 1 }">
							<td></td>
							</c:if>
							
							<c:if test="${authUser.adminCk == 1 }">
							<td>
							<form name="deleteanswerform" method="post" action="/mysiteB/qna">
							<button class="button" type="submit" name="a" value="deleteAns" >삭제</button>
							<input type="hidden" name="qnaNo" value="${QnaboardVo.qnaNo }"/>
							<input type="hidden" name="ansNo" value="${vo.ansNo }"/>
							</form>
							</td>
							</c:if>
							
							
							<td>판매자</td>
							<td>${vo.answer }</td>
							<td>${vo.regDate }</td>
						</tr>
						</c:forEach>
						
						</tbody>
						
						<c:if test="${authUser.adminCk == 1 }">
						<form name="answerform" method="post" action="/mysiteB/qna">
						<div class="field">
							<label for="answer">답변</label>
									<textarea name="answer" id="content" rows="6"></textarea>
							<input type="hidden" name="qnaNo" value="${QnaboardVo.qnaNo }"/>
							<input type="hidden" name="memNo" value="${authUser.memNo }"/>
						</div>
						
						
							<ul class="actions stacked">
									
									<li><button class="button" type="submit"  name="a" value="writeAns" >답변달기</button></li>
									</div>			
							</ul>
						</form>
						</c:if>
						
							
			</table>
			
						
			
			
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