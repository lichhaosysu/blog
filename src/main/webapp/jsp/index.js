/**
 * 返回顶端
 */
function ToTop(btnId) {
	this.backTopId = 0;
	this.btn = document.getElementById(btnId);
	this.init();
}

ToTop.prototype = {
	init : function() {
		var _this = this, version = 0;
		if (!!window.ActiveXObject) {
			version = parseFloat(navigator.userAgent.toLowerCase().match(
					/msie (\d+)/)[1]);
		}
		util.addHandler(this.btn, "click", function() {
			_this.backTopId = setInterval(function() {
				_this.backTop()
			}, 30);
		});
		util.addHandler(window, "scroll", function() {
			var doc = document, scrTop = doc.documentElement.scrollTop
					|| doc.body.scrollTop;

			_this.btn.style.display = (scrTop >= 80 ? "block" : "none");
			// ie6下返回顶端位置
			if (version == 6)
				_this.btn.style.top = 600 + scrTop + "px";
		});
	},
	backTop : function() {
		var doc = document, scrTop = doc.documentElement.scrollTop
				|| doc.body.scrollTop, speed = Math.ceil(scrTop / 4);
		if (scrTop == 0)
			clearInterval(this.backTopId);
		else
			doc.documentElement.scrollTop = doc.body.scrollTop = scrTop - speed;
	}
};

$(function() {

	var options = {
		toolbars : [ [ 'fullscreen', 'source', '|', 'undo', 'redo', '|',
				'fontfamily', 'fontsize', '|', 'bold', 'italic', 'underline',
				'strikethrough', '|', 'pasteplain', '|', 'forecolor',
				'backcolor', '|', 'link', 'unlink', '|', 'insertimage',
				'emotion', 'scrawl', 'attachment', 'highlightcode', 'template',
				'background', '|', 'spechars', 'wordimage', '|', 'preview',
				'searchreplace', 'help' ] ],
		topOffset : 41,
		elementPathEnabled : false,
		autoClearinitialContent : false,
		initialContent:''
	};

	var editor = new baidu.editor.ui.Editor(options);

	editor.render("myEditor");
	new ToTop("J_2Top");

//	$('#publish').click(function() {
//		$('#mainForm').submit();
//	});

});
