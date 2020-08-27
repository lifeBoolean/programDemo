<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="<c:url value="/resources/css/common.css"/>" />
<script src="http://code.jquery.com/jquery-latest.js"></script>
<script src="<c:url value="/resources/js/common.js"/>"></script>
</head>
<body>
	
	<h2>리스트</h2>
	<div class="searchWrap">
		<form action="list">
			<input type="text" name="viewCount" value="${pagination.viewCount}" /><br />
			<select name="searchType">
				<option value="title" <c:out value="${pagination.searchType == 'title'? 'selected' : ''}" />>제목</option>
				<option value="writer" <c:out value="${pagination.searchType == 'writer'? 'selected' : ''}" />>이름</option>
				<option value="content" <c:out value="${pagination.searchType == 'content'? 'selected' : ''}" />>내용</option>
			</select><br />
			<input type="text" name="keyword" value="${pagination.keyword}" /><br />
			<button type="submit">검색</button>
		</form>
	</div>

	<table>
		<tr>
			<td>번호</td>
			<td>제목</td>
			<td>작성자</td>
			<td>내용</td>
			<td>작성일</td>
			<td>조회수</td>
		</tr>
		<c:forEach  items="${list}" var="list">
		<tr>
			<td>${list.idx}</td>
			<td><a href="view?searchType=${pagination.searchType}&keyword=${pagination.keyword}&viewCount=${pagination.viewCount}&page=${pagination.page}&idx=${list.idx}">${list.title}</a></td>
			<td>${list.writer}</td>
			<td>${list.content}</td>
			<td>${list.regDate}</td>
			<td>${list.hit}</td>
		</tr>		
		</c:forEach>		
	</table>	
	
	<div class="asd">
	  <ul>
	  	<li><a href="list?searchType=${pagination.searchType}&keyword=${pagination.keyword}&viewCount=${pagination.viewCount}&page=1">시작페이지</a></li>
	    <c:if test="${pagination.prev}">
	    	<li><a href="list?searchType=${pagination.searchType}&keyword=${pagination.keyword}&viewCount=${pagination.viewCount}&page=${pagination.startNo-1}">이전</a></li>
	    </c:if>
	
	    <c:forEach begin="${pagination.startNo}" end="${pagination.endNo}" var="pageNo">
	    	<c:if test="${pagination.page == pageNo}">
	    	<li><a href="list?searchType=${pagination.searchType}&keyword=${pagination.keyword}&viewCount=${pagination.viewCount}&page=${pageNo}" class="ftRedColor">${pageNo}</a></li>
	    	</c:if>
	    	<c:if test="${pagination.page != pageNo}">
	    	<li><a href="list?searchType=${pagination.searchType}&keyword=${pagination.keyword}&viewCount=${pagination.viewCount}&page=${pageNo}">${pageNo}</a></li>
	    	</c:if>
	    </c:forEach>
	
	    <c:if test="${pagination.next}">
	    	<li><a href="list?searchType=${pagination.searchType}&keyword=${pagination.keyword}&viewCount=${pagination.viewCount}&page=${pagination.endNo+1}">다음</a></li>
	    </c:if>
	    <li><a href="list?searchType=${pagination.searchType}&keyword=${pagination.keyword}&viewCount=${pagination.viewCount}&page=${pagination.totalPage}">마지막페이지</a></li>
	  </ul>
	</div>
	
	<div>
		<a href="${pageContext.request.contextPath}">홈</a> | <a href="list">목록</a> | <a href="register">작성</a>
	</div>
	<div>
		총게시물 수: ${pagination.totalCount}
	</div>
	<div>
		화면 글 수: ${pagination.viewCount}
	</div>
	<div>
		현재페이지: ${pagination.page}
	</div>
	<div>
		총페이지2: ${pagination.totalPage}
	</div>
	<div>
		첫페이지startNo: ${pagination.startNo}
	</div>
	<div>
		끝페이지endNo: ${pagination.endNo}
	</div>
	<div>
		시작로우: ${pagination.rowStart}
	</div>
	<div>
		끝로우: ${pagination.rowEnd}
	</div>
	<div>
		이전목록페이지번호: ${pagination.prev}
	</div>
	<div>
		다음목록페이지번호: ${pagination.next}
	</div>
	<div>
		화면수 : 
		<a href="list?viewCount=10">10</a>
		<a href="list?viewCount=20">20</a>
		<a href="list?viewCount=30">30</a>
	</div>
	<div>
		페이징 : 
		<a href="list?viewCount=10&page=1">1</a>
		<a href="list?viewCount=10&page=2">2</a>
		<a href="list?viewCount=20&page=3">3</a>
		<a href="list?page=4">4</a>
		<a href="list?page=23">23</a>
	</div>

</body>
</html>