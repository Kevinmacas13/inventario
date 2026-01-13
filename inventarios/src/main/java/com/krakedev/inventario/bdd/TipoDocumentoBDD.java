package com.krakedev.inventario.bdd;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.krakedev.entidades.TiposDocumento;
import com.krakedev.exceptions.KrakedevException;
import com.krakedev.inventario.utils.ConexionBDD;

public class TipoDocumentoBDD {
	
	public ArrayList<TiposDocumento> recuperarTodos() throws KrakedevException {
		
		   
		ArrayList<TiposDocumento> tiposDocumentos= new ArrayList<TiposDocumento>();
		Connection con=null;
		ResultSet rs=null;
		TiposDocumento tipoDocumento=null;
		PreparedStatement ps=null;
		
		try {
			con = ConexionBDD.obtenerConexion();
			ps = 
			con.prepareStatement("SELECT * FROM tipo_documento");
		     rs= ps.executeQuery();
		     while(rs.next()) {
		    	 String codigoTd=rs.getString("codigo_td");
		    	 String descripcion=rs.getString("descripcion");
		    	                                     	
		    	 tipoDocumento=new TiposDocumento(codigoTd, descripcion);
		    	 tiposDocumentos.add(tipoDocumento);
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
		return tiposDocumentos;
	}

}
