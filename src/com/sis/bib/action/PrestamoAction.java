package com.sis.bib.action;

import java.util.ArrayList;
import java.util.List;

import com.sis.bib.dao.LibroDao;
import com.sis.bib.dao.PrestamoDao;
import com.sis.bib.dao.UsuarioDao;
import com.sis.bib.dom.LibroDaoImpl;
import com.sis.bib.dom.PrestamosDaoImpl;
import com.sis.bib.dom.UsuarioDaoImpl;
import com.sis.bib.modelo.Libro;
import com.sis.bib.modelo.Prestamos;
import com.sis.bib.modelo.Usuario;
import com.sis.bib.service.ServiceResponse;
import com.sis.bib.service.SisBibAction;

public class PrestamoAction extends SisBibAction{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -451712523074178957L;
	private Prestamos prestamo;
	private List<String> listLibros = new ArrayList<String>();
	private int prestados;

	public String loadAllUsers(){
		UsuarioDao dao = new UsuarioDaoImpl();
		ServiceResponse sr = new  ServiceResponse();
		List<Usuario> user = null;
		try{
			sr = dao.findAllUserByDisponible(null);
			if(sr.isSuccess()){
				user = (List<Usuario>) sr.getResult();
				sr.setSuccess(true);
				sr.setMensaje("OK");
				sr.setResult(user);
			}else{
				sr.setResult(0);
				sr.setSuccess(false);
				sr.setMensaje("");
			}
		}catch(Exception e ){
			e.printStackTrace();
		}
		
		sendJSONObjectToResponse(sr);
		return null;
	}
	
	
	public String loadDisponibleLibros(){
		LibroDao dao = new LibroDaoImpl();
		ServiceResponse sr = new ServiceResponse();
		List<Libro> lib = new ArrayList<Libro>();
		try{
			sr = dao.findAllLibroDisponible(null);
			if(sr.isSuccess()){
				
				lib = (List<Libro>) sr.getResult();
				sr.setSuccess(true);
				sr.setMensaje("ok");
				sr.setResult(lib);
			}else{
				sr.setSuccess(false);
				sr.setMensaje("Mp");
				sr.setResult(0);
			}
		}catch(Exception e ){
			e.printStackTrace();
			sr.setSuccess(false);
			sr.setMensaje("Mo");
			sr.setResult(0);
			
		}
		sendJSONObjectToResponse(sr);
		return null;
	}
	
	public String generarPrestamo(){
		
		PrestamoDao dao = new PrestamosDaoImpl();
		UsuarioDao userDao = new UsuarioDaoImpl();
		LibroDao libroDao = new LibroDaoImpl();
		ServiceResponse sr = new ServiceResponse();
		ServiceResponse user = new ServiceResponse();
		Usuario usuarioToUpdate = null;
		ServiceResponse nuevaLista=null;
		
		try{
			
			Usuario u = new Usuario();
			u.set_idAsStr(prestamo.getUsuarioID());
			user = userDao.findAllUserById(u);
			List<Usuario> listUser = (List<Usuario>) user.getResult();
			usuarioToUpdate = listUser.get(0);
			
			int prestados = usuarioToUpdate.getPrestados();
			boolean flag = false;
			
			System.out.println("Se tienen "+listLibros.size()+ " Libros y se tienen "+prestados+" Prestados");
			for(String s : listLibros){
				if(prestados < 5 ){
					
					System.out.println("Buscando libro [ "+s.trim()+" ]  ....");
					Libro l = new Libro();
					l.setIdAsStr(s.trim());
					ServiceResponse libroResponse = libroDao.findAllLibroById(l);
					
					if(libroResponse.isSuccess()){
						
						List<Libro> ls =  (List<Libro>) libroResponse.getResult();
						l = ls.get(0);
						int disponibles = l.getExistencia();
						System.out.println("Se encontro libro con [ "+disponibles +" ] Existencias");
						
						prestamo.setNombreUsuario(usuarioToUpdate.getNombre()+" " + usuarioToUpdate.getaPaterno()+" " + usuarioToUpdate.getaMaterno());
						prestamo.setNombreLibro(l.getTitulo() +" "+ l.getAutor() +" Ed:"+ l.getEditorial());
						prestamo.setLibroID(s);
						prestamo.setStatus(0);
						if(disponibles > 0){
							sr = dao.generarPrestamo(prestamo);
							if(sr.isSuccess()){
								disponibles --;
								System.out.println("Orden genearada...");
								prestados ++;
								flag=true;
								
							}else{
								throw new Exception("Error al insertar una fila");
							}
							
							l.setExistencia(disponibles);
							System.out.println("Se actualizaron existencias del libro a "+disponibles);
							libroDao.upateLibro(l);
						}else{
							flag = false;
						}
						
						
					}else{
						System.out.println("Algo salio mal al buscar el libr");
					}
					
					
				}else{
					//mandar error de que ya no se le puede prestar a este wey
					flag =false;
				}
			}
			if(flag){
				//actualizar datos y mandar a decir que todo chido
				u.setPrestados(prestados);
				System.out.println("total prestados "+prestados);
				ServiceResponse sus = userDao.updateUser(u);
				
				if(sus.isSuccess()){
					System.out.println("USUARIO INSERTADO");
					List<Usuario> userUp = null;
					
						nuevaLista = userDao.findAllUserByDisponible(null);
						if(sr.isSuccess()){
							userUp = (List<Usuario>) nuevaLista.getResult();
							nuevaLista.setSuccess(true);
							nuevaLista.setMensaje("OK");
							nuevaLista.setResult(userUp);
							sendJSONObjectToResponse(nuevaLista);
						}else{
							nuevaLista.setSuccess(false);
							nuevaLista.setMensaje("Algo salio mal al guardar los libros revise disponibilidad y existencia");
							nuevaLista.setResult(0);
							sendJSONObjectToResponse(nuevaLista);
						}
						
				}else{
					System.err.println("ERROR AL INSERTAR");
				}
				
			}else{
//				No actualizar nada y mandar a decir que salio mal algo 
			}
			
		}catch(Exception e ){
			e.printStackTrace();
		}
		return null;
	}
	
	public String loadAllRegistros(){
		PrestamoDao dao = new PrestamosDaoImpl();
		ServiceResponse sr = new  ServiceResponse();
		List<Prestamos> prestamos = null;
		try{
			sr = dao.findAll(null);
			if(sr.isSuccess()){
				prestamos = (List<Prestamos>) sr.getResult();
				sr.setSuccess(true);
				sr.setMensaje("OK");
				sr.setResult(prestamos);
			}else{
				sr.setResult(0);
				sr.setSuccess(false);
				sr.setMensaje("");
			}
		}catch(Exception e ){
			e.printStackTrace();
		}
		
		sendJSONObjectToResponse(sr);
		return null;
	}


	public Prestamos getPrestamo() {
		return prestamo;
	}


	public void setPrestamo(Prestamos prestamo) {
		this.prestamo = prestamo;
	}


	public List<String> getListLibros() {
		return listLibros;
	}


	public void setListLibros(List<String> listLibros) {
		this.listLibros = listLibros;
	}


	public int getPrestados() {
		return prestados;
	}


	public void setPrestados(int prestados) {
		this.prestados = prestados;
	}

}
