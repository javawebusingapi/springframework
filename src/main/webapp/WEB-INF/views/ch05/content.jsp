<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@ include file="/WEB-INF/views/common/header.jsp" %>

<div class="card m-2">
	<div class="card-header">Ch05. 요청 헤더 얻기 및 쿠키 사용</div>
	<div class="card-body">
	 	<div class="m-2">
	 		<a href="getHeaderValue" class="btn btn-sm btn-info">요청 헤더 값 얻기</a>
	 	</div>
	 	<div class="m-2">
	 		<a href="createCookie" class="btn btn-sm btn-info">쿠키 생성</a>
	 		<a href="getCookie" class="btn btn-sm btn-info">쿠키 값 얻어오기</a>
	 		<a href="javascript:getCookie()" class="btn btn-sm btn-info">쿠키 얻기(JavaScript) (setHttpOnly가 false일 때 사용 가능)</a>
	 		<script>
	 			function getCookie(){
	 				console.log(document.cookie);
	 			}
	 		</script>
	 	</div>
	</div>
</div>

<%@ include file="/WEB-INF/views/common/footer.jsp" %>