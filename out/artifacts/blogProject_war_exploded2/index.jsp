<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!doctype html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="description" content="">
    <meta name="keywords" content="">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
    <title>BLOG | Amaze UI Examples</title>
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
    <script src="assets/js/jquery.min.js"></script>
    <link rel="stylesheet" href="assets/css/amazeui.min.css">
    <link rel="stylesheet" href="assets/css/app.css">
    <style type="text/css">
        #badge {
            padding: 0.1px 3px; /* 不需要定义height与width，添加合适的padding使圆圈随字符长短变化而改变 */
            line-height: 10px;
            text-align: center;
            background-color: red;
            color: white;
            font-size: 1px;
            font-weight: 700;
            border-radius: 50%;
            position: relative;
            bottom: -3px; /*这个改变上下位置*/
            left: 920px; /*这个值改变左右位置*/
        }
    </style>
</head>

<body id="blog">
<%--<script type="text/javascript">--%>
<%--    $(window).unload(function () {--%>
<%--        $.ajax({--%>
<%--            type: 'post',--%>
<%--            url: 'updateUnOnlineServlet?user_id=${user_id}'--%>
<%--        });--%>
<%--    });--%>
<%--</script>--%>


<hr>
<nav class="am-g am-g-fixed blog-fixed blog-nav">
    <button class="am-topbar-btn am-topbar-toggle am-btn am-btn-sm am-btn-success am-show-sm-only blog-button"
            data-am-collapse="{target: '#blog-collapse'}"><span class="am-sr-only">导航切换</span> <span
            class="am-icon-bars"></span></button>
    <script type="text/javascript">
        $(document).ready(function () {
                <%
                if(session.getAttribute("user_id")!=null){
                %>
                $.ajax({
                    url: "newMessageServlet?user_id=<%=session.getAttribute("user_id")%>",//后台文件上传接口
                    type: 'post',
                    success: function (data) {
                        if (data > 0) {
                            // alert(data);
                            document.getElementById("number").innerHTML += "<span id=\"badge\">" + data + "</span>";
                        }
                    }
                });
                setInterval(function () {
                    $.ajax({
                        url: "newMessageServlet?user_id=<%=session.getAttribute("user_id")%>",//后台文件上传接口
                        type: 'post',
                        success: function (data) {
                            if (data > 0) {
                                // alert(data);
                                $("#badge").html(data);
                            }
                        }
                    });
                }, 10000)
                <%
                }
                %>
            }
        );
    </script>
    <div class="am-collapse am-topbar-collapse" id="blog-collapse">
        <ul class="am-nav am-nav-pills am-topbar-nav">
            <li class="am-active"><a href="indexServlet">首页</a></li>
            <li><a href="foundServlet">发现</a></li>
            <%
                if (session.getAttribute("user_id") != null) {
            %>
            <li><a href="myConcernServlet?user_id=<%=session.getAttribute("user_id")%>">我的关注</a></li>
            <%
                }
            %>


        </ul>
        <script type="text/javascript">
            function toClick() {
                var userid = <%=session.getAttribute("user_id")%>;
                if (userid == null) {
                    alert("请先登录");
                } else {
                    window.open("writeArticle.jsp");
                }
            }
        </script>
        <a href="javascript:void(0)" title="写文章" onclick="toClick()"><img class="add" src="images/write84.png"
                                                                          style="height:30px;width:30px"/></a>
        <%
            if (session.getAttribute("user_id") != null) {
        %>
        <a href="showMessageServlet?user_id=<%=session.getAttribute("user_id")%>" title="我的消息" id="number">
            <img id="messageNumber" class="add2" src="images/mes84.png"
                 style="height:30px;width:30px"/></a>
        <%
            }
        %>


        <div class="dropdown" style="width:170px">
            <%
                if (session.getAttribute("user_id") != null) {
            %>
            <!-------------------1.已经登录成功----------------------- -->
            <span id="hello"><%=session.getAttribute("user_name")%></span>，你好！
            <div class="dropdown-content" style="z-index: 999;">
                <a href="personCenterServlet?user_id=<%=session.getAttribute("user_id")%>">
                    <p>个人中心</p>
                </a>
                <a href="outServlet">
                    <p>退出登录</p>
                </a>
            </div>
            <%
            } else {
            %>
            <!-------------------2.没有登录----------------------- -->
            <span id="hello"><a href="Login.jsp">登录</a></span>
            <%}%>
        </div>

        <form class="am-topbar-form am-topbar-right am-form-inline" role="search" style="margin-left: 300px;"
              action="./foundServlet" method="post">
            <div class="am-form-group" style="width: 280px;">
                <input type="text" name="keyword" id="keyword" class="am-form-field am-input-sm" placeholder="输入关键字"
                       style="width: 208px;" value="${keywords }">
                <input type="submit" value="搜索" id="serach-btn"
                       onclick="javascript:window.location.href='foundServlet?pageIndex='+1">
                <input type="hidden" type="submit" id="pageIndex" name="pageIndex" value="1"/>
            </div>
        </form>

    </div>
</nav>
<hr>

<!-- banner start -->
<div class="bback" style="
    position: absolute;
    width: 100%;
    background: #F6F8F9;"
>
    <div class="am-g am-g-fixed blog-fixed am-u-sm-centered blog-article-margin">
        <div data-am-widget="slider" class="am-slider am-slider-b1" data-am-slider='{&quot;controlNav&quot;:false}'>
            <ul class="am-slides">
                <c:forEach items="${slideArticelList }" var="article">
                    <li>
                        <img src="${article.article_cover}">
                        <div class="blog-slider-desc am-slider-desc ">
                            <div class="blog-text-center blog-slider-con">
                                <span><a href="personCenterServlet?user_id=${article.user_id}"
                                         class="blog-color">${article.user_name } &nbsp;</a></span>
                                <h1 class="blog-h-margin">
                                    <a target="_blank"
                                       href="detailArticleServlet?article_id=${article.article_id}">${article.article_title }</a>
                                </h1>
                                <p>${article.article_context }
                                </p>
                                <span class="blog-bor">
                                        ${fn:substring(article.article_date, 0, 10)}</span>
                                <br><br><br><br><br><br><br>
                            </div>
                        </div>
                    </li>
                </c:forEach>
            </ul>
        </div>
    </div>
    <!-- banner end -->

    <!-- content srart -->
    <div class="am-g am-g-fixed blog-fixed">
        <div class="am-u-md-12 am-u-sm-12">
            <c:forEach items="${moreArticelList }" var="article">
                <article class="am-g blog-entry-article">
                    <div class="am-u-lg-6 am-u-md-12 am-u-sm-12 blog-entry-img">
                        <img src="${article.article_cover}" alt="" class="am-u-sm-12">
                    </div>
                    <div class="am-u-lg-6 am-u-md-12 am-u-sm-12 blog-entry-text">
                        <span><a href="" class="blog-color">${article.article_type } &nbsp;</a></span>
                        <span> @
                            <a href="personCenterServlet?user_id=${article.user_id}">${article.user_name } &nbsp;</span>
                        <span>${fn:substring(article.article_date, 0, 10)}</span>
                        <h1>
                            <a target="_blank"
                               href="detailArticleServlet?article_id=${article.article_id}">${article.article_title }</a>
                        </h1>
                        <p>${article.article_context }
                        </p>
                        <p><a href="" class="blog-continue">continue reading</a></p>
                    </div>
                </article>
            </c:forEach>
        </div>

    </div>


    <footer class="blog-footer" id="footer">
        <div class="am-g am-g-fixed blog-fixed am-u-sm-centered blog-footer-padding">
            <div class="am-u-sm-12 am-u-md-4- am-u-lg-4">
                <h1>FUN-BLOG</h1>
                <p class="am-text-sm">这是一个有趣灵魂的聚集地。<br> 博客新生代 <br>
                    多种布局，包括首页、发现页、关注页、个人信息页、消息页等<br>让有趣的思想碰撞，和有趣的灵魂相遇！<br><br>
                    FUN-BLOG 用户可以自由发帖、浏览、关注感兴趣的人、修改信息和维护隐私。</p>
            </div>
            <div class="am-u-sm-12 am-u-md-4- am-u-lg-4">
                <h3>社交账号</h3>
                <p>
                    <a href=""><span class="am-icon-qq am-icon-fw am-primary blog-icon blog-icon"></span></a>
                    <a href=""><span class="am-icon-github am-icon-fw blog-icon blog-icon"></span></a>
                    <a href=""><span class="am-icon-weibo am-icon-fw blog-icon blog-icon"></span></a>
                    <a href=""><span class="am-icon-reddit am-icon-fw blog-icon blog-icon"></span></a>
                    <a href=""><span class="am-icon-weixin am-icon-fw blog-icon blog-icon"></span></a>
                </p>
                <h3>联系我们：www.xxxxxx@gmail.com</h3>
                <p>我们追求卓越，然时间、经验、能力有限。FUM-BLOG有很多不足的地方，希望大家包容、不吝赐教，给我们提意见、建议。感谢你们！</p>
            </div>
            <div class="am-u-sm-12 am-u-md-4- am-u-lg-4">
                <h1>让有趣的我们在这里相遇</h1>
                <h3>We are a team.</h3>
                <p>
                <ul>
                    <li>@俗气的LE</li>
                    <li>@賀JiAQi</li>
                    <li>@天文学的书</li>
                    <li>@人生赢家</li>
                    <li>...</li>
                </ul>
                </p>
            </div>
        </div>

        <a name="Bot1111"></a>
    </footer>


</div>
<!-- content end -->


<div class="fixed"><a href="#top" title="置顶"><img src="images/84-1.png" style="height:40px;width:40px"></a></div>
<div class="submeau" style="width: 35px; height: 60px; background-color: #e4e5e6;border-radius:10px ; z-index: 998;">
    <div style="font-size:12px;line-height:1.3;text-align:center"><br><b><a href="#footer">关于我们</a></b></div>
</div>


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

    .submeau:hover {
        transform: scale(1.1);

    }

</style>


<!--[if (gte IE 9)|!(IE)]><!-->

<script src="assets/js/amazeui.min.js"></script>
<!-- <script src="assets/js/app.js"></script> -->
</body>
</html>