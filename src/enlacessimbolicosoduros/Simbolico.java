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
public class Simbolico{
    public static void main(String[] args){
        
        //Crear enlaces sinbolicos 
        Path newlink = Paths.get("C:\\Users\\AlienWare\\Documents\\PROGRAMACION"
                + "\\JAVA TUTORIAL\\CLASES ESENCIALES DE JAVA\\BASIC E-S\\RutaSimbolica");
        Path target = Paths.get("C:\\Users\\AlienWare\\Documents\\PROGRAMACION"
                + "\\JAVA TUTORIAL\\CLASES ESENCIALES DE JAVA\\BASIC E-S\\directorio\\texto.txt");
        try {
            Files.createSymbolicLink(newlink, target);
        } catch (IOException | UnsupportedOperationException e) {
            System.err.println("Link Creado " + e);
        } 
        //algunos sistemas de archivos no admiten enlaces simbolicos
        
    }

 }
