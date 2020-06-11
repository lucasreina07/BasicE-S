/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package comandos;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 *
 * @author AlienWare
 */
//data output stram y input stram las interfaces mas utilizadas de implementacion
public class DataStreams {
    static final String dataFile = "invoidedata"; //datos de facturacion
    
    static final double[] prices = {19.99, 9.99, 15.99, 3.99, 4.99};
    static final int[] units = {12, 8, 13, 29, 50};
    static final String[] descs = {
    "java T-shirt",
    "java Mug",
    "duke juggling dolls",
    "java pin",
    "java key chain"};
    
    public static void main(String[] args) throws IOException{
        DataOutputStream out = null;
        try{
            out = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(dataFile)));
            for(int i = 0; i < prices.length; i++){
                out.writeDouble(prices[i]);
                out.writeInt(units[i]);
                out.writeUTF(descs[i]);
            }
        }finally{
            out.close();
        }
        
        //DataInputStream in = null;
        try(DataInputStream in = new DataInputStream(new BufferedInputStream(new FileInputStream(dataFile)))){
            double price;
            int unit;
            String desc;
            double total = 0.0;
            
            try{
                while(true){
                    price = in.readDouble();
                    unit = in.readInt();
                    desc = in.readUTF();
                    System.out.format("Usted ordeno %d unidades de %s a $%.2f%n", unit, desc, price);
                    total += unit * price;
                }
            }catch(EOFException e){
                System.out.format("Su total es de: $%.2f%n", total);
                //System.err.println("Error" + e.getMessage());
            }
            in.close();
        }
    }
}
