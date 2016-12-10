package com.sis.bib.action;

import java.util.List;

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
	private UsuarioDao usuarioDao = new UsuarioDaoImpl();
	
	
	public String loadAll(){
		ServiceResponse sr = usuarioDao.findAllUser(null);
		List<Usuario> user= null;
		if(sr.isSuccess()){
			 user = (List<Usuario>) sr.getResult();
		}else{
			user=null;
		}
		sendJSONObjectToResponse(sr);
		return null;
	}
	
	
	public String verficaMail(){
		ServiceResponse sr=null;
		try{
			sr = usuarioDao.findOneUser(usuario);
			List<Usuario> user = (List<Usuario>) sr.getResult();
			
			if(user.size() == 0 && user != null){
				sr.setResult(0);
				sr.setSuccess(true);
				sr.setMensaje("");
				sendJSONObjectToResponse(sr);
			}else if(user.size() > 0){
				sr.setResult(user);
				sr.setSuccess(false);
				sr.setMensaje("");
				sendJSONObjectToResponse(sr);
			}else{
				sr.setResult(0);
				sr.setSuccess(false);
				sr.setMensaje("");
				sendJSONObjectToResponse(sr);
			}
			
		}catch(Exception e){
			e.printStackTrace();
			System.out.println("error");
			sr.setResult(0);
			sr.setSuccess(false);
			sr.setMensaje("");
			sendJSONObjectToResponse(sr);
			
		}
		
		return null;
	}
	
	public String crearUsuario(){
		
		
		
		ServiceResponse sr = null;
		List<Usuario> user= null;
		try{
			sr = usuarioDao.createUser(usuario);
			if(sr.isSuccess()){
				sr = usuarioDao.findAllUser(null);
				if(sr.isSuccess()){
					 user = (List<Usuario>) sr.getResult();
				}else{
					 user=null;
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


	public Usuario getUsuario() {
		return usuario;
	}


	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
}
