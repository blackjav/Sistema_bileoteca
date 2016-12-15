package com.sis.bib.dom;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.bson.Document;
import org.bson.types.ObjectId;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.google.gson.Gson;
import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoWriteException;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.sis.bib.dao.PrestamoDao;
import com.sis.bib.modelo.Libro;
import com.sis.bib.modelo.Prestamos;
import com.sis.bib.modelo.Usuario;
import com.sis.bib.service.ServiceResponse;

public class PrestamosDaoImpl implements PrestamoDao{
	
	
	@Override
	public ServiceResponse generarPrestamo(Prestamos p) {
		//verificar si existe el usuario nuevo
		MongoClient mongoClient = new MongoClient();
	    MongoDatabase db = mongoClient.getDatabase("sisBib");
	    MongoCollection<Document> collection = db.getCollection("prestamos");
		
		Gson gson = new Gson();
	    String jsonString = gson.toJson(p);;
	    boolean status = true;
	    ServiceResponse sr = new ServiceResponse();	 
	    try {
	        Document doc = Document.parse(jsonString);
	        collection.insertOne(doc);
	        status = true;
	    } catch (MongoWriteException mwe) {
	        status = false;
	        mwe.printStackTrace();
	    }finally{
	    	mongoClient.close();
	    }
	    
	    if(status){
	    	sr.setMensaje("Datos OK");
	    	sr.setResult(1);
	    	sr.setSuccess(true);
	    }else{
	    	sr.setMensaje("Datos Fail");
	    	sr.setResult(0);
	    	sr.setSuccess(false);
	    }
	    	
		return sr;
	}
	@Override
	public ServiceResponse findAll(Prestamos p) {
		MongoClient mongoClient = new MongoClient();
	    MongoDatabase db = mongoClient.getDatabase("sisBib");
	    MongoCollection<Document> collection = db.getCollection("prestamos");
	    
	    
		Gson gson = new Gson();
		ServiceResponse sr = new ServiceResponse();
		List<Prestamos> prestamos = new ArrayList<Prestamos>();
		System.out.println("a a sacar");
		
		try{
			BasicDBObject whereQuery = new BasicDBObject();
			whereQuery.put("status", 0);
			MongoCursor<Document> cursor = collection.find(whereQuery).iterator();
			JSONParser parser = new JSONParser();
			
		    while (cursor.hasNext()) {
		    	Document doc =cursor.next();
		    	
		    	
		    	Object obj = parser.parse(doc.toJson());
				JSONObject jsonObject = (JSONObject) obj;
				JSONObject root = (JSONObject) jsonObject.get("_id");
				String objID = (String) root.get("$oid");
				
				
				Prestamos prestamo = gson.fromJson(doc.toJson(), Prestamos.class);
		    	prestamo.setObjID(objID);
		    	double multa = calcularMulta(prestamo);
		    	if(multa != 0.0)	
		    		prestamo.setMulta(multa);
		    	
		    	prestamos.add(prestamo);
			
		    }
		}catch(Exception e ){
			e.printStackTrace();
		}finally {
			mongoClient.close();
			
		}
	    
	    
	    if(prestamos.size() > 0){
	    	sr.setMensaje("Datos recuperados ");
	    	sr.setSuccess(true);
	    	sr.setResult(prestamos);
	    }else{
	    	sr.setMensaje("Datos No recuperados ");
	    	sr.setSuccess(false);
	    	sr.setResult(null);
	    }
		return sr;
	}
	@Override
	public ServiceResponse actualizarPrestamo(Prestamos p) {
		MongoClient mongoClient = new MongoClient();
	    MongoDatabase db = mongoClient.getDatabase("sisBib");
	    MongoCollection<Document> collection = db.getCollection("prestamos");
	    
	    
		Gson gson = new Gson();
		ServiceResponse sr  = new ServiceResponse();
	 
	    try {
	    
	      ObjectId o = new ObjectId(p.getObjID());
	      BasicDBObject newDocument = new BasicDBObject();
	      newDocument.append("$set", new BasicDBObject().append("status", p.getStatus()));
	      BasicDBObject searchQuery = new BasicDBObject().append("_id", o);
	      collection.updateOne(searchQuery, newDocument);
	      sr.setSuccess(true);
	      sr.setMensaje("OK");
	      sr.setResult(0);
	    } catch (MongoWriteException mwe) {
	    	mwe.printStackTrace();
	    	sr.setSuccess(false);
		    sr.setMensaje("OK");
		    sr.setResult(0);
	    }finally{
	    	mongoClient.close();
	    }
	 
	    return sr;
	}
	
	
	
	public double calcularMulta(Prestamos p){
		System.out.println("****************************************************************************************");
		String fechaFin = p.getFechaFin();
		Date fechaActual = new Date();
        SimpleDateFormat formateador = new SimpleDateFormat("dd/MM/yyyy");
        String fechaSistema=formateador.format(fechaActual);
        double result = 0.0;
        
        int resul = compararFechasConDate(fechaFin,fechaSistema);
        
        switch (resul) {
		case 0:
				System.out.println("La fecha de fin es menor que la de el sistema [Aplica Multa]");
				double cobroInicial = 25.00;
				int dias = diferenciaFechas(fechaFin,fechaSistema);
				double calc=cobroInicial;
				for(int i = 0 ;i<dias;i++){
					calc += 5.0;
				}
				result = calc;
					
			break;
		case 1:
				System.out.println("La fecha de fin es mayor que la de el sistema [No aplicar Multa]");
				result=0.0;
			
			break;
		case 2:
				System.out.println("La fecha fin es igual a la del sistema [No aplica Multa]");
				result =0.0;
	
			break;

		default:
			result = 0.0;
			break;
		}
        
		return result;
		
	}
	
	 private int compararFechasConDate(String fecha1, String fechaActual) {  
		  System.out.println("Parametro String Fecha 1 = "+fecha1+"\n" +
		    "Parametro String fechaActual = "+fechaActual	);  
		  String resultado="";
		  try {
		   /**Obtenemos las fechas enviadas en el formato a comparar*/
		   SimpleDateFormat formateador = new SimpleDateFormat("dd/MM/yyyy"); 
		   Date fechaDate1 = formateador.parse(fecha1);
		   Date fechaDate2 = formateador.parse(fechaActual);
		    
		   System.out.println("Parametro Date Fecha 1 = "+fechaDate1+"\n" +
		     "Parametro Date fechaActual = "+fechaDate2+"\n");
		    
		    if ( fechaDate1.before(fechaDate2) ){
		    	resultado= "La Fecha 1 es menor ";
		    	return 0;
		    }else{
		     if ( fechaDate2.before(fechaDate1) ){
		    	 resultado= "La Fecha 1 es Mayor ";
		    	 return 1;
		     }else{
		      resultado= "Las Fechas Son iguales ";
		      	return 2;
		     } 
		    }
		  } catch (ParseException e) {
		   System.out.println("Se Produjo un Error!!!  "+e.getMessage());
		   return 99;
		  }  
		 }
	 
	 private int diferenciaFechas(String fecha1, String fechaActual){
		String fechaIn [] =fecha1.split("/");
		String fechaSis [] = fechaActual.split("/");
		
		Calendar c = Calendar.getInstance();
		Calendar fechaInicio = new GregorianCalendar();
		fechaInicio.set(Integer.parseInt(fechaIn[2]), Integer.parseInt(fechaIn[1]), Integer.parseInt(fechaIn[0]));
		 
		 
		Calendar fechaFin = new GregorianCalendar();
		fechaFin.set(Integer.parseInt(fechaSis[2]), Integer.parseInt(fechaSis[1]), Integer.parseInt(fechaSis[0]));

		
		c.setTimeInMillis(fechaFin.getTime().getTime() - fechaInicio.getTime().getTime());
		System.out.println("N. dias" + c.get(Calendar.DAY_OF_YEAR));
		return c.get(Calendar.DAY_OF_YEAR);
	 }

}
