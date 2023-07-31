<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/views/common/header.jsp" %>

<div class="card">
	<div class="card-header">Ch04. 요청 데이터의 유효성 검사</div>
	<div class="card-body">
	   <div class="card m-2">
         <div class="card-header">
            POST 방식으로 요청
         </div>
         <div class="card-body">
         	<%--onsubmit를 빼서 자바스크립트에서 처리하지 않음 --%>
            <form id="form1" method="post" action="method1">
               <div class="input-group">
                  <div class="input-group-prepend"><span class="input-group-text">param1</span></div>
                  <input type="text" name="param1" class="form-control" value="${ch04Form1.param1}">
                  <%-- taglib 추가 문법  / ch04Form1에 param1로 등록된 에러가 있다면 이 태그에 나타내라 (Html 태그가 아님), 브라우저에는 span태그로 들어간다. --%>
                  <form:errors path="ch04Form1.param1" cssClass="param1-error text-danger"/>
               </div>
               <div class="input-group">
                  <div class="input-group-prepend"><span class="input-group-text">param2</span></div>
                  <input type="text" name="param2" class="form-control" value="${ch04Form1.param2}" >
                  <%-- taglib 추가 문법  / ch04Form1에 param1로 등록된 에러가 있다면 이 태그에 나타내라 (Html 태그가 아님), 브라우저에는 span태그로 들어간다. --%>
                  <form:errors path="ch04Form1.param2" cssClass="param2-error text-danger"/>
               </div>
               <div class="input-group">
                  <div class="input-group-prepend"><span class="input-group-text">param3</span></div>
                  <input type="text" name="param3" class="form-control" value="${ch04Form1.param3}">
                  <%-- taglib 추가 문법  / ch04Form1에 param1로 등록된 에러가 있다면 이 태그에 나타내라 (Html 태그가 아님), 브라우저에는 span태그로 들어간다. --%>
                  <form:errors path="ch04Form1.param3" cssClass="param3-error text-danger"/>
               </div>
               <div class="input-group">
                  <div class="input-group-prepend"><span class="input-group-text">param4</span></div>
                  <div class="btn-group btn-group-toggle" data-toggle="buttons">
                     <label class="btn btn-secondary active">
                       <input type="radio" name="param4" <c:if test="${ch04Form1.param4}">checked</c:if>value="true"> true
                     </label>
                     <label class="btn btn-secondary">
                       <input type="radio" name="param4" <c:if test="${!ch04Form1.param4}">checked</c:if>value="false"> false
                     </label>
                  </div>
               </div>
               <div class="input-group">
                  <div class="input-group-prepend"><span class="input-group-text">param5</span></div>
                  <input type="date" name="param5" class="form-control" value='<fmt:formatDate value="${ch04Form1.param5}" pattern="yyyy-MM-dd"/>'>
                  <span class="param5-error text-danger"></span>
               </div>
               <input class="mt-2 btn btn-info btn-sm" type="submit" value="요청"/>
            </form>
         </div>
         <script>
            function checkData() {
            	const form = document.querySelector("#form1");
            	
               //form의 제출 기능을 off
               event.preventDefault();
               
               //유효성 검사 결과 변수
               let checkResult = true;
               
               //입력 길이 체크
               //name 속성인 경우 .을 쓸 수 있음 (form.param1.value)
               let param1 = form.param1.value;
             //let param1 = document.querySelector("#form0 [~~~~]").value
               const param1Error = document.querySelector("#form1 .param1-error");
               param1Error.innerHTML = "";
               if(param1 === "") {
                  param1Error.innerHTML = "필수 입력 사항";
                  checkResult = false;
               } else {
                  if(param1.length<8 || param1.length>15) {
                     param1Error.innerHTML = "8자 이상, 15자 이하로 입력";
                     checkResult = false;
                  };
               }
               
               //정규 표현식을 이용한 전화번호 형식 체크
               let param2 = form.param2.value;
               const param2Error = document.querySelector("#form1 .param2-error");
               param2Error.innerHTML = "";
               if(param2 === "") {
                  param2Error.innerHTML = "필수 입력 사항";
                  checkResult = false;
               } else {
                  const pattern = /^(010|011)-[0-9]{3,4}-[0-9]{4}$/;
                  const result = pattern.test(param2);
                  if(result === false) {
                     param2Error.innerHTML = "전화번호 형식이 아님";
                     checkResult = false;
                  }
               }
               
               //정규 표현식을 이용한 이메일 형식 체크
               let param3 = form.param3.value;
               const param3Error = document.querySelector("#form1 .param3-error");
               param3Error.innerHTML = "";
               if(param3 === "") {
                  param3Error.innerHTML = "필수 입력 사항";
                  checkResult = false;
               } else {
                  const pattern = /([\w-]+(?:\.[\w-]+)*)@((?:[\w-]+\.)*\w[\w-]{0,66})\.([a-z]{2,6}(?:\.[a-z]{2})?)/i;
                  const result = pattern.test(param3);
                  if(result === false) {
                     param3Error.innerHTML = "이메일 형식이 아님";
                     checkResult = false;
                  }
               }
               
               //날짜가 비었는지 체크
               let param5 = form.param5.value;
               console.log(param5);
               const param5Error = document.querySelector("#form1 .param5-error");
               param5Error.innerHTML = "";
               if(param5 === "") {
                  param5Error.innerHTML = "필수 입력 사항";
                  checkResult = false;
               } 
               
               //서버로 제출할지 말지 결정
               if(checkResult) {
            	  //코드로 action 속성에 지정된 경로로 데이터를 제출
                  form.submit();
               }
            }
         </script>
      </div>
   
      <div class="card m-2">
         <div class="card-header">
            AJAX로 요청
         </div>
         <div class="card-body">
            <form id="form2" name="form2" action="method2">
               <div class="input-group">
                  <div class="input-group-prepend"><span class="input-group-text">param21</span></div>
                  <input type="text" id="param21" name="param21" class="form-control" value="${ch04Form2.param21}">
                  <%-- taglib 추가 문법  / ch04Form1에 param1로 등록된 에러가 있다면 이 태그에 나타내라 (Html 태그가 아님), 브라우저에는 span태그로 들어간다. --%>
                  <form:errors path="ch04Form2.param21" cssClass="param21-error text-danger"/>
               </div>
               <div class="input-group">
                  <div class="input-group-prepend"><span class="input-group-text">param22</span></div>
                  <input type="text" id="param22" name="param22" class="form-control" value="${ch04Form2.param22}">
                  <%-- taglib 추가 문법  / ch04Form1에 param1로 등록된 에러가 있다면 이 태그에 나타내라 (Html 태그가 아님), 브라우저에는 span태그로 들어간다. --%>
                  <form:errors path="ch04Form2.param22" cssClass="param22-error text-danger"/>
               </div>
               <div class="input-group">
                  <div class="input-group-prepend"><span class="input-group-text">param23</span></div>
                  <input type="text" id="param23" name="param23" class="form-control" value="${ch04Form2.param23}">
                  <%-- taglib 추가 문법  / ch04Form1에 param1로 등록된 에러가 있다면 이 태그에 나타내라 (Html 태그가 아님), 브라우저에는 span태그로 들어간다. --%>
                  <form:errors path="ch04Form2.param23" cssClass="param23-error text-danger"/>
               </div>
               <div class="input-group">
                  <div class="input-group-prepend"><span class="input-group-text">param24</span></div>
                  <div class="btn-group btn-group-toggle" data-toggle="buttons">
                     <label class="btn btn-secondary active">
                       <input type="radio" id="radio1" name="param24" <c:if test="${ch04Form2.param24}">checked</c:if> value="true"> true
                     </label>
                     <label class="btn btn-secondary">
                       <input type="radio" id="radio2" name="param24" <c:if test="${ch04Form2.param24}">checked</c:if> value="false"> false
                     </label>
                  </div>
               </div>
               <div class="input-group">
                  <div class="input-group-prepend"><span class="input-group-text">param25</span></div>
                  <input type="date" id="param25" name="param25" class="form-control" value='<fmt:formatDate value="${ch04Form2.param25}" pattern="yyyy-MM-dd"/>'>
               </div>
               <div class="mt-2">
	               <button class="btn btn-info btn-sm" type="submit" value="요청">POST 방식 요청</button>
	            </div>
            </form>
         </div>
         <script>
            function requestPost() {
               const param21 = $("#param21").val(); //주민번호: xxxxxx-1,2,3,4xxxxxx
               const param22 = $("#param22").val(); //년월일: 19680315
               const param23 = $("#param23").val(); //패스워드: 알파벳으로시작 최소 8자 초대 10
               const param24 = $("#form2 input[name=param24]:checked").val();
               const param25 = $("#param25").val();
               
               let checkData = true;
               /*
               const param1Error = $("#form2 .param1-error");
               param1Error.html("");
               if(param1 === "") {
                  param1Error.html("필수 입력 사항");
                  checkData = false;
               } else {
                  const pattern = /^\d{2}([0]\d|[1][0-2])([0][1-9]|[1-2]\d|[3][0-1])[-]*[1-4][0-9]{6}$/;
                  const result = pattern.test(param1);
                  if(result === false) {
                     param1Error.html("주민번호 형식이 아님");
                     checkData = false;
                  }
               } */
               
               if(checkData) {
                  $.ajax({
                     url:"method2",
                     method:"post",
                     data: {
                        param21:param21, 
                        param22, 
                        param23, 
                        param24, 
                        param25
                     },
                     success : function(data){}
                  })
                
               }
            }
         </script>
      </div>
      
      <div class="card m-2">
         <div class="card-header">
            서버측 유효성 검사
         </div>
         <div class="card-body">
            <div class="card m-2">
               <div class="card-header">
                  회원 가입 폼
               </div>
               <div class="card-body">         
                  <form id="form3" method="post" action="join">
                     <div class="input-group">
                        <div class="input-group-prepend"><span class="input-group-text">mid</span></div>
                        <input type="text" name="mid" class="form-control" value="${Ch04Join.mid}" autocomplete="username">
                        <%-- taglib 추가 문법  / ch04Form1에 param1로 등록된 에러가 있다면 이 태그에 나타내라 (Html 태그가 아님), 브라우저에는 span태그로 들어간다. --%>
                  <form:errors path="ch04Join.mid" cssClass="mid-error text-danger"/>
                     </div>
                     <div class="input-group">
                        <div class="input-group-prepend"><span class="input-group-text">mpassword</span></div>
                        <input type="password" name="mpassword" class="form-control" value="${Ch04Join.mpassword}" autocomplete="current-password">
                        <%-- taglib 추가 문법  / ch04Form1에 param1로 등록된 에러가 있다면 이 태그에 나타내라 (Html 태그가 아님), 브라우저에는 span태그로 들어간다. --%>
                  <form:errors path="ch04Join.mpassword" cssClass="mpassword-error text-danger"/>
                     </div>
                     <div class="input-group">
                        <div class="input-group-prepend"><span class="input-group-text">memail</span></div>
                        <input type="text" name="memail" class="form-control" value="${Ch04Join.memail}">
                        <%-- taglib 추가 문법  / ch04Form1에 param1로 등록된 에러가 있다면 이 태그에 나타내라 (Html 태그가 아님), 브라우저에는 span태그로 들어간다. --%>
                 		<form:errors path="ch04Join.memail" cssClass="memail-error text-danger"/>
                     </div>
                     <div class="input-group">
                        <div class="input-group-prepend"><span class="input-group-text">mtel</span></div>
                        <input type="text" name="mtel" class="form-control" value="${Ch04Join.mtel}">
                        <%-- taglib 추가 문법  / ch04Form1에 param1로 등록된 에러가 있다면 이 태그에 나타내라 (Html 태그가 아님), 브라우저에는 span태그로 들어간다. --%>
                  		<form:errors path="ch04Join.mtel" cssClass="mtel-error text-danger"/>
                     </div>
                     <input class="btn btn-info" type="submit" value="가입"/>
                  </form>
               </div>
            </div>
         
            <div class="card m-2">
               <div class="card-header">
                  로그인 폼
               </div>
               <div class="card-body">
                  <form id="form4" method="post" action="login">
                     <div class="input-group">
                        <div class="input-group-prepend"><span class="input-group-text">mid</span></div>
                        <input type="text" name="mid" class="form-control" value="${Ch04Login.mid}">
                        <%-- taglib 추가 문법  / ch04Form1에 param1로 등록된 에러가 있다면 이 태그에 나타내라 (Html 태그가 아님), 브라우저에는 span태그로 들어간다. --%>
                  		<form:errors path="ch04Login.mid" cssClass="mtel-error text-danger"/>
                     </div>
                     <div class="input-group">
                        <div class="input-group-prepend"><span class="input-group-text">mpassword</span></div>
                        <input type="password" name="mpassword" class="form-control" value="${Ch04Login.mpassword}">
                        <%-- taglib 추가 문법  / ch04Form1에 param1로 등록된 에러가 있다면 이 태그에 나타내라 (Html 태그가 아님), 브라우저에는 span태그로 들어간다. --%>
                  		<form:errors path="ch04Login.mpassword" cssClass="mtel-error text-danger"/>
                     </div>
                     <input class="btn btn-info" type="submit" value="로그인"/>
                  </form>
               </div>
            </div>
         </div>
      </div>
	</div>
</div>

<%@ include file="/WEB-INF/views/common/footer.jsp" %>