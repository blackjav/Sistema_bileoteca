package com.sis.bib.action;

import com.sis.bib.modelo.Usuario;
import com.sis.bib.service.SisBibAction;

public class UsuarioAction extends SisBibAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2578288485851147645L;
	private Usuario usuario;
	
	

	public Usuario getUsuario() {
		return usuario;
	}


	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	

	public String crearUsuario(){
		
		System.out.println(usuario.getNombre());
		return null;
	}

}
