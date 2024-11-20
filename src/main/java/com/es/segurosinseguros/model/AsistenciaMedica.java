package com.es.segurosinseguros.model;
import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "asistencias_medicas")
public class AsistenciaMedica {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idAsistenciaMedica;
    @ManyToOne
     @JoinColumn(name = "id_seguro")
    private Seguro seguro;
    @Column(name = "breve_descripcion", nullable = false)
    private String breveDescripcion;
    @Column(name = "lugar", nullable = false)
    private String lugar;
    @Column(name = "explicacion", nullable = false)
    private String explicacion;
    @Column(name = "tipo_asistencia", nullable = false)
    private String tipoAsistencia;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "fecha", nullable = false)
    private LocalDate fecha;
    @DateTimeFormat(pattern = "HH:mm:ss")
    @Column(name = "hora", nullable = false)
    private LocalTime hora;
    @Column(name = "importe", nullable = false)
    private double importe;

    public AsistenciaMedica() {}

    public AsistenciaMedica(Long idAsistenciaMedica, Seguro seguro, String breveDescripcion, String lugar, String explicacion, String tipoAsistencia, LocalDate fecha, LocalTime hora, double importe) {
        this.idAsistenciaMedica = idAsistenciaMedica;
        this.seguro = seguro;
        this.breveDescripcion = breveDescripcion;
        this.lugar = lugar;
        this.explicacion = explicacion;
        this.tipoAsistencia = tipoAsistencia;
        this.fecha = fecha;
        this.hora = hora;
        this.importe = importe;
    }

    public Long getIdAsistenciaMedica() {
        return idAsistenciaMedica;
    }

    public void setIdAsistenciaMedica(Long idAsistenciaMedica) {
        this.idAsistenciaMedica = idAsistenciaMedica;
    }

    public Seguro getSeguro() {
        return seguro;
    }

    public void setSeguro(Seguro seguro) {
        this.seguro = seguro;
    }

    public String getBreveDescripcion() {
        return breveDescripcion;
    }

    public void setBreveDescripcion(String breveDescripcion) {
        this.breveDescripcion = breveDescripcion;
    }

    public String getLugar() {
        return lugar;
    }

    public void setLugar(String lugar) {
        this.lugar = lugar;
    }

    public String getExplicacion() {
        return explicacion;
    }

    public void setExplicacion(String explicacion) {
        this.explicacion = explicacion;
    }

    public String getTipoAsistencia() {
        return tipoAsistencia;
    }

    public void setTipoAsistencia(String tipoAsistencia) {
        this.tipoAsistencia = tipoAsistencia;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public LocalTime getHora() {
        return hora;
    }

    public void setHora(LocalTime hora) {
        this.hora = hora;
    }

    public double getImporte() {
        return importe;
    }

    public void setImporte(double importe) {
        this.importe = importe;
    }
}
