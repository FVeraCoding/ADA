package Hibernate.Ej1_Repaso.Main;

import Hibernate.Ej1_Repaso.Controlador.ConferenciaController;
import Hibernate.Ej1_Repaso.Controlador.InvestigadorController;
import Hibernate.Ej1_Repaso.Controlador.ProyectoController;
import Hibernate.Ej1_Repaso.Modelo.Clases.Conferencia;
import Hibernate.Ej1_Repaso.Modelo.Clases.Investigador;
import Hibernate.Ej1_Repaso.Modelo.Clases.NombreCompleto;
import Hibernate.Ej1_Repaso.Modelo.Clases.Proyecto;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Aplicacion {

    public static void main(String[] args) {
        try {
            ProyectoController proController = new ProyectoController();
            ConferenciaController confeController = new ConferenciaController();
            InvestigadorController invController = new InvestigadorController();

            //Proyectos
            List<Investigador> listaInvestigadoresProyecto1 = new ArrayList<Investigador>();
            Proyecto proyecto1 = new Proyecto("Proyecto1", LocalDate.of(2020, Month.MAY, 05), listaInvestigadoresProyecto1);
            proController.addProyecto(proyecto1);

            List<Investigador> listaInvestigadoresProyecto2 = new ArrayList<Investigador>();
            Proyecto proyecto2 = new Proyecto("Proyecto2", LocalDate.of(2020, Month.JUNE, 12), listaInvestigadoresProyecto2);

            List<Investigador> listaInvestigadoresProyecto3 = new ArrayList<Investigador>();
            Proyecto proyecto3 = new Proyecto("Proyecto3", LocalDate.of(2020, Month.AUGUST, 15), listaInvestigadoresProyecto3);

            List<Investigador> listaInvestigadoresProyecto4 = new ArrayList<Investigador>();
            Proyecto proyecto4 = new Proyecto("Proyecto4", LocalDate.of(2020, Month.NOVEMBER, 1), listaInvestigadoresProyecto4);

            List<Investigador> listaInvestigadoresProyecto5 = new ArrayList<Investigador>();
            Proyecto proyecto5 = new Proyecto("Proyecto5", LocalDate.of(2020, Month.DECEMBER, 12), listaInvestigadoresProyecto5);

            //Conferencias
            List<Investigador> listaInvestigadoresConferencia1 = new ArrayList<Investigador>();
            List<Investigador> listaInvestigadoresConferencia2 = new ArrayList<Investigador>();
            List<Investigador> listaInvestigadoresConferencia3 = new ArrayList<Investigador>();
            List<Investigador> listaInvestigadoresConferencia4 = new ArrayList<Investigador>();

            Conferencia conferencia1 = new Conferencia("Conferencia1", LocalDate.of(2020, Month.NOVEMBER, 02), "San Fernando", 2.5, listaInvestigadoresConferencia1);
            Conferencia conferencia2 = new Conferencia("Conferencia2", LocalDate.of(2021, Month.JANUARY, 12), "Sevilla", 4, listaInvestigadoresConferencia2);
            confeController.addConferencia(conferencia2);
            Conferencia conferencia3 = new Conferencia("Conferencia3", LocalDate.of(2021, Month.JULY, 01), "San Fernando", 1.5, listaInvestigadoresConferencia3);
            Conferencia conferencia4 = new Conferencia("Conferencia4", LocalDate.of(2021, Month.NOVEMBER, 02), "Berlín", 3, listaInvestigadoresConferencia4);

            //Investigadores
            List<Conferencia> listaConferenciasInvestigador1 = new ArrayList<Conferencia>();
            NombreCompleto nombreInvestigador1 = new NombreCompleto("Juan", "Pérez Martínez");
            Investigador investigador1 = new Investigador("3048752M", nombreInvestigador1, "C./ Desengaño 21", 623423523, "Cádiz", proyecto1, listaConferenciasInvestigador1);
            invController.addInvestigador(investigador1);

            investigador1.addProyecto(proyecto1);
            investigador1.addConferencia(conferencia2);

            proController.addProyecto(proyecto1);
            confeController.addConferencia(conferencia2);
            invController.addInvestigador(investigador1);

        } catch (Exception ex) {
            Logger.getLogger(Aplicacion.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
