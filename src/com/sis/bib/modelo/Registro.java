package com.sis.bib.modelo;

import org.bson.types.ObjectId;

public class Registro {
	
	private ObjectId _id;
	private String idAsStr;
	private String usuarioID;
	private String nombreUsuario;
	private String libro;
	private String libroId;
	private String autor;
	private double multas;
	private String fechaInicio;
	private String fechaFin;
	public ObjectId get_id() {
		return _id;
	}
	public void set_id(ObjectId _id) {
		this._id = _id;
	}
	public String getIdAsStr() {
		return idAsStr;
	}
	public void setIdAsStr(String idAsStr) {
		this.idAsStr = idAsStr;
	}
	public String getUsuarioID() {
		return usuarioID;
	}
	public void setUsuarioID(String usuarioID) {
		this.usuarioID = usuarioID;
	}
	public String getNombreUsuario() {
		return nombreUsuario;
	}
	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}
	public String getLibro() {
		return libro;
	}
	public void setLibro(String libro) {
		this.libro = libro;
	}
	public String getLibroId() {
		return libroId;
	}
	public void setLibroId(String libroId) {
		this.libroId = libroId;
	}
	public String getAutor() {
		return autor;
	}
	public void setAutor(String autor) {
		this.autor = autor;
	}
	public double getMultas() {
		return multas;
	}
	public void setMultas(double multas) {
		this.multas = multas;
	}
	public String getFechaInicio() {
		return fechaInicio;
	}
	public void setFechaInicio(String fechaInicio) {
		this.fechaInicio = fechaInicio;
	}
	public String getFechaFin() {
		return fechaFin;
	}
	public void setFechaFin(String fechaFin) {
		this.fechaFin = fechaFin;
	}
}
