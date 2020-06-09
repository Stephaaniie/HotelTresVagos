package ar.com.ada.hoteltresvagos.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.*;

import ar.com.ada.hoteltresvagos.excepciones.HuespedDNIException;

@Entity
@Table(name = "huesped")
public class Huesped {
    @Id
    @Column(name = "huesped_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int huespedId;

    @NotNull
    @Size(min = 5, max = 20)
    private String nombre;

    @NotNull
    @Size(min = 0, max = 8)
    private int dni;
    
    @NotNull
    @Size(min = 5, max = 50)
    private String domicilio;

    @Size(min = 5, max = 50)
    @Column(name = "domicilio_alternativo")
    private String domicilioAlternativo;

    @OneToMany(mappedBy = "huesped", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Reserva> reservas = new ArrayList<>();   

    public Huesped(String nombre) {
        this.nombre = nombre;
    }

    public Huesped() {
    }

    public int getHuespedId() {
        return huespedId;
    }

    public void setHuespedId(int huespedId) {
        this.huespedId = huespedId;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setDni(int dni) throws HuespedDNIException {
        this.dni = dni;
    }

    public int getDni() {
        return dni;
    }

    @Override
    public String toString() {
        return "Huesped [dni=" + dni + ", nombre=" + nombre + "]";
    }

    public String getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(String domicilio) {
        this.domicilio = domicilio;
    }

    public String getDomicilioAlternativo() {
        return domicilioAlternativo;
    }

    public void setDomicilioAlternativo(String domicilioAlternativo) {
        this.domicilioAlternativo = domicilioAlternativo;
    }

    public void setReservas(List<Reserva> reservas) {
        this.reservas = reservas;
    }

	public List<Reserva> getReservas() {
		return this.reservas;
	}

}