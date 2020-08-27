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
		<p class="h24">회원가입</p>
	
		<form action="jsonDemo" method="post">	
		<dl>
			<dt>아이디</dt>
			<dd><input type="text" name="id" id="userId" /></dd>
			<dt>비밀번호</dt>
			<dd><input type="text" name="pw" id="userPw" /></dd>
		</dl>
			<button type="submit" id="memberSubmit">등록</button>
		</form>
	</div>
	
	<div id="result"></div>
	
	<button onclick="test()" type="button">Ajax</button> 
	<script> 
	var obj = {"name": "kim", "age": 30};
	function test() { 
		$.ajax({ 
			url: "<c:url value="/jsonProcess" />", 
			type: "post", 
			data: JSON.stringify(obj), 
			dataType: "json", 
			contentType: "application/json", 
			success: function(data) { 
				alert("성공" + data);
				
				$.each(data, function(key, val) {
					$("#result").append(key + " : " + val + "<br>");
				})
			}, 
			error: function(errorThrown) { 
				alert("에러내용: " + errorThrown.statusText);
			} 
		}); 
	} </script> 
	

	
</body>
</html>
