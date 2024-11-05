
package src.Unidad1.repasoExamen.ficheros;

import java.io.File;
import java.io.IOException;


public class claseFile {
    
    public static void main(String[] args) throws IOException {
            File d = new File("C:\\Users\\Fernando\\Desktop\\d");
            File escritorio = new File("C:\\Users\\Fernando\\Desktop");
            
            crearEstructura(d);
            listarEstructura(d, 1);
            mostrarArchivos(escritorio, "odt");
            borrarArchivos(escritorio);
    }
    
    
    public static void crearEstructura(File d) throws IOException{
        d.mkdir();
        
        File d1 = new File(d, "d1");
        d1.mkdir();
        
        File f11 = new File(d1, "f11");
        File f22 = new File(d1, "f12");
        
        f11.createNewFile();
        f22.createNewFile();
        
        File d2 = new File(d, "d2");
        d2.mkdir();
        
        File d21 = new File(d2, "d21");
        d21.mkdir();
        
        File f21 = new File(d2, "f21");
        f21.createNewFile();
        
        File d22 = new File(d2, "d22");
        d22.mkdir();
        
        File f222 = new File(d22, "f222");
        f222.createNewFile();
        
        File d3 = new File(d, "d3");
        d3.mkdir();
        
        File d31 = new File(d3, "d31");
        d31.mkdir();        
    }
    
    public static void listarEstructura(File archivo, int contador){
        if(archivo.getName().equals("d")){
            System.out.println(archivo.getName());
        }
        String[] listaArchivos = archivo.list();
        
        for(String a : listaArchivos){
            File subdirectorio = new File(archivo, a);
            for(int i = 0; i< contador; i++){
                System.out.print("\t");
            }
            System.out.println(a);
            if(subdirectorio.isDirectory()){
                listarEstructura(subdirectorio, contador + 1);
            } 
        }
    }
    
    public static void mostrarArchivos(File archivo, String extension){
        String[] listaArchivos = archivo.list();
        
        for(String subArchivo : listaArchivos){
            if(subArchivo.endsWith(extension)){
                System.out.println(subArchivo);
            }
        }
    }
    
    public static void borrarArchivos(File archivo){
        String[] listaArchivos = archivo.list();
        
        for(String sub : listaArchivos){
            if(sub.endsWith(".txt")){
                File file = new File(archivo, sub);
                file.delete();
            }
        }
    }
    
    
}

