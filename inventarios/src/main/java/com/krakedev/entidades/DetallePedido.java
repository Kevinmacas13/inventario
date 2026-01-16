package com.krakedev.entidades;

import java.math.BigDecimal;

public class DetallePedido {
	
   private int codigoPedido;
   private   Pedido  cabecera;
   private Producto producto;
   private int cantidadSolicitada;
   private int cantidadRecibida;
   private BigDecimal subtotal;
   
   public DetallePedido() {
	   
   }

   
   
public DetallePedido(int codigoPedido, Pedido cabecera, Producto producto, int cantidadSolicitada, int cantidadRecibida,
		BigDecimal subtotal) {
	super();
	this.codigoPedido = codigoPedido;
	this.cabecera = cabecera;
	this.producto = producto;
	this.cantidadSolicitada = cantidadSolicitada;
	this.cantidadRecibida = cantidadRecibida;
	this.subtotal = subtotal;
}



public int getCodigoPedido() {
	return codigoPedido;
}

public void setCodigoPedido(int codigoPedido) {
	this.codigoPedido = codigoPedido;
}

public Pedido getCabecera() {
	return cabecera;
}

public void setCabecera(Pedido cabecera) {
	this.cabecera = cabecera;
}

public Producto getProducto() {
	return producto;
}

public void setProducto(Producto producto) {
	this.producto = producto;
}

public int getCantidadSolicitada() {
	return cantidadSolicitada;
}

public void setCantidadSolicitada(int cantidadSolicitada) {
	this.cantidadSolicitada = cantidadSolicitada;
}

public int getCantidadRecibida() {
	return cantidadRecibida;
}

public void setCantidadRecibida(int cantidadRecibida) {
	this.cantidadRecibida = cantidadRecibida;
}

public BigDecimal getSubtotal() {
	return subtotal;
}

public void setSubtotal(BigDecimal subtotal) {
	this.subtotal = subtotal;
}



@Override
public String toString() {
	return "DetallePedido [codigoPedido=" + codigoPedido + ", cabecera=" + cabecera + ", producto=" + producto
			+ ", cantidadSolicitada=" + cantidadSolicitada + ", cantidadRecibida=" + cantidadRecibida + ", subtotal="
			+ subtotal + ", toString()=" + super.toString() + "]";
}


   


   
}
