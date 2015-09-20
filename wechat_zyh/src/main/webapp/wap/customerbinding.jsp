<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!doctype html>
<html>
<head>
<base href="<%=basePath%>">
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=no"/>  
<title>会员绑定</title>
<link rel="stylesheet" href="static/css/main.css" />
<script src="static/js/iscroll.js"></script>
<script src="static/js/jquery-1.7.2.min.js"></script>
</head>

<body>
<div class="bd-user">
    <div class="user-text">请输入需要绑定的手机号/邮箱</div>
    <input type="text" name="phoneOrEmail" class="user-input" />
</div>
<div class="bd-pass">
    <div class="pass-text">请输入密码</div>
    <input type="password" name="password" class="pass-input" />
</div>
<div class="btn-area">
    <a class="btn-dh" href="javascript:void(0)">绑定</a>
</div>
<div id="conB"></div>
<div class="bd-tip" style="display:none; z-index:4;">
    <div class="tip-title">
        提示信息
    </div>
    <div class="tip-content">
        
    </div>
    <a href="javascript:void(0)" class="bd-enter">确定</a>
</div>
<input type="hidden" id="openid" value="${openid}" />
<script>
    $(function(){
        $(".user-input").bind("keyup",function(){
            if($(".user-text").text() != ""){
                $(".user-text").text("");
            }
        });
        $(".user-input").bind("blur",function(){
            if($(this).val() == ""){
                $(".user-text").text("请输入需要绑定的手机号/邮箱");
            }
        });
        $(".pass-input").bind("keyup",function(){
            if($(".pass-text").text() != ""){
                $(".pass-text").text("");
            }
        });
        $(".pass-input").bind("blur",function(){
            if($(this).val() == ""){
                $(".pass-text").text("请输入密码");
            }
        });
		function tipclose(data){
			$(".bd-enter").bind("click",function(){
				$("#conB").hide();
				$(".bd-tip").hide();
				if(data){
					WeixinJSBridge.call('closeWindow'); //关闭微信浏览器页面
				}
			});
		}
		var loadajax = 1; //防止用户由于网络延迟一直点击按钮，导致多次向服务器发送请求
        $(".btn-dh").bind("click",function(){
            var mobile="";
            var email="";
            var password=$("input[name='password']").val();
            var openid=$("#openid").val();
            var mobliePartten = /^((13[0-9])|(15[^4,\D])|(18[0,5-9]))\d{8}$/
            if(!mobliePartten.test($('input[name="phoneOrEmail"]').val())){
                var emailPattern=/^[a-z0-9]+([._\\-]*[a-z0-9])*@([a-z0-9]+[-a-z0-9]*[a-z0-9]+.){1,63}[a-z0-9]+$/;
                if(!emailPattern.test($('input[name="phoneOrEmail"]').val())){
					$("#conB").show();
					$(".bd-tip").show();
					$(".tip-content").text("请输入正确的邮箱或手机号").css("color","#666666");
					$(".bd-enter").css("background-color","#666666");
					tipclose(0);
                    return false;
                }else{
                    email= $('input[name="phoneOrEmail"]').val();
                }
            }else{
                mobile= $('input[name="phoneOrEmail"]').val();
            }		
			if(loadajax){
				$(".btn-dh").css({"background-color":"#888888","cursor":"text","box-shadow":"none","-webkit-box-shadow":"none"}).text("处理中...");
				loadajax = 0; //防止用户由于网络延迟一直点击按钮，导致多次向服务器发送请求
				$.ajax({
					type:"POST",
					url:"../customer/wechatBinding.do",
					data:"email="+email+"&password="+password+"&mobile="+mobile+"&openid="+openid,
					dataType:"json",
					success: function(dataobj){
						//var dataobj = eval('(' + data + ')');
						if(dataobj.success){
							$("#conB").show();
							$(".bd-tip").show();
							$(".tip-content").text(dataobj.msg).css("color","#ea7607");
							$(".bd-enter").css("background-color","#ea7607");
							$(".btn-dh").css({"background-color":"#ea7607","cursor":"text"}).text("绑定成功");
							tipclose(1);
							loadajax = 1;
						}else{
							$("#conB").show();
							$(".bd-tip").show();
							$(".tip-content").text(dataobj.msg).css("color","#666666");
							$(".bd-enter").css("background-color","#666666");
							$(".btn-dh").css({"background-color":"#ea7607","cursor":"default"}).text("绑定");
							tipclose(0);
							loadajax = 1;
						}
					},
					error: function(XMLHttpRequest, textStatus, errorThrown){
						$("#conB").show();
						$(".bd-tip").show();
						$(".tip-content").text("通讯发生错误，请重新尝试或刷新页面").css("color","#666666");
						$(".bd-enter").css("background-color","#666666");
						$(".btn-dh").css({"background-color":"#ea7607","cursor":"default"}).text("绑定");
						tipclose(0);
						loadajax = 1;
					}
				});
			}
        });
    });
</script>
</body>
</html>
