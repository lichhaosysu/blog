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
            <li><a href="showTags.action">标签墙</a></li>
            <li class="blog_navbar_for"><a href="#">关于我</a></li>
          </ul>
          <div id="fd"></div>         
        </div>
	</div>
	
	<div id="content" class="clearfix">
		<div id="main">
		<p>博主是一名码农，毕业一年多了。平时的兴趣爱好是研究技术，也很喜欢篮球和户外运动</p>
		<p>这里是我自己尝试着DIY做出来的博客，主机是在香港，所以访问的时候可能有点慢，望见谅</p>
		<p>为什么叫八度空间？因为博主年轻的时候很喜欢Jay的歌</p>
		<p>我会把一些觉得对自己有帮助的文章，在这里分享，也会偶尔记录一下自己的生活</p>
		<p>有问题或者建议，给我留言吧！欢迎各种骚扰。</p>
		<p>QQ：740230215</p>
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
					<#noescape><a href="${comment.url}">${comment.name}：</a>${comment.commentContent}</#noescape>
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