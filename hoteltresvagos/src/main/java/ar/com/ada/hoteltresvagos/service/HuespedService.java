package ar.com.ada.hoteltresvagos.service;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;

import ar.com.ada.hoteltresvagos.entities.*;
import ar.com.ada.hoteltresvagos.excepciones.HuespedDNIException;
import ar.com.ada.hoteltresvagos.managers.HuespedManager;
import ar.com.ada.hoteltresvagos.operadores.*;

public class HuespedService {

    protected HuespedManager managerHuesped = new HuespedManager();

    protected SessionFactory sessionFactory;

    private Service service = new Service();

    private Huesped huesped;

    private Alta alta;

    private Baja baja;

    private Modificacion modificacion;

    List<Reserva> reservas = new ArrayList<>();

    public HuespedService(Huesped huesped) {
        this.huesped = huesped;
        this.alta = new Alta(this.huesped);
        this.baja = new Baja(this.huesped);
        this.modificacion = new Modificacion(this.huesped);
    }

    public void generarAlta() throws Exception {
        this.alta.alta(this.huesped);
        Reserva reserva = new Reserva();
        reservas = service.buscarPor(this.huesped.getDni());
        reservas.add(reserva);
        this.huesped.setReservas(reservas);
        managerHuesped.create(this.huesped);
    }

    public void generarBaja(int dato,String opcion,String nombre) {
        managerHuesped.delete(baja.darBaja(dato,opcion,nombre));
    }

    public void generarModificacion(Huesped huesped,int opcion) throws HuespedDNIException {
        modificacion.modificar(opcion, huesped);
        managerHuesped.update(huesped);
    }

    public Huesped getHuesped() {
        return huesped;
    }

    public void setHuesped(Huesped huesped) {
        this.huesped = huesped;
    }
}