package com.sis.bib.dao;

import com.sis.bib.modelo.Prestamos;
import com.sis.bib.service.ServiceResponse;

public interface PrestamoDao {
	public ServiceResponse generarPrestamo(Prestamos p);
	public ServiceResponse findAll(Prestamos p);
	public ServiceResponse actualizarPrestamo(Prestamos p);
}
