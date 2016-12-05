package com.sis.bib.dao;

import com.sis.bib.modelo.Usuario;
import com.sis.bib.service.ServiceResponse;

public interface UsuarioDao {
	
	public ServiceResponse createUser(Usuario u);
	public ServiceResponse updateUser(Usuario u);
	public ServiceResponse findOneUser(Usuario u);
	public ServiceResponse findAllUser(Usuario u);
}
