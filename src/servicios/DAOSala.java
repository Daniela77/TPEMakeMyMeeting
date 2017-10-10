package servicios;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import entidades.Sala;
//import entidades.Actividad;

public class DAOSala {
	
	private static DAOSala daoSala;
	
	private DAOSala(){
	}
	
	public static DAOSala getInstance() {
		if(daoSala == null)
			daoSala = new DAOSala();
		return daoSala;
	}
	
	public static Sala crearSala(String nombre, String direccion, EntityManager em) {
		// Crea una nueva sala utilizando el nombre y direccion pasados por parametro
		em.getTransaction( ).begin( );
		Sala salaA = new Sala(nombre,direccion);
		em.persist(salaA);
		em.getTransaction().commit();
		return salaA;
	}
	
	public static Sala getSala(int id, EntityManager em) {
		// regresa una sala en base a su ID
		String jpql = "SELECT s FROM Site s WHERE s.id = ?"; 
		Query query = em.createQuery(jpql); 
		query.setParameter(1, id);
		return (Sala) query.getSingleResult();
		 
	}
}
