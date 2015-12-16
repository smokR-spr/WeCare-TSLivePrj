<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html> <!-- PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd"> -->
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<%@ include file="styles-and-js-link.jsp" %>
	<link rel="stylesheet" href="css/topics.css">
	
	<!-- <script type="text/javascript" src="src-scripts/j	query.min.js"></script> -->
	<script type="text/javascript" src="js/my-topics.js"></script>
		
	<script type="text/javascript">
		$(function(){
			$('#left a').removeClass('active-link');
			$('#left #user-topics').addClass('active-link');
		});
	</script>
	
	<title>:: My Topics | ${user.getName()} | We Care ::</title>
</head>
<body>
	<!-- importing tags of JSTL -->
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
	<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
	
	<%-- <%@ include file="user-validation.jsp" %> --%>
		<%-- <c:otherwise> --%>
			<%@ include file="left.jsp" %>
			<%@ include file="loggedin-nav.jsp" %>
		
			<div class="rest">
				<div class="content">
					
					<%@ include file="events.jsp" %>
					
					<%-- <div class="center" style="color: green;">
						${msg}
					</div> --%>
					<div class="info" style="color: red;">
						${msgerr}
					</div>
					<div id="topic-list">
						<!-- <form id="hidden"> -->
						<c:if test="${msgerr == null}">
							<table class="topics">
							<thead>
								<tr>
									<th colspan="6">My Topics</th>
								</tr>
								<tr>
									<th>Sl. No.</th>
									<th>Topic Name</th>
									<th>Comment</th>
									<th>Date</th>
									<th>Delete</th>
									<th>Edit</th>
								</tr>
							</thead>
							
							<tbody>
								<%! int i = 1; %>
								
								<c:forEach items="${ mytopicsArr }" var="mytopic">
									<tr>
										<td id="sl-no" style="width: 30px;"><%= i %></td>
										<td id="topic-name">${ mytopic.getTopicName() }</td>
										<td id="comment">${ mytopic.getComment() }</td>
										<td id="date" style="width: 120px;"><fmt:formatDate value="${ mytopic.getDate() }" pattern="dd MMM, yyyy HH:mm:ss" /></td>
										<%-- <td style="display: none" id="topic-id-<%=i %>">${ mytopic.getTopicID() }</td> --%>
										<!-- Creating a form to delete or edit separately in a table data -->
										<%-- <td>${ mytopic.getTopicID() }</td> --%>
										<td style="width: 63px;">
											<form action="DeleteTopic" onSubmit="return confirm('Are you sure you want to DELETE this topic?')">
											<input type="hidden" name="topic-id" value="${ mytopic.getTopicID() }">
												<button class="button" type="submit">Delete</button>
											</form>
										</td>
										<td id="edit-window" style="width: 47px;">
											<form id="edit-button-form-<%= i %>" action="">
												<input type="hidden" id="edit-topic-id-<%= i %>" name="topic-id" value="${ mytopic.getTopicID() }">
												<button id="edit-<%= i %>" class="button hidden-form">Edit</button>
											</form>
											
											<div id="edit-div" class="hidden">
												<div>${editerr}</div>
												<form id="edit-topic" name="edit-topic" action="EditTopic" onSubmit="return confirm('Are you sure you want to UPDATE the topic?')">
													<input type="hidden" id="edit-topic-id" name="edit-topic-id">
													<input type="hidden" id="edit-topic-name" name="edit-topic-name">
													<%-- <input type="hidden" name="topic-id" value=${ mytopic.getTopicID() }> --%>
													<table class="table-center">
														<thead>
															<tr>
																<th colspan="3">Edit Topic</th>
															</tr>
														</thead>
														<tbody>
															<tr>
																<td>Topic Name</td>
																<td>:</td>
																<td id="edit_topic-name"><%-- test ${topic.getName()} --%></td>
															</tr>
															<tr>
																<td>Comment</td>
																<td>:</td>
																<td>
																	<textarea id="edit_comment" name="edit_comment" placeholder="Comment"><%-- ${topic.getComment()} --%></textarea>
																</td>
															</tr>
															<tr>
																<td colspan="3" class="center"><button class="button" type="submit">Update</button>
														</tbody>
													</table>
												</form>
											</div><!-- #edit-div -->
											<!-- <form>action="edit-topic.jsp"> -->
												
												<!-- <button id="edit" class="button">Edit</button> -->
											<!-- </form> -->
										</td>	
									</tr>
									<% i++; %>
								</c:forEach>
								<%-- <%	
									Topics[] topics = (Topics[])request.getAttribute("mytopicsArr");
									/*out.println(topics == null);
									out.println(topics.length);*/
									for(int i = 0; i < topics.length; i++) { %>
										<tr>
											<td><%= topics[i].getDate() %></td>
											<td><%= topics[i].getTopicName() %></td>
											<td><%= topics[i].getComment() %></td>
										</tr>
									<%}
								%> --%>
							</tbody>
							</table>
						</c:if>
						
						<!-- </form> -->
					</div><!-- #topic-list -->
				</div><!-- #content -->
				
				<!-- storing the number of topics loaded onto the page -->
				<p id="topic-nos" class="hidden"><%= i-1 %></p>
				<% i = 1; %>
			</div><!-- #rest -->
		
	<%-- 	</c:otherwise>
	</c:choose> --%>
</body>
</html>