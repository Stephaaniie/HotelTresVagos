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

    private Huesped huesped;

    private Funcionalidad funcionalidad;

    List<Reserva> reservas = new ArrayList<>();

    private Service service = new Service(this);

    public HuespedService(Huesped huesped) {
        this.huesped = huesped;
        this.funcionalidad = new Funcionalidad(this.huesped);
    }

    public void generarAlta(Huesped huesped2) throws Exception {
        this.funcionalidad.alta(this.huesped);
        Reserva reserva = new Reserva();
        reservas = Service.buscarPor(this.huesped.getDni());
        reservas.add(reserva);
        this.huesped.setReservas(reservas);
        managerHuesped.create(this.huesped);
    }
    public List<Huesped> darBaja(int dato,String opcion, String nombre, String fecha) {
        List<Huesped> huespeds = this.service.listaDeHuespeds(opcion, nombre, dato);
        if (huespeds == null) {
            System.out.println("Huesped no encontrado.");
        }
        return huespeds;
    }

    public void generarBaja(int dato,String opcion,String nombre) {
        managerHuesped.delete(funcionalidad.darBaja(dato,opcion,nombre));
    }

    public void generarModificacion(Huesped huesped,int opcion) throws HuespedDNIException {
        funcionalidad.modificar(opcion, huesped);
        managerHuesped.update(huesped);
    }

    public Huesped getHuesped() {
        return huesped;
    }

    public void setHuesped(Huesped huesped) {
        this.huesped = huesped;
    }
}