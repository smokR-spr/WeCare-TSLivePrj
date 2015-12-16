<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html> <!-- PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd"> -->
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<%@ include file="styles-and-js-link.jsp" %>
	<link rel="stylesheet" href="css/login-success.css">
	
	<!-- <script type="text/javascript" src="src-scripts/jquery.min.js"></script> -->
	<script type="text/javascript">
		$(function(){
			$('#left a').removeClass('active-link');
			$('#left #add-topic').addClass('active-link');
		});
	</script>
	
	<title>:: Add Topic | ${user.getName()} | We Care ::</title>
</head>
<body>
	<%@ include file="left.jsp" %>
	<%@ include file="loggedin-nav.jsp" %>

	<div class="rest">
		<div class="content">
		
		<%@ include file="events.jsp" %>
		
		<div class="stream">
			<div class="center" style="color: green;">
				${msg}
			</div>
			<div class="center" style="color: red;">
				${msgerr}
			</div>
			<div>
				<form id="addtopic" action="AddTopic">
				<table>
					<thead>
						<tr>
							<th>Add Topic</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td><input type="text" name="topic-name" placeholder="Topic Name" required></td>
						</tr>
						<tr>
							<td><textarea id="comment" name="comment" placeholder="Comment" required></textarea></td>
						</tr>
						<tr>
							<td class="center" colspan="3"><button type="submit" class="button">Add</button></td>
						</tr>
					</tbody>
				</table>
				</form>
			</div>
			</div>
		</div>
	</div>
	
	
</body>
</html>