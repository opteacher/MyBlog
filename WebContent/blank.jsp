<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%
	String blkAct = request.getParameter("blkAct");
	if(blkAct == null || blkAct.isEmpty())
	{
		blkAct = "usrLgnInit";
	}
%>
<meta http-equiv="refresh" content="0;url=<%=blkAct%>">
<title>loading...</title>
</head>
<body>

</body>
</html>