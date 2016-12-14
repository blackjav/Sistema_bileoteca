package com.sis.bib.modelo;

import java.io.Serializable;

public class Prestamos implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1766895812101969235L;
	private String objID;
	private String usuarioID;
	private String nombreUsuario;
	private String libroID;
	private String nombreLibro;
	private String fechaInicio;
	private String fechaFin;
	private double multa;
	private int status;
	public String getUsuarioID() {
		return usuarioID;
	}
	public void setUsuarioID(String usuarioID) {
		this.usuarioID = usuarioID;
	}
	public String getLibroID() {
		return libroID;
	}
	public void setLibroID(String libroID) {
		this.libroID = libroID;
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
	public double getMulta() {
		return multa;
	}
	public void setMulta(double multa) {
		this.multa = multa;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getObjID() {
		return objID;
	}
	public void setObjID(String objID) {
		this.objID = objID;
	}
	public String getNombreUsuario() {
		return nombreUsuario;
	}
	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}
	public String getNombreLibro() {
		return nombreLibro;
	}
	public void setNombreLibro(String nombreLibro) {
		this.nombreLibro = nombreLibro;
	}
	
}
