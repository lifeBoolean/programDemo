<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Home</title>
	<link rel="stylesheet" href="<c:url value="/resources/css/common.css"/>" />
	<link rel="stylesheet" href="<c:url value="/resources/css/jquery-ui.css"/>" />
	<link rel="stylesheet" href="<c:url value="/resources/js/jqgrid/css/ui.jqgrid.css"/>" />
	
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
	
	<script src="<c:url value="/resources/js/common.js"/>"></script>
	<script src="<c:url value="/resources/js/jqgridDemo.js"/>"></script>
	<script src="<c:url value="/resources/js/jquery-ui.js"/>"></script>
	<script src="<c:url value="/resources/js/jqgrid/js/i18n/grid.locale-kr.js"/>"></script>
	<script src="<c:url value="/resources/js/jqgrid/js/jquery.jqGrid.min.js"/>"></script>
</head>
<body>

	<h3>jqgrid 데모1</h3>

	<div class="row">
        <table id="list"></table>
        <div id="pager"></div>
    </div>
    
    
    
    
    


</body>
</html>
