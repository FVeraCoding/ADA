
package Hibernate.Ej1.Controlador;

import Hibernate.Ej1.Modelo.Clases.Conferencia;
import Hibernate.Ej1.Modelo.Persistencia.ControladoraPersistencia;
import Hibernate.Ej1.Modelo.Persistencia.exceptions.NonexistentEntityException;
import java.util.ArrayList;


public class ConferenciaController {
    
    ControladoraPersistencia controlPersis = new ControladoraPersistencia();
    
    public void addConferencia(Conferencia conferencia) throws Exception{
        controlPersis.addConferencia(conferencia);
    }
    
    public Conferencia readConferencia(String nombre){
        return controlPersis.readConferencia(nombre);
    }
    
    public ArrayList<Conferencia> readAllConferencias(){
        return controlPersis.readAllConferencias();
    }
    
    public void editConferencia(Conferencia conferencia) throws Exception{
        controlPersis.editConferencia(conferencia);
    }
    
    public void deleteConferencia(String nombre) throws NonexistentEntityException{
        controlPersis.deleteConferencia(nombre);
    }
    
}
