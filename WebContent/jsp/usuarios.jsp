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
<script src="../js/crearUsuario.js"></script>
<title>Usuarios</title>
</head>
<body>
<jsp:include page="header.jsp" flush="true"/>
<div class="w3-container">
  <h2>Lista de usuarios</h2>
  <div  style="overflow:auto;height:55%;">
  <div class="w3-responsive" >
  	<table class="w3-table-all w3-hoverable">
    <thead>
      <tr class="w3-light-blue">
        <th>ID</th>
        <th>Nombre</th>
        <th>Dirección</th>
        <th>Tel. Local</th>
        <th>Email</th>
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
  <p id="mensaje" style="width:30%; margin-right: 20px; padding-bottom: 8px; padding-top: 15px; display:none"></p>
 
</div>


<!-- DIALOGO DE AGREGAR USUARIO  -->
<div id="id01" class="w3-modal w3-animate-zoom " >
    <div class="w3-modal-content w3-animate-top w3-card-5" style="width:55%">
      <header class="w3-container w3-blue"> 
        <span onclick="popUp(this);"class="w3-closebtn">&times;</span>
        <h6>Agregar Usuario</h6>
      </header>
      <div class="w3-responsive">
	      <div class="w3-container">
	      <div >
	      		<p></p>
	      		<div class="w3-row-padding">
	      		
<!-- 	      			Nombre  apellidos-->
					<div class="w3-row-padding">
			      		<div class="w3-row w3-section">
						  <div class="w3-col" style="width:50px"><i class="w3-xxlarge fa fa-user"></i></div>
						    <div class="w3-rest">
						    	<div class="w3-row-padding">
						    	
							    	<div class="w3-third">
									    <input class="w3-input w3-border"   id="nombre" type="text" placeholder="Nombre(s)">
									</div>
									<div class="w3-third">
									    <input class="w3-input w3-border"  id="aPaterno" type="text" placeholder="Apellido Paterno">
									</div>
									<div class="w3-third">
									    <input class="w3-input w3-border" id="aMaterno" type="text" placeholder="Apellido Materno">
									</div>
								</div>
						      
						    </div>
						</div>
					</div>
					
<!-- 					correo -->
					<div class="w3-row-padding">
						<div class="w3-row w3-section">
						  <div class="w3-col" style="width:50px"><i class="w3-xxlarge fa fa-envelope-o"></i></div>
						    <div class="w3-rest">
						      <input class="w3-input w3-border" style="width:98%;" name="email" type="text" id="correo" placeholder="Email">
						    </div>
						</div>
					</div>
					
					
<!-- 					Telefono / movil -->
					<div class="w3-row-padding">
						<div class="w3-half">
						  <div class="w3-row w3-section">
						  	<div class="w3-col" style="width:50px"><i class="w3-xxlarge fa fa-phone"></i></div>
						    	<div class="w3-rest">
							    	<div class="w3-row-padding">
							    		<input class="w3-input w3-border" id="telefono" type="text" placeholder="Telefono">
							    	</div>
						    	</div>
							</div>  	
				    	</div>
				    	<div class="w3-half">
				    		<div class="w3-row w3-section">
						  		<div class="w3-col" style="width:50px"><i class="w3-xxlarge fa fa-mobile"></i></div>
						    		<div class="w3-rest">
							    			<input class="w3-input w3-border" id="movil" type="text" placeholder="Movil">
						    		</div>
							</div>
			    		</div>
		    		</div>
		    		
<!-- 		    		noext noint calle -->
		    		<div class="w3-row-padding">
			    		<div class="w3-row w3-section">
						  <div class="w3-col" style="width:50px"><i class="w3-xxlarge fa fa-home"></i></div>
						    <div class="w3-rest">
						    	<div class="w3-row-padding">
							    	<div class="w3-third">
									    <input type="number"name="usuario.noExt" id="noExt" class="w3-input w3-border"  placeholder="No. Ext"/>
									</div>
									<div class="w3-third">
									    <input type="number" name="usuario.noInt" id="noInt" class="w3-input w3-border" placeholder="No. Int"/>
									</div>
									<div class="w3-third">
									   <input type="text" name="usuario.calle" id="calle" class="w3-input w3-border" placeholder="Calle"/>
									</div>
								</div>
						      
						    </div>
						</div>
					</div>
					
<!-- 					Colonia cṕ delegacion -->
					<div class="w3-row-padding">
			    		<div class="w3-row w3-section">
						  <div class="w3-col" style="width:50px"><i class="w3-xxlarge fa fa-home"></i></div>
						    <div class="w3-rest">
						    	<div class="w3-row-padding">
							    	<div class="w3-third">
									    <input type="text" name="usuario.colonia" id="colonia" class="w3-input w3-border"  placeholder="Colonia"/>
									</div>
									<div class="w3-third">
									    <input type="number" name="usuario.cp" id="cp" class="w3-input w3-border"  placeholder="Codigo Postal"/>
									</div>
									<div class="w3-third">
									   <input type="text" name="usuariomunicipio" id="municipio" class="w3-input w3-border"  placeholder="Delegacion / Municipio"/>
									</div>
								</div>
						      
						    </div>
						</div>
					</div>
					
<!-- 					contraseñas  -->
					<div class="w3-row-padding">
						<div class="w3-half">
						  <div class="w3-row w3-section">
						  	<div class="w3-col" style="width:50px"><i class="w3-xxlarge fa fa-shield"></i></div>
						    	<div class="w3-rest">
							    	<div class="w3-row-padding">
							    		<input type="password" name="usuario.pass1" id="pass1" class="w3-input w3-border"  placeholder="Contraseña"/>
							    	</div>
						    	</div>
							</div>  	
				    	</div>
				    	<div class="w3-half">
				    		<div class="w3-row w3-section">
						  		<div class="w3-col" style="width:50px"></div>
						    		<div class="w3-rest">
							    		<input type="password" name="usuario.pass2" id="pass2"	class="w3-input w3-border"  placeholder="Repita Contraseña"/>
						    		</div>
							</div>
			    		</div>
		    		</div>
					
				</div>
				
				<p id="msn" class="w3-panel w3-pale-red w3-bottombar w3-border-red w3-border w3-right" style="display:none;"></p>
				<br>
				<button class="w3-btn w3-blue" onclick="registraUsuario(this)">Guardar</button>
				<p></p>
	      </div>
	      </div>
      </div>
    </div>
  </div>
<!-- TERMINA DIALOGO DE AGREGAR USUARIO  -->


</body>
</html>