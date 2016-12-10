$( document ).ready(function() {
    
	loadAll();
    	
});

function loadAll(){
	
	var obj;
	$.ajax({
		url : "loadAllBooks",
		type : "POST",
		dataType : "json",
		success : function(jsonResponse) {
			if(jsonResponse.success){
				obj = jsonResponse.result;
				printTable(obj);
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
	if(element.length > 0 || element == null){
		console.log(element)
		for(i=0;i<element.length;i++){
			
			tabLine += "<tr onclick='clickRow(this);' id='"+element[i]._idAsStr+"'>";
			tabLine += "<td> "+element[i].idAsStr+"</td>"
			tabLine += "<td> "+element[i].titulo	 +"</td>";
			tabLine += "<td> "+element[i].autor +"</td>";
			tabLine += "<td> "+element[i].editorial +"</td>";
			tabLine += "<td> "+element[i].ano +"</td>";
			tabLine += "<td> "+element[i].categoria +"</td>";
			tabLine += "<td> "+element[i].existencia +"</td>";
			tabLine += "</tr>";
			
		}
		table.html(tabLine);
	}
	else{
		table.html("No hay registros de usuarios en la base de datos ");
	}
}

function registrarLibro(element){
	//validacion primero
	var titulo =$('#titulo').val();
	var autor =$('#autor').val();
	var editorial =$('#editorial').val();
	var año =$('#ano').val();
	var categoria =$('#categoria').val();
	var existencia =$('#existencia').val();
	var dataSend ={
			'libro.titulo'			: titulo,
			'libro.autor'			: autor,
			'libro.editorial' 		: editorial,
			'libro.ano' 			: año,
			'libro.categoria' 		: categoria,
			'libro.existencia' 		: parseInt(existencia),
			}

	console.log(dataSend)
	$.ajax({
		url : "crearLibro",
		type : "POST",
		data : dataSend,
		dataType : "json",
		success : function(jsonResponse) {
			if(jsonResponse.success){
				
				if(jsonResponse.success){
					document.getElementById('id01').style.display='none';
					cleanElements();
					printTable(jsonResponse.result);
				}
				
			}
		},
		error : function(jqXHR, textStatus, errorThrown) {
			alert("Error")
		}
	});
}


function popUp(element){
	document.getElementById('id01').style.display='none';
	cleanElements();
	
}

function cleanElements(){
	var titulo =$('#titulo').val("");
	var autor =$('#autor').val("");
	var editoria =$('#editorial').val("");
	var año =$('#año').val("");
	var categoria =$('#categoria').val("");
	var existencia =$('#existencia').val("");
}

function clickRow(element){
	console.log("ENtro "+element.id);
}