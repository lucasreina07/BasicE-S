
import java.nio.file.*;
import static java.nio.file.StandardWatchEventKinds.*;
import java.nio.file.LinkOption.*;
import java.nio.file.attribute.*;
import java.io.*;
import java.util.*;

/**
 * Ejemplo monitorea un directorio especificado para archivos nuevos. Si un
 * archivo recién agregado es un archivo de texto sin formato, el archivo puede
 * enviarse por correo electrónico al alias apropiado. Los detalles del correo
 * electrónico se dejan al lector. Lo que el ejemplo realmente hace es imprimir
 * los detalles a la salida estándar.
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
    void processEvents() {
        for (;;) {

            //  espera a que se señale la clave
            WatchKey key;
            try {
                key = watcher.take();
            } catch (InterruptedException x) {
                return;
            }

            for (WatchEvent<?> event : key.pollEvents()) {
                WatchEvent.Kind kind = event.kind();

                if (kind == OVERFLOW) {
                    continue;
                }

                // El nombre del archivo es el contexto del evento. 
                WatchEvent<Path> ev = (WatchEvent<Path>)event;
                Path filename = ev.context();
                // Verifique que el nuevo archivo sea un archivo de texto.
                try {
                    Path child = dir.resolve(filename);
                    if (!Files.probeContentType(child).equals("text/plain")) {
                        System.err.format("Nuevo archivo '%s' no es un archivo de texto sin formato.%n", filename);
                        continue;
                    }
                } catch (IOException x) {
                    System.err.println(x);
                    continue;
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

    static void usage() {
        System.err.println("usa: directorio de correo electronico de java");
        System.exit(-1);
    }

    public static void main(String[] args) throws IOException {
        //args[0] = "C:\\Users\\AlienWare\\Documents\\PROGRAMACION"
        //      + "\\JAVA TUTORIAL\\CLASES ESENCIALES DE JAVA\\BASIC E-S\\directorio\\texto.txt";
        //analisar argumentos
        if (args.length > 1) {
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
