<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ include file="/WEB-INF/views/common/header.jsp" %>

<div class="card m-2">
	<div class="card-header">
		게시물 내용
	</div>
	<div class="card-body">
		<div>
			<div>
				<div>
					<p>
						<span>번호:</span> 
						<span>${board.bno}</span>
					</p>
					
					<p>
						<span>제목:</span> 
						<span>${board.btitle}</span>
					</p>
					
					<p>
						<span>글쓴이:</span> 
						<span>${board.mid}</span>
					<p>
					
					<p>
						<span>날짜:</span> 
						<span><fmt:formatDate value="${board.bdate}" pattern="yyyy-MM-dd HH.mm.ss"/></span> <br/>
					</p>
					
					<c:if test="${board.battachoname !=null}">
						<p>
							<span>첨부:</span> 
							<span>
								${board.battachoname}
								<c:if test="${board.battachsname != null}">
									<!-- 첨부파일이 파일 시스템에 저장되어 있는 경우 -->
									<a href="filedownload1?bno=${board.bno}" class="btn btn-info btn-sm ml-2">다운로드</a>
									<img src="filedownload1?bno=${board.bno}" height="100"/>
								</c:if>
								<c:if test="${board.battachdata != null}">
									<!-- 첨부파일이 직접 메모리(DB)에 저장한 경우 -->
									<a href="filedownload2?bno=${board.bno}" class="btn btn-info btn-sm ml-2">다운로드</a>
									<!-- src의 속성값은 완전한 응답 HTTP가 되어야 한다 -->
									<!-- 1. 서버의 정적 리소스를 요청해서 응답을 받는 경우 -->
									<!-- 2. 요청 경로를 이용해서 컨트롤러에서 생성된 응답을 받는 경우(첫번째의 경우와 동일) -->
									<!-- 만약 데이터를 직접 넣어야 할 경우 src="data:MIME;base64, base64로 인코딩된 데이터 (컨트롤러에서 변환작업) -->
									<img src="data:${board.battachtype};base64, ${base64Img}" height="100"/>
								</c:if>	
							</span>
							<br/>
						</p>
					</c:if>
				</div>
				
				<div>
					<span class="title">내용:</span> <br/>
					<textarea style="width:100%" readonly>${board.bcontent}</textarea>
				</div>
				
				<a class="btn btn-info btn-sm mt-2" href="getboardList">목록</a>
				<a class="btn btn-info btn-sm mt-2" href="updateBoardForm?bno=${board.bno}">수정</a>
				<a class="btn btn-info btn-sm mt-2" href="deleteBoard?bno=${board.bno}">삭제</a>
				
			</div>
		</div>
	</div>
</div>

<%@ include file="/WEB-INF/views/common/footer.jsp" %>