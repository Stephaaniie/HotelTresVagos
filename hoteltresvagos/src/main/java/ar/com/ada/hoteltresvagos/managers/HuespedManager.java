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

public class HuespedManager {

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

    public void create(Huesped huesped) {

        Session session = sessionFactory.openSession();
        session.beginTransaction();

        session.save(huesped);

        session.getTransaction().commit();
        session.close();
    }

    public Huesped read(int huespedId) {
        Session session = sessionFactory.openSession();

        Huesped huesped = session.get(Huesped.class, huespedId);

        session.close();

        return huesped;
    }

    public Huesped readByDNI(int dni) {
        Session session = sessionFactory.openSession();

        Huesped huesped = session.byNaturalId(Huesped.class).using("dni", dni).load();

        session.close();

        return huesped;
    }

    public void update(Huesped huesped) {

        Session session = sessionFactory.openSession();
        session.beginTransaction();

        session.update(huesped);

        session.getTransaction().commit();
        session.close();
    }

    public void delete(List<Huesped> huesped) {

        Session session = sessionFactory.openSession();
        session.beginTransaction();

        session.delete(huesped);

        session.getTransaction().commit();
        session.close();
    }

	public List<Reserva> getReservasPorDni(int dni) {
        Session session = sessionFactory.openSession();

        Query query = session.createNativeQuery("select * from reserva r inner join huesped h on h.huesped_id = r.huesped_id where dni = ?", Reserva.class);

        query.setParameter(1, dni);

        List<Reserva> reservas = query.getResultList();

        return reservas;
	}

	public List<Huesped> getHuespedes() {
        Session session = sessionFactory.openSession();

        Query query = session.createNativeQuery("select * from huesped", Huesped.class);
        
        List<Huesped> todos = query.getResultList();

        return todos;
    }

	public List<Huesped> getHuespedesConNombre(String nombre) {
        Session session = sessionFactory.openSession();

        Query query = session.createNativeQuery("select * from huesped where nombre = ?", Huesped.class);

        query.setParameter(1, nombre);

        List<Huesped> huespedes = query.getResultList();

        return huespedes;
	}

	public List<Huesped> getHuespedConId(int id) {
		Session session = sessionFactory.openSession();

        Query query = session.createNativeQuery("select * from reserva r inner join huesped h on h.huesped_id = r.huesped_id where h.huesped_id = ?", Huesped.class);

        query.setParameter(1, id);

        List<Huesped> huespeds = query.getResultList();

        return huespeds;
	}

	public Huesped getHuespededPorFecha(String fecha) {
        Session session = sessionFactory.openSession();

        Query query = session.createNativeQuery("select * from reserva r inner join huesped h on h.huesped_id = r.huesped_id where r.fecha_reserva = ?", Reserva.class);

        query.setParameter(1, fecha);

        Huesped huesped = (Huesped) query.getSingleResult();
        
	    return huesped;
    }
    
    public Huesped getHuespedConDni(int dni) {
        Session session = sessionFactory.openSession();

        Query query = session.createNativeQuery("select * from reserva r inner join huesped h on h.huesped_id = r.huesped_id where h.dni = ?", Reserva.class);

        query.setParameter(1, dni);
        
        Huesped huesped = (Huesped) query.getSingleResult();
        
	    return huesped;
    }
    
}