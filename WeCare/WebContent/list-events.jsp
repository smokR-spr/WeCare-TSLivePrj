<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<!-- Stylesheets -->
<%@ include file="styles-and-js-link.jsp"%>
<link rel="stylesheet" href="css/topics.css">

<script type="text/javascript" src="src-scripts/jquery.min.js"></script>
<script type="text/javascript">
	$(function() {
		$('#left a').removeClass('active-link');
		$('#left #delete-events-but').addClass('active-link');
		$('#left #delete-events-but').addClass('admin');
	});
</script>

<title>:: List Events | ${user.getName()} | We Care ::</title>
</head>
<body>

	<!-- taglibs for JSTL -->
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%><!-- forEach loop -->
	<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%><!-- top format the date -->

	<!-- navigation menu -->
	<%@ include file="left.jsp"%>
	<%@ include file="loggedin-nav.jsp"%>

	<c:set var="isAdmin" value='${ user.getuID() == "rahul.admin" }' />


	<div class="rest">
		<div class="content">

			<%@ include file="events.jsp"%>

			<div class="info" style="color: red;">${msgerr}</div>

			<c:if test="${ eventslist != null }">
				<div id="topic-list">
					<!-- list of events -->

					<%-- <c:if test="${ isAdmin }">
					<form id="events-delete" name="events-delete" action="DeleteEvents"
						onSubmit="return confirm('Are you sure you want to DELETE this event?')">
				</c:if> --%>

					<table class="topics">
						<thead>
							<tr>
								<th colspan="6" class="center">Events</th>
							</tr>
							<tr>
								<th>Sl. No.</th>
								<th>Event Name</th>
								<th>On</th>
								<th>Venue</th>

								<!-- if admin -->
								<c:if test='${ isAdmin }'>
									<th>Delete</th>
								</c:if>
								<!-- admin close -->
							</tr>
						</thead>
						<tbody>


							<!-- needs a for loop to populate the list using 'events' attribute from the servlet -->
							<c:forEach items="${ eventslist }" var="event" varStatus="count">
								<tr>
									<td class="center">${ count.index + 1 }</td>
									<td>${ event.getName() }</td>
									<td><fmt:formatDate value="${ event.getTime() }"
											pattern="dd MMM, yyyy HH:mm:ss" /> <span class="info-small"
										style="margin-left: 20px;"> <c:catch
												var="eventCompleted">
												<span> ${misc.daysBetween(today, event.getTime())}</span>
											</c:catch> <c:if test="${ eventCompleted != null }">
												<span style="color: red;">${ eventCompleted.message }</span>
											</c:if>
									</span></td>

									<td>${ event.getVenue() }</td>

									<!-- if admin -->
									<c:if test="${ isAdmin }">
										<%-- <td class="admin center"><input id="event-delete-cb" name="event-delete-cb" type="checkbox"
										value="${ event.getId() }"></td> --%>
										<td class="admin center"><a id="delete-event"
											onClick="return confirm('Are you sure you want to DELETE this event?')"
											href="DeleteEvent?id=${ event.getId() }">Delete</a></td>
									</c:if>
									<!-- admin close -->

								</tr>
							</c:forEach>

						</tbody>
					</table>
					<%-- <c:if test="${ isAdmin }">
							<!-- <td colspan="5" style="text-align: right; border: none; border-top: 2px solid gray;" class="admin"> -->
							<div class="center"><button class="button">Delete Selected Events</button></div>
							
							</form>
						</c:if> --%>
				</div>
				<!-- topic-list -->
			</c:if>

		</div>
		<!-- content -->
	</div>
	<!-- rest -->

</body>
</html>
