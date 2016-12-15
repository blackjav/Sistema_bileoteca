 	<%@page import="com.opensymphony.xwork2.ActionContext"%>
<%@page import="java.util.Map"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" href="../css/w3c.css">
<link rel="stylesheet" href="http://www.w3schools.com/lib/w3-theme-black.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<script src="../scripts/jquery.js"></script>
<script src="../scripts/jquery-ui.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
<%
 		Map sess = ActionContext.getContext().getSession();
%>

<ul class="w3-navbar w3-theme w3-xlarge" >
  <li class="w3-navitem"><a href="javascript:void(0)" onclick="w3_open()"><i class="fa fa-bars"></i></a></li>
  <%if( sess.get("userName") == null){%>
  	<li class="w3-navitem"></li>
  <%}else{ %>
  	<li class="w3-navitem">Hola ! <%= sess.get("userName")%></li>
  <%} %>
  <li class="w3-navitem w3-right w3-hide-small"></li>
</ul>


<nav class="w3-sidenav w3-blue w3-card-2" style="display:none" id="mySidenav">
  <a href="javascript:void(0)" onclick="w3_close()" 
  class="w3-closenav w3-large">Close &times;</a>
  <a href="#" onclick="info()">Acerca de</a>
  <a href="#" onclick="cerrarSession()">Cerrar Sessión</a>
</nav>

<script>
function w3_open() {
    document.getElementById("mySidenav").style.display = "block";
}
function w3_close() {
    document.getElementById("mySidenav").style.display = "none";
}

function cerrarSession(){
	
	$.ajax({
		url : "cerrarSession",
		type : "POST",
		dataType : "json",
		success : function(jsonResponse) {
			if(jsonResponse.success){
				window.location="../jsp/main.jsp";
				
			}else{
				console.log("sdsadfsa")
			}
			
		},
		error : function(jqXHR, textStatus, errorThrown) {
			alert("Error" +jqXHR)
		}
	});
}
</script>

</body>
</html>