/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package escaneoformateo;
import static java.lang.Math.*;
/**
 *
 * @author AlienWare
 */
public class Root {
    public static void main(String[] args) {
        int i = 2;
        double r = sqrt(i);
        
        System.out.format("La raiz cuadradada de %d ies %f.%n", i, r);
        
        //todos los espicificadores de formato comienzan con una %
        //d formatea un valor entero como un decimal
        //f formatea un valor de coma flotante como un valor decimal
        //n emite un terminador de linea especificado de la plataforma
        
        //x formatea un entero como n hexadeximal
        //s formatea caulquier valor como una cadena
        //tB formatea un entero como un nombre de mes especificado de la localidad
        
        System.out.format("%f, %1$+020.10f %n", PI);
        System.out.format("%f, %<+020.10f %n", PI);
    }
}
