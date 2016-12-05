package com.sis.bib.action;

import com.sis.bib.dao.UsuarioDao;
import com.sis.bib.dom.UsuarioDaoImpl;
import com.sis.bib.modelo.Usuario;
import com.sis.bib.service.ServiceResponse;
import com.sis.bib.service.SisBibAction;

public class UsuarioAction extends SisBibAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2578288485851147645L;
	private Usuario usuario;
	private UsuarioDao usuarioDao;
	
	

	public Usuario getUsuario() {
		return usuario;
	}


	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	

	public String crearUsuario(){
		usuarioDao = new UsuarioDaoImpl();
		ServiceResponse sr;
		try{
			sr = usuarioDao.createUser(usuario);
		}catch(Exception e ){
			e.printStackTrace();
		}
		
		return null;
	}

}
