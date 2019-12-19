<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>cms-个人中心</title>
<script type="text/javascript" src="/resource/js/jquery-3.2.1/jquery.js" ></script>
<link href="/resource/bootstrap-4.3.1/css/bootstrap.css" rel="stylesheet">
<script type="text/javascript" src="/resource/bootstrap-4.3.1/js/bootstrap.js"></script>
<script type="text/javascript" src="/resource/js/jqueryvalidate/jquery.validate.js"></script>
<script type="text/javascript" src="/resource/js/jqueryvalidate/localization/messages_zh.js"></script>


<style type="text/css">
	.menuselected {
		background:#7FFFD4;
	}
	.mymenuselected li:hover {
		background:#00FFFF;
	}
</style>
</head>
<body>
<nav class="navbar navbar-expand-lg navbar-light bg-light" style="background:#6600FF">
  <div class="collapse navbar-collapse" id="navbarSupportedContent" style="background:#87CEFA">
    
    <div>
    	<ul class="nav">
    		<li class="nav-item nav-link"> <img width="35px" height="35px" src="/resource/images/guest.jpg"> </li>
    	
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
    		<li class="nav-item nav-link">c</li>
    		<li class="nav-item nav-link">d</li>
    	</ul>
    </div>
  </div>
</nav><!--  头结束 -->
	
	<div class="container row">
		<div class="col-md-2" style="margin-top:20px ; border-right:solid 2px"> 
			<!-- 左侧的菜单 -->
			<ul class="nav flex-column mymenuselected">
				  <li class="nav-item ">
				    <a  class="nav-link active" href="#" onclick="showWork($(this),'/admin/article?status=0&page=1')" >文章管理</a>
				  </li>
				  <li class="nav-item">
				    <a class="nav-link" href="#" onclick="showWork($(this),'/admin/comment')" >评论管理</a>
				  </li>
				  <li class="nav-item">
				    <a class="nav-link" href="#" onclick="showWork($(this),'/admin/link')" >友情链接管理</a>
				  </li>
				  <li class="nav-item">
				    <a class="nav-link" href="#" onclick="showWork($(this),'/admin/user')" >用户管理管理</a>
				  </li>
				</ul>	
		</div>
		
		<div class="col-md-10" id="workcontent"> 
		
		    
		</div>	
	</div>
	
<!-- 尾开始 -->
<nav class="nav fixed-bottom justify-content-center "  style="background:#DEB887" height="50px"> 
	         CMS  系统后台管理系统  版权及最终解释权归  贺旭东 所有
</nav>

<script type="text/javascript">	
	
	
	function showWork(obj,url){
		$(".mymenuselected li").removeClass("menuselected");
		obj.parent().addClass("menuselected")		
		$("#workcontent").load(url);
	}
</script>

</body>

</html>