$( document ).ready(function() {
    
	loadAllUsers();
	
	$( function() {
	    $( "#datepickerIn" ).datepicker({ dateFormat: 'dd/mm/yy' });
	  } );
	
	$( function() {
	    $( "#datepickerFn" ).datepicker({ dateFormat: 'dd/mm/yy' });
	    
	  } );
    	
});

function loadAllUsers(){
	console.log("gfsa")
	$.ajax({
		url : "loadAllUsers",
		type : "POST",
		dataType : "json",
		success : function(jsonResponse) {
			if(jsonResponse.success){
				printTableUsers(jsonResponse.result);
			}else{
				console.log("false")
			}
			
		},
		error : function(jqXHR, textStatus, errorThrown) {
			alert("Error")
		}
	});
	
	
}

function printTableUsers(element){
	console.log("dsdsadsadasds")
	var table = $('#tabUsers');
	var tabLine="";
	if(element.length > 0){
		for(i=0;i<element.length;i++){
			
			tabLine += "<tr onclick='clickRow(this);' id='"+element[i]._idAsStr+"'>";
			tabLine += "<td> "+element[i]._idAsStr+"</td>"
			tabLine += "<td id='nombreUser'> "+element[i].nombre +" "+element[i].aPaterno +" "+element[i].aMaterno+"</td>";
			tabLine += "<td> "+element[i].correo +"</td>";
			tabLine += "<td id='disponiblesval'> "+element[i].prestados +"</td>";
			tabLine += "</tr>";
			
		}
		table.html(tabLine);
	}
	else{
		table.html("No hay registros de usuarios en la base de datos ");
	}
	
}

function printTableLibros(element){
	var table = $('#tdLibros');
	console.log("Estamos trabajando con el usuario "+$("#rowSelect").val());
	var tabLine="";
	if(element.length > 0){
		for(i=0;i<element.length;i++){
			
			tabLine += "<tr ' id='"+element[i].idAsStr+"'>";
			
			tabLine += "<td> <input type='checkbox' name='vehicle'</td>"; 
			tabLine += "<td> "+element[i].idAsStr+"</td>";
			tabLine += "<td> "+element[i].titulo +"</td>";
			tabLine += "<td> "+element[i].autor +"</td>";
			tabLine += "<td> "+element[i].categoria +"</td>";
			tabLine += "<td> "+element[i].editorial +"</td>";
			tabLine += "<td> "+element[i].existencia +"</td>";
			tabLine += "</tr>";

			
		}
		table.html(tabLine);
	}
	
	else{
		table.html("No hay registros de usuarios en la base de datos ");
	}
	
}


function clickRow(element){
	
	var name= $("#"+element.id).children("#nombreUser");
	var disponibles = $("#"+element.id).children("#disponiblesval");
	$("#rowSelect").val(element.id);
	$("#disponibles").val(disponibles[0].innerHTML);
	$("#nombreUser").val(name[0].innerHTML);
	console.log("nombre "+name +" disponibles "+disponibles );
	
	$.ajax({
		url : "loadLibrosDisponibles",
		type : "POST",
		dataType : "json",
		success : function(jsonResponse) {
			if(jsonResponse.success){
				printTableLibros(jsonResponse.result);
			}else{
				alert("false");
			}
		},
		error : function(jqXHR, textStatus, errorThrown) {
			alert("Error")
		}
	});
	document.getElementById('id01').style.display='block';
}

function popUp(element){
	document.getElementById('id01').style.display='none';
	
}

/*function clickRowLibro(element){
	var lista =$("#listaPrestados");
	var tr = element;
	var idLibro= element.id;
	var nombre =$("#nombreUser").val();
	var disponibles =$("#disponibles").val();
	
	var liLine="";
	liLine += "<li class='w3-padding-16' id='"+idLibro+">";
	liLine += "<span onclick='this.parentElement.style.display='none''  class='w3-closebtn w3-padding w3-margin-right w3-medium'>&times;</span>";
	liLine += "<i class=' w3-jumbo 	fa fa-user-circle w3-left  w3-margin-right' style='width:60px;'></i>";
	liLine += "<span class='w3-xlarge'>"+nombre+"</span><br>";
	liLine += "<span>Web Designer</span>";
	liLine += "<intput type='hidden' value ='"+idLibro+"/>";
	liLine += "</li>";
	
	lista.append(liLine);
}*/


function registraPrestamo(element){
	var cnt = 0;
	var flag = false;
	var libros ={};
	var i=0;
	$("#tdLibros tr").each(function (index) 
	        {
	            var campo1, campo2, campo3;
	            $(this).children("td").each(function (index2) 
	            {
	                switch (index2) 
	                {
	                    case 0: campo1 = $(this).text();
	                    		if($($(this).children()).is(":checked")){
	                    			cnt ++;
	                    			flag = true;
	                    		}
                        break;  
                        
	                    case 1: if(flag){
			                    	libros[i] = $(this).text();
			                    	i++;
			                		flag = false;
			                    }
	                }
	            })
//	            alert(campo1 + ' - ' + campo2 + ' - ' + campo3);  length
	        })
	        
	
	if(validaFields(element,cnt,libros)){
    	// idUsuario --- idLibro --- fechaIn --- fechaFin --- multaGenerada
		
    	var json = {
    			"prestamo.usuarioID"			:	$("#rowSelect").val(),
    			"listLibros"					:	libros,
    			"prestamo.fechaInicio"			:	$("#datepickerIn").val(),
    			"prestamo.fechaFin"				:	$("#datepickerFn").val(),
    			"prestados"						:	cnt
    	}
    	
    	$.ajax({
    		url : "generarPrestamo",
    		type : "POST",
    		data : json,
    		dataType : "json",
    		success : function(jsonResponse) {
    			if(jsonResponse.success){
    				console.log(jsonResponse.result)
    				printTableUsers(jsonResponse.result);
    				document.getElementById('id01').style.display='none';
    			}else{
    				console.log("false")
    			}
    		},
    		error : function(jqXHR, textStatus, errorThrown) {
    			alert("Error")
    		}
    	});
    }
}

function validaFields(element,cnt,libros){
	var fechaIn = $("#datepickerIn");
	var fechaFn = $("#datepickerFn");
	var disponibles = $("#disponibles").val();
	var nombre =$("#nombreUser").val();
	var idUser = $("#rowSelect").val();
	var hasErrors = true;
	var mensajeDisplay=""

	
	if(fechaIn.val() == ""){
		mensajeDisplay +="[ La fecha Inicio Vacia]";
		hasErrors = false;
	}
	if(fechaFn.val() == ""){
		mensajeDisplay +="[ La fecha Fin Vacia]";
		hasErrors = false;
	}
	
	var p = parseInt(disponibles.trim())+parseInt(cnt);
	console.log(p +" vs "+4 +( parseInt(p)  < 5));
	if(parseInt(p) >  4){
		mensajeDisplay +="[ Rebasa el limite permitido de prestamos]";
		hasErrors = false;
	}
	
		
	if(libros.length <= 0 ){
		mensajeDisplay +="[ No se ha seleccionado ningun libro ]";
		hasErrors = false;
	}
	
	if(!hasErrors ){
		setTimeout(function() {
			$('#msn').css("display","inline-block")
			$('#msn').text(mensajeDisplay);
			$('#msn').fadeOut(9000);
		});
		return false;
	}
	else{
		return true;
	}
	
}