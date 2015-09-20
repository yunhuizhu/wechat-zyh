<!doctype html>
<html>
<head>
<base href="<%=request.getContextPath() + "/wap/"%>">
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=no"/>
<title>投票</title>
<link rel="stylesheet" href="css/main.css" />
<script type="text/javascript" src="js/iscroll.js"></script>
<script src="js/jquery-1.7.2.min.js"></script>  
</head>

<body>
<!--投票页面-->
<div id="voteMain">
  <div id="wrapper">
    <div id="scroller">
      <ul id="thelist">
       <!--<li class="active"> <img src="img/pic.jpg" />
          <div class="text-ifon"> <span class="text">"鲜花鲜花鲜花鲜花鲜花"</span> <span class="author">by 13412345678</span> </div>
        </li>-->
            <li><div id="pullRight" class="loading"> <span class="pullRightIcon"></span><span class="pullRightLabel">正在加载信息,请稍后</span> </div></li>

      </ul>
      <script>
	  var mainpicpath=0; //图片路径前缀
	  var userId = 0; //用户ID
	  $(function(){
		  $("#scroller").height($(window).height());
		  var winW = $(window).width();
		  var liL = $("ul#thelist li").length;
		  $("ul#thelist li").width(winW);
		  $("#wrapper").width(winW);
		  $("#scroller").width(winW*liL);
		  $(".input-text").width(winW - 120);
		  $(".input-text").bind("focus",function(){
			  if($(this).val() == "说点话呗~"){
				  $(this).val("");
			  }
		  });
		  $(".input-text").bind("blur",function(){
			  if($(this).val() == ""){
				  $(this).val("说点话呗~");
			  }
		  });
		  getuId(); //获取用户的uid
		  //loadvote();
	  });
	  
	  var myScrollVote,pullRightEl=0;
	  var winW = $(window).width();
	  var voteId = 0; //投票的ID
	  var saveId; //保存下一张投票图片的ID
	  var loadVoteAddr = 0;
	   
	  //异步获取uid	
	 function getuId(){
		  $.ajax({
			  url:"../customer/getCurrentUserInfo",
			  dataType:"json",
			  success: function(data){
				  if(data.success){
					  userId = data.uid;
					  loadvote(); //获得uid加载第一个投票信息
				  }else{
					  alert(data.msg);	
				  }
			  },
			  error: function(){
				  alert("通讯发生错误");
			  }
		  });
	  }
	  
	  //判断是否用户是第一次进入投票页面
	  function loadvote(){
	  $(function(){
		  $.ajax({
			  url:"../customer/getCurrentUserInfo",
			  dataType:"json",
			  success: function(data){
				  //判断是否用户是第一次进入投票页面
				  if(data.success){
					  voteurl = data.voteInfoAddr;
					  if(voteurl != ""){
   					      var votedata = voteurl.split("&");
						  voteId = votedata[1].replace(/[^0-9]/ig,"");
						  loadmore();
					  }else{
						  loadmore();
					  }
				  }
			  },
			  error: function(){
				  alert("通讯发生错误");
			  }
			  
		  });
	  });
	  }
	  //加载投票信息
	  function loadmore(){
		  //alert(voteId);
		  loadVoteAddr = "/esaying/interesting-vote.action?uid="+userId+"&id="+voteId+"&result=true";
		  $(function(){
				$.ajax({
					url:loadVoteAddr,
					success: function(dataVote){
						mainpicpath = dataVote.picViewUrl;
						var voteLi = '<li class="active" style="width:'+winW+'px;"> <img src="'+ dataVote.picViewUrl + dataVote.picPath +'" /><div class="text-ifon"><span class="text">"'+ dataVote.content +'"</span><span class="author">by '+ dataVote.userInfo.lastName + dataVote.userInfo.firstName +'</span></div></li>';
						voteId = dataVote.id; //获取下一张投票图片的id
						//alert(voteId);
						$("ul#thelist > li:last-child").before(voteLi);
						$("#scroller").width($("ul#thelist li").length * $(window).width());
						myScrollVote.refresh();
						//alert(voteLi);
					},
					error: function(XMLHttpRequest,textStatus,errorThrown){
						$(".pullRightIcon").hide();
						$(".pullRightLabel").html("通讯发生错误,请刷新页面");
						/*alert(XMLHttpRequest.status + ", " + textStatus + ", " +errorThrown);*/
					}
				});
			
		  
		  	$("#scroller").width($("ul#thelist li").length * $(window).width());
		 		myScrollVote.refresh();
		  });
	  }	
	  function loaded() {
		  pullRightEl = document.getElementById('pullRight');	
		  pullRightOffset = pullRightEl.offsetHeight;
		  myScrollVote = new iScroll('wrapper', {
			  snap: true,
			  vScroll: false,
			  momentum: true,

			  bounce: false,
			  hScrollbar: false,
			  useTransition: true,
			  onRefresh: function () {
				 
			  },
			  onScrollMove: function () {
				
			  },
			  onScrollEnd: function () {
				  document.querySelector('#thelist li.active').className = '';

				  loadmore();
				  
			  }
		   });
	  	}

			document.addEventListener('touchmove', function (e) { e.preventDefault(); }, false);
			document.addEventListener('DOMContentLoaded', function () { loaded(); }, false);
		</script> 
    </div>
  </div>
  <div class="attitude">
    <textarea class="input-text" name="">说点话呗~</textarea>
    <div class="btn-agreed"></div>
    <div class="btn-against"></div>
    <script>
		var chooseYes,chooseNo,yesProportion,noProportion,voteDetailAll,uservoteresult=0;
		var voteajax = 1;  //防止由于网络延迟，用户多次点击投票按钮
		var voteIfonUrl = "/esaying/interesting-vote!castvote.action", //进行投票请求地址
			voteIfonUrl2 = 0;
		$(function(){
			var comment;
			//选择赞成
			$(".btn-agreed").bind("click",function(){			
				comment = $(".input-text").val();
				comment = (comment == "说点话呗~")?"":comment;
				voteIfonUrl2 = "uid="+userId+"&id="+voteId+"&comment="+comment+"&result=true";
				//alert(comment);
				if(voteajax){
					voteajax = 0;
					//alert(loadCommentData);
					$.ajax({
						type:"POST",
						url:voteIfonUrl,
						data:voteIfonUrl2,
						success: function(voteDetail){
							voteajax = 1;
							if(voteDetail.success){
								voteDetailAll = voteDetail;
								chooseYes =  voteDetail.countYes;
								chooseNo = voteDetail.countNo;
								yesProportion = Math.floor((chooseYes / (chooseYes+chooseNo))*100);
								//alert(voteId+", "+voteDetail+", "+chooseNo+", "+chooseYes+", "+yesProportion);
								$("#conB").show();
								$(".showfs").stop().animate({bottom:0},300);
								showfs(1,yesProportion,"#e6770b");
								uservoteresult = 1;//保存用户的选择
							}else{
								alert(voteDetail.msg);	
							}
						},
						error: function(){
							voteajax = 1;
							alert("false");
						},
					});
				}
			});
			//选择反对
			$(".btn-against").bind("click",function(){
				var comment = $(".input-text").val();
				comment = (comment == "说点话呗~")?"":comment;
				voteIfonUrl2 = "uid="+userId+"&id="+voteId+"&comment="+comment+"&result=false";
				if(voteajax){
					voteajax = 0;
					$.ajax({
						type:"POST",
						url:voteIfonUrl,
						data:voteIfonUrl2,
						success: function(voteDetail){
							voteajax = 1;
							if(voteDetail.success){
								voteDetailAll = voteDetail;
								chooseYes =  voteDetail.countYes;
								chooseNo = voteDetail.countNo;
								noProportion = Math.floor((chooseNo / (chooseYes+chooseNo))*100);
								$("#conB").show();
								$(".showfs").stop().animate({bottom:0},300);
								showfs(0,noProportion,"#8aaf2e");
								uservoteresult = 0;//保存用户的选择
							}else{
								alert(voteDetail.msg);	
							}
						},
						error: function(){
							voteajax = 1;
							alert("false");
						},
					});
				}
			});
		});
	</script>
    <div class="clear"></div>
  </div>
  <div id="conB"></div>
  <div class="showfs">
    <a class="btn-into" href="javascript:void(0)">
      <canvas class="showd" id="showd" width="160px" height="160px">71%</canvas>
      <script>
			function showfs(data,yesProportion,color){
				data = (data)?"选YES":"选NO";
				var showfs = $("canvas.showd");
				var text = yesProportion + "%";
				var process = text.substring(0, text.length-1);
				var canvas = document.getElementById("showd");
				var context = canvas.getContext("2d");	
				context.clearRect(0,0,100,100);
				//灰色的圆环
				context.beginPath();
				context.arc(70, 70, 60, 0, Math.PI * 2,false);
				context.lineWidth = 15;
				context.strokeStyle = '#bfbfbf';
				context.stroke();
				//比例圆环
				context.beginPath();
				context.arc(70, 70, 60, 0, Math.PI * 2 * process / 100,false);
				context.lineWidth = 15;
				context.strokeStyle = color;
				context.stroke();
				//中间白色背景
				context.beginPath();
				context.arc(70, 70, 50, 0, Math.PI * 2 * process);
				context.fillStyle = '#fff';
				context.fill();
				//中间文字
				context.beginPath();
				context.font = "bold 16pt Arial";  
				context.fillStyle = '#000000';  
				context.textAlign = 'center';  
				context.textBaseline = 'middle';  
				context.moveTo(70, 70);  
				context.fillText(text, 70, 60);  
				context.moveTo(70, 70);  
				context.font = "bold 14pt Arial"
				context.fillText(data, 70, 80); 
			 }
		</script> 
    </a>
    <span class="into-detail">点击查看详情</span> </div>
    <script>
		$(function(){
			$("#conB").click(function(){
				$(".showfs").stop().animate({bottom:"-170px"},200);
				$("#conB").hide();			
			});
			var showDetailCon = 1; //防止用户由于网络延迟多次点击查看详情按钮
			$(".btn-into").bind("click",function(){
				
				var voteresultimg;
				$("ul#thelist li").each(function() {
                    if($(this).hasClass("active")){
						voteresultimg = $(this).html();
					}
                });
				$(".fixed-top .bg").html(voteresultimg);
				if(showDetailCon){
					showDetailCon = 0;
					$.ajax({
						url:"../customer/setCurrentUserInfo",
						type:"POST",
						data:"voteInfoAddr="+encodeURIComponent(voteIfonUrl+"?"+voteIfonUrl2)+"&voteCommentAddr="+encodeURIComponent(loadVoteAddr),
						dataType:"json",
						success: function(data){
							if(data.success){
								window.location = "voteresult.jsp";
								showDetailCon = 1;
							}else{
								alert(data.msg);
								showDetailCon = 1;
							}
						},
						error: function(){
							alert("查看详情失败");
							showDetailCon = 1;
						}
						
					});
				}
			});	
		})
	</script>
    <div onclick="myScrollVote.scrollToPage('next', 0);return false" class="click-next"></div>

</div>

</body>
</html>
