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
		<p class="h24">정보수정</p>
	
		<form action="modifyProcess" method="post">
		<dl>
			<dt>아이디</dt>
			<dd><input type="text" name="userId" id="userId" value="${member.userId}" /></dd>
			<dt>이름</dt>
			<dd><input type="text" name="userName" id="userName" value="${member.userName}" /></dd>
			<dt>전화번호</dt>
			<dd><input type="text" name="phone" id="phone" value="${member.phone}" /></dd>
			<dt>이메일</dt>
			<dd><input type="text" name="email" id="email" value="${member.email}" /></dd>
		</dl>
			<button type="submit" id="modifySubmit">수정</button>
		</form>
	</div>
	
	<dl>
		<dt>아이디</dt>
		<dd><input type="text" name="userId" id="userId" value="${user.userId}" /></dd>
		<dt>이름</dt>
		<dd><input type="text" name="userName" id="userName" value="${user.userName}" /></dd>
		<dt>전화번호</dt>
		<dd><input type="text" name="phone" id="phone" value="${user.phone}" /></dd>
		<dt>이메일</dt>
		<dd><input type="text" name="email" id="email" value="${user.email}" /></dd>
	</dl>
	
</body>
</html>
