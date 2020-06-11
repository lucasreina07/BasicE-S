/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package viendodirectorioscambios;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardWatchEventKinds;
import static java.nio.file.StandardWatchEventKinds.ENTRY_CREATE;
import static java.nio.file.StandardWatchEventKinds.OVERFLOW;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;

/**
 *
 * @author AlienWare
 */
public class Email {

    private final WatchService watcher;
    private final Path dir;
    

    /**
     * Crea un WatchService y registra el directorio dado
     */
    Email(Path dir) throws IOException {
        this.watcher = FileSystems.getDefault().newWatchService();
        dir.register(watcher, ENTRY_CREATE);
        this.dir = dir;
    }

    /**
     * Procesar todos los eventos para la clave en cola para el observador.
     */
    void processEvents() throws InterruptedException {
        //creamos un objeto
        WatchKey key;
        while ((key = watcher.take()) != null) {
            for (WatchEvent<?> event : key.pollEvents()) {
                WatchEvent.Kind kind = event.kind();
                if (kind == OVERFLOW) {
                    continue;
                }
                
                
                WatchEvent<Path> ev = Email.cast((WatchEvent<Path>) event);
                Path filename = ev.context();
                // Verifique que el nuevo archivo sea un archivo de texto.
                try {
                    Path child = dir.resolve(filename);
                    if (!Files.probeContentType(child).equals("text/plain")) {
                        System.err.format("Nuevo archivo '%s' no es un archivo de texto sin formato.%n", filename);
                    }
                } catch (IOException x) {
                    System.err.println(x);
                }
                
                //Enviar el archivo por correo electrónico al alias de correo 
                //electrónico especificado.
                System.out.format("Archivo de correo electronico %s%n", filename);
                //Detalles que quedan para el lector .... 
            }
            
            
            //Restablecer la clave: este paso es fundamental si desea recibir 
            // más eventos de observación. Si la clave ya no es válida, el directorio 
            // es inaccesible, así que salga del bucle.
            boolean valid = key.reset();
            if (!valid) {
                break;
            }
            
        }
    }
    

    @SuppressWarnings("unchecked")
    static <Path> WatchEvent<Path> cast(WatchEvent<Path> event) {
        return (WatchEvent<Path>) event;
    }
    
    static void usage() {
        System.err.println("usa: directorio de correo electronico de java");
        System.exit(-1);
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        //analisar argumentos
        if (args.length > 0) {
            usage();
        }

        args = new String[2];
        args[0] = "C:\\Users\\AlienWare\\Documents\\PROGRAMACION"
                + "\\JAVA TUTORIAL\\CLASES ESENCIALES DE JAVA\\BASIC E-S\\directorio";

        //registra el directorio y procesa sus eventos
        Path dir = Paths.get(args[0]);
        new Email(dir).processEvents();
    }
}
