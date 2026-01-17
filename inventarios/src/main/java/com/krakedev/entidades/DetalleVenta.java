package com.krakedev.entidades;

import java.math.BigDecimal;

public class DetalleVenta {
	private int codigo;
	private Producto producto;
	private int cantidad;
	private BigDecimal subtotal;
	private BigDecimal subtotalIva;
	private BigDecimal precioVenta;
	public DetalleVenta() {}
	public DetalleVenta(int codigo, Producto producto, int cantidad, BigDecimal subtotal, BigDecimal subtotalIva,
			BigDecimal precioVenta) {
		super();
		this.codigo = codigo;
		this.producto = producto;
		this.cantidad = cantidad;
		this.subtotal = subtotal;
		this.subtotalIva = subtotalIva;
		this.precioVenta = precioVenta;
	}
	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
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
	public BigDecimal getSubtotal() {
		return subtotal;
	}
	public void setSubtotal(BigDecimal subtotal) {
		this.subtotal = subtotal;
	}
	public BigDecimal getSubtotalIva() {
		return subtotalIva;
	}
	public void setSubtotalIva(BigDecimal subtotalIva) {
		this.subtotalIva = subtotalIva;
	}
	public BigDecimal getPrecioVenta() {
		return precioVenta;
	}
	public void setPrecioVenta(BigDecimal precioVenta) {
		this.precioVenta = precioVenta;
	}
	@Override
	public String toString() {
		return "DetalleVenta [codigo=" + codigo + ", producto=" + producto + ", cantidad=" + cantidad + ", subtotal="
				+ subtotal + ", subtotalIva=" + subtotalIva + ", precioVenta=" + precioVenta + ", toString()="
				+ super.toString() + "]";
	}
	
	
	

}
