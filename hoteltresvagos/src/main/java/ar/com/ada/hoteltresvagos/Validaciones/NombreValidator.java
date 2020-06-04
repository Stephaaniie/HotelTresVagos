package ar.com.ada.hoteltresvagos.Validaciones;

import ar.com.ada.hoteltresvagos.Interfaz.IngresoValido;

public class NombreValidator implements IngresoValido {
    
    private String nombre;

    public NombreValidator(String nombre){
        this.nombre = nombre;
    }
    @Override
    public boolean esIngresoValido() {
        return (this.nombre.length() < 2 || this.nombre.length() > 20);
    }

}