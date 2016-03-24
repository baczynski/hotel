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
<c:if test="${!reservation.confirmed}"><span id="tab"><input type="button" id="${reservation.reservationId}" style="height: 40px;"  value="Potwierdź" onclick="goToReservation(this.id)"></span></c:if>
<br><br><br><br><br>
<c:if test="${toEarly}">
Data rozpoczęcia pobytu jest późniejsza niż obecna!!!
</c:if>
<c:if test="${toLate}">
Klient się spóźnił!!!
</c:if>
<c:if test="${accommodationState != 'EVICTED'}">
<table>
<c:if test="${accommodated}">
<tr>
<td><input type="checkbox" id="damaged" name="damaged" value="damaged" onchange="damageChange()">Wykryto uszkodzenia na kwotę: 
</td>
<td>
<input type="number" id="damageValue" name="damageValue" disabled>
</td>
</tr>
</c:if>
<tr>
<td>
<c:if test="${!accommodated}"><input type="button" id="accommodation" style="width: 250px"  value="Wybierz pokój dla klienta" onclick="choose_room()"></c:if>
<c:if test="${accommodated}"><input type="button" id="accommodation" style="width: 250px" value="Wykwateruj" onclick="evict()"></c:if>
</td>
<td>
<c:if test="${!accommodated}"><input type="button" id="accommodation" style="width: 250px" value="Modyfikacja" onclick="modify()"></c:if>
<c:if test="${accommodated}"><input type="button" id="accommodation" style="width: 250px" value="Przedłuż pobyt" onclick="prolong()"></c:if>
</td>
<td>
<input type="button" id="accommodation" style="width: 250px" value="Cofnij" onclick="back()">
</td>
</tr>
</table>
</c:if>
<c:if test="${accommodationState == 'EVICTED'}">
<c:if test="${damage != null && damage.value >0}"> Były uszkodzenia na kwotę: ${damage.value} zł<br>
<c:if test="${damage.clientPay}">Klient pokrył straty 
</c:if>
<c:if test="${!damage.clientPay}">Sprawa trafiła na policję
</c:if>
<br><br>
 </c:if>
<input type="button" id="accommodation" style="width: 250px" value="Cofnij" onclick="back()">
</c:if>
<script>
function damageChange(){
	if(document.getElementById("damaged").checked){
		document.getElementById("damageValue").disabled = false;
	}
	else{
		document.getElementById("damageValue").disabled = true;
	}
}
function goToReservation(btn){
	var l= "reservation_details" + "_" + btn + "_" + "c";
	window.location.replace(l);
}
function back(){
	var l=  "reservations";
	window.location.replace(l);
}
function choose_room(){
	var l= "choose_room" + ${reservation.reservationId};
	window.location.replace(l);
}
function evict(){
	var l= "evict" + "_" + ${reservation.reservationId};
	if(document.getElementById("damaged").checked && parseFloat(document.getElementById("damageValue").value)>0){
		l+= "_" + parseFloat(document.getElementById("damageValue").value);
	}
	else{
	alert("Wykwaterowano");
	}
	window.location.replace(l);
}
function modify(){
}
function prolong(){
	var l= "prelong" + "_" + ${reservation.reservationId};
	window.location.replace(l);
}
</script>
</body>
</html>