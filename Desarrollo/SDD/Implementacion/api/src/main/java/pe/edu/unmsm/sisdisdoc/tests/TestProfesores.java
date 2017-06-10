package pe.edu.unmsm.sisdisdoc.tests;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.ws.rs.core.Response;

import com.mysql.jdbc.PreparedStatement;

import pe.edu.unmsm.sisdisdoc.controllers.BaseController;
import pe.edu.unmsm.sisdisdoc.database.Conexion;
import pe.edu.unmsm.sisdisdoc.models.Cursos;
import pe.edu.unmsm.sisdisdoc.models.Escuelas;

public class TestProfesores extends BaseController{
		
	//private static EntityManager manager;
	
	//private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("persistencia");
	
	public static void main(String[] args){
		
		//emf = Persistence.createEntityManagerFactory("persistencia");
		//manager = emf.createEntityManager();		
		/*
		manager.getTransaction().begin();
		
		Modalidades modalidad = manager.find(Modalidades.class, 1);
		//manager.persist(profe);
		modalidad.setTipo("part-time");
		manager.getTransaction().commit();
		//List<Profesor> profesores = (List<Profesor>)manager.createQuery("FROM profesores");		
		//System.out.println("en esta bd hay "+profesores.size()+" profesores");	*/
		imp();
		
	}
		
	/*@SuppressWarnings("unchecked")
	private static void imprimirTodo(){
		List<Escuelas> escuelas = (List<Escuelas>)manager.createQuery("FROM Escuelas").getResultList();
		System.out.println("en esta bd hay: "+escuelas.size()+" modalidades");
		
		for(Escuelas e: escuelas){
			System.out.println(e.toString());
		}
	}*/
	
	private static void imp(){		
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
                	Cursos curso = new Cursos(res.getInt("IDEscuela"), res.getString("nombre"));
                	cursos.add(curso);                	
                }
                escuela.setCursos(cursos);                
                lista.add(escuela);                                
            }
            rs.close();
            stm.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } catch(Exception e){
        	System.out.println(e.getMessage());
        }finally{
            Conexion.cerrarConexion(cn);            
        }
        for(Escuelas escuela : lista){
        	System.out.println(lista.toString());
        }
        
	}

}
