package com.sis.bib.action;

import java.util.List;
import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.sis.bib.dao.ModeradorDao;
import com.sis.bib.dom.ModeradorDaoImpl;
import com.sis.bib.modelo.Moderador;
import com.sis.bib.modelo.Usuario;
import com.sis.bib.service.ServiceResponse;
import com.sis.bib.service.SisBibAction;

public class ModeradorAction extends SisBibAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Moderador moderador;
	private String userLog;
	private String passLog;
	
	public String loadAll(){
		ModeradorDao dao = new ModeradorDaoImpl();
		ServiceResponse sr = dao.findAll(null);
		List<Usuario> user= null;
		if(sr.isSuccess()){
			 user = (List<Usuario>) sr.getResult();
			 sr.setMensaje("OK");
			 sr.setSuccess(true);
			 sr.setResult(user);
			 
		}else{
			user=null;
		}
		sendJSONObjectToResponse(sr);
		
		return null;
	}
	
	
	public String verficaMail(){
		ModeradorDao dao = new ModeradorDaoImpl();
		
		ServiceResponse sr=null;
		try{
			sr = dao.findAllOneByEmail(moderador);
			List<Moderador> user = (List<Moderador>) sr.getResult();
			
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
		ModeradorDao dao = new ModeradorDaoImpl();
		
		ServiceResponse sr = null;
		List<Moderador> moderadorList= null;
		try{
			sr = dao.crearModerador(moderador);
			if(sr.isSuccess()){
				sr = dao.findAll(null);
				if(sr.isSuccess()){
					 moderadorList = (List<Moderador>) sr.getResult();
					 sr.setResult(moderadorList);
					 sr.setSuccess(true);
				}else{
					moderadorList=null;
					sr.setResult(moderadorList);
					sr.setSuccess(false);
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
	
	public String login(){
		ModeradorDao dao = new ModeradorDaoImpl();
		
		ServiceResponse sr=null;
		try{
			Moderador mo = new Moderador();
			System.out.println(userLog);
			System.out.println(passLog);
			mo.setCorreo(userLog.trim());
			sr = dao.findAllOneByEmail(mo);
			List<Moderador> user = (List<Moderador>) sr.getResult();
			
			if(user.size() == 1 && user != null){
				Moderador m = user.get(0);
				
				System.out.println( m.getPass() +" VS "+passLog.trim());
				if(m.getPass().equals(passLog)){
					Map session = ActionContext.getContext().getSession();
					session.put("log",true);
					session.put("userName",m.getNombre());
					session.put("role",1);
					System.out.println("Inicio de sesion creado");
					sr.setSuccess(true);
					sendJSONObjectToResponse(sr);
				}else{
					System.out.println("Las contraseñas no coinciden la contraseña es "+m.getPass());
					sr.setMensaje("Las contraseñas no coinciden");
				}
				
				
				
			}else if(user.size() > 1){
				
				System.out.println("No existe usuario");
				sr.setResult(user);
				sr.setSuccess(false);
				sr.setMensaje("No existe usario");
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
	
	public String cerrarSession(){
		Map session = ActionContext.getContext().getSession();
		session.remove("userName");
        session.remove("role");
        ServiceResponse sr = new ServiceResponse();
        sr.setSuccess(true);
        sendJSONObjectToResponse(sr);
		return null;
	}


	public Moderador getModerador() {
		
		return moderador;
	}


	public void setModerador(Moderador moderador) {
		this.moderador = moderador;
	}


	public String getUserLog() {
		return userLog;
	}


	public void setUserLog(String userLog) {
		this.userLog = userLog;
	}


	public String getPassLog() {
		return passLog;
	}


	public void setPassLog(String passLog) {
		this.passLog = passLog;
	}
	
	
}
