<!-- this page is directly accessed through the reset link sent to the mail id of the user -->

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<%@ include file="styles-and-js-link.jsp"%>

<script src="js/reset.js"></script>

<title>:: Reset Password | ${ resetuser.name } | We Care ::</title>
</head>
<body>
	
	<!-- redirect to login page if tried direct access -->
	<c:if test="${ session != null }">
		<c:redirect url="login.jsp">
		</c:redirect>
	</c:if>
	
	<%@ include file="nav.html" %>
	
	<div id="rest">
		<div id="content">
			<form id="reset-pass">
				<input type="hidden" name="user-id" value="${ resetuser.uID }">

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
		<!-- content -->
	</div>
	<!-- rest -->
</body>

</html>