package src.Unidad1.repasoExamen.ficheros;

import java.io.BufferedReader;
import java.io.EOFException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class flujosCaracteres {

    public static void main(String[] args) {
        File archivo = new File("C:\\Users\\Fernando\\Desktop\\d\\hola.txt");

        escribir(archivo, "Hola Mundo\nEsta           es la segunda linea de prueba.\nY esta es     la tercera");
        leer(archivo);
        contarPalabras(archivo);
        copiarArchivo(archivo);
    }

    public static void escribir(File archivo, String mensaje) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(archivo))) {
            pw.println(mensaje);
        } catch (IOException ex) {
            Logger.getLogger(flujosCaracteres.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void leer(File archivo) {
        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea = br.readLine();

            while (linea != null) {
                System.out.println(linea);
                linea = br.readLine();
            }

        } catch (EOFException ex) {
            System.out.println("Final del documento.");
        } catch (IOException ex) {
            Logger.getLogger(flujosCaracteres.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void contarPalabras(File archivo){
        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea = br.readLine();
            int contadorPalabras = 0;
            
            while(linea != null){
                String[] palabrasLinea = linea.split(" ");
                
                for(String palabra : palabrasLinea){
                    if(!palabra.isEmpty()){
                        contadorPalabras++;
                    }
                }
                linea = br.readLine();
            }
            
            System.out.println(contadorPalabras);
            
        } catch (EOFException ex) {
            System.out.println("Final del documento.");
        } catch (IOException ex) {
            Logger.getLogger(flujosCaracteres.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void copiarArchivo(File archivo){
        
        try{
            File segundoArchivo = new File(archivo.getParent(), archivo.getName()+"_copia"+archivo.getName().substring(archivo.getName().length()-4));
            BufferedReader br = new BufferedReader(new FileReader(archivo));
            PrintWriter pw = new PrintWriter(new FileWriter(segundoArchivo));
            
            String linea = br.readLine();
            
            while(linea != null){
                String[] palabras = linea.split(" ");
                List<String> lista = new ArrayList();
                String cadena = "";
                
                for(String palabra : palabras){
                    if(!palabra.isEmpty()){
                        lista.add(palabra);
                    }
                }
                
                for(String palabra : lista){
                    palabra = palabra.toUpperCase();
                    cadena+=palabra;
                }
                                
                pw.println(cadena);
                linea = br.readLine();
            }
            
            pw.flush();
            pw.close();
            br.close();
            
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(flujosCaracteres.class.getName()).log(Level.SEVERE, null, ex);
        } catch (EOFException ex){
            System.out.println("Final del documento");
        }
        catch (IOException ex) {
            Logger.getLogger(flujosCaracteres.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
}
