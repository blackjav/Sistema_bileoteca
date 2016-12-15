$( document ).ready(function() {
    
	loadAllPrestamos();
	
    	
});




function loadAllPrestamos(){
	var json ={"fechaActual"	: fechaActual()}
	$.ajax({
		url : "loadAllPrestamos",
		type : "POST",
		data : json,
		dataType : "json",
		success : function(jsonResponse) {
			if(jsonResponse.success){
				console.log(jsonResponse.result)
				printTablePrestamos(jsonResponse.result);
			}else{
				console.log("false")
			}
			
		},
		error : function(jqXHR, textStatus, errorThrown) {
			alert("Error")
		}
	});
}

function printTablePrestamos(element){
	var table = $('#tabPrestamos');
	var tabLine="";
	if(element.length > 0){
		for(i=0;i<element.length;i++){
			
			tabLine += "<tr'>";
			tabLine += "<td id='"+element[i].objID+"'> "+element[i].nombreUsuario+"<input id='x"+[i]+"_userID' type='hidden' value='"+element[i].usuarioID+"'/></td>"
 			tabLine += "<td id='x"+[i]+"_NombreLibro' > "+element[i].nombreLibro+"<input id='x"+[i]+"_libroID' type='hidden' value='"+element[i].libroID+"'></td>";
			tabLine += "<td id='x"+[i]+"_FechaIni'> "+element[i].fechaInicio +"</td>";
			tabLine += "<td id='x"+[i]+"_FechaFIn'> "+element[i].fechaFin +"</td>";
			tabLine += "<td id='x"+[i]+"_Multa'> $ "+element[i].multa +"</td>";
			tabLine += "<td> <button type='button' id='x"+[i]+"_Btn' name ='"+element[i].objID+"' onclick='regresarLibro(this);'>Regresar</button> </td>";

			tabLine += "</tr>";
			
		}
		table.html(tabLine);
	}
	else{
		table.html("No hay registros de usuarios en la base de datos ");
	}
	
}

function regresarLibro(element){
	var id = element.id;
	var idx = id.substring(1, 2);
	
	var userID = $("#x"+idx+"_userID").val().trim();
	var libroID = $("#x"+idx+"_libroID").val().trim();
	var prestamoID = element.name;
	
	console.log(userID);
	console.log(libroID);
	console.log(prestamoID);
	
	//Regresamos este libro
	var json ={
		"libID" 		:		libroID,
		"userID"		:		userID,
		"idPrestamo"	:		prestamoID
	}
	
	$.ajax({
		url : "regresarLibro",
		type : "POST",
		data : json,
		dataType : "json",
		success : function(jsonResponse) {
			if(jsonResponse.success){
				console.log(jsonResponse.result);
				printTablePrestamos(jsonResponse.result);
				
			}else{
				console.log("false")
			}
			
		},
		error : function(jqXHR, textStatus, errorThrown) {
			alert("Error");
		}
	});
	
}


function fechaActual(){
	var hoy = new Date();
	var dd = hoy.getDate();
	var mm = hoy.getMonth()+1; //hoy es 0!
	var yyyy = hoy.getFullYear();

	if(dd<10) {
	    dd='0'+dd
	} 

	if(mm<10) {
	    mm='0'+mm
	} 

	hoy = dd+'/'+mm+'/'+yyyy;
	
	return hoy;
}