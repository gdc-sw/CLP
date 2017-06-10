package pe.edu.unmsm.sisdisdoc.service;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import pe.edu.unmsm.sisdisdoc.database.Conexion;
import pe.edu.unmsm.sisdisdoc.models.Escuelas;

public class EscuelaService extends BaseService<Escuelas>{	
	
	public EscuelaService(){
		super("Escuelas");
	}	
	
	public Escuelas getEntity(int id){
		
		Escuelas escuela = manager.find(Escuelas.class, id);		
		
		//Profesores query = (Profesores) manager.createQuery("from Profesores p where p.codigo = :codigo ").setMaxResults(1).getSingleResult();		
		return escuela;
	}	

}
