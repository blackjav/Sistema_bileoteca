package com.sis.bib.dom;

import java.io.IOException;

import org.bson.Document;
import org.codehaus.jackson.map.ObjectMapper;

import com.mongodb.MongoClient;
import com.mongodb.MongoWriteException;
import com.mongodb.client.MongoCollection;
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
	    String jsonString = null;
	    boolean status = true;
	 
	    try {
	        Document doc = Document.parse(jsonString);
	        collection.insertOne(doc);
	    } catch (MongoWriteException mwe) {
	      status = false;
	    }
		return null;
	}

	@Override
	public ServiceResponse updateUser(Usuario u) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ServiceResponse findOneUser(Usuario u) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ServiceResponse findAllUser(Usuario u) {
		// TODO Auto-generated method stub
		return null;
	}

}
