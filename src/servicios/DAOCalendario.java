package servicios;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import entidades.Calendario;
import entidades.Usuario;


public class DAOCalendario {

	private static DAOCalendario daocalendario;
	
	private DAOCalendario(){}

	public static DAOCalendario getInstance() {
		if(daocalendario==null)
			daocalendario=new DAOCalendario();
		return daocalendario;
	}

	
//	public Calendario findById(Integer id) {
//		
//		EntityManager entityManager=EMF.createEntityManager();
//		Calendario c=entityManager.find(Calendario.class, id);
//		entityManager.close();
//		return c;
//	
//	}
//	
	public static void crearCalendario(String nombre, int usuario ,EntityManager em) {
		em.getTransaction().begin();
		Usuario u= DAOUsuario.getUsuario(usuario, em);
		Calendario nc = new Calendario(nombre, u);
		em.persist(nc);
		em.getTransaction().commit();
	}
	
	public static Calendario getCalendario(int idCalendario,EntityManager em) {
		String jpql = "Select c From Calendario c where c.id =?1";
		Query query = em.createQuery(jpql); 
		query.setParameter(1, idCalendario);
		return (Calendario) query.getSingleResult();
		
		

}

}