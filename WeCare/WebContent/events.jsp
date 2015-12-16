<%-- <%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<%@ page import="com.data.DataAccessObject" %>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	
	<!-- jQuery Marquee with CSS3 Support - by Aamir Afridi
		Ref: 
		https://plugins.jquery.com/marquee/
		http://www.jqueryscript.net/demo/Text-Scrolling-Plugin-for-jQuery-Marquee/
		https://github.com/aamirafridi/jQuery.Marquee
		http://aamirafridi.com/jquery/jquery-marquee-plugin#examples 
	-->
	
	
</head>
<body> --%>

<!-- importing tags of JSTL -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<div class="events">
	<!-- getting the DataAccessObject class to be used in the page using
				java beans -->
	<jsp:useBean id="dao" class="com.data.DataAccessObject" scope="page"></jsp:useBean>
	<jsp:useBean id="misc" class="com.data.Misc" scope="page"></jsp:useBean>
	<jsp:useBean id="today" class="java.util.Date" scope="page"></jsp:useBean>
	<!-- creating a Date object with current date and time -->

	<c:catch var="noEvents">
		<c:set var="events" value="${ dao.getEvents(5) }"></c:set>
	</c:catch>
	<%-- ${ events == null } --%>

	<div class="marquee">

		<c:if test="${ noEvents == null }">
			<!-- if exception did not occur -->
			<c:forEach items="${ events }" var="event">

				<div class="event">

					<a href="GetEvent?id=${ event.getId() }">

						<table>
							<thead>
								<tr>
									<th colspan="2">${ event.getName() } <span
										class="info-small"> <%-- <c:choose>
									<c:when test="${ days < 0 }">
										<span style="color: red;">Completed</span>
									</c:when>
									<c:when test="${ days == 0 }">
										<span style="color: green;">Today!</span>
									</c:when>
									<c:otherwise>
										${ days } days to go
									</c:otherwise>
								</c:choose> --%> <c:catch var="eventCompleted">
									${misc.daysBetween(today, event.getTime())}
								</c:catch> <c:if test="${ eventCompleted != null }">
												<span style="color: red;">${ eventCompleted.message }</span>
											</c:if>
									</span>
									</th>
								</tr>
							</thead>
							<tbody>
								<tr>
									<td>On:</td>
									<td class="event-detail"><fmt:formatDate
											value="${ event.getTime() }" pattern="dd MMM, yyyy" /> at <fmt:formatDate
											value="${ event.getTime() }" pattern="HH:mm" /> Hrs</td>
								</tr>
								<tr>
									<td>At:</td>
									<td class="event-detail">${ event.getVenue() }</td>
								</tr>
							</tbody>
						</table>

					</a>
				</div>
			</c:forEach>
		</c:if>

		<c:if test="${ noEvents != null }">
			<!-- if no events found -->
			<span
				style="color: red; width: 200px; margin-left: auto; margin-right: auto; padding: 30px;">${ noEvents.message }</span>
		</c:if>
	</div>
	<div id="all-events" class="info-small">
		<a href="ListEvents">Show all events</a>
	</div>
</div>
<!-- events -->

<link rel="stylesheet" href="css/events.css">
<link
	href='http://fonts.googleapis.com/css?family=Roboto:400,400italic,300,300italic'
	rel='stylesheet' type='text/css'>

<!-- <script type="text/javascript" src="src-scripts/jquery.min.js"></script> -->

<script type="text/javascript" src="src-scripts/jquery.marquee.min.js"></script>
<script type="text/javascript" src="js/events.js"></script>
<!-- </body>

</html> -->