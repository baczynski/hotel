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
		<h1>Dane dotyczące pobytu</h1>
		<%=request.getAttribute("busy") != null ? "Brak miejsc w wybranym terminie" : "" %>
		<%=request.getAttribute("beforeToday") != null ? "Wybierz późniejszy termin" : "" %>
	<form method="POST" action="personal_details" onreset="return confirm('Czy na pewno chcesz wyczyścić wprowadzone dane?')">
		<fieldset>
			<legend></legend>
			<input type="number" name="peopleNumber" id="peopleNumber" min="2" max="5" value='<%=request.getParameter("peopleNumber")!=null ? request.getParameter("peopleNumber") : "" %>' required>
			<label for="peopleNumber">
				<abbr title="Pole wymagane">*</abbr>
				Liczba osób
			</label>
			<br />
			<input type="date"  name="startVisitingDate" id="startVisitingDate" style='<%=request.getParameter("startVisitingDate") == null ? "border:1px solid black" : "border:1px solid red" %>' required/>
			<label for="startVisitingDate">
				<abbr title="Pole wymagane">*</abbr>
				Początek pobytu
			</label>
			<br />
			<input type="number" name="visitLength" id="visitLength" min="1" max="30"  value='<%=request.getParameter("visitLength") != null ? request.getParameter("visitLength") : ""%>' required>
			<label for="visitLength">
				<abbr title="Pole wymagane">*</abbr>
				Długość pobytu
			</label> 
			<br /><br>
			<b>Widok z okna</b><br />
			<input type="radio" name="side" value="park" > Widok na park<br>
  			<input type="radio" name="side" value="sea"> Widok na morze<br>
  			<input type="radio" name="side" value="any" checked> Dowolny<br><br><br>
  			<input type="checkbox" name="reservationRegulations" value="reservationRegulations" required> Akceptuję regulamin rezerwacji<br><br>
  			<input type="checkbox" name="hotelRegulations" value="hotelRegulations" required> Akceptuję regulamin hotelu<br> <br><br>
			<input type="reset" value="Wyczyść" />
			<input type="button" onclick="history.go(-1);return true;" value="Wstecz">
			<input type="submit" value="Dalej" />
		</fieldset>
	</form>
</body>
</html>