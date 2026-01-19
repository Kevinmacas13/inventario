package com.krakedev.inventario.bdd;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.krakedev.entidades.Categoria;
import com.krakedev.exceptions.KrakedevException;
import com.krakedev.inventario.utils.ConexionBDD;

public class CategoriasBDD {
	
	public  void insertar(Categoria categoria) throws KrakedevException {
		Connection con=null;
	    PreparedStatement ps = null;

		try {
		    con = ConexionBDD.obtenerConexion();
	        ps = con.prepareStatement(
	            "INSERT INTO categorias( nombre, categoria_padre) VALUES (?,?)"
	        );
	        ps.setString(1, categoria.getNombre());
	        ps.setInt(2, categoria.getCategoriaPadre().getCodigoCat());
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

	
	
	
	public  void actualizar(Categoria categoria) throws KrakedevException {
		Connection con=null;
	    PreparedStatement ps = null;

		try {
		    con = ConexionBDD.obtenerConexion();
	        ps = con.prepareStatement(
	            "UPDATE categorias SET nombre=?, categoria_padre=? WHERE codigo_cat=?"
	        );

	        ps.setString(1, categoria.getNombre());
	        ps.setInt(2, categoria.getCategoriaPadre().getCodigoCat());
	        ps.setInt(3, categoria.getCodigoCat());
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
	
	
	
	
	public ArrayList<Categoria> recuperarCategorias() throws KrakedevException {
		
		ArrayList<Categoria> categorias= new ArrayList<Categoria>();
		Connection con=null;
		ResultSet rs=null;
		Categoria categoria=null;
		PreparedStatement ps=null;
		try {
			con = ConexionBDD.obtenerConexion();
			ps = 
			con.prepareStatement("SELECT * FROM categorias");
		     rs= ps.executeQuery();
		     while(rs.next()) {
		    	 int codigo=rs.getInt("codigo_cat");
		    	 String nombre=rs.getString("nombre");
		    	 Categoria categoriaP= new Categoria();
		    	 int categoriaPadre=rs.getInt("categoria_padre");
		    	 categoriaP.setCodigoCat(categoriaPadre);
		    	 categoria=new Categoria(codigo,nombre, categoriaP);
		    	 categorias.add(categoria);
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
		
		
		return categorias;
	}

}
