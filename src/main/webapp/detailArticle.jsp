<%@ page import="cn.com.hunau.po.CommentPo" %>
<%@ page import="cn.com.hunau.po.UserPo" %>
<%@ page import="cn.com.hunau.vo.DetailArticleVo" %>
<%@ page import="java.util.List" %>
<%--
  Created by IntelliJ IDEA.
  User: 最帅的LE
  Date: 2019/08/04 0004
  Time: 18:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="description" content="">
    <meta name="keywords" content="">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
    <title>article no sidebar | Amaze UI Examples</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="Cache-Control" content="no-siteapp"/>
    <link rel="icon" type="image/png" href="assets/i/favicon.png">
    <meta name="mobile-web-app-capable" content="yes">
    <link rel="icon" sizes="192x192" href="assets/i/app-icon72x72@2x.png">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <meta name="apple-mobile-web-app-title" content="Amaze UI"/>
    <link rel="apple-touch-icon-precomposed" href="assets/i/app-icon72x72@2x.png">
    <meta name="msapplication-TileImage" content="assets/i/app-icon72x72@2x.png">
    <meta name="msapplication-TileColor" content="#0e90d2">

    <link rel="stylesheet" href="css/bootstrap.css" type="text/css"/>
    <link rel="stylesheet" href="css/animate.css" type="text/css"/>
    <link rel="stylesheet" href="css/font-awesome.min.css" type="text/css"/>
    <link rel="stylesheet" href="css/simple-line-icons.css" type="text/css"/>
    <link rel="stylesheet" href="css/font.css" type="text/css"/>
    <link rel="stylesheet" href="css/app.css" type="text/css"/>

    <link rel="stylesheet" href="assets/css/amazeui.min.css">
    <link rel="stylesheet" href="assets/css/app.css">
    <%
        DetailArticleVo article = (DetailArticleVo) request.getAttribute("article");
        UserPo user = article.getUser();
        List<CommentPo> comments = article.getComments();
    %>
    <script type="text/javascript">
        function showtextarea(data) {
            var something = document.getElementById(data).value;
            if (something) {
                alert(something);
                var article_id = <%=article.getArticle_id()%>;
                $.ajax({
                    url: "addCommentServlet",//后台文件上传接口
                    data: {"comment": something, "article_id": article_id, "parent_id": data},
                    type: 'get',
                    success: function () {
                        alert("评论成功");
                        document.getElementById(data).style.display = "none";
                        location.reload();
                    }, error: function () {
                        alert("评论失败");
                    }
                });
            } else {
                var eles = document.getElementsByName("commentByComment");
                for (var i = 0; i < eles.length; i++) {
                    eles[i].style.display = "none";
                    // alert(eles[i].style.display);
                }
                alert(data);
                document.getElementById(data).style.display = "block";
            }
            return false;
        }
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
            <li><a href="index.html">首页</a></li>
            <li><a href="found.html">发现</a></li>
            <li><a href="myconcern.html">我的关注</a></li>

        </ul>


        <a href="writeArticle.jsp" title="写文章"><img class="add" src="images/write84.png"
                                                    style="height:30px;width:30px"/></a>
        <a href="message.html" title="我的消息"><img class="add2" src="images/mes84.png "
                                                 style="height:30px;width:30px"/></a>

        <div class="dropdown" style="width:170px">

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
                <input type="text" class="am-form-field am-input-sm" placeholder="搜索" style="width: 208px;">
                <input type="button" value="搜索" id="serach-btn">
            </div>
        </form>
    </div>
</nav>
<hr>

<div class="bback" style="
    position: absolute;
    width: 100%;
    background: #F6F8F9;">
    <!-- content srart -->
    <div class="am-g am-g-fixed blog-fixed blog-content">
        <div class="am-u-sm-12">
            <article class="am-article blog-article-p">
                <div class="am-article-hd">
                    <h1 class="am-article-title blog-text-center"><%=article.getArticle_title()%>
                    </h1>
                    <p class="am-article-meta blog-text-center">
                        <span><a href="#" class="blog-color"><%=article.getArticle_type()%> &nbsp;</a></span>-
                        <span><a href="#">@<%=user.getUser_name()%> &nbsp;</a></span>-
                        <span><a href="#"><%=article.getArticle_date().toString().substring(0, 10)%></a></span>
                    </p>
                </div>
                <div class="am-article-bd">
                    <img src=<%=article.getArticle_cover()%> alt="" class="blog-entry-img blog-article-margin">
                    <%=article.getArticle_context()%>
                </div>
            </article>


            <hr>
            <div class="am-g blog-author blog-article-margin">
                <div class="am-u-sm-3 am-u-md-3 am-u-lg-2">
                    <img src=<%=user.getUser_picture()%> alt="" class="blog-author-img am-circle">
                </div>
                <div class="am-u-sm-9 am-u-md-9 am-u-lg-10">
                    <h3><span>作者 &nbsp;: &nbsp;</span><span class="blog-color"><%=user.getUser_name()%></span></h3>
                    <p><%=user.getUser_signature()%>
                    </p>
                </div>
            </div>
            <hr>

            <hr>


            <h3 class="blog-comment" style="margin-top:20px;margin-left:5px">评论</h3>
            <!-- 仅其他用户评论start -->
            <c:forEach items="${article.comments}" var="comments">
                <div class="streamline b-l b-info m-l-lg m-b padder-v">
                    <div>
                        <a class="pull-left thumb-sm avatar m-l-n-md">
                            <img src="${comments.user.user_picture}" class="img-circle" alt="...">
                        </a>
                        <div class="m-l-lg m-b-lg" style="margin-bottom:0px;">
                            <div class="m-b-xs">
                                <a href="javascript:void(0)" class="h4"
                                   style="color:#148600">${comments.user.user_name}</a>
                                <span class="text-muted m-l-sm pull-right">
                                        ${comments.com_time}
                                </span>
                            </div>
                            <div class="m-b">
                                <div class="m-b">${comments.com_text}
                                </div>
                                <div class="am-form am-g">
                                    <textarea id="${comments.com_id}" class="" rows="1" style="display:none"
                                              name="commentByComment"></textarea>
                                    <button type="button" onclick="showtextarea(this.value)"
                                            class="am-btn am-btn-default"
                                            style="padding-left: 8px;padding-top: 4px;padding-right: 8px;padding-bottom: 4px;margin-top: 8px;"
                                            value="${comments.com_id}">
                                        回复
                                    </button>
                                    <c:if test="${comment.user.user_id==1}">
                                        <button type="button" onclick="showtextarea()"
                                                class="am-btn am-btn-default"
                                                style="padding-left: 8px;padding-top: 4px;padding-right: 8px;padding-bottom: 4px;margin-top: 8px;">
                                            删除评论
                                        </button>
                                    </c:if>
                                </div>
                            </div>

                            <div>
                                <c:forEach items="${comments.comments}" var="comment">
                                    <div class="m-l-lg m-b-lg" style="margin-bottom:0px;">
                                        <div class="m-b-xs">
                                            <a href class="h4"
                                               style="color:#148600">${comment.user.user_name}</a><span
                                                style="color: #000000;font-size: 18px;">回复</span>
                                            <span class="text-muted m-l-sm pull-right">${comment.com_time}</span>
                                        </div>
                                        <div class="m-b">
                                            <div class="m-b">${comment.com_text}
                                            </div>
                                        </div>
                                        <c:if test="${comment.user.user_id==1}">
                                            <button type="button" onclick="showtextarea()"
                                                    class="am-btn am-btn-default"
                                                    style="padding-left: 8px;padding-top: 4px;padding-right: 8px;padding-bottom: 4px;margin-top: 8px;">
                                                删除评论
                                            </button>
                                        </c:if>
                                    </div>
                                </c:forEach>
                            </div>

                        </div>
                    </div>
                </div>
            </c:forEach>
            <%--            <fieldset>--%>

            <script type="text/javascript">
                function addComment() {
                    var comment = document.getElementById("comment_context").value;
                    // alert(comment);

                    var article_id = <%=article.getArticle_id()%>;
                    $.ajax({
                        url: "addCommentServlet",//后台文件上传接口
                        data: {"comment": comment, "article_id": article_id},
                        type: 'get',
                        success: function () {
                            alert("评论成功");
                            location.reload();
                        }, error: function () {
                            alert("评论失败");
                        }
                    });
                }
            </script>
            <div class="am-form am-g">
                <div class="am-form-group">
                    <textarea class="" rows="5" placeholder="我要评论" id="comment_context"></textarea>
                </div>

                <p>
                    <button type="" class="am-btn am-btn-default" onclick="addComment()" id="article_id"
                            value="<%=article.getArticle_id()%>">发表评论
                    </button>
                </p>
                <%--            </fieldset>--%>
            </div>

            <hr>
        </div>
    </div>
    <!-- content end -->
</div>
<div class="fixed"><a href="#top" title="置顶"><img src="images/84-1.png" style="height:40px;width:40px"></a></div>

<style type="text/css">

    .fixed {
        position: fixed;
        top: 450px;
        right: 40px;
    }

    .submeau {
        position: fixed;
        top: 500px;
        right: 40px;
    }

    .fixed img:hover {
        transform: scale(1.3);
    }

</style>
<script src="http://apps.bdimg.com/libs/jquery/2.1.4/jquery.min.js"></script>
<script type="text/javascript">
    $("#oa_submeau").hide();
    $(".submeau").hover(function () {
        $("#oa_submeau").show();
    }, function () {
        $("#oa_submeau").hide();
    })
    // 鼠标移动到list的div上的时候list div不会被隐藏
    $("#oa_submeau").hover(function () {
        $("#oa_submeau").show();
    }, function () {
        $("#oa_submeau").hide();
    })

</script>


<!--[if (gte IE 9)|!(IE)]><!-->
<script src="assets/js/jquery.min.js"></script>
<!--<![endif]-->
<!--[if lte IE 8 ]>
<script src="http://libs.baidu.com/jquery/1.11.3/jquery.min.js"></script>
<script src="http://cdn.staticfile.org/modernizr/2.8.3/modernizr.js"></script>
<script src="assets/js/amazeui.ie8polyfill.min.js"></script>
<![endif]-->
<script src="assets/js/amazeui.min.js"></script>
<!-- <script src="assets/js/app.js"></script> -->
</body>
</html>
