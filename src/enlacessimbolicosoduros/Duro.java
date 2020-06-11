/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package enlacessimbolicosoduros;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 *
 * @author AlienWare
 */
public class Duro {
    public static void main(String[] args) {
        Path newlink = Paths.get("C:\\Users\\AlienWare\\Documents\\PROGRAMACION"
                + "\\JAVA TUTORIAL\\CLASES ESENCIALES DE JAVA\\BASIC E-S\\Rutanueva2");
        Path existingFile = Paths.get("C:\\Users\\AlienWare\\Documents\\PROGRAMACION"
                + "\\JAVA TUTORIAL\\CLASES ESENCIALES DE JAVA\\BASIC E-S\\directorio\\texto.txt");
        try {
            Files.createLink(newlink, existingFile);
        } catch (IOException e) {
            System.err.println("Existente " + e);
        } catch (UnsupportedOperationException e){
            // Algunos sistemas de archivos no 
            // admiten agregar un 
            // archivo existente a un directorio. 
            System.err.println("Ruta existente: " + e);
        }
    }
}
