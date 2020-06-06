package ar.com.ada.hoteltresvagos.managers;

import java.util.Date;
import java.util.List;
import java.util.logging.Level;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import ar.com.ada.hoteltresvagos.entities.Reserva;

public class ReservaManager {
    
    
protected SessionFactory sessionFactory;

    public void setup() {
        java.util.logging.Logger.getLogger("org.hibernate").setLevel(Level.OFF);
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
        try {
            sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
        } catch (Exception ex) {
            StandardServiceRegistryBuilder.destroy(registry);
            throw ex;
        }
    }

    public void exit() {
        sessionFactory.close();
    }

    public void create(Reserva reserva) {

        Session session = sessionFactory.openSession();
        session.beginTransaction();

        session.save(reserva);

        session.getTransaction().commit();
        session.close();
    }

    public Reserva read(int reservaId) {
        Session session = sessionFactory.openSession();

        Reserva reserva = session.get(Reserva.class, reservaId);

        session.close();

        return reserva;
    }

    public void update(Reserva reserva) {

        Session session = sessionFactory.openSession();
        session.beginTransaction();

        session.update(reserva);

        session.getTransaction().commit();
        session.close();
    }

    public void delete(Reserva reserva) {

        Session session = sessionFactory.openSession();
        session.beginTransaction();

        session.delete(reserva);

        session.getTransaction().commit();
        session.close();
    }

/**
 * Este metodo en la vida real no debe existir ya qeu puede haber miles de
 * usuarios
 * 
 * @return
 */
    public List<Reserva> buscarTodas() {
        Session session = sessionFactory.openSession();
        Query query = session.createNativeQuery("SELECT * FROM reserva", Reserva.class);
        List<Reserva> todos = query.getResultList();
        return todos;
    }

/**
 * Busca una lista de huespedes por el nombre completo Esta armado para que se
 * pueda generar un SQL Injection y mostrar commo NO debe programarse.
 * 
 * @param nombre
 * @return
 */

    public List<Reserva> buscarTodas(String nombre) {
        Session session = sessionFactory.openSession();
        Query query = session.createNativeQuery("SELECT * FROM reserva where nombre = '" + nombre + "'", Reserva.class);
        List<Reserva> reservas = query.getResultList();
	    return reservas;
    }

    public List<Reserva> buscarTodas(int tipo, String dato) {
        Session session = sessionFactory.openSession();
        List<Reserva> reservas;
        if(dato.equals("dni")){
            Query query = session.createNativeQuery("select * from huesped where dni = " + tipo, Reserva.class);
            reservas = query.getResultList();
        }else{
            Query query = session.createNativeQuery("select * from huesped where huesped_id = "+ tipo, Reserva.class);
            reservas = query.getResultList();
        }
	    return reservas;
    }

    public List<Reserva> buscarTodas(Date ingresarFecha) {
        Session session = sessionFactory.openSession();
	    return null;
    }
}
