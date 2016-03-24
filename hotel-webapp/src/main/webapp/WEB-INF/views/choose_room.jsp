<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Wybierz pokój</title>
</head>
<body>
<h2>Dostępne pokoje</h2>
<br><br>

Pokój nr

<select id="roomNumber">
 <c:forEach items="${suitableRooms}" var="room">
 
  <option value="${room.roomNumber}">${room.roomNumber}</option>

</c:forEach>
</select>

<br><br>

<input type="button" id="accommodate" style="width: 200px" value="Zakwateruj" onclick="accommodate()">
<input type="button" id="accommodation" style="width: 200px" value="Cofnij" onclick="back()">
<script>
function accommodate(){
	var i =document.getElementById("roomNumber").selectedIndex;
	var val = "accommodate_" + ${reservation.reservationId} + "_nr_" + document.getElementById("roomNumber").options[i].text;
	//alert("Zakwaterowano w pokoju numer: " + document.getElementById("roomNumber").options[i].text);
	window.location.replace(val);
}
function back(){
	var l=  "reservation_details" + "_" + ${reservation.reservationId};
	window.location.replace(l);
}
</script>
</body>
</html>