<%@ page import="cn.com.hunau.vo.MessageVo" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: 最帅的LE
  Date: 2019/08/05 0005
  Time: 16:41
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
    <title>timeline in article | Amaze UI Examples</title>
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
    <link rel="stylesheet" href="assets/css/amazeui.min.css">
    <link rel="stylesheet" href="assets/css/app.css">


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

    <%
        List<MessageVo> messagesList = (List<MessageVo>) request.getAttribute("messagesList");
    %>

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
            <li><a href="foundServlet">发现</a></li>
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
                <input type="text" class="am-form-field am-input-sm" placeholder="输入关键字" style="width: 208px;">
                <input type="button" value="搜索" id="serach-btn">
            </div>
        </form>
    </div>
</nav>
<hr>
<!-- content srart -->

<div class="bback" style="
    position: absolute;
    width: 100%;
    background: #F6F8F9;"
>
    <div class="am-g am-g-fixed blog-fixed blog-content">
        <div class="am-u-sm-12">

            <div class="timeline-year">
                <h1>2019年8月</h1>
                <hr>
                <ul>
                    <%--                    <h3>3日</h3>--%>
                    <%--                    <hr>--%>

                    <c:forEach items="${messagesList}" var="message">
                        <li style="line-height: 3;">
                            <span class="am-u-sm-4 am-u-md-2 timeline-span"
                                  style="max-width: 10em;overflow: hidden;white-space: nowrap;text-overflow: ellipsis;">${message.day}日${message.min}</span>
                            <span class=" am-u-sm-8 am-u-md-6"><a
                                    href="#">${message.aboutMeArticles.article_title}</a></span>
                            <c:choose>
                                <c:when test="${!empty message.aboutMeArticles.article_type}">
                                    <span class="am-u-sm-4 am-u-md-2 am-hide-sm-only"
                                          style="max-width: 10em;overflow: hidden;white-space: nowrap;text-overflow: ellipsis; /*超出部分用...代替*/">${message.aboutMeArticles.article_type}</span>
                                </c:when>
                                <c:otherwise>
                                    <span class="am-u-sm-4 am-u-md-2 am-hide-sm-only">作者未添加分类</span>
                                </c:otherwise>
                            </c:choose>
                            <span class="am-u-sm-4 am-u-md-2 am-hide-sm-only">${message.aboutMeArticles.user_name}</span>
                                <%--                            <script type="text/javascript">--%>
                                <%--                                alert(${message.aboutMeArticles.user_name});--%>
                                <%--                            </script>--%>
                        </li>
                    </c:forEach>
                </ul>
            </div>
            <%--                <br>--%>
            <%--                <ul>--%>
            <%--                    <br>--%>
            <%--                    <h3>3月</h3>--%>
            <%--                    <hr>--%>
            <%--                    <li>--%>
            <%--                        <span class="am-u-sm-4 am-u-md-2 timeline-span">时间</span>--%>
            <%--                        <span class="am-u-sm-8 am-u-md-6"><a href="#">我的标题</a></span>--%>
            <%--                        <span class="am-u-sm-4 am-u-md-2 am-hide-sm-only">分类目录</span>--%>
            <%--                        <span class="am-u-sm-4 am-u-md-2 am-hide-sm-only">作者</span>--%>
            <%--                    </li>--%>
            <%--                    <li>--%>
            <%--                        <span class="am-u-sm-4 am-u-md-2 timeline-span">时间</span>--%>
            <%--                        <span class="am-u-sm-8 am-u-md-6"><a href="#">我的标题</a></span>--%>
            <%--                        <span class="am-u-sm-4 am-u-md-2 am-hide-sm-only">分类目录</span>--%>
            <%--                        <span class="am-u-sm-4 am-u-md-2 am-hide-sm-only">作者</span>--%>
            <%--                    </li>--%>
            <%--                    <li>--%>
            <%--                        <span class="am-u-sm-4 am-u-md-2 timeline-span">时间</span>--%>
            <%--                        <span class="am-u-sm-8 am-u-md-6"><a href="#">我的标题</a></span>--%>
            <%--                        <span class="am-u-sm-4 am-u-md-2 am-hide-sm-only">分类目录</span>--%>
            <%--                        <span class="am-u-sm-4 am-u-md-2 am-hide-sm-only">作者</span>--%>
            <%--                    </li>--%>
            <%--                </ul>--%>
            <%--                <br>--%>
            <%--                <ul>--%>
            <%--                    <br>--%>
            <%--                    <h3>8月</h3>--%>
            <%--                    <hr>--%>
            <%--                    <li>--%>
            <%--                        <span class="am-u-sm-4 am-u-md-2 timeline-span">时间</span>--%>
            <%--                        <span class="am-u-sm-8 am-u-md-6"><a href="#">我的标题</a></span>--%>
            <%--                        <span class="am-u-sm-4 am-u-md-2 am-hide-sm-only">分类目录</span>--%>
            <%--                        <span class="am-u-sm-4 am-u-md-2 am-hide-sm-only">作者</span>--%>
            <%--                    </li>--%>
            <%--                    <li>--%>
            <%--                        <span class="am-u-sm-4 am-u-md-2 timeline-span">时间</span>--%>
            <%--                        <span class="am-u-sm-8 am-u-md-6"><a href="#">我的标题</a></span>--%>
            <%--                        <span class="am-u-sm-4 am-u-md-2 am-hide-sm-only">分类目录</span>--%>
            <%--                        <span class="am-u-sm-4 am-u-md-2 am-hide-sm-only">作者</span>--%>
            <%--                    </li>--%>
            <%--                    <li>--%>
            <%--                        <span class="am-u-sm-4 am-u-md-2 timeline-span">时间</span>--%>
            <%--                        <span class="am-u-sm-8 am-u-md-6"><a href="#">我的标题</a></span>--%>
            <%--                        <span class="am-u-sm-4 am-u-md-2 am-hide-sm-only">分类目录</span>--%>
            <%--                        <span class="am-u-sm-4 am-u-md-2 am-hide-sm-only">作者</span>--%>
            <%--                    </li>--%>
            <%--                </ul>--%>
            <%--            </div>--%>
            <%--            <div class="timeline-year">--%>
            <%--                <h1>2014</h1>--%>
            <%--                <hr>--%>
            <%--                <ul>--%>
            <%--                    <h3>9月</h3>--%>
            <%--                    <hr>--%>
            <%--                    <li>--%>
            <%--                        <span class="am-u-sm-4 am-u-md-2 timeline-span">2015/10/18</span>--%>
            <%--                        <span class="am-u-sm-8 am-u-md-6"><a href="#">君埋泉下泥销骨，我寄人间雪满头</a></span>--%>
            <%--                        <span class="am-u-sm-4 am-u-md-2 am-hide-sm-only">风又起</span>--%>
            <%--                        <span class="am-u-sm-4 am-u-md-2 am-hide-sm-only">amazeui</span>--%>
            <%--                    </li>--%>
            <%--                    <li>--%>
            <%--                        <span class="am-u-sm-4 am-u-md-2 timeline-span">2015/10/10</span>--%>
            <%--                        <span class="am-u-sm-8 am-u-md-6"><a href="#">不为无益之事，何以遣有涯之生。</a></span>--%>
            <%--                        <span class="am-u-sm-4 am-u-md-2 am-hide-sm-only">灯火阑珊</span>--%>
            <%--                        <span class="am-u-sm-4 am-u-md-2 am-hide-sm-only">LWXYFER</span>--%>
            <%--                    </li>--%>
            <%--                    <li>--%>
            <%--                        <span class="am-u-sm-4 am-u-md-2 timeline-span">2015/10/5</span>--%>
            <%--                        <span class="am-u-sm-8 am-u-md-6"><a href="#">一想到你，我这张丑脸上就泛起微笑</a></span>--%>
            <%--                        <span class="am-u-sm-4 am-u-md-2 am-hide-sm-only">凌晨四点钟</span>--%>
            <%--                        <span class="am-u-sm-4 am-u-md-2 am-hide-sm-only">二师兄</span>--%>
            <%--                    </li>--%>
            <%--                </ul>--%>
            <%--                <br>--%>
            <%--                <ul>--%>
            <%--                    <br>--%>
            <%--                    <h3>8月</h3>--%>
            <%--                    <hr>--%>
            <%--                    <li>--%>
            <%--                        <span class="am-u-sm-4 am-u-md-2 timeline-span">时间</span>--%>
            <%--                        <span class="am-u-sm-8 am-u-md-6"><a href="#">我的标题</a></span>--%>
            <%--                        <span class="am-u-sm-4 am-u-md-2 am-hide-sm-only">分类目录</span>--%>
            <%--                        <span class="am-u-sm-4 am-u-md-2 am-hide-sm-only">作者</span>--%>
            <%--                    </li>--%>
            <%--                    <li>--%>
            <%--                        <span class="am-u-sm-4 am-u-md-2 timeline-span">时间</span>--%>
            <%--                        <span class="am-u-sm-8 am-u-md-6"><a href="#">我的标题</a></span>--%>
            <%--                        <span class="am-u-sm-4 am-u-md-2 am-hide-sm-only">分类目录</span>--%>
            <%--                        <span class="am-u-sm-4 am-u-md-2 am-hide-sm-only">作者</span>--%>
            <%--                    </li>--%>
            <%--                    <li>--%>
            <%--                        <span class="am-u-sm-4 am-u-md-2 timeline-span">时间</span>--%>
            <%--                        <span class="am-u-sm-8 am-u-md-6"><a href="#">我的标题</a></span>--%>
            <%--                        <span class="am-u-sm-4 am-u-md-2 am-hide-sm-only">分类目录</span>--%>
            <%--                        <span class="am-u-sm-4 am-u-md-2 am-hide-sm-only">作者</span>--%>
            <%--                    </li>--%>
            <%--                </ul>--%>
            <%--                <br>--%>
            <%--                <ul>--%>
            <%--                    <br>--%>
            <%--                    <h3>7月</h3>--%>
            <%--                    <hr>--%>
            <%--                    <li>--%>
            <%--                        <span class="am-u-sm-4 am-u-md-2 timeline-span">时间</span>--%>
            <%--                        <span class="am-u-sm-8 am-u-md-6"><a href="#">我的标题</a></span>--%>
            <%--                        <span class="am-u-sm-4 am-u-md-2 am-hide-sm-only">分类目录</span>--%>
            <%--                        <span class="am-u-sm-4 am-u-md-2 am-hide-sm-only">作者</span>--%>
            <%--                    </li>--%>
            <%--                    <li>--%>
            <%--                        <span class="am-u-sm-4 am-u-md-2 timeline-span">时间</span>--%>
            <%--                        <span class="am-u-sm-8 am-u-md-6"><a href="#">我的标题</a></span>--%>
            <%--                        <span class="am-u-sm-4 am-u-md-2 am-hide-sm-only">分类目录</span>--%>
            <%--                        <span class="am-u-sm-4 am-u-md-2 am-hide-sm-only">作者</span>--%>
            <%--                    </li>--%>
            <%--                    <li>--%>
            <%--                        <span class="am-u-sm-4 am-u-md-2 timeline-span">时间</span>--%>
            <%--                        <span class="am-u-sm-8 am-u-md-6"><a href="#">我的标题</a></span>--%>
            <%--                        <span class="am-u-sm-4 am-u-md-2 am-hide-sm-only">分类目录</span>--%>
            <%--                        <span class="am-u-sm-4 am-u-md-2 am-hide-sm-only">作者</span>--%>
            <%--                    </li>--%>
            <%--                </ul>--%>
            <%--            </div>--%>


            <hr>
        </div>


    </div>
    <!-- content end -->


</div>

<div class="fixed"><a href="#top" title="置顶"><img src="images/84-1.png" style="height:40px;width:40px"></a></div>
<style type="text/css">

    .fixed {
        position: fixed;
        top: 550px;
        right: 40px;
    }

    .fixed img:hover {
        transform: scale(1.3);
    }

</style>


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
