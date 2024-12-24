
package Hibernate.Ej1_Repaso.Modelo.Persistencia;

import Hibernate.Ej1_Repaso.Modelo.Clases.Conferencia;
import Hibernate.Ej1_Repaso.Modelo.Clases.Investigador;
import Hibernate.Ej1_Repaso.Modelo.Clases.Proyecto;
import Hibernate.Ej1_Repaso.Modelo.Persistencia.exceptions.NonexistentEntityException;
import java.util.ArrayList;
import java.util.List;


public class ControladoraPersistencia {
    ConferenciaJpaController confeController = new ConferenciaJpaController();
    InvestigadorJpaController invController = new InvestigadorJpaController();
    ProyectoJpaController proController = new ProyectoJpaController();
    

    //PROYECTO

    public void addProyecto(Proyecto proyecto) throws Exception {
        proController.create(proyecto);
    }

    public Proyecto readProyecto(String nombre) {
        return proController.findProyecto(nombre);
    }

    public ArrayList<Proyecto> readAllProyectos() {
        List<Proyecto> lista = proController.findProyectoEntities();
        ArrayList<Proyecto> listaProyectos = new ArrayList<Proyecto>(lista);
        return listaProyectos;
    }

    public void updateProyecto(Proyecto proyecto) throws Exception {
        proController.edit(proyecto);
    }

    public void deleteProyecto(String nombre) throws NonexistentEntityException {
        proController.destroy(nombre);
    }
    
    //CONFERENCIA

    public void addConferencia(Conferencia conferencia) throws Exception {
        confeController.create(conferencia);
    }

    public Conferencia readConferencia(String nombre) {
        return confeController.findConferencia(nombre);
    }

    public ArrayList<Conferencia> readAllConferencia() {
        List<Conferencia> lista = confeController.findConferenciaEntities();
        ArrayList<Conferencia> listaConferencias = new ArrayList<Conferencia>(lista);
        return listaConferencias;
    }

    public void updateConferencia(Conferencia conf) throws Exception {
        confeController.edit(conf);
    }

    public void deleteConferencia(String nombre) throws NonexistentEntityException {
        confeController.destroy(nombre);
    }

    //Investigador
    
    public void addInvestigador(Investigador inv) throws Exception {
        invController.create(inv);
    }

    public Investigador readInvestigador(String nombre) {
        return invController.findInvestigador(nombre);
    }

    public ArrayList<Investigador> readAllInvestigadores() {
        List<Investigador> lista = invController.findInvestigadorEntities();
        ArrayList<Investigador> listaInvestigador = new ArrayList<Investigador>(lista);
        return listaInvestigador;
    }

    public void updateInvestigador(Investigador inv) throws Exception {
        invController.edit(inv);
    }

    public void deleteInvestigador(String nombre) throws NonexistentEntityException {
        invController.destroy(nombre);
    }
    
    
    
    
}
