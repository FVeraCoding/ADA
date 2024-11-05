package src.Unidad1.repasoExamen.ficheros;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class flujoBytes {

    public static void main(String[] args) {
        File imagen = new File("C:\\Users\\Fernando\\Desktop\\imagen.png");
        File archivoNuevo = new File("C:\\Users\\Fernando\\Desktop\\numeros");

        //copiarImagen(imagen);
        crearArchivo(archivoNuevo);
        leerArchivo(archivoNuevo);
    }

    public static void copiarImagen(File archivo) {
        try {
            File copia = new File(archivo.getParent(), archivo.getName() + "_copia" + archivo.getName().substring(archivo.getName().length() - 4));
            DataInputStream lector = new DataInputStream(new FileInputStream(archivo));
            DataOutputStream escritor = new DataOutputStream(new FileOutputStream(copia));

            for (int i = 0; i < archivo.length(); i++) {
                byte b = lector.readByte();
                escritor.writeByte(b);
            }

            escritor.flush();
            escritor.close();
            lector.close();

        } catch (FileNotFoundException ex) {
            Logger.getLogger(flujoBytes.class.getName()).log(Level.SEVERE, null, ex);
        } catch (EOFException ex) {
            System.out.println("Se ha terminado de copiar la imagen.");
        } catch (IOException ex) {
            Logger.getLogger(flujoBytes.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void crearArchivo(File archivo) {

        try (DataOutputStream escritor = new DataOutputStream(new FileOutputStream(archivo))) {
            Scanner sc = new Scanner(System.in);
            String cadena = "a";
            int numero;
            int contador = 0;

            while (!cadena.isEmpty() || contador % 2 != 0) {
                System.out.println("Introduzca un numero entero. Cuando termine, no introduzca ninguno y pulsa INTRO.");
                cadena = sc.nextLine();

                if (!cadena.isEmpty()) {
                    
                    // Si solo mete un número 
                    if (!cadena.contains(" ")) {
                        numero = Integer.parseInt(cadena);
                        escritor.writeInt(numero);
                        contador++;

                    } else { // Si mete dos números
                        String[] numeros = cadena.split(" ");
                        int numero1 = Integer.parseInt(numeros[0]);
                        int numero2 = Integer.parseInt(numeros[1]);

                        escritor.writeInt(numero1);
                        contador++;
                        
                        //Valoro si el número introducido (el primero de la pareja) se encuentra en primera o segunda posicion.
                        //Si contador es par está en segunda posicion e imprimo salto de linea.
                        //Si contador es impar es el primeo e imprimo espacio.
                        if (contador % 2 == 0) {
                            escritor.writeChars("\n");
                        } else {
                            escritor.writeChars(" ");
                        }
                        
                        //Escribo el segundo número.
                        escritor.writeInt(numero2);
                        contador++;
                    }

                    //Hago la comprobación de antes tanto del número que se ha introducido manualmente (en el if) como del segundo número
                    //de la pareja.
                    if (contador % 2 == 0) {
                        escritor.writeChars("\n");
                    } else {
                        escritor.writeChars(" ");
                    }

                }

            }

        } catch (FileNotFoundException ex) {
            Logger.getLogger(flujoBytes.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(flujoBytes.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void leerArchivo(File archivo) {
        
        int contador = 1;
            double mediaPrimeraColumna = 0;
            double mediaSegundaColumna = 0;
            
            List<Integer> numerosPrimeraColumna = new ArrayList<>();
            List<Integer> numerosSegundaColumna = new ArrayList<>();
        
        try (DataInputStream lector = new DataInputStream(new FileInputStream(archivo))) {

            while (true) {

                if (contador % 2 != 0) {
                    int primeraColumna = lector.readInt();
                    numerosPrimeraColumna.add(primeraColumna);
                    System.out.print(primeraColumna);
                    System.out.print(lector.readChar());
                    mediaPrimeraColumna+=primeraColumna;
                    contador++;
                }else{
                    int segundaColumna = lector.readInt();
                    numerosSegundaColumna.add(segundaColumna);
                    System.out.print(segundaColumna);
                    System.out.print(lector.readChar());
                    contador++;
                    mediaSegundaColumna+=segundaColumna;
                }
            }

        } catch (FileNotFoundException ex) {
            Logger.getLogger(flujoBytes.class.getName()).log(Level.SEVERE, null, ex);
        } catch (EOFException ex) {
            System.out.println("Media de la primera columna: "+mediaPrimeraColumna/numerosPrimeraColumna.size());
            System.out.println("Media de la segunda columna: "+mediaSegundaColumna/numerosSegundaColumna.size());
        } catch (IOException ex) {
            Logger.getLogger(flujoBytes.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
