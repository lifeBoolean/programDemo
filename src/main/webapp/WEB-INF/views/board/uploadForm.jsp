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
	
		<form action="uploadProcess" method="post" enctype="multipart/form-data">	
		<dl>
			<dt>첨부파일</dt>
			<dd><input type="file" multiple="multiple" name="files" /></dd>
		</dl>
			<button type="submit" id="boardSubmit">등록</button>
		</form>
	</div>
	
</body>
</html>
