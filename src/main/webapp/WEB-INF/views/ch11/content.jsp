<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/views/common/header.jsp" %>

<div class="card">
	<div class="card-header">Ch11. Spring Tag Library</div>
	<div class="card-body">
	 	<div class="card">
         <div class="card-header">
            DTO 객체(Command Object)와 폼 연결
         </div>
         <div class="card-body">
            <a href="form1" class="btn btn-info btn-sm">form1</a>
         </div>
      </div>
      
      <div class="card">
         <div class="card-header">
            DTO 객체의 필드값을 select로 세팅
         </div>
         <div class="card-body">
            <a href="form2" class="btn btn-info btn-sm">form2</a>
         </div>
      </div>
      
      <div class="card">
         <div class="card-header">
            DTO 객체의 필드값을 checkbox로 세팅
         </div>
         <div class="card-body">
            <a href="form3" class="btn btn-info btn-sm">form3</a>
         </div>
      </div>
      
      <div class="card">
         <div class="card-header">
            DTO 객체의 필드값을 radio로 세팅
         </div>
         <div class="card-body">
            <a href="form4" class="btn btn-info btn-sm">form4</a>
         </div>
      </div>
      
      <div class="card">
         <div class="card-header">
            국제화를 적용한 폼
         </div>
         <div class="card-body">
            <a href="form5" class="btn btn-info btn-sm">form5</a>
         </div>
      </div>
	</div>
</div>

<%@ include file="/WEB-INF/views/common/footer.jsp" %>