package ar.com.ada.hoteltresvagos.operadores;

import java.math.BigDecimal;
import java.util.*;

import ar.com.ada.hoteltresvagos.entities.*;

public class Alta {
    private  final Scanner Teclado = new Scanner(System.in);

    private Reserva reserva;
    
    private Huesped huesped;
    
    private Habitacion hablitacion;

    private Fecha fecha = new Fecha();

    public Alta(Reserva reserva){
        this.reserva = reserva;
    }
    public Alta(Huesped huesped){
        this.huesped = huesped;
    }

    public Alta(Habitacion habitacion){
        this.hablitacion = habitacion;
    }

    public Huesped alta(Huesped huesped) throws Exception {
        System.out.println("Ingrese el nombre:");
        huesped.setNombre(Teclado.nextLine());

        System.out.println("Ingrese el DNI:");
        huesped.setDni(Teclado.nextInt());

        System.out.println("Ingrese la domicilio:");
        huesped.setDomicilio(Teclado.nextLine());

        System.out.println("Ingrese el Domicilio alternativo(OPCIONAL):");
        String domAlternativo = Teclado.nextLine();
        if (domAlternativo != null)
            huesped.setDomicilioAlternativo(domAlternativo);
        return huesped;
    }
    
    public Reserva alta(Reserva reserva) throws Exception {

        reserva.setImporteReserva(new BigDecimal(1000));
        reserva.setImporteTotal(new BigDecimal(3000));
        reserva.setImportePagado(new BigDecimal(0));
        reserva.setFechaReserva(new Date());
        reserva.setFechaIngreso(fecha.ingresarFecha("Ingreso"));
        reserva.setFechaEgreso(fecha.ingresarFecha("Engreso"));
    
        return reserva;
    }

    public Habitacion alta(Habitacion habitacion) {
        return habitacion;
    }

    public Reserva getReserva() {
        return reserva;
    }

    public void setReserva(Reserva reserva) {
        this.reserva = reserva;
    }

    public Huesped getHuesped() {
        return huesped;
    }

    public void setHuesped(Huesped huesped) {
        this.huesped = huesped;
    }

    public Habitacion getHablitacion() {
        return hablitacion;
    }

    public void setHablitacion(Habitacion hablitacion) {
        this.hablitacion = hablitacion;
    }

}