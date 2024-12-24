package Hibernate.Ej1_Repaso.Controlador;

import Hibernate.Ej1_Repaso.Modelo.Clases.Investigador;
import Hibernate.Ej1_Repaso.Modelo.Clases.Proyecto;
import Hibernate.Ej1_Repaso.Modelo.Persistencia.ControladoraPersistencia;
import Hibernate.Ej1_Repaso.Modelo.Persistencia.exceptions.NonexistentEntityException;
import java.util.ArrayList;

public class InvestigadorController {
    ControladoraPersistencia controlPersis = new ControladoraPersistencia();
    
    //Create
    public void addInvestigador(Investigador inv) throws Exception{
        controlPersis.addInvestigador(inv);
    }
    
    //READ
    public Investigador readInvestigador(String nombre){
        return controlPersis.readInvestigador(nombre);
    }
    
    public ArrayList<Investigador> readAllInvestigadores(){
        return controlPersis.readAllInvestigadores();
    }
    
    //UPDATE
    public void updateInvestigador(Investigador inv) throws Exception{
        controlPersis.updateInvestigador(inv);
    }
    
    //DELETE
    public void deleteInvestigador(String nombre) throws NonexistentEntityException{
        controlPersis.deleteInvestigador(nombre);
    }
}
