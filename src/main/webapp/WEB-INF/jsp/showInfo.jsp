<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
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

</head>
<body class=" theme-blue">

	<!-- Demo page code -->

	<script type="text/javascript">
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
    </script>
	<style type="text/css">

#main{
	margin-left: auto; margin-right: auto;
	width:800px;
	height:500px;
	border: 1px solid black;
}
#line-chart {
	height: 300px;
	width: 800px;
	margin: 0px auto;
	margin-top: 1em;
}
#name{
	float:right;
	width:300px;
}
#sex{
	float:right;
	width:300px;
}
#education{
	float:right;
	width:300px;
}
#image{
	float:right;
	margin-right:200px;
	
}
#img{
	width:100px;
	height:120px;
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
		<table class="table table-striped table-bordered table-condensed list">
        	<tbody>
            	<tr>
                	<td width="15%">姓名<font color="FF0000">*</font></td>
                	<td width="500">
                    	<input id="tName" name="tName" type="text" value="${userinfo.tName}"/>
                	</td>
                	<td width="500" rowspan="2" colspan="2">
                		<img id="img" alt="" src="./images/123.jpg">
                	</td>
                	
            	</tr>
            	<tr>
                	<td>身份证号码<font color="FF0000">*</font></td>
                	<td>
                    	<input id="certificateNumber" name="certificateNumber" type="text" value="${userinfo.certificateNumber}"/></td>
                	
            	</tr>
            	<tr>
                	<td>入校年份<font color="FF0000">*</font></td>
                	<td>
                    	<input id="schoolYear" name="schoolYear" value="${userinfo.schoolYear}" type="text" />
                	</td>
                	<td>职称<font color="FF0000">*</font></td>
                	<td>
                    	<input id="title" name="title" type="text" value="${userinfo.title}"/></td>
            	</tr>
            
            	<tr>
                	<td width="15%">毕业院校<font color="FF0000">*</font></td>
                	<td>
                    	<input id="graduateSchool" name="graduateSchool" type="text" value="${userinfo.graduateSchool}"/>
                	</td>
                	<td width="500">学历学位</td>
                	<td width="500">
                    	<select name="education" class="education">
							<option value="高中">高中</option>
							<option value="本科">本科</option>
							<option value="硕士">硕士</option>
							<option value="博士">博士</option>
						</select>
                	</td>
            	</tr>
           		<tr>
                	<td>籍贯<font color="FF0000">*</font></td>
                	<td>
                    	<input id="native_place" name="native_place" value="${userinfo.nativePlace}" type="text" />
                	</td>
                	<td>性别<font color="FF0000">*</font></td>
                	<td>
                    	<input id="male" type="radio" name="sex" value="1">男</input>
						<input id="female" type="radio" name="sex" value="2">女</input>
					</td>
            	</tr>
            	<tr>
                	<td>专业<font color="FF0000">*</font></td>
                	<td>
                    	<input id="major" name="major" value="${userinfo.major}" type="text" />
                	</td>
                	<td>所属教研室<font color="FF0000">*</font></td>
                	<td>
                		<input id="teaching_research" name="teaching_research" value="${userinfo.teachingResearch}" type="text" />
                    	</td>
            	</tr>
            	<tr>
                	<td>密码<font color="FF0000">*</font></td>
                	<td>
                    	<input id="password" name="password" value="${userinfo.password}" type="password" />
                	</td>
                	
            	</tr>
        	</tbody>
        	<tfoot>
            	<tr>
                	<td colspan="4">
                    	<input class="btn btn-inverse" id="find" type="submit" value="保存" />
                    	<input class="btn btn-inverse" type="reset" value="取消" /></td>
            	</tr>
        	</tfoot>
    	</table>
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
    </script>


</body>
</html>
