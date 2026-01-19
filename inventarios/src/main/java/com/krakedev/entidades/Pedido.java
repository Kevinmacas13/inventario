package com.krakedev.entidades;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

public class Pedido {
	private int numero;
	private Proveedor proveedor;
	private Date date;
	private EstadoPedido estado;
	private ArrayList<DetallePedido> detallesPedido;

	public Pedido() {
		   this.detallesPedido = new ArrayList<DetallePedido>();

	}
	

	public Pedido(int numero, Proveedor proveedor, Date date, EstadoPedido estado,
			ArrayList<DetallePedido> detallesPedido) {
		super();
		this.numero = numero;
		this.proveedor = proveedor;
		this.date = date;
		this.estado = estado;
		this.detallesPedido = detallesPedido;
	}


	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public Proveedor getProveedor() {
		return proveedor;
	}

	public void setProveedor(Proveedor proveedor) {
		this.proveedor = proveedor;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public EstadoPedido getEstado() {
		return estado;
	}

	public void setEstado(EstadoPedido estado) {
		this.estado = estado;
	}

	public ArrayList<DetallePedido> getDetallesPedido() {
		return detallesPedido;
	}

	public void setDetallesPedido(ArrayList<DetallePedido> detallesPedido) {
		this.detallesPedido = detallesPedido;
	}

	@Override
	public String toString() {
		return "Pedido [numero=" + numero + ", proveedor=" + proveedor + ", date=" + date + ", estado=" + estado
				+ ", detallesPedido=" + detallesPedido + ", toString()=" + super.toString() + "]";
	}

	

}
