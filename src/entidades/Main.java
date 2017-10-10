package entidades;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import servicios.DAOActividad;
import servicios.DAOUsuario;

public class Main {

public static void main(String[] args) {
	EntityManagerFactory emf = Persistence.createEntityManagerFactory("my_persistence_unit");
	EntityManager manager = emf.createEntityManager();
	
	///////////////////////////////////Borra BD///////////////////////////////////////////////
//	manager.getTransaction().begin();
//	manager.createQuery("DELETE FROM Usuario").executeUpdate();
//	manager.createQuery("DELETE FROM Calendario").executeUpdate();
//	manager.createQuery("DELETE FROM Actividad").executeUpdate();
//	manager.createQuery("DELETE FROM Sala").executeUpdate();
//	manager.getTransaction().commit();


    
    Usuario u1 = DAOUsuario.crearUsuario("Nombre1","Apellido1",manager);
    
    System.out.println(u1);
    
////	Usuario u1 = new Usuario("Nombre1","Apellido1",null);
//    manager.getTransaction( ).begin( );
//	Usuario u2 = new Usuario("Nombre2","Apellido2",null);
//	Usuario u3 = new Usuario("Nombre3","Apellido3",null);
//	Usuario u4 = new Usuario("Nombre4","Apellido4",null);
//	Usuario u5 = new Usuario("Nombre5","Apellido5",null);
//	Usuario u6 = new Usuario("Nombre6","Apellido6",null);
//	Usuario u7 = new Usuario("Nombre7","Apellido7",null);
//	Usuario u8 = new Usuario("Nombre8","Apellido8",null);
//	Usuario u9 = new Usuario("Nombre9","Apellido9",null);
//	Usuario u10 = new Usuario("Nombre10","Apellido10",null);
//	
//
//	Sala sala1 = new Sala("Sala 1", "Direccion 1");
//	Sala sala2 = new Sala("Sala 2", "Direccion 2");

	Date fechaA1 = new GregorianCalendar(2017, Calendar.SEPTEMBER, 25, 9, 22).getTime();
    Date fechaA2 = new GregorianCalendar(2017, Calendar.SEPTEMBER, 25, 12, 25).getTime();
    Date fechaB1 = new GregorianCalendar(2018, Calendar.SEPTEMBER, 25, 9, 22).getTime();
    Date fechaB2 = new GregorianCalendar(2018, Calendar.SEPTEMBER, 25, 12, 25).getTime();
    
////    List<Usuario> invitados= new ArrayList<Usuario>();
////    List<Usuario> pendientes= new ArrayList<Usuario>();
////
////    pendientes.add(u2);
////    pendientes.add(u3);
////    pendientes.add(u4);
////    pendientes.add(u5);
//    
//    Calendario c= new Calendario("calendario1",u1);
//    Calendario c1= new Calendario("calendario2",u2);
//    Calendario c2= new Calendario("calendario3",u3);
//    Calendario c3= new Calendario("calendario4",u4);
//    Calendario c4= new Calendario("calendario5",u5);
//  
//    Actividad a1 = new Actividad ("Caminar",u1,fechaA1,fechaA2, sala1,c);
////    Actividad a2 = new Actividad ();
////a1.setPendientes(pendientes);    
////    u2.aceptarActividadPendiente(a1);
//    Actividad a2 = new Actividad ("Correr",u2,fechaB1,fechaB2, sala1,c1);
//    a2.setPendiente(u1);
//  
////	manager.persist(u1);
//	manager.persist(u2);
//	manager.persist(u3);
//	manager.persist(u4);
//	manager.persist(u5);
//	manager.persist(u6);
//	manager.persist(u7);
//	manager.persist(u8);
//	manager.persist(u9);
//	manager.persist(u10);
//	manager.persist(a1);
//	manager.persist(a2);
//	manager.persist(c);
//	manager.persist(c1);
//	manager.getTransaction().commit( );
//	
//	
	System.out.println("Ejercicio c.i:");

	
	Usuario uInfo= DAOUsuario.getInfoUsuario(u1.getId(), manager);
	System.out.println(uInfo.toString());
	
	System.out.println("Ejercicio c.ii:");
	
	List<Actividad> resultados = DAOUsuario.getActividadDeUsuario(u1.getId(), fechaA1, manager);
	 System.out.println(resultados);
	 for (Actividad a :resultados) {
		 System.out.println(a.toString());
	 }
	
//	
//	System.out.println("Ejercicio c.iii:");
//	
//	DAOUsuario.getActividadDeUsuarioRango(u1.getId(), fechaA1,fechaA1, manager);
//	
////	System.out.println("Ejercicio c.iv:");
////	
////	DAOActividad.getActividadesSobrepuestas(u1.getId(), a1.getId(), manager);
//	
//
//
////	String jpql = "SELECT u FROM Usuario u"; 
////    Query query = manager.createQuery(jpql); 
////    List<Usuario> resultados = query.getResultList(); 
////    for(Usuario  u : resultados) { 
////        System.out.println(u.getNombre()); 
////    }

	
	manager.close();
	emf.close();

}

}
