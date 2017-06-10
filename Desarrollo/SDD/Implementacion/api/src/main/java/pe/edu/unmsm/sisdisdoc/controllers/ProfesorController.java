package pe.edu.unmsm.sisdisdoc.controllers;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.ws.rs.core.Response;

import com.mysql.jdbc.PreparedStatement;

import pe.edu.unmsm.sisdisdoc.database.Conexion;
import pe.edu.unmsm.sisdisdoc.models.Cursos;
import pe.edu.unmsm.sisdisdoc.models.Escuelas;
import pe.edu.unmsm.sisdisdoc.models.Modalidades;
import pe.edu.unmsm.sisdisdoc.models.Profesores;

public class ProfesorController extends BaseController{
	
	/**
	 * Retorna un json con los datos del profesor 
	 * @param codigo
	 * @param includes
	 * @return
	 * @throws SQLException
	 */
	public Response find(String codigo, String includes) throws SQLException{		
		Response response = Response.status(Response.Status.NOT_FOUND).build();
		try {
            //String query = "select codigoProducto,nombre,precio from producto where codigoProducto=?";
            String query = "select * from profesores where codigo = ?";
            cn = Conexion.getConexion();
            ps = (PreparedStatement) cn.prepareStatement(query);
            ps.setString(1, codigo);
            rs = ps.executeQuery();
            if (rs.next()) {
            	Profesores profesor = new Profesores();
            	profesor.setNombres(rs.getString("nombres"));
            	profesor.setApellidoMaterno(rs.getString("apellidoMaterno"));
            	profesor.setApellidoPaterno(rs.getString("apellidoPaterno"));            	
            	profesor.setCodigo(rs.getString("codigo"));
            	profesor.setTelefono(rs.getString("telefono"));
            	profesor.setCorreo(rs.getString("correo"));
            	profesor.setIDProfesor(rs.getInt("IDProfesor"));
            	profesor.setIDModalidad(rs.getInt("IDModalidad"));
            	
            	String q = "select * from modalidades where IDModalidad = ?";
                ps = (PreparedStatement) cn.prepareStatement(q);
                ps.setInt(1,profesor.getIDModalidad());
                ResultSet res = ps.executeQuery();                
                //obtenemos lista de cursos pertenecientes a una escuela
                if(res.next()){
                	Modalidades modalidad = new Modalidades();
                	modalidad.setTipo(res.getString("tipo"));
                	modalidad.setIDModalidad(res.getInt("IDModalidad"));
                	profesor.setModalidad(modalidad);
                }                
                response = Response.status(Response.Status.OK).entity(profesor).build();
            }
            rs.close();
            ps.close();
            //Conexion.cerrarConexion(cn);
        } catch (SQLException e) {
            throw e;// Conexion.cerrarConexion(cn);
        } finally {
            Conexion.cerrarConexion(cn);
        }
        return response;
	}	
	
	/**
	 * Este metodo actualiza el registro del profesor, solo actualiza teléfono y correo
	 * @param profesor
	 * @return Response
	 */
	public synchronized Response update(Profesores profesor, String codigo){		       
        boolean rpta = false;
        Response response = Response.status(Response.Status.NOT_MODIFIED).build(); 
        try {
            String sql = "update profesores set "
            		+ "telefono=?, correo=?, "
            		+ "editoPerfil=? "
            		+ "where codigo=?";
            //Obtenemos la conexion
            cn = Conexion.getConexion();
            //Decimos que vamos a crear una transaccion
            cn.setAutoCommit(false);
            //Preparamos la sentecia
            ps = (PreparedStatement) cn.prepareStatement(sql);
            //El siguiente parametro del parametro del comando es el nombre
            ps.setString(1, profesor.getTelefono());
            ps.setString(2, profesor.getCorreo());
            ps.setInt(3, 1);
            ps.setString(4, codigo);
            //Ejecutamos la sentencia y si nos devuelve el valor de 1 es porque
            //registro de forma correcta los datos
            rpta = ps.executeUpdate() == 1 ? true : false;
            if (rpta) {
                //Confirmamos la transaccion
                cn.commit();
                response = Response.status(Response.Status.OK).build();
            } else {
                //Negamos la transaccion
                Conexion.deshacerCambios(cn);
            }
            ps.close();
            Conexion.cerrarConexion(cn);
        } catch (SQLException e) {
            Conexion.deshacerCambios(cn);            
        } catch (Exception e) {
            Conexion.deshacerCambios(cn);            
        } finally {
        	Conexion.cerrarConexion(cn);
        }
        return response;
	}	

}
