package com.krakedev.inventario.servicios;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.krakedev.entidades.Producto;
import com.krakedev.exceptions.KrakedevException;
import com.krakedev.inventario.bdd.ProductosBDD;

@Path("productos")
public class ServiciosProductos {
	   @Path("insertar")
	    @POST
	    @Consumes(MediaType.APPLICATION_JSON)
	   public Response insertar(Producto producto) {
	    System.out.println("EL producto >>>"+producto);
	    ProductosBDD prodBD=new ProductosBDD();
	    try {
			prodBD.insertar(producto);
			return Response.ok().build();
		} catch (KrakedevException e) {
			e.printStackTrace();
			return Response.serverError().build();
		}
	   }
	
	@Path("buscarPk/{sub}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
   public Response obtenerCliente(@PathParam("sub") int nombre) {
    ProductosBDD prodBDD=new ProductosBDD();
    System.out.println("Ingresa >>>> " + nombre);
    Producto producto= null;

    try {
		producto= prodBDD.buscarProducoPorPk(nombre);
	    return Response
				.ok(producto)
				.build();

	} catch (KrakedevException e) {
		e.printStackTrace();
		System.out.println(e);
		 return Response.serverError().build();
	}
   }
	
	
	   @Path("actualizar")
	    @PUT
	    @Consumes(MediaType.APPLICATION_JSON)
	   public Response actualizar(Producto producto) {
	    System.out.println("Actualizar Pedido>>>"+ producto);
	    ProductosBDD prodBD=new ProductosBDD();
	    try {
			prodBD.actualizar(producto);
			return Response.ok().build();
		} catch (KrakedevException e) {
			e.printStackTrace();
			return Response.serverError().build();
		}
	   }
	
	
	



}
