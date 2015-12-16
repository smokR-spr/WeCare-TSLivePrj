<!-- <!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<script type="text/javascript" src="src-scripts/jquery-1.8.0.min.js"></script>
	
	<script type="text/javascript" src="src-scripts/jquery-ui.js"></script>
	<script type="text/javascript" src="src-scripts/jquery.nicescroll.js"></script>
	
	
</head>
<body>
 -->

<!-- taglibs for JSTL -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%><!-- forEach loop -->
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%><!-- to format the date -->

<%@ include file="user-validation.jsp" %>

<c:set var="isAdmin" value='${ user.getuID() == "rahul.admin" }' />

<div id="left">
	<span>Welcome <strong>${user.getuID()}</strong>!
	</span>

	<!-- navigation menu -->
	<ul>
		<!-- search bar -->
		<!-- <li><a href="#">Search Topics</a></li> -->
		<li>
			<!-- <form id="search-form" action="/search.jsp" class="search-wrapper cf"> -->
			<form id="search-form" class="search-wrapper cf">
				<div id="search-bar">
					<input id="search-str" name="search-str" type="text"
						placeholder="Search here..." required>
					<!-- <button type="submit"><div></div></button> -->
					<div class="mag-glass">
						<div></div>
					</div>
					<!-- css styling is done for these two divs -->
				</div>
				<div id="search-results">
					<ul>
					</ul>
				</div>
			</form>
		</li>

		<li><a id="list-topics" href="ListTopics">List Topics</a></li>
		<!-- should go through the servlet 'ListTopics' -->
		<li><a id="user-topics" href="UserTopics">My Topics</a></li>
		<!-- should go through the servlet 'UserTopics' -->
		<li><a id="add-topic" href="add-topic.jsp">Add Topic</a></li>
		<c:if test="${ isAdmin }">
			<!-- <li>
					<div class="dropdown">
						<a class="account admin">Admin</a>
						<div class="submenu">
							<ul class="root">
								<li><a id="add-event" href="#">Add Event</a></li>
								<li><a id="delete-events" href="#">Delete Event</a></li>
							</ul>
						</div>
					</div>
				</li> -->
			<li><a id="delete-events-but" class="admin" href="ListEvents">List
					Events</a></li>
			<li><a id="add-event-but" class="admin">Add Event</a></li>
			<li><a id="upload-but" class="admin" href="ListUploads">Upload
					Files</a></li>
			<li id="mail-link"><img src="images/email-send-icon.png" alt="mail" /></li>
		</c:if>
	</ul>
</div>

<div id="overlay-back"></div>

<c:if test="${ isAdmin }">
	<!-- POPUP FOR ADD EVENT -->
	<div id="add-event" class="table-center popup">
		<div>${adderr}</div>
		<form id="add-event-form" name="add-event-form" action="AddEvent"
			method="post"
			onSubmit="return confirm('Are you sure you want to ADD this event?')">
			<table class="table-center">
				<thead>
					<tr>
						<th colspan="3">Add Event</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td>Event Name</td>
						<td>:</td>
						<td><input id="event-name" name="event-name" type="text"
							placeholder="Enter name of the event" required></td>
					</tr>
					<tr>
						<td>When</td>
						<td>:</td>
						<td>
							<!-- <div id="date_picker"></div> --> <input id="event-when"
							name="event-when" type="text" placeholder="Select date and time"
							required value="">
						</td>
					</tr>
					<tr>
						<td>Where</td>
						<td>:</td>
						<td><input id="event-where" name="event-where" type="text"
							placeholder="Specify Venue" required></td>
					</tr>
					<tr>
						<td>Description</td>
						<td>:</td>
						<td><textarea id="event-desc" name="event-desc"
								placeholder="Describe the event" required></textarea>
					</tr>
					<tr>
						<td colspan="3" class="center"><button class="button"
								type="submit">Add Event</button>
				</tbody>
			</table>
		</form>
	</div>
	<!-- add event -->

	<!-- POPUP FOR EMAIL -->
	<div id="email" class="table-center popup">
		
		<div id="sending-email">Sending email... Please wait...</div>
		<form action="SendEmail" method="post">
			<table class="table-center">
				<thead>
					<tr>
						<th colspan="2">Send E-mail</th>
					</tr>
				</thead>
				<tr>
					<td width="50%">Recipient address</td>
					<td><input type="text" name="recipient" size="50" required/></td>
				</tr>
				<tr>
					<td>Subject</td>
					<td><input type="text" name="subject" size="50" required/></td>
				</tr>
				<tr>
					<td>Content</td>
					<td><textarea rows="10" cols="39" name="content" required></textarea></td>
				</tr>
				<tr>
					<td colspan="2" align="center"><button class="button"
							type="submit">Send</button></td>
				</tr>
			</table>

		</form>
	</div>

</c:if>
<!-- is admin -->

<!-- </body> -->

<!-- datetime picker -->
<link rel="stylesheet" href="css/left.css">
<!-- <script type="text/javascript" src="src-scripts/jquery.min.js"></script> -->
<script type="text/javascript" src="src-scripts/jquery-1.7.2.min.js"></script>
<!-- <script type="text/javascript" src="src-scripts/jquery.dtpicker.js"></script> -->
<link rel="stylesheet" href="css/jquery.dtpicker.css" />

<script type="text/javascript" src="js/left.js"></script>

<!-- </html> -->