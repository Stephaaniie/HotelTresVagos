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

    protected static HuespedManager huespedManager;

    protected static ReservaManager reservaManager;

    protected HabitacionManager habitacionManager;

    protected EstadoPagoManager ePagoManager;

    private EstadoService eService;

    private HabitacionService hService;

    private ReservaService rService;

    private HuespedService huService;

    public Service(HuespedManager huespedManager, ReservaManager reservaManager, HabitacionManager habitacionManager,EstadoPagoManager ePagoManager) {
        Service.huespedManager = huespedManager;

        Service.reservaManager = reservaManager;

        this.habitacionManager = habitacionManager;

        this.ePagoManager = ePagoManager;
    }

    public Service(HuespedService huService) {
        this.huService = huService;
    }

    public Service(EstadoService eService) {
        this.eService = eService;
    }

    public Service(HabitacionService hService) {
        this.hService = hService;
    }

    public Service(ReservaService rService) {
        this.rService = rService;
    }

    public static List<Reserva> buscarPor(int tipo) {
        return huespedManager.getReservasPorDni(tipo);
    }

    public boolean existeHuespedEnBD(int id) {
        boolean existe = false;
        if (huespedManager.getHuespedConDni(id) != null)
            existe = true;
        return existe;
    }

    public Huesped buscarHuesped(int id) {
        return huespedManager.getHuespedConDni(id);
    }

    public Reserva buscarReserva(int id) {
        return reservaManager.getReservaConId(id);
    }

    public List<Reserva> listaDeReservas(String opcion, int dato, String nombre, String fecha) {
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

    public static HuespedManager getHuespedManager() {
        return huespedManager;
    }

    public static void setHuespedManager(HuespedManager huespedManager) {
        Service.huespedManager = huespedManager;
    }

    public static ReservaManager getReservaManager() {
        return reservaManager;
    }

    public static void setReservaManager(ReservaManager reservaManager) {
        Service.reservaManager = reservaManager;
    }

    public HabitacionManager getHabitacionManager() {
        return habitacionManager;
    }

    public void setHabitacionManager(HabitacionManager habitacionManager) {
        this.habitacionManager = habitacionManager;
    }

    public EstadoPagoManager getePagoManager() {
        return ePagoManager;
    }

    public void setePagoManager(EstadoPagoManager ePagoManager) {
        this.ePagoManager = ePagoManager;
    }

    public EstadoService geteService() {
        return eService;
    }

    public void seteService(EstadoService eService) {
        this.eService = eService;
    }

    public HabitacionService gethService() {
        return hService;
    }

    public void sethService(HabitacionService hService) {
        this.hService = hService;
    }

    public ReservaService getrService() {
        return rService;
    }

    public void setrService(ReservaService rService) {
        this.rService = rService;
    }

    public HuespedService getHuService() {
        return huService;
    }

    public void setHuService(HuespedService huService) {
        this.huService = huService;
    }
}