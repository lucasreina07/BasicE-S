/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionmetadatos;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;


/**
 *
 * @author AlienWare
 */
public class Permisos {

    public static void main(String[] args) throws IOException {
        //Permisos de archivosmPOSIX
        //POSIX es un acrónimo de Interfaz
        System.out.println("");
        System.out.println("Permisos de archivos POSIX");
        Path path = Files.createTempFile("text-file", "crear.txt");
        try {
            BasicFileAttributes attributes = Files.readAttributes(path, BasicFileAttributes.class);
            printBasicAttributes(attributes);
        } catch (Exception e) {
            
        }
    }

    private static void printBasicAttributes(BasicFileAttributes attributes) throws Exception {
        System.out.println("-- Some BasicFileAttributes --");
        System.out.printf("creationTime     = %s%n", attributes.creationTime());
        System.out.printf("lastAccessTime   = %s%n", attributes.lastAccessTime());
        System.out.printf("lastModifiedTime = %s%n", attributes.lastModifiedTime());
        System.out.printf("size             = %s%n", attributes.size());
        System.out.printf("directory        = %s%n", attributes.isDirectory());
    }
}
