<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>注册BLOG</title>
	<link rel="icon" href="assets/img/favicon.ico">
	<link href="assets/css/bootstrap.min.css" rel="stylesheet">
	<link href="assets/css/signin.css" rel="stylesheet">
</head>
<body>
	<div class="container">

		<form class="form-signin" action="userRegister.action" method="post" enctype="multipart/form-data">
			<h2 class="form-signin-heading">注册界面</h2>
			<input type="text" name="userName" id="inputUser" class="form-control" placeholder="用户名" required autofocus>
			<input type="password" name="password" id="inputFstPwd" class="form-control" placeholder="密码" required>
			<input type="password" id="inputRptPwd" class="form-control" placeholder="重输密码" required>
			<input type="text" name="mgrCode" id="inputMgrCode" class="form-control" placeholder="管理员码（可不填）">
			<div class="btn-group form-control btn-norad no-padding bottom-margin-10">
				<button type="button" class="btn btn-default dropdown-toggle form-control btn-norad no-border" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
					选择角色 <span class="caret"></span>
				</button>
				<ul class="dropdown-menu btn-norad">
					<c:forEach items="${roleMap}" var="rm">
						<li><a href="#" title="${rm.id}">${rm.nam}</a></li>
					</c:forEach>
				</ul>
				<input type="hidden" name="role" id="hiddenRole" value="">
			</div>
			<div class="input-group form-control btn-norad no-padding bottom-margin-10">
				<span class="input-group-addon btn-norad no-border">
					<input type="checkbox" id="chkSelPfl" disabled="disabled">
				</span>
				<button type="button" id="btnSelPfl" class="btn btn-default form-control btn-norad no-border" onclick="$('#hdnFileBtn').click();">选择头像</button>
				<input type="file" name="profile" id="hdnFileBtn" style="display: none">
			</div>
			<button class="btn btn-lg btn-primary btn-block btn-norad" type="submit">注册</button>
		</form>

	</div>

	<script src="assets/js/jquery.min.js"></script>
	<script src="assets/js/bootstrap.min.js"></script>
	<script type="text/javascript">
		$(".dropdown-menu li a").click(function()
		{
			var ttl = $(this).attr("title");
			$("#hiddenRole").val(ttl);
			var txt = $(this).html();
			txt += "&nbsp<span class='caret'></span>";
			$(".dropdown-toggle").html(txt);
		})

		$("#hdnFileBtn").change(function()
		{
			$("#chkSelPfl").attr("checked", "true");
		})
	</script>
</body>
</html>