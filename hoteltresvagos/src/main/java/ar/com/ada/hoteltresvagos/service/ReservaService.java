package ar.com.ada.hoteltresvagos.service;

import java.util.List;

import ar.com.ada.hoteltresvagos.entities.Reserva;
import ar.com.ada.hoteltresvagos.managers.ReservaManager;

import java.util.ArrayList;

import org.hibernate.SessionFactory;

import ar.com.ada.hoteltresvagos.entities.*;
import ar.com.ada.hoteltresvagos.operadores.*;
import ar.com.ada.hoteltresvagos.view.Menu;

public class ReservaService {

    protected ReservaManager managerReserva = new ReservaManager();

    protected SessionFactory sessionFactory;

    private Service service = new Service(this);

    private Reserva reserva;

    private Funcionalidad funcionalidad;

    List<Reserva> reservas = new ArrayList<>();

    public ReservaService(Reserva reserva) {
        this.reserva = reserva;

        this.funcionalidad = new Funcionalidad(this.reserva);
    }

    public void generarAlta(Reserva reserva2) throws Exception {
        this.funcionalidad.alta(this.reserva);
        generarHuesped(reserva);
        managerReserva.create(reserva);
    }

    public void generarHuesped(Reserva reserva) throws Exception {
        Huesped huesped = new Huesped();
        int opcion = Menu.solicitarInt("\n0:SI. \n1:NO");
        if(opcion == 0){
            int id = Menu.solicitarInt("id");
            if (service.existeHuespedEnBD(id)) {
                huesped = service.buscarHuesped(id);
            }else{
                huesped = funcionalidad.alta(huesped);
            }
        }
        reserva.setHuesped(huesped);
    }

    public List<Reserva> darBajaReservas(int dato,String opcion, String nombre,String fecha) {
        List<Reserva> reservas = this.service.listaDeReservas(opcion, dato, nombre, fecha);
        if (reserva == null) {
            System.out.println("reserva no encontrada.");
        }
        return reservas;
    }
    public void generarBaja(int dato,String opcion,String nombre, String fecha) {
        managerReserva.delete(darBajaReservas(dato, opcion, nombre, fecha));
    }

    public void generarModificacion(Reserva reserva,int opcion) {
        funcionalidad.modificar(opcion, reserva);
        managerReserva.update(reserva);
    }

    public Reserva getReserva() {
        return reserva;
    }

    public void setReserva(Reserva reserva) {
        this.reserva = reserva;
    }
}