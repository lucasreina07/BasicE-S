/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crearleerdirectorios;

import java.io.IOException;
import java.nio.file.DirectoryIteratorException;
import java.nio.file.DirectoryStream;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import static java.nio.file.Files.newDirectoryStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.DirectoryStream.Filter;
import static java.nio.file.Files.newDirectoryStream;
import static java.nio.file.Files.newDirectoryStream;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileAttribute;
import java.util.Iterator;

/**
 *
 * @author AlienWare
 */
public class Directorio{

    public static void main(String[] args) throws  NoSuchFieldException {

        //Listado de ditectorios raiz de un sistema de arvhivos 
        /*
        System.out.println("Listado de directorios raiz");
        Iterable<Path> dirs = FileSystems.getDefault().getRootDirectories();
        for (Path name: dirs){
            System.err.println(name);
        }
         */
        //---------------------------
        //Crear un directorio raiz
        //si no se especifica el file atribute tendra atributos predeterminados
        System.out.println();
        System.out.println("Crear un directorio o carpeta");
        Path dir = Paths.get("C:\\Users\\AlienWare\\Documents\\PROGRAMACION"
                + "\\JAVA TUTORIAL\\CLASES ESENCIALES DE JAVA\\BASIC E-S\\directorio");
        try {
            Files.createDirectory(dir);
        } catch (FileAlreadyExistsException e) {
            System.err.println("Direccion ya existe " + e);
        } catch (ClassCastException | IOException e) {

        }

        //---------------------------
        //Crear directorios temporales
        /*
        System.out.println("Directorios temporales");
        try{
        Path dirTemp = Paths.get("C:\\Users\\AlienWare\\Documents\\PROGRAMACION"
                + "\\JAVA TUTORIAL\\CLASES ESENCIALES DE JAVA\\BASIC E-S\\directorioTemp"); 
        Path dirPre = Paths.get("C:\\Users\\AlienWare\\Documents\\PROGRAMACION"
                + "\\JAVA TUTORIAL\\CLASES ESENCIALES DE JAVA\\BASIC E-S\\directorioTemp2");
        Files.createTempDirectory(dirTemp,"temporal", (FileAttribute<Path>)dirTemp);
        }catch(FileAlreadyExistsException e){
            System.err.println("Direccion  Temporal ya existe " + e);
        }catch(ClassCastException e){
            
        }
         */
        //-----------------------------
        //Imprimir directorios
        //este enfoque escala muy bien a directorios muy grandes
        System.out.println();
        System.out.println("Listado del contenido de un directrio");
        try (DirectoryStream<Path> stream = Files.newDirectoryStream(dir)) {
            for (Path file : stream) {
                System.out.println(file.getFileName());
            }
        } catch (IOException | DirectoryIteratorException x) {
            //La iteracion nunca puede generar IOExeprion
            //En este fragmento, solo ouede ser lanzado por new DirectoryStram
            System.err.println("Error al leer directorio " + x);
        }

        //-----------------------------
        //Filtrar unlistado de Directorios utilizando globbing
        System.out.println();
        System.out.println("Listado de dir con globbing");

        try (DirectoryStream<Path> streamGlobbing = Files.newDirectoryStream(dir,
                "*.{txt,class,jar}")) {
            for (Path entry : streamGlobbing) {
                System.out.println(entry.getFileName());
            }
        } catch (IOException x) {
            System.err.println("Error de globbing " + x);
        }

        //-----------------------------
        //Escribir su propio filtro de directorio
        System.out.println();
        System.out.println("Escribir su propio filtro de directorio");
        
        Path dirPropio = Paths.get("C:\\Users\\AlienWare\\Documents\\PROGRAMACION"
                + "\\JAVA TUTORIAL\\CLASES ESENCIALES DE JAVA\\BASIC E-S\\tutorial");
        //este fragmentoo de codigo solo recupera directorios
        DirectoryStream.Filter<Path> filter=
        new DirectoryStream.Filter<Path>(){
            @Override
            public boolean accept(Path file) throws IOException {
                file = Paths.get("C:\\Users\\AlienWare\\Documents\\PROGRAMACION"
                + "\\JAVA TUTORIAL\\CLASES ESENCIALES DE JAVA\\BASIC E-S\\directorio");
                return (Files.isDirectory(file));
            }
            
        };
        
        //pasmos a invocar el filtro de directorio
        try(DirectoryStream <Path> stream = Files.newDirectoryStream(dirPropio,filter)){
            for (Path entry : stream) {
                System.out.println(entry.getFileName());
            }
        }catch (IOException e){
            System.err.println(e);
        }
    }   
}