/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionmetadatos;


import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.nio.file.FileStore;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.DosFileAttributes;
import java.nio.file.attribute.FileTime;
import java.nio.file.attribute.GroupPrincipal;
import java.nio.file.attribute.PosixFileAttributeView;
import java.nio.file.attribute.UserDefinedFileAttributeView;
import java.nio.file.attribute.UserPrincipal;

/**
 *
 * @author AlienWare
 */
public class AtributosArchivos {

    public static void main(String[] args) throws IOException {

        //----------------------------------------------------------------
        //Atributos basicos de archivos.
        Path file = Paths.get("C:\\Users\\AlienWare\\Pictures\\kali.png");

        try {
            System.out.println("Atributos basicos de archivos");
            BasicFileAttributes attr;
            attr = Files.readAttributes(file, BasicFileAttributes.class);
            System.out.println("creationTime: " + attr.creationTime());
            System.out.println("lastAccessTime: " + attr.lastAccessTime());
            System.out.println("lastModifiedTime: " + attr.lastModifiedTime());

            System.out.println("isDirectory: " + attr.isDirectory());
            System.out.println("isOther: " + attr.isOther());
            System.out.println("isRegularFile: " + attr.isRegularFile());
            System.out.println("isSymbolicLink: " + attr.isSymbolicLink());
            System.out.println("size: " + attr.size());
            //metodo fileKey devuelve un objeto que identifica de forma
            //exclusiva el archivo o null si no hay clave de archivps disponibles
            System.out.println("fileKey: " + attr.fileKey());

            //-------------------------------------
            //Establecer sello de tiempo
            //esta opcion estalbece la ultima hora en milisegundos
            System.out.println("");
            System.out.println("Establecer sello de tiempo/Hora del atributo en milisegundos");
            long currentTime = System.currentTimeMillis();
            FileTime ft = FileTime.fromMillis(currentTime);
            Files.setLastModifiedTime(file, ft);
            System.out.println("File Milisegundos: " + ft);
        } catch (IOException e) {
            System.err.println("Errir en la lectura de atributo" + e.getMessage());
        }

        //------------------------------------------------------------------
        //Atributos de arvhivos DOS
        System.out.println("");
        System.out.println("Atributos de archivos DOS");
        try {
            DosFileAttributes attrDOS = Files.readAttributes(file, DosFileAttributes.class);
            System.out.println("isReadOnly es" + attrDOS.isReadOnly());
            System.out.println("isHidden es" + attrDOS.isHidden());
            System.out.println("isArchive es" + attrDOS.isArchive());
            System.out.println("isSystem es" + attrDOS.isSystem());

        } catch (UnsupportedOperationException e) {
            System.err.println("Error en el archivo DOS" + e.getMessage());
        } catch (IOException e) {

        }

        //------------------------------------------------------------------
        //Establecer un propietario de archivos o grupo
        
        //este objeto es parabuscar usuarios y grupo principales de nombres
        //userPrincipal representa un identidad que puede usarse para determinar los
        //derechis de accseso a los objetos en un sistema de arvhivos.
        
        //para hacer funcionar este codigo deberemos crear un usuario y buscarlo en una db
        
        
        /*
        System.out.println("");
        System.out.println("Establecer un propietario de archivos o grupo");
        //codigo que confugura la pripiedad del archivo mediante setOwner
        
        UserPrincipal owner = userFile.getFileSystem().getUserPrincipalLookupService().lookupPrincipalByName("lucas");
        Files.setOwner(userFile, owner);
        UserPrincipal userPrincipal = Files.getOwner(userFile);
        
        //
        GroupPrincipal group = userFile.getFileSystem().getUserPrincipalLookupService().lookupPrincipalByGroupName("green");
        Files.getFileAttributeView(userFile, PosixFileAttributeView.class).setGroup(group);
        System.out.println("grupo de propietario de archivos " + userPrincipal.getName());

        */
        
        //------------------------------------------------------------------
        //Atributos de arvhico definido por el usuario
        
        //para usar este codigo ay que crear un archivo con usuarios y bd
        /*
        System.out.println("");
        System.out.println("Atributo de arvhico definido por el usuario");
        UserDefinedFileAttributeView view = Files.getFileAttributeView(file, UserDefinedFileAttributeView.class);
        view.write("user.minetype", Charset.defaultCharset().encode("text/html"));
        
        //Para leer el atributo de tipo MIME, usaria este fragmente de codigo
        UserDefinedFileAttributeView viewMIME = Files.getFileAttributeView(file, UserDefinedFileAttributeView.class);
        String  name = "user.mimetype";
        ByteBuffer buf = ByteBuffer.allocate(view.size(name));
        viewMIME.read(name, buf);
        buf.flip();
        String value = Charset.defaultCharset().decode(buf).toString();
        System.out.println(value);
        */
        
        //------------------------------------------------------------------
        //Atributos de arvhico definido por el usuario
        
        //da informacion de la cantidad de espacio disponible
        System.out.println("");
        System.out.println("Atributo del almacen de archivos");
        Path fileStore = Paths.get("C:\\Users\\AlienWare\\Pictures\\user.txt");
        FileStore store = Files.getFileStore(fileStore);
        long total = store.getTotalSpace() / 1024;
        long used = (store.getTotalSpace() - store.getUnallocatedSpace()) / 1024;
        long avail = store.getUsableSpace() / 1024;
        
        System.out.println("total " + total);
        System.out.println("used " + used);
        System.out.println("avail " + avail);
    }
}
