package com.sis.bib.modelo;

import java.io.Serializable;

import org.bson.types.ObjectId;

public class Usuario implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2542406411872025931L;
	
	private ObjectId _id;
	private String _idAsStr;
	private String nombre;
	private String aPaterno;
	private String aMaterno;
	private String sexo;
	private String telefono;
	private String movil;
	private String correo;
	private String noInt;
	private String noExt;
	private String calle;
	private String colonia;
	private String cp;
	private String municipio;
	private String estado;
	private String tipoUsuario;
	private String pass1;
	private String pass2;
	private int prestados;
	private int multas;
	private float multa;
	
	
	
	public String get_idAsStr() {
		return _idAsStr;
	}
	public void set_idAsStr(String _idAsStr) {
		this._idAsStr = _idAsStr;
	}
	public ObjectId get_id() {
		return _id;
	}
	public void set_id(ObjectId _id) {
		this._id = _id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getaPaterno() {
		return aPaterno;
	}
	public void setaPaterno(String aPaterno) {
		this.aPaterno = aPaterno;
	}
	public String getaMaterno() {
		return aMaterno;
	}
	public void setaMaterno(String aMaterno) {
		this.aMaterno = aMaterno;
	}
	public String getSexo() {
		return sexo;
	}
	public void setSexo(String sexo) {
		this.sexo = sexo;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public String getMovil() {
		return movil;
	}
	public void setMovil(String movil) {
		this.movil = movil;
	}
	public String getCorreo() {
		return correo;
	}
	public void setCorreo(String correo) {
		this.correo = correo;
	}
	public String getNoInt() {
		return noInt;
	}
	public void setNoInt(String noInt) {
		this.noInt = noInt;
	}
	public String getNoExt() {
		return noExt;
	}
	public void setNoExt(String noExt) {
		this.noExt = noExt;
	}
	public String getCalle() {
		return calle;
	}
	public void setCalle(String calle) {
		this.calle = calle;
	}
	public String getColonia() {
		return colonia;
	}
	public void setColonia(String colonia) {
		this.colonia = colonia;
	}
	public String getCp() {
		return cp;
	}
	public void setCp(String cp) {
		this.cp = cp;
	}
	public String getMunicipio() {
		return municipio;
	}
	public void setMunicipio(String municipio) {
		this.municipio = municipio;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public String getTipoUsuario() {
		return tipoUsuario;
	}
	public void setTipoUsuario(String tipoUsuario) {
		this.tipoUsuario = tipoUsuario;
	}
	public String getPass1() {
		return pass1;
	}
	public void setPass1(String pass1) {
		this.pass1 = pass1;
	}
	public String getPass2() {
		return pass2;
	}
	public void setPass2(String pass2) {
		this.pass2 = pass2;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString();
	}
	public int getPrestados() {
		return prestados;
	}
	public void setPrestados(int prestados) {
		this.prestados = prestados;
	}
	public int getMultas() {
		return multas;
	}
	public void setMultas(int multas) {
		this.multas = multas;
	}
	public float getMulta() {
		return multa;
	}
	public void setMulta(float multa) {
		this.multa = multa;
	}
		
}
