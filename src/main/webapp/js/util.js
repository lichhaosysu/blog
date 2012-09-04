/**
 * Created by JetBrains PhpStorm.
 * User: xuheng
 * Date: 12-4-9
 * Time: 上午10:16
 * To change this template use File | Settings | File Templates.
 */
var util = function () {
    return {
        addHandler:function (element, type, handler) {
        	// Mozilla, Netscape, Firefox
            if (element.addEventListener) {
                element.addEventListener(type, handler, false);
            }
            //IE
            else if (element.attachEvent) {
                element.attachEvent("on" + type, handler);
            }
            else {
                element["on" + type] = handler;
            }
        },
        removeHandler:function (element, type, handler) {
            if (element.removeEventListener) {
                element.removeEventListener(type, handler, false);
            }
            else if (element.detachEvent) {
                element.detachEvent("on" + type, handler);
            }
            else {
                element["on" + type] = null;
            }
        },
        isEmpty:function (data) {
            return data.replace(/[ ]/g, "") != "" ? data : "无";
        },
        getEvent:function (event) {
            return event ? event : window.event;
        },
        getTarget:function (event) {
            return event.target || event.srcElement;
        },
        getInnerText:function (element) {
            return typeof element.textContent == "string" ? element.textContent : element.innerText;
        },
        setInnerText:function (element, text) {
            if (typeof element.textContent == "string")
                element.textContent = text;
            else
                element.innerText = text;
        },
        $G:function (id) {
            return document.getElementById(id)
        },
        getFirstNode:function (ele) {
            return ele.firstChild.nodeType == 1 ? ele.firstChild : ele.firstElementChild;
        },
        getLastNode:function (ele) {
            return ele.lastChild.nodeType == 1 ? ele.lastChild : ele.lastElementChild;
        },
        getNextSiblingNode:function (ele) {
            var firstNode = util.getFirstNode(ele);
            return firstNode.nextSibling.nodeType == 1 ? firstNode.nextSibling : firstNode.nextElementSibling;
        },
        getElementsByClassName:function (clsName) {
            var doc = document;
            if (!doc.getElementsByClassName) {
                var clsArr = [];
                var reg = new RegExp("\\b" + clsName + "\\b");
                var eleArr = doc.getElementsByTagName("*");
                for (var i = 0, eleobj; eleobj = eleArr[i++];) {
                    if (reg.test(eleobj.className))
                        clsArr.push(eleobj);
                }
                return clsArr;
            }
            else {
                return doc.getElementsByClassName(clsName);
            }
        },
        deleteStr2Num:function(str,deleteStr){
            return parseFloat(str.substring(0,str.indexOf(deleteStr)));
        },
        getCharCode:function (event) {
            return event.keyCode || event.which || event.charCode;
        },
        eventSimulation:function (ele) {
            var doc = document,
                event;
            if (doc.createEvent) {
                event = doc.createEvent("UIEvents");
                event.initEvent("click", true, true);
                event.view = doc.defaultView;
                ele.dispatchEvent(event);
            }
            else {
                event = doc.createEventObject();
                ele.fireEvent("onclick", event);
            }
        },
        getStyleValue:function(ele,attr){
            var doc=document;
            var style=ele.currentStyle||doc.defaultView.getComputedStyle(ele,null);
            return parseInt(style[attr].replace(/px/g,""));
        },
        getBrowerVersion:function(){
            var agent = navigator.userAgent.toLowerCase(),
                opera = window.opera,
                browser = {
                    ie		: !!window.ActiveXObject,
                    webkit	: ( agent.indexOf( ' applewebkit/' ) > -1 ),
                    opera	: ( !!opera && opera.version )
                };
            browser.gecko = ( navigator.product == 'Gecko' && !browser.webkit && !browser.opera );
            return browser;
        },
        request:function (option) {
            var ajaxRequest = creatAjaxRequest();
            if (ajaxRequest == null) {
                alert("您的浏览器不支持AJAX！");
                return;
            }
            ajaxRequest.onreadystatechange = function () {
                if (ajaxRequest.readyState == 4) {
                    if (ajaxRequest.status >= 200 && ajaxRequest.status < 300 || ajaxRequest.status == 304) {
                        option.onSuccess(ajaxRequest.responseText);
                    }
                }
                else {
                    if (option.hasLoading)
                        util.$G(option.loading_Id).innerHTML = "<div class='hook_con'><img class='loading_pic' src='images/loading.gif'/></div>";
                }
            };
            ajaxRequest.open("post", option.url, true);
            ajaxRequest.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
            ajaxRequest.send(option.param);
        }
    };

    /**
     * 创建一个ajaxRequest对象
     */
    function creatAjaxRequest() {
        var xmlHttp = null;
        if (window.XMLHttpRequest) {
            xmlHttp = new XMLHttpRequest();
        } else {
            try {
                xmlHttp = new ActiveXObject("Msxml2.XMLHTTP");
            } catch (e) {
                try {
                    xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
                } catch (e) {
                }
            }
        }
        return xmlHttp;
    }
}();