<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
</head>
<body>
	<script type="text/javascript">
		window.onload=function(){
			if(basePage=="projectPublish"){
				
				
			}
			
		}
		function changeAuthor(){
			$.ajax({
                type: "post",
                url: "${pageContext.request.contextPath}/changeAuthor.do", 
                dataType: "json",
                success: function(data){
                	location.reload();
                }
            });
		}
	</script>
	<div class="navbar navbar-default" role="navigation">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle collapsed"
				data-toggle="collapse" data-target=".navbar-collapse">
				<span class="sr-only">Toggle navigation</span> <span
					class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
			<a class="" href="index.html"><span class="navbar-brand"><span
					class="fa fa-paper-plane"></span> 商学院</span></a>
		</div>

		<div class="navbar-collapse collapse" style="height: 1px;">
			<ul id="main-menu" class="nav navbar-nav navbar-right">
				<li class="dropdown hidden-xs"><a href="#"
					class="dropdown-toggle" data-toggle="dropdown"> <span
						class="glyphicon glyphicon-user padding-right-small"
						style="position: relative; top: 3px;"></span> ${userinfo.tName} <i
						class="fa fa-caret-down"></i>
				</a>

					<ul class="dropdown-menu">
						<c:if test="${userinfo.authorlever == 1}">
							<li><a onclick="changeAuthor()">切换权限  (当前: <c:choose> <c:when test='${openAuthor == "false"}'>普通  </c:when>
										<c:otherwise>
											管理员
										</c:otherwise>
									</c:choose> )
							</a></li>
							<li class="divider"></li>
						</c:if>

						<li><a tabindex="-1"
							href="${pageContext.request.contextPath}/loginOut.action">登出</a></li>
					</ul></li>
			</ul>

		</div>
	</div>
	</div>


	
</body>
</html>