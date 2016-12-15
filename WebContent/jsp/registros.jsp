<%@page import="com.opensymphony.xwork2.ActionContext"%>
<%@page import="java.util.Map"%>
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
<script src="../js/registro.js"></script>
<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<title>Registros</title>
<%
 		Map sess = ActionContext.getContext().getSession();
		if(sess.get("userName")==null){
			response.sendRedirect("main.jsp");
		}
		
%>
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
		        <th>Nombre Usuario </th>
		        <th>Libro</th>
		        <th>Fecha Inicio </th>
		        <th>Fecha FIn</th>
		        <th>Multa</th>
		        <th>Devolver</th>
		      </tr>
		    </thead>
		    <tbody id="tabPrestamos">
		    </tbody>
	
	   </table>
	  </div>
 </div>
</div>
<div class="w3-padding w3-clear">
  <a class="w3-btn-floating-large w3-left w3-blue" href="menu.jsp"><<<</a>
  <p id="mensaje" style="width:30%; margin-right: 20px; padding-bottom: 8px; padding-top: 15px; display:none"></p>
</div>

</body>
</html>