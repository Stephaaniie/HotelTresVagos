package ar.com.ada.hoteltresvagos.entities;

import java.math.BigDecimal;

import javax.persistence.*;

public class EstadoDePago {

    @Id
    @Column(name = "estado_id")
    private int estadoId;

    @Column(name = "cantidad_reservas")
    private int cantidadReservas;

    @Column(name = "importe_reserva")
    private BigDecimal importeReserva;

    @Column(name = "importe_pagado")
    private BigDecimal importePagado;

    @Column(name = "importe_total")
    private BigDecimal importeTotal;

    public int getEstadoId() {
        return estadoId;
    }

    public void setEstadoId(int estadoId) {
        this.estadoId = estadoId;
    }

    public int getCantidadReservas() {
        return cantidadReservas;
    }

    public void setCantidadReservas(int cantidadReservas) {
        this.cantidadReservas = cantidadReservas;
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
    
}