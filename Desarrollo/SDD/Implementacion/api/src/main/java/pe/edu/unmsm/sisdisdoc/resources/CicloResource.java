package pe.edu.unmsm.sisdisdoc.resources;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import pe.edu.unmsm.sisdisdoc.models.Ciclos;
import pe.edu.unmsm.sisdisdoc.service.CicloService;

@Path("/ciclos")
public class CicloResource {
	
	CicloService cicloService = new CicloService();
	
	@GET
	@Produces("application/json")	
    public List<Ciclos> getEscuelas() 
    {
		return cicloService.getAll();
		
    }

}
