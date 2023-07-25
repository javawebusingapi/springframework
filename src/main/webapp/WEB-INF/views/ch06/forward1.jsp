<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!-- 소스 복사/붙여넣기 -->
<%@ include file="/WEB-INF/views/common/header.jsp" %>

<div class="card">
	<div class="card-header">Forward된 페이지1</div>
	<div class="card-body">
	 	<c:if test="${loginStatus}">
	 		<%--forward1.jsp에서 완전히 forward2.jsp로 이동 --%>
	 		<jsp:forward page="/WEB-INF/views/ch06/forward2.jsp" />
	 	</c:if>
	</div>
</div>

<%@ include file="/WEB-INF/views/common/footer.jsp" %>