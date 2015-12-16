<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<!--  PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd"> -->
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<link rel="stylesheet" href="css/layout.css">
<link rel="stylesheet" href="css/topics.css">
<link rel="stylesheet" href="css/topic.css">

<script type="text/javascript" src="src-scripts/jquery.min.js"></script>


<title>Upload | ${ user.getName() } | WeCare</title>
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

			<%@ include file="events.jsp"%>

			<div class="info" style="color: red;">${msgerr}</div>
			<div class="topic-info">
				<div id="upload-file">
					<form id="upload-form" action="SaveDocument" method="post"
						enctype="multipart/form-data">
						<table class="table-center">
							<tr>
								<th><h2>Upload files</h2></th>
							</tr>
							<tr>
								<td class="center">
									<!-- <div id="test"> --> <label id="attach1" class="add-file">
										<input type="file" name="attachment1" id="attachment1" /><span>Attach
											File</span>

								</label> <!-- </div> -->
								</td>
							</tr>
							<tr>
								<td class="center"><button type="submit" class="button">Upload
										File(s)</button></td>
							</tr>
						</table>
					</form>
				</div>
				<!-- upload-file -->
			</div>

			<c:if test="${ uploads != null }">
				<div id="topic-list">
					<!-- list of uploaded files -->
					<table style="table-layout: auto;" class="topics">
						<thead>
							<tr>
								<th colspan="2">Uploaded files</th>
							</tr>
							<tr>
								<th>Sl. No.</th>
								<th>File</th>
						</thead>
						<tbody>
							<%-- <tr>${ uploads }</tr> --%>
							<c:forEach items="${ uploads }" var="file" varStatus="count">
								<tr>
									<td class="center">${ count.index + 1 }</td>
									<%-- <td><a href="${ misc.getValidURL(file) }">${ file.name }</a></td> --%>
									<td><a href="DownloadDocument?f=${ misc.getValidParameterURI(file) }">${ file.name }</a></td>
								</tr>
								<%-- <tr><td>${ misc.getValidURL(file) }</td></tr> --%>
							</c:forEach>
						</tbody>
					</table>
				</div>
				<!-- topic-list : uploaded files list -->
			</c:if>
		</div>
		<!-- content -->
	</div>
	<!-- rest -->
</body>

<script type="text/javascript" src="js/upload.js"></script>

</html>