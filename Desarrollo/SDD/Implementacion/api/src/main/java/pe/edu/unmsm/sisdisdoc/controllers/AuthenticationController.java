package pe.edu.unmsm.sisdisdoc.controllers;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.ws.rs.core.Response;

import com.mysql.jdbc.PreparedStatement;

import pe.edu.unmsm.sisdisdoc.database.Conexion;
import pe.edu.unmsm.sisdisdoc.models.Profesores;

public class AuthenticationController extends BaseController{
	
	private static final char[] toHex = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };
	
	/*public Response authenticate(String user, String password) throws SQLException{
		String username = user;// from UI input
		String plaintext_password = password;// from UI input

		String pwhash_from_passwd = makePwHash(username, plaintext_password);
		String pwhash_from_db = "";
		
		try {
            String query = "select password from profesores where codigo = ? password = ? LIMIT 1";
            cn = Conexion.getConexion();
            ps = (PreparedStatement) cn.prepareStatement(query);
            ps.setString(1, username);
            ps.setString(2, password);
            rs = ps.executeQuery();
            if (rs.next()) {
            	pwhash_from_db = rs.getString("password");
            }else{
            	return Response.status(Response.Status.UNAUTHORIZED).entity(new UnauthorizedMessage()).build();
            }
		} catch (SQLException e) {
            throw e;
        } finally{
			Conexion.cerrarConexion(cn);
		}

		if (pwhash_from_db.equals(pwhash_from_passwd)) {
		    return Response.status(Response.Status.ACCEPTED).build();
		} else {
			return Response.status(Response.Status.UNAUTHORIZED).build();
		}
	}*/
	
	public Response authenticate(String username, String password ) throws SQLException{
		String pw_from_db = "";
		Profesores profesor = new Profesores();
		List<String> errors = validate(username, password);		
		
		if(errors.size()>0){
			return Response.status(Response.Status.BAD_REQUEST).entity("{\"error\": \""+errors.toString()+"\"}").build();
		}
		
		try {
            String query = "select * from profesores where codigo = ? AND password = ? LIMIT 1";
            cn = Conexion.getConexion();
            ps = (PreparedStatement) cn.prepareStatement(query);
            ps.setString(1, username);
            ps.setString(2, password);
            rs = ps.executeQuery();
            if (rs.next()) {
            	pw_from_db = rs.getString("password");
            	profesor.setNombres(rs.getString("nombres"));
            	profesor.setApellidoMaterno(rs.getString("apellidoMaterno"));
            	profesor.setApellidoPaterno(rs.getString("apellidoPaterno"));            	
            	profesor.setCodigo(rs.getString("codigo"));
            	profesor.setTelefono(rs.getString("telefono"));
            	profesor.setCorreo(rs.getString("correo"));
            	profesor.setIDProfesor(rs.getInt("IDProfesor"));
            	profesor.setIDModalidad(rs.getInt("IDModalidad"));
            	profesor.setEditoPerfil(rs.getInt("EditoPerfil"));
            	profesor.setRegistroCursos(rs.getInt("registroCursos"));
            	profesor.setRegistroDisponibilidadHoraria(rs.getInt("registroDisponibilidadHoraria"));
            }else{
            	return Response.status(Response.Status.UNAUTHORIZED).entity("{\"error\": \"Usuario no autorizado\"}").build();
            }
		} catch (SQLException e) {
            throw e;
        } finally{
			Conexion.cerrarConexion(cn);
		}
		
		if (pw_from_db.equals(password)) {
			System.out.println(profesor.toString());
		    return Response.status(Response.Status.ACCEPTED).entity(profesor).build();
		} else {
			return Response.status(Response.Status.UNAUTHORIZED).build();
		}
		
	}
	
	private List<String> validate(String username, String password){
		ArrayList<String> errors = new ArrayList<>();
		String regexSpecialCharacters = "[^\\w]";
		String regexUpperCase = "[A-Z]";
		String regexLength = "[\\w]{8,15}";
		Pattern pattern ;
		Matcher m;
		
		pattern = Pattern.compile(regexSpecialCharacters);
		m = pattern.matcher(username);		
		if(m.find()){
			errors.add("Usuario no debe contener caracteres especiales");					
		}
		
		pattern = Pattern.compile(regexUpperCase);
		m = pattern.matcher(username);
		if(m.find()){
			errors.add("Usuario no debe contener mayúsculas");
		}
		
		pattern = Pattern.compile(regexLength);
		m = pattern.matcher(username);
		if(!m.find()){
			errors.add("Usuario debe contener entre 8 y 15 caracteres");
		}			
		
		pattern = Pattern.compile(regexUpperCase);
		m = pattern.matcher(password);
		if(m.find()){
			errors.add("Password no debe contener mayúsculas");
		}
		
		pattern = Pattern.compile(regexLength);
		m = pattern.matcher(password);
		if(!m.find()){
			errors.add("Password debe contener entre 8 y 15 caracteres");
		}
		return errors;
	}

}
