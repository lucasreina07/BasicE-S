/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package escaneoformateo;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Locale;
import java.util.Scanner;

/**
 *
 * @author AlienWare
 */
public class ScanXan {

    public static void main(String[] args) throws IOException {

        Scanner s = null;

        try {
            s = new Scanner(new BufferedReader(new FileReader("C:\\Users\\AlienWare\\Documents"
                    + "\\PROGRAMACION\\JAVA TUTORIAL\\CLASES ESENCIALES DE JAVA"
                    + "\\BASIC E-S\\BasicIO\\src\\escaneoformateo\\escaner.txt")));
            s.useDelimiter(", \\s*");
            while (s.hasNext()) {
                System.out.println(s.next());
            }
        } finally {
            if (s != null) {
                s.close();
            }
        }
    }
}
