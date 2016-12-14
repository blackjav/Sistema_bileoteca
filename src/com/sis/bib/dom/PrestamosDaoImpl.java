package com.sis.bib.dom;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.google.gson.Gson;
import com.mongodb.MongoClient;
import com.mongodb.MongoWriteException;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.sis.bib.dao.PrestamoDao;
import com.sis.bib.modelo.Libro;
import com.sis.bib.modelo.Prestamos;
import com.sis.bib.service.ServiceResponse;

public class PrestamosDaoImpl implements PrestamoDao{
	
	MongoClient mongoClient = new MongoClient();
    MongoDatabase db = mongoClient.getDatabase("sisBib");
    MongoCollection<Document> collection = db.getCollection("prestamos");
	@Override
	public ServiceResponse generarPrestamo(Prestamos p) {
		//verificar si existe el usuario nuevo
		
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
			JSONParser parser = new JSONParser();
			for (Document doc : collection.find()) {
				
				Object obj = parser.parse(doc.toJson());
				JSONObject jsonObject = (JSONObject) obj;
				JSONObject root = (JSONObject) jsonObject.get("_id");
				String objID = (String) root.get("$oid");
				
				
		    	Prestamos prestamo = gson.fromJson(doc.toJson(), Prestamos.class);
		    	prestamo.setObjID(objID);
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

}
