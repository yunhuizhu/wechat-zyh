<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!doctype html>
<html>
<head>
<base href="<%=request.getContextPath() + "/wap/"%>">
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=no"/>  
<title>礼品详情</title>
<link rel="stylesheet" href="css/main.css" />
<script src="js/iscroll.js"></script>
<script src="js/jquery-1.7.2.min.js"></script>
</head>

<body>
<div id="pro-detail">
	<div class="pro-detail-img">
    	<img src="${prize.showImagePath}" />
    </div>
    <div class="pro-detail-name">
    	<span class="pro-name">${prize.prizeName}&nbsp;</span> 
        <span style="color:#ea7607">${prize.prizePoints}&nbsp;</span>  积分
    </div>
    <div class="clear"></div>
    
</div>
<div class="pro-title">
    礼品信息
</div>
<textarea class="pro-info" readonly>
${prize.prizeDes}&nbsp;
</textarea>
<div class="btn-area">
	<a class="btn-dh" href="../prize/exchange-confirm.do">兑换</a>
</div>
</body>
</html>
