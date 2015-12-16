<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<!--  PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd"> -->
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<%@ include file="styles-and-js-link.jsp"%>
<link rel="stylesheet" href="css/signup.css" />
<title>:: Sign Up | We Care ::</title>
</head>
<body>
	<%@ include file="nav.html"%>

	<div id="rest">
		<!-- <div id="left"></div> -->
		<div id="content">

			<div class="stream">
				<div id='msgErr' class="center" style="color: red;">
					<%!//variables
	String msg, uName, sex, city, uID, email;

	//methods
	public String checkCity(String cityName) {
		return (city != null && city.equals(cityName)) ? "selected" : "";
	}%>
					<%
						msg = (String) request.getAttribute("msg");

						if (msg != null) {
							out.println(msg);
						}

						//to retain form values entered
						uName = (String) request.getAttribute("uname");
						sex = (String) request.getAttribute("sex");
						city = (String) request.getAttribute("city");
						email = (String) request.getAttribute("email");
						/*out.println(city);*/
						uID = (String) request.getAttribute("uid");
					%>
				</div>
				<form name="registration" action="Registration" method="post">
					<table class="table-center">
						<thead>
							<tr>
								<td class="center" colspan="2"><h2>Registration Form</h2></td>
							</tr>
						</thead>

						<tr>
							<td>Name</td>
							<td><input type="text" name="user-name" required
								value=<%=(uName != null) ? uName : ""%>></td>
						</tr>
						<tr>
							<td>Gender</td>
							<td><input type="radio" name="sex" value="Male"
								<%=(sex == null || sex.equals("Male")) ? "checked" : ""%>>Male
								<input type="radio" name="sex" value="Female"
								<%=(sex != null && sex.equals("Female")) ? "checked" : ""%>>Female
							</td>
						</tr>
						<tr>
							<td>E-mail</td>
							<td><input type="text" name="email" required></td>
						<tr>
							<td>City</td>
							<td><select name="city" required>
									<option value=""
										<%=(city == null || city.equals("")) ? "selected" : ""%>>Select
										City</option>
									<option value="Hyderabad" <%=checkCity("Hyderabad")%>>Hyderabad</option>
									<option value="Bangalore" <%=checkCity("Bangalore")%>>Bangalore</option>
									<option value="Chennai" <%=checkCity("Chennai")%>>Chennai</option>
									<option value="Delhi" <%=checkCity("Delhi")%>>Delhi</option>
									<option value="Pune" <%=checkCity("Pune")%>>Pune</option>
							</select></td>
						</tr>
						<tr>
							<td>Required User ID</td>
							<td><input type="text" name="user-id" required
								value=<%=(uID != null) ? uID : ""%>></td>
						</tr>
						<tr>
							<td>Password</td>
							<td><input type="password" name="password" required /></td>
						</tr>
						<tr>
							<td>Retype Password</td>
							<td><input type="password" name="re-password" required /></td>
						</tr>
						<!-- <tr>
							<td>Security Question</td>
							<td><select name="sec-q" required>
									<option value="">Select a question</option>
									<option value="What is your mother's maiden name?">What
										is your mother's maiden name?</option>
									<option value="What is your first pet's name?">What is
										your first pet's name?</option>
									<option value="What is your first school's name?">What
										is your first school's name?</option>
									<option value="Who is your favourite teacher?">Who is
										your favourite teacher?</option>
									<option value="What is your first mobile used?">What
										is your first mobile used?</option>
							</select></td>
						</tr>
						<tr>
							<td>Answer</td>
							<td><input size="50" type="text" name="sec-a" required/></td>
						</tr> -->

						<tr>
							<td class="center" colspan="2"><button type="submit"
									class="button">Sign Up</button></td>
						</tr>
					</table>
				</form>
				<!-- <div id="pass-validation"></div> -->
			</div>
		</div>
	</div>

	<!-- <script src="js/validate.js"></script> -->
</body>
</html>