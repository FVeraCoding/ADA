
package Hibernate.Ej1_Repaso.Modelo.Clases;

import jakarta.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Entity
public class Proyecto implements Serializable {
    @Id
    private String nombre;
    private LocalDate fechaInicio;
    
    @OneToMany(mappedBy = "proyecto")
    List<Investigador> listaInvestigadores;

    public Proyecto() {
    }

    public Proyecto(String nombre, LocalDate fechaInicio, List<Investigador> listaInvestigadores) {
        this.nombre = nombre;
        this.fechaInicio = fechaInicio;
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

    public List<Investigador> getListaInvestigadores() {
        return listaInvestigadores;
    }

    public void setListaInvestigadores(List<Investigador> listaInvestigadores) {
        this.listaInvestigadores = listaInvestigadores;
    }
    
    public void addInvestigador(Investigador inv){
        if(!this.getListaInvestigadores().contains(inv)){
            this.getListaInvestigadores().add(inv);
            
            if(inv.getProyecto() != this){
                inv.setProyecto(this);
            }
        }
    }
}
