<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	
	<%@ include file="styles-and-js-link.jsp" %>
	
	<title>:: We Care ::</title>
</head>
<body>
	<%@ include file="nav.html" %>
	
	<!-- if already logged in goto login-success -->
	<c:if test="${ user != null }">
		<div id="logged-in-user" class="info-small">
			logged in as<br/><div id="uid"><a href="login-success.jsp">${ user.getuID() }</a></div>
			Not you? <a href="SignOut">Click here</a></div>
	</c:if>
	
	<div id="rest">
		<!-- <div id="left"></div> -->
		<div class="content">
			<div id="intro">
				<%@ include file="intro.html" %>
			</div>
		</div>
	</div>
</body>
</html>