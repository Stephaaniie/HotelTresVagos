package ar.com.ada.hoteltresvagos.managers;

import java.util.List;
import java.util.logging.Level;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import javax.persistence.Query;

import ar.com.ada.hoteltresvagos.entities.*;

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

    public void delete(List<Reserva> list) {

        Session session = sessionFactory.openSession();
        session.beginTransaction();

        for (Reserva reserva : list) {
            session.delete(reserva);
        }

        session.getTransaction().commit();
        session.close();
    }

    public List<Reserva> getReservas() {

        Session session = sessionFactory.openSession();

        Query query = session.createNativeQuery("select * from reserva", Reserva.class);
        
        List<Reserva> todas = query.getResultList();

        return todas;
    }

    public List<Reserva> getReservasId(int id) {
        Session session = sessionFactory.openSession();
        
        Query query = session.createNativeQuery("selct * from reserva where reserva_id = ?", Reserva.class);

        query.setParameter(1, id);

        List<Reserva> reservas = query.getResultList();

        return reservas;
        
    }

    public List<Reserva> getRservasDniH(int dni) {
	    Session session = sessionFactory.openSession();

        Query query = session.createNativeQuery("select * from reserva r inner join huesped h on h.huesped_id = r.huesped_id where dni = ?", Reserva.class);

        query.setParameter(1, dni);

        List<Reserva> reservas = query.getResultList();

        return reservas;
    }

    public List<Reserva> getRservasNombre(String nombre) {
        Session session = sessionFactory.openSession();
        
        Query query = session.createNativeQuery("selct * from reserva r inner join huesped h on h.huesped_id = r.huesped_id where nombre = ?", Reserva.class);

        query.setParameter(1, nombre);

        List<Reserva> reservas = query.getResultList();

        return reservas;
    }

    public List<Reserva> getRservasFecha(String fecha, String tipoFecha) {
	    Session session = sessionFactory.openSession();

        Query query = session.createNativeQuery("selct * from reserva where "+tipoFecha+"= ?", Reserva.class);

        query.setParameter(1,fecha);

        List<Reserva> reservas = query.getResultList();
        
        return reservas;
    }

    public Reserva getReservaConId(int id) {
        Session session = sessionFactory.openSession();

        Query query = session.createNativeQuery("select * from reserva r inner join huesped h on h.huesped_id = r.huesped_id where huesped_id = ?", Reserva.class);

        query.setParameter(1, id);

        Reserva reserva = (Reserva) query.getSingleResult();

	    return reserva;
    }

}