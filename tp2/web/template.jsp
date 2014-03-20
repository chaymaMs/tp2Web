<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML>
<html>
<head>
<title>${param.title}</title>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/resources/style.css" />
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/resources/bootstrap.min.css" />
</head>
<body>
	
        <jsp:include page="header.jsp"/>

	<jsp:include page="${param.content}.jsp"/>
	
<%--	<jsp:include page="footer.jsp"/> --%>
	
        <!-- Scripts Jquery et Bootstrap -->
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/bootstrap.min.js" ></script>
	
	
</body>
</html>
