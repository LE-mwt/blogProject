<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>Insert title here</title>
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <title>用户管理</title>
        <meta name="description" content="这是一个 index 页面">
        <meta name="keywords" content="index">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta name="renderer" content="webkit">
        <meta http-equiv="Cache-Control" content="no-siteapp"/>
        <link rel="icon" type="image/png" href="admin_assets/i/favicon.png">
        <link rel="apple-touch-icon-precomposed" href="admin_assets/i/app-icon72x72@2x.png">
        <meta name="apple-mobile-web-app-title" content="Amaze UI"/>
        <script src="admin_assets/js/echarts.min.js"></script>
        <link rel="stylesheet" href="admin_assets/css/amazeui.min.css"/>
        <link rel="stylesheet" href="admin_assets/css/amazeui.datatables.min.css"/>
        <link rel="stylesheet" href="admin_assets/css/app.css">
        <script src="admin_assets/js/jquery.min.js"></script>


        <script type="text/javascript">
            //打开文章详情页的方法
            function toOpenDetailWindow(_id) {
                var url = "./adminDetailArticleServlet?article_id=" + _id;
                window.open(url, '_blank');
            }

            //实现分页的方法
            function page_nav(frm, num) {
                frm.pageIndex.value = num;
                // if (num > 0) {
                //     alert("第" + num + "页")
                // } else {
                //     alert("当前已是第1页")
                // }
                frm.submit();
            }

            //全选的功能
            function allcheck() {
                //先得到所有的checkbox
                var ck = document.getElementsByName("allcheck");//得到一组checkbox  相当于数组
                //循环这一组checkbox让每一个checkbox选中
                for (var i = 0; i < ck.length; i++) {
                    var c = ck[i];//得到一个checkbox
                    c.checked = true;//true代表选中
                }
            }

            //全不选
            function allnotcheck() {
                //先得到所有的checkbox
                var ck = document.getElementsByName("ck");//得到一组checkbox  相当于数组
                //循环这一组checkbox让每一个checkbox选中
                for (var i = 0; i < ck.length; i++) {
                    var c = ck[i];//得到一个checkbox
                    c.checked = false;//false代表不选
                }
            }

            //反选
            function backcheck() {//先得到所有的checkbox
                var ck = document.getElementsByName("ck");//得到一组checkbox  相当于数组
                //循环这一组checkbox让每一个checkbox选中
                for (var i = 0; i < ck.length; i++) {
                    var c = ck[i];//得到一个checkbox
                    if (c.checked == true) {//如果当前的checkbox是选中的则不让其选中
                        ck[i].checked = false;
                    } else {
                        ck[i].checked = true;
                    }
                }
            }

            //批量删除
            function alldel() {
                var f = false;
                //得到要删除的主键id
                var arr = [];//用于存要删除的选中的id值

                var ck = document.getElementsByName("ck");//得到一组checkbox  相当于数组
                //循环这一组checkbox让每一个checkbox选中
                for (var i = 0; i < ck.length; i++) {
                    if (ck[i].checked == true) {//代表选中
                        arr.push(ck[i].value);
                        f = true;
                    }
                }

                alert(arr);
                //跳到删除的servlet里去
                if (f == true) {
                    if (confirm("您确认要冻结吗？"))
                    //location.href="adminDeleteArticleServlet?arr="+arr;
                        dongjieMany(arr);
                } else {
                    alert("请至少选中一条进行批量冻结");
                }
            }

            function dongjieMany(arr) {
                alert(arr);
                $.ajax({
                    url: "dongjieUserServlet",
                    data: {"arr": arr + ""},
                    type: 'get',
                    success: function () {
                        alert("批量冻结用户成功");
                        location.reload();
                    }, error: function () {
                        alert("批量冻结用户失败");
                    }
                });
            }

            function dongjieOne(user_id) {

                $.ajax({
                    url: "dongjieUserServlet",
                    data: {"user_id": user_id},
                    type: 'get',
                    success: function () {
                        alert("冻结用户成功");
                        location.reload();
                    }, error: function () {
                        alert("冻结用户失败");
                    }
                });
            }

            function cancleDongjieOne(user_id) {
                $.ajax({
                    url: "cancleDongjieUserServlet",
                    data: {"user_id": user_id},
                    type: 'get',
                    success: function () {
                        alert("取消冻结用户成功");
                        location.reload();
                    }, error: function () {
                        alert("取消冻结用户失败");
                    }
                });
            }

        </script>


    </head>

<body data-type="widgets">
<script src="admin_assets/js/theme.js"></script>
<div class="am-g tpl-g">
    <!-- 头部 -->
    <header>
        <!-- logo -->
        <div class="am-fl tpl-header-logo">

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
                        <a href="javascript:;">欢迎你, <span>${user_id }</span> </a>
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

            </div>
        </div>

        <!-- 菜单 -->
        <ul class="sidebar-nav">

            <li class="sidebar-nav-link">
                <a href="./adminIndexServlet">
                    <i class="am-icon-home sidebar-nav-link-logo"></i> 首页
                </a>
            </li>


            <li class="sidebar-nav-link">
                <a href="./adminUserServlet" class="active">
                    <i class="am-icon-table sidebar-nav-link-logo"></i> 用户管理

                </a>
            </li>

            <li class="sidebar-nav-link">
                <a href="./adminArticleServlet">
                    <i class="am-icon-bar-chart sidebar-nav-link-logo"></i> 博文管理

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
                        <div class="widget-head am-cf">
                            <div class="widget-title  am-cf">用户列表</div>


                        </div>
                        <div class="widget-body  am-fr">

                            <div class="am-u-sm-12 am-u-md-6 am-u-lg-6">
                                <div class="am-form-group">
                                    <div class="am-btn-toolbar">
                                        <div class="am-btn-group am-btn-group-xs">
                                            <input type="radio" id="allcheck" name="allcheck" onclick="allcheck();"/>全选&nbsp;&nbsp;
                                            <input type="radio" id="allnotcheck" name="allcheck"
                                                   onclick="allnotcheck();"/>全不选&nbsp;&nbsp;
                                            <input type="radio" id="backcheck" name="allcheck" onclick="backcheck();"/>反选&nbsp;&nbsp;
                                            <button type="button" class="am-btn am-btn-default am-btn-danger"
                                                    onclick="alldel()"><span class="am-icon-trash-o"></span> 批量冻结
                                            </button>
                                        </div>
                                    </div>
                                </div>
                            </div>


                            <form action="./adminUserServlet" method="post">
                                <div class="am-input-group am-input-group-sm tpl-form-border-form cl-p"
                                     style="width: 200px;">
                                    <input type="text" name="keyword" id="keyword" class="" value="${keywords }">
                                    <span class="am-input-group-btn">
                                                <button
                                                        onclick="javaScript:page_nav(document.forms[1],1)"
                                                        class="am-btn  am-btn-default am-btn-success tpl-table-list-field am-icon-search"
                                                        type="button">
											  </button>
                                           </span>
                                    <input type="hidden" type="submit" id="pageIndex" name="pageIndex" value="1"/>
                                </div>
                            </form>

                            <div class="am-u-sm-12">
                                <table width="100%" class="am-table am-table-compact am-table-striped tpl-table-black "
                                       id="example-r">
                                    <thead>
                                    <tr>
                                        <th>选择</th>
                                        <th>用户名</th>
                                        <th>性别</th>
                                        <th>粉丝数</th>
                                        <th>关注人数</th>
                                        <th>个性签名</th>
                                        <th>操作</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <c:forEach items="${userlist}" var="user">
                                        <tr class="gradeX">
                                            <td><input type="checkbox" name="ck" value="${user.user_id}"/></td>
                                            <td>${user.user_name}</td>
                                            <td>${user.user_sex}</td>
                                            <td>${fn:length(user.myFans)}</td>
                                            <td>${fn:length(user.myConcern)}</td>
                                            <td>${user.user_signature}</td>


                                            <td>
                                                <div class="tpl-table-black-operation">
                                                    <c:choose>
                                                        <c:when test="${user.states==0}">
                                                            <a href="javascript:cancleDongjieOne(${user.user_id })"
                                                               class="tpl-table-black-operation-del">取消冻结</a>

                                                        </c:when>
                                                        <c:otherwise>
                                                            <a href="javascript:dongjieOne(${user.user_id })"
                                                               class="tpl-table-black-operation-del">冻结</a>

                                                        </c:otherwise>
                                                    </c:choose>
                                                </div>
                                            </td>
                                        </tr>
                                    </c:forEach>


                                    </tbody>
                                </table>
                            </div>
                            <div class="am-u-lg-12 am-cf">

                                <div class="am-fr">
                                    <ul class="am-pagination tpl-pagination">
                                        <li><a href="javaScript:page_nav(document.forms[1],${currPageNo-1})">上一页</a>
                                        </li>
                                        <li><a href="javaScript:page_nav(document.forms[1],${currPageNo+1})">下一页</a>
                                        </li>
                                        <li>第</li>
                                        <input type="text" id="inputPage" name="inputPage"
                                               style="width:50px;height:33px;margin-top:-5px">
                                        <li>页</li>
                                        <li>
                                            <a href="javaScript:page_nav(document.forms[1],document.getElementById('inputPage').value)">跳转</a>
                                        </li>
                                    </ul>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</div>
<script src="admin_assets/js/amazeui.min.js"></script>
<script src="admin_assets/js/amazeui.datatables.min.js"></script>
<script src="admin_assets/js/dataTables.responsive.min.js"></script>
<script src="admin_assets/js/app.js"></script>


</body>

</html>