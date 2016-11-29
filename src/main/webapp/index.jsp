<%@ page import="org.church.our.loving.util.StringUtil" %>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String configs = " Base: " + basePath + "<br/> Root Dir:  " + StringUtil.getRootDir() + "<br/> Application Path: " + application.getRealPath("") + "<br/>";
%>

<!DOCTYPE html>
<html>
<head>
    <base href="<%=basePath%>">
	<meta charset="UTF-8">
    <title>Harvest Tree of Life Church v3.0</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
  </head>
  <body>
    This is index page of our church. v3.1 <br>
    Configurations:<br/>
    <%=configs %>
    <form method="POST" enctype="multipart/form-data" action="upload">
	  File to upload: <input type="file" name="upfile"><br/>
	  Notes about the file: <input type="text" name="note"><br/>
	  <br/>
  		<input type="submit" value="Press"> to upload the file!
	</form>
  </body>
</html>
