<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="http://www.w3schools.com/lib/w3.css">
<title>Usuarios</title>
</head>
<body>
<jsp:include page="header.jsp" flush="true"/>
<div class="w3-container">
  <h2>Lista de usuarios</h2>
  <div class="w3-responsive">
  	<table class="w3-table-all w3-hoverable">
    <thead>
      <tr class="w3-light-blue">
        <th>Nombre</th>
        <th>Apellido Paterno</th>
        <th>Apellido Materno</th>
        <th>Correo</th>
        <th>Telefono</th>
        <th>Direccion</th>
        <th>Historial</th>
        <th>Extra </th>
      </tr>
    </thead>
    <tr>
      <td>Jill</td>
      <td>Smith</td>
      <td>50</td>
      <td>50</td>
      <td>50</td>
      <td>50</td>
      <td>50</td>
      
    </tr>
    <tr>
      <td>Eve</td>
      <td>Jackson</td>
      <td>94</td>
      <td>50</td>
      <td>50</td>
      <td>50</td>
      <td>50</td>
      <td>50</td>
      
    </tr>
    <tr>
      <td>Adam</td>
      <td>Johnson</td>
      <td>67</td>
      <td>50</td>
      <td>50</td>
      <td>50</td>
      <td>50</td>
      <td>50</td>
    </tr>
  </table>
 </div>
 	
 <div class="w3-padding left">
 	
 	<a class="w3-btn-floating w3-blue "><-</a>
 </div>
 
 <div class="w3-padding w3-display-right">
 	<a class="w3-btn-floating w3-blue" onclick="document.getElementById('id01').style.display='block'">+</a>
 </div>
</div>

<!-- DIALOGO DE AGREGAR USUARIO  -->
<div id="id01" class="w3-modal w3-animate-zoom" >
    <div class="w3-modal-content w3-animate-top w3-card-5" style="width:40%">
      <header class="w3-container w3-blue"> 
        <span onclick="document.getElementById('id01').style.display='none'"class="w3-closebtn">&times;</span>
        <h6>Agregar Usuario</h6>
      </header>
      <div class="w3-responsive">
	      <div class="w3-container">
	      <div style="margin-left: 18%;">
	      		<p></p>
				<s:textfield name="usuario.nombre" id="nombre" cssClass="w3-input" cssStyle="height:30px; width:350px;" placeholder="Nombre(s)"/>
				<s:textfield name="usuario.aPaterno" id="aPaterno" cssClass="w3-input" cssStyle="height:30px;width:350px;" placeholder="Apellido Paterno"/>
				<s:textfield name="usuario.aMaterno" id="aMaterno" cssClass="w3-input" cssStyle="height:30px;width:350px;" placeholder="Apellido Materno"/>
				<s:textfield name="usuario.telefono" id="telefono" cssClass="w3-input" cssStyle="height:30px;width:350px;" placeholder="Telefono"/>
				<s:textfield name ="usuario.correo" id="correo" cssClass="w3-input" cssStyle="height:30px;width:350px;" placeholder="Correo"/>
				<s:textfield name="usuario.noExt" id="noExt" cssClass="w3-input" cssStyle="height:30px;width:350px;" placeholder="No. Ext"/>
				<s:textfield name="usuario.noInt" id="noInt" cssClass="w3-input" cssStyle="height:30px;width:350px;" placeholder="No. Int"/>
				<s:textfield name="usuario.calle" id="calle" cssClass="w3-input" cssStyle="height:30px;width:350px;" placeholder="Calle"/>
				<s:textfield name="usuario.colonia" id="colonia" cssClass="w3-input" cssStyle="height:30px;width:350px;" placeholder="Colonia"/>
				<s:textfield name="usuario.cp" id="cp" cssClass="w3-input" cssStyle="height:30px;width:350px;" placeholder="Codigo Postal"/>
				<s:textfield name="usuariomunicipio" id="municipio" cssClass="w3-input" cssStyle="height:30px;width:350px;" placeholder="Delegacion / Municipio"/>
				<s:textfield name="usuario.estado" id="estado" cssClass="w3-input" cssStyle="height:30px;width:350px;" placeholder="Estado"/>
				<s:select headerKey="-1" id="tipoUsuario" cssStyle="height:30px;width:350px;" placeholder="Tipo de Usuario" list="#{'0':'', '1':'Publico General','2':'Encargado'}" name="usuario.tipoUsuario" cssClass="w3-input"></s:select>
				<s:password name="usuario.pass1" id="pass1" cssClass="w3-input" cssStyle="height:30px;width:350px;" placeholder="Contraseña"/>
				<s:password name="usuario.pass2" id="pass2"	cssClass="w3-input" cssStyle="height:30px;width:350px;" placeholder="Repita Contraseña"/>
				<p></p><p></p>
				<button class="w3-btn w3-blue" onclick="">Guardar</button>
				<p></p>
	      </div>
	      </div>
      </div>
    </div>
  </div>
<!-- TERMINA DIALOGO DE AGREGAR USUARIO  -->


</body>
</html>