$( document ).ready(function() {
    
	loadAllPrestamos();
	
    	
});




function loadAllPrestamos(){
	$.ajax({
		url : "loadAllPrestamos",
		type : "POST",
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
			
			tabLine += "<tr '>";
			tabLine += "<td> "+element[i].nombreUsuario+"</td>"
			tabLine += "<td> "+element[i].nombreLibro+"</td>";
			tabLine += "<td> "+element[i].fechaInicio +"</td>";
			tabLine += "<td> "+element[i].fechaFin +"</td>";
			tabLine += "<td> "+element[i].multa +"</td>";
			tabLine += "<td> <button type='button'id='btn' onclick='regresarLibro(this);'>Regresar</button> </td>";
			tabLine += "</tr>";
			
		}
		table.html(tabLine);
	}
	else{
		table.html("No hay registros de usuarios en la base de datos ");
	}
	
}

function regresarLibro(element){
	console.log(element);
}
