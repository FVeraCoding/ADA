package src.Unidad1.repasoExamen.ficheros;

import java.io.EOFException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ficherosAccesoAleatorio {

    public static void main(String[] args) {
        File archivo = new File("C:\\Users\\Fernando\\Desktop\\d\\prueba.txt");
        //modificarFicheros(archivo);
        modificarCincos(archivo);
    }

    public static void modificarFicheros(File archivo) {
        List<String> palabrasNoVacias = new ArrayList<>();
        try (RandomAccessFile raf = new RandomAccessFile(archivo, "rw")) {
            String linea = raf.readLine();

            while (linea != null) {
                String[] palabras = linea.split(" ");

                for (String cadena : palabras) {
                    if (!cadena.isEmpty()) {
                        palabrasNoVacias.add(cadena.toUpperCase());
                    }
                }

                linea = raf.readLine();
            }

            raf.setLength(0);
            raf.seek(0);

            for (String cadena : palabrasNoVacias) {
                raf.writeBytes(cadena);
            }

        } catch (FileNotFoundException ex) {
            Logger.getLogger(ficherosAccesoAleatorio.class.getName()).log(Level.SEVERE, null, ex);
        } catch (EOFException ex) {
            System.out.println("\nFinal del documento.");
        } catch (IOException ex) {
            Logger.getLogger(ficherosAccesoAleatorio.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void modificarCincos(File archivo) {
        try (RandomAccessFile raf = new RandomAccessFile(archivo, "rw")) {

            Scanner sc = new Scanner(System.in);
            int entero;

            System.out.println("Introduce numeros enteros en el archivo. Para terminar, introduce -1.");
            entero = sc.nextInt();

            while (entero != -1) {
                raf.writeInt(entero);
                raf.writeChar(' ');

                System.out.println("Introduce numeros enteros en el archivo. Para terminar, introduce -1.");
                entero = sc.nextInt();
            }
            
            raf.seek(0);
            
            while(true){
                
                int enteroLeido = raf.readInt();
                
                if(enteroLeido == 5){
                    raf.seek(raf.getFilePointer()-4);
                    raf.writeInt(0);
                    raf.seek(raf.getFilePointer()-4);
                    enteroLeido = raf.readInt();
                }
                
                System.out.println(enteroLeido);

                raf.readChar();
            }
            

        } catch (FileNotFoundException ex) {
            Logger.getLogger(ficherosAccesoAleatorio.class.getName()).log(Level.SEVERE, null, ex);
        } catch (EOFException ex){
            System.out.println("\n Final del documento.");
        }
        catch (IOException ex) {
            Logger.getLogger(ficherosAccesoAleatorio.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
