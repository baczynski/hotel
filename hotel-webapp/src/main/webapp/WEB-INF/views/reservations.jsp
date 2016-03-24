<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
   <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Rezerwacje</title>
<style>
table, th, td {
    
    border-collapse: collapse;
}
th, td {
	border: 1px solid black;
    padding: 15px;
}
</style>
</head>
<body>
<h2>Wyszukaj rezerwację</h2>
Wpisz numer rezerwacji lub nazwisko klienta
<form method="POST" action="search">
<input type="text" name="nrOrSurname" id="nrOrSurname" />
<input type="submit" value="Wyszukaj">	<br><br>
</form>	
	<c:if test="${!reservationsList.isEmpty() }">
	<table>
	<tr>
	<th>Numer rezerwacji</th>
	<th>Nazwisko</th>
	<th>Imię</th>
	<th>Liczba osób</th>
	<th>Początek pobytu</th>
	<th>Data zarezerwowania</th>
	</tr>
       <c:forEach items="${reservationsList}" var="reservation">
            <tr>
                <td><c:out value="${reservation.reservationId}"/></td>
                <td><c:out value="${reservation.client.surname}"/></td> 
                <td><c:out value="${reservation.client.name}"/></td> 
           		<td><c:out value="${reservation.peopleNumber}"/></td> 
           		<td><fmt:formatDate value="${reservation.startVisitingDate.getTime()}" pattern="yyyy-MM-dd" /></td> 
           		<td><fmt:formatDate value="${reservation.fillFormDate.getTime()}" pattern="yyyy-MM-dd"/></td>
           		<td style="border: 0px"><input type="button" id="${reservation.reservationId}"  value="Przejdź" onclick="goToReservation(this.id)"></td>
            </tr>
        </c:forEach>
    </table>
	</c:if>
	<c:if test="${busy}"><script>alert("Nie udało się przedłużyć terminu. Brak miejsc")</script></c:if>
	<c:if test="${busy!=null && !busy}"><script>alert("Przedłużono pobyt")</script></c:if>
	<script>
	function goToReservation(btn){
		var l= ("reservation_details" + "_" + btn);
		window.location.replace(l);
	}
	</script>
</body>
</html>