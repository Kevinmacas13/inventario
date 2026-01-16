package com.krakedev.inventario.servicios;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.krakedev.entidades.Pedido;
import com.krakedev.exceptions.KrakedevException;
import com.krakedev.inventario.bdd.PedidoBDD;
@Path("pedidos")
public class ServiciosCabecera {
	   @Path("registrar")
	    @POST
	    @Consumes(MediaType.APPLICATION_JSON)
	   public Response insertar(Pedido pedido) {
	    System.out.println("La Cabecera >>>"+ pedido);
	    PedidoBDD provBD=new PedidoBDD();
	    try {
			provBD.insertar(pedido);
			return Response.ok().build();
		} catch (KrakedevException e) {
			e.printStackTrace();
			return Response.serverError().build();
		}
	   }
	   
	   
	   @Path("recibir")
	    @PUT
	    @Consumes(MediaType.APPLICATION_JSON)
	   public Response actualizar(Pedido pedido) {
	    System.out.println("Actualizar Pedido>>>"+pedido);
	    PedidoBDD provBD=new PedidoBDD();
	    try {
			provBD.recibir(pedido);
			return Response.ok().build();
		} catch (KrakedevException e) {
			e.printStackTrace();
			return Response.serverError().build();
		}
	   }

}
