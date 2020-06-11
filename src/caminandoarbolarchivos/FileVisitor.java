/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package caminandoarbolarchivos;

import java.io.IOException;
import java.nio.file.FileVisitOption;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.EnumSet;

/**
 *  FileVisitor: visita todos los archivos de un arbol de archivos
 *  Podenis eliminar - ver archivos no accedidos en meses.
 * @author AlienWare
 */
public class FileVisitor extends SimpleFileVisitor<Path>{
    
    //-------------------------------------------
    //IMPLEMENTAMOS FILEVISITOR
    //inprime informacion sobre cada tipo de archivos
    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attr){
        if(attr.isSymbolicLink()){
            System.out.format("Symbolic link: %s", file);
        } else if (attr.isRegularFile()){
            System.out.format("Regular file: ", file);
        } else {
            System.out.format("Otros: %s" , file);
        }
        System.out.println("(" + attr.size() + "butes)");
        return FileVisitResult.CONTINUE;
    }
    
    //Impreime cada directorio visitado
    @Override
    public FileVisitResult postVisitDirectory(Path dir, IOException exc){
        System.out.format("Directorio: %S%n", dir);
        return FileVisitResult.CONTINUE;
    }
    
    //si hay algun error al acceder al archivo, hagale saber al usuario.
    //SI no anula este metodo y se produce un error, una IOExeption
    //es aventado.
    @Override
    public FileVisitResult visitFileFailed(Path dir, IOException exc){
        System.err.println();
        return FileVisitResult.CONTINUE;
    }
    //FIN DE IMPLEMENTACION DE FILEVISITOR
    //-----------------------
  
    //------------------------
    //Recorrido de FileVisitor
    
    //una vez implementamos el filevisitor
    //pasamos a recorrerlo con el metodo walkFileTree
    public static void walkingFile(Path file, FileVisitResult fileVisitor){
        
    }
    
    public static void main(String[] args) throws IOException {
        Path file = Paths.get("C:\\Users\\AlienWare\\Documents\\PROGRAMACION"
                + "\\JAVA TUTORIAL\\CLASES ESENCIALES DE JAVA\\BASIC E-S\\RutaSimbolica");
        BasicFileAttributes attr = Files.readAttributes(file, BasicFileAttributes.class);
        String mensaje = "Error de directorio";
        IOException exc = new IOException(mensaje);
        
        FileVisitor visitor = new FileVisitor();
        
        visitor.visitFile(file, attr);
        visitor.postVisitDirectory(file, exc);
        visitor.visitFileFailed(file, exc);
        
        //Recorrido de FileVisitor
    
        //una vez implementamos el filevisitor
        //pasamos a recorrerlo con el metodo walkFileTree
        Files.walkFileTree(file, visitor);
        
        EnumSet<FileVisitOption> opts = EnumSet.of(FileVisitOption.FOLLOW_LINKS);
        Files.walkFileTree(file, opts, Integer.MAX_VALUE, visitor);
    }
        
}
