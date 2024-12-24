
package Hibernate.Ej1.Controlador;

import Hibernate.Ej1.Modelo.Clases.Proyecto;
import Hibernate.Ej1.Modelo.Persistencia.ControladoraPersistencia;
import Hibernate.Ej1.Modelo.Persistencia.exceptions.NonexistentEntityException;
import java.util.ArrayList;

public class ProyectoController {
    ControladoraPersistencia controlPersis = new ControladoraPersistencia();
    
    public void addProyecto(Proyecto proyecto) throws Exception{
        controlPersis.addProyecto(proyecto);
    }
    
    public Proyecto readProyecto(String nombre){
        return controlPersis.readProyecto(nombre);
    }
    
    public ArrayList<Proyecto> readAllProyectos(){
        return controlPersis.readAllProyectos();
    }
    
    public void editProyecto(Proyecto proyecto) throws Exception{
        controlPersis.editProyecto(proyecto);
    }
    
    public void deleteProyecto(String nombre) throws NonexistentEntityException{
        controlPersis.deleteProyecto(nombre);
    }
    
    
}
