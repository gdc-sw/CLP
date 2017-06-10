package pe.edu.unmsm.sisdisdoc.controllers;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mysql.jdbc.PreparedStatement;

import pe.edu.unmsm.sisdisdoc.database.Conexion;
import pe.edu.unmsm.sisdisdoc.models.Cursos;
import pe.edu.unmsm.sisdisdoc.models.Disponibilidades;
import pe.edu.unmsm.sisdisdoc.models.Escuelas;
import pe.edu.unmsm.sisdisdoc.models.IntervaloHoras;

public class IntervaloHorasController extends BaseController{

	/**
	 * 
	 * @return
	 */
	public  synchronized Response getAll() {
		Response response = Response.status(Response.Status.NOT_FOUND).build();
        ArrayList<IntervaloHoras> lista = new ArrayList<IntervaloHoras>();        
        try {
            String query = "select * from intervalo_horas";
            cn = Conexion.getConexion();
            Statement stm = cn.createStatement();                        
            rs = stm.executeQuery(query);
            int count =0;
            while (rs.next()) {            									
            	IntervaloHoras intervalo = new IntervaloHoras();
            	intervalo.setHora_fin(rs.getInt("hora_fin"));
            	intervalo.setHora_inicio(rs.getInt("hora_inicio"));
            	intervalo.setIDIntervalo_horas(rs.getInt("IDIntervalo_horas"));                                
                lista.add(intervalo);
                count++;
            }           
            if(count>0){
            	Gson gson = new GsonBuilder().create();
        		String intervalosJSON = gson.toJson(lista);
            	response = Response.status(Status.OK).entity(intervalosJSON).build();
            }
            rs.close();
            stm.close();
        } catch (SQLException e) {
            
        } finally {
            try {
                Conexion.cerrarConexion(cn);
            } catch (Exception e) {
            }
        }
        return response;
    }
	
}
