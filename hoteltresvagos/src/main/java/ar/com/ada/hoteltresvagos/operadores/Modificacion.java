package ar.com.ada.hoteltresvagos.operadores;

import java.util.Scanner;

import ar.com.ada.hoteltresvagos.entities.*;
import ar.com.ada.hoteltresvagos.excepciones.HuespedDNIException;

public class Modificacion {
    private  final Scanner Teclado = new Scanner(System.in);

    private Reserva reserva;
    
    private Huesped huesped;
    
    private EstadoDePago estado;

    private Habitacion hablitacion;

    private Fecha fecha = new Fecha();

    public Modificacion(Reserva reserva){
        this.reserva = reserva;
    }

    public Modificacion(Huesped huesped){
        this.huesped = huesped;
    }

    public Modificacion(EstadoDePago estado){
        this.estado = estado;
    }

    public Modificacion(Habitacion habitacion){
        this.hablitacion = habitacion;
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
                reserva.setFechaIngreso(fecha.ingresarFecha(Teclado.nextLine()));
                break;
            case 2:
                System.out.println("Ingrese la nueva fecha de egreso");
                reserva.setFechaEgreso(fecha.ingresarFecha(Teclado.nextLine()));
                break;
            default:
                break;
        }
        return reserva;
    }

}