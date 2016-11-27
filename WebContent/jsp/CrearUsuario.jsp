<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="../css/jquery-ui.css">
<script src="../scripts/jquery.js"></script>
<script src="../scripts/jquery-ui.js"></script>
<script src="../js/crearUsuario.js"></script>
<%-- <script src="../scripts/jquery-3.1.1.min.js"></script> --%>
<title>Insert title here</title>
</head>
<body>

<div id="tableFields">
<s:form>
	<table>
		<tbody>
			<tr>
				<td><s:textfield name="usuario.nombre" id="nombre" label="Nombre"/></td>
			</tr>
			<tr>
				<td><s:textfield name="usuario.aPaterno" id="aPaterno" label="Apellido Paterno "/></td>
			</tr>
			<tr>
				<td><s:textfield name="usuario.aMaterno" id="aMaterno" label="Aepllido Materno"/></td>
			</tr>
			<tr>
				<td><s:select headerKey="-1" id="sexo" list="#{'0':'Femenino', '1':'Masculino'}" name="usuario.sexo" label="Sexo" ></s:select></td>
			</tr>
			<tr>
				<td><s:textfield name="usuario.telefono" id="telefono" label="Telefono"/></td>
			</tr>
			<tr>
				<td><s:textfield name="usuario.movil" id="movil" label="Movil"/></td>
			</tr>
			<tr>
				<td><s:textfield name ="usuario.correo" id="correo" label="Correo"/></td>
			</tr>
			<tr>
				<td><s:textfield name="usuario.noExt" id="noExt" label="No.Ext."/></td>
			</tr>
			<tr>
				<td><s:textfield name="usuario.noInt" id="noInt" label="No.Int"/></td>
			</tr>
			<tr>
				<td><s:textfield name="usuario.calle" id="calle" label="Calle"/></td>
			</tr>
			<tr>
				<td><s:textfield name="usuario.colonia" id="colonia" label="Colonia"/></td>
			</tr>
			<tr>
				<td><s:textfield name="usuario.cp" id="cp" label="C.P"/></td>
			</tr>
			<tr>
				<td><s:textfield name="usuariomunicipio" id="municipio" label="Delegación/Municipio"/></td>
			</tr>
			<tr>
				<td><s:textfield name="usuario.estado" id="estado" label="Estado"/></td>
			</tr>
			<tr>
				<td><s:select headerKey="-1" id="tipoUsuario" list="#{'0':'', '1':'Publico General','2':'Encargado'}" name="usuario.tipoUsuario" label="Tipo de Usuario" ></s:select></td>
			</tr>
			<tr>
				<td><s:password name="usuario.pass1" id="pass1" label="Contraseña"/></td>
			</tr>
			<tr>
				<td><s:password name="usuario.pass2" id="pass2" label="Repita Contraseña"/></td>
			</tr>
		</tbody>
	</table>
</s:form>
</div>
<div>
	<div id="regisButton" >Registrar</div>
	<div id="backButton" >Regresar</div>
</div>



</body>
</html>