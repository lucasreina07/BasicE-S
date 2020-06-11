/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package caminandoarbolarchivos;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.PathMatcher;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import org.graalvm.compiler.lir.alloc.lsra.Interval;

/**
 *
 * @author AlienWare
 */
public class Find {

    public static class Finder extends SimpleFileVisitor<Path> {

        private final PathMatcher matcher;
        private int numMatches = 0;

        Finder(String pattern) {
            matcher = FileSystems.getDefault().getPathMatcher("glob:" + pattern);
        }

        //Compara el patron glob contra el nombre del archivo o directorio
        void find(Path file) {
            Path name = file.getFileName();
            if (name != null && matcher.matches(name)) {
                numMatches++;
                System.out.println(file);
            }
        }

        //Impimr el numero total de coincide con la salida estandar
        void done() {
            System.out.println("Matched: " + numMatches);
        }

        //invoca la coincidencia de patrones metodo en cada archivo
        @Override
        public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) {
            find(file);
            if (attrs.isSymbolicLink()) {
                System.out.format("Symbolic link: %s", file);
            } else if (attrs.isRegularFile()) {
                System.out.format("Regular file: ", file);
            } else {
                System.out.format("Otros: %s", file);
            }
            System.out.println("(" + attrs.size() + "butes)");
            return FileVisitResult.CONTINUE;
        }

        @Override
        public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) {
            find(dir);
            return FileVisitResult.CONTINUE;
        }

        @Override
        public FileVisitResult visitFileFailed(Path file, IOException exc) {
            System.err.println(exc);
            return FileVisitResult.CONTINUE;
        }
    }

    static void usage() {
        System.err.println("java Find<path>" + "-name \"<glob_pattern>\"");
        System.exit(-1);
    }

    public static void main(String[] args) throws IOException {
        if (args.length > 0) {
            usage();
        }
        args = new String[3];
        args[0] = "C:\\Users\\AlienWare\\Documents\\PROGRAMACION"
                + "\\JAVA TUTORIAL\\CLASES ESENCIALES DE JAVA\\BASIC E-S\\directorio";
        args[1] = "C:\\Users\\AlienWare\\Documents\\PROGRAMACION"
                + "\\JAVA TUTORIAL\\CLASES ESENCIALES DE JAVA\\BASIC E-S\\directorio\\texto.txt";

        String mensaje = "Error de directorio";
        IOException exc = new IOException(mensaje);

        Path startingDir = Paths.get(args[0]);
        String pattern = args[1];

        //trabaja en linea, se coloca esta linea debajo del path startingDir
        BasicFileAttributes attrs = Files.readAttributes(startingDir, BasicFileAttributes.class);

        Finder finder = new Finder(pattern);
        Files.walkFileTree(startingDir, finder);
        finder.done();

        finder.visitFile(startingDir, attrs);
        finder.preVisitDirectory(startingDir, attrs);
        finder.visitFileFailed(startingDir, exc);
    }
}
