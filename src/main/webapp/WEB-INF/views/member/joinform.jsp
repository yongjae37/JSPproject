<%@ page contentType="text/html; charset=utf-8"%>
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
<script
	src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script src="http://code.jquery.com/jquery-latest.js"></script>

<script>
    //본 예제에서는 도로명 주소 표기 방식에 대한 법령에 따라, 내려오는 데이터를 조합하여 올바른 주소를 구성하는 방법을 설명합니다.
    function sample4_execDaumPostcode() {
        new daum.Postcode({
            oncomplete: function(data) {
                // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

                // 도로명 주소의 노출 규칙에 따라 주소를 표시한다.
                // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
                var roadAddr = data.roadAddress; // 도로명 주소 변수
                var extraRoadAddr = ''; // 참고 항목 변수

                // 법정동명이 있을 경우 추가한다. (법정리는 제외)
                // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
                if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
                    extraRoadAddr += data.bname;
                }
                // 건물명이 있고, 공동주택일 경우 추가한다.
                if(data.buildingName !== '' && data.apartment === 'Y'){
                   extraRoadAddr += (extraRoadAddr !== '' ? ', ' + data.buildingName : data.buildingName);
                }
                // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
                if(extraRoadAddr !== ''){
                    extraRoadAddr = ' (' + extraRoadAddr + ')';
                }

                // 우편번호와 주소 정보를 해당 필드에 넣는다.
                document.getElementById('memPostc').value = data.zonecode;
                document.getElementById("memDoro").value = roadAddr;
                document.getElementById("memJibun").value = data.jibunAddress;
                
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
                if(data.autoRoadAddress) {
                    var expRoadAddr = data.autoRoadAddress + extraRoadAddr;
                    guideTextBox.innerHTML = '(예상 도로명 주소 : ' + expRoadAddr + ')';
                    guideTextBox.style.display = 'block';

                } else if(data.autoJibunAddress) {
                    var expJibunAddr = data.autoJibunAddress;
                    guideTextBox.innerHTML = '(예상 지번 주소 : ' + expJibunAddr + ')';
                    guideTextBox.style.display = 'block';
                } else {
                    guideTextBox.innerHTML = '';
                    guideTextBox.style.display = 'none';
                }
            }
        }).open();
    }
</script>


<script>
   $(document).ready(function (){   
     // 아이디체크  url : "api/emailCheck.jsp", "/mysite/user",
     $("#btn-checkid").on("click", function(){
    	 
    	 var str = "";

    	 var i = 0;
  	     for(i=0;i<10;i++)
  	     {
  	         var rIndex = Math.floor(Math.random()*3); 
  	         switch (rIndex) {
  	         case 0:
  	             // a-z
  	             str += String.fromCharCode(Math.floor(Math.random()*26) + 97);
  	             break;
  	         case 1:
  	             // A-Z
  	             str += String.fromCharCode(Math.floor(Math.random()*26) + 65);
  	             break;
  	         case 2:
  	             // 0-9
  	             str += Math.floor(Math.random()*10);
  	             break;
  	         }
  	     }

  	   document.getElementById("number").value = str;
    	 
    	 
       // json 형식으로 데이터 set
       var params = {
                 a      : "idcheck"
               , memId  : $("[name=memId1]").val() +"@"+ $("[name=memId2]").val()
               , number : $("[name=number]").val()
       }
    
       $.ajax({
         url : "api/idCheck.jsp",
         type : "post",
         data : params,
         dataType : "json",
         success : function(isExist) {
           console.log(isExist);
           if(isExist == false){
        	   alert("사용가능한 이메일 입니다.");
        	   $('#noneDiv').show();
           }else {
             alert("이미 사용중인 이메일 입니다.");
             document.getElementById("memId").value ='';
           }
         },
         error : function(XHR, status, error) {
             console.error(status + " : " + error);
           }
         });
       
       
       });
       
     });//ready
 
     </script>



<script>
        function check_pw(){
 
            var pw = document.getElementById('pw').value;
            var SC = ["!","@","#","$","%"];
            var check_SC = 0;
 
            if(pw.length < 6 || pw.length>16){
                window.alert('비밀번호는 6글자 이상, 16글자 이하만 이용 가능합니다.');
                document.getElementById('pw').value='';
            }
            for(var i=0;i<SC.length;i++){
                if(pw.indexOf(SC[i]) != -1){
                    check_SC = 1;
                }
            }
            if(check_SC == 0){
                window.alert('!,@,#,$,% 의 특수문자가 들어가 있지 않습니다.')
                document.getElementById('pw').value='';
            }
            if(document.getElementById('pw').value !='' && document.getElementById('pw2').value!=''){
                if(document.getElementById('pw').value==document.getElementById('pw2').value){
                    document.getElementById('check').innerHTML='비밀번호가 일치합니다.'
                    document.getElementById('check').style.color='blue';
                }
                else{
                    document.getElementById('check').innerHTML='비밀번호가 일치하지 않습니다.';
                    document.getElementById('check').style.color='red';
                }
            }
        }
    </script>


<script>
      function check() {
 
        var f = document.form; 
    
 
        

        if (f.memName.value == "") {
            alert("이름을 입력해주십시오");
            f.memName.focus();
            return false;
        }
        
        if (f.memId1.value == "") {
            alert("아이디를 입력해주십시오");
            f.memId.focus();
            return f = false;
        } 
        if (f.memPass.value == "") {
            alert("비밀번호를 입력해주십시오");
            f.memPass.focus();
            
            return false;
        }
 
        if (f.memPostc.value == "") {
            alert("우편번호를 검색하여 입력해주십시오");
            f.memPostc.focus();
            return false;
        }
 
 
        if (f.memPhone2.value == "") {
            alert("전화번호를 입력해주십시오");
            f.memPhone2.focus();
            return false;
        }
        if (f.memPhone3.value == "") {
            alert("전화번호를 입력해주십시오");
            f.memPhone3.focus();
            return false;
        }
        
        
        if (f.check_email.value == "") {
        	alert("이메일 인증을 해주세요");
            return false;
        }
        
        
        
        f.submit();
 
    }   
      
      
 
</script>

<script>

function mail_ck() {
	
	    
	 
     

     if (document.getElementById('mail').value == document.getElementById('number').value) {
         alert("인증이 완료 되었습니다.");
         $("input[name=check_email]").val('y');
         return true;
     } else {
    	 alert("인증 번호가 다릅니다.");
         return false;
     }
	
	
}


</script>


<script>
function checkAll() {
    if (!checkUserId(form.memId.value)) {
        return false;
    }
    if (!checkPassword(form.memId.value, form.memPass.value,    form.password2.value)) {
        return false;
    }
    if (!checkPhone(form.memPhone.value)) {
        return false;
    }
    if (!checkName(form.memName.value)) {
        return false;
    }
    

    return true;
}


function checkUserId(memId) {

    var idRegExp = ^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$; //아이디 유효성 검사(이메일 형식검사)
    if (!idRegExp.test(memId)) {
        alert("아이디는 이메일 형식으로 입력해야 합니다!");
        form.memId.value = "";
        form.memId.focus();
        return false;
    }
    return true; //확인이 완료되었을 때
}

function checkPassword(id, memPass, password2) {
    //비밀번호가 입력되었는지 확인하기
    if (!checkExistData(memPass, "비밀번호를"))
        return false;
    //비밀번호 확인이 입력되었는지 확인하기
    if (!checkExistData(password2, "비밀번호 확인을"))
        return false;

    //아이디와 비밀번호가 같을 때..
    if (memId == memPass) {
        alert("아이디와 비밀번호는 같을 수 없습니다!");
        form.memPass.value = "";
        form.password2.value = "";
        form.password2.focus();
        return false;
    }
    return true; //확인이 완료되었을 때
}




</script>

<script>
//아이디 가입할때 직접입력을 할 수 있는 스크립트
function selectEmail(ele){ 
	  var $ele = $(ele); 
      var $memId2 = $('input[name=memId2]'); // '1'인 경우 직접입력 
      if($ele.val() == "1"){ 
    	  $memId2.attr('readonly', false);
          $memId2.val(''); 
          } 
      else { $memId2.attr('readonly', true); 
          $memId2.val($ele.val()); 
     } 
}

</script>


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
						<h1>회원 가입</h1>
					</header>



					<hr class="major" />

					<!-- Elements -->
					<div class="row gtr-200">


						<form name=form method="post"
							onsubmit="return !!(check() & checkAll());"
							action="/mysiteB/user">
							<input type="hidden" name="a" value="join" />
							<div class="row gtr-uniform">
							
								<div class="col-6 col-12-xsmall">
									<input type="text" name="memName" id="name" value=""
										placeholder="Name" />
								</div>
								<div class="col-12">
									<ul class="actions">


										
										<li><input type="text" name="memId1" id="memId1" value="" placeholder="id" /></li>
										<li>@</li>
										
										<li><input name="memId2" type="text"></li>
										<li>
										<select name="select_email" onChange="selectEmail(this)"> 
								 		<option value="" selected>선택하세요</option> 
								 		<option value="naver.com">naver.com</option> 
								 		<option value="gmail.com">gmail.com</option> 
								 		<option value="hanmail.com">hanmail.com</option>
								  		<option value="1">직접입력</option> 
								  </select></li>
								  		<li><input id="btn-checkid" type="button" value="이메일 확인"></li>
								  
									</ul>
									
									
								</div>
								
								<div class="col-12" id="noneDiv" style="display: none;">
									
									<ul class="actions">
									<li>인증 번호 입력</li>
									<li><input type = "text" name = "mail"  id = "mail" value = "" /></li>
									<li><button onclick="mail_ck()" id="authCodeCheckBtn"
										type="button" class="btnChk">확인</button></li>
									
									</ul>
										<input type="hidden" name="number" id = "number" value = "" />
										<input type="hidden" name="check_email" value = "" />
										

								</div>

								<div class="col-6 col-12-xsmall">
									<input type="password" name="memPass" id="pw" value=""
										placeholder="password" onchange="check_pw()" />
								</div>
								<div class="col-6 col-12-xsmall">
									<input type="password" name="mempass_confirm" id="pw2" value=""
										placeholder="password 확인" onchange="check_pw()" /><span
										id="check"></span>
								</div>

								<div class="col-12">

									<ul class="actions">


										<li><select name="demo-category" id="demo-category">
												<option value="" name="memPhone1" id="memPhone">-선택 -</option>
												<option value="010" name="memPhone1" id="memPhone1">010</option>
												<option value="011" name="memPhone1" id="memPhone1">011</option>
												<option value="070" name="memPhone1" id="memPhone1">070</option>
										</select></li>
										<li><input type="text" name="memPhone2" id="memPhone2"
											value="" placeholder="전화번호"></li>

										<li><input type="text" name="memPhone3" id="memPhone3"
											value="" placeholder="전화번호"></li>
									</ul>
								</div>


								<div class="col-12">
									<ul class="actions">
										<li><input type="text" name="memPostc" id="memPostc"
											placeholder="우편번호"></li>
										<li><input type="button"
											onclick="sample4_execDaumPostcode()" value="우편번호 찾기"><br></li>

									</ul>
									<ul class="actions">


										<li><input type="text" name="memDoro" id="memDoro"
											value="" placeholder="도로명주소"></li>
										<li><input type="text" name="memJibun" id="memJibun"
											value="" placeholder="지번주소"></li>
										<li><span id="guide" name="memadd"
											style="color: #999; display: none"></span></li>
										<li><input type="text" name="memAdd" id="memAdd" value=""
											placeholder="상세주소"></li>
										<li><input type="hidden" name="memadd"
											id="sample4_extraAddress" value="" placeholder="참고항목"></li>
									</ul>
								</div>



								<div class="col-12">
									<ul class="actions">
										<li><input type="button" name="join" onclick="check()"
											value="회원 가입" /></li>
									</ul>
								</div>


							</div>
						</form>


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