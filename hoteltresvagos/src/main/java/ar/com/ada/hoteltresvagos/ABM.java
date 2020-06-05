package ar.com.ada.hoteltresvagos;

import java.math.BigDecimal;

import java.text.*;

import java.util.*;

import ar.com.ada.hoteltresvagos.entities.*;

import ar.com.ada.hoteltresvagos.managers.HuespedManager;
import ar.com.ada.hoteltresvagos.managers.ReservaManager;

public class ABM {

    public static Scanner Teclado = new Scanner(System.in);

    protected HuespedManager ABMHuesped = new HuespedManager();

    protected ReservaManager ABMReserva = new  ReservaManager();

    public void iniciar() throws Exception {
        try {
            ABMHuesped.setup();
            ABMReserva.setup();
            printOpciones();
            int opcion = Teclado.nextInt();
            Teclado.nextLine();

            while (opcion > 0) {
                opcionesUsuario(opcion);
                printOpciones();
                opcion = Teclado.nextInt();
                Teclado.nextLine();
            }
            ABMHuesped.exit();
        } catch (Exception e) {
            System.out.println("Que lindo mi sistema,se rompio mi sistema");
            throw e;
        } finally {
            System.out.println("Saliendo del sistema, bye bye...");
        }
    }

    public void opcionesUsuario(int opcion) throws Exception {
        switch (opcion) {
            case 1:
                alta();
                break;
            case 2:
                baja();
                break;
            case 3:
                modifica();
                break;
            case 4:
                listar();
                break;
            case 5:
                listarPorNombre();
                break;
            case 6:
                listarPorNombre();
                break;
            case 7:
                listarReservas();
                break;
            default:
                System.out.println("La opcion no es correcta.");
                break;
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
        ABMHuesped.create(huesped);
        System.out.println("Huesped generada con exito.  " + huesped);
    }

    public Reserva generarReserva(){
        Reserva reserva = new Reserva();

        reserva.setImporteReserva(new BigDecimal(1000));

        reserva.setImporteTotal(new BigDecimal(3000));

        reserva.setImportePagado(new BigDecimal(0));

        reserva.setFechaReserva(new Date());

        reserva.setFechaIngreso(ingresarFecha(reserva, "Ingreso"));

        reserva.setFechaEgreso(ingresarFecha(reserva, "Engreso"));

        return reserva;
    }

    public Date ingresarFecha(Reserva reserva, String tipo) {
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
        }while(!esValida);
        return fechaValida;
    }

    public void baja() {
        System.out.println("Ingrese el nombre:");
        String nombre = Teclado.nextLine();
        System.out.println("Ingrese el ID de Huesped:");

        int id = Teclado.nextInt();
        Teclado.nextLine();
        Huesped huespedEncontrado = ABMHuesped.read(id);

        if (huespedEncontrado == null) {
            System.out.println("Huesped no encontrado.");

        } else {
            try {
                ABMHuesped.delete(huespedEncontrado);
                System.out
                        .println("El registro del huesped " + huespedEncontrado.getHuespedId() + " ha sido eliminado.");
            } catch (Exception e) {
                System.out.println("Ocurrio un error al eliminar una huesped. Error: " + e.getCause());
            }
        }
    }

    public void bajaPorDNI() {
        System.out.println("Ingrese el nombre:");
        String nombre = Teclado.nextLine();
        System.out.println("Ingrese el DNI de Huesped:");
        int dni = Teclado.nextInt();
        Huesped huespedEncontrado = ABMHuesped.readByDNI(dni);

        if (huespedEncontrado == null) {
            System.out.println("Huesped no encontrado.");

        } else {
            ABMHuesped.delete(huespedEncontrado);
            System.out.println("El registro del DNI " + huespedEncontrado.getDni() + " ha sido eliminado.");
        }
    }

    public void modifica() throws Exception {
        // System.out.println("Ingrese el nombre de la huesped a modificar:");
        // String n = Teclado.nextLine();

        System.out.println("Ingrese el ID de la huesped a modificar:");
        int id = Teclado.nextInt();
        Teclado.nextLine();
        Huesped huespedEncontrado = ABMHuesped.read(id);

        if (huespedEncontrado != null) {

            // RECOMENDACION NO USAR toString(), esto solo es a nivel educativo.
            System.out.println(huespedEncontrado.toString() + " seleccionado para modificacion.");

            System.out.println(
                    "Elija qué dato de la huesped desea modificar: \n1: nombre, \n2: DNI, \n3: domicilio, \n4: domicilio alternativo");
            int selecper = Teclado.nextInt();

            switch (selecper) {
                case 1:
                    System.out.println("Ingrese el nuevo nombre:");
                    Teclado.nextLine();
                    huespedEncontrado.setNombre(Teclado.nextLine());

                    break;
                case 2:
                    System.out.println("Ingrese el nuevo DNI:");
                    Teclado.nextLine();
                    huespedEncontrado.setDni(Teclado.nextInt());
                    Teclado.nextLine();

                    break;
                case 3:
                    System.out.println("Ingrese el nuevo domicilio:");
                    Teclado.nextLine();
                    huespedEncontrado.setDomicilio(Teclado.nextLine());

                    break;
                case 4:
                    System.out.println("Ingrese el nuevo domicilio alternativo:");
                    Teclado.nextLine();
                    huespedEncontrado.setDomicilioAlternativo(Teclado.nextLine());

                    break;

                default:
                    break;
            }

            // Teclado.nextLine();

            ABMHuesped.update(huespedEncontrado);

            System.out.println("El registro de " + huespedEncontrado.getNombre() + " ha sido modificado.");

        } else {
            System.out.println("Huesped no encontrado.");
        }

    }

    public void listar() {
        List<Huesped> todos = ABMHuesped.buscarTodos();
        for (Huesped c : todos) {
            mostrarHuesped(c);
        }
    }

    public void listarReservas(){
        List<Reserva> todas = ABMReserva.buscarTodas();
        for (Reserva x : todas) {
            mostrarReserva(x);
        }
    }
    public void listarPorNombre() {

        System.out.println("Ingrese el nombre:");
        String nombre = Teclado.nextLine();

        List<Huesped> huespedes = ABMHuesped.buscarPor(nombre);
        for (Huesped huesped : huespedes) {
            mostrarHuesped(huesped);
        }
    }

    public void mostrarReserva(Reserva reserva) {

        System.out.print("Id: " + reserva.getReservaId() + " Nombre: " + reserva.getHuesped().getNombre()
        + "Importe pagado: "   +reserva.getImportePagado()
        + "Importe reserva: "  + reserva.getImporteReserva()
        + "Importe Total: "    + reserva.getImporteTotal()
        + "Fecha de Reserva: " + reserva.getFechaReserva()
        + "Fecha de Engreso: " + reserva.getFechaEgreso()
        + "Fecha de Ingreso: " + reserva.getFechaIngreso()
        + "Estado: " + reserva.getTipoEstado());
    }

    public void mostrarHuesped(Huesped huesped) {

        System.out.print("Id: " + huesped.getHuespedId() + " Nombre: " + huesped.getNombre()
        + " DNI: " + huesped.getDni()
        + " Domicilio: " + huesped.getDomicilio());

        if (huesped.getDomicilioAlternativo() != null)
            System.out.println(" Alternativo: " + huesped.getDomicilioAlternativo());
    }

    public static void printOpciones() {
        System.out.println("=======================================");
        System.out.println("");
        System.out.println("1. Para agregar un huesped.");
        System.out.println("2. Para eliminar un huesped.");
        System.out.println("3. Para modificar un huesped.");
        System.out.println("4. Para ver el listado.");
        System.out.println("5. Buscar un huesped por nombre especifico(SQL Injection)).");
        System.out.println("6. Agregar una reserva.");
        System.out.println("7. Para ver el listado de reservas.");
        System.out.println("0. Para terminar.");
        System.out.println("");
        System.out.println("=======================================");
    }
}