<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<ul>
	<li><a href="">목록</a>
	<li><a href="">작성</a>
	<li>
		<c:if test="${member != null}"><a href="${pageContext.request.contextPath}/member/logout">로그아웃</a></c:if>
		<c:if test="${member == null}"><a href="${pageContext.request.contextPath}/member/login">로그인</a></c:if>
	</li>
	<li>${member.userName}님 반갑습니다.</li>
</ul>