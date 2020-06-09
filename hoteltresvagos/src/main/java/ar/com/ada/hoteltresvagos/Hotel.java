package ar.com.ada.hoteltresvagos;

import java.util.Scanner;

import ar.com.ada.hoteltresvagos.managers.EstadoPagoManager;
import ar.com.ada.hoteltresvagos.managers.HabitacionManager;
import ar.com.ada.hoteltresvagos.managers.HuespedManager;
import ar.com.ada.hoteltresvagos.managers.ReservaManager;
import ar.com.ada.hoteltresvagos.view.Menu;

public class Hotel {

    public static Scanner Teclado = new Scanner(System.in);

    protected HuespedManager ABMHuesped = new HuespedManager();

    protected ReservaManager ABMReserva = new ReservaManager();

    protected HabitacionManager ABMHabitacion = new HabitacionManager();

    protected EstadoPagoManager ABMEstadoPago = new EstadoPagoManager();

    public Menu menu = new Menu();

    public void iniciar() throws Exception {
        try {
            managers("open");
            menu.printOpciones();
            int opcion = Teclado.nextInt();
            while (opcion > 0) {
                menu.opcionesUsuario(opcion);
                menu.printOpciones();
                opcion = Teclado.nextInt();
            }
            managers("close");
        } catch (Exception e) {
            System.out.println("Que lindo mi sistema,se rompio mi sistema");
            throw e;
        } finally {
            System.out.println("Saliendo del sistema, bye bye...");
        }
    }

    private void managers(String estado) {
        if (estado.equals("open")) {
            ABMHuesped.setup();
            ABMReserva.setup();
            ABMHabitacion.setup();
            ABMEstadoPago.setup();
        } else if (estado.equals("close")) {
            ABMReserva.exit();
            ABMHuesped.exit();
            ABMHabitacion.exit();
            ABMEstadoPago.exit();
        }
    }    
}