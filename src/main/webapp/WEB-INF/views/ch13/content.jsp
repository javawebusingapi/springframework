<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@ include file="/WEB-INF/views/common/header.jsp" %>

<div class="card">
	<div class="card-header">Ch13. Service와 DAO(데이터 접근객체)</div>
	<div class="card-body">
	 	<div class="mt-2">
	 		<a href="insertboard" class="btn btn-info btn-sml">게시물 삽입(insert)</a>
	 	</div>
	 	<div class="mt-2">
	 		<a href="getboardList" class="btn btn-info btn-sml">게시물 목록 가져오기(select)</a>
	 	</div>
	 	<div class="mt-2">
	 		<a href="updateboard" class="btn btn-info btn-sml">게시물 수정(update)</a>
	 	</div>
	 	<div class="mt-2">
	 		<a href="deleteboard" class="btn btn-info btn-sml">게시물 삭제(delete)</a>
	 	</div>
	</div>
</div>

<%@ include file="/WEB-INF/views/common/footer.jsp" %>