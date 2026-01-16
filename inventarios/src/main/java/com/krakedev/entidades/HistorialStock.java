package com.krakedev.entidades;

import java.util.Date;

public class HistorialStock {

	
	
	 private int codigoHS;
	 private Date fecha;
	 private String referencia;
	 private Producto producto;
	 private int cantidad;
	 public HistorialStock() {}
	public HistorialStock(int codigoHS, Date fecha, String referencia, Producto producto, int cantidad) {
		super();
		this.codigoHS = codigoHS;
		this.fecha = fecha;
		this.referencia = referencia;
		this.producto = producto;
		this.cantidad = cantidad;
	}
	public int getCodigoHS() {
		return codigoHS;
	}
	public void setCodigoHS(int codigoHS) {
		this.codigoHS = codigoHS;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public String getReferencia() {
		return referencia;
	}
	public void setReferencia(String referencia) {
		this.referencia = referencia;
	}
	public Producto getProducto() {
		return producto;
	}
	public void setProducto(Producto producto) {
		this.producto = producto;
	}
	public int getCantidad() {
		return cantidad;
	}
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	@Override
	public String toString() {
		return "HistorialStock [codigoHS=" + codigoHS + ", fecha=" + fecha + ", referencia=" + referencia
				+ ", producto=" + producto + ", cantidad=" + cantidad + ", toString()=" + super.toString() + "]";
	}
	 
	 
	
}
