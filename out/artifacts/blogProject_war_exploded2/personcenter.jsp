<%@ page import="cn.com.hunau.po.UserPo" %>
<%@ page import="cn.com.hunau.vo.UserVo" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: 最帅的LE
  Date: 2019/08/06 0006
  Time: 15:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
    <meta charset="utf-8"/>
    <title>博客</title>
    <meta name="description"
          content="app, web app, responsive, responsive layout, admin, admin panel, admin dashboard, flat, flat ui, ui kit, AngularJS, ui route, charts, widgets, components"/>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1"/>
    <link rel="stylesheet" href="css/bootstrap.css" type="text/css"/>
    <link rel="stylesheet" href="css/animate.css" type="text/css"/>
    <link rel="stylesheet" href="css/font-awesome.min.css" type="text/css"/>
    <link rel="stylesheet" href="css/simple-line-icons.css" type="text/css"/>
    <link rel="stylesheet" href="css/font.css" type="text/css"/>
    <link rel="stylesheet" href="css/app.css" type="text/css"/>
    <link href="bootstrap-3.3.7-dist/css/bootstrap.min.css" rel="stylesheet">
    <script type="text/javascript" src="jquery/jQuery.3.4.1.min.js"></script>
    <script src="bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="assets/css/amazeui.min.css">
    <link rel="stylesheet" href="assets/css/app.css">
    <link rel="shortcut icon" href="favicon.ico">
    <link href="important/css/bootstrap.min14ed.css?v=3.3.6" rel="stylesheet">
    <link href="important/css/font-awesome.min93e3.css?v=4.4.0" rel="stylesheet">
    <link href="important/css/plugins/iCheck/custom.css" rel="stylesheet">
    <link href="important/css/animate.min.css" rel="stylesheet">
    <link href="important/css/style.min862f.css?v=4.1.0" rel="stylesheet">
</head>
<style type="text/css">
    .ww {
        position: absolute;
        top: 10px;
        left: 0%;
        background-color: #00ffff00;
        width: 201px;
        height: -webkit-fill-available;
    }

    td.td1 {
        font-size: 23px;
    }

    td.td2 {
        text-align: -webkit-center;
    }

    td {
        width: 140px;
        line-height: 25px;
        background: #eee0;
    }

    input {

        line-height: 23px;
    }

    .bianji {
        width: 700px;
        height: 150px;
        background-color: #ff000000;
        margin: 0px;
    }

    table {
        width: 500px;
    }

    a#edit {
        color: #10D07A;
        font-size: 16px;
    }

</style>

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
        bottom: -5px; /*这个改变上下位置*/
        left: 920px; /*这个值改变左右位置*/
    }
</style>
<script type="text/javascript">
    function changeContent() {
        var o = document.getElementById("mas-name");
        var c = o.innerHTML;
        o.innerHTML = "<input type='text' value='" + c + "'/>"
    }
</script>
<body id="blog">
<!--  <div class="app app-header-fixed" id="app">-->
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
        <ul class="am-nav am-nav-pills am-topbar-nav" style="font-size: 16px;">
            <li><a href="indexServlet">首页</a></li>
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
            function write() {
                var userid = <%=session.getAttribute("user_id")%>;
                alert(userid);
                if (userid == null) {
                    alert("请先登录");
                }
            }
        </script>
        <a href="javascript:void(0)" title="写文章" onclick="write()"><img class="add" src="images/write84.png"
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


        <div class="dropdown" style="width:170px;font-size: 16px;line-height: px;bottom: -5px;">
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


<!-- menu -->
<%
    UserVo userVo = (UserVo) request.getAttribute("userVo");
    List<UserPo> myConcern = userVo.getMyConcern();
    List<UserPo> myFans = userVo.getMyFans();
%>

<!-- content -->
<div class="bback" style="
    position: absolute;
    width: 100%;
    background: #F6F8F9;">
    <div class="app-content">
        <div class="ww">
            <div>
                <select onchange="change(this.value)" id="cp"
                        style="float: inherit;font-size: 17px;margin-left: 30px;margin: 20px;background-color: #F6F8F9;color: cadetblue;border: none;">
                    <option value="myConcern">关注</option>
                    <option value="myFans">粉丝</option>
                </select>
            </div>
            <ul class="list-group no-bg no-borders pull-in" id="list">
                <script type="text/javascript">
                    $(document).ready(function () {
                        var op = $("#cp").val();
                        // alert(op);
                        var m = "";
                        <%
                        for (UserPo fans:myConcern ) {
                        %>
                        m += "<li class=\"list-group-item\">\n" +
                            "                    <a href=\"personCenterServlet?user_id=<%=fans.getUser_id()%>\" class=\"pull-left thumb-sm avatar m-r\">\n" +
                            "                        <img src=<%=fans.getUser_picture()%> alt=\"...\" class=\"img-circle\">\n" +
                            "                        <i class=\"on b-white bottom\"></i>\n" +
                            "                    </a>\n" +
                            "                    <div class=\"clear\">\n" +
                            "                        <div><a href=\"personCenterServlet?user_id=<%=fans.getUser_id()%>\"><%=fans.getUser_name()%></a></div>\n" +
                            "                        <small class=\"text-muted\"><%=fans.getUser_sex()%>, <%=fans.getUser_address()%></small>\n" +
                            "                    </div>\n" +
                            "                </li>";
                        <%
                        }
                        %>
                        document.getElementById("list").innerHTML += m;
                    });

                    function change(data) {
                        // alert(data);
                        if (data == "myFans") {
                            document.getElementById("list").innerHTML = "";
                            var m = "";
                            <%
                            for (UserPo fans:myFans ) {
                            %>
                            m += "<li class=\"list-group-item\">\n" +
                                "                    <a href=\"personCenterServlet?user_id=<%=fans.getUser_id()%>\" class=\"pull-left thumb-sm avatar m-r\">\n" +
                                "                        <img src=<%=fans.getUser_picture()%> alt=\"...\" class=\"img-circle\">\n" +
                                "                        <i class=\"on b-white bottom\"></i>\n" +
                                "                    </a>\n" +
                                "                    <div class=\"clear\">\n" +
                                "                        <div><a href=\"personCenterServlet?user_id=<%=fans.getUser_id()%>\"><%=fans.getUser_name()%></a></div>\n" +
                                "                        <small class=\"text-muted\"><%=fans.getUser_sex()%>, <%=fans.getUser_address()%></small>\n" +
                                "                    </div>\n" +
                                "                </li>";
                            <%
                            }
                            %>
                            document.getElementById("list").innerHTML += m;
                        } else {
                            document.getElementById("list").innerHTML = "";
                            var m = "";
                            <%
                            for (UserPo fans:myConcern ) {
                            %>
                            m += "<li class=\"list-group-item\">\n" +
                                "                    <a href=\"personCenterServlet?user_id=<%=fans.getUser_id()%>\" class=\"pull-left thumb-sm avatar m-r\">\n" +
                                "                        <img src=<%=fans.getUser_picture()%> alt=\"...\" class=\"img-circle\">\n" +
                                "                        <i class=\"on b-white bottom\"></i>\n" +
                                "                    </a>\n" +
                                "                    <div class=\"clear\">\n" +
                                "                        <div><a href=\"personCenterServlet?user_id=<%=fans.getUser_id()%>\"><%=fans.getUser_name()%></a></div>\n" +
                                "                        <small class=\"text-muted\"><%=fans.getUser_sex()%>, <%=fans.getUser_address()%></small>\n" +
                                "                    </div>\n" +
                                "                </li>";
                            <%
                            }
                            %>
                            document.getElementById("list").innerHTML += m;
                        }
                    }
                </script>
            </ul>
        </div>

        <div ui-butterbar></div>
        <a href class="off-screen-toggle hide" data-toggle="class:off-screen" data-target=".app-aside"></a>
        <script type="text/javascript">
            $(function () {
                var pic_path;
                //获取点击的图片元素
                var cdimg = $('#picture');
                //获取上传图片的 input 标签元素
                var cdfile = $('#pic');
                //设置input 大小和图片一致
                cdfile.css({'width': cdimg.css('width'), 'height': cdimg.css('height')});
                //input透明度为0,定位，优先级比图片高
                cdfile.css({'opacity': 0, 'position': 'absolute', 'z-index': 10});
                //判断input的图片文件改变则img的图片也替换为input选择的图片
                cdfile.change(function () {
                    if (this.files && this.files[0]) {
                        // var formdata = this.files[0];
                        var formData = new FormData();
                        formData.append("file", this.files[0]);
                        $.ajax({
                            url: "uploadImgServlet",//后台文件上传接口
                            data: formData,
                            type: 'POST',
                            processData: false,
                            contentType: false,
                            success: function (path) {//后台返回一个该图片的path
                                pic_path = path;
                                // alert(pic_path);
                                $.ajax({
                                    url: "updatePersonServlet",//后台文件上传接口
                                    data: {"picture_path": pic_path, "user_id": ${sessionScope.user_id}},
                                    type: 'get',
                                    success: function () {//后台返回一个该图片的path
                                        alert("头像更换成功！");
                                        location.reload();
                                        // $('#picture').attr("src", pic_path);
                                    }, error: function () {
                                        alert("头像更换失败！!!!");
                                    }
                                });
                            }, error: function () {
                                alert("头像更换失败！");
                            }
                        });
                    }
                });
            });
        </script>
        <div class="app-content-body fade-in-up">
            <!-- COPY the content from "tpl/" -->
            <div class="hbox hbox-auto-xs hbox-auto-sm">
                <div class="col">
                    <div style="background:url(img/picture5.jpg) center center; background-size:cover">
                        <div class="wrapper-lg bg-white-opacity">
                            <div class="row m-t" style="margin-top:0px;">
                                <div class="col-sm-7">
                                    <a href class="thumb-lg pull-left m-r" style="margin: 10px;">
                                        <%
                                            if (session.getAttribute("user_id") != null) {
                                                if ((Integer) session.getAttribute("user_id") == userVo.getUserPo().getUser_id()) {
                                        %>
                                        <input type="file" name='img4' id="pic">
                                        <%
                                                }
                                            }
                                        %>
                                        <img src="<%=userVo.getUserPo().getUser_picture()%>" class="img-circle"
                                             width="100px" height="100px" id="picture">
                                    </a>
                                    <div class="bianji">
                                        <div style="margin-top:20px;">
                                            <span style="font-size: 24px;margin-right: 20px;"><%=userVo.getUserPo().getUser_name()%></span>
                                            <span style="font-size: 16px;margin-right: 20px;"><%=userVo.getUserPo().getUser_address()%></span>
                                            <span style="font-size: 16px;margin-right: 20px;"><%=userVo.getUserPo().getUser_sex()%></span>
                                            <br><br>
                                            <span><%=userVo.getUserPo().getUser_signature()%></span>
                                        </div>
                                        <script type="text/javascript">
                                            $(document).ready(function () {
                                                $('#guanzhu123').click(function () {
                                                    var codeTxt = $("#guanzhu123").html();
                                                    var code;
                                                    if (codeTxt == "关注") {
                                                        code = "add";
                                                    } else {
                                                        code = "delete";
                                                    }
                                                    $.ajax({
                                                        url: "concernServlet",//后台文件上传接口
                                                        data: {
                                                            "code": code,
                                                            "user_id": <%=userVo.getUserPo().getUser_id()%>,
                                                            "fan_id": <%=session.getAttribute("user_id")%>
                                                        },
                                                        type: 'get',
                                                        success: function () {
                                                            if (codeTxt == "关注") {
                                                                $("#guanzhu123").html("取消关注");
                                                                alert("关注成功！");
                                                            } else {
                                                                $("#guanzhu123").html("关注");
                                                                alert("取消成功！");
                                                            }
                                                        }, error: function () {
                                                            alert("操作失败！!!!");
                                                        }
                                                    });

                                                });
                                                $.ajax({
                                                    url: "checkConcernServlet",//后台文件上传接口
                                                    data: {
                                                        "user_id": <%=userVo.getUserPo().getUser_id()%>,
                                                        "fan_id": <%=session.getAttribute("user_id")%>
                                                    },
                                                    type: 'get',
                                                    success: function (flag) {
                                                        // alert(flag);
                                                        if (flag == "true") {
                                                            // alert(true);
                                                            $("#guanzhu123").html("取消关注");
                                                        } else {
                                                            // alert(false);
                                                            $("#guanzhu123").html("关注");
                                                        }
                                                    }
                                                });
                                            });
                                        </script>
                                        <%
                                            if (session.getAttribute("user_id") != null) {
                                                if ((Integer) session.getAttribute("user_id") != userVo.getUserPo().getUser_id()) {
                                        %>
                                        <a href="javascript:void(0)" class="btn btn-sm btn-success btn-rounded"
                                           style="margin-top: 30px;background-color: #27c24c;border: 0px;"
                                           id="guanzhu123"></a>
                                        <%
                                                }
                                            }
                                        %>
                                    </div>
                                </div>
                                <div class="col-sm-5">

                                    <!-- ______现在编辑信息的页面________________________________ -->
                                    <%
                                        if
                                        (session.getAttribute("user_id") != null) {
                                            if ((Integer) session.getAttribute("user_id") == userVo.getUserPo().getUser_id()) {
                                    %>
                                    <div style="width:80px;height:30px; margin-top: 20px;">

                                        <div class="text-center">
                                            <a data-toggle="modal" class="btn btn-primary"
                                               href="form_basic.html#modal-form">编辑</a>
                                        </div>
                                        <script type="text/javascript">
                                            $(document).ready(function () {
                                                $("#updateIn").click(function () {
                                                    $.ajax({
                                                        type: "POST",   //提交的方法
                                                        url: "updatePersonServlet?user_id=<%=session.getAttribute("user_id")%>", //提交的地址
                                                        data: $('#myForm').serialize(),// 序列化表单值
                                                        async: false,
                                                        error: function (request) {  //失败的话
                                                            alert("Connection error");
                                                        },
                                                        success: function (data) {  //成功
                                                            alert("修改成功！");  //就将返回的数据显示出来
                                                            location.reload();
                                                        }
                                                    });
                                                });
                                            });
                                        </script>
                                        <div id="modal-form" class="modal fade" aria-hidden="true">
                                            <div class="modal-dialog">
                                                <div class="modal-content">
                                                    <div class="modal-body">
                                                        <div class="row">
                                                            <div class="col-sm-6 b-r"
                                                                 style="border-right: none;width:100%">
                                                                <h3 class="m-t-none m-b">修改信息</h3>
                                                                <form id="myForm">
                                                                    <div class="form-group">
                                                                        <label>性别：</label>
                                                                        <input id="man" type="radio" name="sex"
                                                                               value="man"
                                                                                <%
                                                                                    if (userVo.getUserPo().getUser_sex().equals("男")) {
                                                                                %>
                                                                               checked="checked"
                                                                                <%
                                                                                    }
                                                                                %>
                                                                               name="1"/>男
                                                                        <input id="woman" type="radio" name="sex"
                                                                               value="woman"
                                                                                <%
                                                                                    if (userVo.getUserPo().getUser_sex().equals("女")) {
                                                                                %>
                                                                               checked="checked"
                                                                                <%
                                                                                    }
                                                                                %>
                                                                               name="1"/>女
                                                                    </div>
                                                                    <div class="form-group">
                                                                        <label>用户名：</label>
                                                                        <input type="text"
                                                                               value="<%=userVo.getUserPo().getUser_name()%>"
                                                                               class="form-control" name="user_name">
                                                                    </div>
                                                                    <div class="form-group">
                                                                        <label>地区：</label>
                                                                        <input type="text"
                                                                               value="<%=userVo.getUserPo().getUser_address()%>"
                                                                               class="form-control" name="user_address">
                                                                    </div>
                                                                    <div class="form-group">
                                                                        <label>个性签名：</label>
                                                                        <input type="text" name="user_signature"
                                                                               value="<%=userVo.getUserPo().getUser_signature()%>"
                                                                               class="form-control">
                                                                    </div>
                                                                    <div>
                                                                        <input type="button" value="保存" id="updateIn"
                                                                               class="btn btn-sm btn-primary pull-right m-t-n-xs">
                                                                    </div>
                                                                </form>
                                                            </div>

                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <script src="important/js/jquery.min.js?v=2.1.4"></script>
                                        <script src="important/js/bootstrap.min.js?v=3.3.6"></script>
                                        <script src="important/js/content.min.js?v=1.0.0"></script>
                                        <script src="important/js/plugins/iCheck/icheck.min.js"></script>

                                        <script type="text/javascript" src="http://tajs.qq.com/stats?sId=9051096"
                                                charset="UTF-8"></script>


                                    </div>
                                    <%
                                            }
                                        }
                                    %>
                                    <div class="pull-right pull-none-xs text-center">
                                        <a href class="m-b-md inline m">
                                            <span class="h3 block font-bold"><%=myFans.size()%></span>
                                            <big>粉丝</big>
                                        </a>

                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div id="padder" class="padder">
                        <script type="text/javascript">
                            //删除文章按钮的点击事件
                            function deleteArticle(data) {
                                $.ajax({
                                    type: "POST",   //提交的方法
                                    url: "deleteArticleServlet?article_id=" + data, //提交的地址
                                    error: function () {  //失败的话
                                        alert("删除失败");
                                    },
                                    success: function () {  //成功
                                        alert("删除成功！");  //就将返回的数据显示出来
                                        location.reload();
                                    }
                                });
                            }
                        </script>
                        <!-- .comment-reply -->
                        <c:forEach items="${myArticles}" var="article">
                            <!-- / .comment-reply -->
                            <div class="streamline b-l b-info m-l-lg m-b padder-v">
                                <div>
                                    <a class="pull-left thumb-sm avatar m-l-n-md">
                                        <img src="<%=userVo.getUserPo().getUser_picture()%>" class="img-circle"
                                             alt="...">
                                    </a>
                                    <div class="m-l-lg m-b-lg" style="padding-top: 5px;">
                                        <div class="m-b-xs">
                                            <a href class="h4"><%=userVo.getUserPo().getUser_name()%>
                                            </a>
                                            <span class="text-muted m-l-sm pull-right">
                                                    ${fn:substring(article.articlePo.article_date, 0, 10)}
                                            </span>
                                        </div>
                                        <div class="m-b" style="width: 610px;">
                                            <div class="m-b" style="font-size: 15px;margin-top: 25px;">
                                                <a target="_blank"
                                                   href="detailArticleServlet?article_id=${article.articlePo.article_id}"><font
                                                        color="green">${article.articlePo.article_title}</font></a>
                                                <br>
                                                    ${article.articlePo.article_context}
                                            </div>
                                            <img src="${article.articlePo.article_cover}"
                                                 class="b b-a wrapper-xs bg-white img-responsive">

                                            <!-- -这是文章浏览量 转发等等 -->
                                            <div class="m-t-sm">
                                                <%
                                                    if (session.getAttribute("user_id") != null) {
                                                        if ((Integer) session.getAttribute("user_id") == userVo.getUserPo().getUser_id()) {
                                                %>
                                                <button style="border-radius: 10px;border-left-width: 0px;border-right-width: 0px;border-bottom-width: 0px;border-top-width: 0px;background-color: #1AB394;color: white"
                                                        onclick="deleteArticle(${article.articlePo.article_id})">
                                                    删除
                                                </button>
                                                <%
                                                        }
                                                    }
                                                %>
                                                <div style="float:right">
                                                    <img src="images/eyes.png"
                                                         style="height:20px;width:20px;margin-left:20px"><span>${article.articlePo.article_viewcount}</span>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </c:forEach>
                    </div>
                </div>
            </div>


        </div>
        <!-- PASTE above -->
    </div>
</div>
<!-- /content -->

</div>


<!-- footer -->
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

<!-- / footer -->
</div>
<!-- jQuery -->

<script type="text/javascript">
    +function ($) {
        $(function () {
            // class
            $(document).on('click', '[data-toggle^="class"]', function (e) {
                e && e.preventDefault();
                console.log('abc');
                var $this = $(e.target), $class, $target, $tmp, $classes, $targets;
                !$this.data('toggle') && ($this = $this.closest('[data-toggle^="class"]'));
                $class = $this.data()['toggle'];
                $target = $this.data('target') || $this.attr('href');
                $class && ($tmp = $class.split(':')[1]) && ($classes = $tmp.split(','));
                $target && ($targets = $target.split(','));
                $classes && $classes.length && $.each($targets, function (index, value) {
                    if ($classes[index].indexOf('*') !== -1) {
                        var patt = new RegExp('\\s' +
                            $classes[index].replace(/\*/g, '[A-Za-z0-9-_]+').split(' ').join('\\s|\\s') +
                            '\\s', 'g');
                        $($this).each(function (i, it) {
                            var cn = ' ' + it.className + ' ';
                            while (patt.test(cn)) {
                                cn = cn.replace(patt, ' ');
                            }
                            it.className = $.trim(cn);
                        });
                    }
                    ($targets[index] != '#') && $($targets[index]).toggleClass($classes[index]) || $this.toggleClass($classes[index]);
                });
                $this.toggleClass('active');
            });

            // collapse nav
            $(document).on('click', 'nav a', function (e) {
                var $this = $(e.target), $active;
                $this.is('a') || ($this = $this.closest('a'));

                $active = $this.parent().siblings(".active");
                $active && $active.toggleClass('active').find('> ul:visible').slideUp(200);

                ($this.parent().hasClass('active') && $this.next().slideUp(200)) || $this.next().slideDown(200);
                $this.parent().toggleClass('active');

                $this.next().is('ul') && e.preventDefault();

                setTimeout(function () {
                    $(document).trigger('updateNav');
                }, 300);
            });
        });
    }(jQuery);
</script>
</body>
</html>
