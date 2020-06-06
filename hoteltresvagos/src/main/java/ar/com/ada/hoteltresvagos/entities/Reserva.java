package ar.com.ada.hoteltresvagos.entities;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Future;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "reserva")
public class Reserva {
    @Id
    @Column(name = "reserva_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int reservaId;

    @DateTimeFormat(pattern = "dd.MM.yy")
    @Temporal(value=TemporalType.TIMESTAMP) 
    @Future
    @Column(name = "fecha_reserva")
    private Date fechaReserva;

    @DateTimeFormat(pattern = "dd.MM.yy") 
    @Temporal(value=TemporalType.TIMESTAMP) 
    @Future
    @Column(name = "fecha_ingreso")
    private Date fechaIngreso;


    @Column(name = "fecha_egreso")
    private Date fechaEgreso;

    private Integer habitacion;

    @Column (name = "importe_reserva")
    private BigDecimal importeReserva;

    @Column(name = "importe_total")
    private BigDecimal importeTotal;

    @Column(name = "importe_pagado")
    private BigDecimal importePagado;

    @Column (name = "estado_id")
    private int tipoEstado;

    @ManyToOne 
    @JoinColumn(name = "huesped_id",referencedColumnName = "huesped_id")
    private Huesped huesped;

    public int getReservaId() {
        return reservaId;
    }

    public void setReservaId(int reservaId) {
        this.reservaId = reservaId;
    }

    public Date getFechaReserva() {
        return fechaReserva;
    }

    public void setFechaReserva(Date fechaReserva) {
        this.fechaReserva = fechaReserva;
    }

    public Date getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(Date fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public Date getFechaEgreso() {
        return fechaEgreso;
    }

    public void setFechaEgreso(Date fechaEgreso) {
        this.fechaEgreso = fechaEgreso;
    }

    public Integer getHabitacion() {
        return habitacion;
    }

    public void setHabitacion(Integer habitacion) {
        this.habitacion = habitacion;
    }

    public BigDecimal getImporteReserva() {
        return importeReserva;
    }

    public void setImporteReserva(BigDecimal importeReserva) {
        this.importeReserva = importeReserva;
    }

    public BigDecimal getImporteTotal() {
        return importeTotal;
    }

    public void setImporteTotal(BigDecimal importeTotal) {
        this.importeTotal = importeTotal;
    }

    public BigDecimal getImportePagado() {
        return importePagado;
    }

    public void setImportePagado(BigDecimal importePagado) {
        this.importePagado = importePagado;
    }

    public int getTipoEstado() {
        return tipoEstado;
    }

    public void setTipoEstado(int tipoEstado) {
        this.tipoEstado = tipoEstado;
    }

    public Huesped getHuesped() {
        return huesped;
    }

    public void setHuesped(Huesped huesped) {
        this.huesped = huesped;
        this.huesped.getReservas().add(this);
    }
}