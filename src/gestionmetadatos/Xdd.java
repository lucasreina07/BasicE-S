/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionmetadatos;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.nio.file.*;
import java.nio.file.attribute.*;
import java.io.IOException;

/**
 *
 * @author AlienWare
 */
public class Xdd {
    static void usage() {
        System.out.println("Usage: java Xdd <file>");
        System.out.println("       java Xdd -set <name>=<value> <file>");
        System.out.println("       java Xdd -get <name> <file>");
        System.out.println("       java Xdd -del <name> <file>");
        System.exit(-1);
    }

    public static void main(String[] args) throws IOException {
        // one or three parameters
        if (args.length != 1 && args.length != 3)
            usage();

        Path file = (args.length == 1) ?
            Paths.get(args[0]) : Paths.get(args[2]);

        // verifica que los atributos definidos por el usuario sean 
        //compatibles con el almacén de archivos
        FileStore store = Files.getFileStore(file);
        if (!store.supportsFileAttributeView(UserDefinedFileAttributeView.class)) {
            System.err.format("UserDefinedFileAttributeView not supported on %s\n", store);
            System.exit(-1);

        }
        UserDefinedFileAttributeView view = Files.
            getFileAttributeView(file, UserDefinedFileAttributeView.class);

        // enumera los atributos definidos por el usuario
        if (args.length == 1) {
            System.out.println("    Size  Name");
            System.out.println("--------  --------------------------------------");
            for (String name: view.list()) {
                System.out.format("%8d  %s\n", view.size(name), name);
            }
            return;
        }

        // Agregar / reemplazar el atributo definido por el usuario de un archivo
        if (args[0].equals("-set")) {
            // name=value
            String[] s = args[1].split("=");
            if (s.length != 2)
                usage();
            String name = s[0];
            String value = s[1];
            view.write(name, Charset.defaultCharset().encode(value));
            return;
        }

        // Imprime el valor del atributo definido por el usuario de un archivo
        if (args[0].equals("-get")) {
            String name = args[1];
            int size = view.size(name);
            ByteBuffer buf = ByteBuffer.allocateDirect(size);
            view.read(name, buf);
            buf.flip();
            System.out.println(Charset.defaultCharset().decode(buf).toString());
            return;
        }

        // Elimina el atributo definido por el usuario
        if (args[0].equals("-del")) {
            view.delete(args[1]);
            return;
        }

        // opcion no reconocida
        usage();
    }
}
