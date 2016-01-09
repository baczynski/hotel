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
<link href="<c:url value="/resources/css/styles.css" />"
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
		<h1>Dane osobowe rezerwującego</h1>
	<form method="POST" action="reservationForm.jsp" onsubmit="return confirm('Czy na pewno chcesz wysłać te dane?')"
						onreset="return confirm('Czy na pewno chcesz wyczyścić wprowadzone dane?')">
		<fieldset>
			<legend></legend>
			<input type="text" name="firstName" id="firstName" autofocus required onkeypress="namePressed(event)" />
			<label for="firstName">
				<abbr title="Pole wymagane">*</abbr>
				Imię
			</label> 
			<br />
			<input type="text" name="surname" id="surname" onblur="surnameBlur(this)" onfocus="surnameFocus(this)" />
			<label for="surname">
				<abbr title="Pole wymagane">*</abbr>
				Nazwisko
			</label>
			<br />
			<input type="text" name="streetAndNumber" id="streetAndNumber"  required/>
			<label for="streetAndNumber">
				<abbr title="Pole wymagane">*</abbr>
				Ulica i nr domu
			</label>
			<br />
			<input type="text" name="city" id="city"  required/>
			<label for="city">
				<abbr title="Pole wymagane">*</abbr>
				Miejscowość
			</label>
			<br />
			<input type="kod_pocztowy" pattern="^[0-9]{2}-[0-9]{3}$" required>
			<label for="city">
				<abbr title="Pole wymagane">*</abbr>
				Kod pocztowy
			</label>
			<br />
			<input type="email" name="email" id="email" autocomplete="off"  required/>
			<label for="email">
				<abbr title="Pole wymagane">*</abbr>
				E-mail
			</label>
			<br />
			<input type="tel" name="phoneNumber" id="phoneNumber" pattern="[0-9]{3}-[0-9]{3}-[0-9]{3}"  autocomplete="on" required />
			<label for="phoneNumber">
				<abbr title="Pole wymagane">*</abbr>
				Numer telefonu (w formacie 123-456-789)
			</label>
			<br />
			<input type="date" name="date" id="date"  required/>
			<label for="date">
				<abbr title="Pole wymagane">*</abbr>
				Data urodzenia
			</label>
			<br />
			<input type="reset" value="Anuluj" />
			<input type="submit" value="Wyślij" />
		</fieldset>
	</form>
</body>
</html>