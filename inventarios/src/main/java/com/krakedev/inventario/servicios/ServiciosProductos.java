package com.krakedev.inventario.servicios;

import java.util.ArrayList;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
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
	
	@Path("buscar/{sub}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
   public Response obtenerCliente(@PathParam("sub") String nombre) {
    ProductosBDD prodBDD=new ProductosBDD();
    System.out.println("Ingresa >>>> " + nombre);
   
    ArrayList<Producto> productos= new ArrayList<Producto>();
   
    try {
		productos= prodBDD.buscar(nombre);
	    return Response
				.ok(productos)
				.build();

	} catch (KrakedevException e) {
		e.printStackTrace();
		System.out.println(e);
		 return Response.serverError().build();
	}
   }


}
