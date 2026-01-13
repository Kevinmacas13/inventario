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

import com.krakedev.entidades.Proveedor;
import com.krakedev.exceptions.KrakedevException;
import com.krakedev.inventario.bdd.ProveedoresBDD;

@Path("proveedores")
public class ServiciosProveedores {
	
	
	   @Path("insertar")
	    @POST
	    @Consumes(MediaType.APPLICATION_JSON)
	   public Response insertar(Proveedor proveedor) {
	    System.out.println("EL Cliente >>>"+proveedor);
	    ProveedoresBDD provBD=new ProveedoresBDD();
	    try {
			provBD.insertar(proveedor);
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
    ProveedoresBDD provBDD=new ProveedoresBDD();
    System.out.println("Ingresa >>>> " + nombre);
    ArrayList<Proveedor> proveedores= new ArrayList<Proveedor>();
   
    try {
		proveedores= provBDD.buscar(nombre);
	    return Response
				.ok(proveedores)
				.build();

	} catch (KrakedevException e) {
		e.printStackTrace();
		 return Response.serverError().build();
	}
   }

}
