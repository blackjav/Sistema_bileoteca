package com.sis.bib.dom;

import java.util.ArrayList;
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
import com.sis.bib.dao.LibroDao;
import com.sis.bib.modelo.Libro;
import com.sis.bib.modelo.Usuario;
import com.sis.bib.service.ServiceResponse;

public class LibroDaoImpl implements LibroDao{
	

	@Override
	public ServiceResponse createLibro(Libro l) {
		MongoClient mongoClient = new MongoClient();
	    MongoDatabase db = mongoClient.getDatabase("sisBib");
	    MongoCollection<Document> collection = db.getCollection("libros");
	    
		Gson gson = new Gson();
	    String jsonString = gson.toJson(l);;
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
	public ServiceResponse upateLibro(Libro l) {
		MongoClient mongoClient = new MongoClient();
	    MongoDatabase db = mongoClient.getDatabase("sisBib");
	    MongoCollection<Document> collection = db.getCollection("libros");
	    
		Gson gson = new Gson();
		ServiceResponse sr  = new ServiceResponse();
	 
	    try {
	    
	      ObjectId o = new ObjectId(l.getIdAsStr());
	      l.set_id(o);
	      BasicDBObject newDocument = new BasicDBObject();
	      newDocument.append(  "$set", new BasicDBObject().append( "existencia", l.getExistencia() )  );
	      BasicDBObject searchQuery = new BasicDBObject().append("_id", l.get_id());
	      collection.updateOne(searchQuery, newDocument);
	      sr.setSuccess(true);
	      sr.setMensaje("OK");
	      sr.setResult(1);
	    } catch (MongoWriteException mwe) {
	    	mwe.printStackTrace();
	    	sr.setSuccess(false);
		    sr.setMensaje("OK");
		    sr.setResult(0);
	    }finally {
			mongoClient.close();
			
		}
	 
	    return sr;
	}

	@Override
	public ServiceResponse findOneLibro(Libro l) {
		// TODO Auto-generated method stub
		
		return null;
	}

	@Override
	public ServiceResponse findAllLibro(Libro l) {
		MongoClient mongoClient = new MongoClient();
	    MongoDatabase db = mongoClient.getDatabase("sisBib");
	    MongoCollection<Document> collection = db.getCollection("libros");
	    
	    
		Gson gson = new Gson();
		ServiceResponse sr = new ServiceResponse();
		List<Libro> usuarios = new ArrayList<Libro>();
		System.out.println("a a sacar");
		
		try{
			JSONParser parser = new JSONParser();
			for (Document doc : collection.find()) {
				
				Object obj = parser.parse(doc.toJson());
				JSONObject jsonObject = (JSONObject) obj;
				JSONObject root = (JSONObject) jsonObject.get("_id");
				String objID = (String) root.get("$oid");
				
				
		    	Libro libro = gson.fromJson(doc.toJson(), Libro.class);
		    	libro.setIdAsStr(objID);
		    	usuarios.add(libro);
		    }
		}catch(Exception e ){
			e.printStackTrace();
		}finally {
			mongoClient.close();
			
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
	public ServiceResponse findAllLibroDisponible(Libro l) {
		
		MongoClient mongoClient = new MongoClient();
	    MongoDatabase db = mongoClient.getDatabase("sisBib");
	    MongoCollection<Document> collection = db.getCollection("libros");
	    
	    
		Gson gson = new Gson();
		List<Libro> lib = new ArrayList<Libro>();
		ServiceResponse sr  = new ServiceResponse();
		try{
			BasicDBObject gtQuery = new BasicDBObject();
			gtQuery.put("existencia", new BasicDBObject("$gt", 0));
			MongoCursor<Document> cursor = collection.find(gtQuery).iterator();
			JSONParser parser = new JSONParser();
		    while (cursor.hasNext()) {
		    	Document doc =cursor.next();
		    	
		    	Object obj = parser.parse(doc.toJson());
				JSONObject jsonObject = (JSONObject) obj;
				JSONObject root = (JSONObject) jsonObject.get("_id");
				String objID = (String) root.get("$oid");
				
				
		    	Libro libro = gson.fromJson(doc.toJson(), Libro.class);
		    	libro.setIdAsStr(objID);
		    	lib.add(libro);
		    }
		    if(lib.size() > 0){
		    	sr.setSuccess(true);
				sr.setResult(lib);
				sr.setMensaje("Todo ok");
		    }else{
		    	sr.setSuccess(false);
				sr.setResult(lib);
				sr.setMensaje("no data");
		    }
		    
		}catch(Exception e){
			e.printStackTrace();
			sr.setSuccess(false);
			sr.setResult(lib);
			sr.setMensaje("Ocurrio un error");
		}finally {
			mongoClient.close();
			
		}
	    return sr;
	}

	@Override
	public ServiceResponse findAllLibroById(Libro l) {
		
		MongoClient mongoClient = new MongoClient();
	    MongoDatabase db = mongoClient.getDatabase("sisBib");
	    MongoCollection<Document> collection = db.getCollection("libros");
	    
	    
		Gson gson = new Gson();
		List<Libro> libro = new ArrayList<Libro>();
		ServiceResponse sr  = new ServiceResponse();
		try{
			JSONParser parser = new JSONParser();
			BasicDBObject gtQuery = new BasicDBObject();
			gtQuery.put("_id", new ObjectId(l.getIdAsStr()));
			MongoCursor<Document> cursor = collection.find(gtQuery).iterator();
		    while (cursor.hasNext()) {
				
		    	Document doc =cursor.next();
		    	Libro lib = gson.fromJson(doc.toJson(), Libro.class);
		    	
		    	Object obj = parser.parse(doc.toJson());
				JSONObject jsonObject = (JSONObject) obj;
				JSONObject root = (JSONObject) jsonObject.get("_id");
				String objID = (String) root.get("$oid");
				
				lib.setIdAsStr(objID);
		    	libro.add(lib);
		    }
		    
		    if(libro.size() == 1){
		    	sr.setSuccess(true);
				sr.setResult(libro);
				sr.setMensaje("Todo ok");
		    }else{
		    	sr.setSuccess(false);
				sr.setResult(0);
				sr.setMensaje("Ocurrio un error");
		    }
		    
		}catch(Exception e){
			e.printStackTrace();
			sr.setSuccess(false);
			sr.setResult(0);
			sr.setMensaje("Ocurrio un error");
		}finally {
			mongoClient.close();
			
		}
		
		
	    return sr;
	}

}
