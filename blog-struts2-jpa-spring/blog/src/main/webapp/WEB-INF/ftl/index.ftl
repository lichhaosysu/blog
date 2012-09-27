<#--
底部分页栏所用到的宏
-->
<#macro pagination page total>

<#assign x = ((page-1)/5)?int * 5+1>
<#list 1..5 as count>
	<#if x <= total>
		<#if x == page>
		<span class="current">${x}</span>
		<#else>
		<a href="index.action?page=${x}">${x}</a>
		</#if>
	</#if>
	<#assign x = (x+1)>
</#list>

</#macro>

<#escape x as x?html>
<!DOCTYPE html>
<html lang="zh">
<head>
	<meta charset="utf-8">
	<link rel="stylesheet" href="css/public.css">
	<script src="js/jquery/jquery-1.7.2.js"></script>
	<script src="js/article/index.js"></script>
	<title>八度空间-倾注世间之爱于设计与创作</title>
</head>
<body>
<div id="header"></div>
<div id="page">

	<div id="branding" class="clearfix">
        <div id="blog_name">
          <h1><a href="index.action">八度空间</a></h1>
          <span class="sub-title">倾注世间之爱于设计与创作</span>
        </div>
        <div id="fd"></div>
        <div id="blog_navbar">
          <ul>
            <li class="blog_navbar_for"><a href="index.action">首页</a></li>
            <li><a href="admin.action">写博文</a></li>
            <li><a href="#">标签墙</a></li>
            <li><a href="#">关于我</a></li>
          </ul>
          <div id="fd"></div>         
        </div>
	</div>
	
	<div id="content" class="clearfix">
		<div id="main">
		
			<#list articles as article>
				<div class="blog_main">
					<div class="posttime-blue">
							<div class="posttime-MY">
								${(article.createDate)?string("yyyy-MM")}
							</div>
							<div class="posttime-D">
								${(article.createDate)?string("dd")}
							</div>
					</div>			
					<div class="blog_title">
					    <h3>
					      <a href="viewArticle.action?article.articleId=${article.articleId}">${article.title}</a>
					    </h3>
					    <ul class="blog_categories">
						    <strong>标签：</strong> 
			    			<#list article.tags as tag>
	 							<li><a href="#">${tag.tagName}</a></li> 
					    	</#list>
					    </ul>
					    <div class="news_tag"></div>
					</div>		
		
					<div class="blog_content"><#noescape>${article.summary}</#noescape></div>
					<div class="blog_bottom">
					    <ul>
					      <li>最后编辑于：${article.modifyDate}</li>
					      <li>浏览：${article.visitCount}</li>
					      <li style="float:right; border:0; padding-right: 0;margin: 0"><a href="viewArticle.action?article.articleId=${article.articleId}">阅读更多》</a></li>
					    </ul>
  					</div>		
				</div>
			</#list>
				<div class="pagination">
					<#if page gt 1>
					<a href="index.action?page=${page-1}">« 上一页</a> 
					</#if>
					<@pagination page=page total=total />
					<#--
					<span class="current">1</span> 
					<a href="#">2</a> 
					<a href="#">3</a> 
					<a href="#">4</a> 
					<a href="#">5</a> 
					-->
					<#if page lt total>
					<a href="index.action?page=${page+1}">下一页 »</a> 
					</#if>
				</div>

		</div>
		<div id="local">
			<div id="blog_owner">
			  <a href="index.action">
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
</#escape>