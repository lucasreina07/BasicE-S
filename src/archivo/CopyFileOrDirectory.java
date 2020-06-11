/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package archivo;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

/**
 *
 * @author AlienWare
 */
public class CopyFileOrDirectory {
    public static void main(String[] args) throws IOException{
        Path copia = Paths.get("C:\\Users\\AlienWare\\Pictures\\kali.png");
        Path destino = Paths.get("C:\\Users\\AlienWare\\Pictures\\kali2.png");
        //Files.copy(copia, destino, REPLACE_EXISTING);
        
        Files.copy(copia, destino);
    }
}
