package pe.edu.unmsm.sisdisdoc.controllers;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.ws.rs.core.Response;

import com.mysql.jdbc.PreparedStatement;

import pe.edu.unmsm.sisdisdoc.database.Conexion;
import pe.edu.unmsm.sisdisdoc.models.Cursos;
import pe.edu.unmsm.sisdisdoc.models.Escuelas;

public class EscuelasController extends BaseController{
	
	/**
	 * Metodo utilizado para obtener todas las escuelas de nuestra base de datos 	
	 * @param t
	 * @return ArrayList
	 */
	public  synchronized Response getAll() {
		Response response = Response.status(Response.Status.NOT_FOUND).build();
        ArrayList<Escuelas> lista = new ArrayList<Escuelas>();        
        try {        	
            String query = "select * from escuelas";
            cn = Conexion.getConexion();
            Statement stm = cn.createStatement();            
            
            rs = stm.executeQuery(query);
            while (rs.next()) {            						            	
            	Escuelas escuela= new Escuelas(rs.getInt("IDEscuela"), rs.getString("nombre"));                
                String q = "select * from cursos where IDEscuela = ?";
                ps = (PreparedStatement) cn.prepareStatement(q);
                ps.setInt(1,escuela.getIDEscuela());
                ResultSet res = ps.executeQuery();
                ArrayList<Cursos> cursos = new ArrayList<Cursos>();
                while(res.next()){
                	Cursos curso = new Cursos(res.getInt("IDCurso"), res.getString("nombre"));
                	cursos.add(curso);                	
                }
                escuela.setCursos(cursos);                
                lista.add(escuela);                                
            }
            response = Response.status(Response.Status.OK).entity(lista).build();
            rs.close();
            stm.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } catch(Exception e){
        	System.out.println(e.getMessage());
        } finally {
            Conexion.cerrarConexion(cn);            
        }        
        return response;
    }
	
	/**
	 * Retorna los datos de la escuela con el ID que se pasa por parametro
	 * @param IDEscuela
	 * @return
	 * @throws SQLException
	 */
	public Escuelas find(int IDEscuela) throws SQLException{		
		Escuelas escuela = new Escuelas();
		try {
            //String query = "select codigoProducto,nombre,precio from producto where codigoProducto=?";
            String query = "select * from escuelas where IDEscuela = ?";
            cn = Conexion.getConexion();
            ps = (PreparedStatement) cn.prepareStatement(query);
            ps.setInt(1, IDEscuela);
            rs = ps.executeQuery();
            if (rs.next()) {
            	escuela.setIDEscuela(rs.getInt("IDEscuela"));
            	escuela.setNombre(rs.getString("nombre"));
            	String q = "select * from cursos where IDEscuela = ?";
                ps = (PreparedStatement) cn.prepareStatement(q);
                ps.setInt(1,escuela.getIDEscuela());
                ResultSet res = ps.executeQuery();
                ArrayList<Cursos> cursos = new ArrayList<Cursos>();
                //obtenemos lista de cursos pertenecientes a una escuela
                while(res.next()){
                	Cursos curso = new Cursos(res.getInt("IDEscuela"), res.getString("nombre"));
                	cursos.add(curso);                	
                }
                escuela.setCursos(cursos);                
            }
            rs.close();
            ps.close();
            //Conexion.cerrarConexion(cn);
        } catch (SQLException e) {
        	System.out.println(e.getMessage());
            throw e;// Conexion.cerrarConexion(cn);
        } catch (Exception e){
        	System.out.println(e.getMessage());
        	throw e;
        } finally {
            Conexion.cerrarConexion(cn);
        }		
        return escuela;
	}

}
