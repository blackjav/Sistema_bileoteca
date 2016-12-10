package com.sis.bib.modelo;

import java.io.Serializable;

import org.bson.types.ObjectId;

public class Libro implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2379342421257566494L;
	private ObjectId _id;
	private String idAsStr;
	private String titulo;
	private String autor;
	private String editorial;
	private String ano;
	private String categoria;
	private int existencia;
	
	
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
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getAutor() {
		return autor;
	}
	public void setAutor(String autor) {
		this.autor = autor;
	}
	public String getEditorial() {
		return editorial;
	}
	public void setEditorial(String editorial) {
		this.editorial = editorial;
	}
	
	
	public String getAno() {
		return ano;
	}
	public void setAno(String ano) {
		this.ano = ano;
	}
	public String getCategoria() {
		return categoria;
	}
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
	public int getExistencia() {
		return existencia;
	}
	public void setExistencia(int existencia) {
		this.existencia = existencia;
	}
	
}
