package com.sis.bib.dao;

import com.sis.bib.modelo.Moderador;
import com.sis.bib.service.ServiceResponse;

public interface ModeradorDao {
	
	public ServiceResponse crearModerador(Moderador m);
	public ServiceResponse modificarModerador(Moderador m);
	public ServiceResponse findAll(Moderador m);
	public ServiceResponse findAllOneByEmail(Moderador m);
	
	

}
