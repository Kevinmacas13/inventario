package com.krakedev.entidades;

import java.math.BigDecimal;

public class Producto {
	
	  private String codigoPro;
	  private String nombre;
	  private UnidadDeMedida unidadDeMedida;
	  private BigDecimal precio_venta;
	  private boolean tiene_iva;
	  private BigDecimal  costo;
	  private Categoria categoria;
	  private int stock;
	  
	  
	  public Producto() {
		  
	  }
	  
	public Producto(String codigoPro, String nombre, UnidadDeMedida unidadDeMedida, BigDecimal precio_venta,
			boolean tiene_iva, BigDecimal costo, Categoria categoria, int stock) {
		super();
		this.codigoPro = codigoPro;
		this.nombre = nombre;
		this.unidadDeMedida = unidadDeMedida;
		this.precio_venta = precio_venta;
		this.tiene_iva = tiene_iva;
		this.costo = costo;
		this.categoria = categoria;
		this.stock = stock;
	}



	public String getCodigoPro() {
		return codigoPro;
	}
	
	

	public void setCodigoPro(String codigoPro) {
		this.codigoPro = codigoPro;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public UnidadDeMedida getUnidadDeMedida() {
		return unidadDeMedida;
	}
	public void setUnidadDeMedida(UnidadDeMedida unidadDeMedida) {
		this.unidadDeMedida = unidadDeMedida;
	}
	public BigDecimal getPrecio_venta() {
		return precio_venta;
	}
	public void setPrecio_venta(BigDecimal precio_venta) {
		this.precio_venta = precio_venta;
	}
	public boolean isTiene_iva() {
		return tiene_iva;
	}
	public void setTiene_iva(boolean tiene_iva) {
		this.tiene_iva = tiene_iva;
	}
	public BigDecimal getCosto() {
		return costo;
	}
	public void setCosto(BigDecimal costo) {
		this.costo = costo;
	}
	public Categoria getCategoria() {
		return categoria;
	}
	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}
	public int getStock() {
		return stock;
	}
	public void setStock(int stock) {
		this.stock = stock;
	}
	@Override
	public String toString() {
	    return "Producto [" +
	            "codigoPro='" + codigoPro + '\'' +
	            ", nombre='" + nombre + '\'' +
	            ", unidadDeMedida=" + unidadDeMedida +
	            ", precio_venta=" + precio_venta +
	            ", tiene_iva=" + tiene_iva +
	            ", costo=" + costo +
	            ", categoria=" + categoria +
	            ", stock=" + stock +
	            ']';
	}
	  
	  
	  
	  
}
