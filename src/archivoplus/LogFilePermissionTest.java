/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package archivoplus;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SeekableByteChannel;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import static java.nio.file.StandardOpenOption.APPEND;
import static java.nio.file.StandardOpenOption.CREATE;
import java.nio.file.attribute.FileAttribute;
import java.nio.file.attribute.PosixFilePermission;
import java.nio.file.attribute.PosixFilePermissions;
import java.util.HashSet;
import java.util.Set;

/**
 * Este comando es solo para UNIX POSIX
 * @author AlienWare
 */

public class LogFilePermissionTest {
    public static void main(String[] args) {
        
        //Cre el conjunto de opciones para agregar al archivo.
        Set<OpenOption> options = new HashSet<>();
        options.add(APPEND);
        options.add(CREATE);
        
        //Crea el atributo de permisos personalizados
        Set<PosixFilePermission> perms = PosixFilePermissions.fromString("rw-r-----");
        FileAttribute<Set<PosixFilePermission>> attr = PosixFilePermissions.asFileAttribute(perms);
        
        //convierte la cadena en un byteBuffer
        String s = "Hola mundo";
        byte[] data = s.getBytes();
        ByteBuffer bb = ByteBuffer.wrap(data);
        
        Path file = Paths.get("./permmisions.log");
        
        try(SeekableByteChannel sbc = Files.newByteChannel(file, options, attr)){
            sbc.write(bb);
        }catch(IOException x){
            
        }
    }
}
