package pe.edu.unmsm.sisdisdoc.resources;

import java.sql.SQLException;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import pe.edu.unmsm.sisdisdoc.controllers.DisponibilidadesController;
import pe.edu.unmsm.sisdisdoc.models.Cursos;
import pe.edu.unmsm.sisdisdoc.models.CursosDisponibles;
import pe.edu.unmsm.sisdisdoc.models.Disponibilidades;

@Path("/disponibilidades")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class DisponibilidadesResource {
	
	DisponibilidadesController disponibilidadesController = new DisponibilidadesController();
	
	@GET
	@Path("/{idProfesor}")
	public Response getDisponibilidadHoraria(@PathParam("idProfesor") int idProfesor){
		return disponibilidadesController.getDisponibilidadHoraria(idProfesor);
	}
	
	@GET
	@Path("cursos/{idProfesor}")
	public Response getCursosDisponibles(@PathParam("idProfesor") int idProfesor){
		return disponibilidadesController.getCursosDisponibles(idProfesor);
	}
	
	@POST
	@Path("/{IDProfesor}")
	public Response storeDisponibilidades(List<Disponibilidades> disponibilidades, @PathParam("IDProfesor") int IDProfesor) throws SQLException{			
		return disponibilidadesController.store(disponibilidades, IDProfesor);
	}
	
	@POST
	@Path("cursos/{idProfesor}")
	public Response storeDisponibilidadCursos(List<CursosDisponibles> disponibilidades, @PathParam("idProfesor") int idProfesor) throws SQLException{
		System.out.println(disponibilidades.toString());		
		return disponibilidadesController.storeCursosDisponibles(disponibilidades, idProfesor);
	}
	
}
