<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/views/common/header.jsp" %>

<div class="card">
	<div class="card-header">Ch08. Session 이용 (로그인)</div>
	<div class="card-body">
		<c:if test="${login == null}">
			<form method="POST" action="login">
	    		<div class="form-group">
	          		<label for="mid">Member ID:</label>
	          		<input type="text" class="form-control" id="mid" name ="mid">
	        	</div>
	        	<div class="form-group">
	          		<label for="mpassword">Member Password:</label>
	          		<input type="password" class="form-control" id="mpassword" name="mpassword">
	        	</div>
	        	<button type="submit" class="btn btn-primary btn-sm">로그인</button>
     		</form>
		</c:if>
		<c:if test="${login != null}">
			<img src="${pageContext.request.contextPath}/resources/images/image/${login.mid}.jpg" width="30"/>
			현재 ${login.mid}님이 로그인 하셨습니다.<br>
			<a href="logout" class="btn btn-danger btn-sm">로그아웃</a>
		</c:if>
	</div>	
	<c:if test="${login != null}">
		<div class="card-header">Ch08. Session 이용 (장바구니)</div>
		<div class="card-body">
		 	<form method="post" action="addCart">
		 		<div class="form-group">
		 			<label for="sell">상품선택</label>
		 			<select name="name" class="form-control" id="name">
		 				<option value="item1">아이템1</option>
			 			<option value="item2">아이템2</option>
			 			<option value="item3">아이템3</option>
			 			<option value="item4">아이템4</option>
			 			<option value="item5">아이템5</option>
		 			</select>
		 		</div>
		 		<div class="form-group">
		 			<label for="amount">수량</label>
		 			<input type="number" id="ammount" name="amount" value="1"/>
		 		</div>
		 		<button type="submit" class="btn btn-info btn-sm mt-2">장바구니 넣기</button>
		 	</form>
		 	
		 	<a href="clearCart" class="btn btn-info btn-sm mt-2">장바구니 비우기</a>
		 	
		 	<hr>
		 	
		 	<p>장바구니 내용</p>
		 	<ul>
		 		<c:forEach var="item" items="${cart}">
		 			<li>${item.name}, ${item.amount}개</li>
		 		</c:forEach>
		 	</ul>
		</div>
	</c:if>
</div>

<%@ include file="/WEB-INF/views/common/footer.jsp" %>