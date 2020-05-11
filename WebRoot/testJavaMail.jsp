<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
   
    <title>演示javaMail</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	

  </head>
  
  <body>
   	<form action="MailServlet" method="post">
   		发信人<input type="text" name="from"/><br/>
   		收信人<input type="text" name="to"/><br/>
   		主题<input type="text" name="subject"/><br/>
   		<textarea name="content" cols="50" rows="10"></textarea><br/>
   		<input type="submit" value="发送"/>
   	</form>
  </body>
</html>
