package ar.com.ada.hoteltresvagos.Validaciones;

import ar.com.ada.hoteltresvagos.Interfaz.IngresoValido;

public class DireccionValidator implements IngresoValido {

    private String direccion;

    public DireccionValidator(String direccion){
        this.direccion = direccion;
    }
    
    @Override
    public boolean esIngresoValido() {
        return(this.direccion.length() < 2 || this.direccion.length() > 20);
    }
    
}