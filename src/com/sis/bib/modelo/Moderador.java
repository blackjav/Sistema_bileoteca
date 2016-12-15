package com.sis.bib.modelo;

import org.bson.types.ObjectId;

public class Moderador {
	
	private ObjectId _id;
	private String idAsStr;
	private String nombre;
	private String correo;
	private String pass;
	public String getIdAsStr() {
		return idAsStr;
	}
	public void setIdAsStr(String idAsStr) {
		this.idAsStr = idAsStr;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getCorreo() {
		return correo;
	}
	public void setCorreo(String correo) {
		this.correo = correo;
	}
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	public ObjectId get_id() {
		return _id;
	}
	public void set_id(ObjectId _id) {
		this._id = _id;
	}
	
	
	

}
