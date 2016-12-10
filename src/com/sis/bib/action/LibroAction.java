package com.sis.bib.action;

import java.util.List;

import com.sis.bib.dao.LibroDao;
import com.sis.bib.dom.LibroDaoImpl;
import com.sis.bib.modelo.Libro;
import com.sis.bib.modelo.Usuario;
import com.sis.bib.service.ServiceResponse;
import com.sis.bib.service.SisBibAction;

public class LibroAction extends SisBibAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6642891876377774714L;
	private LibroDao libroDao = new LibroDaoImpl();
	private Libro libro;
	
	
	public String loadAllBooks(){
		ServiceResponse sr = libroDao.findAllLibro(null);
		List<Usuario> user= null;
		if(sr.isSuccess()){
			 user = (List<Usuario>) sr.getResult();
		}else{
			user=null;
		}
		sendJSONObjectToResponse(sr);
		
		return null;
	}
	
	public String crearLibro(){
		ServiceResponse sr = null;
		List<Libro> libros= null;
		System.out.println(libro.getAno());
		System.out.println(libro.getExistencia());
		try{
			sr = libroDao.createLibro(libro);
			if(sr.isSuccess()){
				sr = libroDao.findAllLibro(null);
				if(sr.isSuccess()){
					 libros = (List<Libro>) sr.getResult();
				}else{
					 libros=null;
				}
			}else{
				System.out.println("nada salio bien");
			}
			
		}catch(Exception e ){
			e.printStackTrace();
		}
		
		sendJSONObjectToResponse(sr);
		return null;
	}

	
	
	
	public Libro getLibro() {
		return libro;
	}

	public void setLibro(Libro libro) {
		this.libro = libro;
	}

}
