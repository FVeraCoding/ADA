package Hibernate.Ej1.Modelo.Clases;

import jakarta.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Conferencia implements Serializable {

    @Id
    private String nombre;

    private LocalDateTime fechaHoraInicio;
    private String lugar;
    private double numeroHoras;

    @ManyToMany(mappedBy = "listaConferencias")
    private List<Investigador> listaInvestigadores;

    public Conferencia() {
        this.listaInvestigadores = new ArrayList<>();
    }

    public Conferencia(String nombre, LocalDateTime fechaHoraInicio, String lugar, double numeroHoras, List<Investigador> listaInvestigadores) {
        this.nombre = nombre;
        this.fechaHoraInicio = fechaHoraInicio;
        this.lugar = lugar;
        this.numeroHoras = numeroHoras;
        this.listaInvestigadores = listaInvestigadores;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public LocalDateTime getFechaHoraInicio() {
        return fechaHoraInicio;
    }

    public void setFechaHoraInicio(LocalDateTime fechaHoraInicio) {
        this.fechaHoraInicio = fechaHoraInicio;
    }

    public String getLugar() {
        return lugar;
    }

    public void setLugar(String lugar) {
        this.lugar = lugar;
    }

    public double getNumeroHoras() {
        return numeroHoras;
    }

    public void setNumeroHoras(double numeroHoras) {
        this.numeroHoras = numeroHoras;
    }

    public List<Investigador> getListaInvestigadores() {
        return listaInvestigadores;
    }

    public void setListaInvestigadores(List<Investigador> listaInvestigadores) {
        this.listaInvestigadores = listaInvestigadores;
    }

    void addInvestigador(Investigador inv) {
        if (!this.getListaInvestigadores().contains(inv)) {
            this.listaInvestigadores.add(inv);

           
        }
    }

}
