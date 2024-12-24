package Hibernate.Ej1.Vista;

import Hibernate.Ej1.Controlador.ConferenciaController;
import Hibernate.Ej1.Controlador.InvestigadorController;
import Hibernate.Ej1.Controlador.ProyectoController;
import Hibernate.Ej1.Modelo.Clases.Conferencia;
import Hibernate.Ej1.Modelo.Clases.Investigador;
import Hibernate.Ej1.Modelo.Clases.NombreCompleto;
import Hibernate.Ej1.Modelo.Clases.Proyecto;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {

    public static void main(String[] args) {
        try {
            InvestigadorController invController = new InvestigadorController();
            ProyectoController proController = new ProyectoController();
            ConferenciaController conController = new ConferenciaController();

            

        } catch (Exception ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, "Error durante la persistencia de datos: " + ex.getMessage(), ex);
        }
    }
}
