package com.sis.bib.service;

import java.io.Serializable;

public class ServiceResponse implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2055640693492482190L;
	
	private String mensaje;
	private boolean success;
	private Object result;
	public String getMensaje() {
		return mensaje;
	}
	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	public Object getResult() {
		return result;
	}
	public void setResult(Object result) {
		this.result = result;
	}
	
}
