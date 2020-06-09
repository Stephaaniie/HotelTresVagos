package ar.com.ada.hoteltresvagos.service;

import java.util.ArrayList;
import java.util.List;

import ar.com.ada.hoteltresvagos.entities.*;
import ar.com.ada.hoteltresvagos.managers.*;

public class Service {

    private static final String DEUDORES = "deudores";

    private static final String NO_DEUDORES = "noDeudores";

    public static final String NOMBRE = "nombre";

    public static final  String ID = "ID";

    public static final String DNI = "dni";

    public static final String FECHA_I = "fecha_i";

    public static final String FECHA_E = "fecha_e";

    public static final String FECHAR_R = "fecha_r";

    public static final String FECHA = "fecha";
    
    public static final String OCUPADO = "ocupados";
    
    public static final String LIBRE = "libres";

    public static final String PRECIO = "precios";

    public static final String TODO = "todo";

    protected HuespedManager huespedManager = new HuespedManager();

    protected ReservaManager reservaManager = new ReservaManager();

    protected HabitacionManager habitacionManager = new HabitacionManager();

    protected EstadoPagoManager ePagoManager = new EstadoPagoManager();


	public List<Reserva> buscarPor(int tipo) {
        return huespedManager.getReservasPorDni(tipo);
    }
    
    public boolean existeHuespedEnBD(int id) {
        boolean existe = false;
        if(huespedManager.getHuespedConDni(id) != null)
		    existe = true;
        return existe;
    }
    
	public Huesped buscarHuesped(int id) {
		return huespedManager.getHuespedConDni(id);
    }
    
    public Reserva buscarReserva(int id) {
		return reservaManager.getReservaConId(id);
	}

    public List<Reserva> listaDeReservas(String opcion,int dato, String nombre,String fecha){
        List<Reserva> reservas = new ArrayList<>();
        switch (opcion) {
            case TODO:
                reservas = reservaManager.getReservas();
                break;
            case ID:
                reservas = reservaManager.getReservasId(dato);
                break;
            case DNI:
                reservas = reservaManager.getRservasDniH(dato);
                break;
            case NOMBRE:
                reservas = reservaManager.getRservasNombre(nombre);
                break;
            case FECHA_I:
                reservas = reservaManager.getRservasFecha(fecha,"ingreso");
                break;
            case FECHA_E:
                reservas = reservaManager.getRservasFecha(fecha,"egreso");
                break;
            case FECHAR_R:
                reservas = reservaManager.getRservasFecha(fecha,"reserva");
                break;
            default:
                break;
        }
        return reservas;
    }

    public List<Huesped> listaDeHuespeds(String opcion, String nombre, int dato ){
        List<Huesped> huespedes = new ArrayList<>();
        switch (opcion) {
            case TODO:
                huespedes = huespedManager.getHuespedes();
                break;
            case NOMBRE:
                huespedes = huespedManager.getHuespedesConNombre(nombre);
                break;
            case ID:
                huespedes = huespedManager.getHuespedConId(dato);
                break;
            case DNI:
                huespedes.add(huespedManager.getHuespedConDni(dato));
                break;
            case FECHA:
                huespedes.add(huespedManager.getHuespededPorFecha(nombre));
                break;
            default:
                break;
        }
        return huespedes;        
    }
    public List<Habitacion> listaDeHabitacion(String opcion, String fecha){
        List<Habitacion> habitaciones =  new ArrayList<>();
        switch (opcion) {
            case OCUPADO:
                habitaciones = habitacionManager.getHabitacionesOcupadas();
                break;
            case LIBRE:
                habitaciones = habitacionManager.getHabitacionesLibres();
                break;
            case PRECIO:
                habitaciones = habitacionManager.getHabitacionesPorPrecio();
                break;
            case TODO:
                habitaciones = habitacionManager.getHabitaciones();
            case FECHA:
                habitaciones = habitacionManager.getHabitacionPorFecha(fecha);
            default:
                break;
        }
        return habitaciones;
    }

    public List<EstadoDePago> lEstadoDePagos(String opcion, String nombre, int dni){
        List<EstadoDePago> lestadoPago = new ArrayList<>();
        switch (opcion) {
            case DEUDORES:
                lestadoPago = ePagoManager.getPorEstadoId(0);
                break;
            case NO_DEUDORES:
                lestadoPago =  ePagoManager.getPorEstadoId(1);
                break;
            case TODO:
                lestadoPago = ePagoManager.getEstadosPago();
                break;
            case DNI:
                lestadoPago = ePagoManager.getEstadoPorDni(dni);
            case NOMBRE:
                lestadoPago = ePagoManager.getPorNombre(nombre);
                break;
            default:
                break;
        }
        return lestadoPago;
    }
}