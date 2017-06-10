package pe.edu.unmsm.sisdisdoc.resources;

import java.sql.SQLException;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import pe.edu.unmsm.sisdisdoc.controllers.AuthenticationController;
import pe.edu.unmsm.sisdisdoc.models.Credentials;
import pe.edu.unmsm.sisdisdoc.service.AuthenticateService;

@Path("/authentication")
public class AuthenticationResource {
	
	//AuthenticateService authenticateService = new AuthenticateService();
	AuthenticationController authenticationController = new AuthenticationController();
	@POST
	@Consumes(MediaType.APPLICATION_JSON)	
	@Produces(MediaType.APPLICATION_JSON)	
	public Response authenticate(Credentials credentials) throws SQLException{
		
		/*boolean a = authenticateService.auth(credentials.getUsername(), credentials.getPassword());
		if(!a){
			Response.status(Response.Status.BAD_REQUEST).build(); 
		}
		return Response.status(Response.Status.ACCEPTED).build();
		*/
		return authenticationController.authenticate(credentials.getUsername(), credentials.getPassword());
		/*try {

            // Authenticate the user using the credentials provided

			authenticateService.auth(credentials.getUsername(), credentials.getPassword());

            // Issue a token for the user
            String token = authenticateService.issueToken(credentials.getUsername());

            // Return the token on the response
            return Response.ok(token).build();

        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }    */
		
	}

	
}
