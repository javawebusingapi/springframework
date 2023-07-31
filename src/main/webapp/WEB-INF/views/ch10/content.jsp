<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/views/common/header.jsp" %>

<div class="card">
	<div class="card-header">Ch10. 에러/예외 처리</div>
	<div class="card-body">
	 	<div class="mt-2">
	 		<a href="handlingException1" class="btn btn-info btn-sm">try-catch</a>
	 	</div>
	 	<div class="mt-2">
	 		<a href="handlingException2" class="btn btn-info btn-sm">NullPointerException</a>
	 	</div>
	 	<div class="mt-2">
	 		<a href="handlingException3" class="btn btn-info btn-sm">ClassCastException</a>
	 	</div>
	 	<div class="mt-2">
	 		<a href="handlingException4" class="btn btn-info btn-sm">Ch10SoldOutException</a>
	 	</div>
	 	<div class="mt-2">
	 		<a href="handlingException5" class="btn btn-info btn-sm">Exception</a>
	 	</div>
	</div>
</div>

<%@ include file="/WEB-INF/views/common/footer.jsp" %>