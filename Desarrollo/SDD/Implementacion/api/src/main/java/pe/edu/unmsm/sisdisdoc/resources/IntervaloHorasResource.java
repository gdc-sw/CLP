package pe.edu.unmsm.sisdisdoc.resources;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import pe.edu.unmsm.sisdisdoc.controllers.IntervaloHorasController;
import pe.edu.unmsm.sisdisdoc.models.Disponibilidades;

@Path("/intervalo-horas")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class IntervaloHorasResource {
	
	//IntervaloHorasService intervaloHorasService = new IntervaloHorasService();
	IntervaloHorasController intervaloHorasController = new IntervaloHorasController();
	
	@GET	
    public Response getIntervaloHoras() 
    {
		return intervaloHorasController.getAll();
		
    }	

}
