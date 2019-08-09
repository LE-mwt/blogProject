<%@ page import="cn.com.hunau.po.CommentPo" %>
<%@ page import="cn.com.hunau.po.UserPo" %>
<%@ page import="cn.com.hunau.vo.DetailArticleVo" %>
<%@ page import="java.util.List" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>文章详情页</title>
    <meta name="description" content="这是一个 index 页面">
    <meta name="keywords" content="index">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="renderer" content="webkit">
    <meta http-equiv="Cache-Control" content="no-siteapp"/>
    <link rel="icon" type="image/png" href="admin_assets/i/favicon.png">
    <link rel="apple-touch-icon-precomposed" href="admin_assets/i/app-icon72x72@2x.png">
    <meta name="apple-mobile-web-app-title" content="Amaze UI"/>
    <link rel="stylesheet" href="admin_assets/css/amazeui.min.css"/>
    <link rel="stylesheet" href="admin_assets/css/amazeui.datatables.min.css"/>
    <link rel="stylesheet" href="admin_assets/css/app.css">
    <script src="admin_assets/js/jquery.min.js"></script>
    <script type="text/javascript" src="admin_assets/js/select.js"></script>

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

</head>

<body data-type="widgets">
<script src="admin_assets/js/theme.js"></script>
<div class="am-g tpl-g">
    <!-- 头部 -->
    <header>
        <!-- logo -->
        <div class="am-fl tpl-header-logo">
            <!--<a href="javascript:;"><img src="assets/img/logo.png" alt=""></a>-->
        </div>
        <!-- 右侧内容 -->
        <div class="tpl-header-fluid">
            <!-- 侧边切换 -->
            <div class="am-fl tpl-header-switch-button am-icon-list">
                    <span>

                </span>
            </div>
            <!-- 搜索 -->
            <div class="am-fl tpl-header-search">
                <form class="tpl-header-search-form" action="javascript:;">
                    <button class="tpl-header-search-btn am-icon-search"></button>
                    <input class="tpl-header-search-box" type="text" placeholder="搜索内容...">
                </form>
            </div>
            <!-- 其它功能-->
            <div class="am-fr tpl-header-navbar">
                <ul>
                    <!-- 欢迎语 -->
                    <li class="am-text-sm tpl-header-navbar-welcome">
                        <a href="javascript:;">欢迎你, <span>Amaze UI</span> </a>
                    </li>


                    <!-- 退出 -->
                    <li class="am-text-sm">
                        <a href="outServlet">
                            <span class="am-icon-sign-out"></span> 退出
                        </a>
                    </li>
                </ul>
            </div>
        </div>

    </header>
    <!-- 风格切换 -->
    <div class="tpl-skiner">
        <div class="tpl-skiner-toggle am-icon-cog">
        </div>
        <div class="tpl-skiner-content">
            <div class="tpl-skiner-content-title">
                选择主题
            </div>
            <div class="tpl-skiner-content-bar">
                <span class="skiner-color skiner-white" data-color="theme-white"></span>
                <span class="skiner-color skiner-black" data-color="theme-black"></span>
            </div>
        </div>
    </div>
    <!-- 侧边导航栏 -->
    <div class="left-sidebar">
        <!-- 用户信息 -->
        <div class="tpl-sidebar-user-panel">
            <div class="tpl-user-panel-slide-toggleable">
                <div class="tpl-user-panel-profile-picture">
                    <img src="admin_assets/img/user04.png" alt="">
                </div>
                <span class="user-panel-logged-in-text">
              <i class="am-icon-circle-o am-text-success tpl-user-panel-status-icon"></i>
              禁言小张
          </span>
                <a href="javascript:;" class="tpl-user-panel-action-link"> <span class="am-icon-pencil"></span> 账号设置</a>
            </div>
        </div>


        <!-- 菜单 -->
        <ul class="sidebar-nav">

            <li class="sidebar-nav-link">
                <a href="admin_index.html">
                    <i class="am-icon-home sidebar-nav-link-logo"></i> 首页
                </a>
            </li>


            <li class="sidebar-nav-link">
                <a href="table-list.html">
                    <i class="am-icon-wpforms sidebar-nav-link-logo"></i> 用户管理

                </a>
            </li>

            <li class="sidebar-nav-link">
                <a href="table-list-img.html" class="active">
                    <i class="am-icon-wpforms sidebar-nav-link-logo"></i> 博文管理

                </a>
            </li>


        </ul>
    </div>


    <!-- 内容区域 -->
    <div class="tpl-content-wrapper">
        <div class="row-content am-cf">
            <div class="row">
                <div class="am-u-sm-12 am-u-md-12 am-u-lg-12">
                    <div class="widget am-cf">
                        <article class="am-article blog-article-p">
                            <div class="am-article-hd" style="text-align: center;">
                                <h1 class="am-article-title blog-text-center"><%=article.getArticle_title()%>
                                </h1>
                                <p class="am-article-meta blog-text-center">
                                    <span><a href="#"
                                             class="blog-color"><%=article.getArticle_type()%> &nbsp;</a></span>-
                                    <span><a href="#">@<%=user.getUser_name()%> &nbsp;</a></span>-
                                    <span><a
                                            href="#"><%=article.getArticle_date().toString().substring(0, 10)%></a></span>
                                    <img src="images/eyes.png" style="height:20px;width:20px;margin-left:20px;">
                                    <span>浏览量</span>
                                    <a href="adminDeleteArticleServlet?de_article_id=<%=article.getArticle_id()%>"
                                       id="delete" style="color:red;margin-left: 30px;font-size: 15px;">删除</a>
                                </p>
                            </div>
                            <div class="am-article-bd" style="color: black;">
                                <img src=<%=article.getArticle_cover()%> alt=""
                                     class="blog-entry-img blog-article-margin">
                                <%=article.getArticle_context()%>
                            </div>
                        </article>
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
                                            <c:if test="${comments.user.user_id==article.user.user_id}">
                                                (作者)
                                            </c:if>

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


                                            </div>
                                        </div>

                                        <div>
                                            <c:forEach items="${comments.comments}" var="comment">
                                                <div class="m-l-lg m-b-lg" style="margin-bottom:0px;">
                                                    <div class="m-b-xs">
                                                        <a href class="h4"
                                                           style="color:#148600">${comment.user.user_name}</a>
                                                        <c:if test="${comment.user.user_id==article.user.user_id}">
                                                            (作者)
                                                        </c:if>
                                                        <span
                                                                style="color: #000000;font-size: 18px;">回复</span>
                                                        <span class="text-muted m-l-sm pull-right">${comment.com_time}</span>
                                                    </div>
                                                    <div class="m-b">
                                                        <div class="m-b">${comment.com_text}
                                                        </div>
                                                    </div>

                                                </div>
                                            </c:forEach>
                                        </div>

                                    </div>
                                </div>
                            </div>
                        </c:forEach>
                    </div>

                </div>
            </div>
        </div>
    </div>
</div>

<script src="admin_assets/js/amazeui.min.js"></script>
<script src="admin_assets/js/app.js"></script>

</body>

</html>