package ar.com.ada.hoteltresvagos.operadores;

import java.util.List;

import ar.com.ada.hoteltresvagos.entities.*;
import ar.com.ada.hoteltresvagos.service.Service;

public class Baja {

    private Reserva reserva;
    
    private Huesped huesped;
    
    private Habitacion hablitacion;

    private Service service = new Service();

    public Baja(Reserva reserva){
        this.reserva = reserva;
    }
    
    public Baja(Huesped huesped){
        this.huesped = huesped;
    }

    public Baja(Habitacion habitacion){
        this.hablitacion = habitacion;
    }

    public List<Reserva> darBajaReservas(int dato,String opcion, String nombre,String fecha) {
        List<Reserva> reservas = service.listaDeReservas(opcion, dato, nombre, fecha);
        if (reserva == null) {
            System.out.println("reserva no encontrada.");
        }
        return reservas;
    }

    public List<Huesped> darBaja(int dato,String opcion, String nombre) {
        List<Huesped> huespeds = service.listaDeHuespeds(opcion, nombre, dato);
        if (huespeds == null) {
            System.out.println("Huesped no encontrado.");
        }
        return huespeds;
    }
   
    public void baja(Habitacion habitacion){
        
    }
}