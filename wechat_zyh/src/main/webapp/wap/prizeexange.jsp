<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!doctype html>
<html>
<head>
<base href="<%=request.getContextPath() + "/wap/"%>">
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=no"/>
    <title>礼品兑换</title>
    <link rel="stylesheet" href="css/main.css" />
    <script src="js/iscroll.js"></script>
    <script src="js/jquery-1.7.2.min.js"></script>
    <script type="text/javascript">

        $(document).ready(function(e) {
            pullUpAction(); //页面装载完毕就加载第一页的数据
        });

        var myScroll,
                pullUpEl,
                generatedCount = 0;

        var errorStatus = 1; //判断ajax请求是否抛出error
        var pageNum = -1; //获取数据的页码
        //异步加载
        function pullUpAction () {
            $(function(){
                //if用于判断是否执行ajax发生过了错误或是否数据已经全部加载完毕
                if(errorStatus){
                    pageNum++;
                    $.ajax({
                        url:"/esaying/exchange-gift.action?pageIndex="+pageNum+"&pageSize=10",
                        success: function(data){
                            //var dataObj=eval("("+data+")");
                            if(data.array.length != 0){
                                for(i=0; i<data.array.length;i++){
                                    if(i%2 != 0){
                                        var li = '<li style="background-color:#f5f5f5"><a href="../prize/detail.do?prizeId='+data.array[i].id+'"><div class="pro-img"><img src="'+data.picViewUrl+data.array[i].iconPath+'" /></div><div class="pro-js"><div class="pro-name"><nobr>'+data.array[i].prizeName+'</nobr></div><div class="pro-view"><img src="img/menu_see.png" /><div class="pro-view-text">'+data.array[i].prizeClickCount+'人看过&nbsp;</div><img src="img/icon_s_buy_grey.png" /><div class="pro-view-text">'+data.array[i].exchangeCount+'人已换</div></div><div style="color:#ea7607">'+data.array[i].prizePoints+' 积分</div></div></a></li>';
                                        $("ul#prizeUl").append(li);
                                    }else{
                                        var li = '<li><a href="../prize/detail.do?prizeId='+data.array[i].id+'"><div class="pro-img"><img src="'+data.picViewUrl+data.array[i].iconPath+'" /></div><div class="pro-js"><div class="pro-name"><nobr>'+data.array[i].prizeName+'</nobr></div><div class="pro-view"><img src="img/menu_see.png" /><div class="pro-view-text">'+data.array[i].prizeClickCount+'人看过&nbsp;</div><img src="img/icon_s_buy_grey.png" /><div class="pro-view-text">'+data.array[i].exchangeCount+'人已换</div></div><div style="color:#ea7607">'+data.array[i].prizePoints+' 积分</div></div></a></li>';
                                        $("ul#prizeUl").append(li);
                                    }
                                }
                            }else{
                                $("ul#prizeUl").append('<li style="text-align:center; font-size:12px; color:#888; height:24px; line-height:24px;">所有礼品加载完毕</li>');
                                errorStatus = 0; //全部内容加载完毕
                                $("#pullUp").hide();
                            }
                            /*alert("success");*/
                            myScroll.refresh();
                        },
                        error: function(XMLHttpRequest, textStatus, errorThrown){
                            /*alert(XMLHttpRequest.status + " ," + textStatus + " ," +errorThrown);*/
                            $("ul#prizeUl").append('<li style="padding-left:2%; font-size:12px; color:#333; line-height:20px;">通讯发生错误<br />请尝试刷新页面</li>')
                            errorStatus = 0;//发生错误,不会再加载内容
                        }
                    });
                }
            });
        }


        function loaded() {

            pullUpEl = document.getElementById('pullUp');
            pullUpOffset = pullUpEl.offsetHeight;
            myScroll = new iScroll('prizeexange', {
                useTransition: true,

                onRefresh: function () {
                    if (pullUpEl.className.match('loading')) {
                        pullUpEl.className = '';
                        pullUpEl.querySelector('.pullUpLabel').innerHTML = '查看更多';
                    }
                },
                onScrollMove: function () {
                    if (this.y < (this.maxScrollY - 5) && !pullUpEl.className.match('flip')) {
                        pullUpEl.className = 'flip';
                        pullUpEl.querySelector('.pullUpLabel').innerHTML = '查看更多';
                        this.maxScrollY = this.maxScrollY;
                    } else if (this.y > (this.maxScrollY + 5) && pullUpEl.className.match('flip')) {
                        pullUpEl.className = '';
                        pullUpEl.querySelector('.pullUpLabel').innerHTML = '查看更多';
                        this.maxScrollY = pullUpOffset;
                    }
                },
                onScrollEnd: function () {
                    if (pullUpEl.className.match('flip')) {
                        pullUpEl.className = 'loading';
                        pullUpEl.querySelector('.pullUpLabel').innerHTML = '加载中...';
                        pullUpAction();	
                    }
                }
            });

        }

        document.addEventListener('touchmove', function (e) { e.preventDefault(); }, false);
        document.addEventListener('DOMContentLoaded', function () { setTimeout(loaded, 200); }, false);


    </script>
</head>

<body>
<div class="showjf">
    现有积分：<span style="color:#ea7607">${currentPoint}</span>
</div>
<div id="prizeexange">
    <div class="prize-detail">
        <ul id="prizeUl">
            <!--<li>
            	<a href="#">
            	<div class="pro-img">
                	<img src="img/pro-img.jpg" />
                </div>
                <div class="pro-js">
                	<div class="pro-name">单筒望眼镜</div>
                    <div class="pro-view"><img src="img/menu_see.png" /><div class="pro-view-text">44人看过&nbsp;</div><img src="img/icon_s_buy_grey.png" /><div class="pro-view-text">1人已换</div></div>
                    <div style="color:#ea7607">502 积分</div>
                </div>
                </a>
            </li>-->
        </ul>
        <div id="pullUp">
            <span class="pullUpIcon"></span><span class="pullUpLabel">查看更多</span>
        </div>
        <script>
            $(function(){
                function setLibackground(){
                    $(".prize-detail ul li").each(function(index) {
                        if(index%2 != 0){
                            $(this).css("background-color","#f5f5f5");
                        }
                    });
                }
                setLibackground();
            });
        </script>
    </div>
</div>
<!--礼品兑换成功显示的提示框-->
<div class="dh-tip" <c:if test="${exchangeSubmit==null}" >style="display:none;"</c:if>>
    您兑换的 ${prizeName}
    <div class="p-left">
        <span style="color:#ea7607">正在等待核审</span>
        <a href="javascript:void(0)" class="know">知道了</a>
    </div>
</div>
<script>
    $(function(){
        if($(".dh-tip").css("display") == "block"){
            $(".know").bind("click",function(){
                $(".dh-tip").fadeOut(500);
            });
            /*setTimeout(function(){
             $(".dh-tip").fadeOut(500);
             },1500);*/
        }
    });
</script>
</body>
</html>
