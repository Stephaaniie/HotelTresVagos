package ar.com.ada.hoteltresvagos.excepciones;

import ar.com.ada.hoteltresvagos.entities.Huesped;

public class HuespedDNIException extends HuespedInfoException {

    public HuespedDNIException(Huesped huesped, String mensaje) {
        super(huesped, mensaje);
    }

}