package src.Unidad1.ficherosbinarios;

import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ejercicio3 {

    public static void main(String[] args) {
        File archivo = new File("C:\\Users\\Fernando\\Desktop\\archivo.dat");
        int sumatorioPrimeraColumna = 0;
        int sumatorioSegundaColumna = 0;
        int contadorPrimeraColumna = 0;
        int contadorSegundaColumna = 0;
        
        try(DataInputStream dataInput = new DataInputStream(new FileInputStream(archivo))){
            int contador = 0;
            boolean bandera = true;
            while(true){
                
                sumatorioPrimeraColumna += dataInput.readInt();
                contadorPrimeraColumna++;
                dataInput.readChar();
                sumatorioSegundaColumna += dataInput.readInt();
                contadorSegundaColumna++;
                
                System.out.println("La media aritmetica de la primera columna es: "+sumatorioPrimeraColumna/contadorPrimeraColumna);
                
                
                
                
            }
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ejercicio3.class.getName()).log(Level.SEVERE, null, ex);
        } catch(EOFException ex){
            
        } 
        catch (IOException ex) {
            Logger.getLogger(ejercicio3.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
   
}
