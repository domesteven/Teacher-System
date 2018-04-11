<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
</head>
<body>
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
						<li><a
							href="${pageContext.request.contextPath}/goPersonInfoPage.action">账户</a></li>
						<li class="divider"></li>

						<li><a tabindex="-1"
							href="${pageContext.request.contextPath}/loginOut.action">登出</a></li>
					</ul></li>
			</ul>

		</div>
	</div>
	</div>


	<div class="sidebar-nav">
    <ul>
    <li><a href="#" data-target=".dashboard-menu" class="nav-header" data-toggle="collapse"><i class="fa fa-fw fa-dashboard"></i> 个人信息<i class="fa fa-collapse"></i></a></li>
    <li><ul class="dashboard-menu nav nav-list collapse in">
            <li><a href="${pageContext.request.contextPath}/goShowInfoPage.action"><span class="fa fa-caret-right"></span> 个人信息展示</a></li>
            <li ><a href="${pageContext.request.contextPath}/goEditInfoPage.action"><span class="fa fa-caret-right"></span> 个人信息修改</a></li>
    </ul></li>

    <li data-popover="true" data-content="Items in this group require a <strong><a href='http://portnine.com/bootstrap-themes/aircraft' target='blank'>premium license</a><strong>." rel="popover" data-placement="right"><a href="#" data-target=".premium-menu" class="nav-header collapsed" data-toggle="collapse"><i class="fa fa-fw fa-fighter-jet"></i> 教学<i class="fa fa-collapse"></i></a></li>
        <li><ul class="premium-menu nav nav-list collapse">
                <li class="visible-xs visible-sm"><a href="#">- Premium features require a license -</a></span>
            <li ><a href="${pageContext.request.contextPath}/goTeachingTask.action"><span class="fa fa-caret-right"></span> 教学任务</a></li>
            <li ><a href="premium-blog.html"><span class="fa fa-caret-right"></span> 教学任务新增</a></li>
            <li ><a href="premium-blog-item.html"><span class="fa fa-caret-right"></span> 教学任务修改</a></li>
    </ul></li>

        <li><a href="#" data-target=".accounts-menu" class="nav-header collapsed" data-toggle="collapse"><i class="fa fa-fw fa-briefcase"></i> 科研 <span class="label label-info">+3</span></a></li>
        <li><ul class="accounts-menu nav nav-list collapse">
            <li ><a href="sign-in.html"><span class="fa fa-caret-right"></span> 科研项目列表</a></li>
            <li ><a href="sign-up.html"><span class="fa fa-caret-right"></span> 科研项目新增</a></li>
            <li ><a href="reset-password.html"><span class="fa fa-caret-right"></span> 科研项目修改</a></li>
    </ul></li>

        <li><a href="#" data-target=".legal-menu" class="nav-header collapsed" data-toggle="collapse"><i class="fa fa-fw fa-legal"></i>荣誉奖励<i class="fa fa-collapse"></i></a></li>
        <li><ul class="legal-menu nav nav-list collapse">
            <li ><a href="privacy-policy.html"><span class="fa fa-caret-right"></span> 荣誉奖励列表</a></li>
            <li ><a href="terms-and-conditions.html"><span class="fa fa-caret-right"></span> 荣誉奖励新增</a></li>
            <li ><a href="terms-and-conditions.html"><span class="fa fa-caret-right"></span> 荣誉奖励修改</a></li>
    </ul></li>

        <li><a href="help.html" class="nav-header"><i class="fa fa-fw fa-question-circle"></i> Help</a></li>
            <li><a href="faq.html" class="nav-header"><i class="fa fa-fw fa-comment"></i> Faq</a></li>
                <li><a href="http://portnine.com/bootstrap-themes/aircraft" class="nav-header" target="blank"><i class="fa fa-fw fa-heart"></i> Get Premium</a></li>
            </ul>
    </div>
</body>
</html>