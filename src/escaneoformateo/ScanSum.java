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
public class ScanSum {
    public static void main(String[] args) throws IOException {

        Scanner s = null;
        double suma = 0;

        try {
            s = new Scanner(new BufferedReader(new FileReader("C:\\Users\\AlienWare\\Documents"
                    + "\\PROGRAMACION\\JAVA TUTORIAL\\CLASES ESENCIALES DE JAVA"
                    + "\\BASIC E-S\\BasicIO\\src\\escaneoformateo\\usernumber.txt")));
            s.useLocale(Locale.US);

            while (s.hasNext()) {
                if (s.hasNextDouble()) {
                    suma += s.nextDouble();
                } else {
                    s.next();
                }
            }
        } finally {
            s.close();

        }
        System.out.println(suma);
    }
}
