package servicios;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import entidades.Actividad;
import entidades.Calendario;
import entidades.Usuario;

public class DAOUsuario {
private static DAOUsuario daousuario;
	
	private DAOUsuario(){}

	public static DAOUsuario getInstance() {
		if(daousuario==null)
			daousuario=new DAOUsuario();
		return daousuario;
	}
	
	public static void crearUsuario(String nombre, String apellido, int usuario ,EntityManager em) {
		em.getTransaction().begin();
		Usuario nu= new Usuario(nombre,apellido);
		em.persist(nu);
		em.getTransaction().commit();
	}
	
	public static List<Usuario> getUsuarios(EntityManager em) {
		String jpql = "SELECT u FROM Usuario u"; 
	    Query query = em.createQuery(jpql); 
	    List<Usuario> resultados = query.getResultList(); 
	    return resultados;
	    
	}
	
	
	public static Usuario getUsuario(int idUsuario,EntityManager em) {
		String jpql = "Select u From Usuario u where u.id =?1";
		Query query = em.createQuery(jpql); 
		query.setParameter(1, idUsuario);
		return (Usuario) query.getSingleResult();
	}
	
	public static void getInfoUsuario(int idUsuario,EntityManager em) {
		em.getTransaction().begin();
		String jpql = "Select u From Usuario u where u.id =?1";
		Query query = em.createQuery(jpql); 
		query.setParameter(1, idUsuario);
		Usuario u =(Usuario) query.getSingleResult();
		System.out.println(u.toString());
		em.getTransaction().commit();
		
	}

	public static void getActividadDeUsuario(int idUsuario,Date dia, EntityManager em) {
		Calendar c = Calendar.getInstance();
		int year = c.get(Calendar.YEAR);
		int month = c.get(Calendar.MONTH) + 1;			
		int day= c.get(Calendar.DAY_OF_MONTH);  
//		int d= c.getActividades().indexOf(dia);
		String jpql = "Select a From Actividad a where (a.duenio.id=?1) and extract(day from a.fechaInicio )= ?2"
				+ " and extract(month from a.fechaInicio) = ?3"
			+ " and extract(year from a.fechaInicio) = ?4"; 
		Query query = em.createQuery(jpql); 
		query.setParameter(1, idUsuario);
		query.setParameter(2, day);
		query.setParameter(3, month);
		query.setParameter(4, year);
		 List<Actividad> resultados = query.getResultList(); 
		 System.out.println(resultados);
		 for (Actividad a :resultados) {
			 System.out.println(a.toString());
		 }
		
	}
	
	
	public static void getActividadDeUsuarioRango(int idUsuario,Date dia1,Date dia2, EntityManager em) {
		String jpql = "Select a From Actividad a where (a.duenio.id=?1) and a.fechaInicio Between ?2 AND ?3";
		Query query = em.createQuery(jpql); 
		query.setParameter(1, idUsuario);
		query.setParameter(2, dia1);
		query.setParameter(3, dia2);
		 List<Actividad> resultados = query.getResultList(); 
		 System.out.println(resultados);
		 for (Actividad a :resultados) {
			 System.out.println(a.toString());
		 }
		
	}
	
	public static List<Calendario> getCalendario(int idUsuario,EntityManager em) {
		String jpql = "Select c From Calendario c";
		Query query = em.createQuery(jpql); 
		List<Calendario> resultados = query.getResultList(); 
	    return resultados;
	}

}






	