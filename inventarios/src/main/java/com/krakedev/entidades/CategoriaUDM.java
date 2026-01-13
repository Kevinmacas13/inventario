package com.krakedev.entidades;

public class CategoriaUDM {
	private  String codigoUDM;
	private String nombre;
	public CategoriaUDM() {
		
	}
	public CategoriaUDM(String codigoUDM, String nombre) {
		super();
		this.codigoUDM = codigoUDM;
		this.nombre = nombre;
	}
	public String getCodigoUDM() {
		return codigoUDM;
	}
	public void setCodigoUDM(String codigoUDM) {
		this.codigoUDM = codigoUDM;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	@Override
	public String toString() {
		return "CategoriaUDM [codigoUDM=" + codigoUDM + ", nombre=" + nombre + "]";
	}

}
