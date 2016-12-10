package com.sis.bib.dom;


import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

import com.google.gson.Gson;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCursor;
import com.mongodb.MongoClient;
import com.mongodb.MongoWriteException;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
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
	    String jsonString = gson.toJson(u);;
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
		// TODO Auto-generated method stub
		return null;
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
		    	System.out.println(doc);
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
		System.out.println("a a sacar");
		
		try{
			for (Document doc : collection.find()) {
		    	Usuario user = gson.fromJson(doc.toJson(), Usuario.class);
		    	user.set_idAsStr(user.get_id().toString());
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
}
