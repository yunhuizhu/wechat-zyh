<!doctype html>
<html>
<head>
<base href="<%=request.getContextPath() + "/wap/"%>">
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=no"/>
<title>投票结果</title>
<link rel="stylesheet" href="css/main.css" />
<script src="js/iscroll.js"></script>
<script src="js/jquery-1.7.2.min.js"></script>
</head>

<body>
<script type="text/javascript">
var picUrl,voteurl,voteId = 0;
$(function(){
	$.ajax({
		url:"../customer/getCurrentUserInfo",
		dataType:"json",
		success: function(data){
			if(data.success){
				voteurl = data.voteInfoAddr;
				picUrl = data.picViewUrl;
				var votedata = voteurl.split("&");
				voteId = votedata[1];
				//alert(voteurl);
				voteIfon();//加载投票评论图片信息
				pullUpAction();//加载第一页评论
			}
		},
		error: function(){
			alert("通讯发生错误");
		}
		
	});
});
var myScroll,
	pullUpEl,
	generatedCount = 0;
	
var errorStatus = 1; //判断ajax请求是否抛出error
var pageNum = -1; //获取数据的页码

//异步加载投票评论图片信息
function voteIfon(){
$.ajax({
	url:voteurl,
	dataType:"json",
	success: function(voteDetailAll){
		//alert(voteDetailAll.picPath);
		$(".vote-username").text(voteDetailAll.nickName);
		$(".vote-createtime").text(voteDetailAll.createTime);
		$(".vote-person").text(Math.floor(voteDetailAll.countYes)+Math.floor(voteDetailAll.countNo));
		$(".vote-comments").text(voteDetailAll.comments);
		var voteuserface = (voteDetailAll.face != "")?picUrl+"/"+voteDetailAll.face:"img/user_icon.jpg";
		$(".user-icon").html('<img src="'+voteuserface+'" />');
		$(".fixed-top .bg img").attr("src",picUrl+voteDetailAll.picPath);
		showDetail(voteDetailAll.myIdea,voteDetailAll.countYes,voteDetailAll.countNo);//绘制比例圆环
	},
	error: function(){
		alert("通讯发生错误");
	}
});
}

//异步加载分页信息
function pullUpAction () {
	$(function(){
		//if用于判断是否执行ajax发生过了错误或是否数据已经全部加载完毕
		if(errorStatus){
			pageNum++;
			$.ajax({
				url:"/esaying/interesting-vote!getcomment.action?"+voteId+"&pageSize=10&pageIndex="+pageNum,
				success: function(dataObj){
					//var dataObj=eval("("+data+")");
					//alert(voteId);
					if(dataObj.array.length){
						for(i=0; i<dataObj.array.length;i++){
							var createTime = dataObj.array[i].createTime; 
							if(i%2 == 0){
								var face = dataObj.array[i].face;
								face = (face == "")?"img/user_icon.jpg":dataObj.picViewUrl+face;
								img = (dataObj.array[i].result)?'<img src="img/vote_result_yes.png" />':'<img src="img/vote_result_no.png" />';
								var li = '<li><div class="user-tx"><img src="'+face+'" /></div><div class="user-xx"><div class="user-al">'+dataObj.array[i].account+' '+img+'</div><div>'+dataObj.array[i].comment+'</div></div><div class="user-rq"> '+createTime.substr(0, 10)+' </div></li>';	
								$("ul#comment-list").append(li);
							}else{
								var li = '<li style="background-color:#f5f5f5;"><div class="user-tx"><img src="'+face+'" /></div><div class="user-xx"><div class="user-al">'+dataObj.array[i].account+' '+img+'</div><div>'+dataObj.array[i].comment+'</div></div><div class="user-rq"> '+createTime.substr(0, 10)+' </div></li>';
								$("ul#comment-list").append(li);
							}
						}					
					}else{
						$("ul#comment-list").append('<li style="text-align:center; font-size:12px; color:#888; height:24px; line-height:24px;">所有评论加载完毕</li>');
						errorStatus = 0; //全部内容加载完毕
						$("#pullUp").hide();
					}
					/*alert("success");*/
					myScroll.refresh();	
				},
				error: function(XMLHttpRequest, textStatus, errorThrown){
					alert(XMLHttpRequest.status + " ," + textStatus + " ," +errorThrown);
					$("ul#prizeUl").append('<li style="padding-left:2%; font-size:12px; color:#333; line-height:20px;">通讯发生错误<br />请尝试刷新页面</li>')
					errorStatus = 0;//发生错误,不会再加载内容
				}	
			});
		}
	});	
}

function showDetail(uservote,voteYes,voteNo){					
	var winw = $(window).width();
	$(".result-canvas").css("margin-left",(winw-160)/2);
	var totleVote = voteYes+voteNo;
	//alert(voteYes);
	var w = $("canvas.result-canvas").width();
	var h = $(window).height();
	var thisway = $("canvas.result-canvas");
	var votePercentage = Math.floor(voteYes/totleVote*100);
	var text = votePercentage+"%";
	var process = text.substring(0, text.length-1);
	$(".percentage-agreed").text(votePercentage);
	$(".percentage-against").text(100-votePercentage);
	var bAngle,eAngle;
	var canvas = document.getElementById("result-canvas");
	var context = canvas.getContext("2d");	
	context.clearRect(0,0,w,w);
	//判断赞成的比例是否大于50%
	if(process >= 50){
		bAngle = 0.5 - (process-50)/100;
		eAngle = bAngle + process*2/100;
	}else{
		bAngle = 0.5 + (50 - process)/100;
		eAngle = bAngle + process*2/100;
	}
	//左边的圆环
	context.beginPath();
	context.arc(80, 80, 60, Math.PI * bAngle, Math.PI * eAngle,false);
	context.lineWidth = 15;
	context.strokeStyle = '#e6770b';
	context.stroke();
	//右边的圆环
	context.beginPath();
	context.arc(80, 80, 60, Math.PI * bAngle - 0.1, Math.PI * eAngle + 0.1,true);
	context.lineWidth = 15;
	context.strokeStyle = '#8aaf2e';
	context.stroke();
	//中间的箭头
	context.beginPath();
	//选择yes
	if(uservote){
		context.arc(80, 80, 19, Math.PI * 1.25, Math.PI * 0.75,false);
		context.lineTo(45,80);
		context.fillStyle = '#e6770b';
	}else{
	//选择no
		context.arc(80, 80, 19, Math.PI * 0.25, Math.PI * 1.75,false);
		context.lineTo(115,80);
		context.fillStyle = '#8aaf2e';
	}
	context.fill();
	context.moveTo(80, 80);  
	context.font = "normal 14px Arial"; 
	context.fillStyle = '#ffffff';  
	context.textAlign = 'center';  
	context.textBaseline = 'middle';
	context.fillText("我在", 80, 72);
	context.fillText("这里", 80, 87);
  
}

function loaded2() {

	pullUpEl = document.getElementById('pullUp');	
	pullUpOffset = pullUpEl.offsetHeight;
	myScroll = new iScroll('user-comment', {
		snap: true,
		useTransition: true,
		hScroll: false,
		lockDirection: true,
		momentum: true,
		
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
				pullUpAction();	// Execute custom function (ajax call?)
			}
		}
	});
	
}

document.addEventListener('touchmove', function (e) { e.preventDefault(); }, false);
document.addEventListener('DOMContentLoaded', function () { setTimeout(loaded2, 200); }, false);


</script>
  <div id="user-comment">
    <div id="scroller2">
      <div class="fixed-top">
        <div class="bg"> <img src="img/pic.jpg" /> </div>
        <div class="content">
          <div class="title">
            <div class="user-icon"></div>
            <div class="user-ifon"> <span class="vote-username"></span> <br>
              你认可这句话吗？ </div>
            <div class="user-date"> <span class="vote-createtime"></span> 发起 </div>
            <div class="clear"></div>
          </div>
          <ul class="total">
            <li>
              <div class="number"></div>
              <span class="vote-person"></span>人参与</li>
            <li>
              <div class="comment"></div>
              <span class="vote-comments"></span>条评论</li>
          </ul>
          <div class="clear"></div>
          <div class="result-show">
            <div class="user-choose" style="display:none;">YES</div>
            <canvas class="result-canvas" id="result-canvas" width="160px" height="160px">66%</canvas>
            <div class="r-agreed"> <span class="percentage-agreed">0</span>%
              <div class="i-agreed"><img src="img/vote_result_yes.png" />YES</div>
            </div>
            <div class="r-against"> <span class="percentage-against">0</span>%
              <div class="i-against"><img src="img/vote_result_no.png" />NO</div>
            </div>
          </div>
        </div>
      </div>
      <ul id="comment-list">
        <!--<li>
          <div class="user-tx"> <img src="img/user_icon.jpg" /> </div>
          <div class="user-xx">
            <div class="user-al">用户的名称：<img src="img/vote_result_yes.png" /><img src="img/vote_result_no.png" /><img src="img/icon_s_message_dark.png" /></div>
            用户评论内容 </div>
          <div class="user-rq"> 2015-04-29 </div>
        </li>-->
        
      </ul>
      <div id="pullUp"> <span class="pullUpIcon"></span><span class="pullUpLabel">查看更多</span> </div>
    </div>
</div>
</body>
  </html>
