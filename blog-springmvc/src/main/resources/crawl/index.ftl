<!DOCTYPE html>
<html lang="zh">
<head>
<style type="text/css">
body {
font-size: 12px;
font-family: Tahoma,Geneva,'宋体B8B\4F53';
}
html {
color: black;
}
body, div, dl, dt, dd, ul, ol, li, h1, h2, h3, h4, h5, h6, form, fieldset, legend, input, button, textarea, p, blockquote {
margin: 0;
padding: 0;
}
.mtb4 {
margin-top: 4px;
margin-bottom: 4px;
}
.clearfix {
display: block;
}

.tbCenter {
border: 1px solid #CCC;
margin: 0 auto;
/*width: 980px;*/
background-color: white;
/*height: 600px;*/
}
.pr {
position: relative;
}
.cb {
clear: both;
}
img {
border: 0;
cursor:pointer;
}
.w980 {
width: 980px;
margin: 0 auto;
}
.tc {
text-align: center;
}
.main-btn {
display: inline-block;
text-align: center;
padding: 12px 0;
}

a.btn-red, a.btn-blue {
display: inline-block;
height: 26px;
font: bold 14px/26px Tahoma,Geneva,'宋体';
background: url(../../nav_bg.png) 0 0 repeat-x;
margin: 0 2px;
padding: 0 16px;
text-align: center;
vertical-align: middle;
color: white;
text-decoration: none;
border-radius: 2px;
}
a.btn-red {
border: 1px solid #C00F20;
background-position: 0 0;
text-shadow: 1px 1px 0 black;
cursor:pointer;
}
a.btn-red:hover
{
background-position:0 -50px;
}
a.btn-red:focus,a.btn-red:active
{
background-position:0 -100px;
}
.main-btn select {
font-size: 14px;
vertical-align: middle;
width:100px;
}
h1, h2, h3, h4, h5, h6 {
font-size: 100%;
font-weight: normal;
}
.title {
font-family: Tahoma,"宋体",Helvetica,Arial,sans-serif;
border-bottom: 1px solid #F0F0F0;
line-height: 40px;
}
.title h1, .title h2 {
font-family: "微软雅黑",Tahoma,Helvetica,Arial,sans-serif;
display: inline-block;
color: red;
font-weight: 400;
vertical-align: middle;
}
.title h1 {
font-size: 24px;
}
.title h1 a {
color: red;
text-decoration: none;
}
.title em {
font-style: normal;
color: #666;
display: inline-block;
padding: 0 6px;
vertical-align: middle;
}
.title h2 {
font-size: 20px;
}
.title span {
color: #666;
font-size: 14px;
vertical-align: middle;
}
#page {
color: #F60;
font-size: 22px;
font-weight: bold;
vertical-align: middle;
}
</style>
<meta charset="utf-8">
<link rel="icon" href="data:image/png;base64,AAABAAEAEBAAAAEACABoBQAAFgAAACgAAAAQAAAAIAAAAAEACAAAAAAAaAEAAAAAAAAAAAAAAAAAAAAAAAAAAAAAgAAAAPr6+gD/AAAA9lUAAHL//gAhcP0A20YEAICAAADYRAIAAP//AP//AACAgIAARgVaAP///wDoWgAA+XAgAME8AAD/h0sAS//+AP+CSQD7+/sAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAFBERERERERERERERERERERIPDw8PDw8PDw8PDw8PDxESDw8PDw8PDw8PDw8PDw8REg8TExMTExMTExMTExMPERIPDw8PDw8TFRMPDw8PDxESDw8TExMTEw8VEw8PDw8REg8PDw8TExMTExMPDw8PERIPDw8PExMPDw8PDw8PDxESDw8TEw8TExMTExMTEw8REg8PExUVFQ8VFQ8VEw8PERIPExMPFRUVFQ8PDxUTDxESDxMTExMVFRUVFRUTEw8REg8PDw8TFRUVDxUTDw8PERIPDxMTExMTExMTExMPDxESDw8PDw8PDw8PDw8PDw8REhISEhISEhISEhISEhISFAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA=">
<title>${comicName}-${comicVolumn}</title>
</head>
<body>

<div class="w980 title">
<div>
<h1><a href="">${comicName}</a></h1> <em>/</em>  <h2>${comicVolumn}</h2> <em>/</em><span>(<span id="page"></span>/${count})</span>
</div>
</div>

<div class="w980 tc">
	<div class="main-btn">
		<a href="../../../../imanhua/${comicName}" class="btn-red">漫画列表</a>
		 <#if prev != "">
		 <a href="../${prev}/index.html" class="btn-red prevC">上一章</a>
		 <#else>
		 <a onclick="alert('已经是第一章！')" class="btn-red prevC">上一章</a>
		 </#if>
		<a name="prevPage" class="btn-red">上一页</a> 
		<select>
		<#list 1..count as x>
			<option value="${x}">第${x}页</option>
		</#list>
		</select> 
		<a name="nextPage" class="btn-red">下一页</a>
		<#if next != "">
		 <a href="../${next}/index.html" class="btn-red nextC">下一章</a>
		<#else>
		 <a onclick="alert('已经是最后一章！')" class="btn-red nextC">下一章</a>
		</#if>
		<a href="../../../../imanhua" class="btn-red">返回首页</a>
	</div>
</div>
</div>
<div class="clearfix mtb4">
<table border="0" cellspacing="0" cellpadding="0" class="pr tbCenter cb">
<tbody>
<tr>
<td align="center" id="tbBox">
<div align="center" id="mangaBox">
 <img id="mangaFile" alt="火影忍者 607话正式版">
 <script type="text/javascript">
	var total = ${count};
	function getQuery(b){
		var a=RegExp("[?&]"+b+"=([^&]*)").exec(window.location.search);
		return a?decodeURIComponent(a[1].replace(/\+/g," ")):"";
	}
	
	function getImgFileName(num){
		var next = Number(num);
		if(next>total){
			window.location.search = '?p=1';
		}
		if(Math.floor(next/10) !=0){
			next = "0"+next;
		}else{
			next = "00"+next;
		}
		return 'imanhua_'+next+'.png';
	}
 
	
	var mangaFile = document.getElementById('mangaFile');
	var p = getQuery('p');
	mangaFile.setAttribute("src",p == ""?"imanhua_001.png":getImgFileName(p));
	var page = document.getElementById('page');
	page.innerHTML = p == ""?"1":p;
	
	mangaFile.ondblclick = function(){
		var src = this.getAttribute('src');
		var num = /imanhua_(\d\d\d)/g.exec(src)[1];
		
		var next = Number(num)+1;
		if(next>total){
			alert('已经是最后一页！');
			//window.location.search = '?p=1';
		}else{
			window.location.search = '?p='+next;
		}
	};
	
 </script>
</div>
</td>
</tr>
</tbody>
</table>
</div>
<div class="w980 tc">
	<div class="main-btn">
		<a href="../../../../imanhua/${comicName}" class="btn-red">漫画列表</a> 
		 <#if prev != "">
		 <a href="../${prev}/index.html" class="btn-red prevC">上一章</a>
		 <#else>
		 <a onclick="alert('已经是第一章！')" class="btn-red prevC">上一章</a>
		 </#if>
		<a name="prevPage" class="btn-red">上一页</a> 
		<select>
		<#list 1..count as x>
			<option value="${x}">第${x}页</option>
		</#list>
		</select> 
		<a name="nextPage" class="btn-red">下一页</a> 
		<#if next != "">
		 <a href="../${next}/index.html" class="btn-red nextC">下一章</a>
		<#else>
		 <a onclick="alert('已经是最后一章！')" class="btn-red nextC">下一章</a>
		</#if>
		<a href="../../../../imanhua" class="btn-red">返回首页</a>
	</div>
</div>
		<script type="text/javascript">
			var p = getQuery('p');
			
			var selects=document.getElementsByTagName("select");
			for(var i=0;i<selects.length;i++){
				selects[i].selectedIndex = p==""?0:(Number(p)-1);
				selects[i].onchange = function() { 
					window.location.search = '?p='+Number(this.value);
				} 
			}

			var prevs=document.getElementsByName("prevPage");
			for(var i=0;i<prevs.length;i++){
				prevs[i].onclick = function(){
					if(p==""||p=="1"){
						alert('已经是第一页！');
						//window.location.search = '?p='+total;
					}else{
						window.location.search = '?p='+(Number(p)-1);
					}
				};
			}
			var nexts=document.getElementsByName("nextPage");
			for(var i=0;i<nexts.length;i++){
				nexts[i].onclick = function(){
					if(p==""){
						window.location.search = '?p=2';
					}else if(Number(p)==total){
						alert('已经是最后一页！');
						//window.location.search = '?p=1';
					}else{
						window.location.search = '?p='+(Number(p)+1);
					}
				};
			}
		</script>
</body>
</html>