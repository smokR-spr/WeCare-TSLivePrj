<%-- <%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
</head>
<body>
 --%>
<link rel="stylesheet" href="css/loggedin-nav.css">
<div class="header">
	<div class="container">
		<div id="logo">
			<a href="home.jsp"><img src="images/WeCare-Logo.png"
				alt="We Care"></a>
		</div>

		<div class="dropdown">
			<a class="account">My Account</a>
			<div class="submenu">
				<ul class="root">
					<li><a href="login-success.jsp">Home</a></li>
					<li><a href="settings.jsp">Settings</a></li>
					<li><a id="signout" href="SignOut">
							<!-- onClick="javascript:SignOut()"> -->
							<!-- href="SignOut"> -->Sign Out
					</a></li>
				</ul>
			</div>
		</div>

		<div id="name">
			<img src="images/WeCare-small.png" />
		</div>
	</div>
</div>

<div class="footer">Copyright © WeCare</div>

<%@ include file="user-validation.jsp"%>

<!-- <script type="text/javascript" src="src-scripts/jquery.min.js"></script> -->
<script type="text/javascript" src="js/loggedin.js"></script>
<!-- </body>
</html> -->