package com.krakedev.entidades;

public class EstadoPedido {
	private String codigoPedido;
	private String nombre;
	
	public EstadoPedido() {
		
	}
	public EstadoPedido(String codigoPedido, String nombre) {
		super();
		this.codigoPedido = codigoPedido;
		this.nombre = nombre;
	}
	public String getCodigoPedido() {
		return codigoPedido;
	}
	public void setCodigoPedido(String codigoPedido) {
		this.codigoPedido = codigoPedido;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	@Override
	public String toString() {
		return "EstadoPedido [codigoPedido=" + codigoPedido + ", nombre=" + nombre + ", toString()=" + super.toString()
				+ "]";
	}
	
	

}
