package com.sis.bib.dao;

import com.sis.bib.modelo.Libro;
import com.sis.bib.service.ServiceResponse;

public interface LibroDao {

	public ServiceResponse createLibro(Libro l);
	public ServiceResponse upateLibro(Libro l);
	public ServiceResponse findOneLibro(Libro l);
	public ServiceResponse findAllLibro(Libro l);
	public ServiceResponse findAllLibroById(Libro l);
	public ServiceResponse findAllLibroDisponible(Libro l);
}
