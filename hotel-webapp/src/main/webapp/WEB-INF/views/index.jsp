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
		<header>
		<h1>Witaj na stronie hotelu!</h1>
	</header>
	<section id="navigationSection">
		<header>
			<h4>Przejdź do</h4>
		</header>
		<nav>
			<a name="rezerwuj" href="reservation_form" style="font-size: 26px">Rezerwuj </a>
			<br>
			<a name="kwateruj" href="reservations" style="font-size: 26px">Kwateruj </a>
		</nav>
		<p></p>
	</section>
	<section id="articleSection">
		<header>
			<h3>O nas</h3>
		</header>
		<article>
		<header>
			<h5>HOTEL XXI WIEKU</h5>
		</header>
			<p class="article" id="article">
			Jesteśmy firmą świadczącą usługi hotelowe od 140 lat. Możemy pochwalić się zadowoleniem z pobytu co najmniej 98% naszych klientów.
			Dbamy, aby ciągle poprawiać jakość świadczonych przez nas usług.
			Sprawdź naszą ofertę a będziesz mógł się przekonać, że nasze ceny są niższe niż konkurencji. Organizujemy również imprezy okolicznościowe. Mają państwo możliwość wynajęcia całego hotelu.
			</p>
			<div id="cover">
			<img src = "resources/images/hotel.jpg"  alt ="Zdjęcie hotelu">
			</div>

		</article>
		<p></p>
	</section>

	<footer style="text-align: center;">
		Hotel 2016 &copy;
	</footer>
</body>
</html>