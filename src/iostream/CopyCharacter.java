/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iostream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
/**
 *
 * @author AlienWare
 */
public class CopyCharacter {
    public static void main(String[] args) throws IOException{
        FileReader inputStream = null;
        FileWriter outputStream = null;
        
        try{
            inputStream = new FileReader("C:\\Users\\AlienWare\\Documents"
                    + "\\PROGRAMACION\\JAVA TUTORIAL\\CLASES ESENCIALES DE JAVA"
                    + "\\BASIC E-S\\BasicIO\\src\\iostream\\texto.txt");
            outputStream = new FileWriter("C:\\Users\\AlienWare\\Documents"
                    + "\\PROGRAMACION\\JAVA TUTORIAL\\CLASES ESENCIALES DE JAVA"
                    + "\\BASIC E-S\\BasicIO\\src\\iostream\\salidatexto.txt");
            int c;
            while((c = inputStream.read()) != -1){
                outputStream.write(c);
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
