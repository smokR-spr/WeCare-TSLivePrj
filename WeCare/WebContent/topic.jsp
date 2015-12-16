<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html> <!--  PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd"> -->
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	
	<link rel="stylesheet" href="css/layout.css">
	<link rel="stylesheet" href="css/topic.css">
	
	<script type="text/javascript" src="src-scripts/jquery.min.js"></script>
	<script type="text/javascript">
		$(function(){
			$('#left a').removeClass('active-link');
			$('#left #list-topics').addClass('active-link');
		});
	</script>
	
	<title>${ topic.getTopicName() } | ${ user.getName() } | We Care</title>
</head>
<body>
	<!-- taglibs for JSTL -->
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %><!-- forEach loop -->
	<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %><!-- top format the date -->
	
	
	<!-- navigation menu -->
	<%@ include file="loggedin-nav.jsp" %>
	<%@ include file="left.jsp" %>
	
	<div class="rest">
		<div class="content">
			
			<%@ include file="events.jsp" %>
			
			<div class="info" style="color: red;">
				${msgerr}
			</div>
			<div class="stream">
				<div class="topic-info">
					<div class="info">Topic name</div>
					<h1 title="Topic Name">${ topic.getTopicName() }</h1>
					<div class="author info" class="info">Added by ${ topic.getUID() }, on <fmt:formatDate value="${ topic.getDate() }" pattern="dd MMM, yyyy HH:mm:ss"/> Hrs.</div>
					<div class="author-comment">
						<div class="info">Author comment</div>
						<p>${ topic.getComment() }</p>
					</div>
				</div>
				<div class="comment window">
					<form id="user-comment" name="user-comment" action="AddUserComment">
						<input type="hidden" name="tid" value=${ topic.getTopicID() }>
						<input type="hidden" name="uid" value="${ user.getuID() }">
						<p class="info">Your comment</p>
						<textarea id="comment" name="comment" placeholder="Comment"></textarea>
						<button class="button" type="submit">comment</button>
					</form>
				</div>
				<c:forEach items="${ comments }" var="comment">
					<div class="comment">
						<span class="username author">${ comment.getUserID() } </span>
						<span class="info"><fmt:formatDate value="${ comment.getTime() }" pattern="dd MMM, yyyy HH:mm:ss"/> Hrs.</span>
							<p>${ comment.getComment() }</p>
					</div>
				</c:forEach>
			</div>
		</div><!-- content -->
	</div><!-- rest -->
	
</body>
</html>