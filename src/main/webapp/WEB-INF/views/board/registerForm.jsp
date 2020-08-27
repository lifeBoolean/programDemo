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
		<p class="h24">게시판</p>
	
		<form action="registerProcess" method="post" enctype="multipart/form-data">	
		<dl>
			<dt>제목</dt>
			<dd><input type="text" name="title" id="title" /></dd>
			<dt>이름</dt>
			<dd><input type="text" name="writer" id="writer" /></dd>
			<dt>첨부파일</dt>
			<dd><input type="file" multiple="multiple" name="files" /></dd>
			<dt>내용</dt>
			<dd>
				<textarea rows="5" name="content"></textarea>
			</dd>
		</dl>
			<button type="submit" id="boardSubmit">등록</button>
		</form>
	</div>
	
</body>
</html>
