<%--
  Created by IntelliJ IDEA.
  User: 最帅的LE
  Date: 2019/08/04 0004
  Time: 17:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <title>写文章</title>
    <link rel="stylesheet" href="assets/css/amazeui.min.css">
    <link rel="stylesheet" href="assets/css/app.css">
    <script src="assets/js/echarts.min.js"></script>
    <link rel="stylesheet" href="assets/css/form.min.css"/>
    <link rel="stylesheet" href="assets/css/amazeui.datatables.min.css"/>
    <link rel="stylesheet" href="assets/css/app_form.css">
    <link rel="icon" type="image/png" href="assets/i/favicon.png">
    <link rel="icon" sizes="192x192" href="assets/i/app-icon72x72@2x.png">
    <link rel="apple-touch-icon-precomposed" href="assets/i/app-icon72x72@2x.png">
    <link rel="stylesheet" href="assets/css/amazeui.min.css">
    <link rel="stylesheet" href="assets/css/app.css">
    <!-- include libraries(jQuery, bootstrap) -->
    <link href="bootstrap-3.3.7-dist/css/bootstrap.min.css" rel="stylesheet">
    <script type="text/javascript" src="jquery/jQuery.3.4.1.min.js"></script>
    <script src="bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
    <!-- include summernote css/js -->
    <link href="summernote/dist/summernote.css" rel="stylesheet">
    <script src="summernote/dist/summernote.js" type="text/javascript"></script>

    <script type="text/javascript">
        function toSubmit() {
            $("input[name='files']").remove();
            $("#testForm").submit();
        }

        // var markupStr=$("#content").html(); $('#summernote').summernote( { height: 300 }); //alert(markupStr); $("#summernote").summernote('code', markupStr);
        $(document).ready(function () {
            // $('#summernote').summernote();


            var obj = $('#summernote').summernote({
                height: 300,                 // set editor height
                minHeight: null,             // set minimum height of editor
                maxHeight: null,             // set maximum height of editor
                focus: true,               // set focus to editable area after initializing summernote
                callbacks: {
                    onImageUpload: function (files) {
                        //上传图片到服务器
                        var formData = new FormData();
                        formData.append("file", files[0]);
                        $.ajax({
                            url: "uploadImgServlet",//后台文件上传接口
                            data: formData,
                            type: 'POST',
                            processData: false,
                            contentType: false,
                            success: function (path) {//后台返回一个该图片的path
                                var imgNode = document.createElement("img");
                                imgNode.src = path;
                                obj.summernote('insertNode', imgNode);
                            }, error: function () {
                                alert("上传失败");
                            }
                        });
                    }
                }
            });
        });

        //html5实现图片预览功能
        $(function () {
            $("#file").change(function (e) {
                var file = e.target.files[0] || e.dataTransfer.files[0];
                $('#photoCover').val(document.getElementById("file").files[0].name);
                if (file) {
                    var reader = new FileReader();
                    reader.onload = function () {
                        $("#img").attr("src", this.result);
                    }
                    reader.readAsDataURL(file);
                    // alert(document.getElementById("imgarea").innerHTML);
                }
            });
        })
    </script>

</head>

<body id="blog">

<hr>
<nav class="am-g am-g-fixed blog-fixed blog-nav">
    <button class="am-topbar-btn am-topbar-toggle am-btn am-btn-sm am-btn-success am-show-sm-only blog-button"
            data-am-collapse="{target: '#blog-collapse'}"><span class="am-sr-only">导航切换</span> <span
            class="am-icon-bars"></span></button>

    <div class="am-collapse am-topbar-collapse" id="blog-collapse">
        <ul class="am-nav am-nav-pills am-topbar-nav">
            <li><a href="index.html" style="font-size: 16px">首页</a></li>
            <li><a href="foundServlet" style="font-size: 16px">发现</a></li>
            <li><a href="myconcern.html" style="font-size: 16px">我的关注</a></li>

        </ul>


        <a href="writeArticle.jsp" title="写文章"><img class="add" src="images/write84.png"
                                                    style="height:30px;width:30px"/></a>
        <a href="message.html" title="我的消息"><img class="add2" src="images/mes84.png "
                                                 style="height:30px;width:30px"/></a>

        <div class="dropdown" style="width:170px;margin-top:15px;font-size:16px">

            <!-------------------1.已经登录成功----------------------- -->
            <span id="hello">美少女</span>，你好！
            <div class="dropdown-content" style="z-index: 999;">
                <a href="personcenter.html">
                    <p>个人中心</p>
                </a>
                <a href="login.html">
                    <p>退出登录</p>
                </a>
            </div>


            <!-------------------2.没有登录----------------------- -->
            <!--
              <span id="hello"><a href="login.html">登录</a></span>
               -->
        </div>

        <form class="am-topbar-form am-topbar-right am-form-inline" role="search" style="margin-left: 300px;">
            <div class="am-form-group" style="width: 280px;">
                <input type="text" class="am-form-field am-input-sm" placeholder="输入关键字" style="width: 208px;">
                <input type="button" value="搜索" id="serach-btn">
            </div>
        </form>
    </div>
</nav>
<hr>
<!-- content srart -->
<div class="bback" style="position: absolute;width: 100%;background: url(img/picture1.jpg);"
>
    <div id="edit1" class="am-g am-g-fixed blog-fixed blog-content"
         style="background-color:#f9f9f9a6;background-size:cover;width:1000px;margin-top: 40px;width:1000px;">
        <script src="assets/js/theme.js"></script>
        <form onsubmit="toSubmit()" id="testForm" class="am-form tpl-form-line-form" method="post"
              enctype="multipart/form-data" action="uploadServlet?math=<%=Math.random()%>">
            <div class="am-form-group">
                <label class="am-u-sm-3 am-form-label">标题 <span
                        class="tpl-form-line-small-title">Titel</span></label>
                <div class="am-u-sm-9">
                    <input type="text" class="tpl-form-input" id="title" name="article_title" placeholder="请输入标题文字">
                    <small>请填写标题文字5-20字左右。</small>
                </div>
            </div>
            <div class="am-form-group">
                <label for="user-weibo" class="am-u-sm-3 am-form-label">封面图 <span
                        class="tpl-form-line-small-title">Images</span></label>
                <div class="am-u-sm-9">
                    <div class="control-group" style="position: unset">
                        <div class="col-md-9" id="imgarea">
                            <img id="img" src="" width="100px" height="100px">
                        </div>
                    </div>
                    <div class="control-group" style="position: unset">
                        <div class="col-md-9">
                            <div class="input-group">
                                <input id="photoCover" class="form-control" readonly type="text"
                                       style="background-color:#f9f9f9a6">
                                <label class="input-group-btn">
                                    <input id="file" type="file" name="article_cover"
                                           style="position: absolute;">
                                    <span class="btn btn-default">Browse</span>
                                </label>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

            <div class="am-form-group">
                <label for="user-weibo" class="am-u-sm-3 am-form-label">添加分类标签 <span
                        class="tpl-form-line-small-title">Type</span></label>
                <div class="am-u-sm-9">
                    <input type="text" id="user-weibo" placeholder="请添加分类用点号隔开" name="article_type">
                    <div>

                    </div>
                </div>
            </div>
            <div class="am-form-group">
                <label class="am-u-sm-3 am-form-label">设为仅自己可见</label>
                <div class="am-u-sm-9">
                    <div class="tpl-switch">
                        <input type="checkbox" class="ios-switch bigswitch tpl-switch-btn" name="article_private">
                        <div class="tpl-switch-btn-view">
                            <div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

            <div class="am-form-group">
                <label class="am-u-sm-3 am-form-label">文章内容</label>
                <div class="am-u-sm-9">
                    <textarea id="summernote" name="article_context"></textarea>
                </div>
            </div>

            <div class="am-form-group">
                <div class="am-u-sm-9 am-u-sm-push-3">
                    <input type="submit" value="发布"
                           class="am-btn am-btn-primary tpl-btn-bg-color-success"
                           id="submit" style="border-radius:20px">
                </div>
            </div>
        </form>
    </div>
</div>
<!-- content end -->


</body>
</html>
