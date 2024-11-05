
package src.Unidad1.RandomAccessFile;

import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Ejercicio1 {

    
    public static void main(String[] args) {
    String ruta = "C:\\Users\\Fernando\\desktop\\archivo1.txt";
    File archivo = new File(ruta);
    
    convertirArchivo(archivo);
    }
    
    
    public static void convertirArchivo(File archivo){
        try {
            RandomAccessFile random = new RandomAccessFile(archivo, "rw");
            String lineaConvertida;
            String linea = random.readLine();
                    
            while(linea != null){
                String[] lineaArray = linea.split(" ");
                lineaConvertida = "";
                
                for(int i = 0; i<lineaArray.length; i++){
                    lineaArray[i] = lineaArray[i].toUpperCase();
                    
                    lineaConvertida += lineaArray[i];
                }
                
                
                
                System.out.print(lineaConvertida);
                
                linea = random.readLine();
            }   
            
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Ejercicio1.class.getName()).log(Level.SEVERE, null, ex);
        } catch (EOFException e){
            System.out.println("Final del documento");
        }
        catch (IOException ex){
            Logger.getLogger(Ejercicio1.class.getName()).log(Level.SEVERE, null, ex);

        }
    }
    
}
