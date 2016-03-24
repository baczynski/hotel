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
<td style="border-collapse: collapse; border: 1px solid black;">
<input type="date" name="visitingStart" id="visitingStart" value="<fmt:formatDate value="${reservation.startVisitingDate.getTime()}" pattern="yyyy-MM-dd" />" readonly/>
</td>
</tr>
<tr style="border-collapse: collapse; border: 1px solid black;">
<td style="border-collapse: collapse; border: 1px solid black;">Długość pobytu</td>
<td style="border-collapse: collapse; border: 1px solid black;">${reservation.visitLength}</td>
</tr>
<tr style="border-collapse: collapse; border: 1px solid black;">
<td style="border-collapse: collapse; border: 1px solid black;">Koniec pobytu</td>
<td style="border-collapse: collapse; border: 1px solid black;" >
<input type="date" name="visitingEnd" id="visitingEnd" value="<fmt:formatDate value="${visitingEnd.getTime()}" pattern="yyyy-MM-dd" />" readonly/>
</td>
</tr>
<tr style="border-collapse: collapse; border: 1px solid black;">
<td style="border-collapse: collapse; border: 1px solid black;">Przedłużam do</td>
<td style="border-collapse: collapse; border: 1px solid black;">
<input type="date" name="newVisitingEnd" id="newVisitingEnd" onchange="endChanged()" value="<fmt:formatDate value="${visitingEnd.getTime()}" pattern="yyyy-MM-dd" />" max="2025-12-31" min="<fmt:formatDate value="${visitingEnd.getTime()}" pattern="yyyy-MM-dd" />">
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

<table>
<tr>
<td style="width: 200px">Całkowita liczba dni </td>
<td><input type="number" name="totalDays" id="totalDays" value="${reservation.visitLength}" readonly/>
</td>
</tr>
<tr>
<td style="width: 200px">Cena </td>
<td><input type="number" name="totalDays" id="totalDays" value="340" readonly/> zł</td>
</tr>
</table>
<br><br>
<input type="button" id="accommodation" style="width: 250px" value="Akceptuj" onclick="acc()">
<input type="button" id="accommodation" style="width: 250px" value="Cofnij" onclick="back()">
<script>
function acc(){
	var nve =  document.getElementById("newVisitingEnd").value;
	var l= "accept" + "_" + ${reservation.reservationId} + "_" + nve;
	window.location.replace(l);
}
function back(){
	var l= "reservation_details" + "_" + ${reservation.reservationId};
	window.location.replace(l);
}
function endChanged(){
	var s = parseDate(document.getElementById("visitingStart").value);
	var n = parseDate(document.getElementById("newVisitingEnd").value);
	var o = parseDate(document.getElementById("visitingEnd").value);
	if(o>n){
		(document.getElementById("newVisitingEnd")).value=o;
	}
	var dd = daydiff(s,n);
	if(dd>30){
		document.getElementById("newVisitingEnd").value=document.getElementById("visitingEnd").value;
		document.getElementById("totalDays").value=${reservation.visitLength};
	}
	else{
		document.getElementById("totalDays").value=dd;
	}
	if((document.getElementById("newVisitingEnd")).value==""){
		(document.getElementById("newVisitingEnd")).value=document.getElementById("visitingEnd").value ;
	}
}

function parseDate(str) {
    var mdy = str.split('-')
    return new Date(mdy[0], mdy[1]-1, mdy[2]);
}

function daydiff(first, second) {
    return Math.round((second-first)/(1000*60*60*24));
}
</script>
</body>
</html>