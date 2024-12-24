package Hibernate.Ej1.Modelo.Clases;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Investigador implements Serializable {

    @Id
    private String dni;
    @Embedded
    private NombreCompleto nombre;
    private String direccion;
    private int telefono;
    private String localidad;

    @ManyToOne
    private Proyecto proyecto;

    @ManyToMany
    @JoinTable(name = "Investigador_Conferencia",
            joinColumns = @JoinColumn(name = "investigador_dni"),
            inverseJoinColumns = @JoinColumn(name = "conferencia_nombre")
    )
    private List<Conferencia> listaConferencias;

    public Investigador() {
        this.listaConferencias = new ArrayList<>();
    }

    public Investigador(String dni, NombreCompleto nombre, String direccion, int telefono, String localidad, Proyecto proyecto, List<Conferencia> listaConferencias) {
        this.dni = dni;
        this.nombre = nombre;
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

    public NombreCompleto getNombre() {
        return nombre;
    }

    public void setNombre(NombreCompleto nombre) {
        this.nombre = nombre;
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

    public List<Conferencia> getListaConferencias() {
        return listaConferencias;
    }

    public void setListaConferencias(List<Conferencia> listaConferencias) {
        this.listaConferencias = listaConferencias;
    }
    
    

    public void addConferencia(Conferencia conferencia) {
        if (!this.listaConferencias.contains(conferencia)) {
            listaConferencias.add(conferencia);

            if (!conferencia.getListaInvestigadores().contains(this)) {
                conferencia.addInvestigador(this);
            }
        }
    }

    public void addProyecto(Proyecto proyecto) {
        this.proyecto = proyecto;
        if (!proyecto.getListaInvestigadores().contains(this)) {
            proyecto.getListaInvestigadores().add(this);
        }
    }

}
