<#escape x as x?html>
<!DOCTYPE html>
<html lang="zh">
<head>
	<meta charset="utf-8">
	<link rel="stylesheet" href="css/public.css">
	<link rel="icon shortcut" href="img/logo.jpg">
	<script src="js/jquery/jquery-1.7.2.js"></script>
	<title>八度空间-倾注世间之爱于设计与创作</title>
</head>
<body>
<div id="page">

	<div id="branding" class="clearfix">
        <div id="blog_name">
          <h1><a href="index.action">八度空间</a></h1>
          <span class="sub-title">倾注世间之爱于设计与创作</span>
        </div>
        <div id="fd"></div>
        <div id="blog_navbar">
          <ul>
            <li><a href="index.action">首页</a></li>
            <li><a href="admin.action">写博文</a></li>
            <li class="blog_navbar_for"><a href="showTags.action">标签墙</a></li>
            <li><a href="aboutMe.action">关于我</a></li>
          </ul>
          <div id="fd"></div>         
        </div>
	</div>
	
	<div id="content" class="clearfix">
		<div id="main">
		<#if thisTag.articles?size != 0>
			<#list thisTag.articles as article>
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
	 							<li><a href="showArticleByTag.action?thisTag.tagId=${tag.tagId}">${tag.tagName}</a></li> 
					    	</#list>
					    </ul>
					    <div class="news_tag"></div>
					</div>		
		
					<div class="blog_content"><#noescape>${article.summary}</#noescape></div>
					<div class="blog_bottom">
					    <ul>
					      <li>最后编辑于：${article.modifyDate}</li>
					      <li>浏览：${article.visitCount}</li>
					      <li style="float:right; border:0; padding-right: 0;margin: 0"><a href="viewArticle.action?article.articleId=${article.articleId}">阅读全文》</a></li>
					    </ul>
  					</div>		
				</div>
			</#list>
		<#else>
		<span>该标签下还没有任何博文，赶快<a href="admin.action">去写一篇</a>吧</span>
		</#if>
		</div>
		<div id="local">
			<div id="blog_owner">
			  <a href="index.action">
			  <img src="${contextPath}/img/IMG_7552.JPG" title="八度空间：倾注世间之爱于创新和设计">
			  </a>
			</div>
			<div class="local_block">
                <h5>最新文章</h5>
                <ul>
	            <#list latestArticles as article>
				<li>
					<a href="viewArticle.action?article.articleId=${article.articleId}" title="${article.title}">${article.title}</a>
				</li>
				</#list>
                </ul>
            </div>
           	<div class="local_block">
                <h5>最新评论</h5>
                <ul>
	            <#list latestComments as comment>
				<li>
					<#noescape><a href="${comment.commentUrl}">${comment.commentName}：</a>${comment.commentContent}</#noescape>
				</li>
				</#list>
                </ul>
            </div>
		</div>
	</div>
</div>
<div id="footer"></div>
</body>
</html>
</#escape>