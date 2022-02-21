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
<link rel="icon" href="images/favicon.ico" type="image/x-icon">

<script>
	function Rating() {
	};
	Rating.prototype.rate = 0;
	Rating.prototype.setRate = function(newrate) {
		//별점 마킹 - 클릭한 별 이하 모든 별 체크 처리
		this.rate = newrate;
		let items = document.querySelectorAll('.rate_radio');
		items.forEach(function(item, idx) {
			if (idx < newrate) {
				item.checked = true;
			} else {
				item.checked = false;
			}
		});
	}
	let rating = new Rating();
	document.addEventListener('DOMContentLoaded', function() {
		//별점선택 이벤트 리스너
		document.querySelector('.rating').addEventListener('click',
				function(e) {
					let elem = e.target;
					if (elem.classList.contains('rate_radio')) {
						rating.setRate(parseInt(elem.value));
					}
				})
	});
</script>

<script type="text/javascript" src="js/jquery-3.1.0.min.js"
	charset="utf-8">
	
</script>

<script type="text/javascript">
	// 이미지 정보들을 담을 배열
	var sel_files = [];
	// 파일 현재 필드 숫자 totalCount랑 비교값
	var fileCount = 0;
	// 해당 숫자를 수정하여 전체 업로드 갯수를 정한다.
	var totalCount = 2;

	$(document).ready(function() {
		$("#input_imgs").on("change", handleImgFileSelect);
	});

	function fileUploadAction() {
		console.log("fileUploadAction");
		$("#input_imgs").trigger('click');
	}

	function handleImgFileSelect(e) {

		// 이미지 정보들을 초기화
		sel_files = [];
		$(".imgs_wrap").empty();

		var files = e.target.files;
		var filesArr = Array.prototype.slice.call(files);

		var index = 0;
		
		// 파일 개수 확인 및 제한
	    if (fileCount + filesArr.length > totalCount) {
	      alert('파일은 최대 '+totalCount+'개까지 업로드 할 수 있습니다.');
	      return;
	    } else {
	    	 fileCount = fileCount + filesArr.length;
	    }

		
		filesArr.forEach(function(f) {
					if (!f.type.match("image.*")) {
						alert("확장자는 이미지 확장자만 가능합니다.");
						return;
					}

					sel_files.push(f);

					var reader = new FileReader();
					reader.onload = function(e) {
						var html = "<a href=\"javascript:void(0);\" onclick=\"deleteImageAction("
								+ index
								+ ")\" id=\"img_id_"
								+ index
								+ "\"><img src=\"" + e.target.result + "\" data-file='"+f.name+"' class='selProductFile' title='Click to remove'></a>";
						$(".imgs_wrap").append(html);
						index++;

					}
					reader.readAsDataURL(f);

				});
	}

	function deleteImageAction(index) {
		console.log("index : " + index);
		console.log("sel length : " + sel_files.length);

		sel_files.splice(index, 1);

		var img_id = "#img_id_" + index;
		$(img_id).remove();
	}

	function fileUploadAction() {
		console.log("fileUploadAction");
		$("#input_imgs").trigger('click');
	}

	function submitAction() {
		console.log("업로드 파일 갯수 : " + sel_files.length);
		var data = new FormData();

		for (var i = 0, len = sel_files.length; i < len; i++) {
			var name = "image_" + i;
			data.append(name, sel_files[i]);
		}
		data.append("image_count", sel_files.length);

		if (sel_files.length < 1) {
			alert("한개이상의 파일을 선택해주세요.");
			return;
		}

		var xhr = new XMLHttpRequest();
		xhr.open("POST", "./study01_af.php");
		xhr.onload = function(e) {
			if (this.status == 200) {
				console.log("Result : " + e.currentTarget.responseText);
			}
		}

		xhr.send(data);

	}
</script>


<style>
.rating .rate_radio+label {
	position: relative;
	display: inline-block;
	margin-left: -4px;
	z-index: 10;
	width: 60px;
	height: 60px;
	background-image: url('images/starrate.png');
	background-repeat: no-repeat;
	background-size: 60px 60px;
	cursor: pointer;
}

.rating .rate_radio:checked+label {
	background-color: #ff8;
}

.imgs_wrap img {
            max-width: 150px;
            margin-left: 50px;
            margin-right: 10px;
        }

body {
margin-left: 20px;
margin-right: auto;
}

</style>
</head>
<body class="is-preload">

	<c:import url="/WEB-INF/views/includes/header2.jsp"></c:import>


	<!-- Contact -->
	<section id="contact">
		<div class="inner">
			<section>
				<form method="post" action="/mysiteB/product?a=updateReview&revNo=${revNo}&proNo=${pvo.proNo}" enctype="multipart/form-data">
					
					<div class="fields">
						<div class="field half" style = "margin-top : 30px;">
							<label for="name"> 상품 [${pvo.proName}]</label>
							
						</div>
						<div class="field">
							<div class="review_rating">
								<div class="warning_msg">별점을 선택해 주세요.</div>
								<div class="rating">
									<!-- 해당 별점을 클릭하면 해당 별과 그 왼쪽의 모든 별의 체크박스에 checked 적용 -->
									<input type="checkbox" name="rate" id="rating1" value="1"
										class="rate_radio" title="1점"> <label for="rating1"></label>
									<input type="checkbox" name="rate" id="rating2" value="2"
										class="rate_radio" title="2점"> <label for="rating2"></label>
									<input type="checkbox" name="rate" id="rating3" value="3"
										class="rate_radio" title="3점"> <label for="rating3"></label>
									<input type="checkbox" name="rate" id="rating4" value="4"
										class="rate_radio" title="4점"> <label for="rating4"></label>
									<input type="checkbox" name="rate" id="rating5" value="5"
										class="rate_radio" title="5점"> <label for="rating5"></label>
								</div>
							</div>
						</div>
						<div class="input_wrap">
							<label className="input-file-button" for="input_imgs"> <img
								alt="" style="width: 100px; margin-left: 30px"
								src="images/main.png">
							</label> <input type="file" name = "fileName" id="input_imgs" style="display: none;"
								value="${rvo.file1 }" />

							<div style="margin-left: 30px">
								<div class="imgs_wrap" >
									<img id="img"  />
								</div>
							</div>
						</div>
						<div class="field">
							<label for="message">Title</label>
							<input name="title" id="message" type="text" value="${rvo.title}"/>
						</div>
						<div class="field">
							<label for="message">Message</label>
							<textarea name="content" id="message" rows="6" style = "width : 600px; margin-left : 20px">${rvo.content}</textarea>
						</div>
					</div>
					<ul class="actions">
						<li><input type="submit" value="Send Message" class="primary" /></li>
						<li><input type="reset" value="Clear" /></li>
					</ul>
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