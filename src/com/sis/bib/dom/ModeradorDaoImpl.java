package com.sis.bib.dom;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.google.gson.Gson;
import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoWriteException;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.sis.bib.dao.ModeradorDao;
import com.sis.bib.modelo.Libro;
import com.sis.bib.modelo.Moderador;
import com.sis.bib.modelo.Usuario;
import com.sis.bib.service.ServiceResponse;

public class ModeradorDaoImpl implements ModeradorDao {

	@Override
	public ServiceResponse crearModerador(Moderador m) {
		MongoClient mongoClient = new MongoClient();
	    MongoDatabase db = mongoClient.getDatabase("sisBib");
	    MongoCollection<Document> collection = db.getCollection("moderador");
	    
		Gson gson = new Gson();
	    String jsonString = gson.toJson(m);;
	    boolean status = true;
	    ServiceResponse sr = new ServiceResponse();
	    System.out.println(jsonString);
	 
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
	public ServiceResponse modificarModerador(Moderador m) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ServiceResponse findAll(Moderador m) {

		MongoClient mongoClient = new MongoClient();
	    MongoDatabase db = mongoClient.getDatabase("sisBib");
	    MongoCollection<Document> collection = db.getCollection("moderador");
	    
	    
		Gson gson = new Gson();
		ServiceResponse sr = new ServiceResponse();
		List<Moderador> moderador = new ArrayList<Moderador>();
		System.out.println("Va sacar Moderadores...");
		
		try{
			JSONParser parser = new JSONParser();
			for (Document doc : collection.find()) {
				
				Object obj = parser.parse(doc.toJson());
				JSONObject jsonObject = (JSONObject) obj;
				JSONObject root = (JSONObject) jsonObject.get("_id");
				String objID = (String) root.get("$oid");
				
				
		    	Moderador mode = gson.fromJson(doc.toJson(), Moderador.class);
		    	mode.setIdAsStr(objID);
		    	moderador.add(mode);
		    }
		}catch(Exception e ){
			e.printStackTrace();
		}finally {
			mongoClient.close();
			
		}
	    
	    
	    if(moderador.size() > 0){
	    	sr.setMensaje("Datos recuperados ");
	    	sr.setSuccess(true);
	    	sr.setResult(moderador);
	    }else{
	    	sr.setMensaje("Datos No recuperados ");
	    	sr.setSuccess(false);
	    	sr.setResult(null);
	    }
		return sr;
	
	}

	@Override
	public ServiceResponse findAllOneByEmail(Moderador m) {
		MongoClient mongoClient = new MongoClient();
	    MongoDatabase db = mongoClient.getDatabase("sisBib");
	    MongoCollection<Document> collection = db.getCollection("moderador");
	    
	    
		Gson gson = new Gson();
		List<Moderador> moderador = new ArrayList<Moderador>();
		ServiceResponse sr  = new ServiceResponse();
		try{
			BasicDBObject gtQuery = new BasicDBObject();
			gtQuery.put("correo", m.getCorreo());
			MongoCursor<Document> cursor = collection.find(gtQuery).iterator();
			
			
		    while (cursor.hasNext()) {
		    	Document doc =cursor.next();
		    	System.out.println(doc);
		    	Moderador mod = gson.fromJson(doc.toJson(), Moderador.class);
		    	moderador.add(mod);
		    }
		    
		    sr.setSuccess(true);
			sr.setResult(moderador);
			sr.setMensaje("Todo ok");
		}catch(Exception e){
			e.printStackTrace();
			sr.setSuccess(false);
			sr.setResult(0);
			sr.setMensaje("Ocurrio un error");
		}
	    return sr;
	}
	
	

}
