<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>登陆BLOG</title>
	<link rel="icon" href="assets/img/favicon.ico">
	<link href="assets/css/bootstrap.min.css" rel="stylesheet">
	<link href="assets/css/signin.css" rel="stylesheet">
</head>
<body>
	<div class="container">
		<form class="form-signin" action="userLogin.action" method="post">
			<%
				HttpSession hs = request.getSession();
				String msg = (String) hs.getAttribute("errMsg");
				if(msg != null && !msg.isEmpty())
				{
					hs.removeAttribute("errMsg");
			%>
				<div class="alert alert-danger" role="alert" style="border-radius:0;"><%= msg %></div>
			<%	} %>
			<h2 class="form-signin-heading">登陆界面</h2>
			<input type=text name="userName" id="inputUser" class="form-control" placeholder="用户名" value="${dftUsr}" required autofocus>
			<input type="password" name="password" id="inputPassword" class="form-control" placeholder="密码" required>
			<div class="checkbox">
				<label>
					<input name="rmbUsr" type="checkbox"> 记住用户名
				</label>
			</div>
			<button class="btn btn-lg btn-primary btn-block btn-norad" type="submit">登陆</button>
			<div class="text-center" style="margin-top:10px;">
				<a href="blank.jsp?blkAct=usrRegInit">还未注册，前往注册页面</a>
			</div>
		</form>
	</div>
</body>
</html>