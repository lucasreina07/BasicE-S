/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package viendodirectorioscambios;


import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;

/**
 *
 * @author AlienWare
 */
public class WatchServices {
    public static void main(String[] args) throws IOException, InterruptedException {
        
        //---------------------------
        //Crear un servicio de vigilancia y registrarse para eventos
        
        //crear un WatchService utilizando newWatchService
        WatchService watcher = FileSystems.getDefault().newWatchService();
        
        //el siguiente fragmento de codigo muestra como registrar una 
        //instancia Path para los tres tipos de eventos
        
        Path dir = Paths.get("C:\\Users\\AlienWare\\Documents\\PROGRAMACION"
                + "\\JAVA TUTORIAL\\CLASES ESENCIALES DE JAVA\\BASIC E-S\\directorio");
        
        try {
            WatchKey key = dir.register(watcher, 
                    StandardWatchEventKinds.ENTRY_CREATE,
                    StandardWatchEventKinds.ENTRY_DELETE,
                    StandardWatchEventKinds.ENTRY_MODIFY);
            while((key = watcher.take()) != null){
                for(WatchEvent<?> event: key.pollEvents()){
                    System.out.println(
                            "Event king: " + event.kind()
                            +". File Affectec: " + event.context());
                }
                key.reset();
            }
        } catch (IOException e) {
            System.err.println("Error de VRE " + e);
        }
        
        //-----------------------------
        //Procesando evento
        
    }
}
