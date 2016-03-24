<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@page language="Java"  contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Hotel</title>

<!-- Bootstrap -->
<link type="text/css"
	href="${pageContext.request.contextPath}/bootstrap/css/bootstrap.min.css"
	rel="stylesheet" />
<link rel="icon"
	href="${pageContext.request.contextPath}/resources/images/icon.jpg">
<link href="<c:url value="/resources/css/styles-glowna.css" />"
	rel="stylesheet">

<link href="<c:url value="/resources/css/font-awesome.min.css" />"
	rel="stylesheet">
<script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
<script
	src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>


<style>
.main-ad, #content>#right>.dose>.dosesingle, #content>#center>.dose>.dosesingle
	{
	display: none !important;
}
</style>
</head>
<body>
	<h2>Udało się zarezerwować pobyt. Jednocześnie prosimy o odwiedzenie podanego adresu e-mail w celu potwierdzenia rezerwacji</h2>
	
	<input type="button" onclick="location.href='index'" value="Przejdź na stronę główną">
</body>
</html>