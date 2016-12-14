package com.sis.bib.dao;

import com.sis.bib.modelo.Registro;
import com.sis.bib.modelo.Usuario;
import com.sis.bib.service.ServiceResponse;

public interface RegistroDao {
	public ServiceResponse createUser(Registro u);
	public ServiceResponse updateUser(Registro u);
	public ServiceResponse findOneRegistro(Registro u);
	public ServiceResponse findAllRegistros(Registro u);
}
