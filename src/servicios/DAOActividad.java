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
	}
		
		
		public static void getActividadesSobrepuestas(int usuario, int actividad, EntityManager em) {
			// saco lo de las actividades solapadas que estaba en Actividad y lo consulto directamenrte en la bd
			//((act1i.compareTo(act2f) > 0 )||(act1f.compareTo(act2i) < 0))	y lo adapto a la consulta
			String jpql = "SELECT a1 FROM Actividad a1 , Actividad a2 "
					+ "WHERE a.duenio_idUsuario = ?1"
					+ " AND a1.id != ?2" /// Sean distintas actividades
					+ " AND a2.id = ?2" /// la a con a2
					+ " AND (a1.fechaInicio < a2.fechaFin" + " AND a2.fechaInicio < a1.fechaFin"
					+ " OR a1.fechaInicio <= a2.fechaFin" + " AND a2.fechaInicio <= a1.fechaInicio)";
			Query query = em.createQuery(jpql); 
			query.setParameter(1, usuario);
			query.setParameter(2, actividad);
			List<Actividad> res = query.getResultList(); 
			for(Actividad  acti : res) { 
				System.out.println(acti.toString()); 
			}
		
		}
}
