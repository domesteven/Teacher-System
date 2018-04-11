<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html lang="en">
<head>
<meta charset="utf-8">
<title>Bootstrap Admin</title>
<meta content="IE=edge,chrome=1" http-equiv="X-UA-Compatible">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="">
<meta name="author" content="">

<link href='http://fonts.googleapis.com/css?family=Open+Sans:400,700'
	rel='stylesheet' type='text/css'>
<link rel="stylesheet" type="text/css"
	href="lib/bootstrap/css/bootstrap.css">
<link rel="stylesheet" href="lib/font-awesome/css/font-awesome.css">

<script src="lib/jquery-1.11.1.min.js" type="text/javascript"></script>

<script src="lib/jQuery-Knob/js/jquery.knob.js" type="text/javascript"></script>
<script type="text/javascript">
        $(function() {
            $(".knob").knob();
        });
    </script>


<link rel="stylesheet" type="text/css" href="stylesheets/theme.css">
<link rel="stylesheet" type="text/css" href="stylesheets/premium.css">

<link href="stylesheets/toastr.min.css" rel="stylesheet" />
<script src="js/toastr.min.js"></script>

</head>
<body class=" theme-blue">

	<!-- Demo page code -->

	<script type="text/javascript">
		toastr.options.positionClass = 'toast-bottom-center';
        $(function() {
            var match = document.cookie.match(new RegExp('color=([^;]+)'));
            if(match) var color = match[1];
            if(color) {
                $('body').removeClass(function (index, css) {
                    return (css.match (/\btheme-\S+/g) || []).join(' ')
                })
                $('body').addClass('theme-' + color);
            }

            $('[data-popover="true"]').popover({html: true});
            
        });
       
        function up(){
        	
        	if("${currentPage-1}">0){
        		window.location.href="${pageContext.request.contextPath}/goTeachingTask.action?page=${currentPage-1}";	
        	}
        	
        }
        
        function down(){
        	
        	var max = eval("${pageTimes}");
        	
        	if(eval("${currentPage+1}") <= max){
        		window.location.href="${pageContext.request.contextPath}/goTeachingTask.action?page=${currentPage+1}";	
        	}
        	
        }
        
        function search(){
        	var num =  eval($("#searchPage").val());
        	var max = eval("${pageTimes}");
        	if(num>0  && num <= max){
        		window.location.href="${pageContext.request.contextPath}/goTeachingTask.action?page="+num;
        	}else{
        		toastr.warning("请输入正确的页码");
        		$("#searchPage").val("${currentPage}");
        	}
        }
        function setRecordId(id){
        	
        	$("#recordId").val(id);
            $('#myModal').modal('show');    
        }
        function delRecord(){
        	debugger
        	var id= $("#recordId").val();
        	$.ajax({
                type: "post",
                url: "${pageContext.request.contextPath}/delTaskTeaching.do", 
                data: {id:id},
                dataType: "json",
                success: function(data){
                	if(data.errcode == "-1"){
                		toastr.error(data.errmsg);
                	}else{
                		window.location.href="${pageContext.request.contextPath}/goTeachingTask.action?page=${currentPage}";
                	}
                	
                }
            });
        }
        
        
    </script>
	<style type="text/css">
#main {
	margin-left: auto;
	margin-right: auto;
	width: 800px;
	height: 500px;
	border: 1px solid black;
}

.input-group {
	width: 100px;
	float: left;
}

#but1 {
	float: left;
}

#but2 {
	float: left;
}
#total{
	margin-left:50px;
}
.navbar-default .navbar-brand,.navbar-default .navbar-brand:hover {
	color: #fff;
}
</style>

	<script type="text/javascript">
        $(function() {
            var uls = $('.sidebar-nav > ul > *').clone();
            uls.addClass('visible-xs');
            $('#main-menu').append(uls.clone());
        });
    </script>

	<!-- Le HTML5 shim, for IE6-8 support of HTML5 elements -->
	<!--[if lt IE 9]>
      <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
    <![endif]-->

	<!-- Le fav and touch icons -->
	<link rel="shortcut icon" href="../assets/ico/favicon.ico">
	<link rel="apple-touch-icon-precomposed" sizes="144x144"
		href="../assets/ico/apple-touch-icon-144-precomposed.png">
	<link rel="apple-touch-icon-precomposed" sizes="114x114"
		href="../assets/ico/apple-touch-icon-114-precomposed.png">
	<link rel="apple-touch-icon-precomposed" sizes="72x72"
		href="../assets/ico/apple-touch-icon-72-precomposed.png">
	<link rel="apple-touch-icon-precomposed"
		href="../assets/ico/apple-touch-icon-57-precomposed.png">


	<!--[if lt IE 7 ]> <body class="ie ie6"> <![endif]-->
	<!--[if IE 7 ]> <body class="ie ie7 "> <![endif]-->
	<!--[if IE 8 ]> <body class="ie ie8 "> <![endif]-->
	<!--[if IE 9 ]> <body class="ie ie9 "> <![endif]-->
	<!--[if (gt IE 9)|!(IE)]><!-->

	<!--<![endif]-->

	<%@include file="menu.jsp"%>

	<div class="content">
		<div class="btn-toolbar list-toolbar">
			<button class="btn btn-primary">
				<i class="fa fa-plus"></i> 新增
			</button>
			<button class="btn btn-default">导出</button>
			<div class="btn-group"></div>
		</div>
		<table class="table">
			<thead>
				<tr>
					<th>课程名称</th>
					<th>课程所属专业</th>
					<th>课程性质</th>
					<th>课时</th>
					<th>人数</th>
					<th>考核方式</th>
					<th>所属教学改革课题</th>
					<th style="width: 3.5em;"></th>
				</tr>
			</thead>
			<tbody>

				<c:forEach items="${list}" var="item">
					<tr>
						<td>${item.name}</td>
						<td>${item.major}</td>
						<td>${item.property}</td>
						<td>${item.hour}</td>
						<td>${item.countMan}</td>
						<td>${item.assessmentMethod}</td>
						<td>${item.teachingSubject}</td>
						<td><a href="user.html"><i class="fa fa-pencil"></i></a> <a onclick="setRecordId(${item.id})"><i
								class="fa fa-trash-o"></i></a></td>
					</tr>
				</c:forEach>


			</tbody>
		</table>
		<div class="pagination">
			<button id="but1" type="button" class="btn btn-primary"
				data-toggle="button" onclick="up()">&laquo;</button>

			<div class="input-group">
				<input type="text" id="searchPage" class="form-control"
					value="${currentPage}" /> <span class="input-group-btn">
					<button id="but3" class="btn btn-default" type="button"
						onclick="search()">Go!</button>
				</span>
			</div>
			<!-- /input-group -->

			<button id="but2" type="button" class="btn btn-primary"
				data-toggle="button" onclick="down()">&raquo;</button>
			 
			<span id="total" class="btn btn-primary">${userNum}条，共${pageTimes}页</span>
		</div>
	</div>
	<!-- 模态框（Modal） -->
	<div class="modal small fade" id="myModal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">×</button>
					<h3 id="myModalLabel">删除</h3>
				</div>
				<div class="modal-body">
					<input type="hidden" id="recordId"/>
					<p class="error-text">
						<i class="fa fa-warning modal-icon"></i>确定删除？
					</p>
				</div>
				<div class="modal-footer">
					<button class="btn btn-default" data-dismiss="modal"
						aria-hidden="true">取消</button>
					<button class="btn btn-danger" onclick="delRecord()">删除</button>
				</div>
			</div>
		</div>
	</div>

	<script src="lib/bootstrap/js/bootstrap.js"></script>
	<script type="text/javascript">
		$(".education").val("${userinfo.education}"); 
		if("${userinfo.sex}" == "1"){
			$("#male").click()
		}else{
			$("#female").click()
		}
        $("[rel=tooltip]").tooltip();
        $(function() {
            $('.demo-cancel-click').click(function(){return false;});
        });
        function toAction(){
        	$("#form1").submit();
        }
    </script>


</body>
</html>