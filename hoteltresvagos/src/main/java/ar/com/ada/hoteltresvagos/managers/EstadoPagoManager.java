package ar.com.ada.hoteltresvagos.managers;

import java.util.List;

import java.util.logging.Level;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import ar.com.ada.hoteltresvagos.entities.EstadoDePago;

public class EstadoPagoManager {

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

	public void create(EstadoDePago estado) {

        Session session = sessionFactory.openSession();
        session.beginTransaction();

        session.save(estado);

        session.getTransaction().commit();
        session.close();
    }

    public EstadoDePago read(int estadoId) {
        Session session = sessionFactory.openSession();

        EstadoDePago reporte = session.get(EstadoDePago.class, estadoId);

        session.close();

        return reporte;
    }

    public void update(EstadoDePago estado) {

        Session session = sessionFactory.openSession();
        session.beginTransaction();

        session.update(estado);

        session.getTransaction().commit();
        session.close();
    }

    public void delete(EstadoDePago estado) {

        Session session = sessionFactory.openSession();
        session.beginTransaction();

        session.delete(estado);

        session.getTransaction().commit();
        session.close();
	}
	public List<EstadoDePago> getPorEstadoId(int estado_id) {

        Session session = sessionFactory.openSession();

        Query query = session.createNativeQuery
        ("select r.estado_id, count(r.reserva_id) cantidad_reservas, sum(r.importe_reserva) total_importe_reserva, sum(r.importe_pagado) total_importe_pagado, sum(r.importe_total) total_importe from huesped h inner join reserva r on h.huesped_id = r.huesped_id where estado_id = ?", EstadoDePago.class);

        query.setParameter(1, estado_id);

        List<EstadoDePago> reportes = query.getResultList();

        return reportes;

    }

	public List<EstadoDePago> getEstadoPorDni(int dni) {
		Session session = sessionFactory.openSession();

        Query query = session.createNativeQuery
        ("select h.huesped_id, h.nombre, count(r.reserva_id) cantidad_reservas, sum(r.importe_reserva) total_importe_reserva, sum(r.importe_pagado) total_importe_pagado, sum(r.importe_total) total_importe from huesped h inner join reserva r on h.huesped_id = r.huesped_id where h.dni = ?", EstadoDePago.class);

        query.setParameter(1, dni);

        List<EstadoDePago> reportes = query.getResultList();

        return reportes;
	}

	public List<EstadoDePago> getPorNombre(String nombre) {
		Session session = sessionFactory.openSession();

        Query query = session.createNativeQuery
        ("select h.huesped_id, h.nombre, count(r.reserva_id) cantidad_reservas, sum(r.importe_reserva) total_importe_reserva, sum(r.importe_pagado) total_importe_pagado, sum(r.importe_total) total_importe from huesped h inner join reserva r on h.huesped_id = r.huesped_id where h.nombre = ? ", EstadoDePago.class);

        query.setParameter(1, nombre);

        List<EstadoDePago> reportes = query.getResultList();

        return reportes;
	}

	public List<EstadoDePago> getEstadosPago() {
		Session session = sessionFactory.openSession();

		Query query = session.createNativeQuery("select r.estado_id," + 
        "count(r.reserva_id) cantidad_reservas," +
        "sum(r.importe_reserva) total_importe_reserva," +
        "sum(r.importe_pagado) total_importe_pagado," +
        "sum(r.importe_total) total_importe" +
        "from huesped h inner join reserva r on h.huesped_id = r.huesped_id", EstadoDePago.class);

        List<EstadoDePago> reportes = query.getResultList();

        return reportes;

    }
}