package com.krakedev.inventario.bdd;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;

import com.krakedev.entidades.DetallePedido;
import com.krakedev.entidades.Pedido;
import com.krakedev.exceptions.KrakedevException;
import com.krakedev.inventario.utils.ConexionBDD;

public class PedidoBDD {

	
	
	public  void insertar(Pedido pedido) throws KrakedevException {
		Connection con=null;
	    PreparedStatement ps = null;
	    PreparedStatement psDet = null;
	    Date fechaActual =new Date();
	    java.sql.Date fechasSQL= new java.sql.Date(fechaActual.getTime());
	    ResultSet rsClave= null;
	    DetallePedido det;
	    int codigoCabecera=0;

		try {
			   con = ConexionBDD.obtenerConexion();
			   
			   ps=con.prepareStatement(
					   "INSERT INTO cabecera_pedido(proveedor, fecha, estado) VALUES (?,?,?)",
					   Statement.RETURN_GENERATED_KEYS
					   );
					   
				ps.setString(1, pedido.getProveedor().getIdentificador());
				ps.setDate(2, fechasSQL);
				ps.setString(3, "S");
			     ps.executeUpdate();
			     rsClave= ps.getGeneratedKeys();
			     if(rsClave.next()) {
			    	codigoCabecera= rsClave.getInt(1);
			     }
			    
			     ArrayList<DetallePedido> detallesPedido = pedido.getDetallesPedido();
			     for(int i=0;i<detallesPedido.size();i++) {
			    	 det= detallesPedido.get(i);
			    	 psDet= con.prepareStatement("insert into detalle_pedido"+
			    	  " (cabecera_pedido, producto,cantidad_solicitada,cantidad_recibida,subtotal)"+
			    			 " values (?, ? ,?, ?, ?)");
			    	 
			    	 psDet.setInt(1,codigoCabecera);
			    	 psDet.setString(2, det.getProducto().getCodigoPro());
			    	 psDet.setInt(3, det.getCantidadSolicitada());
			    	 psDet.setInt(4,0);
			   
			    	 BigDecimal pv= det.getProducto().getPrecioVenta();
			    	 BigDecimal cantidaSoli = new BigDecimal(det.getCantidadSolicitada());
			    	 BigDecimal subtotal=pv.multiply(cantidaSoli);
			    	 
			    	 psDet.setBigDecimal(5,subtotal);
			    	 psDet.executeUpdate();
			     }

		}catch (SQLException e) {
			e.printStackTrace();
		 throw new KrakedevException("Error al insertar el cliente"+e.getMessage());
		} 
		catch (KrakedevException e) {
			throw e;
		} 
		finally {
			if(con!=null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	  }	
}
