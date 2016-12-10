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
<script src="../js/libro.js"></script>
<title>Libros</title>
</head>
<body>
<jsp:include page="header.jsp" flush="true"/>
<div class="w3-container">
  <h2>Lista de libros</h2>
  <div  style="overflow:auto;height:70%;">
  <div class="w3-responsive" >
  	<table class="w3-table-all w3-hoverable">
    <thead>
      <tr class="w3-light-blue">
        <th>ISBN</th>
        <th>Titulo</th>
        <th>Autor</th>
        <th>Editorial</th>
        <th>Año</th>
        <th>Categoria</th>
        <th>Disponibles</th>
      </tr>
    </thead>
    <tbody id="tabHead">
    </tbody>

  </table>
 </div>
 </div>
</div>


<div class="w3-padding w3-clear">
  <a class="w3-btn-floating-large w3-left w3-blue" href="menu.jsp"><<<</a>
  <a class="w3-btn-floating-large w3-right w3-blue" onclick="document.getElementById('id01').style.display='block'">+</a>
</div>

<!-- DIALOGO DE AGREGAR USUARIO  -->
<div id="id01" class="w3-modal w3-animate-zoom" >
    <div class="w3-modal-content w3-animate-top w3-card-5" style="width:40%">
      <header class="w3-container w3-blue"> 
        <span onclick="popUp(this);"class="w3-closebtn">&times;</span>
        <h6>Agregar Libro</h6>
      </header>
      <div class="w3-responsive">
	      <div class="w3-container">
	      <div >
	      		<p></p>
	      		<div class="w3-row-padding">
				  <div class="w3-half">
				    	<s:textfield name="libro.titulo" id="titulo" cssClass="w3-input w3-border "  placeholder="Titulo"/>
				  </div>
				  <div class=w3-half>
				    	<s:textfield name="libro.autor" id="autor" cssClass="w3-input w3-border "  placeholder="Autor"/>
				  </div>
				  
				</div>
				
				<br>
				
				<div class="w3-row-padding">
				
					<div class="w3-half">
					    <s:textfield name="libro.editorial" id="editorial" cssClass="w3-input w3-border"  placeholder="Editorial"/>
					</div>
					<div class="w3-half">
					    <s:textfield name="libro.año" id="ano" cssClass="w3-input w3-border"  placeholder="Año"/>
					</div>
				  
				</div>
				<br>
				
				<div class="w3-row-padding">
				
					<div class="w3-half">
					    <s:textfield name="libro.categoria" id="categoria" cssClass="w3-input w3-border"  placeholder="Categoria"/>
					</div>
					<div class="w3-half">
					    <s:textfield name="libro.existencia" id="existencia" cssClass="w3-input w3-border"  placeholder="Existencias"/>
					</div>
				  
				</div>

				<p></p><p></p>
				<button class="w3-btn w3-blue" onclick="registrarLibro(this)">Guardar</button>
				<p></p>
	      </div>
	      </div>
      </div>
    </div>
  </div>
<!-- TERMINA DIALOGO DE AGREGAR USUARIO  -->


</body>
</html>