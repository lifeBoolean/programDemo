<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Home</title>
	<link rel="stylesheet" href="<c:url value="/resources/css/common.css"/>" />
	<script src="http://code.jquery.com/jquery-latest.js"></script>
	<script src="<c:url value="/resources/js/common.js"/>"></script>
</head>
<body>
	<div class="wrap">
		<p class="h24">로그인</p>
	
		<form action="loginProcess" method="post">	
		<dl>
			<dt>아이디</dt>
			<dd><input type="text" name="userId" id="userId" /></dd>
			<dt>비밀번호</dt>
			<dd><input type="text" name="userPw" id="userPw" /></dd>
			
		</dl>
			<button type="submit" id="memberSubmit">등록</button>
		</form>
	</div>
	
</body>
</html>
