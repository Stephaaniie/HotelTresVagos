package ar.com.ada.hoteltresvagos.operadores;

import java.text.*;
import java.util.Date;

import ar.com.ada.hoteltresvagos.view.Menu;

public class Fecha {

    public Fecha() {
    }

    public static Date ingresarFecha(String tipo) {
        Date fechaValida = null;
        boolean esValida = true;
        DateFormat dFormat = new SimpleDateFormat("dd/MM/yy");
        do {
            esValida = true;
            try {
                fechaValida = dFormat.parse(Menu.solicitarString("Ingrese la fecha de " + tipo + " (dd/mm/yy)"));
            } catch (Exception e) {
                System.out.println("Error al ingresar fecha ");
                esValida = false;
            }
        } while (!esValida);
        return fechaValida;
    }
    
}