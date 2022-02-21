<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
<title>Elements - Forty by HTML5 UP</title>
<meta charset="utf-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, user-scalable=no" />
<link rel="stylesheet" href="assets/css/main.css" />
<link rel="stylesheet" href="assets/css/noscript.css" />
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.6.1/css/all.css">
<style>
@import url("https://fonts.googleapis.com/css?family=Roboto:400,400i,700");

* {
  font-family: Roboto, sans-serif;
  padding: 0;
  margin: 0;
}

html{
  width: 100%;
  height: 100%;
}

.flexbox {
  width: 100%;
  height: 100%;
  display: flex;
  justify-content: right;
  align-items: center;
}

.search {
  margin: 20px;
}

.search > h3 {
  font-weight: normal;
}

.search > h1,
.search > h3 {
  color: white;
  margin-bottom: 15px;
  text-shadow: 0 1px #0091c2;
}

.search > div {
  display: inline-block;
  position: relative;
  filter: drop-shadow(0 1px #0091c2);
}

.search > div:after {
  content: "";
  background: white;
  width: 4px;
  height: 8px;
  position: absolute;
  top: 20px;
  left: 0px;
  transform: rotate(225deg);
}

.search > div > input {
  color: white;
  font-size: 16px;
  background: transparent;
  width: 25px;
  height: 25px;
  padding: 10px;
  border: solid 3px white;
  outline: none;
  border-radius: 35px;
  transition: width 0.5s;
}

.search > div > input::placeholder {
  color: #efefef;
  opacity: 0;
  transition: opacity 150ms ease-out;
}

.search > div > input:focus::placeholder {
  opacity: 1;
}

.search > div > input:focus,
.search > div > input:not(:placeholder-shown) {
  width: 250px;
}

input[type="text"]:focus {
	border-color: white;
	box-shadow: none;
}

</style>


<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js">$("input").click(function(){
	$(".search > div:after").fadeTo(1000,0.5);
});</script>


<script>
	function enterkey() {
		if (window.event.keyCode== 13) {
			
		}
	}
</script>


</head>

<div id="header">

	<ul>
		<c:choose>
			<c:when test="${authUser == null }">

				<!-- Header -->
				<header id="header" class="alt">
					<a href="/mysiteB/main" class="logo"><strong>shopping
							mall</strong> <span>by B team</span></a>
						
						
					<nav>
					<!-- 
					
					<input style="width:200px;" type="text">
					<input type="button" value="검색">
					<a href="#menu">Menu</a>
					
					 -->
					 
			
						
						
						<div class="flexbox">
						<form method="post" action="/mysiteB/product?a=productList">
							<select name="searchFrom" class="txt" style="width:auto; height: 2em">
								<option disabled selected value> - select - </option>
								<option value="proName">상품명</option>
								<option value="proDesc">본문에서</option>
								<option value="proCateg">장르</option>
							</select>
							<div class="search">
								<div>
									<input type="text" name="kwd" placeholder="Search . . ." required>
								</div>
							</div>
							</form>
						</div>
						<a href="#menu">Menu</a>
						
						
						
					</nav>
				</header>
				

				<!-- Menu -->
				<nav id="menu">
					<ul class="links">
						<li><a href="/mysiteB/main">Home</a></li>
						<li><a href="/mysiteB/user?a=mypage">cart</a></li>
					</ul>
					<ul class="actions stacked">
						<li><a href="/mysiteB/user?a=loginform"
							class="button primary fit">로그인</a></li>
						<li><a href="/mysiteB/user?a=joinform" class="button fit">회원 가입</a></li>
					</ul>
				</nav>

			</c:when>
			<c:otherwise>

				<!-- Header -->
				<header id="header" class="alt">
					<a href="/mysiteB/main" class="logo"><strong>shopping
							mall</strong> <span>by B team</span></a>
							
							
					<nav>
						<div class="flexbox">
							<div class="search">
								<div>
									<input type="text" placeholder="Search . . .">
								</div>
							</div>
						</div>
								<a href="/mysiteB/user?a=mypage" style="width:200px; text-align:center">${authUser.memName }님</a>
						<a href="#menu">Menu</a>
					</nav>
				</header>

				<!-- Menu -->
				<nav id="menu">
					<ul class="links">
						<li><a href="/mysiteB/main">Home</a></li>
						<li><a href="/mysiteB/orderInfo?a=showCart">cart</a></li>
					</ul>
					<ul class="actions stacked">
						<li><a href="/mysiteB/user?a=logout" class="button primary fit">로그 아웃</a></li>

					</ul>
				</nav>
			</c:otherwise>
		</c:choose>

	</ul>
</div>
<!-- /header -->



</html>