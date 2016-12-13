package com.sis.bib.dom;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;
import org.codehaus.jackson.map.ObjectMapper;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.google.gson.Gson;
import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoWriteException;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.UpdateOptions;
import com.mongodb.client.result.UpdateResult;
import com.sis.bib.dao.UsuarioDao;
import com.sis.bib.modelo.Usuario;
import com.sis.bib.service.ServiceResponse;

public class UsuarioDaoImpl implements UsuarioDao{
	
	MongoClient mongoClient = new MongoClient();
    MongoDatabase db = mongoClient.getDatabase("sisBib");
    MongoCollection<Document> collection = db.getCollection("usuarios");

	@Override
	public ServiceResponse createUser(Usuario u) {
		//verificar si existe el usuario nuevo
		
		Gson gson = new Gson();
	    String jsonString = gson.toJson(u);
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
	public ServiceResponse updateUser(Usuario u) {
	    Gson gson = new Gson();
		ServiceResponse sr  = new ServiceResponse();
	 
	    try {
	    
	      ObjectId o = new ObjectId(u.get_idAsStr());
	      u.set_id(o);
	      BasicDBObject newDocument = new BasicDBObject();
	      newDocument.append("$set", new BasicDBObject().append("prestados", u.getPrestados()));
	      BasicDBObject searchQuery = new BasicDBObject().append("_id", u.get_id());
	      collection.updateOne(searchQuery, newDocument);
	      sr.setSuccess(true);
	      sr.setMensaje("OK");
	      sr.setResult(0);
	    } catch (MongoWriteException mwe) {
	    	mwe.printStackTrace();
	    	sr.setSuccess(false);
		    sr.setMensaje("OK");
		    sr.setResult(0);
	    } 
	 
	    return sr;
	}

	@Override
	public ServiceResponse findOneUser(Usuario u) {
		
		Gson gson = new Gson();
		List<Usuario> user = new ArrayList<Usuario>();
		ServiceResponse sr  = new ServiceResponse();
		try{
			BasicDBObject gtQuery = new BasicDBObject();
			gtQuery.put("correo", u.getCorreo());
			MongoCursor<Document> cursor = collection.find(gtQuery).iterator();
			
			
		    while (cursor.hasNext()) {
		    	Document doc =cursor.next();
		    	Usuario usuario = gson.fromJson(doc.toJson(), Usuario.class);
		    	user.add(usuario);
		    }
		    
		    sr.setSuccess(true);
			sr.setResult(user);
			sr.setMensaje("Todo ok");
		}catch(Exception e){
			e.printStackTrace();
			sr.setSuccess(false);
			sr.setResult(user);
			sr.setMensaje("Ocurrio un error");
		}
		
		
	    return sr;
	}

	@Override
	public ServiceResponse findAllUser(Usuario u) {
		
		Gson gson = new Gson();
		ServiceResponse sr = new ServiceResponse();
		List<Usuario> usuarios = new ArrayList<Usuario>();
		
		try{
			JSONParser parser = new JSONParser();
			
			for (Document doc : collection.find()) {
				
				//Obtener el fucking objectID de mongo
				Object obj = parser.parse(doc.toJson());
				JSONObject jsonObject = (JSONObject) obj;
				JSONObject root = (JSONObject) jsonObject.get("_id");
				String objID = (String) root.get("$oid");
				
				// to json google
		    	Usuario user = gson.fromJson(doc.toJson(), Usuario.class);
		    	user.set_idAsStr(objID);
		    	usuarios.add(user);
		    }
		}catch(Exception e ){
			e.printStackTrace();
		}
	    
	    
	    if(usuarios.size() > 0){
	    	sr.setMensaje("Datos recuperados ");
	    	sr.setSuccess(true);
	    	sr.setResult(usuarios);
	    }else{
	    	sr.setMensaje("Datos No recuperados ");
	    	sr.setSuccess(false);
	    	sr.setResult(null);
	    }
		return sr;
	}

	@Override
	public ServiceResponse findAllUserByDisponible(Usuario u) {
		Gson gson = new Gson();
		List<Usuario> user = new ArrayList<Usuario>();
		ServiceResponse sr  = new ServiceResponse();
		try{
			BasicDBObject gtQuery = new BasicDBObject();
			gtQuery.put("prestados", new BasicDBObject("$lt", 4));
			MongoCursor<Document> cursor = collection.find(gtQuery).iterator();
			JSONParser parser = new JSONParser();
		    while (cursor.hasNext()) {
		    	Document doc =cursor.next();
		    	
		    	
		    	Object obj = parser.parse(doc.toJson());
				JSONObject jsonObject = (JSONObject) obj;
				JSONObject root = (JSONObject) jsonObject.get("_id");
				String objID = (String) root.get("$oid");
				
				
		    	Usuario usuario = gson.fromJson(doc.toJson(), Usuario.class);
		    	usuario.set_idAsStr(objID);
		    	user.add(usuario);
		    }
		    if(user.size() > 0){
		    	sr.setSuccess(true);
				sr.setResult(user);
				sr.setMensaje("Todo ok");
		    }else{
		    	sr.setSuccess(false);
				sr.setResult(user);
				sr.setMensaje("no data");
		    }
		    
		}catch(Exception e){
			e.printStackTrace();
			sr.setSuccess(false);
			sr.setResult(user);
			sr.setMensaje("Ocurrio un error");
		}
		
		
	    return sr;
	}

	@Override
	public ServiceResponse findAllUserById(Usuario u) {
		Gson gson = new Gson();
		List<Usuario> user = new ArrayList<Usuario>();
		ServiceResponse sr  = new ServiceResponse();
		try{
			JSONParser parser = new JSONParser();
			BasicDBObject gtQuery = new BasicDBObject();
			gtQuery.put("_id", new ObjectId(u.get_idAsStr()) );
			MongoCursor<Document> cursor = collection.find(gtQuery).iterator();
		    while (cursor.hasNext()) {
				
		    	Document doc =cursor.next();
		    	Usuario usuario = gson.fromJson(doc.toJson(), Usuario.class);
		    	
		    	Object obj = parser.parse(doc.toJson());
				JSONObject jsonObject = (JSONObject) obj;
				JSONObject root = (JSONObject) jsonObject.get("_id");
				String objID = (String) root.get("$oid");
				
				usuario.set_idAsStr(objID);
		    	user.add(usuario);
		    }
		    
		    if(user.size() == 1){
		    	sr.setSuccess(true);
				sr.setResult(user);
				sr.setMensaje("Todo ok");
		    }else{
		    	sr.setSuccess(false);
				sr.setResult(user);
				sr.setMensaje("Ocurrio un error");
		    }
		    
		}catch(Exception e){
			e.printStackTrace();
			sr.setSuccess(false);
			sr.setResult(user);
			sr.setMensaje("Ocurrio un error");
		}
		
		
	    return sr;
	}
}
