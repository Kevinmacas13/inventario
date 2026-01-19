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

import com.krakedev.entidades.DetallePedido;
import com.krakedev.entidades.EstadoPedido;
import com.krakedev.entidades.Pedido;
import com.krakedev.entidades.Producto;
import com.krakedev.entidades.Proveedor;
import com.krakedev.exceptions.KrakedevException;
import com.krakedev.inventario.utils.ConexionBDD;

public class PedidoBDD {

	
	
public ArrayList<Pedido> buscar(String subcadena) throws KrakedevException {
		Connection con=null;
		ResultSet rs=null;
		ResultSet rsDet=null;
		PreparedStatement ps=null;
		PreparedStatement psDet=null;
	    ProductosBDD prodBDD = new ProductosBDD();
	    ProveedoresBDD provBDD= new ProveedoresBDD();
	   ArrayList<Pedido> pedidos = new ArrayList<Pedido>();
	 
		try {
			con = ConexionBDD.obtenerConexion();
			ps = 
			con.prepareStatement(
					"SELECT " +
						    "ped.numero_serial AS codigo_pedido, " +
						    "ped.fecha AS fecha_pedido, " +
						    "ped.estado AS estado_pedido, " +
						    "ped.proveedor, " +
						    "estPed.nombre AS nombre_estado " +
						    "FROM cabecera_pedido AS ped " +
						    "JOIN estado_pedidos AS estPed ON ped.estado = estPed.codigo " +
						    "WHERE UPPER(ped.proveedor) LIKE ?"

					);
			 ps.setString(1, subcadena.toUpperCase());
		     rs= ps.executeQuery();
		     while(rs.next()) {
		    	 int   codigoPedido=rs.getInt("codigo_pedido");
		    	 Date fecha= rs.getDate("fecha_pedido");
		    	 String estado=rs.getString("estado_pedido");
		    	 String proveedorCode=rs.getString("proveedor");
		    	 Proveedor proveedor=  provBDD.buscarProveedorPorPk(proveedorCode);
		    	 String estadoNombre= rs.getString("nombre_estado");
		    	 Pedido pedido= new Pedido();
		    	 pedido.setNumero(codigoPedido);
		    	 pedido.setDate(fecha);
		    	 pedido.setEstado(new EstadoPedido(estado,estadoNombre));
		    	 pedido.setProveedor(proveedor);
		  	   ArrayList<DetallePedido> detallePedidos = new ArrayList<DetallePedido>();
		 
					psDet = 
							con.prepareStatement(
									"SELECT " +
										    "detPed.codigo_serial AS codigo_detalle, " +
										    "detPed.cabecera_pedido AS pedido, " +
										    "detPed.subtotal, " +
										    "detPed.cantidad_recibida, " +
										    "detPed.cantidad_solicitada, " +
										    "detPed.producto " +
										    "FROM cabecera_pedido AS ped " +
										    "JOIN detalle_pedido AS detPed ON detPed.cabecera_pedido= ? " 
									);
							 psDet.setInt(1,codigoPedido);
						     rsDet= psDet.executeQuery();
	
						     while(rsDet.next()) {
						    	 
						    	 int codigoDetalle= rsDet.getInt("codigo_detalle");
						    	 BigDecimal subtotal= rsDet.getBigDecimal("subtotal");
						    	 int cantidadSolicitada= rsDet.getInt("cantidad_solicitada");
						    	 int cantidadRecibida= rsDet.getInt("cantidad_recibida");
						    	 int prod= rsDet.getInt("producto");
						    	 Producto producto = prodBDD.buscarProducoPorPk(prod);
						    	 DetallePedido detallePedido = new DetallePedido();
						    	 detallePedido.setCodigoPedido(codigoDetalle);
						    	 detallePedido.setCantidadRecibida(cantidadRecibida);
						    	 detallePedido.setCantidadSolicitada(cantidadSolicitada);
						    	 detallePedido.setSubtotal(subtotal);
						    	 detallePedido.setProducto(producto);
						    	 detallePedidos.add(detallePedido);
						    	 
						     }
						     pedido.setDetallesPedido(detallePedidos);
					    	 pedidos.add(pedido);
		     }
		     
		     
		     
		     
		}catch (SQLException e) {
			e.printStackTrace();
		 throw new KrakedevException("Error al cargar los clientes"+e.getMessage());
		} 
		catch (KrakedevException e) {
			throw e;
		} 	finally {
			if(con!=null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return pedidos;
	}
	
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
			    	 psDet.setInt(2, det.getProducto().getCodigo());
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
	
	
	
	
	public  void recibir(Pedido pedido) throws KrakedevException {
		Connection con=null;
	    PreparedStatement ps = null;
	    PreparedStatement psDet = null;
	    PreparedStatement psHis=null;
	    ResultSet rsClave= null;
	    DetallePedido det;
	    int codigoCabecera=0;
	    Date fechaActual = new Date();
	    Timestamp fechaHoraActual = new Timestamp(fechaActual.getTime());

		try {
			   con = ConexionBDD.obtenerConexion();
			   
			   ps=con.prepareStatement(
					   "UPDATE cabecera_pedido SET  estado=? WHERE  numero_serial=?"
					   );
				ps.setString(1, "R");
				ps.setInt(2, pedido.getNumero());
			     ps.executeUpdate();
			    
			     ArrayList<DetallePedido> detallesPedido = pedido.getDetallesPedido();
			     
			     for(int i=0;i<detallesPedido.size();i++) {
			    	 det= detallesPedido.get(i);
			    	 psDet = con.prepareStatement(
			    			    "UPDATE detalle_pedido " +
			    			    "SET cantidad_recibida = ?, subtotal = ? " +
			    			    "WHERE codigo_serial = ?"
			    			);
			    	 psDet.setInt(1,det.getCantidadRecibida());
			    	 BigDecimal pv= det.getProducto().getPrecioVenta();
			    	 BigDecimal cantidaSoli = new BigDecimal(det.getCantidadSolicitada()- det.getCantidadRecibida());
			    	 BigDecimal subtotal=pv.multiply(cantidaSoli);
			    	 
			    	 psDet.setBigDecimal(2, subtotal);
			    	 psDet.setInt(3,det.getCodigoPedido());
			    	 psDet.executeUpdate();
			  	   psHis=con.prepareStatement(
						   "INSERT INTO  historial_stock(fecha,referencia,producto,cantidad)VALUES (?,?,?,?)"
						   );
					psHis.setTimestamp(1,fechaHoraActual);
					psHis.setString(2,"PEDIDO "+pedido.getNumero());
					psHis.setInt(3, det.getProducto().getCodigo());
					psHis.setInt(4,det.getCantidadRecibida());
				     psHis.executeUpdate(); 
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
