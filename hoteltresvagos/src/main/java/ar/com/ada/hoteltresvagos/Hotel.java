package ar.com.ada.hoteltresvagos;

import java.util.Scanner;

import ar.com.ada.hoteltresvagos.managers.EstadoPagoManager;
import ar.com.ada.hoteltresvagos.managers.HabitacionManager;
import ar.com.ada.hoteltresvagos.managers.HuespedManager;
import ar.com.ada.hoteltresvagos.managers.ReservaManager;
import ar.com.ada.hoteltresvagos.service.Service;
import ar.com.ada.hoteltresvagos.view.Menu;

public class Hotel {

    public static Scanner Teclado = new Scanner(System.in);

    protected HuespedManager huespedManager = new HuespedManager();

    protected ReservaManager reservaManager = new ReservaManager();

    protected HabitacionManager habitacionManager = new HabitacionManager();

    protected EstadoPagoManager ePagoManager = new EstadoPagoManager();

    protected Service service;
    
    public Menu menu;

    public void iniciar() throws Exception {
        try {
            managers("open");
            service = new Service(huespedManager, reservaManager, habitacionManager, ePagoManager);
            menu = new Menu(service);
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
            huespedManager.setup();
            reservaManager.setup();
            habitacionManager.setup();
            ePagoManager.setup();
        } else if (estado.equals("close")) {
            reservaManager.exit();
            huespedManager.exit();
            habitacionManager.exit();
            ePagoManager.exit();
        }
    }    
}