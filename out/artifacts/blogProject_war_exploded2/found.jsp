<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!doctype html>
<html>
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="description" content="">
  <meta name="keywords" content="">
  <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
  <title>BLOG  | Amaze UI Examples</title>
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
  
  <script type="text/javascript">
function page_nav(frm,num){
		frm.pageIndex.value=num;
		if(num>0){
			alert("第"+num+"页")
		}else{
			alert("当前已是第1页")
		}
		frm.submit();
	}

</script>
</head>

<body id="blog">
<hr>
		<nav class="am-g am-g-fixed blog-fixed blog-nav">
			<button class="am-topbar-btn am-topbar-toggle am-btn am-btn-sm am-btn-success am-show-sm-only blog-button"
			 data-am-collapse="{target: '#blog-collapse'}"><span class="am-sr-only">导航切换</span> <span class="am-icon-bars"></span></button>

			<div class="am-collapse am-topbar-collapse" id="blog-collapse">
				<ul class="am-nav am-nav-pills am-topbar-nav">
					<li><a href="index.html">首页</a></li>
					<li class="am-active"><a href="found.html">发现</a></li>
					<li><a href="myconcern.html">我的关注</a></li>


				</ul>

			<a href="writeArticle.html" title="写文章"><img class="add" src="images/write84.png" style="height:30px;width:30px"/></a>
  			<a href="message.html" title="我的消息"><img class="add2" src="images/mes84.png " style="height:30px;width:30px"/></a>

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

				<form class="am-topbar-form am-topbar-right am-form-inline" role="search" style="margin-left: 300px;" action="./foundServlet" method="post">
					<div class="am-form-group" style="width: 280px;">
						<input type="text"  name="keyword" id="keyword" class="am-form-field am-input-sm" placeholder="输入关键字" style="width: 208px;"value="${keywords }">
						<input type="submit" value="搜索" id="serach-btn" onclick="javascript:window.location.href='foundServlet?pageIndex='+1">
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
    background: #F6F8F9;"
>
<div class="am-g am-g-fixed blog-fixed">
    <div class="am-u-md-8 am-u-sm-12" style="
    padding-left: 18px;
    left: 200px">

        <c:forEach items="${articleList }" var="article">
        <article class="am-g blog-entry-article">
            <div class="am-u-lg-6 am-u-md-12 am-u-sm-12 blog-entry-img">
                <img src="${article.article_cover }" alt="" class="am-u-sm-12">
            </div>
            <div class="am-u-lg-6 am-u-md-12 am-u-sm-12 blog-entry-text">
                <span><a href="" class="blog-color">${article.article_type } &nbsp;</a></span>
                <span> ${article.user_name } &nbsp;</span>
                <span>${article.article_date }</span>
                <h1><a href="">${article.article_title }</a></h1>
                <p>${article.article_context }
                </p>
                <p><a href="" class="blog-continue">continue reading</a></p>
            </div>
        </article>
        </c:forEach>

        <!-- <article class="am-g blog-entry-article">
            <div class="am-u-lg-6 am-u-md-12 am-u-sm-12 blog-entry-img">
                <img src="assets/i/f6.jpg" alt="" class="am-u-sm-12">
            </div>
            <div class="am-u-lg-6 am-u-md-12 am-u-sm-12 blog-entry-text">
                <span><a href="" class="blog-color">article&nbsp;</a></span>
                <span>@amazeUI &nbsp;</span>
                <span>2015/10/9</span>
                <h1><a href="detailArticle.html">选择的正确性</a></h1>
                <p>你可以选择在原处不停地跟周遭不解的人解释你为何这么做，让他们理解你，你可以选择什么都不讲，自顾自往前走。
                </p>
                <p><a href="" class="blog-continue">continue</a></p>
            </div>
        </article>

        <article class="am-g blog-entry-article">
            <div class="am-u-lg-6 am-u-md-12 am-u-sm-12 blog-entry-img">
                <img src="assets/i/f12.jpg" alt="" class="am-u-sm-12">
            </div>
            <div class="am-u-lg-6 am-u-md-12 am-u-sm-12 blog-entry-text">
                <span><a href="" class="blog-color">article&nbsp;</a></span>
                <span>@amazeUI</span>
                <span>2015/10/9</span>
                <h1><a href="detailArticle.html">开心时拥抱全世界</a></h1>
                <p>那时候刚好下着雨，柏油路面湿冷冷的，还闪烁着青、黄、红颜色的灯火。我们就在骑楼下躲雨，看绿色的邮筒孤独地站在街的对面。
                </p>
                <p><a href="" class="blog-continue">continue</a></p>
            </div>
        </article>

        <article class="am-g blog-entry-article">
            <div class="am-u-lg-6 am-u-md-12 am-u-sm-12 blog-entry-img">
                <img src="assets/i/f13.jpg" alt="" class="am-u-sm-12">
            </div>
            <div class="am-u-lg-6 am-u-md-12 am-u-sm-12 blog-entry-text">
                <span><a href="" class="blog-color">article&nbsp;</a></span>
                <span>@amazeUI</span>
                <span>2015/10/9</span>
                <h1><a href="detailArticle.html">总以为机会无限</a></h1>
                <p>那时候刚好下着雨，柏油路面湿冷冷的，还闪烁着青、黄、红颜色的灯火。我们就在骑楼下躲雨，看绿色的邮筒孤独地站在街的对面。
                </p>
                <p><a href="" class="blog-continue">continue</a></p>
            </div>
        </article>

        <article class="am-g blog-entry-article">
            <div class="am-u-lg-6 am-u-md-12 am-u-sm-12 blog-entry-img">
                <img src="assets/i/f10.jpg" alt="" class="am-u-sm-12">
            </div>
            <div class="am-u-lg-6 am-u-md-12 am-u-sm-12 blog-entry-text">
                <span><a href="" class="blog-color">article&nbsp;</a></span>
                <span> @amazeUI &nbsp;</span>
                <span>2015/10/9</span>
                <h1><a href="">GIVE ME SOME BIG TEXT AND FONT </a></h1>
                <p>那时候刚好下着雨，柏油路面湿冷冷的，还闪烁着青、黄、红颜色的灯火。我们就在骑楼下躲雨，看绿色的邮筒孤独地站在街的对面。
                </p>
                <p><a href="detailArticle.html" class="blog-continue">continue reading</a></p>
            </div>
        </article>

        <article class="am-g blog-entry-article">
            <div class="am-u-lg-6 am-u-md-12 am-u-sm-12 blog-entry-img">
                <img src="assets/i/f10.jpg" alt="" class="am-u-sm-12">
            </div>
            <div class="am-u-lg-6 am-u-md-12 am-u-sm-12 blog-entry-text">
                <span><a href="" class="blog-color">article&nbsp;</a></span>
                <span> @amazeUI &nbsp;</span>
                <span>2015/10/9</span>
                <h1><a href="detailArticle.html">taha cool man i do believe</a></h1>
                <p>那时候刚好下着雨，柏油路面湿冷冷的，还闪烁着青、黄、红颜色的灯火。我们就在骑楼下躲雨，看绿色的邮筒孤独地站在街的对面。
                </p>
                <p><a href="" class="blog-continue">continue reading</a></p>
            </div>
        </article>

         <article class="am-g blog-entry-article">
            <div class="am-u-lg-6 am-u-md-12 am-u-sm-12 blog-entry-img">
                <img src="assets/i/f10.jpg" alt="" class="am-u-sm-12">
            </div>
            <div class="am-u-lg-6 am-u-md-12 am-u-sm-12 blog-entry-text">
                <span><a href="" class="blog-color">article&nbsp;</a></span>
                <span> @amazeUI &nbsp;</span>
                <span>2015/10/9</span>
                <h1><a href="detailArticle.html">taha cool man i do believe</a></h1>
                <p>那时候刚好下着雨，柏油路面湿冷冷的，还闪烁着青、黄、红颜色的灯火。我们就在骑楼下躲雨，看绿色的邮筒孤独地站在街的对面。
                </p>
                <p><a href="" class="blog-continue">continue reading</a></p>
            </div>
        </article> -->
        
        <ul class="am-pagination">
  <li class="am-pagination-prev"><a href="javaScript:page_nav(document.forms[0],${currPageNo-1});">&laquo; Prev</a></li>
  <li class="am-pagination-next"><a href="javaScript:page_nav(document.forms[0],${currPageNo+1});">Next &raquo;</a></li>
</ul>
    </div>
</div>
<footer class="blog-footer">
    <div class="am-g am-g-fixed blog-fixed am-u-sm-centered blog-footer-padding">
        <div class="am-u-sm-12 am-u-md-4- am-u-lg-4">
            <h1>FUN-BLOG</h1>
            <p class="am-text-sm">这是一个有趣灵魂的聚集地。<br> 博客新生代 <br> 多种布局，包括首页、发现页、关注页、个人信息页、消息页等<br>让有趣的思想碰撞，和有趣的灵魂相遇！<br><br>
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
    
  <a name="Bot1111">
</footer>


</div>
<!-- content end -->



<div class="fixed"><a href="#top" title="置顶"><img src="images/84-1.png" style="height:40px;width:40px"></a></div>
<div class="submeau" style="width: 35px; height: 60px; background-color: #e4e5e6;border-radius:10px ; z-index: 998;" >
<div style="font-size:12px;line-height:1.3;text-align:center"><br><b><a href="#Bot1111">关于我们</a></b></div>
</div>


  <style type="text/css">
	
	.fixed{
		position: fixed;
		top: 450px;
		right: 40px;
	}
	.submeau{
		position: fixed;
		top: 500px;
		right: 40px;
	}
	.fixed img:hover{
	 transform: scale(1.3);
	}
	.submeau:hover{
	 transform: scale(1.1);

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