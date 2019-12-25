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
<!--日期插件--><script type="text/javascript" src="/resource/My97DatePicker/WdatePicker.js"></script>
<!--bootstrap的css,js -->
<link rel="stylesheet" href="/resource/css/index3.css"/>
<link rel="stylesheet" href="/resource/bootstrap-4.3.1/css/bootstrap.css"/> 
<script type="text/javascript" src="/resource/js/jquery-1.8.3.js"></script>
<script type="text/javascript" src="/resource/bootstrap-4.3.1/js/bootstrap.js"></script> 
</head>
<body>
	投诉详情
	
	<form action="cx" method="post">
	<select>
		<option>黄色</option>
		<option>抄袭</option>
		<option>盗版</option>
	</select>
	次数大于:<input type="text" name="c1">---<input type="text" name="c2">
	<input type="submit" value="搜索">
	
	</form>
			<table>
				<tr>
					<td>文章编号</td>
					<td>文章标题</td>
					<td>文章投诉人</td>
					<td>文章投诉时间</td>
					<td>文章投诉类型</td>
					<td>文章投诉证据</td>
				</tr>
				<c:forEach items="${xq}" var="f">
				<tr>
					<td>${f.articleId}</td>
					<td>${article.title}</td>
					<td>${f.userId}</td>
					<td><fmt:formatDate value="${article.created}"/></td>
					<td>
						${f.complainType}
					</td>
					<td>${f.urlip}</td>
				</tr>
				</c:forEach>
			</table>
</body>
</html>