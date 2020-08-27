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
		<p class="h24">게시판수정</p>
	
		<form action="modifyProcess" method="post">
			<input type="hidden" name="idx" value="${modifyOne.idx}" />
			<input type="text" name="viewCount" value="${pagination.viewCount}" />
			<input type="text" name="searchType" value="${pagination.searchType}" />
			<input type="text" name="keyword" value="${pagination.keyword}" />
			<input type="text" name="page" value="${pagination.page}" />
			<ul>
				<li>제목</li>
				<li><input type="text" name="title" id="title" value="${modifyOne.title}" /></li>
				<li>이름</li>
				<li><input type="text" name="writer" id="writer" value="${modifyOne.writer}" /></li>
				<li>내용</li>
				<li><textarea rows="5" name="content">${modifyOne.content}</textarea></li>
			</ul>
			<button type="submit" id="boardSubmit">수정</button>
		</form>
	</div>
	
</body>
</html>
