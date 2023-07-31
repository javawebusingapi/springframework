<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@ include file="/WEB-INF/views/common/header.jsp" %>

<div class="card">
	<div class="card-header">Ch13. 회원 가입</div>
	<div class="card-body">
	 	<c:if test="${ch13Login == null}">
		 	<div class="mt-2">
		 		<a href="join" class="btn btn-info btn-sml">회원가입하기(join)</a>
		 	</div>
		 	<div class="mt-2">
		 		<a href="login" class="btn btn-info btn-sml">로그인(login)</a>
		 	</div>
	 	</c:if>
	 	<c:if test="${ch13Login != null}">
		 	<div class="mt-2">
		 		<a href="logout" class="btn btn-info btn-sml">로그아웃</a>
		 		현재  ${ch13Login.mid}가 로그인됨
		 	</div>
	 	</c:if>
	</div>
</div>
<div class="card">
	<div class="card-header">Ch13. 게시판</div>
	<div class="card-body">
	 	<div class="mt-2">
	 		<a href="getboardList" class="btn btn-info btn-sml">게시물 목록 가져오기(select)</a>
	 	</div>
	</div>
</div>
<%@ include file="/WEB-INF/views/common/footer.jsp" %>