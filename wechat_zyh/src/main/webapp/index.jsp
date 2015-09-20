<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
<head>
 <base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="ckeditor4.4.0/ckeditor.js"></script>
    <script type="text/javascript" src="ckfinder2.5.0/ckfinder.js"></script>
<title>Insert title here</title>
</head>
<body>
index.jsp<br/>
<form id="detailForm" method="post">
    <textarea id="content" name="content"></textarea>
    <input type="button" value="保存" id="saveId" onclick="save()" />
</form>
</body>
<script type="text/javascript">

    var editor = null;
    window.onload = function(){
        editor = CKEDITOR.replace('content', {
            on: {
                instanceReady: function( ev ) {
                    this.dataProcessor.writer.setRules( 'p', {
                        indent: false,
                        breakBeforeOpen: false,   //<p>之前不加换行
                        breakAfterOpen: false,    //<p>之后不加换行
                        breakBeforeClose: false,  //</p>之前不加换行
                        breakAfterClose: false    //</p>之后不加换行7
                    });
                }
            }
        }); //参数‘content’是textarea元素的name属性值，而非id属性值
        <%--CKFinder.setupCKEditor( editor, '<%=basePath%>/ckfinder2.5.0/' );--%>
        CKFinder.setupCKEditor( editor,{ basePath : '<%=path%>/ckfinder2.5.0/'} );
    } ;
    function save(){
        editor.updateElement(); //非常重要的一句代码
        var gethtml=editor.getData();
        alert("gethtml:"+gethtml);
        //前台验证工作
        //提交到后台
    };
</script>
</html>

