<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Formularz rezerwacyjny</title>
</head>
<body>
	<h1>Rezerwacja</h1>
	<form method="POST" action="php/Rezerwacja.php" onsubmit="return confirm('Czy na pewno chcesz wysłać te dane?')"
						onreset="return confirm('Czy na pewno chcesz wyczyścić wprowadzone dane?')">
		<fieldset>
			<legend>Zarezerwuj pokój</legend>
			<input type="text" name="firstName" id="firstName" autofocus required onkeypress="namePressed(event)" />
			<label for="firstName">
				<abbr title="Pole wymagane">*</abbr>
				Imię
			</label> 
			<br />
			<input type="text" name="surname" id="surname" onblur="surnameBlur(this)" onfocus="surnameFocus(this)" />
			<label for="surname">
				Nazwisko
			</label>
			<br />
			<input type="email" name="email" id="email" autocomplete="off"  required/>
			<label for="email">
				<abbr title="Pole wymagane">*</abbr>
				E-mail
			</label>
			<br />
			<input type="text" name="monthOfBirth" id="monthOfBirth" list="months" />
			<label for="monthOfBirth">
				Miesiąc urodzenia
			</label>
			<datalist id="months">
				<option value="Styczeń">Styczeń</option>
				<option value="Luty">Luty</option>
				<option value="Marzec">Marzec</option>
				<option value="Kwiecień">Kwiecień</option>
				<option value="Maj">Maj</option> 
				<option value="Czerwiec">Czerwiec</option>
				<option value="Lipiec">Lipiec</option>
				<option value="Sierpień">Sierpień</option>
				<option value="Wrzesień">Wrzesień</option>
				<option value="Październik">Październik</option>
				<option value="Listopad">Listopad</option>
				<option value="Grudzień">Grudzień</option>
			</datalist>
			<br />
			<input type="tel" name="phoneNumber" id="phoneNumber"  autocomplete="on" />
			<label for="phoneNumber">
				Numer telefonu (w formacie 123-456-789)
			</label>
			<br />
			<input type="date" name="date" id="date" />
			<label for="date">
				Początek pobytu
			</label>
			<br />
			<input type="text" name="days" id="days" min="1" step="1" onchange="changeDays()" />
			<label for="days">
				Czas pobytu
			</label>
			<br />
			<input type="reset" value="Anuluj" />
			<input type="submit" value="Wyślij" />
		</fieldset>
	</form>
</body>
</html>