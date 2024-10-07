/*
Escribe un programa que guarde en un fichero tantos enteros como el usuario
quiera, una vez el usuario termine se deberá cambiar todos los enteros con
valor 5 por el valor 0.
 */
package RandomAccessFile;

import java.io.EOFException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Ejercicio2 {

    public static void main(String[] args) {
        String ruta = "C:\\Users\\Fernando\\desktop\\archivo2.txt";
        File fichero = new File(ruta);

        convertirInts(fichero);
    }

    public static void convertirInts(File archivo) {
        try {
            Scanner sc = new Scanner(System.in);
            String numScanner;
            int num;

            RandomAccessFile random = new RandomAccessFile(archivo, "rw");
            random.setLength(0);

            System.out.println("Introduzca un número entero en el fichero. Cuando termine, pulse INTRO sin introducir ningún número.");
            numScanner = sc.nextLine();

            while (!numScanner.equals("")) {
                num = Integer.valueOf(numScanner);
                random.writeInt(num);
                System.out.println("Introduzca un número entero en el fichero. Cuando termine, pulse INTRO sin introducir ningún número.");
                numScanner = sc.nextLine();
            }
            
            random.seek(0);
            
            while(true){
                int numero = random.readInt();
                if(numero!=5){
                    System.out.println(numero);
                }else{
                    System.out.println(0);
                }
            }

        } catch (FileNotFoundException ex) {
            Logger.getLogger(Ejercicio2.class.getName()).log(Level.SEVERE, null, ex);
        } catch (EOFException ex) {
            System.out.println("Se ha llegado al final del documento.");
        } catch (IOException ex) {
            Logger.getLogger(Ejercicio2.class.getName()).log(Level.SEVERE, null, ex);

        }

    }
}
