<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<!--  PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd"> -->
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<link rel="stylesheet" href="css/layout.css">
<link rel="stylesheet" href="css/topic.css">
<script type="text/javascript" src="src-scripts/jquery.min.js"></script>

<title>${ event.getName() }| ${ user.getName() } | We Care</title>
</head>
<body>
	<!-- taglibs for JSTL -->
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%><!-- forEach loop -->
	<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%><!-- top format the date -->


	<!-- navigation menu -->
	<%@ include file="loggedin-nav.jsp"%>
	<%@ include file="left.jsp"%>

	<div class="rest">
		<div class="content">
		
			<%@ include file="events.jsp" %>
			
			<div class="info" style="color: red;">
				${msgerr}
			</div>

			<div class="info" style="color: red;">${msgerr}</div>
			<div class="stream">
				<div class="topic-info">
					<div class="info">Event Name</div>
					<h1 title="Event Name">${ event.getName() }</h1>
					<div class="author info" class="info">
						On
						<fmt:formatDate value="${ event.getTime() }"
							pattern="dd MMM, yyyy HH:mm:ss" />
						Hrs.<br> At ${ event.getVenue() }
					</div>
					<div class="author-comment">
						<div class="info">Description</div>
						<p>${ event.getDescription() }</p>
					</div>
				</div>
			</div>
		</div>
		<!-- content -->
	</div>
	<!-- rest -->

	

</body>
</html>
