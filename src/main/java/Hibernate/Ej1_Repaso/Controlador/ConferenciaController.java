
package Hibernate.Ej1_Repaso.Controlador;

import Hibernate.Ej1_Repaso.Modelo.Clases.Conferencia;
import Hibernate.Ej1_Repaso.Modelo.Persistencia.ControladoraPersistencia;
import Hibernate.Ej1_Repaso.Modelo.Persistencia.exceptions.NonexistentEntityException;
import java.util.ArrayList;


public class ConferenciaController {
    ControladoraPersistencia controlPersis = new ControladoraPersistencia();
    
    //Create
    public void addConferencia(Conferencia conferencia) throws Exception{
        controlPersis.addConferencia(conferencia);
    }
    
    //READ
    public Conferencia readConferencia(String nombre){
        return controlPersis.readConferencia(nombre);
    }
    
    public ArrayList<Conferencia> readAllConferencias(){
        return controlPersis.readAllConferencia();
    }
    
    //UPDATE
    public void updateConferencia(Conferencia conf) throws Exception{
        controlPersis.updateConferencia(conf);
    }
    
    //DELETE
    public void deleteConferencia(String nombre) throws NonexistentEntityException{
        controlPersis.deleteConferencia(nombre);
    }
}
