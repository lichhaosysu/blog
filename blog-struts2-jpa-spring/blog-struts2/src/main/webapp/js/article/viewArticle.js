var CookieUtil = {
	get : function(name) {
		var cookieName = encodeURIComponent(name) + "=", cookieStart = document.cookie
				.indexOf(cookieName), cookieValue = null;

		if (cookieStart > -1) {
			var cookieEnd = document.cookie.indexOf(";", cookieStart);
			if (cookieEnd == -1) {
				cookieEnd = document.cookie.length;
			}
			cookieValue = decodeURIComponent(document.cookie.substring(
					cookieStart + cookieName.length, cookieEnd));
		}
		return cookieValue;
	},
	set : function(name, value, expires, path, domain, secure) {
		var cookieText = encodeURIComponent(name) + "="
				+ encodeURIComponent(value);

		if (expires instanceof Date) {
			cookieText += "; expires=" + expires.toGMTString();
		}
		if (path) {
			cookieText += "; path=" + path;
		}
		if (domain) {
			cookieText += "; domain=" + domain;
		}
		if (secure) {
			cookieText += "; secure";
		}
		document.cookie = cookieText;
	},
	unset : function(name, path, domain, secure) {
		this.set(name, "", new Date(0), path, domain, secure);
	}
}

$(function() {

	var blog_comment = CookieUtil.get("blog_comment");

	if (blog_comment) {
		var obj = JSON.parse(blog_comment);

		$('input[name="comment.name"]').val(obj.name);
		$('input[name="comment.email"]').val(obj.email);
		$('input[name="comment.url"]').val(obj.url);

		$('#toggleComment').text('更改》');
		$('#comment_name_span').text(obj.name + " ");

		$('#comment_name').hide();
		$('#comment_email').hide();
		$('#comment_url').hide();
	} else if (CookieUtil.get("blog_comment_name")) {

		var blog_comment_name = CookieUtil.get("blog_comment_name");
		var blog_comment_email = CookieUtil.get("blog_comment_email");
		var blog_comment_url = CookieUtil.get("blog_comment_url");

		$('input[name="comment.name"]').val(blog_comment_name);
		$('input[name="comment.email"]').val(blog_comment_email);
		$('input[name="comment.url"]').val(blog_comment_url);

		$('#toggleComment').text('更改》');
		$('#comment_name_span').text(blog_comment_name + " ");

		$('#comment_name').hide();
		$('#comment_email').hide();
		$('#comment_url').hide();

	} else {
//		$('td', '#welcome_tr').replaceWith($('<td colspan="3"><span>发表评论</span></td>'));
		$('td', '#welcome_tr').remove();
	}

	$('#toggleComment').click(function() {

		$('#comment_name').toggle();
		$('#comment_email').toggle();
		$('#comment_url').toggle();

		if ($(this).text() == '更改》') {
			$(this).text('隐藏》');
		} else {
			$(this).text('更改》');
		}

	});
	
	SyntaxHighlighter.highlight();
	
	var options = {
		toolbars : [ [ 'source', '|', 'fontfamily', 'fontsize', '|', 'bold',
				'italic', 'underline', 'strikethrough', '|', 'link', '|',
				'emotion', 'highlightcode' ] ],
		elementPathEnabled : false,
		initialContent : '',
		autoHeightEnabled : false,
		minFrameHeight : 200,
		'fontsize' : [ 10, 11, 12, 13, 14, 16, 18, 20, 24, 36 ],
		initialStyle : 'body{font-size:14px}',
		enterTag : 'br',
//		highlightJsUrl : UEDITOR_HOME_URL + "third-party/SyntaxHighlighter/shCore.js",
//		highlightCssUrl : UEDITOR_HOME_URL
//				+ "third-party/SyntaxHighlighter/shCoreDefault.css",
		iframeCssUrl: contextPath + '/js/syntaxhighlighter_3.0.83/styles/shCoreDefault.css'
	};

	var editor = new baidu.editor.ui.Editor(options);
	editor.render("contentEditor");
	
	$('#commentFrom').submit(function() {

		if (editor.getContent() == '') {
			alert('评论内容不能为空！');
			editor.focus();
			return false;
		}

		var name = $('input[name="comment.name"]').val();
		var email = $('input[name="comment.email"]').val();
		var url = $('input[name="comment.url"]').val();

		if (name == '') {
			alert('请至少输入你的姓名，用于显示评论！');
			$('input[name="comment.name"]').focus();
			return false;
		}

		var commentObj = {};
		commentObj.name = name;
		commentObj.email = email;
		commentObj.url = url;

		var date = new Date();
		date.setFullYear(2099);

		if (window.JSON) {
			CookieUtil.set("blog_comment", JSON.stringify(commentObj), date);
		} else {
			CookieUtil.set("blog_comment_name", name, date);
			CookieUtil.set("blog_comment_email", email, date);
			CookieUtil.set("blog_comment_url", url, date);
		}

		return true;

	});
	
	$('.reply_comment').click(function(){
		
		var panel = $(this).parent().parent().parent();
		var id = panel.attr('id');
		$('#reply_comment_id').val(id);
		var name = $('div.author-img-left>img',panel).attr('alt');
		editor.focus();
		editor.setContent('<a href="#'+id+'">'+'@'+name+'</a><br/><br/>');
	});

});