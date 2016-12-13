package com.sis.bib.dao;

import com.sis.bib.modelo.Prestamos;
import com.sis.bib.service.ServiceResponse;

public interface PrestamoDao {
	public ServiceResponse generarPrestamo(Prestamos p);
}
