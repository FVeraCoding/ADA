
package Hibernate.Ej1.Modelo.Clases;

import jakarta.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Entity
public class Proyecto implements Serializable {
    @Id
    private String nombre;
    
    @Basic
    private LocalDate FechaInicio;

    
    @OneToMany(mappedBy="proyecto")
    private List<Investigador> listaInvestigadores;
    
    public Proyecto() {
        this.listaInvestigadores = new ArrayList<>();
    }

    public Proyecto(String nombre, LocalDate FechaInicio, List<Investigador> listaInvestigadores) {
        this.nombre = nombre;
        this.FechaInicio = FechaInicio;
        this.listaInvestigadores = listaInvestigadores;
    }
    
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public LocalDate getFechaInicio() {
        return FechaInicio;
    }

    public void setFechaInicio(LocalDate FechaInicio) {
        this.FechaInicio = FechaInicio;
    }

    public List<Investigador> getListaInvestigadores() {
        return listaInvestigadores;
    }

    public void setListaInvestigadores(LinkedList<Investigador> listaInvestigadores) {
        this.listaInvestigadores = listaInvestigadores;
    }
    
    
}
