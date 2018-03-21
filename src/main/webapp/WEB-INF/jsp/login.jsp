<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'login.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.2.1.min.js"></script>
  	<script type="text/javascript">
  		function req(){
  			$.ajax({
  				type:"post",
  				url:"${pageContext.request.contextPath}/reqJson.action",
  				contentType:"application/json;charset=utf-8",

  				success:function(data){
  					alert(data);
  				}
  			});
  		}
  	</script>
  </head>
  
  <body>
  	<c:if test="{allerrors != null}">
  		<c:forEach items="${allerrors}" var="error">
             	错误信息：${ error.defaultMessage}
         </c:forEach>
  	</c:if>
  	<c:if test="true">
  		nininin
  	</c:if>
    <form action="${pageContext.request.contextPath}/setUser.action" name="userForm" method="post" enctype="multipart/form-data">
    	name:<input name="name" id="name"/><br/>
    	age:<input name="age" id="age"/><br/>
    	<input type="submit" value="submit">
    	<input name="file" type="file" value="upload"/>
    </form>
	
    <image src="images/a1.jpg"/>
  </body>
</html>
