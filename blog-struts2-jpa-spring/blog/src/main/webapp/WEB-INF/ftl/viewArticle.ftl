<!DOCTYPE html>
<html lang="zh">
<head>
	<meta charset="utf-8">
	<link rel="stylesheet" href="css/public.css">
	<link rel="icon shortcut" href="img/logo.jpg">
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
				<div class="comments" id="comments">
					<div class="comment-body">
						<#--
						<div class="comment-panel">
							<div class="left comment-author">
								<div>
									<img alt="相见欢" src="http://secure.gravatar.com/avatar/4543d381c252c70925caa58a2921c347?s=60&amp;d=http://localhost:8080/b3log/images/default-user-thumbnail.png">
								</div>
								<a href="http://lichhao.com/blog" target="_blank" title="相见欢">相见欢</a>
							</div>
							<div class="left comment-info">
								<div class="left">
									2012-09-11 23:14:19
								</div>
								<div class="clear">
								</div>
								<div class="comment-content">
									今天终于见到传说中的b3log真面目了
								</div>
							</div>
							<div class="clear"></div>
						</div>
						-->
						<#if article.comments?size == 0>
						<span>还没有任何评论</span>
						</#if>
						
						<#list article.comments as comment>
						<div class="comment-panel">
							<div class="left comment-author">
								<div>
									<img alt="${comment.name}" src="http://www.gravatar.com/avatar/${comment.email}?s=200&r=pg&d=${default_person_icon}">
								</div>
								<a href="${comment.url}" target="_blank" title="${comment.name}">${comment.name}</a>
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
				<form action="commentArticle.action" method="post">
				<input type="hidden" name="article.articleId" value="${article.articleId}" />
				<table id="commentForm" class="comment-form">
					<tbody>
						<tr>
							<td width="208px">
								<input type="text" name="comment.name">
							</td>
							<td colspan="2" width="400px">
								姓名
							</td>
						</tr>
						<tr>
							<td>
								<input type="text" name="comment.email">
							</td>
							<td colspan="2">
								邮箱
							</td>
						</tr>
						<tr>
							<td>
								<input type="text" name="comment.url">
							</td>
							<td colspan="2">
								URL
							</td>
						</tr>
						<tr>
							<td colspan="3">
								<textarea rows="10" cols="96" name="comment.commentContent"></textarea>
							</td>
						</tr>
						<tr>
							<td colspan="3" align="right">
								<button type="submit">提交评论</button>
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