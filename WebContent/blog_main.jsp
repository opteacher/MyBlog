<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.op.web.pojo.User"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>BlOG</title>
	<link rel="icon" href="assets/img/favicon.ico">
	<link href="assets/css/bootstrap.min.css" rel="stylesheet">
	<link href="assets/css/blog.css" rel="stylesheet">
</head>
<body>
	<!-- 导航栏 -->
	<div class="blog-masthead">
		<div class="container">
			<nav class="blog-nav">
				<a class="blog-nav-item active" href="blog_main.jsp">主页</a>
				<a class="blog-nav-item" href="blog_list.jsp">日志列表</a>
				<a class="blog-nav-item navbar-right" href="usrLogout.action">退出</a>
			</nav>
		</div>
	</div>

	<div class="container">

		<!-- BLOG内容 -->
		<div class="row">

			<!-- 左边详细 -->
			<div class="col-sm-8 blog-main">

				<!-- BLOG标头 -->
				<div class="blog-header">
					<h1 class="blog-title">${userInfo.nam}的博客</h1>
					<p class="lead blog-description">技术记录和讨论</p>
				</div>

				<div class="blog-add">
					<button type="button" class="btn btn-primary" data-toggle="modal" data-target="#addEdtDlg">+</button>
				</div>
				
				<!-- Modal -->
				<div class="modal fade" id="addEdtDlg" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
					<div class="modal-dialog" role="document">
						<div class="modal-content">
							<form action="addBlog.action" method="post">
								<div class="modal-header">
									<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
									<h4 class="modal-title" id="myModalLabel">添加编辑博客</h4>
								</div>
								<div class="modal-body">
									<input name="blgTitle" class="edt-title" type="text" placeholder="标题" required autofocus>
									<textarea name="blgContents" class="edt-content" placeholder="内容"></textarea>
									<div class="btn-group btn-group-justified">
										<button class="btn btn-default btn-edt-tool" type="button">
											<img src="assets/img/code.png">
										</button>
										<button class="btn btn-default btn-edt-tool" type="button">
											<img src="assets/img/strong.png">
										</button>
										<button class="btn btn-default btn-edt-tool" type="button">
											<img src="assets/img/info.png">
										</button>
										<button class="btn btn-default btn-edt-tool" type="button">
											<img src="assets/img/olst.png">
										</button>
										<button class="btn btn-default btn-edt-tool" type="button">
											<img src="assets/img/ulst.png">
										</button>
									</div>
								</div>
								<input type="hidden" name="blgIndex" id="hdnBlgIdx" >
								<div class="modal-footer">
									<button type="submit" class="btn btn-primary" style="border-radius:0">添加/保存</button>
								</div>
							</form>
						</div>
					</div>
				</div>
				
				<c:forEach items="${blogContents}" var="blgCts">
					<div class="blog-post">
						<div class="row">
							<div class="col-md-9 text-left">
								<h2 class="blog-post-title">${blgCts.ttl}</h2>
							</div>
							<c:if test="${userInfo.mgr}">
								<div class="col-md-3 text-right">
									<button type="button" class="btn btn-default btn-edt" title="${blgCts.idx}">
										<img src="assets/img/edit.png">
									</button>
									<button type="button" class="btn btn-default btn-del" title="${blgCts.idx}">
										<img src="assets/img/close.png">
									</button>
								</div>
							</c:if>
						</div>
						<p class="blog-post-meta"><span>${blgCts.pbc}</span> 由 
							<a href="#">${blgCts.nam}</a> 发表
						</p>
						<div>${blgCts.cts}</div>
					</div>
				</c:forEach>

				<!-- 翻页 -->
				<!-- <div>
					<div style="display:inline;"><a href="#">上一篇</a></div>
					<div style="display:inline;float:right;"><a href="#">下一篇</a></div>
				</div> -->
			</div><!-- /.blog-main -->

			<!-- 右侧信息 -->
			<div class="col-sm-3 col-sm-offset-1 blog-sidebar">
				<div class="text-center user-icn">
					<img class="img-circle" src="${userInfo.pfl}">
				</div>
				<div class="sidebar-module sidebar-module-inset">
					<div class="list-group">
						<c:forEach items="${lgnUsr}" var="lu">
							<a href="#" class="list-group-item">
								<img class="img-circle usr-small" src="${lu.pfl}">
								<span class="text-right">${lu.nam}</span>
							</a>
						</c:forEach>
					</div>
				</div>
				<div class="sidebar-module">
					<h4>发表日期</h4>
					<ol class="list-unstyled">
						<c:forEach items="${pbcMonth}" var="pm">
							<li><a href="#">${pm}</a></li>
						</c:forEach>
					</ol>
				</div>
			</div><!-- /.blog-sidebar -->
		</div><!-- /.row -->
	</div><!-- /.container -->

	<footer class="blog-footer">
		<p>Blog template built for <a href="http://getbootstrap.com">Bootstrap</a> by <a href="https://twitter.com/mdo">@mdo</a>.</p>
		<p>
			<a href="#">Back to top</a>
		</p>
	</footer>

	<script src="assets/js/jquery.min.js"></script>
	<script src="assets/js/bootstrap.min.js"></script>
	<script type="text/javascript">
		$(".btn-edt").bind(
		{
			mouseenter: function()	{ $(this).children("img").attr("src", "assets/img/edit_hover.png"); },
			mouseleave: function()	{ $(this).children("img").attr("src", "assets/img/edit.png"); },
			click: function()
			{
				var pnt = $(this).parent();
				var ttl = pnt.prev().children("h2").html();
				var ahrPbc = pnt.parent().next();
				var cts = ahrPbc.next().html();
				//var pbc = ahrPbc.children("span").html();
				//var ahr = ahrPbc.children("a").html();
				$("input[name='blgTitle']").val(ttl);
				$("textarea[name='blgContents']").val(cts);
				$("#hdnBlgIdx").val($(this).attr("title"));
				$("#addEdtDlg").modal("show");
			}
		});
		$(".btn-del").bind(
		{
			mouseenter: function()	{ $(this).children("img").attr("src", "assets/img/close_hover.png"); },
			mouseleave: function()	{ $(this).children("img").attr("src", "assets/img/close.png"); },
			click: function()
			{
				window.location.href = "delBlog.action?idx=" + $(this).attr("title");
			}
		})
	</script>
</body>
</html>