
package Hibernate.Ej1.Modelo.Persistencia;

import Hibernate.Ej1.Modelo.Clases.Conferencia;
import Hibernate.Ej1.Modelo.Clases.Investigador;
import Hibernate.Ej1.Modelo.Clases.Proyecto;
import Hibernate.Ej1.Modelo.Persistencia.exceptions.NonexistentEntityException;
import java.util.ArrayList;
import java.util.List;


public class ControladoraPersistencia {
    ProyectoJpaController proController = new ProyectoJpaController();
    InvestigadorJpaController invController = new InvestigadorJpaController();
    ConferenciaJpaController conController = new ConferenciaJpaController();

    
    //Proyecto
    public void addProyecto(Proyecto proyecto) throws Exception {
        proController.create(proyecto);
    }

    public Proyecto readProyecto(String nombre) {
        return proController.findProyecto(nombre);
    }

    public ArrayList<Proyecto> readAllProyectos() {
        List<Proyecto> lista = proController.findProyectoEntities();
        ArrayList<Proyecto> listaProyectos = new ArrayList<>(lista);
        
        return listaProyectos;
    }

    public void editProyecto(Proyecto proyecto) throws Exception {
        proController.edit(proyecto);
    }

    public void deleteProyecto(String nombre) throws NonexistentEntityException {
        proController.destroy(nombre);
    }
    
    //Investigador

    public void addInvestigador(Investigador investigador) throws Exception {
        invController.create(investigador);
    }

    public Investigador readInvestigador(String dni) {
        return invController.findInvestigador(dni);
    }

    public ArrayList<Investigador> readAllInvestigadores() {
        List<Investigador> lista = invController.findInvestigadorEntities();
        ArrayList<Investigador> listaInv = new ArrayList<>(lista);
        
        return listaInv;
    }

    public void editInvestigador(Investigador investigador) throws Exception {
        invController.edit(investigador);
    }

    public void deleteInvestigador(String dni) throws NonexistentEntityException {
        invController.destroy(dni);
    }
    
    
    //Conferencia

    public void addConferencia(Conferencia conferencia) throws Exception {
        conController.create(conferencia);
    }

    public Conferencia readConferencia(String nombre) {
        return conController.findConferencia(nombre);
    }

    public ArrayList<Conferencia> readAllConferencias() {
        List<Conferencia> lista = conController.findConferenciaEntities();
        ArrayList<Conferencia> listaConferencias = new ArrayList<Conferencia>(lista);
        
        return listaConferencias;
    }

    public void editConferencia(Conferencia conferencia) throws Exception {
        conController.edit(conferencia);
    }

    public void deleteConferencia(String nombre) throws NonexistentEntityException {
        conController.destroy(nombre);
    }
    
    
    
    
    
}
