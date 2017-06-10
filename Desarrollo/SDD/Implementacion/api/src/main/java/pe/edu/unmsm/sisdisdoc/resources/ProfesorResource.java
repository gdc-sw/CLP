package pe.edu.unmsm.sisdisdoc.resources;

import java.sql.SQLException;

import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import pe.edu.unmsm.sisdisdoc.controllers.ProfesorController;
import pe.edu.unmsm.sisdisdoc.models.Profesores;

@Path("/profesores")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ProfesorResource {
	
	//ProfesorService profesorService = new ProfesorService();
	ProfesorController profesorController = new ProfesorController();
	
	/*
	@GET	
	public Response getProfesores(@QueryParam("include") String include){		
		return profesorController.getAll();
	}*/
	
	@GET
	@Path("/{codigo}")	
	public Response getProfesor(@PathParam("codigo") String codigo,
									@QueryParam("includes") String includes) throws SQLException{		
		return profesorController.find(codigo, includes);
	}
	
	@PUT
	@Path("/{codigo}")	
	public Response updateProfesor(@PathParam("codigo") String codigo, @Valid Profesores profesor){
		
		System.out.println("Mi request es: "+profesor.toString());
		return profesorController.update(profesor, codigo);
	}	
}
