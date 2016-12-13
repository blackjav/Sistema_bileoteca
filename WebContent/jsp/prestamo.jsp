<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- <link rel="stylesheet" href="http://www.w3schools.com/lib/w3.css"> -->
<link rel="stylesheet" href="../css/w3c.css">
<link rel="stylesheet" type="text/css" href="../css/help.css">
<script src="../scripts/jquery.js"></script>
<script src="../scripts/jquery-ui.js"></script>
<script src="../js/prestamo.js"></script>
<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<title>Prestamos</title>
</head>
<body>
<jsp:include page="header.jsp" flush="true"/>

<div class="w3-container">
<input type="hidden" id="rowSelect" name="IDUSUARIO" />
<input type="hidden" id="disponibles"/> 
<input type="hidden" id="nombreUser"/> 
  <h2>Lista de usuarios</h2>
  <div  style="overflow:auto;height:60%;">
	  <div class="w3-responsive" >
	  	<table class="w3-table-all w3-hoverable">
		    <thead>
		      <tr class="w3-light-blue">
		        <th>ID</th>
		        <th>Nombre</th>
		        <th>Correo</th>
		        <th>Prestados</th>
		      </tr>
		    </thead>
		    <tbody id="tabUsers">
		    </tbody>
	
	   </table>
	  </div>
 </div>
</div>

<!-- Dialogo -->
<div id="id01" class="w3-modal w3-animate-zoom " >
    <div class="w3-modal-content w3-animate-top w3-card-5" style="width:55%">
      <header class="w3-container w3-blue"> 
        <span onclick="popUp(this);"class="w3-closebtn">&times;</span>
        <h6>Lista de libros</h6>
      </header>
     
      <div class="w3-responsive">
      <div class="w3-container">
      <br/>
       <div  style="overflow:auto;height:60%;">
           <table class="w3-table-all w3-hoverable" id="tas">
			    <thead>
			      <tr class="w3-light-blue">
			        <th></th>
			        <th>ID Libro</th>
			        <th>Titulo</th>
			        <th>Autor</th>
			        <th>Categoria</th>
			        <th>Editorial</th>
			        <th>Existencia</th>
			      </tr>
			    </thead>
			    <tbody id="tdLibros">
			    </tbody>
	        </table>
	     </div>
	     <br/>
	     <div class="w3-row-padding">
		  <div class="w3-half">
		    <label>Fecha Inicio</label>
		    <input class="w3-input w3-border" type="text"  id="datepickerIn">
		  </div>
		  <div class="w3-half">
		    <label>Fecha Fin</label>
		    <input class="w3-input w3-border" type="text" id="datepickerFn">
		  </div>
		</div>
		<br/>
		<p id="msn" class="w3-panel w3-pale-red w3-bottombar w3-border-red w3-border w3-right" style="display:none;"></p>
		<br/>
	     <button class="w3-btn w3-blue" onclick="registraPrestamo(this)">Guardar</button>	
       </div>
       
	   <br/>
<!-- 	   <ul class="w3-ul w3-card-4" id="listaPrestados"> -->
		   
		     
<!-- 		</ul> -->
  
  
      </div>
    </div>
  </div>
<!-- TERMINA DIALOGO DE AGREGAR USUARIO  -->

</body>
</html>