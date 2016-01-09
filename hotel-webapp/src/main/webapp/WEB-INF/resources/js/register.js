
function confirmPass() {
	var pass = document.getElementById("inputPassword").value
	var confPass = document.getElementById("inputConfirmPassword").value
	if (pass != confPass) {
		alert('Hasła nie zgadzają się!');
	}
}

function checkPassword() {
	var pass = document.getElementById("inputPassword").value
	var re = /(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{6,30}/;
	if (!re.test(pass)) {
		alert('Hasło musi zawierać przynajmniej jedną wielką literę, jedną małą literę, cyfrę i znak specjalny!');
	}
}

function bConfirmPass() {
	var pass = document.getElementById("inputPassword").value
	var confPass = document.getElementById("inputConfirmPassword").value
	return pass != confPass
}

function bCheckPassword() {
	var pass = document.getElementById("inputPassword").value
	var re = /(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{6,30}/;
	return !re.test(pass)
}
function checkForm(form)
{
  if(form.login.value == "") {
    alert("Login nie może być pusty!");
    /*form.login.focus();*/
    return false;
  }
  re = /^\w+$/;
  if(!re.test(form.login.value)) {
/*    alert("Login może zawierać jedynie litery, cyfry i znak podkreślenia!");
*/    form.login.focus();
    return false;
  }
  if(form.login.value.length < 6 || form.login.value.length>30){
/*	  alert("Login nie może być krótszy niż 6 znaków i dłuższy niż 30.");
*/	  form.login.focus();
	  return false;
  }
  if(form.email.value.length >60){
/*	  alert("Adres email nie może być dłuższy niż 60 znaków.");
*/	  form.email.focus();
	  return false;
  }
  if(form.password.value != "" && form.password.value == form.confPassword.value) {
    if(form.password.value.length < 6) {
/*      alert("Hasło nie może być krótsze niż 6 znaków!");
*/      form.password.focus();
      return false;
    }
    if(form.password.value == form.login.value) {
/*      alert("Login nie może być hasłem!");
*/      form.password.focus();
      return false;
    }
    
    re =/(?=.*\d)(?=.*[a-z])(?=.*[A-Z])/;
    if(!re.test(form.password.value)) {
/*      alert("Hasło musi zawierać przynajmniej jedną wielką literę, jedną małą literę, cyfrę i znak specjalny!");
*/      form.password.focus();
      return false;
    }
   
  } else {
/*    alert("Proszę sprawdzić czy wprowadzone hasła są identyczne.");
*/    form.password.focus();
    return false;
  }

  return true;
}