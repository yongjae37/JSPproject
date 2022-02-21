<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<meta name="viewport"
	content="width=device-width, initial-scale=1, user-scalable=no" />
<title>Insert title here</title>
<script language="JavaScript">
    function setCookie(name, value, expiredays) {
        var date = new Date();
        date.setDate(date.getDate() + expiredays);
        document.cookie = escape(name) + "=" + escape(value) + "; expires=" + date.toUTCString();
    }

    function closePopup() {
        if (document.getElementById("check").value) {
            setCookie("popupYN", "N", 1);
            self.close();
        }
    }
</script>

<style>
body {
	background-image: url('images/mainpop.jpg');
	background-repeat: no-repeat;
	background-size: cover;
}
</style>

</head>
<body>

	<input type="checkbox" id="check" onclick="closePopup();">
    <br />
    <fontsize=3> <b>하루에 한번만 보기</b> </font>



</body>
</html>