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
	
	if(blog_comment){
		var obj = JSON.parse(blog_comment);

		$('input[name="comment.name"]').val(obj.name);
		$('input[name="comment.email"]').val(obj.email);
		$('input[name="comment.url"]').val(obj.url);
		
		$('#toggleComment').text('更改》');
		$('#comment_name_span').text(obj.name+" ");
		
		$('#comment_name').hide();
		$('#comment_email').hide();
		$('#comment_url').hide();
	}else{
		$('td','#welcome_tr').replaceWith($('<td colspan="3"><span>发表评论</span></td>'));
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

	$('#commentFrom').submit(function() {
		
		var name = $('input[name="comment.name"]').val();
		var email = $('input[name="comment.email"]').val();
		var url = $('input[name="comment.url"]').val();
		
		var commentObj = {};
		commentObj.name = name;
		commentObj.email = email;
		commentObj.url = url;
		
		var date = new Date();
		date.setFullYear(2099);

		CookieUtil.set("blog_comment", JSON.stringify(commentObj), date);
		
		return true;
		
	});

});