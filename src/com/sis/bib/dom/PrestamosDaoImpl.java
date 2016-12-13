package com.sis.bib.dom;

import org.bson.Document;

import com.google.gson.Gson;
import com.mongodb.MongoClient;
import com.mongodb.MongoWriteException;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.sis.bib.dao.PrestamoDao;
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

}
