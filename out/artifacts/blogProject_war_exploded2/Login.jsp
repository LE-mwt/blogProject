<%@ page import="cn.com.hunau.dao.DAOFactory" %>
<%@ page import="cn.com.hunau.dao.UserDAO" %>
<%@ page import="cn.com.hunau.po.UserPo" %>
<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<%
    boolean hasLogined = false;

    Cookie[] cookies = request.getCookies();
    if (cookies != null) {
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("user_id")) {
                String user_id = cookie.getValue();
                int int_userid = Integer.parseInt(user_id);
                request.getSession().setAttribute("user_id", int_userid);
                UserDAO userdao = DAOFactory.buildDAOFactory().createUserDAO();
                UserPo userPo = userdao.findUserioByUser_id(int_userid);
                request.getSession().setAttribute("user_name", userPo.getUser_name());
                if (user_id != null && !(user_id.equals("")) && !(user_id.equals("-1")))
                    hasLogined = true;
            }
        }
    }
    if (hasLogined) {
        request.getRequestDispatcher("/indexServlet").forward(request, response);
    } else {
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="description" content="">
    <meta name="keywords" content="">
    <meta name="viewport"
          content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
    <title>LOG-IN | Amaze UI Examples</title>

    <!-- Set render engine for 360 browser -->
    <meta name="renderer" content="webkit">

    <!-- No Baidu Siteapp-->
    <meta http-equiv="Cache-Control" content="no-siteapp"/>

    <!--   <link rel="icon" type="image/png" href="assets/i/favicon.png">

      Add to homescreen for Chrome on Android
      <meta name="mobile-web-app-capable" content="yes">
      <link rel="icon" sizes="192x192" href="assets/i/app-icon72x72@2x.png"> -->

    <!--   Add to homescreen for Safari on iOS
      <meta name="apple-mobile-web-app-capable" content="yes">
      <meta name="apple-mobile-web-app-status-bar-style" content="black">
      <meta name="apple-mobile-web-app-title" content="Amaze UI"/>
      <link rel="apple-touch-icon-precomposed" href="assets/i/app-icon72x72@2x.png">
     -->
    <!-- Tile icon for Win8 (144x144 + tile color) -->
    <!-- <meta name="msapplication-TileImage" content="assets/i/app-icon72x72@2x.png">
      <meta name="msapplication-TileColor" content="#0e90d2"> -->

    <!-- SEO: If your mobile URL is different from the desktop URL, add a canonical link to the desktop page https://developers.google.com/webmasters/smartphone-sites/feature-phones -->
    <!--
      <link rel="canonical" href="http://www.example.com/">
      -->
    <link rel="stylesheet" href="assets/css/amazeui.min.css">
    <link rel="stylesheet" href="assets/css/app.css">
</head>
<body>
<header>
    <div class="log-header">
        <h1>
            <a href="/">Amaze UI</a>
        </h1>
    </div>
    <div class="log-re">
        <button type="button" onclick="tz()"
                class="am-btn am-btn-default am-radius log-button">注册
        </button>
    </div>
</header>

<div class="log" style="background-image: url(img/picture7.jpg);">
    <div class="am-g">
        <div
                class="am-u-lg-3 am-u-md-6 am-u-sm-8 am-u-sm-centered log-content"
                style="margin-top: 150px;">
            <h1 class="log-title am-animation-slide-top">FUN-BLOG</h1>
            <br>
            <form method="post" class="am-form" id="log-form"
                  action="http://localhost:8080/blogProject/loginServlet">
                <div class="am-input-group am-radius am-animation-slide-left">
                    <input type="text" name="user_id" id="doc-vld-email-2-1"
                           class="am-radius" data-validation-message="至多填写11个字符"
                           value="${id}"
                           placeholder="账号" required/> <span
                        class="am-input-group-label log-icon am-radius"><i
                        class="am-icon-user am-icon-sm am-icon-fw"></i></span>
                </div>
                <br>
                <div
                        class="am-input-group am-animation-slide-left log-animation-delay">
                    <input type="password" name="user_password"
                           class="am-form-field am-radius log-input" placeholder="密码"
                           minlength="11" required> <span
                        class="am-input-group-label log-icon am-radius"><i
                        class="am-icon-lock am-icon-sm am-icon-fw"></i></span>
                </div>
                <br>
                <div
                        class="am-input-group am-animation-slide-left log-animation-delay"
                        style="width: 400px;">
                    <input type="text" name="yz" placeholder="验证码" required
                           style="width: 150px; margin: 0px; display: -webkit-inline-box; float: left;">
                    <div
                            style="width: 100px; height: 37px; float: left; background-color: none;">
                        <img onclick="tochange()" id="picture"
                             src="http://localhost:8080/blogProject/dynamicImageServlet"
                             style="width: 100px; height: 37px;margin-left:25px;"/>
                    </div>
                    <div>
                        <a
                                style="color: #ffffff; float: left; margin-top: 15px; text-size-adjust: 12px; font-size: 14px;margin-left:45px">点击图片切换</a>

                    </div>
                    <br>
                    <input type="checkbox" name="checkbox" value="check"
                           style="position: absolute;top: 44px;left: 10px;">
                    <span style="float:left;margin-left:30px;margin-top:5px">自动登录</span>
                    <br>
                    <br>
                    <button type="submit"
                            class="am-btn am-btn-primary am-btn-block am-btn-lg am-radius am-animation-slide-bottom log-animation-delay">
                        登
                        录
                    </button>
                    <!--  <p class="am-animation-slide-bottom log-animation-delay"><a href="#">忘记密码?</a></p> -->
            </form>
        </div>
    </div>

</div>

<script>
    function tz() {
        window.location.href = 'Regist.jsp';
    }


    function tochange() {
        var pic = document.getElementById("picture");
        var i = Math.random();//目的是使页面不一样
        pic.src = "http://localhost:8080/blogProject/dynamicImageServlet?id=" + i;
    }
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
<script src="assets/js/app.js"></script>
</body>
</html>
<%
    }
%>



