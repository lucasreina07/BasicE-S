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
public class DetectarSimbolicos {
    public static void main(String[] args) {
        
        //------------
        //Detectar un enlace simbolico
        Path ruta = Paths.get("C:\\Users\\AlienWare\\Documents\\PROGRAMACION"
                + "\\JAVA TUTORIAL\\CLASES ESENCIALES DE JAVA\\BASIC E-S\\RutaSimbolica");
        
        boolean isSymblicLink = Files.isSymbolicLink(ruta);
        if(isSymblicLink == true){
            System.out.println("La ruta es simbolica");
        }else {
            System.out.println("NO es una ruta simbolica");
        }
        
        //------------------
        //Encontrar el objeto de un enlace 
        
        System.out.println("Encontrar el objeto de un enlace");
        try {
            System.out.format("destino del enlace '%s' es '%s'%n", ruta, Files.readSymbolicLink(ruta));
        } catch (IOException e) {
            System.err.println(e);
        }
    }
}
