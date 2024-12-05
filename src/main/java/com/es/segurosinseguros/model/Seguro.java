package com.es.segurosinseguros.model;

import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "seguros")
public class Seguro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idSeguro;
    @Column(name = "nif", nullable = false, unique = true)
    private String nif;
    @Column(name = "nombre", nullable = false)
    private String nombre;
    @Column(name = "ape1", nullable = false)
    private String ape1;
    @Column(name = "ape2")
    private String ape2;
    @Column(name = "edad", nullable = false)
    private int edad;
    @Column(name = "num_hijos")
    private int numHijos;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "fecha_creacion")
    private Date fechaCreacion;
    @Column(name = "sexo", nullable = false)
    private String sexo;
    @Column(name = "casado")
    private boolean casado;
    @Column(name = "embarazada")
    private boolean embarazada;
    @OneToMany(mappedBy = "seguro", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<AsistenciaMedica> asistenciaMedica; // Relación con la entidad AsistenciaMedica (1 a muchos)>
    @OneToOne
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;

    public Seguro() {
    }

    public Seguro(boolean embarazada, boolean casado, String sexo, Date fechaCreacion, int numHijos, int edad, String ape2, String ape1, String nombre, String nif, Long idSeguro) {
        this.embarazada = embarazada;
        this.casado = casado;
        this.sexo = sexo;
        this.fechaCreacion = fechaCreacion;
        this.numHijos = numHijos;
        this.edad = edad;
        this.ape2 = ape2;
        this.ape1 = ape1;
        this.nombre = nombre;
        this.nif = nif;
        this.idSeguro = idSeguro;
    }

    public Long getIdSeguro() {
        return idSeguro;
    }

    public void setIdSeguro(Long idSeguro) {
        this.idSeguro = idSeguro;
    }

    public String getNif() {
        return nif;
    }

    public void setNif(String nif) {
        this.nif = nif;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApe1() {
        return ape1;
    }

    public void setApe1(String ape1) {
        this.ape1 = ape1;
    }

    public String getApe2() {
        return ape2;
    }

    public void setApe2(String ape2) {
        this.ape2 = ape2;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public int getNumHijos() {
        return numHijos;
    }

    public void setNumHijos(int numHijos) {
        this.numHijos = numHijos;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public boolean isCasado() {
        return casado;
    }

    public void setCasado(boolean casado) {
        this.casado = casado;
    }

    public boolean isEmbarazada() {
        return embarazada;
    }

    public void setEmbarazada(boolean embarazada) {
        this.embarazada = embarazada;
    }

    public List<AsistenciaMedica> getAsistenciaMedica() {
        return asistenciaMedica;
    }

    public void setAsistenciaMedica(List<AsistenciaMedica> asistenciaMedica) {
        this.asistenciaMedica = asistenciaMedica;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
