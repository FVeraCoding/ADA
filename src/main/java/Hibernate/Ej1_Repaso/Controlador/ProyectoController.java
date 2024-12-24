
package Hibernate.Ej1_Repaso.Controlador;

import Hibernate.Ej1_Repaso.Modelo.Clases.Proyecto;
import Hibernate.Ej1_Repaso.Modelo.Persistencia.ControladoraPersistencia;
import Hibernate.Ej1_Repaso.Modelo.Persistencia.exceptions.NonexistentEntityException;
import java.util.ArrayList;




public class ProyectoController {
    ControladoraPersistencia controlPersis = new ControladoraPersistencia();
    
    //Create
    public void addProyecto(Proyecto proyecto) throws Exception{
        controlPersis.addProyecto(proyecto);
    }
    
    //READ
    public Proyecto readProyecto(String nombre){
        return controlPersis.readProyecto(nombre);
    }
    
    public ArrayList<Proyecto> readAllProyectos(){
        return controlPersis.readAllProyectos();
    }
    
    //UPDATE
    public void updateProyecto(Proyecto proyecto) throws Exception{
        controlPersis.updateProyecto(proyecto);
    }
    
    //DELETE
    public void deleteProyecto(String nombre) throws NonexistentEntityException{
        controlPersis.deleteProyecto(nombre);
    }
}
