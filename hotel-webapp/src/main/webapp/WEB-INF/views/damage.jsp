<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
   <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Dane rezerwacji</title>
<style>
 table, th, td {
}
th, td {
    padding: 5px;
}

#info {
   width:1000px;
}
#info_left_col {
   float:left;
   width:400px;
}
#info_right_col {
   float:right;
   width:400px;
}
#options{
width:1000px;
}
#opitons_left_col {
   float:left;
   width:400px;
}
#options_right_col {
   float:right;
   width:400px;
}
#tab { display:inline-block; 
       margin-left: 20px; }
</style>
</head>
<body>
<h2 style="text-align: center;">Dane rezerwacji</h2>
<br><br>


<div id="info">
<table id="info_left_col" style="border-collapse: collapse; border: 1px solid black;">
<tr style="border-collapse: collapse; border: 1px solid black;"><th colspan="2">Rezerwacja</th></tr>
<tr style="border-collapse: collapse; border: 1px solid black;">
<td style="border-collapse: collapse; border: 1px solid black;">Numer rezerwacji</td>
<td style="border-collapse: collapse; border: 1px solid black;">${reservation.reservationId}</td>
</tr>
<tr style="border-collapse: collapse; border: 1px solid black;">
<td style="border-collapse: collapse; border: 1px solid black;">Początek pobytu</td>
<td style="border-collapse: collapse; border: 1px solid black;"><fmt:formatDate value="${reservation.startVisitingDate.getTime()}" pattern="yyyy-MM-dd" /></td>
</tr>
<tr style="border-collapse: collapse; border: 1px solid black;">
<td style="border-collapse: collapse; border: 1px solid black;">Długość pobytu</td>
<td style="border-collapse: collapse; border: 1px solid black;">${reservation.visitLength}</td>
</tr>
<tr style="border-collapse: collapse; border: 1px solid black;">
<td style="border-collapse: collapse; border: 1px solid black;">Data rezerwacji</td>
<td style="border-collapse: collapse; border: 1px solid black;"><fmt:formatDate value="${reservation.fillFormDate.getTime()}" pattern="yyyy-MM-dd"/></td>
</tr>
<tr style="border-collapse: collapse; border: 1px solid black;">
<td style="border-collapse: collapse; border: 1px solid black;">Liczba osób</td>
<td style="border-collapse: collapse; border: 1px solid black;">${reservation.peopleNumber}</td>
</tr>
<tr style="border-collapse: collapse; border: 1px solid black;">
<td style="border-collapse: collapse; border: 1px solid black;">Widok z okna</td>
<td style="border-collapse: collapse; border: 1px solid black;">
<c:if test="${reservation.roomSide eq 'any'}"><c:out value="Dowolny"/></c:if>
<c:if test="${reservation.roomSide eq 'sea'}"><c:out value="Morze"/></c:if>
<c:if test="${reservation.roomSide eq 'park'}"><c:out value="Park"/></c:if>
</td>
</tr>
<tr style="border-collapse: collapse; border: 1px solid black;">
<td style="border-collapse: collapse; border: 1px solid black;">Potwierdzono</td>
<td style="border-collapse: collapse; border: 1px solid black;">
<c:if test="${reservation.confirmed}"><c:out value="Tak"/></c:if>
<c:if test="${!reservation.confirmed}"><c:out value="Nie"/></c:if>
</td>
</tr>	
</table>

<table id="info_right_col" style="border-collapse: collapse; border: 1px solid black;">
<tr><th>Dane klienta</th></tr>
<tr>
<td>${reservation.client.name} ${reservation.client.surname}</td>
</tr>
<tr>
<td>ul. ${reservation.client.address.streetAndNumber}</td>
</tr>
<tr>
<td>${reservation.client.address.zipCode} ${reservation.client.address.city}</td>
</tr>
<tr>
<td>${reservation.client.email}</td>
</tr>
<tr>
<td>${reservation.client.phoneNumber}</td>
</tr>
<tr>
<td><fmt:formatDate value="${reservation.client.dateOfBirth.getTime()}" pattern="yyyy-MM-dd"/></td>
</tr>
	
</table>
</div>

<br><br><br><br><br><br><br><br><br><br><br>
<br><br><br><br><br>
<h2>Wykryto straty na kwotę: ${damage} zł</h2>
<input type="button" id="clientWillPay" style="width: 250px" value="Klient pokrywa straty" onclick="clientPay()">
<input type="button" id="" style="width: 250px" value="Sprawa trafia na policję" onclick="callPolice()">
<input type="button" id="back" style="width: 250px" value="Cofnij" onclick="back()">
<script>
function clientPay(){
	var l=  "damage_" + ${reservation.reservationId} + "_" + ${damage} + "_" + "true";
	window.location.replace(l);
}
function callPolice(){
	var l=  "damage_" + ${reservation.reservationId} + "_" + ${damage} + "_" + "false";
	window.location.replace(l);
}
function back(){
	var l=  "reservation_details_" + ${reservation.reservationId};
	window.location.replace(l);
}
</script>
</body>
</html>