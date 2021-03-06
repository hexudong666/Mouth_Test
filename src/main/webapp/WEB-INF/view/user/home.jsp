<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   <%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
   <%@  taglib  prefix="fmt"  uri="http://java.sun.com/jsp/jstl/fmt"%>
   <%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<%
	String path=request.getContextPath();
%>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户个人中心</title>
<link href="/resource/bootstrap-4.3.1/css/bootstrap.css" rel="stylesheet">
<script type="text/javascript" src="/resource/js/jquery-3.2.1/jquery.js" ></script>
<script type="text/javascript" src="/resource/bootstrap-4.3.1/js/bootstrap.js"></script>
<script type="text/javascript" src="/resource/js/jqueryvalidate/jquery.validate.js"></script>
<script type="text/javascript" src="/resource/js/jqueryvalidate/localization/messages_zh.js"></script>
 <link rel="stylesheet" href="/resource/kindeditor/themes/default/default.css" />
 <link rel="stylesheet" href="/resource/kindeditor/plugins/code/prettify.css" />
 <script charset="utf-8" src="/resource/kindeditor/plugins/code/prettify.js"></script>
 <script charset="utf-8" src="/resource/kindeditor/kindeditor-all.js"></script>
 <script charset="utf-8" src="/resource/kindeditor/lang/zh-CN.js"></script>
 <link rel="stylesheet" type="text/css" href="<%=path%>/css/index_work.css"/> 

<style type="text/css">
	.menuselected {
		background:#7FFFD4;/* 点了之后的留色 */
	}
	.mymenuselected li:hover {
		background:#C0C0C0;/* 划过的颜色 */
	}
</style>
</head>
<body>
<nav class="navbar navbar-expand-lg navbar-light bg-light" style="background:#87CEEB"><!-- 头部的底色 -->
  <div class="collapse navbar-collapse" id="navbarSupportedContent" style="background:#87CEEB"><!-- 头部表面颜色 -->
    
    <ul class="navbar-nav mr-auto">
    	<li class="nav-item">
           <a class="nav-link" href="#"><img src="/resource/images/logo.png" height="50px" width="50px"> </a>
      </li>
      
      <li class="nav-item active">
        <a class="nav-link" href="#">Home <span class="sr-only">(current)</span></a>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="#">Link</a>
      </li>
      <li class="nav-item dropdown">
        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
          Dropdown
        </a>
        <div class="dropdown-menu" aria-labelledby="navbarDropdown">
          <a class="dropdown-item" href="#">Action</a>
          <a class="dropdown-item" href="#">Another action</a>
          <div class="dropdown-divider"></div>
          <a class="dropdown-item" href="#">Something else here</a>
        </div>
      </li>
      <li class="nav-item">
        <a class="nav-link disabled" href="#" tabindex="-1" aria-disabled="true">Disabled</a>
      </li>
    </ul>
    <form class="form-inline my-2 my-lg-0">
      <a href="http://localhost:8084/"><input type="button" value="首页"></a>
      <input class="form-control mr-sm-2" type="search" placeholder="Search" aria-label="Search">
      <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
    </form>
    <div>
    	<ul class="nav">
    		<li class="nav-item nav-link"> <img width="35px" height="35px" src="/resource/images/MAN.jpg"> </li>
    		<li class="nav-item nav-link">
    			<%-- 用户名字:${sessionScope.uname} --%>
    		</li>
    		<li class="nav-item nav-link">
    			<script language="javaScript">
					now = new Date(),hour = now.getHours()
					if(hour < 6){document.write("凌晨好!")}
					else if (hour < 9){document.write("早上好!")}
					else if (hour < 12){document.write("上午好!")}
					else if (hour < 14){document.write("中午好!")}
					else if (hour < 17){document.write("下午好!")}
					else if (hour < 19){document.write("傍晚好!")}
					else if (hour < 22){document.write("晚上好!")}
					else {document.write("夜里好!")}
				</script>
    		</li>
    		<!-- <li class="nav-item nav-link"><a href="loginOut">注销</a></li> -->
    	</ul>
    </div>
  </div>
</nav><!--  头结束 -->
	<div class="container row">
		<div class="col-md-2" style="margin-top:20px ; border-right:solid 2px"> 
			<!-- 左侧的菜单 -->
			<ul class="nav flex-column mymenuselected">
				  <li class="nav-item ">
				    <a  class="nav-link active" href="#" onclick="showWork($(this),'/user/articles')" >我的文章</a>
				  </li>
				  <li class="nav-item">
				    <a id="postLink" class="nav-link" href="#" onclick="showWork($(this),'/user/postArticle')">发表文章</a>
				  </li>
				  <li class="nav-item">
				    <a class="nav-link" href="#" onclick="showWork($(this),'/user/comments')" >我的评论</a>
				  </li>
				  <li class="nav-item">
				    <a class="nav-link disabled" href="#" tabindex="-1" aria-disabled="true">个人设置</a>
				  </li>
				</ul>	
		</div>
		
		<div class="col-md-10" id="workcontent"> 
		
		</div>	
</div>
	
<!-- 尾开始 -->
<nav class="nav fixed-bottom justify-content-center "  style="background:#DEB887" height="50px"> 
	      欢迎您进入CMS系统
</nav>

<script type="text/javascript">	
		
 	 KindEditor.ready(function(K) {
		window.editor1 = K.create('#contentId', {
			cssPath : '/resource/kindeditor/plugins/code/prettify.css',
			//uploadJson : '/resource/kindeditor/jsp/upload_json.jsp',
			uploadJson:'/file/upload'// );
		   })
		prettyPrint();
	});
	
	function showWork(obj,url){
		$(".mymenuselected li").removeClass("menuselected");
		obj.parent().addClass("menuselected")		
		$("#workcontent").load(url);
		
	}
</script>

</body>
</html>