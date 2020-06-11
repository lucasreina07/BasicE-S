/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package otrosmetodosutilies;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileStore;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.PathMatcher;
import java.nio.file.Paths;

/**
 * Determina un tipo MIME de un archivo Metodo util probeContextType(Path)
 *
 * @author AlienWare
 */
public class Mime {

    public static void main(String[] args) throws IOException {
        Path filename = Paths.get("C:\\Users\\AlienWare\\Documents\\PROGRAMACION"
                + "\\JAVA TUTORIAL\\CLASES ESENCIALES DE JAVA\\BASIC E-S\\directorio\\texto.txt");
        try {
            String type = Files.probeContentType(filename);
            if (type == null) {
                System.err.format("'%s' tiene un "
                        + "tipo de archivo desconocido.$n", filename);
            } else if (!type.equals("text/plain"))  {
                System.err.format("'%s' no es" 
                        + " un archivo de texto sin formato.%n", filename);
            } 
        } catch (IOException e) {
            System.err.println(e);
        }
        
        //sistema de archivo predeterminado
        PathMatcher matcher = FileSystems.getDefault().getPathMatcher("glob *.*");
        
        //deparador de cadena de ruta
        ///String separator = File.separator;
        String separator = FileSystems.getDefault().getSeparator();
        
        //Almacenes de arvhicos del sistema de archivos
        //para recuperar un lista de todos los almacenes de archivos
        //puede usar el getFileStore metodo.
        
        for(FileStore store: FileSystems.getDefault().getFileStores()){
            System.out.println(store);
        }
        FileStore store = Files.getFileStore(filename);
        System.out.println(store);
    }
}
