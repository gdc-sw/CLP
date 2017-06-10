package pe.edu.unmsm.sisdisdoc.controllers;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.Response;

import com.mysql.jdbc.PreparedStatement;

import pe.edu.unmsm.sisdisdoc.database.Conexion;
import pe.edu.unmsm.sisdisdoc.models.Cursos;
import pe.edu.unmsm.sisdisdoc.models.CursosDisponibles;
import pe.edu.unmsm.sisdisdoc.models.Disponibilidades;
import pe.edu.unmsm.sisdisdoc.models.Modalidades;

public class DisponibilidadesController extends BaseController{
	
	/**
	 * Este metodo valida la cantidad de horas ingresadas e 
	 * ingresa las disponibilidades horarias de un profesor en la bd
	 * @param disponibilidades
	 * @return Response 
	 * @throws SQLException 
	 */
	public synchronized Response store(List<Disponibilidades> disponibilidades, int IDProfesor) throws SQLException {
		Response response = errorMessage("Error en el servidor");		
		cn = Conexion.getConexion();
        cn.setAutoCommit(false);        
		if(disponibilidades != null  && disponibilidades!=null && disponibilidades.size()>0){
			Modalidades modalidad;
			modalidad = getModalidad(IDProfesor);
			if(modalidad.getTipo().equals("part-time")){					
				if(disponibilidades.size()>=12){
			        response = insertDisponibilidades(disponibilidades, IDProfesor, response);
				}else{
					response = errorMessage("{\"error\": \"Debe ingresar mas de 12 horas\"}");
				}
			}else if(modalidad.getTipo().equals( "full-time")){
				if(disponibilidades.size()>=16){
			        response = insertDisponibilidades(disponibilidades, IDProfesor, response);
				}else{
					response = errorMessage("{\"error\": \"Debe ingresar mas de 16 horas\"}");
					
				}
			}
		}else if(disponibilidades.size()==0)
			response = errorMessage("{\"error\": \"No ingresó disponibilidades\"}");
		
		return response;
	}
	
	private Response errorMessage(String message){
		return Response.status(Response.Status.BAD_REQUEST).entity(message).build();
	}
	/**
	 * Este método ingresa las disponibilidades horarias de un profesor en la bd
	 * @param disponibilidades
	 * @param IDProfesor
	 * @param response
	 * @return
	 */
	private Response insertDisponibilidades(List<Disponibilidades> disponibilidades, int IDProfesor, Response response) {
		boolean rpta = false;
		boolean rpt = false;		
		try {
			for(Disponibilidades disponibilidad: disponibilidades){
		        String sql = "insert into disponibilidades(IDProfesor, IDIntervalo_hora, IDCiclo, dia) "
		        		+ "values(?,?,?,?)";		        
		        ps = (PreparedStatement) cn.prepareStatement(sql);
		        ps.setInt(1, disponibilidad.getIdprofesor());
		        ps.setInt(2, disponibilidad.getIdintervalo_hora());
		        ps.setInt(3, disponibilidad.getIdciclo());		            
		        ps.setString(4, disponibilidad.getDia());
		        IDProfesor = disponibilidad.getIdprofesor();
		        rpta = ps.executeUpdate() == 1 ? true : false;
		        if (rpta) {		                		            	
		        	cn.commit();
		        } else {
		            Conexion.deshacerCambios(cn);
		        }
		        ps.close();
			}			
			String q = "update profesores set registroDisponibilidadHoraria =? where IDProfesor = ?";
			PreparedStatement p = (PreparedStatement) cn.prepareStatement(q);
			p.setInt(1, 1);
			p.setInt(2, IDProfesor);
			rpt = p.executeUpdate() == 1 ? true: false;
			if(rpt){
				cn.commit();
				response = Response.status(Response.Status.CREATED).build();
			} else {
				Conexion.cerrarConexion(cn);
			}
			p.close();
					        	
		} catch (SQLException e) {					
			response = errorMessage("{\"error\": \""+e.getMessage()+"\"}");
		    Conexion.deshacerCambios(cn);
		    Conexion.cerrarConexion(cn);
		} catch (Exception e) {				
			response = errorMessage("{\"error\": \""+e.getMessage()+"\"}");
		    Conexion.deshacerCambios(cn);
		    Conexion.cerrarConexion(cn);
		}
		return response;
	}
		
	/**
	 * Inserta los cursos disponibles que un profesor envió
	 * @param disponibilidades
	 * @param IDProfesor
	 * @param response
	 * @return Response
	 */
	private Response insertCursosDisponibles(List<CursosDisponibles> disponibilidades, int IDProfesor, Response response) {
		boolean rpta = false;
		boolean rpt = false;		
		try {
			for(CursosDisponibles disponibilidad: disponibilidades){				
		        String sql = "insert into cursos_disponibles(IDProfesor, IDCurso, IDCiclo) "
		        		+ "values(?,?,?)";		        
		        ps = (PreparedStatement) cn.prepareStatement(sql);
		        ps.setInt(1, disponibilidad.getIdprofesor());
		        ps.setInt(2, disponibilidad.getIdcurso());
		        ps.setInt(3, disponibilidad.getIdciclo());		            
		        
		        rpta = ps.executeUpdate() == 1 ? true : false;
		        if (rpta) {		                		            	
		        	cn.commit();
		        } else {
		            Conexion.deshacerCambios(cn);
		        }
		        ps.close();
			}			
			String q = "update profesores set registroCursos =? where IDProfesor = ?";
			PreparedStatement p = (PreparedStatement) cn.prepareStatement(q);
			p.setInt(1, 1);
			p.setInt(2, IDProfesor);
			rpt = p.executeUpdate() == 1 ? true: false;
			if(rpt){
				cn.commit();
				response = Response.status(Response.Status.CREATED).build();
			} else {
				Conexion.cerrarConexion(cn);
			}
			p.close();
					        	
		} catch (SQLException e) {					
			response = errorMessage("{\"error\": \""+e.getMessage()+"\"}");
		    Conexion.deshacerCambios(cn);
		    Conexion.cerrarConexion(cn);
		} catch (Exception e) {				
			response = errorMessage("{\"error\": \""+e.getMessage()+"\"}");
		    Conexion.deshacerCambios(cn);
		    Conexion.cerrarConexion(cn);
		}
		return response;
	}
	
	/**
	 * Este método retorna la modalidad de trabajo del profesor
	 * @param IDProfesor
	 * @return
	 * @throws SQLException
	 */
	private Modalidades getModalidad(int IDProfesor) throws SQLException{
		Modalidades modalidad = new Modalidades();
		PreparedStatement pstm;
		ResultSet resulSet;		
		try {
            String query = "select m.tipo as modalidad, m.min_cantidad_cursos as cant_cursos from profesores as p "
            		+ "inner join modalidades as m on m.IDModalidad = p.IDModalidad where p.IDProfesor = ? LIMIT 1";
            pstm = (PreparedStatement) cn.prepareStatement(query);
            pstm.setInt(1, IDProfesor);
            resulSet = pstm.executeQuery();
            if (resulSet.next()) {            	
            	modalidad.setCantidad_cursos(resulSet.getInt("cant_cursos"));
            	modalidad.setTipo(resulSet.getString("modalidad"));
            	//modalidad = resulSet.getString("modalidad");
            }
            resulSet.close();
            pstm.close();
        } catch (SQLException e) {
        	System.out.println(e.getMessage());
            throw e;
        }		
		return modalidad;
	}
	
	/**
	 * Este metodo valida la cantidad de cursos ingresados e 
	 * ingresa las cursos disponibles de un profesor en la bd
	 * @param disponibilidades
	 * @param IDProfesor
	 * @return Response
	 * @throws SQLException
	 */
	public synchronized Response storeCursosDisponibles(List<CursosDisponibles> disponibilidades, int IDProfesor) throws SQLException {		
		Response response = errorMessage("Error en el servidor");		
		cn = Conexion.getConexion();
        cn.setAutoCommit(false);        
		if(disponibilidades != null  && disponibilidades!=null && disponibilidades.size()>0){
			Modalidades modalidad;
			modalidad = getModalidad(IDProfesor);
			if(modalidad.getCantidad_cursos() <= disponibilidades.size() ) {
				response = insertCursosDisponibles(disponibilidades, IDProfesor, response);
			}else{				
				response = errorMessage("{\"error\": \"Debe ingresar un mínimo de "+modalidad.getCantidad_cursos()+" cursos\"}");				
			}			
		}else if(disponibilidades.size()==0)
			response = errorMessage("{\"error\": \"No registró ningun curso\"}");
		
		return response;
	}
	
	/**
	 * Devuelve la disponibilidad de un profesor
	 * @param idProfesor
	 * @return Response
	 */
	public  synchronized Response getDisponibilidadHoraria(int idProfesor) {
		Response response = Response.status(Response.Status.NOT_FOUND).build();
        ArrayList<Disponibilidades> lista = new ArrayList<Disponibilidades>();        
        try {        	
            /*String query = "select p.codigo from disponibilidades d "
            		+ "JOIN profesores p ON d.IDProfesor =p.IDProfesor "
            		+ "JOIN intervalo_horas ih ON ih.IDIntervalo_hora = d.IDIntervalo_hora "
            		+ "JOIN ciclos c ON c.IDCiclo = d.IDCiclo "
            		+ "WHERE p.IDProfesor = ? "
            		+ "AND IDCiclo = 1";*/
        	String query = "select * from disponibilidades where IDProfesor=?";
            cn = Conexion.getConexion();            
            ps = (PreparedStatement) cn.prepareStatement(query);
            ps.setInt(1, idProfesor);                        
            rs = ps.executeQuery();
            while (rs.next()) {            						            	
            	Disponibilidades disponibilidad = new Disponibilidades();
            	disponibilidad.setIdciclo(rs.getInt("IDCiclo"));
            	disponibilidad.setIdprofesor(rs.getInt("IDProfesor"));
            	disponibilidad.setIdintervalo_hora(rs.getInt("IDIntervalo_hora"));
            	disponibilidad.setDia(rs.getString("dia"));            	                               
                lista.add(disponibilidad);                                
            }
            response = Response.status(Response.Status.OK).entity(lista).build();
            rs.close();            
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
	 * Devuelve los cursos disponibles de un profesor
	 * @param idProfesor
	 * @return Response
	 */
	public  synchronized Response getCursosDisponibles(int idProfesor) {
		Response response = Response.status(Response.Status.NOT_FOUND).build();
        ArrayList<CursosDisponibles> lista = new ArrayList<CursosDisponibles>();        
        try {        	            
        	String query = "select * from cursos_disponibles where IDProfesor=?";
            cn = Conexion.getConexion();            
            ps = (PreparedStatement) cn.prepareStatement(query);
            ps.setInt(1, idProfesor);                        
            rs = ps.executeQuery();
            while (rs.next()) {    
            	CursosDisponibles disponibilidad = new CursosDisponibles();
            	disponibilidad.setIdciclo(rs.getInt("IDCiclo"));
            	disponibilidad.setIdcurso(rs.getInt("IDCurso"));
            	disponibilidad.setIdprofesor(rs.getInt("IDProfesor"));
            	
                String q = "select * from cursos where IDCurso = ? LIMIT 1";
                ps = (PreparedStatement) cn.prepareStatement(q);
                ps.setInt(1,disponibilidad.getIdcurso());
                ResultSet res = ps.executeQuery();                
                if(res.next()){
                	Cursos curso = new Cursos(res.getInt("IDCurso"), res.getString("nombre"));
                	disponibilidad.setCurso(curso);
                }                
                
                lista.add(disponibilidad);                                
            }
            response = Response.status(Response.Status.OK).entity(lista).build();
            rs.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } catch(Exception e){
        	System.out.println(e.getMessage());
        } finally {
            Conexion.cerrarConexion(cn);            
        }        
        return response;
    }		
	
	public class ErrorMessage{
		private String error;
		
		public String getError() {
			return error;
		}

		public void setError(String error) {
			this.error = error;
		}

		public ErrorMessage(String message){
			this.error = message;
		}
	}
}
