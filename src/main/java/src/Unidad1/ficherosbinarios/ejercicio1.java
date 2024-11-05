
package src.Unidad1.ficherosbinarios;

import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;


public class ejercicio1 {

    
    public static void main(String[] args) {
        
        File imagen = new File("C:\\Users\\Fernando\\Desktop\\torneo_malaga.png");
        File imagenCopia = new File("C:\\Users\\Fernando\\Desktop\\torneo_malaga_copia.png");
        
        try(DataInputStream dataIn = new DataInputStream(new FileInputStream(imagen));
            DataOutputStream dataOut = new DataOutputStream(new FileOutputStream(imagenCopia))){
            
            while(true){
                byte imagenByte = dataIn.readByte();
                dataOut.writeByte(imagenByte);
            }
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ejercicio1.class.getName()).log(Level.SEVERE, null, ex);
        } catch (EOFException ex){
            System.out.println("Final del documento.");
        } catch (IOException ex) {
            Logger.getLogger(ejercicio1.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
}
