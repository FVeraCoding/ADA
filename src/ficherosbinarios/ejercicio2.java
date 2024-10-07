package ficherosbinarios;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ejercicio2 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        File archivo = new File("C:\\Users\\Fernando\\Desktop\\archivo.dat");

        int num = -99;
        String input = " ";


        try {
            archivo.createNewFile();
        } catch (IOException ex) {
            Logger.getLogger(ejercicio2.class.getName()).log(Level.SEVERE, null, ex);
        }

        try (DataInputStream dataIn = new DataInputStream(new FileInputStream(archivo)); DataOutputStream dataOut = new DataOutputStream(new FileOutputStream(archivo))) {
            int contador = 0;
            System.out.print("Introduce uno o dos numeros: ");
            input = sc.nextLine();

            while (!input.isEmpty()) {

                num = Integer.parseInt(input);

                while (num < 0 || num > 99) {
                    System.out.println("El numero introducido debe estar entre 0 y 99");
                    input = sc.nextLine();

                    num = Integer.parseInt(input);
                }

                dataOut.writeInt(num);
                dataOut.writeChar(' ');

                System.out.print("Introduce uno o dos numeros: ");
                input = sc.nextLine();
            }
            
            
            while(true){
                
                System.out.print(dataIn.readInt());
                System.out.print(dataIn.readChar());
                contador++;
                if(contador%2 == 0){
                    System.out.print("\n");
                }
            }

            

        } catch (FileNotFoundException ex) {
            Logger.getLogger(ejercicio2.class.getName()).log(Level.SEVERE, null, ex);
        } catch (EOFException ex) {
            System.out.println();
        } catch (IOException ex) {
            Logger.getLogger(ejercicio2.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
