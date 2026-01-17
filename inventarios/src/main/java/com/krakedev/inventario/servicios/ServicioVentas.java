package com.krakedev.inventario.servicios;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.krakedev.entidades.Pedido;
import com.krakedev.entidades.Venta;
import com.krakedev.exceptions.KrakedevException;
import com.krakedev.inventario.bdd.PedidoBDD;
import com.krakedev.inventario.bdd.VentaBDD;

@Path("ventas")
public class ServicioVentas {
	
	 @Path("guardar")
	    @POST
	    @Consumes(MediaType.APPLICATION_JSON)
	   public Response insertar(Venta venta) {
	    System.out.println("La venta >>>"+ venta);
	   VentaBDD provBD=new VentaBDD();
	    try {
			provBD.insertar(venta);
			return Response.ok().build();
		} catch (KrakedevException e) {
			e.printStackTrace();
			return Response.serverError().build();
		}
	   }
	 
	 


	
	
}
