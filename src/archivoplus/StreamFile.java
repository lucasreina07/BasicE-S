/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package archivoplus;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import static java.nio.file.StandardOpenOption.APPEND;
import static java.nio.file.StandardOpenOption.CREATE;

/**
 *
 * @author AlienWare
 */
public class StreamFile {
    public static void main(String[] args) {
        //-----------------------------
        
        //Craer y escribir un archivo Stram I/O
        
        //Convierte una cadena en un metodo de bytes
        String s = "Hola Mundo";
        byte[] data = s.getBytes();
        Path p = Paths.get("./logfile.txt");
        
        try(OutputStream out = new BufferedOutputStream(Files.newOutputStream(p, CREATE,APPEND))){
            out.write(data, 0, data.length);
        }catch(IOException x){
            System.out.println("" + x);
        }
    }
}
