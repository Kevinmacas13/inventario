package com.krakedev.inventario.bdd;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.krakedev.entidades.Proveedor;
import com.krakedev.exceptions.KrakedevException;
import com.krakedev.inventario.utils.ConexionBDD;

public class ProveedoresBDD {
	
public ArrayList<Proveedor> buscar(String subcadena) throws KrakedevException {
		
		Connection con=null;
		ResultSet rs=null;
		Proveedor proveedor=null;
		ArrayList<Proveedor> proveedores= new ArrayList<Proveedor>();
		PreparedStatement ps=null;
		
		try {
			con = ConexionBDD.obtenerConexion();
			ps = 
			con.prepareStatement("SELECT * FROM proveedor WHERE upper(nombre) like ?");
			 ps.setString(1, "%"+subcadena.toUpperCase()+"%");
		     rs= ps.executeQuery();
		     while(rs.next()) {
		    	 String identificador=rs.getString("identificador");
		    	 String tipoDocumento=rs.getString("tipo_documento");
		    	 String nombre=rs.getString("nombre");
		    	 String telefono=rs.getString("telefono");
		    	 String correo=rs.getString("correo");
		    	 String direccion=rs.getString("direccion");
		    	proveedor=new Proveedor(identificador, tipoDocumento, nombre, telefono, correo, direccion);
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
			con.prepareStatement("SELECT * FROM proveedores");
		     rs= ps.executeQuery();
		     while(rs.next()) {
		    	 String identificador=rs.getString("identificador");
		    	 String tipoDocumento=rs.getString("tipo_documento");
		    	 String nombre=rs.getString("nombre");
		    	 String telefono=rs.getString("telefono");
		    	 String correo=rs.getString("correo");
		    	 String direccion=rs.getString("direccion");
		    	
		    	 cliente=new Proveedor(identificador, tipoDocumento, nombre, telefono, correo, direccion);
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
