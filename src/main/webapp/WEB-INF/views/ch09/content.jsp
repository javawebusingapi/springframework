<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/views/common/header.jsp" %>

<div class="card">
	<div class="card-header">Ch09. File Upload & Download</div>
	<div class="card-body">
		<div class="card">
         <div class="card-header">
            Form 태그를 이용한 FileUpload
         </div>
         <div class="card-body">
            <form method="post" enctype="multipart/form-data" action="fileupload">
               <div class="form-group">
                  <label for="title">File Title</label> 
                  <input type="text" class="form-control" id="title" name="title" placeholder="제목">
               </div>
               <div class="form-group">
                  <label for="desc">File Description</label> 
                  <input type="text" class="form-control" id="desc" name="desc" placeholder="설명">
               </div>
               <div class="form-group">
                   <label for="attach">Example file input</label>						<%-- 파일을 여러개 올릴 수 있게 된다. --%>
                   <input type="file" class="form-control-file" id="attach" name="attach" <%--multiple="multiple"--%>>
                 </div>
                 <%-- 방법 1 --%>
                 <button class="btn btn-info btn-sm">Form 파일 업로드</button>
                 <%-- 방법 2 --%>
                 <a href="javascript:fileupload()" class="btn btn-info btn-sm">AJAX 파일 업로드</a>
            </form>
         </div>
         <script>
            var saveFilename = "${saveFilename}";
         
         	function fileupload() {
               //입력된 정보를 얻기
               const title = $("#title").val();
               const desc = $("#desc").val();
               //엘리먼트 객체를 직접 가져와야 하기 때문에 JQuery를 사용할 수 없다.
               const attach = document.querySelector("#attach").files[0];
               
               //Multipart/form-data
               const formData = new FormData();
               //문자 파트
               formData.append("title", title);
               formData.append("desc", desc);
               //파일 파트
               formData.append("attach", attach);
               
               //Ajax로 서버로 전송
               $.ajax({
                  url: "fileuploadAjax",
                  method: "post",
                  data: formData,
                  cache: false,        //파일이 포함되어 있으니, 브라우저 메모리에 저장하지 마라 (크기가 너무 크기 때문에 캐시를 주지 않음)
                  processData: false,  //title=xxx&desc=yyy& 방식으로 만들지 마라 
                  contentType: false,   //파트마다 Content-Type이 포함되기 때문에 따로 헤더에 Content-Type에 추가하지 마라
               	  success : function(data){
        		      console.log(data);
                   	  if(data.result === "success") {
                      	window.alert("파일 전송이 성공됨");
                     	 }
                   	  saveFilename = data.saveFilename;
                   	  //파일로 다운로드
                   	  $("#link1").attr("href", "filedownload?saveFilename="+saveFilename);
                   	  //올린 파일을 바로 볼 수 있게 만들기
                   	  $("#img1").attr("src", "filedownload?saveFilename="+saveFilename);
                   	  }
                    });
               	}
         </script>
      </div>
   
      <div class="card">
         <div class="card-header">
            File Downlaod
         </div>
         <div class="card-body">
         	<!-- 서버에 있는 세션값을 넣는거임 (자바스크립트가 아님) -->
          	<a id="link1" href="filedownload?saveFilename=${saveFilename}" class="btn btn-info btn-sm" >파일 다운로드</a>
            <a href="javascript:filedownload()" class="btn btn-info btn-sm">파일 보기</a>
            <hr/>	
            <img id="img1" src="filedownload?saveFilename=${saveFilename}" width="200px" class="mr-2" alt="이미지 파일이 없습니다."/>
            <img id="downloadImg" width="200px"/>
         </div>
         <script type="text/javascript">
            function filedownload() {
            	//파일보기 버튼 클릭 시 올린 파일 볼 수 있음
               $("#downloadImg").attr("src", "filedownload?saveFilename="+saveFilename);
            }
         </script>
      </div>
	</div>
</div>

<%@ include file="/WEB-INF/views/common/footer.jsp" %>