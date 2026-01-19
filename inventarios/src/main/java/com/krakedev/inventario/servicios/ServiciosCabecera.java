package com.krakedev.inventario.servicios;

import java.util.ArrayList;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
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
	   
		@Path("buscar/{sub}")
	    @GET
	    @Produces(MediaType.APPLICATION_JSON)
	   public Response obtenerCliente(@PathParam("sub") String nombre) {
	    PedidoBDD pedidoBDD=new PedidoBDD();
	    System.out.println("Ingresa >>>> " + nombre);
	    ArrayList<Pedido> pedidos= new ArrayList<Pedido>();
	    try {
		  pedidos= pedidoBDD.buscar(nombre);
		    return Response
					.ok(pedidos)
					.build();
		} catch (KrakedevException e) {
			e.printStackTrace();
			 return Response.serverError().build();
		}
	   }

}
