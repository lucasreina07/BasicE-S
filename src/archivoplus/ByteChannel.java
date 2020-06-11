/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package archivoplus;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SeekableByteChannel;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 *
 * @author AlienWare
 */
public class ByteChannel {
    public static void main(String[] args) {
        Path file = Paths.get("./logfile.txt");
        try(SeekableByteChannel sbc = Files.newByteChannel(file)){
            ByteBuffer buf = ByteBuffer.allocate(10); //capacidad de lienas
            
            // Lee los bytes con la codificación adecuada para esta plataforma. Si 
            // omite este paso, es posible que vea algo parecido a 
            // caracteres chinos cuando espera caracteres de estilo latino. 
            String encoding = System.getProperty("file.encoding");
            while(sbc.read(buf) > 0){
                buf.rewind();
                System.out.println(Charset.forName(encoding).decode(buf));
                buf.flip();
            }
        }catch(IOException x){
            
        }
    }
}
