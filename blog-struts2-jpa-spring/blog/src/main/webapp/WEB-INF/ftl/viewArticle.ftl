<!DOCTYPE html>
<html lang="zh">
<head>
	<meta charset="utf-8">
	<link rel="stylesheet" href="css/article/viewArticle.css">
	<script src="js/jquery/jquery-1.7.2.js"></script>
	<script src="js/article/viewArticle.js"></script>
	<title>八度空间-${article.title}</title>
</head>
<body>
<div id="header"></div>
<div id="page">

	<div id="branding" class="clearfix">
        <div id="blog_name">
          <h1><a href="/">八度空间</a></h1>
          <span class="sub-title">倾注世间之爱于设计与创作</span>
        </div>
        <div id="fd"></div>
        <div id="blog_navbar">
          <ul>
            <li class="blog_navbar_for"><a href="#">首页</a></li>
            <li><a href="admin.action">写博文</a></li>
            <li><a href="#">标签墙</a></li>
            <li><a href="#">关于我</a></li>
          </ul>
          <div id="fd"></div>         
        </div>
	</div>
	
	<div id="content" class="clearfix">
		<div id="main">
			<div class="blog_main">
				<div class="blog_title">
				    <h3>
				      <a href="#">${article.title}</a>
				    </h3>
				    <ul class="blog_categories">
					    <strong>标签：</strong> 
		    			<#list article.tags as tag>
 							<li><a href="#">${tag.tagName}</a></li> 
				    	</#list>
				    </ul>
				    <div class="news_tag"></div>
				</div>		
				<div class="blog_content">${article.content}</div>		
			</div>
		</div>
		<div id="local">
			<div id="blog_owner">
			  <a href="javascript:void();">
			  <img src="${contextPath}/img/IMG_7552.JPG" title="八度空间：倾注世间之爱于创新和设计">
			  </a>
			  <span id="blog_owner_name">相见欢</span>
			</div>
		</div>
	</div>
</div>
<div id="footer"></div>
</body>
</html>