package com.krakedev.inventario.bdd;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.krakedev.entidades.Categoria;
import com.krakedev.entidades.Producto;
import com.krakedev.entidades.Proveedor;
import com.krakedev.entidades.UnidadDeMedida;
import com.krakedev.exceptions.KrakedevException;
import com.krakedev.inventario.utils.ConexionBDD;

public class ProductosBDD {
	
public ArrayList<Producto> buscar(String subcadena) throws KrakedevException {
		
		Connection con=null;
		ResultSet rs=null;
		Producto producto=null;
		ArrayList<Producto> productos= new ArrayList<Producto>();
		PreparedStatement ps=null;
		
		try {
			con = ConexionBDD.obtenerConexion();
			ps = 
					con.prepareStatement(
						    "SELECT prod.codigo_pro, prod.nombre as nombre_producto, udm.codigo_udm as nombre_udm, udm.descripcion as descripcion_udm, "
						  + "cast(prod.precio_venta as decimal(6,2)), prod.tiene_iva, cast(prod.coste as decimal(5,4)), prod.categoria_serial as categoria, cat.nombre as nombre_categoria, prod.stock "
						  + "FROM producto prod, unidad_medida udm, categorias cat "
						  + "WHERE prod.udm = udm.codigo_udm "
						  + "AND prod.categoria_serial = cat.codigo_cat "
						  + "AND upper(prod.nombre) LIKE ?"
						);
			 ps.setString(1, "%"+subcadena.toUpperCase()+"%");
		     rs= ps.executeQuery();
		     while(rs.next()) {
		    	 String codigoPro=rs.getString("codigo_pro");
		    	 String nombreProducto=rs.getString("nombre_producto");
		    	 String nombreUnidadMedida=rs.getString("nombre_udm");
		    	 String descripcionUdm=rs.getString("descripcion_udm");
                 BigDecimal precioVenta=rs.getBigDecimal("precio_venta");
                 boolean tieneIva=rs.getBoolean("tiene_iva");
                 BigDecimal coste=rs.getBigDecimal("coste");
                 int codigoCategoria=rs.getInt("categoria");
                 String nombreCategoria=rs.getString("nombre_categoria");
                 int stock=rs.getInt("stock");
  
		    
		    	 UnidadDeMedida udm= new UnidadDeMedida();
		    	 udm.setNombre(nombreUnidadMedida);
		    	 udm.setDescripcion(descripcionUdm);
		    	 Categoria categoria=new Categoria();
		    	 categoria.setCodigoCat(codigoCategoria);
		    	 categoria.setNombre(nombreCategoria);
		    	 
		    	 producto=new Producto( codigoPro, nombreProducto,  udm, precioVenta,
		    				 tieneIva, coste, categoria,  stock );
		    	
		    	productos.add(producto);
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
		return productos;
	}


}
