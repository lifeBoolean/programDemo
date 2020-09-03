<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Home</title>
	<link rel="stylesheet" href="<c:url value="/resources/css/common.css"/>" />	
	<script src="http://code.jquery.com/jquery-latest.min.js"></script>
	<script src="<c:url value="/resources/js/common.js"/>"></script>
</head>
<body>
	<button type="button" onClick="test();">전송</button>
	<h1>
		Hello world!  2222
	</h1>

	<P>  The time on the server is ${serverTime}. </P>
	
	
	<c:if test="${user == null }">
	<div>
		<a href="${pageContext.request.contextPath}/user/join">회원가입</a>
		<a href="${pageContext.request.contextPath}/user/login">로그인</a>
	</div>
	</c:if>
	
	<c:if test="${user != null}">
	<div>
		<p>${user.userName}(${user.userId})님 환영합니다.</p>
		<a href="${pageContext.request.contextPath}/user/modify">정보수정</a>
		<a href="${pageContext.request.contextPath}/user/logout">로그아웃</a>
	</div>
	</c:if>
	<c:if test="${msg == false}">
		<p>로그인 실패</p>
	</c:if>
	
	<p>
		<a href="${pageContext.request.contextPath}/first">첫페이지</a>
	</p>
	
	<p><a href="${pageContext.request.contextPath}/user/list">회원리스트</a></p>
	
	<a href="${pageContext.request.contextPath}/board/upload">업로드등록</a><br>
	<a href="${pageContext.request.contextPath}/board/register">게시판등록</a><br>
	<a href="${pageContext.request.contextPath}/board/list">게시판목록</a><br>
	<a href="${pageContext.request.contextPath}/board/rooping">루핑</a><br>
	<a href="${pageContext.request.contextPath}/jsonDemo/thisme">JSON</a><br>
	<a href="${pageContext.request.contextPath}/jsonForm">JSON 테스트입력</a><br>
	<a href="${pageContext.request.contextPath}/jsonView">JSON View</a><br>
	<a href="${pageContext.request.contextPath}/board/jqgrid">jqgrid</a><br>
	

</body>
</html>