$(function() {

	var options = {
		toolbars : [ [ 'fullscreen', 'source', '|', 'undo', 'redo', '|',
				'fontfamily', 'fontsize', '|', 'bold', 'italic', 'underline',
				'strikethrough', '|', 'pasteplain', '|', 'forecolor',
				'backcolor', '|', 'link', 'unlink', '|', 'insertimage',
				'emotion', 'attachment', 'highlightcode', 'template',
				'background', '|', 'preview' ] ],
		// elementPathEnabled : false,
		initialContent : '',
		autoHeightEnabled : false
	};

	var editor = new baidu.editor.ui.Editor(options);

	editor.render("myEditor");

	$('input[name="article.title"]').focus();

	$('#navbar-container>ul>li').mousedown(function() {
		$(this).addClass('navbar-item-focus');
	}).mouseup(function() {
		$(this).removeClass('navbar-item-focus');
	});

	$('#navbar-container>ul>li').click(function() {

		// 移除兄弟节点选中状态
		$(this).siblings('.navbar-item-selected').each(function(index) {
			$(this).removeClass('navbar-item-selected');
		});

		// 添加选中状态
		$(this).addClass('navbar-item-selected');

	});

	$('#publishForm').submit(function() {

		$('#publish').addClass("disabled");

		var $title = $('input[name="article.title"]');
		if ($.trim($title.val()) == '') {
			$title.val('');

			alert('标题不能为空！');

			try {
				if ($.browser.safari) {
					$(document.body).animate({
						'scrollTop' : 0
					}, 'slow');
				} else {
					$(document.documentElement).animate({
						'scrollTop' : 0
					}, 'slow');
				}

				// var scrollTop = document.documentElement.scrollTop
				// || document.body.scrollTop;
				// document.documentElement.scrollTop = document.body.scrollTop
				// = 0;

			} catch (e) {
				alert(e.message);
			}

			$title.focus();
			$('#publish').removeClass('disabled');
			return false;
		}
		return true;
	});

});