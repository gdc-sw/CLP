package pe.edu.unmsm.sisdisdoc.service;


import pe.edu.unmsm.sisdisdoc.models.Profesores;

public class ProfesorService extends BaseService<Profesores>{
	
	public ProfesorService(){
		super("Profesores");
	}
	
	public Profesores getEntity(int id){
		Profesores profesor = manager.find(Profesores.class, id);
		//Profesores query = (Profesores) manager.createQuery("from Profesores p where p.codigo = :codigo ").setMaxResults(1).getSingleResult();		
		return profesor;
	}

}
