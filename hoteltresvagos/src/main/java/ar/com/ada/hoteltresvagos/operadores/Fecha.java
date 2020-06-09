package ar.com.ada.hoteltresvagos.operadores;

import java.text.*;
import java.util.Date;
import java.util.Scanner;

public class Fecha {

    private  final Scanner Teclado = new Scanner(System.in);
    
    public Fecha(){
    }
    
    public Date ingresarFecha(String tipo) {
        Date fechaValida = null;
        boolean esValida = true;
        DateFormat dFormat = new SimpleDateFormat("dd/MM/yy");
        do {
            esValida = true;
            System.out.println("Ingrese la fecha de " + tipo + " (dd/mm/yy)");
            try {
                fechaValida = dFormat.parse(Teclado.nextLine());
            } catch (Exception e) {
                System.out.println("Error al ingresar fecha ");
                esValida = false;
            }
        } while (!esValida);
        return fechaValida;
    }
    
}