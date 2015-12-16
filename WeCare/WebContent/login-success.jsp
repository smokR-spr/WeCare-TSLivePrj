<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<%@ include file="styles-and-js-link.jsp" %>
	<link rel="stylesheet" href="css/login-success.css"/>
	<title>:: ${user.getName()} | We Care<%-- <%= (String)request.getAttribute("user-name") %> --%> ::</title>
</head>
<body>
	<%@ include file="left.jsp" %>
	<%@ include file="loggedin-nav.jsp" %>
	
	<div class="rest">
		<div class="content">
		
			<%@ include file="events.jsp" %>
			
			<div class="center info" style="color: green;">
				${msg}
				${ param.msg } <!-- to access the attribute passed through the URL -->
			</div>
			<div class="center info" style="color: red;">
				${msgerr}
				${ param.msgerr }
			</div>
			<%-- <div class="user-page">
				<div class="center" style="color: green;">
					<h1>
					<%
						String msg = (String)request.getAttribute("msg");
						
						if(msg != null) {
							out.println(msg);
						}
					%>
					</h1>
				</div>
			</div> --%>
			
			<div class="info">
				<p>Hello ${user.getName()}! Happy discussion. :)</p>
			</div>
			
		</div>
	</div>
</body>
</html>