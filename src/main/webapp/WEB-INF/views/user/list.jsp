<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	
	<h2>리스트</h2>

	<table>
		<tr>
			<td>번호</td>
			<td>아이디</td>
			<td>이름</td>
			<td>이메일</td>
			<td>전화번호</td>
			<td>생년월일</td>
		</tr>
		<c:forEach  items="${users}" var="members">
		<tr>
			<td>${members.idx}</td>
			<td>${members.userId}</td>
			<td>${members.userName}</td>
			<td>${members.email}</td>
			<td>${members.phone}</td>
			<td>${members.regDate}</td>
		</tr>		
		</c:forEach>		
	</table>
	<div>
	<c:forEach var="i" begin="1" end="5">
		<strong><c:out value="${i}" /></strong>
	</c:forEach>
	</div>

</body>
</html>