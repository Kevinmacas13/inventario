package com.krakedev.inventario.servicios;

import java.util.ArrayList;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.krakedev.entidades.TiposDocumento;
import com.krakedev.exceptions.KrakedevException;
import com.krakedev.inventario.bdd.TipoDocumentoBDD;
@Path("tiposdocumento")
public class ServiciosTiposDocumento {


	@Path("recuperar")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
   public Response obtenerCliente() {
    TipoDocumentoBDD tipoBDD=new TipoDocumentoBDD();
    ArrayList<TiposDocumento> tiposDocumento= new ArrayList<TiposDocumento>();
   
    try {
		tiposDocumento= tipoBDD.recuperarTodos();
	    return Response
				.ok(tiposDocumento)
				.build();

	} catch (KrakedevException e) {
		e.printStackTrace();
		 return Response.serverError().build();
	}
   }
}
