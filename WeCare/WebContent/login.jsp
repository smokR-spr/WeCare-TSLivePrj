<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<!-- PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd"> -->
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<%@ include file="styles-and-js-link.jsp"%>

<link rel="stylesheet" href="css/login.css" />

<title>:: Log In | We Care ::</title>
</head>
<body>

	<!-- if already logged in goto login-success -->
	<c:if test="${ user != null }">
		<c:redirect url="/login-success.jsp">
		</c:redirect>
	</c:if>


	<%@ include file="nav.html"%>

	<div id="rest">
		<!-- <div id="left"></div> -->
		<div id="content">

			<div class="stream">
				<div class="center" style="color: green;">
					<!-- using EL syntax -->
					${msg}
				</div>
				<div class="center" style="color: red;">
					${param.msgerr}
					<!-- getting param from jsp:forward of every page -->
					${msgerr}
					<!-- request parameter -->
				</div>
				<form id="login-form" action="Login" method="post">
					<table class="table-center">
						<thead>
							<tr>
								<td class="center" colspan="2"><h2>Log In</h2></td>
							</tr>
						</thead>

						<tr>
							<td>User ID</td>
							<!-- ${ (param["user-id"] == null) || (param["user-id"].equals(""))? "autofocus": "" } -->
							<td><input type="text" id="user-id" name="user-id" required
								value=${param["user-id"]}></td>
						</tr>
						<tr>
							<td>Password</td>
							<!-- ${ !(param["user-id"] == null) || (param["user-id"].equals(""))? "autofocus": "" } -->
							<td><input type="password" id="password" required
								name="password" /></td>
						</tr>
						<tr>
							<td colspan="2" class="info-small" style="text-align: right;"><a
								href="forgot-pass.jsp">Forgot password?</a></td>
						</tr>
						<tr>
							<td class="center" colspan="2"><button type="submit"
									class="button">Log In</button></td>
						</tr>
					</table>
				</form>
				<!-- <div id="pass-validation"></div> -->
			</div>
		</div>
	</div>
	<script type="text/javascript">
		//jQuery script to autofocus the empty field
		$(function() {
			var username = $('#login-form #user-id');
			if (!username.val()) { //username field is empty
				username.focus();
			} else {
				$('#login-form #password').focus();
			}
		});
	</script>
</body>
</html>