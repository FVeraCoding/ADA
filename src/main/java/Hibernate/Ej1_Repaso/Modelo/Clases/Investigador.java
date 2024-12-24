
package Hibernate.Ej1_Repaso.Modelo.Clases;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
public class Investigador implements Serializable {
    @Id
    private String dni;
    @Embedded
    private NombreCompleto nombreCompleto;
    private String direccion;
    private int telefono;
    private String localidad;
    
    @ManyToOne
    Proyecto proyecto;
    
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY)
    @JoinTable(name = "Investigador_Conferencia",
            joinColumns = @JoinColumn(name = "investigador_dni"),
            inverseJoinColumns = @JoinColumn(name = "conferencia_nombre"))
    List<Conferencia> listaConferencias;

    public Investigador() {
    }

    public Investigador(String dni, NombreCompleto nombreCompleto, String direccion, int telefono, String localidad, Proyecto proyecto, List<Conferencia> listaConferencias) {
        this.dni = dni;
        this.nombreCompleto = nombreCompleto;
        this.direccion = direccion;
        this.telefono = telefono;
        this.localidad = localidad;
        this.proyecto = proyecto;
        this.listaConferencias = listaConferencias;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public NombreCompleto getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(NombreCompleto nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public int getTelefono() {
        return telefono;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    public String getLocalidad() {
        return localidad;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    public Proyecto getProyecto() {
        return proyecto;
    }

    public void setProyecto(Proyecto proyecto) {
        this.proyecto = proyecto;
    }   
    
    public void addConferencia(Conferencia conferencia){
        if(!this.listaConferencias.contains(conferencia)){
            this.listaConferencias.add(conferencia);
            
            if(!conferencia.getListaInvestigadores().contains(this)){
                conferencia.getListaInvestigadores().add(this);
            }
        }
    }
    
    public void addProyecto(Proyecto proyecto){
        this.setProyecto(proyecto);
        if(!proyecto.getListaInvestigadores().contains(this)){
            proyecto.getListaInvestigadores().add(this);
        }
    }
    
}
