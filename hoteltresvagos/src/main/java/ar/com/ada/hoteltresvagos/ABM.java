package ar.com.ada.hoteltresvagos;

import java.math.BigDecimal;

import java.text.*;

import java.util.*;

import ar.com.ada.hoteltresvagos.entities.*;
import ar.com.ada.hoteltresvagos.excepciones.HuespedDNIException;
import ar.com.ada.hoteltresvagos.managers.*;

public class ABM {

    public static Scanner Teclado = new Scanner(System.in);

    protected HuespedManager ABMHuesped = new HuespedManager();

    protected ReservaManager ABMReserva = new ReservaManager();

    protected HabitacionManager ABMHabitacion = new HabitacionManager();

    protected EstadoPagoManager ABMEstadoPago = new EstadoPagoManager();

    public void iniciar() throws Exception {
        try {
            managers("open");
            printOpciones();
            int opcion = Teclado.nextInt();
            Teclado.nextLine();
            while (opcion > 0) {
                opcionesUsuario(opcion);
                printOpciones();
                opcion = Teclado.nextInt();
                Teclado.nextLine();
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

    public void alta() throws Exception {
        Huesped huesped = new Huesped();
        System.out.println("Ingrese el nombre:");
        huesped.setNombre(Teclado.nextLine());

        System.out.println("Ingrese el DNI:");
        huesped.setDni(Teclado.nextInt());

        System.out.println("Ingrese la domicilio:");
        huesped.setDomicilio(Teclado.nextLine());

        System.out.println("Ingrese el Domicilio alternativo(OPCIONAL):");
        String domAlternativo = Teclado.nextLine();
        if (domAlternativo != null)
            huesped.setDomicilioAlternativo(domAlternativo);
        Reserva reserva = generarReserva();
        reserva.setHuesped(huesped);
        ABMReserva.create(reserva);
        ABMHuesped.create(huesped);
        System.out.println("Huesped generada con exito.  " + huesped);
    }

    public Reserva generarReserva() {
        Reserva reserva = new Reserva();
        reserva.setImporteReserva(new BigDecimal(1000));
        reserva.setImporteTotal(new BigDecimal(3000));
        reserva.setImportePagado(new BigDecimal(0));
        reserva.setFechaReserva(new Date());
        reserva.setFechaIngreso(ingresarFecha("Ingreso"));
        reserva.setFechaEgreso(ingresarFecha("Engreso"));
        return reserva;
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

    public void bajaReserva(int id) {
        Reserva reservaEncontrada = ABMReserva.read(id);
        if (reservaEncontrada == null) {
            System.out.println("reserva no encontrada.");
        } else {
            try {
                ABMReserva.delete(reservaEncontrada);
                System.out.println("La reserva " + reservaEncontrada.getReservaId() + " ha sido eliminado.");
            } catch (Exception e) {
                System.out.println("Ocurrio un error al eliminar una RESERVA. Error: " + e.getCause());
            }
        }
    }

    public void bajaPorDNI(int dni) {
        Huesped huespedEncontrado = ABMHuesped.readByDNI(dni);
        if (huespedEncontrado == null) {
            System.out.println("Huesped no encontrado.");
        } else {
            ABMHuesped.delete(huespedEncontrado);
            System.out.println("El registro del DNI " + huespedEncontrado.getDni() + " ha sido eliminado.");
        }
    }

    public void modifica(int id) throws Exception {
        Huesped huespedEncontrado = ABMHuesped.read(id);
        if (huespedEncontrado != null) {
            System.out.println("Elija qué dato de la huesped desea modificar: \n1: nombre, \n2: DNI, \n3: domicilio, \n4: domicilio alternativo");
            opcionAModificar(Teclado.nextInt(), huespedEncontrado);
            System.out.println("El registro de " + huespedEncontrado.getNombre() + " ha sido modificado.");
        } else {
            System.out.println("Huesped no encontrado.");
        }
    }

    private void modificaReserva(int id) {
        Reserva reservaEncontrada = ABMReserva.read(id);
        if (reservaEncontrada != null) {
            System.out.println("Elija qué dato de la reserva desea modificar: \n1: Fecha ingreso, \n2: Fecha de egreso:");
            opcionAModificar(Teclado.nextInt(), reservaEncontrada);
            System.out.println("La reserva con ID " + reservaEncontrada.getReservaId() + " ha sido modificado.");
        } else {
            System.out.println("Reserva no encontrado.");
        }
    }

    private void opcionAModificar(int opcion, Reserva reservaEncontrada) {
        switch (opcion) {
            case 1:
                System.out.println("Ingrese la nueva fecha de ingreso");
                reservaEncontrada.setFechaIngreso(ingresarFecha(Teclado.nextLine()));
                break;
            case 2:
                System.out.println("Ingrese la nueva fecha de egreso");
                reservaEncontrada.setFechaEgreso(ingresarFecha(Teclado.nextLine()));
                break;
            default:
                break;
        }
        ABMReserva.update(reservaEncontrada);
    }

    private void opcionAModificar(int opcion, Huesped huespedEncontrado) throws HuespedDNIException {
        switch (opcion) {
            case 1:
                System.out.println("Ingrese el nuevo nombre:");
                huespedEncontrado.setNombre(Teclado.nextLine());
                break;
            case 2:
                System.out.println("Ingrese el nuevo DNI:");
                huespedEncontrado.setDni(Teclado.nextInt());
                break;
            case 3:
                System.out.println("Ingrese el nuevo domicilio:");
                huespedEncontrado.setDomicilio(Teclado.nextLine());
                break;
            case 4:
                System.out.println("Ingrese el nuevo domicilio alternativo:");
                huespedEncontrado.setDomicilioAlternativo(Teclado.nextLine());
                break;
            default:
                break;
        }
        ABMHuesped.update(huespedEncontrado);
    }

    private void altaReserva(int dni) throws Exception {
        List<Huesped> huespedes = ABMHuesped.buscarPor(dni);
        if (huespedes == null) {
            alta();
        }else{
            Reserva reserva = generarReserva();
            for (Huesped x : huespedes) {
                reserva.setHuesped(x);
                System.out.println("Huesped generada con exito.  " + x);
            }
            ABMReserva.create(reserva);
        }
    }

    public void listarReservas(){
        List<Reserva> todas = ABMReserva.buscarTodas();
        for (Reserva x : todas) {
            mostrarReserva(x);
        }
    }

    private void listarReserva(String nombre) {
        List<Reserva> todas = ABMReserva.buscarTodas(nombre);
        for (Reserva x : todas) {
            mostrarReserva(x);
        }
    }

    private void listarReserva(int tipo, String dato) {
        List<Reserva> todas = ABMReserva.buscarTodas(tipo,dato);
        for (Reserva x : todas) {
            mostrarReserva(x);
        }
    }

    private void listar(Date ingresarFecha) {
        List<Reserva> todas = ABMReserva.buscarTodas(ingresarFecha);
        for (Reserva x : todas) {
            mostrarReserva(x);
        }
    }

    public void listar() {
        List<Huesped> todos = ABMHuesped.buscarTodos();
        for (Huesped c : todos) {
            mostrarHuesped(c);
        }
    }

    private void listar(String tipo){
        List<Huesped> huespedes = ABMHuesped.buscarPor(tipo);
        for (Huesped huesped : huespedes) {
            mostrarHuesped(huesped);
        }
    }

    private void listar(int tipo) {
        List<Huesped> huespedes = ABMHuesped.buscarPor(tipo);
        for (Huesped huesped : huespedes) {
            mostrarHuesped(huesped);
        }
    }

    private void listarTransaccion(int nextInt) {
    }

    private void listarTransaccion(String string) {
    }

    private void listarHabitacion(String string) {
    }

    private void listarHabitacion(Date ingresarFecha) {

    }

    public void mostrarReserva(Reserva reserva) {
        System.out.println("Id: " + reserva.getReservaId() + " Nombre: " + reserva.getHuesped().getNombre()
        + "Importe pagado: "   +reserva.getImportePagado()
        + "Importe reserva: "  + reserva.getImporteReserva()
        + "Importe Total: "    + reserva.getImporteTotal()
        + "Fecha de Reserva: " + reserva.getFechaReserva()
        + "Fecha de Engreso: " + reserva.getFechaEgreso()
        + "Fecha de Ingreso: " + reserva.getFechaIngreso()
        + "Estado: " + reserva.getTipoEstado());
    }

    public void mostrarHuesped(Huesped huesped) {
        System.out.println("Id: " + huesped.getHuespedId() + " Nombre: " + huesped.getNombre()
        + " DNI: " + huesped.getDni()
        + " Domicilio: " + huesped.getDomicilio());
        if (huesped.getDomicilioAlternativo() != null)
            System.out.println(" Alternativo: " + huesped.getDomicilioAlternativo());
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
                System.out.println("La opcion no es correcta.");
                break;
        }
    }

    private void opcionDeHuesped(int opcion) throws Exception {
        switch (opcion) {
            case 1:
                alta();
                break;
            case 2:
                System.out.println("Ingrese el DNI de Huesped:");
                bajaPorDNI(Teclado.nextInt());
                break;
            case 3:
                System.out.println("Ingrese el ID de la huesped a modificar:");
                modifica(Teclado.nextInt());
                break;
            case 4:
                listar();
                break;
            case 5:
                System.out.println("Ingrese el ID:");
                listar(Teclado.nextInt());
                break;
            case 6:
                System.out.println("Ingrese el Nombre:");
                listar(Teclado.nextLine());
            break;
            case 7:
                System.out.println("Ingrese el DNI:");
                listar(Teclado.nextInt());
                break;
            default:
                System.out.println("La opcion no es correcta.");
                break;
        }
    }
        
    private void opcionDeReserva(int opcion) throws Exception {
        switch (opcion) {
            case 1:
                System.out.println("Ingrese si DNI");
                altaReserva(Teclado.nextInt());
                break;
            case 2:
                System.out.println("Ingrese el ID de reserva:");
                bajaReserva(Teclado.nextInt());
                break;
            case 3:
                System.out.println("Ingrese el ID de reserva:");
                modificaReserva(Teclado.nextInt());
                break;
            case 4:
                listarReservas();
                break;
            case 5:
                System.out.println("Ingrese el ID:");
                listarReserva(Teclado.nextInt(),"id");
                break;
            case 6:
                System.out.println("Ingrese el Nombre:");
                listarReserva(Teclado.nextLine());
            break;
            case 7:
                System.out.println("Ingrese el DNI:");
                listarReserva(Teclado.nextInt(),"dni");
                break;
            case 8:
                System.out.println("Ingrese Fecha de ingreso:");
                listar(ingresarFecha(Teclado.nextLine()));
                break;
            case 9:
                System.out.println("Ingrese Fecha de egreso:");
                listar(ingresarFecha(Teclado.nextLine()));
            break;
            case 10:
                System.out.println("Ingrese Fecha de reserva:");
                listar(ingresarFecha(Teclado.nextLine()));
                break;
            default:
                System.out.println("La opcion no es correcta.");
                break;
        }
    }

    private void opcionesHabitacion(int opcion) {
        switch (opcion) {
            case 1:
                listarHabitacion("porPrecio");
                break;
            case 2:
                listarHabitacion("ocupadas");
                break;
            case 3:
                listarHabitacion("libres");
                break;
            case 4:
                listarHabitacion("todas");
                break;
            case 5:
                listarHabitacion(ingresarFecha(Teclado.nextLine()));
                break;
            default:
                System.out.println("La opcion no es correcta.");
                break;
        }
    }

    private void opcionesTransacion(int opcion) {
        switch (opcion) {
            case 1:
                listarTransaccion("deudores");
                break;
            case 2:
                listarTransaccion("noDeudores");
                break;
            case 3:
                System.out.println("Ingrese el DNI:");
                listarTransaccion(Teclado.nextInt());
                break;
            case 4:
                System.out.println("nombre");
                listarTransaccion(Teclado.nextLine());
                break;
            default:
                System.out.println("La opcion no es correcta.");
                break;
        }
    }

    public static void printOpciones() {
        System.out.println("=======================================================");
        System.out.println("Bienvenido le gustaria realizar realizar consultas en: ");
        System.out.println("");
        System.out.println("1. Huesped.");
        System.out.println("2. Reserva.");
        System.out.println("3. Habitacion.");
        System.out.println("4. Transaccion.");
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
        System.out.println("1. Lista de deudores.");
        System.out.println("2. Lista de los libres de deudas.");
        System.out.println("3. Buscar estado de pago de un huesped por DNI.");
        System.out.println("4. Buscar estado de pago de un huesped por nombre.");
        System.out.println("0. Para volver atras.");
        System.out.println("");
        System.out.println("=======================================");
    }
}