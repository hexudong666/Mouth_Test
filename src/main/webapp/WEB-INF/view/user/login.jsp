<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   <%@taglib uri="http://www.springframework.org/tags/form" prefix="fm" %>
   <%@  taglib  prefix="fmt"  uri="http://java.sun.com/jsp/jstl/fmt"%>
   <%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<%
	String path=request.getContextPath();
%>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<!--日期插件--><script type="text/javascript" src="<%=path%>/My97DatePicker/WdatePicker.js"></script>
<!-- 新的css,js -->
<link rel="stylesheet" href="/bootstrap-4.3.1/css/bootstrap.css">
<script type="text/javascript" src="/bootstrap-4.3.1/js/bootstrap.js"></script>
</head>
<body>
<div>
		<ul>
			<li>
				账号:
				<br><br>
				密码:
				<br><br>
			
			</li>
		</ul>
	<input type="button" value="登录">
</div>
	
</body>
</html>