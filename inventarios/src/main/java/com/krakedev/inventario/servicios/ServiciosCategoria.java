package com.krakedev.inventario.servicios;

import java.util.ArrayList;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.krakedev.entidades.Categoria;
import com.krakedev.exceptions.KrakedevException;
import com.krakedev.inventario.bdd.CategoriasBDD;

@Path("categorias")
public class ServiciosCategoria {
	
	@Path("recuperar")
	@GET
	@Produces
	(MediaType.APPLICATION_JSON)
	   public Response obtenerCategorias() {
	    CategoriasBDD catBD=new CategoriasBDD();
	    ArrayList<Categoria> categorias=null;
	    try {
			categorias= catBD.recuperarCategorias();
		    
		    return Response
					.ok(categorias)
					.build();

		} catch (KrakedevException e) {
			e.printStackTrace();
			 return Response.serverError().build();
		}
	    }
	
	
	   @Path("registrar")
	    @POST
	    @Consumes(MediaType.APPLICATION_JSON)
	   public Response insertar(Categoria categoria) {
	    System.out.println("La categoría >>>"+categoria);
	    CategoriasBDD catBD=new CategoriasBDD();
	    try {
			catBD.insertar(categoria);
			return Response.ok().build();
		} catch (KrakedevException e) {
			e.printStackTrace();
			return Response.serverError().build();
		}
	   }
	   

	   @Path("actualizar")
	    @PUT
	    @Consumes(MediaType.APPLICATION_JSON)
	   public Response actualizar(Categoria categoria) {
	    System.out.println("Actualizar Categoria>>>"+ categoria);
	    CategoriasBDD catBD=new CategoriasBDD();
	    try {
			catBD.actualizar(categoria);
			return Response.ok().build();
		} catch (KrakedevException e) {
			e.printStackTrace();
			return Response.serverError().build();
		}
	   }
	

}
