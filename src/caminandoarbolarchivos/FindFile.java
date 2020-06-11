/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package caminandoarbolarchivos;


import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.PathMatcher;
import java.nio.file.Paths;

/**
 *
 * @author AlienWare
 */
public class FindFile {
    public static void main(String[] args) {
        PathMatcher matcher = FileSystems.getDefault().getPathMatcher("glob:*.{java,class}");
        Path filename = Paths.get("C:\\Users\\AlienWare\\Documents\\PROGRAMACION"
                + "\\JAVA TUTORIAL\\CLASES ESENCIALES DE JAVA\\BASIC E-S\\RutaSimbolica");
        if(matcher.matches(filename)){
            System.out.println(filename.getFileName());   
        }
    }
}
