<%--
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
    <script src="assets/js/jquery.min.js"></script>

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
            bottom: -6px; /*这个改变上下位置*/
            left: 920px; /*这个值改变左右位置*/
        }
    </style>

</head>

<body id="blog">
<hr>
<nav class="am-g am-g-fixed blog-fixed blog-nav">
    <button class="am-topbar-btn am-topbar-toggle am-btn am-btn-sm am-btn-success am-show-sm-only blog-button"
            data-am-collapse="{target: '#blog-collapse'}"><span class="am-sr-only">导航切换</span> <span
            class="am-icon-bars"></span></button>

    <script type="text/javascript">
        var dd = document.lastModified;
        // alert(dd);
        $.ajax({
            url: "updateLastTimeServlet",//后台文件上传接口
            type: 'post',
            data: {'lastTime': dd, 'user_id':${sessionScope.user_id}},
            success: function () {
                // location.reload();
            }
        });

    </script>
    <div class="am-collapse am-topbar-collapse" id="blog-collapse">
        <ul class="am-nav am-nav-pills am-topbar-nav">
            <li><a href="indexServlet">首页</a></li>
            <li><a href="foundServlet">发现</a></li>
            <li><a href="myConcernServlet?user_id="<%=session.getAttribute("user_id")%>>我的关注</a></li>
        </ul>
        <script type="text/javascript">
            $(document).ready(function () {
                    var number;
                    $.ajax({
                        url: "newMessageServlet?user_id=<%=session.getAttribute("user_id")%>",//后台文件上传接口
                        type: 'post',
                        success: function (data) {
                            if (data > 0) {
                                number = data;     // alert(data);
                                document.getElementById("number").innerHTML += "<span id=\"badge\">" + data + "</span>";
                            }
                        }
                    });
                }
            );
        </script>
        <a href="writeArticle.jsp" title="写文章"><img class="add" src="images/write84.png"
                                                    style="height:30px;width:30px"/></a>
        <a href="showMessageServlet?user_id=<%=session.getAttribute("user_id")%>" title="我的消息" id="number">
            <img id="messageNumber" class="add2" src="images/mes84.png"
                 style="height:30px;width:30px"/></a>

        <!-- 不能把span改成div，不然宽度会占据整行-->

        <div class="dropdown" style="width:170px">

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
<!-- content srart -->

<div class="bback" style="
    position: absolute;
    width: 100%;
    background: #F6F8F9;">
    <div class="am-g am-g-fixed blog-fixed blog-content">
        <div class="am-u-sm-12">

            <div class="timeline-year" id="context">
                <c:forEach items="${messagesList}" var="message">
                    <script type="text/javascript">
                        var month;
                        var m = "<br/><br><h1>${message.year}年${message.month}月</h1><hr>";
                        if (month != '${message.month}') {
                            document.getElementById("context").innerHTML += m;
                        }
                    </script>
                    <ul id="${message.aboutMeArticles.article_id}">
                        <script type="text/javascript">
                            var day;
                            var s = "<br><br/><h3 style='margin-top:0px;'>${message.day}日</h3><hr>";
                            if (day != '${message.day}') {
                                document.getElementById('${message.aboutMeArticles.article_id}').innerHTML += s;
                            }
                        </script>
                        <li style="line-height: 3;">
                            <span class="am-u-sm-4 am-u-md-2 timeline-span"
                                  style="max-width: 10em;overflow: hidden;white-space: nowrap;text-overflow: ellipsis;">${message.min}</span>
                            <span class=" am-u-sm-8 am-u-md-6">
                                <script type="text/javascript">
                                    function toClick(value, article_id) {
                                        value.style.color = 'black';

                                        window.open("detailArticleServlet?article_id=" + article_id + "&flag=1");
                                    }
                                </script>
                            <c:choose>
                                <c:when test="${not empty message.newMessage}">
                                    <a target="_blank"
                                       href="javascript:void(0)"
                                       style="color: red;"
                                       onclick="toClick(this,${message.aboutMeArticles.article_id})">
                                    ${message.aboutMeArticles.article_title}
                                    ("有新消息！")
                                </c:when>
                                <c:otherwise>
                                    <a target="_blank"
                                       href="javascript:void(0)"
                                       onclick="toClick(this,${message.aboutMeArticles.article_id})">
                                    ${message.aboutMeArticles.article_title}
                                </c:otherwise>
                            </c:choose>
                            </a>
                            </span>
                            <c:choose>
                                <c:when test="${not empty message.aboutMeArticles.article_type}">
                                    <span class="am-u-sm-4 am-u-md-2 am-hide-sm-only"
                                          style="max-width: 10em;overflow: hidden;white-space: nowrap;text-overflow: ellipsis; /*超出部分用...代替*/">${message.aboutMeArticles.article_type}</span>
                                </c:when>
                                <c:otherwise>
                                    <span class="am-u-sm-4 am-u-md-2 am-hide-sm-only">作者未添加分类</span>
                                </c:otherwise>
                            </c:choose>
                            <span class="am-u-sm-4 am-u-md-2 am-hide-sm-only">${message.aboutMeArticles.user_name}</span>
                            <script type="text/javascript">
                                <%--alert(${message.day}+"," + day);--%>
                                day = ${message.day};
                                // alert("day=" + day);
                                month = ${message.month};
                            </script>
                        </li>
                    </ul>
                    <br/>
                </c:forEach>
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
        top: 550px;
        right: 40px;
    }

    .fixed img:hover {
        transform: scale(1.3);
    }

</style>
<script src="assets/js/amazeui.min.js"></script>
<!-- <script src="assets/js/app.js"></script> -->
</body>
</html>
