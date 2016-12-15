<%@page import="com.opensymphony.xwork2.ActionContext"%>
<%@page import="java.util.Map"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- <link rel="stylesheet" href="http://www.w3schools.com/lib/w3.css"> -->
<link rel="stylesheet" href="../css/w3c.css">
<link rel="stylesheet" type="text/css" href="../css/help.css">
<script src="../scripts/jquery.js"></script>
<script src="../scripts/jquery-ui.js"></script>
<script src="../js/moderador.js"></script>
<title>Sistema Bibleotecario</title>

</head>
<body>
<jsp:include page="header.jsp" flush="true"/>

<div class="w3-container">
<br/>
  <div class="w3-card-8 w3-dark-grey" style="width:60%">
	<div class="w3-display-middle">
	    <div class="w3-container w3-center w3-dark-grey">
	      <h3>Bienvenido</h3>
	      <img src="../imagenes/avatar.png"  alt="Avatar" style="width:60%;">
	      <h5>Ingresa tu correro y contraseña</h5>
	
	      <div class="w3-section">
	      	
			<input type="email" id="usuario" placeholder="user@acc.com">
			<input type="password" id="pass" placeholder="Contraseña">
	        <button class="w3-btn w3-green" onclick="inicar(this);">Entrar</button>
	      </div>
	    </div>
	    </div>

  </div>
</div>



<script>
function w3_open() {
    document.getElementById("mySidenav").style.display = "block";
}
function w3_close() {
    document.getElementById("mySidenav").style.display = "none";
}
</script>

</body>
</html>