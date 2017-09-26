package servicios;

import java.util.Date;
import java.util.List;
import javax.persistence.Query;
import javax.persistence.EntityManager;
import entidades.Actividad;
import entidades.Calendario;
import entidades.Sala;
import entidades.Usuario;

public class DAOActividad {
	
	private static DAOActividad daoactividad;

	private DAOActividad(){}

	public static DAOActividad getInstance() {
		if(daoactividad==null)
			daoactividad=new DAOActividad();
		return daoactividad;
	}

	public static void crearActividad(String nombre,int idCalendario, int usuario ,Date fechaInicio, Date fechafin ,int sala ,EntityManager em) {
	em.getTransaction().begin();
	Usuario u= DAOUsuario.getUsuario(usuario, em);
	Sala s= DAOSala.getSala(sala, em);
	Calendario c =DAOCalendario.getCalendario(idCalendario, em);
	Actividad na = new Actividad(nombre,u,fechaInicio,fechafin,s,c);
	em.persist(na);
	em.getTransaction().commit();
	}
		
	public static void GetActividades(EntityManager em) {
		String jpql = "Select a From Actividad a";
		Query query = em.createQuery(jpql); 
		List<Actividad> resultados = query.getResultList(); 
		for(Actividad  a : resultados) { 
	        System.out.println(a.toString()); 
	    }
	}
		
	public static void GetActividadesUsuario(int usuario, Date dia, EntityManager em) {
		String jpql = "Select a From Actividad where (duenio=usuario) and (actividad)";
		Query query = em.createQuery(jpql); 
		List<Actividad> resultados = query.getResultList(); 
		for(Actividad  a : resultados) { 
	        System.out.println(a.toString()); 
	    }
		
		
		public static void getReunionesSuperpuestas(int usuario, int actividad, EntityManager em) {
			String jpql = "Select a From Actividad a join Acticvidad a2 on (a2.id = ?1) where("
					+ "a.usuario.id=?2 and a.fechaInicio <= a2.fechaInicio and a2.fechaInicio "
					+ "<= a.fechaFin or a.fechaInicio <= a2,fechaFin and a2.fechainicio <= a.fechaInicio) and a.id != ?1";
			Query query = em.createQuery(jpql); 
			query.setParameter(1, actividad);
			query.setParameter(2, usuario);
			List<Actividad> resultados = query.getResultList(); 
			for(Actividad  a : resultados) { 
		        System.out.println(a.toString()); 
		    }
		}
		

}





}