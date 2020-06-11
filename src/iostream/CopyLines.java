/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iostream;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
/**
 *
 * @author AlienWare
 */
public class CopyLines {
    public static void main(String[] args) throws IOException{
        BufferedReader inputStream = null;
        PrintWriter outputStream = null;
        
        try{
            inputStream = new BufferedReader(new FileReader("C:\\Users\\AlienWare\\Documents"
                    + "\\PROGRAMACION\\JAVA TUTORIAL\\CLASES ESENCIALES DE JAVA"
                    + "\\BASIC E-S\\BasicIO\\src\\iostream\\linetexto.txt"));
            outputStream = new PrintWriter(new FileWriter("C:\\Users\\AlienWare\\Documents"
                    + "\\PROGRAMACION\\JAVA TUTORIAL\\CLASES ESENCIALES DE JAVA"
                    + "\\BASIC E-S\\BasicIO\\src\\iostream\\salidalinetexto.txt"));
            String l;
            while((l = inputStream.readLine()) != null){
                outputStream.println(l);
            }
        }finally{
            if(inputStream != null){
                inputStream.close();
            }
            if(outputStream != null){
                outputStream.close();
            }
        }
    }
}
