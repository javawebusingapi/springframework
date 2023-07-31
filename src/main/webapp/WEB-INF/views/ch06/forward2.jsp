<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!-- header.jsp로 이동해서 실행하고, 그 결과를 삽입 -->
<jsp:include page="/WEB-INF/views/common/header.jsp"/>

<div class="card">
	<div class="card-header">Forward된 페이지2</div>
	<div class="card-body">
		<p><%=request.getAttribute("userName") %></p>
	 	응애
	</div>
</div>

<!-- footer.jsp로 이동해서 실행하고, 그 결과를 삽입 -->
<jsp:include page="/WEB-INF/views/common/footer.jsp"/>