package com.sis.bib.dom;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

import com.google.gson.Gson;
import com.mongodb.MongoClient;
import com.mongodb.MongoWriteException;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.sis.bib.dao.LibroDao;
import com.sis.bib.modelo.Libro;
import com.sis.bib.service.ServiceResponse;

public class LibroDaoImpl implements LibroDao{
	MongoClient mongoClient = new MongoClient();
    MongoDatabase db = mongoClient.getDatabase("sisBib");
    MongoCollection<Document> collection = db.getCollection("libros");

	@Override
	public ServiceResponse createLibro(Libro l) {
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ServiceResponse findOneLibro(Libro l) {
		// TODO Auto-generated method stub
		
		return null;
	}

	@Override
	public ServiceResponse findAllLibro(Libro l) {
		Gson gson = new Gson();
		ServiceResponse sr = new ServiceResponse();
		List<Libro> usuarios = new ArrayList<Libro>();
		System.out.println("a a sacar");
		
		try{
			for (Document doc : collection.find()) {
		    	Libro libro = gson.fromJson(doc.toJson(), Libro.class);
		    	libro.setIdAsStr(libro.get_id().toString());
		    	usuarios.add(libro);
		    	System.out.println(doc);
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
