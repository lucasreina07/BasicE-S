/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package archivo;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.nio.file.DirectoryNotEmptyException;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import static java.nio.file.StandardCopyOption.*;

/**
 *
 * @author AlienWare
 */
public class CheckingFile {

    enum existe {
        EXISTE,
        NOEXISTE
    };

    public static void main(String[] args) {

        //recuperando la ruta
        Path checkingPaths = Paths.get("C:\\Users\\AlienWare\\Desktop\\sql.txt");
        Path checkingPaths2 = Paths.get("C:\\Users\\AlienWare\\Desktop\\xm.txt");

        //Verificacion de al existencia del archivo
        System.out.println("Metodo !File.exists");
        if (!Files.exists(checkingPaths2)) {
            System.out.format("El archivo no existe %s%n", checkingPaths2.getFileName());
        } else {
            System.out.format("El archivo existe %s%n", checkingPaths2.getFileName());
        }

        System.out.println("");

        System.out.println("Metodo Files.notExits");
        if (Files.notExists(checkingPaths)) {
            System.out.format("El archivo no existe %s%n", checkingPaths.getFileName());
        } else {
            System.out.format("El archivo existe %s%n", checkingPaths.getFileName());
            /*
            try {
                File obfFile = new File("C:\\Users\\AlienWare\\Desktop\\sql.txt");
                Desktop.getDesktop().open(obfFile);
                //Process p = Runtime.getRuntime().exec("C:\\Program Files (x86)\\MetaTrader 4\\terminal.exe");
            } catch (IOException e) {
                System.err.println("error" + e);
            }
             */
        }

        System.out.println("");

        System.out.println("archivo abierto");

        //--------------------------------------------------------
        //comprobando si las dos rutas ubican el mismo archivo
        try {
            if (Files.isSameFile(checkingPaths, checkingPaths2)) {
                System.out.println("las rutas son iguales");
            } else {
                System.out.println("las rutas no son iguales");
            }
        } catch (IOException e) {

        }
        //-----------------------------------------------------

        //eliminando archivos o directorios
        /*
        Path borrarRuta = Paths.get("C:\\Users\\AlienWare\\Desktop\\delete.txt");
        try {
            Files.delete(borrarRuta);
        } catch (NoSuchFileException x) {
            System.err.format("%s: no such" + " file or directory%n", borrarRuta);
        } catch (DirectoryNotEmptyException x) {
            System.err.format("%s not empty%n", borrarRuta);
        } catch (IOException x) {
            // File permission problems are caught here.
            System.err.println(x);
        }
        if (Files.notExists(borrarRuta)) {
            System.out.println("ruta eliminada exitosamente");
        }
         */
        //------------------------------------------------------
        //mover un archivo o direccion
        System.out.println("");
        System.out.println("Metodo Mover ruta");
        Path moverRuta = Paths.get("C:\\Users\\AlienWare\\Desktop\\move.png");
        Path destinoMovido = Paths.get("C:\\Users\\AlienWare\\Pictures\\move.png");

        try {
            if (Files.exists(moverRuta)) {
                Files.move(moverRuta, destinoMovido, REPLACE_EXISTING);
                System.out.println("Moviendo arvhico a ruta destido");
                if (Files.exists(destinoMovido)) {
                    System.out.format("archivo movido exitosamente %s%n", destinoMovido.getFileName());
                } else {
                    System.out.format("El archivo no se encuentra en la ruta destino %s%n", destinoMovido.getFileName());
                }
            } else {
                System.out.println("el archivo no se encuentra en la ruta a mover.");
            }

        } catch (IOException e) {

        } 

    }
}
