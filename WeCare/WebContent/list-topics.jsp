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
		$('#left #list-topics').addClass('active-link');
	});
</script>

<title>:: List Topics | ${user.getName()} | We Care ::</title>
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

			<c:if test="${ topics != null }">
				<div id="topic-list">
					<!-- list of topics -->
					<table class="topics">
						<thead>
							<tr>
								<th colspan="6" class="center">Topics</th>
							</tr>
							<tr>
								<th>Sl. No.</th>
								<th>Topic Name</th>
								<th>Comment</th>
								<th>Date</th>
								<th>Added By</th>

								<!-- if admin -->
								<c:if test='${ isAdmin }'>
									<th>Delete</th>
								</c:if>
								<!-- admin close -->
							</tr>
						</thead>
						<tbody>

							<!-- needs a for loop to populate the list using 'topics' attribute from the servlet -->
							<c:forEach items="${topics}" var="topic" varStatus="count">
								<tr>
									<td class="center">${ count.index + 1 }</td>
									<td><a href="TopicDetails?tid=${ topic.getTopicID() }">${ topic.getTopicName() }</a></td>
									<td>${ topic.getComment() }</td>
									<td><fmt:formatDate value="${ topic.getDate() }"
											pattern="dd MMM, yyyy HH:mm:ss" />
									<td>${ topic.getUID() }</td>

									<!-- if admin -->
									<c:if test="${ isAdmin }">
										<td class="admin">
											<form action="DeleteTopic"
												onSubmit="return confirm('Are you sure you want to DELETE this topic?')">
												<input type="hidden" name="topic-id"
													value="${ topic.getTopicID() }">
												<button class="button" type="submit">Delete</button>
											</form>
										</td>
									</c:if>
									<!-- admin close -->

								</tr>
							</c:forEach>

						</tbody>
					</table>
				</div>
				<!-- topic-list -->
			</c:if>
		</div>
		<!-- content -->
	</div>
	<!-- rest -->

</body>
</html>
