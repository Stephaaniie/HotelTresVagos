package ar.com.ada.hoteltresvagos.managers;

import java.util.List;

import java.util.logging.Level;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import ar.com.ada.hoteltresvagos.entities.Habitacion;

public class HabitacionManager {
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

	public void create(Habitacion habitacion) {

        Session session = sessionFactory.openSession();
        session.beginTransaction();

        session.save(habitacion);

        session.getTransaction().commit();
        session.close();
    }

    public Habitacion read(int habitacionId) {
        Session session = sessionFactory.openSession();

        Habitacion reporte = session.get(Habitacion.class, habitacionId);

        session.close();

        return reporte;
    }

    public void update(Habitacion habitacion) {

        Session session = sessionFactory.openSession();
        session.beginTransaction();

        session.update(habitacion);

        session.getTransaction().commit();
        session.close();
    }

    public void delete(Habitacion habitacion) {

        Session session = sessionFactory.openSession();
        session.beginTransaction();

        session.delete(habitacion);

        session.getTransaction().commit();
        session.close();
	}

	public List<Habitacion> getHabitacionesOcupadas() {
		return null;
	}

	public List<Habitacion> getHabitacionesLibres() {
		return null;
	}

	public List<Habitacion> getHabitacionesPorPrecio() {
		return null;
	}

	public List<Habitacion> getHabitaciones() {
		return null;
	}

	public List<Habitacion> getHabitacionPorFecha(String fecha) {
		return null;
	}
    
}