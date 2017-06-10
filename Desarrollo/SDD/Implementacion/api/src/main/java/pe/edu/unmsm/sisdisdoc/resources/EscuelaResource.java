package pe.edu.unmsm.sisdisdoc.resources;

import java.sql.SQLException;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import pe.edu.unmsm.sisdisdoc.controllers.EscuelasController;
import pe.edu.unmsm.sisdisdoc.service.EscuelaService;

@Path("/escuelas")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class EscuelaResource {
		
	EscuelasController controller = new EscuelasController();
	/*
	@GET
	@Produces("application/json")	
    public List<Escuelas> getEscuelas() 
    {
		return escuelaService.getAll();
		
    }*/
	
	@GET	
    public Response getEscuelas() 
    {		
		System.out.println("entré a");
		
		//ObjectMapper om;
		/*Gson gson = new GsonBuilder().create();
		String escuelasJSON = gson.toJson(controller.getAll());
		return Response.ok(escuelasJSON, MediaType.APPLICATION_JSON).build();*/
		return controller.getAll();
		//return Response.ok(escuelaService.getAll(), MediaType.APPLICATION_JSON).build();
		
		//return Response.status(Response.Status.FORBIDDEN).entity(escuelaService.getAll()).build();
		//return escuelaService.getAll();
		
    }
	
	@GET
	@Path("/{id}")	
	public Response getEscuela(@PathParam("id") Integer id) throws SQLException{
		Gson gson = new GsonBuilder().create();
		String escuelasJSON = gson.toJson(controller.find(id));
		return Response.ok(escuelasJSON, MediaType.APPLICATION_JSON).build();
	}

}
