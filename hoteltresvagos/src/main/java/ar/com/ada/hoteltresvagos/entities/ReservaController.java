package ar.com.ada.hoteltresvagos.entities;

import ar.com.ada.hoteltresvagos.Validaciones.*;

public class ReservaController {

    private boolean esValida = true;
   
    private Reserva reserva;

    private Huesped huesped;

    private DireccionValidator direccionValidator = new DireccionValidator(huesped.getDomicilio());

    private DniValidator dniValidator = new DniValidator(this.huesped.getDni()); 

    private NombreValidator nombreValidator = new NombreValidator(huesped.getNombre());

    public ReservaController(Reserva reserva,Huesped huesped){
        this.reserva = reserva;
        this.huesped = huesped;
    }

    public boolean reservaValida() {
        return (direccionValidator.esIngresoValido() && dniValidator.esIngresoValido() && nombreValidator.esIngresoValido());
    }

}