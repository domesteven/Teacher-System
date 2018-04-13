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
		var baseurl  = "${pageContext.request.contextPath}/goTaskDirectortournament.action";
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
        	var name = $("#searchName").val();
        	var url  = baseurl+"?page=${currentPage-1}";
        	if(name != null && name != ""){
        		url += "&name="+encodeURI(encodeURI(name));
        	}
        	if("${currentPage-1}">0){
        		window.location.href= url;	
        	}
        	
        }
        
        function down(){
        	debugger
        	var name = $("#searchName").val();
        	var url = baseurl+"?page=${currentPage+1}";
        	var max = eval("${pageTimes}");
        	if(name != null && name != ""){
        		url += "&name="+encodeURI(encodeURI(name));
        	}
        	if(eval("${currentPage+1}") <= max){
        		window.location.href=url;	
        	}
        	
        }
        
        function search(){
        	var num =  eval($("#searchPage").val());
        	var url = baseurl+"?page="+num;
        	var max = eval("${pageTimes}");
        	var name = $("#searchName").val();
        	if(name != null && name != ""){
        		url += "&name="+encodeURI(encodeURI(name));
        	}
        	if(num>0  && num <= max){
        		window.location.href=url;
        	}else{
        		toastr.warning("请输入正确的页码");
        		$("#searchPage").val("${currentPage}");
        	}
        }
        function setRecordId(id){
        	
        	$("#recordId").val(id);
            $('#myModal').modal('show');    
        }
        function showCreateModel(){
        	$('#myModal1').modal('show');   
        }
        function delRecord(){
        	debugger
        	var id= $("#recordId").val();
        	$.ajax({
                type: "post",
                url: "${pageContext.request.contextPath}/delTaskCompany.do", 
                data: {id:id},
                dataType: "json",
                success: function(data){
                	if(data.errcode == "-1"){
                		toastr.error(data.errmsg);
                	}else{
                		window.location.href=baseurl+"?page=${currentPage}";
                	}
                	
                }
            });
        }
        function insert(){
        	var id = $("#id").val();
        	if(id != null && id != ""){
        		$("#form1").attr('action',"${pageContext.request.contextPath}/editTaskDirectortournament.action"); 
        	}
        	$("#form1").submit();
        }
        function reset(){
        	$("#searchName").val("");
        }
        function resetForm(){
        	document.getElementById("form1").reset();
        }
        function searchByName(){
        	var name = $("#searchName").val();
        	window.location.href=baseurl+"?name="+encodeURI(encodeURI(name));
        }
        function editTaskInfo(id){
        	
        	$.ajax({
                type: "post",
                url: "${pageContext.request.contextPath}/selectTaskCompanyById.action", 
                data: {id:id},
                dataType: "json",
                success: function(data){
                	if(data.errcode == "-1"){
                		toastr.error(data.errmsg);
                	}else{
                		$("#id").val(data.data.id);
                		$("#name").val(data.data.name);
                		$("#phone").val(data.data.phone);
                		$("#place").val(data.data.place);
                		
                		$('#myModal1').modal('show');   
                	}
                	
                }
            });
        }
        
        function excel(){
        	var url = "${pageContext.request.contextPath}/TaskCompanyExcel.action";
        	var name = $("#searchName").val();
        	if(name != null && name != ""){
        		url += "?name="+encodeURI(encodeURI(name));
        	}
        	window.location.href = url;
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
#searchName{
	width:100px;
	float:right;
}
#toSearch{
	float:right;
}
#reset {
	float:right;
}
#seaName{
	font-size:16px;
	margin-top:5px;
	margin-right:10px;
	float:right;
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
			<button class="btn btn-primary" onclick="showCreateModel()">
				<i class="fa fa-plus"></i> 新增
			</button>
			<button class="btn btn-default" onclick="excel()">导出</button>
			<button id="reset" class="btn btn-default" onclick="reset()">重置</button>
			<button id="toSearch" class="btn btn-primary" onclick="searchByName()">查询</button>
			<input type="text" id="searchName" name="searchName" class="form-control" value="${searchName }"/>
			<label id="seaName">企业名称:</label>
		</div>
		<div id="info">
			<table class="table">
				<thead>
					<tr>
						<th>项目名</th>
						<th>指导老师姓名</th>
						<th>学生姓名</th>
						<th>获奖荣誉</th>
						<th>获奖时间</th>
						<th style="width: 3.5em;"></th>
					</tr>
				</thead>
				<tbody>
	
					<c:forEach items="${list}" var="item">
						<tr>
							<td>${item.name}</td>
							<td>${item.tName}</td>
							<td>${item.studentName}</td>
							<td>${item.attach}</td>
							<td>${item.time}</td>
							<td><a onclick="editTaskInfo(${item.id})"><i class="fa fa-pencil"></i></a> <a onclick="setRecordId(${item.id})"><i
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
	
	<div class="modal small fade" id="myModal1" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true" data-backdrop="static">
		<div class="modal-dialog" style="width: 700px;height:250px;">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true" onclick="resetForm()">×</button>
					<h3 id="myModalLabel">校企合作任务</h3>
				</div>
				<div class="modal-body">
				<div style="height:300px;width:500px,overflow:auto; ">
					<form id="form1" action="${pageContext.request.contextPath}/saveTaskDirectortournament.action"
			method="post" enctype="multipart/form-data">
			<table id="table1"
				class="table table-striped table-bordered table-condensed list">
				<tbody>
					<tr>
						
						<td width="30%">项目名<font color="FF0000">*</font></td>
						<td width="500"><input id="name" name="name" type="text"
							value="" /></td>

					</tr>
					
					<tr>
						<td>指导老师姓名<font color="FF0000">*</font></td>
						<td><input id="tName1" name="tName1"
							type="text" value="${userinfo.tName}" disabled="disabled"/>
							<input id="tName" name="tName"
							type="hidden" value="${userinfo.tName}" /></td>
						

					</tr>
					<tr>
						<td>学生姓名<font color="FF0000">*</font></td>
						<td><input id="studentName" name="studentName"
							type="text" value="" /></td>

					</tr>
					
					<tr>
						<td>获奖荣誉<font color="FF0000">*</font></td>
						<td><input id="attach" name="attach"
							type="text" value="" /></td>

					</tr>
					
					<tr>
						<td>获奖时间<font color="FF0000">*</font></td>
						<td><input id="time" name="time"
							type="date" value="" /></td>

					</tr>

				</tbody>
				<input id="tId" name="tId" value="${userinfo.tId}" type="hidden" />
				<input id="id" name="id" value="" type="hidden"/>
			</table>
		</form>
		</div>
				</div>
				<div class="modal-footer">
					
					<button class="btn btn-default" data-dismiss="modal"
						aria-hidden="true" onclick="resetForm()">取消</button>
					<button class="btn btn-primary" onclick="insert()">保存</button>
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
