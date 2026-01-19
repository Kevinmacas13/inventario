package com.krakedev.inventario.bdd;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.krakedev.entidades.Proveedor;
import com.krakedev.entidades.TiposDocumento;
import com.krakedev.exceptions.KrakedevException;
import com.krakedev.inventario.utils.ConexionBDD;

public class ProveedoresBDD {
	
	public  void insertar(Proveedor proveedor) throws KrakedevException {
		Connection con=null;
	    PreparedStatement ps = null;

		try {
		      con = ConexionBDD.obtenerConexion();
		        ps = con.prepareStatement(
		            "INSERT INTO proveedor(identificador, tipo_documento, nombre, telefono, correo, direccion) VALUES (?,?,?,?,?,?)"
		        );
		        ps.setString(1, proveedor.getIdentificador());
		        ps.setString(2, proveedor.getTipoDocumento().getCodigoTd()); 
		        ps.setString(3, proveedor.getNombre());
		        ps.setString(4, proveedor.getTelefono());
		        ps.setString(5, proveedor.getCorreo());
		        ps.setString(6, proveedor.getDireccion());
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

	
public Proveedor buscarProveedorPorPk(String subcadena) throws KrakedevException {
		
		Connection con=null;
		ResultSet rs=null;
		Proveedor proveedor=null;
		PreparedStatement ps=null;
		try {
			con = ConexionBDD.obtenerConexion();
			ps = 
			con.prepareStatement("SELECT * FROM proveedor prov, tipo_documento td where"
					+ " prov.tipo_documento=td.codigo_td"
					+ " AND upper(identificador) like ?");
			 ps.setString(1, subcadena.toUpperCase());
		     rs= ps.executeQuery();
		    if(rs.next()) {
		    	 String identificador=rs.getString("identificador");
		    	 String tipoDocumento=rs.getString("tipo_documento");
		    	 String descripcionTd=rs.getString("descripcion");
		    	 TiposDocumento tipoDoc=new TiposDocumento(tipoDocumento, descripcionTd);
		    	 String nombre=rs.getString("nombre");
		    	 String telefono=rs.getString("telefono");
		    	 String correo=rs.getString("correo");
		    	 String direccion=rs.getString("direccion");
		    	proveedor=new Proveedor(identificador, tipoDoc, nombre,telefono, correo, direccion);
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
		return proveedor;
	}



public ArrayList<Proveedor> buscar(String subcadena) throws KrakedevException {
		
		Connection con=null;
		ResultSet rs=null;
		Proveedor proveedor=null;
		ArrayList<Proveedor> proveedores= new ArrayList<Proveedor>();
		PreparedStatement ps=null;
		
		try {
			con = ConexionBDD.obtenerConexion();
			ps = 
			con.prepareStatement("SELECT * FROM proveedor prov, tipo_documento td where"
					+ " prov.tipo_documento=td.codigo_td"
					+ " AND upper(nombre) like ?");
			 ps.setString(1, "%"+subcadena.toUpperCase()+"%");
		     rs= ps.executeQuery();
		     while(rs.next()) {
		    	 String identificador=rs.getString("identificador");
		    	 String tipoDocumento=rs.getString("tipo_documento");
		    	 String descripcionTd=rs.getString("descripcion");
		    	 TiposDocumento tipoDoc=new TiposDocumento(tipoDocumento, descripcionTd);
		    	 String nombre=rs.getString("nombre");
		    	 String telefono=rs.getString("telefono");
		    	 String correo=rs.getString("correo");
		    	 String direccion=rs.getString("direccion");
		    	proveedor=new Proveedor(identificador, tipoDoc, nombre,telefono, correo, direccion);
		    	proveedores.add(proveedor);
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
		return proveedores;
	}
	
	
public ArrayList<Proveedor> recuperarTodos() throws KrakedevException {
		
   
		ArrayList<Proveedor> proveedores= new ArrayList<Proveedor>();
		Connection con=null;
		ResultSet rs=null;
		Proveedor cliente=null;
		PreparedStatement ps=null;
		
		try {
			con = ConexionBDD.obtenerConexion();
			ps = 
			con.prepareStatement("SELECT * FROM proveedores prov, tipo_documento td where prov.tipo_documento=td.codigo_td");
		     rs= ps.executeQuery();
		     while(rs.next()) {
		    	 String identificador=rs.getString("identificador");
		    	 String tipoDocumento=rs.getString("tipo_documento");
		    	 TiposDocumento tipoDoc=new TiposDocumento(tipoDocumento, "");
		    	 String nombre=rs.getString("nombre");
		    	 String telefono=rs.getString("telefono");
		    	 String correo=rs.getString("correo");
		    	 String direccion=rs.getString("direccion");
		    	 cliente=new Proveedor(identificador,tipoDoc, nombre, telefono, correo, direccion);
		    	 proveedores.add(cliente);
		     }
		     
		}catch (SQLException e) {
			e.printStackTrace();
		 throw new KrakedevException("Error al cargar los proveedores"+e.getMessage());
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
		
		
		return proveedores;
	}




}
