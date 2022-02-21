<%@ page language="java" contentType="text/html; charset=UTF-8"
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
<title>Elements - Forty by HTML5 UP</title>
<meta charset="utf-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, user-scalable=no" />
<link rel="stylesheet" href="assets/css/main.css" />
<link rel="stylesheet" href="assets/css/noscript.css" />
<link rel="icon" href="images/favicon.ico" type="image/x-icon">

<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>

<script>
    //�� ���������� ���θ� �ּ� ǥ�� ��Ŀ� ���� ���ɿ� ����, �������� �����͸� �����Ͽ� �ùٸ� �ּҸ� �����ϴ� ����� �����մϴ�.
    function sample4_execDaumPostcode() {
        new daum.Postcode({
            oncomplete: function(data) {
                // �˾����� �˻���� �׸��� Ŭ�������� ������ �ڵ带 �ۼ��ϴ� �κ�.

                // ���θ� �ּ��� ���� ��Ģ�� ���� �ּҸ� ǥ���Ѵ�.
                // �������� ������ ���� ���� ��쿣 ����('')���� �����Ƿ�, �̸� �����Ͽ� �б� �Ѵ�.
                var roadAddr = data.roadAddress; // ���θ� �ּ� ����
                var extraRoadAddr = ''; // ���� �׸� ����

                // ���������� ���� ��� �߰��Ѵ�. (�������� ����)
                // �������� ��� ������ ���ڰ� "��/��/��"�� ������.
                if(data.bname !== '' && /[��|��|��]$/g.test(data.bname)){
                    extraRoadAddr += data.bname;
                }
                // �ǹ����� �ְ�, ���������� ��� �߰��Ѵ�.
                if(data.buildingName !== '' && data.apartment === 'Y'){
                   extraRoadAddr += (extraRoadAddr !== '' ? ', ' + data.buildingName : data.buildingName);
                }
                // ǥ���� �����׸��� ���� ���, ��ȣ���� �߰��� ���� ���ڿ��� �����.
                if(extraRoadAddr !== ''){
                    extraRoadAddr = ' (' + extraRoadAddr + ')';
                }

                // �����ȣ�� �ּ� ������ �ش� �ʵ忡 �ִ´�.
                document.getElementById('memPostc').value = data.zonecode;
                document.getElementById("memDoro").value = roadAddr;
                document.getElementById("memJibun").value = data.jibunAddress;
                
                // �����׸� ���ڿ��� ���� ��� �ش� �ʵ忡 �ִ´�.
                if(roadAddr !== ''){
                    document.getElementById("sample4_extraAddress").value = extraRoadAddr;
                } else {
                    document.getElementById("sample4_extraAddress").value = '';
                }

                var guideTextBox = document.getElementById("guide");
                // ����ڰ� '���� ����'�� Ŭ���� ���, ���� �ּҶ�� ǥ�ø� ���ش�.
                if(data.autoRoadAddress) {
                    var expRoadAddr = data.autoRoadAddress + extraRoadAddr;
                    guideTextBox.innerHTML = '(���� ���θ� �ּ� : ' + expRoadAddr + ')';
                    guideTextBox.style.display = 'block';

                } else if(data.autoJibunAddress) {
                    var expJibunAddr = data.autoJibunAddress;
                    guideTextBox.innerHTML = '(���� ���� �ּ� : ' + expJibunAddr + ')';
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
        function check_pw(){
 
            var pw = document.getElementById('pw').value;
            var SC = ["!","@","#","$","%"];
            var check_SC = 0;
 
            if(pw.length < 6 || pw.length>16){
                window.alert('��й�ȣ�� 6���� �̻�, 16���� ���ϸ� �̿� �����մϴ�.');
                document.getElementById('pw').value='';
            }
            for(var i=0;i<SC.length;i++){
                if(pw.indexOf(SC[i]) != -1){
                    check_SC = 1;
                }
            }
            if(check_SC == 0){
                window.alert('!,@,#,$,% �� Ư�����ڰ� �� ���� �ʽ��ϴ�.')
                document.getElementById('pw').value='';
            }
            if(document.getElementById('pw').value !='' && document.getElementById('pw2').value!=''){
                if(document.getElementById('pw').value==document.getElementById('pw2').value){
                    document.getElementById('check').innerHTML='��й�ȣ�� ��ġ�մϴ�.'
                    document.getElementById('check').style.color='blue';
                }
                else{
                    document.getElementById('check').innerHTML='��й�ȣ�� ��ġ���� �ʽ��ϴ�.';
                    document.getElementById('check').style.color='red';
                }
            }
        }
    </script>
    
<script>
      function check() {
 
        var f = document.form; 
    
 
        

        if (f.memName.value == "") {
            alert("�̸��� �Է����ֽʽÿ�");
            f.memName.focus();
            return false;
        }
        
       
        if (f.memPass.value == "") {
            alert("��й�ȣ�� �Է����ֽʽÿ�");
            f.memPass.focus();
            
            return false;
        }
 
        if (f.memPostc.value == "") {
            alert("�����ȣ�� �˻��Ͽ� �Է����ֽʽÿ�");
            f.memPostc.focus();
            return false;
        }
 
 
        if (f.memPhone2.value == "") {
            alert("��ȭ��ȣ�� �Է����ֽʽÿ�");
            f.memPhone2.focus();
            return false;
        }
        if (f.memPhone3.value == "") {
            alert("��ȭ��ȣ�� �Է����ֽʽÿ�");
            f.memPhone3.focus();
            return false;
        }
        
     
        
        f.submit();
 
    }   
      
   
 
</script>
    
    <script>
function checkAll() {
   
    if (!checkPassword(form.memId1.value, form.memPass.value,    form.password2.value)) {
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


function checkUserId(memId1) {

    var idRegExp = ^[A-Za-z0-9_\.\-]$; //���̵� ��ȿ�� �˻�(�̸��� ���İ˻�)
    if (!idRegExp.test(memId1)) {
        alert("���̵�� �̸��� �������� �Է��ؾ� �մϴ�!");
        form.memId1.value = "";
        form.memId1.focus();
        return false;
    }
    return true; //Ȯ���� �Ϸ�Ǿ��� ��
}

function checkPassword(id, memPass, password2) {
    //��й�ȣ�� �ԷµǾ����� Ȯ���ϱ�
    if (!checkExistData(memPass, "��й�ȣ��"))
        return false;
    //��й�ȣ Ȯ���� �ԷµǾ����� Ȯ���ϱ�
    if (!checkExistData(password2, "��й�ȣ Ȯ����"))
        return false;

    //���̵�� ��й�ȣ�� ���� ��..
    
    if (memId1 == memPass) {
        alert("���̵�� ��й�ȣ�� ���� �� �����ϴ�!");
        form.memPass.value = "";
        form.password2.value = "";
        form.password2.focus();
        return false;
    }
    return true; //Ȯ���� �Ϸ�Ǿ��� ��
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
						<h1>ȸ�� ���� ����</h1>
					</header>

					<hr class="major" />
					<div class="row">
						<c:import url="/WEB-INF/views/includes/navigation3.jsp"></c:import>

					
						<div class="col-6 col-12-medium">

						

							<form name=form id="mypage-form" name="modifyform" onsubmit="return !!(check() & checkAll());"
												method="post" action="/mysiteB/user">
												<input type="hidden" name="a" value="modify" /> 
												<input type="hidden" name="checked_id" value="${authUser.memId}" />

								<div class="row gtr-uniform">
									
									<div class="col-12">
									<h3>���̵�	${authUser.memId} </h3>
										
									</div>
									
									<div></div>
									
									<div class="col-12">
									<h3>��� ��ȣ*</h3>
										<input type="password" name="memPass" id="pw" value=""
											placeholder="password" onchange="check_pw()"/>
									</div>
									
									<div class="col-12">
									<h3>��� ��ȣ Ȯ��</h3>
										<input type="password" name="memPass_confirm" id="pw2" value=""
											placeholder="password Ȯ��" onchange="check_pw()"/><span
										id="check"></span>
									</div>
									
									
									<div class="col-12">
									<h3>�̸�*</h3>
										<input type="text" name="memName" id="name" value=""
											placeholder="${authUser.memName}" />
									</div>
									
									
									
									
									<div class="col-12">
									<h3>�ּ�</h3>
										<ul class="actions">
											<li><input type="text" id="memPostc" name="memPostc" placeholder="�����ȣ"></li>
											<li><input type="button" onclick="sample4_execDaumPostcode()" value="�����ȣ ã��"><br></li>
											
										</ul>
										<ul class="actions" >
										<li><input type="text" name = "memDoro" id="memDoro" placeholder="${authUser.memDoro}"></li>
											<li><input type="text" name = "memJibun" id="memJibun" placeholder="${authUser.memJibun}"></li>
											<li><span id="guide" name = "memAdd" style="color:#999;display:none"></span></li>
											<li><input type="text" name = "memAdd" id="memAdd" placeholder="${authUser.memAdd}"></li>
											<li><input type="hidden" name = "memAdd" id="sample4_extraAddress" placeholder="${authUser.memAdd}"></li>
										</ul>
									</div>
									
									
									
									<div class="col-12">
									
                                   <h3>�޴���ȭ</h3>	

									<ul class="actions">
								<li><select name="memPhone1" id="demo-category">
												<option value="" name="memPhone1" id="memPhone">-���� -</option>
												<option value="010" name="memPhone1" id="memPhone">010</option>
												<option value="011" name="memPhone1" id="memPhone">011</option>
												<option value="070" name="memPhone1" id="memPhone">070</option>
										</select></li>
										<li><input type="text" name="memPhone2" id="memPhone2"
											value="" placeholder="��ȭ��ȣ"></li>

										<li><input type="text" name="memPhone3" id="memPhone3"
											value="" placeholder="��ȭ��ȣ"></li>
									</ul>
								</div>
								
								<div class="col-12"><br></div>
								<div class="col-12">
										<ul class="actions">
										<li><input type="button" name="modify" onclick="check()"
											value="����" /></li>
									</ul>
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