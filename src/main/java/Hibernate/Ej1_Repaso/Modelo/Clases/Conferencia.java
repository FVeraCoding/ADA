
package Hibernate.Ej1_Repaso.Modelo.Clases;

import jakarta.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Entity
public class Conferencia implements Serializable {
    @Id
    private String nombre;
    private LocalDate fechaInicio;
    private String lugar;
    private double numeroHoras;
    
    @ManyToMany(mappedBy = "listaConferencias", cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY)
    List<Investigador> listaInvestigadores;

    public Conferencia() {
    }

    public Conferencia(String nombre, LocalDate fechaInicio, String lugar, double numeroHoras, List<Investigador> listaInvestigadores) {
        this.nombre = nombre;
        this.fechaInicio = fechaInicio;
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

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(LocalDate fechaInicio) {
        this.fechaInicio = fechaInicio;
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
        if(!this.getListaInvestigadores().contains(inv)){
            this.getListaInvestigadores().add(inv);
            
            if(!inv.listaConferencias.contains(this)){
                inv.listaConferencias.add(this);
            }
        }
        
        
    }
}
