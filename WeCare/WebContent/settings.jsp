<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<%@ include file="styles-and-js-link.jsp"%>
<link rel="stylesheet" href="css/settings.css" />
<script src="js/settings.js"></script>
<title>:: Settings | ${ user.getName() } | We Care ::</title>
</head>
<body>
	<!-- importing tags of JSTL -->
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
	<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

	<!-- logged in nav -->
	<%@ include file="left.jsp"%>
	<%@ include file="loggedin-nav.jsp"%>

	<div class="rest">
		<div class="content">

			<%@ include file="events.jsp"%>

			<%-- <div class="center" style="color: green;">
						${msg}
					</div> --%>
			<div class="info" style="color: red;">${msgerr}</div>

			<!-- change password form -->
			<div class="settings stream">
				<div class="flip">Change Password</div>
				<div class="panel">
					<form id="change-pass">
						<input type="hidden" name="user-id" value="${ user.getuID() }">
						
						<!-- action="ChangePassword" method="post"> -->
						<div class="red center" id="matchResult"></div>
						<table class="table-center">
							
							<tr>
								<td>Old password</td>
								<td><input id="old-pass" type="password" name="old-pass"><span
									id="status"></span></td>
							</tr>
							<tr>
								<td>New password</td>
								<td><input id="new-pass" type="password" name="new-pass"><span
									id="status"></span></td>
							</tr>
							<tr>
								<td>Confirm password</td>
								<td><input id="re-pass" type="password" name="re-pass"><span
									id="status"></span></td>
							</tr>
						<!-- 	<tr>
								<td colspan="2" class="center"></td>
							</tr> -->
							<tr>
								<td colspan="2" class="center"><button class="button">Update
										Password</button></td>
							</tr>
						</table>
					</form>
				</div>
			</div>
		</div>
		<!-- content -->
	</div>
	<!-- rest -->


</body>
</html>