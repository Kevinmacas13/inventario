package com.krakedev.entidades;

public class TiposDocumento {

	private String codigoTd;
	private String descripcion;
	public TiposDocumento() {
	}
	public TiposDocumento(String codigoTd, String descripcion) {
		this.codigoTd = codigoTd;
		this.descripcion = descripcion;
	}
	public String getCodigoTd() {
		return codigoTd;
	}
	public void setCodigoTd(String codigoTd) {
		this.codigoTd = codigoTd;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	@Override
	public String toString() {
		return "TiposDocumento [codigoTd=" + codigoTd + ", descripcion=" + descripcion + "]";
	}
	
}
