/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package archivo;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 *
 * @author AlienWare
 */
public class FileTest {

    public static void main(String[] args) {
        
        
        
        
        
        //creando un camino-----------------------------
        System.out.println("Creando un camino");
        //Path p1 = Paths.get(args[0]);
        Path p2 = Paths.get(URI.create("file:///Users/AlienWare//Desktop/sql.txt"));
        //Creando ruta solaris
        //Path p3 = Paths.get(/tmo/foo);

        //abreviatura para el codigo anterior 
        //Path p4 = FileSystems.getDefault().getPath(ruta) SOLARIS
        //Path p5 = Paths.get(System.getProperty("user.home"), "logs", "Desktop.log");
        System.out.format("getFileName: %s%n", p2.getFileName());
        System.out.format("toUri: %s%n", p2.toUri());

        System.out.println("");
        //--------------------------------------------
        
        
        
        
        

        //Recuperando informacion sobre la ruta-------------
        System.out.println("Recuperando informacion");
        //obteniendo info sobre la ruta
        Path path = Paths.get("C:\\Users\\AlienWare\\Desktop\\xm.txt"); //windows
        System.out.format("toString: %s%n", path.toString());
        System.out.format("getFileName: %s%n", path.getFileName());
        System.out.format("getName(0): %s%n", path.getName(0));
        System.out.format("getNameCount: %d%n", path.getNameCount());
        System.out.format("subpath(0,2): %s%n", path.subpath(0, 2));
        System.out.format("getParent: %s%n", path.getParent());
        System.out.format("getRoot: %s%n", path.getRoot());

        //---------------------------------------------------------
        
        
        
        
        
        
        
        
        
        //Convirtiedno una ruta-----------------------------------
        /*
        System.out.println("");
        System.out.println("Convirtiendo un ruta");
        if (args.length < 1) {
            System.out.println("uso: archivo FileTest");
            System.exit(-1);
        }

        //Convierte la cadena de entrada en un objeto Path
        //System.out.println("Ingrese una ruta");
        //Scanner in = new Scanner(System.in);
        //args[0] = in.next();
        Path inputPath = Paths.get(args[0]);
        //convierte la ruta de entrada
        //en una ruta absoluta.
        //generalmente, esto significa anteponer el directrio
        //de trabajo actual. si este ejemplo
        //se llamara asi: java FileTest foo los
        //metodos get root y getParent
        //devolvieran nulo.

        // en la instancia original "inputPath". 
        //Invocar getRoot y 
        // getParent en la instancia "fullPath" 
        // devuelve los valores esperados. 
        Path fullPath = inputPath.toAbsolutePath();
        */
        //---------------------------------------------------------
        
        
        
        
        
        
        
        
        //toRealaPath devuelve la ruta real del archivo existente
        
        System.out.println("");
        System.out.println("toRealPath");
        try{
            Path fp = path.toRealPath();
            System.out.format("toRealPath: %s%n", fp);
        }catch(NoSuchFileException x){
            System.err.format("%s: no existe el archivo" + "o directrio%n", path);
        }catch(IOException x){
            System.err.format("%s%n", x);
        }
        
        //-------------------------------------------------------------
        
        
        
        
        
        
        

        //Uniendo Caminos metodo resolve
        System.out.println("");
        System.out.println("Uniendo caminos");
        //Windows
        Path uc = Paths.get("C:\\Users\\AlienWare\\Desktop");
        Path ruta = uc.resolve("xm.txt");
        System.out.format("%s%n", uc.resolve(ruta));
        
        //---------------------------------------------------------------
        
        
        
        
        
        
        
        
        
        /*
        System.out.println("");
        //abriendo un archivo
        System.out.println("Abriendo archivos");
        try{
            File obfFile = new File("C:\\Users\\AlienWare\\Desktop\\xmm.txt");
            Desktop.getDesktop().open(obfFile);
            //Process p = Runtime.getRuntime().exec("C:\\Program Files (x86)\\MetaTrader 4\\terminal.exe");
        }catch(IOException e){
            System.err.println("error" + e);
        }
        */
        //-------------------------------------------------------------------
        
        
        
        
        
        
        
        
        
        //Creando una ruta entre dos rutas
        System.out.println("");
        System.out.println("Creando una ruta entre dos rutas");
        Path r = Paths.get("Lucas");
        Path r2 = Paths.get("sally");
        
        //el resultado es ../sally
        Path rTor2 = r.relativize(r2);
        //el resultado es ../Lucas
        Path r2Tor = r2.relativize(r);
        
        System.out.format("Ruta Uno %s%n", rTor2);
        System.out.format("Ruta Dos %s%n", r2Tor);
        
        //ejempplo un poco mas complicado
        Path rutaUno = Paths.get("home");
        Path rutaTres = Paths.get("home/sally/bar");
        //el resultado sera sally/bar
        Path rutaUnoToRutaTres = rutaUno.relativize(rutaTres);
        //el resultado ses ../..
        Path rutaTresToRutaUno = rutaTres.relativize(rutaUno);
        
        System.out.format("Ruta Uno %s%n", rutaUnoToRutaTres);
        System.out.format("Ruta Dos %s%n", rutaTresToRutaUno);
        
        //-------------------------------------------------------------------
        
        
        
        
        
        
        
        
        
        
        
        

        //Comparando dos caminmos
        
        System.out.println("");
        System.out.println("Comparando dos caminos");
        Path p = Paths.get("home/sally/bar");
        Path pathss = p;
        Path otherPath = p;
        Path begining = Paths.get("/home");
        Path ending = Paths.get("foo");
        
        if(pathss.equals(otherPath)){
            System.out.format("Paths es igual a otra ruta = %s%n", pathss.getFileName());
        }else if(pathss.startsWith(begining)){
            System.out.format("Paths es igual a begining =  %s%n", begining.getFileName());
        }else if(pathss.endsWith(ending)){
            System.out.format("Paths es igual a ending =  %s%n", ending.getFileName());
        }
        
        //recorrer la ruta pathss
        for(Path name: pathss){
            System.out.println(name);
        }
        
        //------------------------------------------------------------------------
        
        
        
        
        
        
        
        
        
        
        
    }
}
