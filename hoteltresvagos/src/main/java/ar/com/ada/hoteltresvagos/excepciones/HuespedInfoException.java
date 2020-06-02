package ar.com.ada.hoteltresvagos.excepciones;

import ar.com.ada.hoteltresvagos.entities.Huesped;

/**
 * HuespedInfoException
 */
public class HuespedInfoException extends Exception {

    private Huesped huesped;

    public HuespedInfoException(Huesped huesped, String mensaje) {

        super(huesped.getNombre() + ":" + mensaje);
        this.huesped = huesped;
    }
}
