/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iostream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
/**
 *
 * @author AlienWare
 */
public class CopyBytes {
    public static void main(String[] args) throws IOException{
        
        FileInputStream in = null;
        FileOutputStream out = null;
        
        try{
            int c;
            in = new FileInputStream("C:\\Users\\AlienWare\\Documents\\PROGRAMACION"
                    + "\\JAVA TUTORIAL\\CLASES ESENCIALES DE JAVA\\BASIC E-S"
                    + "\\BasicIO\\src\\iostream\\xanadu.txt");
            out = new FileOutputStream("C:\\Users\\AlienWare\\Documents\\PROGRAMACION"
                    + "\\JAVA TUTORIAL\\CLASES ESENCIALES DE JAVA\\BASIC E-S"
                    + "\\BasicIO\\src\\iostream\\outagain.txt");
            
            while( (c = in.read()) != -1 ){
                out.write(c);
            }
        }finally{
            if(in != null){
                in.close();
            }
            if(out != null){
                out.close();
            }
        }
        
        //-----------------------------------------------------
        //otra prueba de lectura de archivo
        FileWriter fichero = new FileWriter("C:\\Users\\AlienWare\\Desktop\\prueba.txt");
        for(int i = 0; i<10; i++){
            fichero.write("File Number " + i + "\n");
        }
        fichero.close();
    }
}
