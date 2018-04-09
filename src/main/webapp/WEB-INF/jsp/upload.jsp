<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="./webuploader/webuploader.css" />

<body>
	<%-- <form action="${pageContext.request.contextPath}/upload.action" name="uploadForm" enctype="multipart/form-data" method="post"  >
		<input type="file" name="file" />
		<input type="submit" value="提交"/>
	</form> --%>
	<a href="./webuploader/WebUploader.html">上传</a>
	<div id="uploader">  
                        <!--用来存放文件信息-->  
                        <div id="thelist"></div>  
                        <div>  
                            <div id="attach"></div>  
                            <!--  <input type="button" value="上传" id="upload"/> -->  
                        </div>  
                    </div>  
	<script type="text/javascript"
		src="./js/jquery-3.2.1.min.js"></script>
	<script type="text/javascript"
		src="./webuploader/webuploader.html5only.min.js"></script>
	<script type="text/javascript"
		src="./webuploader/webuploader.js"></script>
		<script type="text/javascript"
		src="./webuploader/webuploader.min.js"></script>
		<script type="text/javascript" src="./webuploader/upload3.js"></script>  
</body>
</html>