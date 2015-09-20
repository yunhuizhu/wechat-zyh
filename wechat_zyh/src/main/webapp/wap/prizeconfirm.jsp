<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
    <script>
        function changerCity(province,city){
            //alert(province);
            if(province != "" && city != ""){
                $("select[name='province'] option").each(function() {
                    if($(this).val() == province){
                        $(this).attr("selected","selected");
                        $("select[name='city']").append($("#citys option[title='"+$("select[name='province'] option:selected").val()+"']"));
                        $("select[name='city'] option").each(function() {
                            if($(this).val() == city){
                                $(this).attr("selected","selected");
                            }
                        });
                    }
                });
            }else{
                $("#citys").append($("select[name='city'] option"));
                $("select[name='city']").append($("#citys option[title='"+$("select[name='province'] option:selected").val()+"']"));
            }
        }
        $(function(){
            var province = '${customer.province}'; //默认省份
            var city = '${customer.city}';  //默认城市
            changerCity(province,city);
        });

    </script>
</head>

<body>
<div class="pro-title">
    收件信息
</div>
<div class="form-d">
<ul>
<li>
    <label>收货人：</label> <input type="text" value="${customer.lastName}${customer.firstName}" name="receiver" class="input-text" />
</li>
<li>
<label>省市：</label>
<select name="province" onchange="changerCity('','')" class="input-select" id="province">
    <option value="">请选择</option>
    <option value="101">安徽</option>
    <option value="102">重庆</option>
    <option value="114">北京</option>
    <option value="108">福建</option>
    <option value="112">甘肃</option>
    <option value="125">广东</option>
    <option value="131">广西</option>
    <option value="124">贵州</option>
    <option value="123">海南</option>
    <option value="117">河北</option>
    <option value="111">河南</option>
    <option value="115">黑龙江</option>
    <option value="103">湖北</option>
    <option value="126">湖南</option>
    <option value="104">吉林</option>
    <option value="121">江苏</option>
    <option value="119">江西</option>
    <option value="110">辽宁</option>
    <option value="105">内蒙古</option>
    <option value="106">宁夏</option>
    <option value="116">青海</option>
    <option value="128">山东</option>
    <option value="107">山西</option>
    <option value="129">陕西</option>
    <option value="127">上海</option>
    <option value="118">四川</option>
    <option value="120">天津</option>
    <option value="122">西藏</option>
    <option value="113">新疆</option>
    <option value="130">云南</option>
    <option value="109">浙江</option>
</select>
<select name="city" id="city" class="input-select" style="width:100px">
</select>

</li>
<li>
    <label>地址：</label> <input type="text" value="${customer.address}" name="address" class="input-text" />
</li>
<li>
    <label>电话：</label> <input type="text" value="${customer.phoneNum}" name="phone" class="input-text" />
</li>
<li>
    <label>邮编：</label> <input type="text" value="${customer.postCode}" name="zipCode" class="input-text" />
</li>
</ul>
</div>
<div class="pro-title">
    礼品信息
</div>
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
<div class="btn-area">
    <a class="btn-dh" href="javascript:void(0)">确认提交</a>
   <script>
		$(function(){
			var loadajax = 1; //防止用户由于网络延迟一直点击按钮，导致多次向服务器发送请求
			$(".btn-dh").bind("click",function(){
				var focusobj;
				var receiver = $("input[name='receiver']").val();
				var phone = $("input[name='phone']").val();
				var address = $("input[name='address']").val();
				var provinceId = $("select[name='province'] option:selected").val();
				var cityId = $("select[name='city'] option:selected").val();
				var zipCode = $("input[name='zipCode']").val();
				if(receiver == ""){
					$("#conB").show();
					$(".bd-tip").show();
					$(".tip-content").text("收件人不能为空！");
					closetip(focusobj = $("input[name='receiver']"));
					return;
				}
				if(provinceId == "" || cityId == ""){
					$("#conB").show();
					$(".bd-tip").show();
					$(".tip-content").text("请选择省份和城市！");
					closetip(focusobj = $("select[name='province']"));
					return;
				}
				if(address == ""){
					$("#conB").show();
					$(".bd-tip").show();
					$(".tip-content").text("地址不能为空！");
					closetip(focusobj = $("input[name='address']"));
					return;
				}
				if(phone == ""){
					$("#conB").show();
					$(".bd-tip").show();
					$(".tip-content").text("电话不能为空！");
					closetip(focusobj = $("input[name='phone']"));					
					return;
				}else if(isNaN(phone)){
						
					$("#conB").show();
					$(".bd-tip").show();
					$(".tip-content").text("联系电话格式不正确！");
					closetip(focusobj = $("input[name='phone']"));	
					$("input[name='phone']").select();			
					return;
				}
				if(loadajax){
					$(".btn-dh").css({"background-color":"#888888","cursor":"text","box-shadow":"none","-webkit-box-shadow":"none"}).text("处理中...");
					loadajax=0; //防止用户由于网络延迟一直点击按钮，导致多次向服务器发送请求
					$.ajax({
						url:"../prize/exchange-commit.do",
						data:"receiver="+receiver+"&phone="+phone+"&address="+address+"&provinceId="+provinceId+"&cityId="+cityId+"&post="+zipCode,
						type:"POST",
						success: function(data){
							var dataobj = eval('(' + data + ')'); 
							if(dataobj.success){
								$("#conB").show();
								$(".bd-tip").show();
								$(".tip-content").text("兑换成功！").css("color","#ea7607");
								$(".bd-enter").css("background-color","#ea7607");
								$(".btn-dh").css({"background-color":"#ea7607","cursor":"default"}).text("兑换成功");
								$(".bd-enter").click(function(){
									window.location.href=dataobj.redirectURL;
								});
								setTimeout(function(){window.location.href=dataobj.redirectURL;},2000);
							}else{
								$("#conB").show();
								$(".bd-tip").show();
								$(".tip-content").text(dataobj.msg);
								closetip();
								$(".btn-dh").css({"background-color":"#ea7607","cursor":"default"}).text("确认提交");
							}
							loadajax=1;
						},
						error: function(XMLHttpRequest, textStatus, errorThrown){
							/*alert(XMLHttpRequest.status + " ," + textStatus + " ," +errorThrown);*/;
							loadajax=1;
							$("#conB").show();
							$(".bd-tip").show();
							$(".tip-content").text("通讯发生错误，请重新尝试或刷新页面");
							closetip();
							$(".btn-dh").css({"background-color":"#ea7607","cursor":"default"}).text("确认提交");
						}
					});
				}
			});	
			function closetip(focusobj){
				$(".bd-enter").click(function(){
					$("#conB").hide();
					$(".bd-tip").hide();
					focusobj.focus();
				});
			}
		});
	</script>
</div>
<div id="conB"></div>
<div class="bd-tip" style="display:none; z-index:4;">
	<div class="tip-title">
    	提示信息
    </div>
    <div class="tip-content" style="color:#666666;">
    	
    </div>
    <a href="javascript:void(0)" style="background-color:#666666;" class="bd-enter">确定</a>
</div>
<select id="citys" style="display: none">
<option value="1073" title="101">安庆</option>
<option value="1085" title="101">蚌埠</option>
<option value="1105" title="101">巢湖</option>
<option value="1095" title="101">滁州</option>
<option value="1059" title="101">阜阳</option>
<option value="1058" title="101">毫州</option>
<option value="1096" title="101">合肥</option>
<option value="1044" title="101">淮北</option>
<option value="1077" title="101">淮南</option>
<option value="1049" title="101">黄山</option>
<option value="1034" title="101">宣城市</option>
<option value="1033" title="101">六安</option>
<option value="1075" title="101">马鞍山</option>
<option value="1097" title="101">池州市</option>
<option value="1042" title="101">铜陵</option>
<option value="1081" title="101">芜湖</option>
<option value="1064" title="101">宿州</option>
<option value="132" title="114">北京</option>
<option value="134" title="114">昌平</option>
<option value="135" title="114">大兴</option>
<option value="139" title="114">怀柔</option>
<option value="136" title="114">密云</option>
<option value="140" title="114">平台</option>
<option value="138" title="114">顺义</option>
<option value="133" title="114">通县</option>
<option value="137" title="114">延庆</option>
<option value="8132" title="114">东城区</option>
<option value="8133" title="114">西城区</option>
<option value="8134" title="114">崇文区</option>
<option value="8135" title="114">宣武区</option>
<option value="8136" title="114">朝阳区</option>
<option value="8137" title="114">丰台区</option>
<option value="8138" title="114">石景山区</option>
<option value="8139" title="114">海淀区</option>
<option value="8140" title="114">门头沟区</option>
<option value="8141" title="114">房山区</option>
<option value="1188" title="108">福州</option>
<option value="1228" title="108">龙岩</option>
<option value="1246" title="108">南平</option>
<option value="1199" title="108">宁德</option>
<option value="1208" title="108">莆田</option>
<option value="1210" title="108">泉州</option>
<option value="1235" title="108">三明</option>
<option value="1197" title="108">厦门</option>
<option value="1218" title="108">漳州</option>
<option value="2165" title="112">白银</option>
<option value="2129" title="112">定西</option>
<option value="2177" title="112">甘南州</option>
<option value="2156" title="112">嘉峪关</option>
<option value="2138" title="112">金昌</option>
<option value="2150" title="112">酒泉</option>
<option value="2124" title="112">兰州</option>
<option value="2123" title="112">临夏</option>
<option value="2139" title="112">武威</option>
<option value="2130" title="112">平凉</option>
<option value="2167" title="112">平凉</option>
<option value="2191" title="112">庆阳</option>
<option value="2158" title="112">天水</option>
<option value="2164" title="112">陇南</option>
<option value="2144" title="112">张掖</option>
<option value="1460" title="125">潮州</option>
<option value="1461" title="125">东莞</option>
<option value="1452" title="125">佛山</option>
<option value="1439" title="125">广州</option>
<option value="1456" title="125">河源</option>
<option value="1447" title="125">惠州</option>
<option value="1445" title="125">江门</option>
<option value="1443" title="125">揭阳</option>
<option value="1444" title="125">茂名</option>
<option value="1448" title="125">梅州</option>
<option value="1457" title="125">清远</option>
<option value="1449" title="125">汕头</option>
<option value="1440" title="125">汕尾</option>
<option value="1446" title="125">韶关</option>
<option value="1450" title="125">深圳</option>
<option value="1442" title="125">阳江</option>
<option value="1459" title="125">云浮</option>
<option value="1454" title="125">湛江</option>
<option value="1453" title="125">肇庆</option>
<option value="1455" title="125">中山</option>
<option value="1451" title="125">珠海</option>
<option value="1522" title="131">百色</option>
<option value="1548" title="131">北海</option>
<option value="1468" title="131">崇左</option>
<option value="1462" title="131">防城港</option>
<option value="1519" title="131">贵港</option>
<option value="1492" title="131">桂林</option>
<option value="1537" title="131">河池</option>
<option value="1489" title="131">来宾</option>
<option value="1479" title="131">柳州</option>
<option value="1464" title="131">南宁</option>
<option value="1534" title="131">钦州</option>
<option value="1505" title="131">梧州</option>
<option value="1514" title="131">玉林</option>
<option value="1509" title="131">贺州</option>
<option value="1776" title="123">安定</option>
<option value="1789" title="123">白沙</option>
<option value="1785" title="123">保亭</option>
<option value="1792" title="123">昌江</option>
<option value="1775" title="123">澄迈</option>
<option value="1788" title="123">儋州</option>
<option value="1791" title="123">东方</option>
<option value="1774" title="123">海口</option>
<option value="1784" title="123">乐东</option>
<option value="1793" title="123">临高</option>
<option value="1786" title="123">陵水</option>
<option value="1779" title="123">琼海</option>
<option value="1787" title="123">琼中</option>
<option value="1782" title="123">三亚</option>
<option value="1783" title="123">五指山</option>
<option value="1778" title="123">屯昌</option>
<option value="1780" title="123">万宁</option>
<option value="1777" title="123">文昌</option>
<option value="1790" title="123">洋浦</option>
<option value="206" title="117">保定</option>
<option value="272" title="117">沧州</option>
<option value="243" title="117">承德</option>
<option value="314" title="117">邯郸</option>
<option value="286" title="117">衡水</option>
<option value="263" title="117">廊坊</option>
<option value="329" title="117">秦皇岛</option>
<option value="189" title="117">石家庄</option>
<option value="252" title="117">唐山</option>
<option value="297" title="117">邢台</option>
<option value="229" title="117">张家口</option>
<option value="457" title="111">安阳</option>
<option value="523" title="111">鹤壁</option>
<option value="556" title="111">潢川</option>
<option value="522" title="111">济源</option>
<option value="515" title="111">焦作</option>
<option value="499" title="111">开封</option>
<option value="505" title="111">洛阳</option>
<option value="542" title="111">漯河</option>
<option value="547" title="111">泌阳</option>
<option value="475" title="111">平顶山</option>
<option value="553" title="111">平舆</option>
<option value="526" title="111">濮阳</option>
<option value="550" title="111">确山</option>
<option value="549" title="111">汝南</option>
<option value="563" title="111">三门峡</option>
<option value="440" title="111">商丘</option>
<option value="552" title="111">上蔡</option>
<option value="548" title="111">遂平</option>
<option value="486" title="111">南阳</option>
<option value="551" title="111">西平</option>
<option value="554" title="111">新蔡</option>
<option value="463" title="111">新乡</option>
<option value="483" title="111">信阳</option>
<option value="471" title="111">许昌</option>
<option value="555" title="111">正阳</option>
<option value="449" title="111">郑州</option>
<option value="532" title="111">周口</option>
<option value="546" title="111">驻马店</option>
<option value="753" title="115">安达</option>
<option value="751" title="115">大庆</option>
<option value="677" title="115">哈尔滨</option>
<option value="723" title="115">鹤岗</option>
<option value="737" title="115">黑河</option>
<option value="708" title="115">鸡西</option>
<option value="744" title="115">大兴安岭地区</option>
<option value="713" title="115">佳木斯</option>
<option value="700" title="115">牡丹江</option>
<option value="711" title="115">七台河</option>
<option value="690" title="115">齐齐哈尔</option>
<option value="725" title="115">双鸭山</option>
<option value="729" title="115">绥化</option>
<option value="748" title="115">伊春</option>
<option value="1270" title="103">鄂州</option>
<option value="1315" title="103">恩施</option>
<option value="1278" title="103">黄冈</option>
<option value="1288" title="103">黄石</option>
<option value="1333" title="103">荆门</option>
<option value="1297" title="103">荆州</option>
<option value="1336" title="103">潜江</option>
<option value="1327" title="103">神农架</option>
<option value="1323" title="103">十堰</option>
<option value="1332" title="103">随州</option>
<option value="1335" title="103">天门</option>
<option value="1256" title="103">武汉</option>
<option value="1290" title="103">咸宁</option>
<option value="1262" title="103">襄樊</option>
<option value="1271" title="103">孝感</option>
<option value="1305" title="103">宜昌</option>
<option value="8178" title="103">仙桃</option>
<option value="1378" title="126">常德</option>
<option value="1368" title="126">郴州</option>
<option value="1360" title="126">衡阳</option>
<option value="1417" title="126">怀化</option>
<option value="1406" title="126">湘西</option>
<option value="1391" title="126">娄底</option>
<option value="1396" title="126">邵阳</option>
<option value="1350" title="126">湘潭</option>
<option value="1351" title="126">湘潭县</option>
<option value="1386" title="126">益阳</option>
<option value="1429" title="126">永州</option>
<option value="1338" title="126">岳阳</option>
<option value="1414" title="126">张家界</option>
<option value="1345" title="126">长沙</option>
<option value="1354" title="126">株洲</option>
<option value="655" title="104">白城</option>
<option value="635" title="104">吉林</option>
<option value="660" title="104">辽源</option>
<option value="648" title="104">四平</option>
<option value="663" title="104">松原</option>
<option value="652" title="104">通化</option>
<option value="670" title="104">长白</option>
<option value="629" title="104">长春</option>
<option value="904" title="121">常州</option>
<option value="892" title="121">淮安</option>
<option value="894" title="121">淮安</option>
<option value="899" title="121">连云港</option>
<option value="846" title="121">南京</option>
<option value="864" title="121">南通</option>
<option value="861" title="121">苏州</option>
<option value="912" title="121">泰州</option>
<option value="852" title="121">无锡</option>
<option value="918" title="121">宿迁</option>
<option value="885" title="121">徐州</option>
<option value="877" title="121">盐城</option>
<option value="871" title="121">扬州</option>
<option value="856" title="121">镇江</option>
<option value="1581" title="119">抚州</option>
<option value="1616" title="119">赣州</option>
<option value="1603" title="119">吉安</option>
<option value="1634" title="119">景德镇</option>
<option value="1557" title="119">九江</option>
<option value="1552" title="119">南昌</option>
<option value="1637" title="119">萍乡</option>
<option value="1569" title="119">上饶</option>
<option value="1550" title="119">新余</option>
<option value="1593" title="119">宜春</option>
<option value="1639" title="119">鹰潭</option>
<option value="587" title="110">鞍山</option>
<option value="595" title="110">本溪</option>
<option value="616" title="110">朝阳</option>
<option value="580" title="110">大连</option>
<option value="598" title="110">丹东</option>
<option value="591" title="110">抚顺</option>
<option value="610" title="110">阜新</option>
<option value="625" title="110">葫芦岛</option>
<option value="602" title="110">锦州</option>
<option value="613" title="110">辽阳</option>
<option value="622" title="110">盘锦</option>
<option value="569" title="110">沈阳</option>
<option value="607" title="110">营口</option>
<option value="8177" title="110">铁岭</option>
<option value="843" title="105">阿拉善左旗</option>
<option value="777" title="105">包头</option>
<option value="802" title="105">赤峰</option>
<option value="812" title="105">鄂尔多斯市</option>
<option value="757" title="105">呼伦贝尔市</option>
<option value="770" title="105">呼和浩特</option>
<option value="783" title="105">乌兰察布市</option>
<option value="820" title="105">巴彦淖尔市</option>
<option value="794" title="105">通辽</option>
<option value="782" title="105">乌海</option>
<option value="839" title="105">兴安盟</option>
<option value="827" title="105">锡林郭勒盟</option>
<option value="2262" title="106">固原</option>
<option value="2251" title="106">石嘴山</option>
<option value="2255" title="106">吴忠</option>
<option value="2248" title="106">银川</option>
<option value="2257" title="106">中卫</option>
<option value="2220" title="116">海西</option>
<option value="2217" title="116">海南</option>
<option value="2206" title="116">海北</option>
<option value="2209" title="116">海东</option>
<option value="2243" title="116">冷湖</option>
<option value="2218" title="116">果洛</option>
<option value="2216" title="116">黄南</option>
<option value="2207" title="116">西宁</option>
<option value="2219" title="116">玉树</option>
<option value="1002" title="128">滨州</option>
<option value="946" title="128">德州</option>
<option value="1009" title="128">东营</option>
<option value="921" title="128">荷泽</option>
<option value="930" title="128">济南</option>
<option value="976" title="128">济宁</option>
<option value="1022" title="128">莱芜</option>
<option value="1023" title="128">聊城</option>
<option value="992" title="128">临沂</option>
<option value="936" title="128">青岛</option>
<option value="1019" title="128">日照</option>
<option value="987" title="128">泰安</option>
<option value="1013" title="128">威海</option>
<option value="967" title="128">潍坊</option>
<option value="957" title="128">烟台</option>
<option value="1017" title="128">枣庄</option>
<option value="942" title="128">淄博</option>
<option value="358" title="107">大同</option>
<option value="392" title="107">晋城</option>
<option value="414" title="107">吕梁</option>
<option value="397" title="107">临汾</option>
<option value="334" title="107">朔州</option>
<option value="353" title="107">太原</option>
<option value="339" title="107">忻州</option>
<option value="366" title="107">阳泉</option>
<option value="369" title="107">晋中</option>
<option value="427" title="107">运城</option>
<option value="380" title="107">长治</option>
<option value="2117" title="129">安康</option>
<option value="2102" title="129">宝鸡</option>
<option value="2109" title="129">汉中</option>
<option value="2068" title="129">商洛</option>
<option value="2091" title="129">铜川</option>
<option value="2033" title="129">渭南</option>
<option value="2067" title="129">西安</option>
<option value="2060" title="129">咸阳</option>
<option value="2047" title="129">延安</option>
<option value="2034" title="129">榆林</option>
<option value="149" title="127">崇明</option>
<option value="147" title="127">川沙</option>
<option value="146" title="127">奉贤</option>
<option value="143" title="127">嘉定</option>
<option value="150" title="127">金山</option>
<option value="145" title="127">南汇</option>
<option value="148" title="127">青浦</option>
<option value="141" title="127">上海</option>
<option value="144" title="127">松江</option>
<option value="8142" title="127">黄浦区</option>
<option value="8143" title="127">卢湾区</option>
<option value="8144" title="127">徐汇区</option>
<option value="8145" title="127">长宁区</option>
<option value="8146" title="127">静安区</option>
<option value="8147" title="127">普陀区</option>
<option value="8148" title="127">闸北区</option>
<option value="8149" title="127">虹口区</option>
<option value="8150" title="127">杨浦区</option>
<option value="8151" title="127">闵行区</option>
<option value="8152" title="127">宝山区</option>
<option value="8153" title="127">浦东新区</option>
<option value="1746" title="118">巴中</option>
<option value="1642" title="118">成都</option>
<option value="1673" title="118">达州</option>
<option value="1727" title="118">德阳</option>
<option value="1682" title="118">广安</option>
<option value="1732" title="118">广元</option>
<option value="1725" title="118">甘孜</option>
<option value="1708" title="118">乐山</option>
<option value="1687" title="118">泸州</option>
<option value="1726" title="118">阿坝</option>
<option value="1710" title="118">眉山</option>
<option value="1659" title="118">绵阳</option>
<option value="1666" title="118">南充</option>
<option value="1701" title="118">内江</option>
<option value="1655" title="118">攀枝花</option>
<option value="1679" title="118">遂宁</option>
<option value="1723" title="118">凉山</option>
<option value="1667" title="118">西充</option>
<option value="1724" title="118">雅安</option>
<option value="1692" title="118">宜宾</option>
<option value="1703" title="118">资阳</option>
<option value="1656" title="118">自贡</option>
<option value="153" title="120">宝坻</option>
<option value="152" title="120">蓟县</option>
<option value="155" title="120">静海</option>
<option value="156" title="120">宁河</option>
<option value="151" title="120">天津</option>
<option value="154" title="120">武清</option>
<option value="8154" title="120">和平区</option>
<option value="8155" title="120">河东区</option>
<option value="8156" title="120">河西区</option>
<option value="8157" title="120">南开区</option>
<option value="8158" title="120">河北区</option>
<option value="8159" title="120">红桥区</option>
<option value="8160" title="120">塘沽区</option>
<option value="8161" title="120">汉沽区</option>
<option value="8162" title="120">大港区</option>
<option value="8163" title="120">东丽区</option>
<option value="8164" title="120">西青区</option>
<option value="8165" title="120">津南区</option>
<option value="8166" title="120">北辰区</option>
<option value="2021" title="122">阿里</option>
<option value="2004" title="122">昌都</option>
<option value="2001" title="122">拉萨</option>
<option value="2003" title="122">林芝</option>
<option value="2018" title="122">那曲</option>
<option value="2002" title="122">日喀则</option>
<option value="2024" title="122">山南</option>
<option value="2322" title="113">阿克苏</option>
<option value="2329" title="113">阿拉尔</option>
<option value="2282" title="113">阿勒泰</option>
<option value="2289" title="113">克孜勒苏柯尔克孜</option>
<option value="2292" title="113">博尔塔拉</option>
<option value="2302" title="113">昌吉</option>
<option value="2333" title="113">咯什</option>
<option value="2272" title="113">哈密</option>
<option value="2275" title="113">和田</option>
<option value="2295" title="113">克拉玛依</option>
<option value="2313" title="113">巴音郭楞</option>
<option value="2298" title="113">伊犁</option>
<option value="2300" title="113">石河子</option>
<option value="2268" title="113">塔城</option>
<option value="2310" title="113">吐鲁番</option>
<option value="2297" title="113">乌鲁木齐</option>
<option value="8179" title="113">图木舒克市</option>
<option value="8180" title="113">五家渠市</option>
<option value="1929" title="130">保山</option>
<option value="1951" title="130">楚雄</option>
<option value="1895" title="130">大理</option>
<option value="1910" title="130">红河</option>
<option value="1992" title="130">西双版纳</option>
<option value="1886" title="130">昆明</option>
<option value="1988" title="130">丽江</option>
<option value="1972" title="130">临沧</option>
<option value="1980" title="130">怒江</option>
<option value="1995" title="130">德宏</option>
<option value="1962" title="130">普洱</option>
<option value="1920" title="130">曲靖</option>
<option value="1934" title="130">文山</option>
<option value="1942" title="130">玉溪</option>
<option value="1875" title="130">昭通</option>
<option value="1985" title="130">迪庆</option>
<option value="1115" title="109">杭州</option>
<option value="1123" title="109">湖州</option>
<option value="1127" title="109">嘉兴</option>
<option value="1175" title="109">金华</option>
<option value="1166" title="109">丽水</option>
<option value="1133" title="109">宁波</option>
<option value="1109" title="109">衢州</option>
<option value="1141" title="109">绍兴</option>
<option value="1147" title="109">台州</option>
<option value="1156" title="109">温州</option>
<option value="1184" title="109">舟山</option>
<option value="160" title="102">巴县</option>
<option value="159" title="102">北碚</option>
<option value="176" title="102">壁山</option>
<option value="167" title="102">城口</option>
<option value="174" title="102">大足</option>
<option value="187" title="102">垫江</option>
<option value="165" title="102">丰都</option>
<option value="169" title="102">奉节</option>
<option value="163" title="102">涪陵</option>
<option value="179" title="102">合川</option>
<option value="161" title="102">江北</option>
<option value="180" title="102">江津</option>
<option value="173" title="102">开县</option>
<option value="172" title="102">梁平</option>
<option value="166" title="102">南川</option>
<option value="182" title="102">南桐</option>
<option value="184" title="102">綦江</option>
<option value="185" title="102">黔江</option>
<option value="175" title="102">荣昌</option>
<option value="164" title="102">石柱</option>
<option value="181" title="102">双桥</option>
<option value="177" title="102">铜梁</option>
<option value="178" title="102">潼南</option>
<option value="157" title="102">万县</option>
<option value="188" title="102">巫山</option>
<option value="168" title="102">巫溪</option>
<option value="186" title="102">武隆</option>
<option value="158" title="102">永川</option>
<option value="170" title="102">云阳</option>
<option value="183" title="102">长寿</option>
<option value="171" title="102">忠县</option>
<option value="162" title="102">重庆</option>
<option value="8167" title="102">渝中区</option>
<option value="8168" title="102">大渡口区</option>
<option value="8169" title="102">沙坪坝区</option>
<option value="8170" title="102">九龙坡区</option>
<option value="8171" title="102">南岸区</option>
<option value="8172" title="102">万盛区</option>
<option value="8173" title="102">渝北区</option>
<option value="8174" title="102">秀山土家族苗族自治县</option>
<option value="8175" title="102">酉阳土家族苗族自治县</option>
<option value="8176" title="102">彭水苗族土家族自治县</option>
</select>
</body>
</html>

