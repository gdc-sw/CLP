package pe.edu.unmsm.sisdisdoc.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

abstract class BaseService<T> {
	public static EntityManager manager;
	
	public static EntityManagerFactory emf = Persistence.createEntityManagerFactory("persistencia");
	
	private String entityName;
	
	public BaseService(){
		
	}
	
	public BaseService(String entityName){
		manager = emf.createEntityManager();
		this.entityName = entityName;
	}
	
	@SuppressWarnings("unchecked")
	public List<T> getAll(){
		List<T> lentity = null;		
		manager.getTransaction().begin();						
		lentity = (List<T>)manager.createQuery("FROM "+this.entityName).getResultList();
		manager.getTransaction().commit();
		manager.close();
		return lentity;/*
		try {
			lentity = (List<T>)manager.createQuery("FROM "+this.entityName).getResultList();
			manager.getTransaction().commit();
        } catch (Exception e) {
        	manager.getTransaction().rollback();
            throw e;
        }		
		manager.close();
		
		return lentity;*/
	}
	

}
