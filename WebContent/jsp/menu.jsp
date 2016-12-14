<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" type="text/css" href="../css/help.css">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="http://www.w3schools.com/lib/w3.css">
<link rel="stylesheet" href="http://www.w3schools.com/lib/w3-theme-black.css">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<jsp:include page="header.jsp" flush="true"/>
 
<div class="w3-container" style="padding-top: 2%;padding-left: 5%;">

	<div class="w3-row-padding w3-center">
		<div class="w3-col w3-container w3-hover-opacity" style="width:25%; cursor:pointer;">
			<a href="usuarios.jsp"><img src="../imagenes/photo.png"  height="230" width="230" class="w3-circle" alt="Norway"><h2>Usuarios</h2></a>
		</div>
		<div class="w3-col w3-container w3-hover-opacity" style="width:25%; cursor:pointer;">
			<a href="libros.jsp"><img src="../imagenes/bookA.png" height="230" width="230" class="w3-circle" alt="Norway"><h2>Libros</h2></a>
		</div>
		<div class="w3-col w3-container w3-hover-opacity" style="width:25%; cursor:pointer;">
			<a href="prestamo.jsp"><img src="../imagenes/eye.png" height="230" width="230" class="w3-circle" alt="Norway"><h2>Generar Prestamo</h2></a>
		</div>
		<div class="w3-col w3-container w3-hover-opacity" style="width:25%; cursor:pointer;">
			<a href="registros.jsp"><img src="../imagenes/images.jpg" height="230" width="230" class="w3-circle" alt="Norway"><h2>Prestamos / multas</h2></a>
		</div>
	</div>
	
</div>

</body>
</html>