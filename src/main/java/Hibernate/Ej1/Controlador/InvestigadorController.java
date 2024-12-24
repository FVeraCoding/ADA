
package Hibernate.Ej1.Controlador;

import Hibernate.Ej1.Modelo.Clases.Investigador;
import Hibernate.Ej1.Modelo.Clases.Proyecto;
import Hibernate.Ej1.Modelo.Persistencia.ControladoraPersistencia;
import Hibernate.Ej1.Modelo.Persistencia.exceptions.NonexistentEntityException;
import java.util.ArrayList;


public class InvestigadorController {
    ControladoraPersistencia controlPersis = new ControladoraPersistencia();
    
    public void addInvestigador(Investigador investigador) throws Exception{
        controlPersis.addInvestigador(investigador);
    }
    
    public Investigador readInvestigador(String dni){
        return controlPersis.readInvestigador(dni);
    }
    
    public ArrayList<Investigador> readAllInvestigadores(){
        return controlPersis.readAllInvestigadores();
    }
    
    public void editInvestigador(Investigador investigador) throws Exception{
        controlPersis.editInvestigador(investigador);
    }
    
    public void deleteInvestigador(String dni) throws NonexistentEntityException{
        controlPersis.deleteInvestigador(dni);
    }
    
    
}
