package com.krakedev.inventario.bdd;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

import com.krakedev.entidades.DetalleVenta;
import com.krakedev.entidades.Venta;
import com.krakedev.exceptions.KrakedevException;
import com.krakedev.inventario.utils.ConexionBDD;

public class VentaBDD {

	
	
	public  void insertar(Venta venta) throws KrakedevException {
		Connection con=null;
	    PreparedStatement ps = null;
	    PreparedStatement psDet = null;
	    PreparedStatement psUCV = null;
	    PreparedStatement psHis= null;
	    Date fechaActual =new Date();
	    java.sql.Date fechasSQL= new java.sql.Date(fechaActual.getTime());
	    ResultSet rsClave= null;
	    DetalleVenta det;
	    int codigoCabecera=0;
	    
	    Timestamp fechaHoraActual = new Timestamp(fechaActual.getTime());

		try {
			   con = ConexionBDD.obtenerConexion();
			   ps=con.prepareStatement(
					   "INSERT INTO cabecera_venta(fecha,total_sin_iva, iva, total) VALUES (?,?,?,?)",
					   Statement.RETURN_GENERATED_KEYS
					   );
				ps.setTimestamp(1, fechaHoraActual);
				ps.setBigDecimal(2, new BigDecimal(0));
				ps.setBigDecimal(3, new BigDecimal(0));
				ps.setBigDecimal(4,new BigDecimal(0));
			     ps.executeUpdate();
			     
			     rsClave= ps.getGeneratedKeys();
			     if(rsClave.next()) {
			    	codigoCabecera= rsClave.getInt(1);
			     }
			    
			     ArrayList<DetalleVenta> detallesVenta = venta.getDetalles();
			     
			     
				 BigDecimal total_sin_iva= new BigDecimal(0);
				 BigDecimal  iva=new BigDecimal(0);
				 BigDecimal  total=new BigDecimal(0);
			     
			     for(int i=0;i<detallesVenta.size();i++) {
			    	 det= detallesVenta.get(i);
			    	 psDet= con.prepareStatement("insert into detalle_ventas"+
			    	  " (cabecera_venta, producto,cantidad, precio_venta, subtotal, subtotal_iva)"+
			    			 " values (?, ? ,?, ?, ?, ?)");
			    	 
			    	 psDet.setInt(1,codigoCabecera);
			    	 psDet.setInt(2, det.getProducto().getCodigo());
			    	 psDet.setInt(3, det.getCantidad());
			    	 psDet.setBigDecimal(4,det.getProducto().getPrecioVenta());
			    	 
			    	 BigDecimal pv= det.getProducto().getPrecioVenta();
			    	 BigDecimal cantidaSoli = new BigDecimal(det.getCantidad());
			    	 BigDecimal subtotal=pv.multiply(cantidaSoli);
			    	 
			    	 psDet.setBigDecimal(5,subtotal);
			    	 if(det.getProducto().isTieneIva()) {
			    		 psDet.setBigDecimal(6, subtotal.multiply(new BigDecimal(1.12)));
			    		 iva= iva.add(det.getProducto().getPrecioVenta());
			    	 }else 
			    	 {
			    		 psDet.setBigDecimal(6, subtotal);
			    		 total_sin_iva= total_sin_iva.add(det.getProducto().getPrecioVenta());
			    	 }
			    	 
			    	 
			    	 psDet.executeUpdate();
			    	 
			    	 
			    	 
				  	   psHis=con.prepareStatement(
							   "INSERT INTO  historial_stock(fecha,referencia,producto,cantidad)VALUES (?,?,?,?)"
							   );
						psHis.setTimestamp(1,fechaHoraActual);
						psHis.setString(2,"Venta  "+ codigoCabecera);
						psHis.setInt(3, det.getProducto().getCodigo());
						psHis.setInt(4,det.getCantidad()*-1);
					     psHis.executeUpdate(); 
			    	 
			     }

			     
			     total= iva.add(total_sin_iva);
			     
			     
				   psUCV=con.prepareStatement(
						   "UPDATE cabecera_venta SET total_sin_iva=? , iva =?, total= ? WHERE codigo=?",
						   Statement.RETURN_GENERATED_KEYS
						   );
					psUCV.setBigDecimal(1,total_sin_iva );
					psUCV.setBigDecimal(2, iva);
					psUCV.setBigDecimal(3, total);
					psUCV.setInt(4,codigoCabecera);
				     psUCV.executeUpdate();
				     
				     
				     
				     
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
	
	
	
	
	
	
	public  void actualizar(Venta venta) throws KrakedevException {
		Connection con=null;
	    PreparedStatement ps = null;
	    DetalleVenta det;

	   
		try {
			   con = ConexionBDD.obtenerConexion();
			   ps=con.prepareStatement(
					   "UPDATE cabecera_venta SET total_sin_iva=? , iva =?, total= ? WHERE codigo=?",
					   Statement.RETURN_GENERATED_KEYS
					   );
	              
			    BigDecimal total_sin_iva= new BigDecimal(0);
			    BigDecimal  iva=new BigDecimal(0);
			    BigDecimal  total=new BigDecimal(0);
			     ArrayList<DetalleVenta> detallesVenta = venta.getDetalles();	     
			     for(int i=0;i<detallesVenta.size();i++) {
			    	 det= detallesVenta.get(i);
			    	 if(det.getProducto().isTieneIva()) {
			    		iva= iva.add(det.getProducto().getPrecioVenta());
			    	 }else {
			    		 total_sin_iva= total_sin_iva.add(det.getProducto().getPrecioVenta());
			    	 }
			     }
			     
			     total= iva.add(total_sin_iva);
			     
					ps.setBigDecimal(1,total_sin_iva );
					ps.setBigDecimal(2, iva);
					ps.setBigDecimal(3, total);
					ps.setInt(4,venta.getCodigo());
					
				     ps.executeUpdate();

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
