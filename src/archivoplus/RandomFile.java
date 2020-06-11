/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package archivoplus;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Path;
import java.nio.file.Paths;
import static java.nio.file.StandardOpenOption.READ;
import static java.nio.file.StandardOpenOption.WRITE;

/**
 *
 * @author AlienWare
 */
public class RandomFile {
    public static void main(String[] args) {
        //Accedo aleatorio de archivos
        Path path = Paths.get("./logfile.txt");
        String s = "Estaba aqui";
        byte[] data = s.getBytes();
        ByteBuffer out = ByteBuffer.wrap(data);
        
        ByteBuffer copy = ByteBuffer.allocate(12);
        try(FileChannel fc = (FileChannel.open(path, READ, WRITE))){
            //lee los primeros 12 bytes del archivo
            int nread;
            
            do{
                nread = fc.read(copy);
            }while(nread != -1 && copy.hasRemaining());
            
            //escribe estaba aqui al principio del archivo
            fc.position(0);
            while (out.hasRemaining()) {
                fc.write(out);
            }
            out.rewind();
            
            // Moverse al final del archivo. Copie los primeros 12 bytes en
            // el final del archivo. Luego escribe "¡Estaba aquí!" de nuevo.
            long length = fc.size();
            fc.position(length-1);
            copy.flip();
            
            while(copy.hasRemaining())
                fc.write(copy);
            while(out.hasRemaining())
                fc.write(out);
        }catch(IOException e){
            System.out.println("I/O Exceptio " + e);
        }
    }
}
