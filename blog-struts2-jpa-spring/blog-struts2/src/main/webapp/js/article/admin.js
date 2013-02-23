$(function() {

	$('#myModal').modal({
		backdrop : 'static',
		show : false
	});

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

		$('#page-container').find('.page').hide();
		$($(this).attr('title') + '').show();

	});

	$('#publishForm').submit(function() {

		var $title = $('input[name="article.title"]');
		if ($.trim($title.val()) == '') {
			$title.val('');

			$('#myModal').modal('show');

			$('#myModal').on('hidden', function() {
				if ($.browser.safari) {
					$(document.body).animate({
						'scrollTop' : 0
					}, 'slow');
				} else {
					$(document.documentElement).animate({
						'scrollTop' : 0
					}, 'slow');
				}
				$title.focus();
			});

			return false;

		} else {

			if ($('input[name="article.isPublished"]').val() == 'true') {
				$('#doPublish').button('loading');
				$('#saveDraft').attr({
					disabled : 'disabled'
				});
			} else {
				$('#saveDraft').button('loading');
				$('#doPublish').attr({
					disabled : 'disabled'
				});
			}
			return true;
		}

	});

	$('#doPublish').click(function() {
		$('input[name="article.isPublished"]').val('true');
	});
	$('#saveDraft').click(function() {
		$('input[name="article.isPublished"]').val('false');
	});

	$('#tags button').click(function() {
		$(this).toggleClass('selected');
		var text = [];
		$('#tags button.selected').each(function(index) {
			text[index] = $(this).text();
		});
		$('#tagInput').val(text.join(','));
	});

	$('#selectTag').click(function() {

		if ($(this).text() == '选择标签') {
			$(this).text('输入标签');
		} else {
			$(this).text('选择标签');
		}

		$('#tags').toggle();
		if ($('#tagInput').attr('readonly') != undefined) {
			$('#tagInput').removeAttr('readonly');
			$('#tagInput').focus();
		} else {
			$('#tagInput').attr({
				readonly : 'true'
			});
		}
	});

	// $('input[name="article.title"]').focus();

});

$(function() {
	
//	SyntaxHighlighter.highlight();

	var options = {
		toolbars : [ [ 'fullscreen', 'source', '|', 'undo', 'redo', '|',
				'fontfamily', 'fontsize', '|', 'bold', 'italic', 'underline',
				'strikethrough', '|', 'pasteplain', '|', 'forecolor',
				'backcolor', '|', 'link', 'unlink', '|', 'insertimage',
				'emotion', 'attachment', 'highlightcode', 'template',
				'background', '|', 'preview' ] ],
		elementPathEnabled : false,
		initialContent : '',
		autoHeightEnabled : false,
		initialStyle : 'body{font-size:14px}',
		'fontsize' : [ 10, 11, 12, 13, 14, 16, 18, 20, 24, 36 ],
		enterTag : 'p',
        tabSize:2,
        tabNode:'&nbsp;',
//		highlightJsUrl : UEDITOR_HOME_URL + "third-party/SyntaxHighlighter/shCore.js",
//		highlightCssUrl : UEDITOR_HOME_URL
//				+ "third-party/SyntaxHighlighter/shCoreDefault.css",
		iframeCssUrl: contextPath + '/css/editor.css'
	};
	var editor = new baidu.editor.ui.Editor(options);
	editor.render("contentEditor");

	var options2 = $.extend({}, options);
	var editor2 = new baidu.editor.ui.Editor(options2);
	editor2.render("summaryEditor");
});