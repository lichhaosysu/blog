<!DOCTYPE html>
<html lang="zh">
<head>
	<meta charset="utf-8">
	<link rel="stylesheet" href="css/public.css">
	<link rel="stylesheet" href="js/ueditor/themes/default/ueditor.css">
	<link rel="icon shortcut" href="img/logo.jpg">
	<script src="js/jquery/jquery-1.7.2.js"></script>
	<script>
		window.UEDITOR_HOME_URL = '${contextPath}'+'/js/ueditor/';
		var contextPath = '${contextPath}';
	</script>
	<script src="js/ueditor/editor_config.js"></script>
	<script src="js/ueditor/editor_all.js"></script>
	
	<script src="js/ueditor/third-party/SyntaxHighlighter/shCore.js"></script> 
	<!--
	<link href="js/ueditor/third-party/SyntaxHighlighter/shCoreDefault.css"  rel="stylesheet"> 
	<script type="text/javascript">SyntaxHighlighter.all();</script>
	-->
	<!--
	<script type="text/javascript" src="js/syntaxhighlighter_3.0.83/scripts/shCore.js"></script>
	<script type="text/javascript" src="js/syntaxhighlighter_3.0.83/scripts/shBrushJScript.js"></script>
	<script type="text/javascript" src="js/syntaxhighlighter_3.0.83/scripts/shBrushJava.js"></script>
	-->
	<link type="text/css" rel="stylesheet" href="js/syntaxhighlighter_3.0.83/styles/shCoreDefault.css"/>
	
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
				<div class="blog_sibling">
					<!-- JiaThis Button BEGIN -->
					<div class="jiathis_style_32x32">
						<div style="float: left;"><strong>分享到：</strong></div>
						<a class="jiathis_button_qzone"></a>
						<a class="jiathis_button_tsina"></a>
						<a class="jiathis_button_tqq"></a>
						<a class="jiathis_button_renren"></a>
						<a class="jiathis_button_kaixin001"></a>
						<a href="http://www.jiathis.com/share" class="jiathis jiathis_txt jtico jtico_jiathis" target="_blank"></a>
						<a class="jiathis_counter_style"></a>
					</div>
					<script type="text/javascript" src="http://v3.jiathis.com/code/jia.js?uid=1349071342748634" charset="utf-8"></script>
					<!-- JiaThis Button END -->
					<!-- UJian Button BEGIN -->
						<script type="text/javascript" src="http://v1.ujian.cc/code/ujian.js?type=slide"></script>
					<!-- UJian Button END -->
					<div class="clear"></div>
					<#if preArticle??>
					<div style="margin-top: 15px;"><span>前一篇：</span><a href="viewArticle.action?article.articleId=${preArticle.articleId}">${preArticle.title}</a></div>
					<#else>
					<div style="margin-top: 15px;"><span>没有前一篇</span></div>
					</#if>
					<#if nextArticle??>
					<div><span>后一篇：</span><a href="viewArticle.action?article.articleId=${nextArticle.articleId}">${nextArticle.title}</a></div>
					<#else>
					<div><span>没有后一篇</span></div>
					</#if>

				</div>	
				<div class="comments" id="comments">
					<h3 class="reply-title">评论（${article.comments?size}）</h3>
					<div class="comment-body">
						<#if article.comments?size == 0>
						<span>还没有任何评论</span>
						</#if>
						
						<#list article.comments as comment>
						<div class="comment-panel">
							<div class="comment-author">
								<div class="author-img">
									<img style="margin:0;" alt="${comment.name}" src="http://www.gravatar.com/avatar/${comment.email}?s=200&r=pg&d=${default_person_icon}">
								</div>
								<div class="author-name">
								<a href="${comment.url}" target="_blank" title="${comment.name}">${comment.name}</a>
								</div>
							</div>
							<div class="left comment-info">
								<div class="left">
									${comment.createDate}
								</div>
								<div class="clear">
								</div>
								<div class="comment-content">
									${comment.commentContent}
								</div>
							</div>
							<div class="clear"></div>
						</div>						
						</#list>
						
					</div>
				</div>
				
				<form style="margin:0;paddingL:0;" id="commentFrom" action="commentArticle.action" method="post">
				<input type="hidden" name="article.articleId" value="${article.articleId}" />
				<table class="comment-form">
					<tbody>
						<tr>
							<td colspan="3">
							<h3 style="margin: 3px 0 0 0;" class="reply-title">发表评论</h3>
							</td>
						</tr>
						<tr id="welcome_tr">
							<td colspan="3">
							<span>欢迎回来：<strong><span id="comment_name_span"></span></strong></span><a id="toggleComment" style="cursor: pointer;">隐藏》</a>
							</td>
						</tr>
						<tr id="comment_name">
							<td width="10%" style="width:200px">
								<input type="text" name="comment.name">
							</td>
							<td colspan="2" width="90%">
								姓名（必填）
							</td>
						</tr>
						<tr id="comment_email">
							<td>
								<input type="text" name="comment.email">
							</td>
							<td colspan="2">
								邮箱（用于显示此邮箱在<a href="http://cn.gravatar.com/" target="_blank">Gravatar</a>上注册的头像，不会被保存）
							</td>
						</tr>
						<tr id="comment_url">
							<td>
								<input type="text" name="comment.url">
							</td>
							<td colspan="2">
								URL（你的博客或网站链接分享）
							</td>
						</tr>
						<tr>
							<td colspan="3">
								<#--
								<script type="text/plain" id="contentEditor" style="width:690px" name="comment.commentContent"></script>
								-->
								<textarea id="contentEditor" style="width: 690px; resize: none;" rows="10" cols="96" name="comment.commentContent"></textarea>
								
							</td>
						</tr>
						<tr>
							<td colspan="3" width="690px" align="right">
								<button id="submit-button" type="submit">提交评论</button>
							</td>
						</tr>
					</tbody>
				</table>		
			</form>
					
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