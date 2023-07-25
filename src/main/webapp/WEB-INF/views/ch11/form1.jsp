<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@ include file="/WEB-INF/views/common/header.jsp" %>

<div class="card">
	<div class="card-header">Ch11. Spring Tag Library</div>
	<div class="card-body">
	 	<form:form method="post" modelAttribute="member">
	 		<div class="form-group">
	 			<label for="mid">ID : </label>
	 			<form:input path="mid" class="form-control"/>
	 		</div>
	 		<div class="form-group">
	 			<label for="mname">NAME : </label>
	 			<form:input path="mname" class="form-control"/>
	 		</div>
	 		<div class="form-group">
	 			<label for="mpassword">PASSWORD : </label>
	 			<form:password path="mpassword" class="form-control"/>
	 		</div>
	 		<form:hidden path="mnation" />
	 		<button class="btn btn-sm btn-info">submit</button>
	 	</form:form>
	</div>
</div>

<%@ include file="/WEB-INF/views/common/footer.jsp" %>