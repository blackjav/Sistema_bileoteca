$( document ).ready(function() {
    
//	$("#regisButton").button({
//		icons: {primary: "ui-icon-zoomout"}
//		}).click(function(event ){
//		viewPiezas();
//	});
	
	$("#regisButton").button({}).click(function(event ){
		registraUsuario(this);
	});
	
	$("#backButton").button({}).click(function(event ){
		regresarButton(this);
	});
    	
});

function registraUsuario(element){
	//validacion primero
	var nombre =$('#nombre').val();
	var aPaterno =$('#aPaterno').val();
	var aMaterno =$('#aMaterno').val();
	var sexo =$('#sexo').val();
	var telefono =$('#telefono').val();
	var movil =$('#movil').val();
	var correo =$('#correo').val();
	var noInt =$('#noInt').val();
	var noExt =$('#noExt').val();
	var calle =$('#calle').val();
	var colonia =$('#colonia').val();
	var cp =$('#cp').val();
	var municipio =$('#municipio').val();
	var estado =$('#estado').val();
	var tipoUsuario =$('#tipoUsuario').val();
	var pass1 =$('#pass1').val();
	var pass2 =$('#pass2').val();
	var dataSend ={
			'usuario.nombre'		: nombre,
			'usuario.aPaterno'		: aPaterno,
			'usuario.aMaterno' 		: aMaterno,
			'usuario.sexo' 			: sexo,
			'usuario.telefono' 		: telefono,
			'usuario.movil' 		: movil,
			'usuario.correo' 		: correo,
			'usuario.noInt' 		: noInt,
			'usuario.noExt' 		: noExt,
			'usuario.calle' 		: calle,
			'usuario.colonia' 		: colonia,
			'usuario.cp' 			: cp,
			'usuario.municipio' 	: municipio,
			'usuario.estado' 		: estado,
			'usuario.tipoUsuario' 	: tipoUsuario,
			'usuario.pass1' 		: pass1,
			'usuario.pass2' 		: pass2
			}
	
	$.ajax({
		url : "crearUsuario",
		type : "POST",
		data : dataSend,
		dataType : "json",
		success : function(jsonResponse, textStatus, jqXHR) {
			alert("SSSS")
		},
		error : function(jqXHR, textStatus, errorThrown) {
			alert("Error")
		}
	});
}

function regresarButton(element){
	console.log("voy a regresar ");
	
}