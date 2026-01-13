package com.krakedev.entidades;

import java.math.BigDecimal;

public class Producto {
	
	  private String codigoPro;
	  private String nombre;
	  private UnidadDeMedida unidadDeMedida;
	  private BigDecimal precioVenta;
	  private boolean tieneIva;
	  private BigDecimal  costo;
	  private Categoria categoria;
	  private int stock;
	  
	  
	  public Producto() {
		  
	  }
	  
	public Producto(String codigoPro, String nombre, UnidadDeMedida unidadDeMedida, BigDecimal precioVenta,
			boolean tieneIva, BigDecimal costo, Categoria categoria, int stock) {
		super();
		this.codigoPro = codigoPro;
		this.nombre = nombre;
		this.unidadDeMedida = unidadDeMedida;
		this.precioVenta = precioVenta;
		this.tieneIva = tieneIva;
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

	public BigDecimal getPrecioVenta() {
		return precioVenta;
	}

	public void setPrecioVenta(BigDecimal precioVenta) {
		this.precioVenta = precioVenta;
	}

	public boolean isTieneIva() {
		return tieneIva;
	}

	public void setTieneIva(boolean tieneIva) {
		this.tieneIva = tieneIva;
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
	            ", precio_venta=" + precioVenta +
	            ", tiene_iva=" + tieneIva +
	            ", costo=" + costo +
	            ", categoria=" + categoria +
	            ", stock=" + stock +
	            ']';
	}
	  
	  
	  
	  
}
