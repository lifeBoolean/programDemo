<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Home</title>
	<link rel="stylesheet" href="<c:url value="/resources/css/jquery-ui.css"/>" />
	<link rel="stylesheet" href="<c:url value="/resources/jqGrid/css/ui.jqgrid.css"/>" />	
	<link rel="stylesheet" href="<c:url value="/resources/css/common.css"/>" />
	
	
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>	
	<script>
	// jQuery import 바로아래에 넣어 주면 됩니다.
	// Cannot read property 'msie' of undefined 에러 나올때
	jQuery.browser = {};
	(function () {
	    jQuery.browser.msie = false;
	    jQuery.browser.version = 0;
	    if (navigator.userAgent.match(/MSIE ([0-9]+)\./)) {
	        jQuery.browser.msie = true;
	        jQuery.browser.version = RegExp.$1;
	    }
	})();
	</script>
	<script src="<c:url value="/resources/js/jquery-ui.js"/>"></script>
	<script src="<c:url value="/resources/jqGrid/js/i18n/grid.locale-kr.js"/>"></script>
	<script src="<c:url value="/resources/jqGrid/js/jquery.jqGrid.min.js"/>"></script>
	<script src="<c:url value="/resources/js/common.js"/>"></script>
	<script src="<c:url value="/resources/js/jqgridDemo.js"/>"></script>
</head>
<body>

	<h3>jqgrid 데모1</h3>

	<div class="row">
        <table id="list"></table><br>
        <p>페이징</p>
        <div id="pager"></div>
    </div>
    
    <button type="submit" id="deleteBtn">삭제</button>
    
    
    
    
    


</body>
</html>
