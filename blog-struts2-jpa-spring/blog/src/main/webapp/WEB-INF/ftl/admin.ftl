<!DOCTYPE html>
<html lang="zh">
<head>
	<meta charset="utf-8">
	<link rel="stylesheet" href="js/bootstrap/css/bootstrap.css">
	<link rel="stylesheet" href="css/article/admin.css">
	<link rel="stylesheet" href="js/ueditor/themes/default/ueditor.css">
	<script src="js/jquery/jquery-1.7.2.js"></script>
	<script>
		window.UEDITOR_HOME_URL = '${contextPath}'+'/js/ueditor/';
	</script>
	<script src="js/ueditor/editor_config.js"></script>
	<script src="js/ueditor/editor_all.js"></script>
	<script src="js/bootstrap/js/bootstrap.js"></script>
	<script src="js/article/admin.js"></script>
	<link rel="icon" href="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAABAAAAAQCAYAAAAf8/9hAAADZmlUWHRYTUw6Y29tLmFkb2JlLnhtcAAAAAAAPD94cGFja2V0IGJlZ2luPSLvu78iIGlkPSJXNU0wTXBDZWhpSHpyZVN6TlRjemtjOWQiPz4gPHg6eG1wbWV0YSB4bWxuczp4PSJhZG9iZTpuczptZXRhLyIgeDp4bXB0az0iQWRvYmUgWE1QIENvcmUgNS4wLWMwNjAgNjEuMTM0Nzc3LCAyMDEwLzAyLzEyLTE3OjMyOjAwICAgICAgICAiPiA8cmRmOlJERiB4bWxuczpyZGY9Imh0dHA6Ly93d3cudzMub3JnLzE5OTkvMDIvMjItcmRmLXN5bnRheC1ucyMiPiA8cmRmOkRlc2NyaXB0aW9uIHJkZjphYm91dD0iIiB4bWxuczp4bXBNTT0iaHR0cDovL25zLmFkb2JlLmNvbS94YXAvMS4wL21tLyIgeG1sbnM6c3RSZWY9Imh0dHA6Ly9ucy5hZG9iZS5jb20veGFwLzEuMC9zVHlwZS9SZXNvdXJjZVJlZiMiIHhtbG5zOnhtcD0iaHR0cDovL25zLmFkb2JlLmNvbS94YXAvMS4wLyIgeG1wTU06T3JpZ2luYWxEb2N1bWVudElEPSJ4bXAuZGlkOjBFNDgzRkIxNDNDM0RFMTFCNTBGQ0RDRTc5MzAyODhBIiB4bXBNTTpEb2N1bWVudElEPSJ4bXAuZGlkOjU0NzhFMEI0QjlFRDExREY5RTRFRjFFRUJENUE4Q0EzIiB4bXBNTTpJbnN0YW5jZUlEPSJ4bXAuaWlkOjU0NzhFMEIzQjlFRDExREY5RTRFRjFFRUJENUE4Q0EzIiB4bXA6Q3JlYXRvclRvb2w9IkFkb2JlIFBob3Rvc2hvcCBDUzUgTWFjaW50b3NoIj4gPHhtcE1NOkRlcml2ZWRGcm9tIHN0UmVmOmluc3RhbmNlSUQ9InhtcC5paWQ6MDc4MDExNzQwNzIwNjgxMThGNjI4MzI4OUNEQTc1NEYiIHN0UmVmOmRvY3VtZW50SUQ9InhtcC5kaWQ6MEU0ODNGQjE0M0MzREUxMUI1MEZDRENFNzkzMDI4OEEiLz4gPC9yZGY6RGVzY3JpcHRpb24+IDwvcmRmOlJERj4gPC94OnhtcG1ldGE+IDw/eHBhY2tldCBlbmQ9InIiPz6RheuGAAAAGXRFWHRTb2Z0d2FyZQBBZG9iZSBJbWFnZVJlYWR5ccllPAAAAqRJREFUOMt9kzuIXWUUhb+9//+cc89klIFBc3WIEyejlZXCwBBBQYiNnaQwiKCoaXwEJDHY+UgTbHwUWiTBLrUPVDRgGoUEKwk+JgNhJBozRUCTO3PPf/beFvcyWLmaBRsWrL3W3nLPQy8+kqt8Mue8opoQCSKCCIcQQphCICAicPcLZv2xKxc+Op9TzmcH7cywGQzIlUKAWSHcgEBQQhKiCSERBGa2Usbjs8BdOVfVcDDT0s7M8OjK/Tzz5CrLi7v5++YWn31zkfdPf4moorlGcwYUD0dTHgJoSkqVK1LKrP++yR9/3cA8aAcNB5/Yz1uvHcT6EURBRNCcSbkiVzUAOtnLIYyr1zZ5/cQnfPr1D7g7Zs7+lQfYM5ylG9+i77axvodgBzoJxTj6wgGaSijdFm1TYR707pg7dXK68Yi+dLj1uDseDkAODHdj9cH7eOzhY/yytsHyvQuYTcTuQd+N6McjUm6RVBMo7j514ODufH7uR8ycfXvvxszpzXf4lcNP01RB6UaUfhvve9wCQDQCwuGDM1/x3fc//UdsO7y0tMi7bx+hSUa/PaKUbcwKgMry6kux6/bbyFmxfos9u2dp8sT2y4cPsW9pEffAPVhb3+D4O6foaEBqfj53cqCTSxPQTKpaNv78h0vr17l0+TpH3niPX9euYNMw9y4u8OyhA5TxCC8dQK3hECGoZlJuyYNZ6naOetc8hZajb37Mb5c3poE6d8zPYaWb1AkpezhBEAiSKgQlSYWkBtWGrow4fuIUzz31OHfOz/Ht+YsgQuAAksP8qrkvWAS4I6KoVIQmtE4kUbrxiA9Pf4FbIVA01ThcAyyb9c+X8fiMah56FZOPI/Bwwp1wQbRGMyAFBwI2x7duvAoU4f8hgAIZqIE0nRlQgPIv71p79I9S1nUAAAAASUVORK5CYII=">
	<title>八度空间-后台管理</title>
</head>
<body>
	<div class="header"></div>
	<div id="main-content">
		<div id="navbar-container">
			<h1 id="navbar-content-title">后台管理</h1>
			<ul>
     			<li class="navbar-item navbar-item-selected">发表文章</li>
     			<li class="navbar-item">文章管理</li>
     			<li class="navbar-item">评论管理</li>
     			<li class="navbar-item">更多设置</li>
     		</ul>
		</div>
		<div id="mainview-content">
			<div id="page-container">
				<div id="publishArticle" class="page">
					<h1>发表文章</h1>
					<form id="publishForm" action="saveArticle.action" method="post">			
						<div class="form">
							<div>
						        <label>标题（不能为空）：</label>
						        <input type="text" name="article.title">
						    </div>
						    <div>
						    	<label>正文：</label>
						    	<script type="text/plain" id="myEditor" name="article.content"></script>
							</div>
						    <div>
						    	<label>标签（用分号分隔多个标签）：</label>
						    	<input type="text" name="tag">
						    	<span style="float:right;width:10%"><button type="button" style="float:right;" class="btn btn-info">选择标签</button></span>
							</div>							
							<div>
					        	<button type="submit" id="publish" class="btn btn-primary">
									<i class="icon-envelope icon-white"></i> 立即发表
								</button>
								<button type="button" class="btn btn-success disabled">
									<i class="icon-file icon-white"></i> 存为草稿
								</button>
						    </div>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
	
	
	
</body>
</html>