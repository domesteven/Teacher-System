<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
</head>
<style>
		pre {outline: 1px solid #ccc; padding: 5px; margin: 5px; }
		.string { color: green; }
		.number { color: darkorange; }
		.boolean { color: blue; }
		.null { color: magenta; }
		.key { color: red; }
</style>
<script type="text/javascript">

		function syntaxHighlight(json) {
		if (typeof json != 'string') {
		json = JSON.stringify(json, undefined, 2);
		}
		json = json.replace(/&/g, '&').replace(/</g, '<').replace(/>/g, '>');
		return json.replace(/("(\\u[a-zA-Z0-9]{4}|\\[^u]|[^\\"])*"(\s*:)?|\b(true|false|null)\b|-?\d+(?:\.\d*)?(?:[eE][+\-]?\d+)?)/g, function (match) {
		var cls = 'number';
		if (/^"/.test(match)) {
		if (/:$/.test(match)) {
		cls = 'key';
		} else {
		cls = 'string';
		}
		} else if (/true|false/.test(match)) {
		cls = 'boolean';
		} else if (/null/.test(match)) {
		cls = 'null';
		}
		return '<span class="' + cls + '">' + match + '</span>';
		});
		}
		
	function text(){
		var data = <%=request.getAttribute("json")%>;
		$('#result').html(syntaxHighlight(data));
	}
</script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.2.1.min.js"></script>
<body onload="text()">
	<pre id="result"></pre>
</body>
</html>