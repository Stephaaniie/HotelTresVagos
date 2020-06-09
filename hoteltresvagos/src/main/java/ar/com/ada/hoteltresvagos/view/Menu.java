package ar.com.ada.hoteltresvagos.view;

import java.util.List;
import java.util.Scanner;

import ar.com.ada.hoteltresvagos.entities.*;
import ar.com.ada.hoteltresvagos.excepciones.HuespedDNIException;
import ar.com.ada.hoteltresvagos.service.HuespedService;
import ar.com.ada.hoteltresvagos.service.ReservaService;
import ar.com.ada.hoteltresvagos.service.Service;

public class Menu {

    private static final Scanner Teclado = new Scanner(System.in);

    private Service service = new Service();

    private HuespedService hService = new HuespedService(new Huesped());

    private ReservaService rService = new ReservaService(new Reserva());

    public void lisarReservas(List<Reserva> reservas) {
        for (Reserva x : reservas) {
            System.out.println("Id: " + x.getReservaId() + " Nombre: " + x.getHuesped().getNombre() + "Importe pagado: "
                    + x.getImportePagado() + "Importe reserva: " + x.getImporteReserva() + "Importe Total: "
                    + x.getImporteTotal() + "Fecha de Reserva: " + x.getFechaReserva() + "Fecha de Engreso: "
                    + x.getFechaEgreso() + "Fecha de Ingreso: " + x.getFechaIngreso() + "Estado: " + x.getTipoEstado());
        }
    }

    public void listarHuesped(List<Huesped> huesped) {
        for (Huesped c : huesped) {
            System.out.println("Id: " + c.getHuespedId() + " Nombre: " + c.getNombre() + " DNI: " + c.getDni()
                    + " Domicilio: " + c.getDomicilio());
            if (c.getDomicilioAlternativo() != null)
                System.out.println(" Alternativo: " + c.getDomicilioAlternativo());
        }
    }

    public void listarTransaccion(List<EstadoDePago> estadoDePagos) {
        for (EstadoDePago x : estadoDePagos) {
            System.out.println("Estado: "+ x.getEstadoId()+"Cantidad Reservas: "+x.getCantidadReservas()+"Importe Pagado: "+x.getImportePagado()+"Importe Reserva: "+x.getImporteReserva()+"Importe Total: "+x.getImporteTotal());
        }
    }

    public void listarHabitacion(List<Habitacion> habitaciones) {
        for (Habitacion a : habitaciones) {
            System.out.println("");
        }
    }

    public static String solicitarString(String argumento) {
        System.out.println("Ingrese " + argumento + " : ");
        return (Teclado.nextLine());
    }

    public static int solicitarInt(String argumento) {
        System.out.println("Ingrese " + argumento + " : ");
        return (Teclado.nextInt());
    }

    public static void error() {
        System.out.println("Error opcion incorrecta");
    }


    public void opcionAModificarH(int id) throws HuespedDNIException {
        Huesped huesped = service.buscarHuesped(id);
        if (huesped != null) {
            hService.generarModificacion(huesped,solicitarInt("modificar: \n1: Nombre, \n2: DNI:, \n3: Domicilio, \n4: Domicilio Alternativo"));
        }else{
            error();
        }
    }
    
    public void opcionAModificarR(int id) throws HuespedDNIException {
        Reserva reserva = service.buscarReserva(id);
        if (reserva != null) {
            rService.generarModificacion(reserva,solicitarInt("modificar: \n1: fecha ingreso, \n2: fecha egreso:"));
        }else{
            error();
        }
    }
    public void opcionesParaBorrar(int opcion) {
        switch (opcion) {
            case 1:
                hService.generarBaja(solicitarInt("Id"), "ID", null);
                break;
            case 2:
                hService.generarBaja(solicitarInt("DNI"), "dni", null);
                break;
            case 3:
                hService.generarBaja(0, "nombre", solicitarString("nombre"));
                break;
            case 4:
                hService.generarBaja(0, "fecha", solicitarString("fecha"));
                break;
            default:
                break;
        }
    }
    
    public void opcionesParaBorrarR(int opcion) {
        switch (opcion) {
            case 1:
                rService.generarBaja(solicitarInt("Id"), "ID", null, null);
                break;
            case 2:
                rService.generarBaja(solicitarInt("DNI"), "dni", null,null);
                break;
            case 3:
                rService.generarBaja(0, "nombre", solicitarString("nombre"),null);
                break;
            case 4:
                rService.generarBaja(0, "fecha",null, solicitarString("fecha"));
                break;
            default:
                break;
        }
    }

    public void opcionesUsuario(int opcion) throws Exception {
        switch (opcion) {
            case 1:
                printOpcionesHuesped();
                opcionDeHuesped(Teclado.nextInt());
                break;
            case 2:
                printOpcionesReserva();
                opcionDeReserva(Teclado.nextInt());
                break;
            case 3:
                printOpcionesHabitacion();
                opcionesHabitacion(Teclado.nextInt());
                break;
            case 4:
                printOpcionesTransaccion();
                opcionesTransacion(Teclado.nextInt());
                break;
            default:
                error();
                break;
        }
    }

    public void opcionDeHuesped(int opcion) throws Exception {
        switch (opcion) {
            case 1:
                hService.generarAlta();
                break;
            case 2:
                printOpcionesBorrar();
                opcionesParaBorrar(solicitarInt("opcion :"));
                break;
            case 3:
                opcionAModificarH(solicitarInt("Id :"));
                break;
            case 4:
                listarHuesped(service.listaDeHuespeds("todo", null, 0));
                break;
            case 5:
                listarHuesped(service.listaDeHuespeds("ID", null, solicitarInt("ID")));
                break;
            case 6:
                listarHuesped(service.listaDeHuespeds("nombre", solicitarString("nombre"),0));
            break;
            case 7:
                listarHuesped(service.listaDeHuespeds("dni", null, solicitarInt("DNI")));
            break;
            default:
                error();
                break;
        }
    }

    public void opcionDeReserva(int opcion) throws Exception {
        switch (opcion) {
            case 1:
                rService.generarAlta();
                break;
            case 2:
                printOpcionesBorrar();
                opcionesParaBorrarR(solicitarInt("opcion :"));
                break;
            case 3:
                opcionAModificarR(solicitarInt("ID"));
                break;
            case 4:
                lisarReservas(service.listaDeReservas("todo",0, null,null));
            break;
            case 5:
                lisarReservas(service.listaDeReservas("ID", solicitarInt("ID"), null,null));
                break;
            case 6:
                lisarReservas(service.listaDeReservas("nombre",0, solicitarString("nombre"),null));
            break;
            case 7:
                lisarReservas(service.listaDeReservas("dni", solicitarInt("DNI"), null,null));
                break;
            case 8:
                lisarReservas(service.listaDeReservas("fecha_i", 0, null,solicitarString("Fecha de Ingreso")));
                break;
            case 9:
                lisarReservas(service.listaDeReservas("fecha_e", 0, null,solicitarString("Fecha de Egreso")));
            break;
            case 10:
                lisarReservas(service.listaDeReservas("fecha_r", 0, null,solicitarString("Fecha de Reserva")));
                break;
            default:
                error();
                break;
        }
    }

    public void opcionesHabitacion(int opcion) {
        switch (opcion) {
            case 1:
                listarHabitacion(service.listaDeHabitacion("precios",null));
                break;
            case 2:
                listarHabitacion(service.listaDeHabitacion("ocupados",null));
                break;
            case 3:
                listarHabitacion(service.listaDeHabitacion("libres",null));
                break;
            case 4:
                listarHabitacion(service.listaDeHabitacion("todo",null));
                break;
            case 5:
                listarHabitacion(service.listaDeHabitacion("fecha",solicitarString("fecha")));
                break;
            default:
                error();
                break;
        }
    }

    public void opcionesTransacion(int opcion) {
        switch (opcion) {
            case 1:
                listarTransaccion(service.lEstadoDePagos("todo", null, 0));
                break;
            case 2:
                listarTransaccion(service.lEstadoDePagos("deudores", null, 0));
                break;
            case 3:
                listarTransaccion(service.lEstadoDePagos("noDeudores", null, 0));
                break;
            case 4:
                listarTransaccion(service.lEstadoDePagos("dni", null,solicitarInt("dni")));
                break;
            case 5:
                listarTransaccion(service.lEstadoDePagos("nombre",solicitarString("nombre"), 0));
                break;
            default:
                error();
                break;
        }
    }

    public static void printOpcionesBorrar() {
        System.out.println("=======================================================");
        System.out.println("");
        System.out.println("1. Id.");
        System.out.println("2. Dni.");
        System.out.println("3. Nombre.");
        System.out.println("4. Fecha.");
        System.out.println("");
        System.out.println("=======================================================");
    }
    
    public void printOpciones() {
        System.out.println("=======================================================");
        System.out.println("Bienvenido le gustaria realizar realizar consultas en: ");
        System.out.println("");
        System.out.println("1. Huesped.");
        System.out.println("2. Reserva.");
        System.out.println("3. Habitacion.");
        System.out.println("4. Transaccion.");
        System.out.println("0. Salir");
        System.out.println("");
        System.out.println("=======================================================");
    }

    public static void printOpcionesHuesped(){
        System.out.println("========== HUESPED ================");
        System.out.println("");
        System.out.println("1. Para agregar nuevo huesped.");
        System.out.println("2. Para eliminar un huesped.");
        System.out.println("3. Para modificar un huesped.");
        System.out.println("4. Para ver el listado completo.");
        System.out.println("5. Buscar un huesped por ID especifico(SQL Injection)).");
        System.out.println("6. Buscar un huesped por nombre especifico(SQL Injection)).");
        System.out.println("7. Buscar un huesped por DNI especifico(SQL Injection)).");
        System.out.println("0. Para volver atras.");
        System.out.println("");
        System.out.println("=======================================");
    }

    public static void printOpcionesReserva(){
        System.out.println("========== RESERVA ================");
        System.out.println("");
        System.out.println("1. Para agregar nueva reserva.");
        System.out.println("2. Para eliminar una reserva.");
        System.out.println("3. Para modificar una reserva.");
        System.out.println("4. Para ver el listado completo.");
        System.out.println("5. Buscar una reserva por ID especifico(SQL Injection)).");
        System.out.println("6. Buscar una reserva por nombre huesped especifico(SQL Injection)).");
        System.out.println("7. Buscar una reserva por DNI huesped especifico(SQL Injection)).");
        System.out.println("8. Buscar una reserva por fecha de ingreso: ");
        System.out.println("9. Buscar una reserva por fecha de egreso: ");
        System.out.println("10. Buscar una reserva por fecha de reserva: ");
        System.out.println("0. Para volver atras.");
        System.out.println("");
        System.out.println("=======================================");
    }

    public static void printOpcionesHabitacion(){

        System.out.println("========== Habitacion ================");
        System.out.println("");
        System.out.println("1. Listado precios.");
        System.out.println("2. Listado habitaciones libres.");
        System.out.println("3. Listado habitaciones ocupadas.");
        System.out.println("4. Listado de habitaciones.");
        System.out.println("5. Buscar diponibilidad segun fecha.");
        System.out.println("0. Para volver atras.");
        System.out.println("");
        System.out.println("=======================================");
    }

    public static void printOpcionesTransaccion(){

        System.out.println("========== Transaccion ================");
        System.out.println("");
        System.out.println("1. Listar todos");
        System.out.println("2. Lista de deudores.");
        System.out.println("3. Lista de los libres de deudas.");
        System.out.println("4. Buscar estado de pago de un huesped por DNI.");
        System.out.println("5. Buscar estado de pago de un huesped por nombre.");
        System.out.println("0. Para volver atras.");
        System.out.println("");
        System.out.println("=======================================");
    }
    
}