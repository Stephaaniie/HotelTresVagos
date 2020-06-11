package ar.com.ada.hoteltresvagos.operadores;

import java.math.BigDecimal;
import java.util.*;

import ar.com.ada.hoteltresvagos.entities.*;
import ar.com.ada.hoteltresvagos.excepciones.HuespedDNIException;
import ar.com.ada.hoteltresvagos.service.Service;

public class Funcionalidad {
    private final Scanner Teclado = new Scanner(System.in);

    private Reserva reserva;

    private Huesped huesped;

    private Habitacion hablitacion;

    public Funcionalidad(Reserva reserva) {
        this.reserva = reserva;
    }

    public Funcionalidad(Huesped huesped) {
        this.huesped = huesped;
    }

    public Funcionalidad(Habitacion habitacion) {
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
        
        reserva.setFechaIngreso(Fecha.ingresarFecha("Ingreso"));
        
        reserva.setFechaEgreso(Fecha.ingresarFecha("Engreso"));
    
        return reserva;
    }
   
    public void baja(Habitacion habitacion){
        
    }
  
    public Huesped modificar(int opcion, Huesped huesped) throws HuespedDNIException {
        switch (opcion) {
            case 1:
                System.out.println("Ingrese el nuevo nombre:");
                huesped.setNombre(Teclado.nextLine());
                break;
            case 2:
                System.out.println("Ingrese el nuevo DNI:");
                huesped.setDni(Teclado.nextInt());
                break;
            case 3:
                System.out.println("Ingrese el nuevo domicilio:");
                huesped.setDomicilio(Teclado.nextLine());
                break;
            case 4:
                System.out.println("Ingrese el nuevo domicilio alternativo:");
                huesped.setDomicilioAlternativo(Teclado.nextLine());
                break;
            default:
                break;
        }
        return huesped;
    }

    public Reserva modificar(int opcion, Reserva reserva) {
        switch (opcion) {
            case 1:
                System.out.println("Ingrese la nueva fecha de ingreso");
                reserva.setFechaIngreso(Fecha.ingresarFecha(Teclado.nextLine()));
                break;
            case 2:
                System.out.println("Ingrese la nueva fecha de egreso");
                reserva.setFechaEgreso(Fecha.ingresarFecha(Teclado.nextLine()));
                break;
            default:
                break;
        }
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

	public List<Huesped> darBaja(int dato, String opcion, String nombre) {
		return null;
	}

}