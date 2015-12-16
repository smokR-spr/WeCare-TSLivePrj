<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<%@ include file="styles-and-js-link.jsp"%>
<style type="text/css">
p {
	/* font-weight: bold; */
	text-align: left;
}
</style>

<title>:: Forgot Password | We Care ::</title>

<script type="text/javascript" src="js/forgot-pass.js"></script>

</head>
<body>
	<%@ include file="nav.html"%>

	<div id="rest">
		<div id="content">
			<div class="stream">
				<%-- <div class="center info" style="color: green;">
					${msg} ${ param.msg }
					<!-- to access the attribute passed through the URL -->
				</div>
				<div class="center info" style="color: red;">${msgerr} ${ param.msgerr }
				</div> --%>

				<div id="forgot-form" class="center">
					<h2>Forgot Password?</h2>
					<form>
						<p style="text-align: left;">Enter your user id below and press continue</p>
						<input size="40" type="text" name="userid" required autofocus>
						<button type="submit" class="button">Continue</button>
					</form>

					<div id="user-id-search-result">
						<!-- content is added here using jQuery -->
					</div>
				</div>
			</div>
		</div>
		<!-- stream -->
		<!-- content -->
	</div>
	<!-- rest -->
</body>
</html>