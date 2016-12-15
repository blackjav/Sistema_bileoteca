$( document ).ready(function() {
    
	loadAll();
    	
});

function loadAll(){
	
	var obj;
	$.ajax({
		url : "loadAllM",
		type : "POST",
		dataType : "json",
		success : function(jsonResponse) {
			if(jsonResponse.success){
				obj = jsonResponse.result;
				printTable(obj);
			}else{
				console.log("sdsadfsa")
			}
			
		},
		error : function(jqXHR, textStatus, errorThrown) {
			alert("Error")
		}
	});
}

function printTable(element){
	
	var table = $('#tabHead');
	var tabLine="";
	if(element.length > 0){
		for(i=0;i<element.length;i++){
			
			tabLine += "<tr onclick='clickRow(this);' id='"+element[i]._idAsStr+"'>";
			tabLine += "<td> "+element[i].idAsStr+"</td>"
			tabLine += "<td> "+element[i].nombre +"</td>";
			tabLine += "<td> "+element[i].correo +"</td>";
			tabLine += "</tr>";
			
		}
//		table.clean();
		table.html(tabLine);
	}
	else{
//		tabñe.clean();
		table.html("No hay registros de usuarios en la base de datos ");
	}
}

function registraUsuario(element){
	//validacion primero
	var valida = validaFIelds();
	
	if(valida){		
		json ={
			"moderador.correo"  : $("#correo").val()
		};
		$.ajax({
			url : "verficaMail",
			type : "POST",
			data : json,
			dataType : "json",
			success : function(jsonResponse) {
				
				if(jsonResponse.success){
					registraSendServer();
					
				}else{
					
					var mensajeDisplay = " El correo [ "+$("#correo").val()+" ] Ya se encuentra registrado";
					setTimeout(function() {
						$('#msn').css("display","inline-block")
						$('#msn').text(mensajeDisplay);
						$('#msn').fadeOut(9000);
					});
				}
				
			},
			error : function(jqXHR, textStatus, errorThrown) {
				console.log("entro aqui")
				var mensajeDisplay = " El correo [ "+$("#correo").val()+" ] Ya se encuentra registrado";
				setTimeout(function() {
					$('#msn').css("display","inline-block")
					$('#msn').text(mensajeDisplay);
					$('#msn').fadeOut(9000);
				});
			}
		});
	}
	
}

function registraSendServer(){
	
	var obj;
	var idMensaje =$('#mensaje');
	var nombre =$('#nombre').val();
	var correo =$('#correo').val();
	var pass1 =$('#pass1').val();
	var pass2 =$('#pass2').val();
	var dataSend ={
			'moderador.nombre'			: nombre,
			'moderador.correo' 			: correo,
			'moderador.pass' 			: pass1,
			}

	$.ajax({
		url : "crearUsuario",
		type : "POST",
		data : dataSend,
		dataType : "json",
		success : function(jsonResponse) {
			if(jsonResponse.success){
				
				if(jsonResponse.success){
					document.getElementById('id01').style.display='none';
					cleanElements();
					printTable(jsonResponse.result);
					
					setTimeout(function() {
						
						idMensaje.css("display","inline-block")
						idMensaje.addClass("w3-panel w3-pale-green w3-bottombar w3-border-green w3-border w3-right");
						idMensaje.text("Guardado Exitoso");
						idMensaje.fadeOut(6000);
					});
				}else{
					setTimeout(function() {
						idMensaje.css("display","inline-block")
						idMensaje.addClass("w3-panel w3-pale-red w3-bottombar w3-border-red w3-border w3-right");
						idMensaje.text("Error al Guardar ");
						idMensaje.fadeOut(6000);
					});
				}
				
			}
		},
		error : function(jqXHR, textStatus, errorThrown) {
			setTimeout(function() {
				idMnesjae.css("display:block;")
				idMensaje.addClass("w3-panel w3-pale-red w3-bottombar w3-border-red w3-border w3-right");
				idMensaje.text("Error al Guardar ");
				idMensaje.fadeOut(6000);
			});
		}
	});
}

function popUp(element){
	document.getElementById('id01').style.display='none';
	cleanElements();
	
}

function cleanElements(){
	var nombre =$('#nombre').val("");
	var correo =$('#correo').val("");
	var pass1 =$('#pass1').val("");
	var pass2 =$('#pass2').val("");
}

function validaFIelds(){
	
	var mensajeDisplay ="";
	var flag =0;
	var hasErrors = true;
	var nombre =$('#nombre')
	var correo =$('#correo');
	var pass1 =$('#pass1');
	var pass2 =$('#pass2');
	
//	nombre
	if(nombre.val() != "" ){
		
	}else{
		hasErrors = false;
		mensajeDisplay += "[ nombre ] ";
	}
	
//	correo
	if(correo.val() != "" ){
		if (isValidEmail(correo.val())){
			
		}else{
			mensajeDisplay =" [ Correo ]"
			correo.css("borderColor","red");
			hasErrors =false;
		}
		
	}else{
		hasErrors = false;
		mensajeDisplay += "[ Correo ] ";
	}
	
//	pass1
	if(pass1.val() != "" ){
		if(pass2.val() == ""){
			mensajeDisplay += "[ Password 2   ] ";
		}else{
			if(pass2.val() == pass1.val()){
				mensajeDisplay += " Las contraseñas no coinciden !!! ";
				flag =99;
			}
		}
		
	}else{
//		pass1.css("borderColor","red");
		hasErrors = false; 
		mensajeDisplay += "[ Password  ] ";
	}
	
	if(!hasErrors ){
		if(flag != 0){
			setTimeout(function() {
				$('#msn').css("display","inline-block")
				$('#msn').text(mensajeDisplay);
				$('#msn').fadeOut(9000);
			});
		}else{
			mensajeDisplay += " Se encuentran Vacios o no coincide con el formato ";
			setTimeout(function() {
				$('#msn').css("display","inline-block")
				$('#msn').text(mensajeDisplay);
				$('#msn').fadeOut(9000);
			});
		}
		
		return false;
	}else{
		return true;
	}
		
	
}

function isValidEmail(mail)
{
    return /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,4})+$/.test(mail);
}

function clickRow(element){
	console.log("ENtro "+element.id);
}



function inicar(element){
	var name= $("#usuario").val();
	var pass =$("#pass").val();
	
	var json ={
			"userLog"		:name,
			"passLog"		:pass
	}
	
	$.ajax({
		url : "login",
		type : "POST",
		data:json,
		dataType : "json",
		success : function(jsonResponse) {
			if(jsonResponse.success){
				window.location="../jsp/menu.jsp";
				
			}else{
				console.log("sdsadfsa")
			}
			
		},
		error : function(jqXHR, textStatus, errorThrown) {
			alert("Error" +jqXHR)
		}
	});
}