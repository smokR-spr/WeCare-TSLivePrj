<%-- <%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
</head>
<body>
 --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<c:choose>
	<c:when test="${ user == null }">
		<%-- <jsp:forward page="/login.jsp">
				<jsp:param name="msgerr" value="Please login first."></jsp:param>
			</jsp:forward> --%>
		<c:redirect url="/login.jsp">
			<c:param name="msgerr" value="Please login first."></c:param>
		</c:redirect>
	</c:when>
</c:choose>
<!-- </body>
</html> -->