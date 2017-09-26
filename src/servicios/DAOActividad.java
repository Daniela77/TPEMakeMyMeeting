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





}