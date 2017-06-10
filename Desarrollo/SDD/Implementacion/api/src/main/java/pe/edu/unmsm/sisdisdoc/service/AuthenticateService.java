package pe.edu.unmsm.sisdisdoc.service;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import javax.ws.rs.core.Response;

import pe.edu.unmsm.sisdisdoc.models.Profesores;

public class AuthenticateService extends BaseService{
	
	public AuthenticateService(){
		super();
	}
	
	public boolean auth(String username, String password){
		manager.getTransaction().begin();	
		List<Profesores> profesores = (List<Profesores>) manager.createQuery("FROM Profesores p WHERE p.codigo like :codigo AND p.password like :password")
				.setParameter("codigo", "10200123")
				.setParameter("password", "10200123")
				.getResultList();
		manager.getTransaction().commit();	
		manager.close();
		System.out.println(profesores.toString());
		if(profesores.size()==0){
			//throw new RuntimeException("Error");
			return false;
		}		
		return true;
	}
	
	public String issueToken(String username) {
        // Issue a token (can be a random String persisted to a database or a JWT token)
        // The issued token must be associated to a user
        // Return the issued token
		return username + ThreadLocalRandom.current().nextInt(0, 999 + 1);
    }

}
