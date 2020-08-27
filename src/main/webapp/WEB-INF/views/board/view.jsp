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
		<p class="h24">게시글 보기</p>
		
		<ul>
			<li>제목</li>
			<li>${readOne.title}</li>
			<li>이름</li>
			<li>${readOne.writer}</li>
			<li>내용</li>
			<li>${readOne.content}</li>
		</ul>
		<span>파일 목록</span>
		<div>
			<c:forEach var="fileList" items="${fileList}">
				<a href="#">${fileList.ORG_FILE_NAME}</a><br>
			</c:forEach>
		</div>
		<a href="modify?searchType=${pagination.searchType}&keyword=${pagination.keyword}&viewCount=${pagination.viewCount}&page=${pagination.page}&idx=${readOne.idx}">수정</a>
		
	</div>
	
</body>
</html>
