import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */



/**
 *
 * @author toni
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception{
        Scanner scanf = new Scanner(System.in);
        String clave;
        /*ByteArrayInputStream bais=null;
       //read("cert.cer");
       
       FileInputStream fis = new FileInputStream("cert.cer");
      
       
       byte value[] = new byte[fis.available()];
         fis.read(value);
        bais = new ByteArrayInputStream(value);
*/
        
        //TODO: Obtener los datos del DNIe
        ObtenerDatos od = new ObtenerDatos();
        //String datosnif = od.oBtenerDatos();
          String datosnif = "esanchezc26052698t";
          
          
          
          System.out.println("Introduce la contraseña: ");
          clave = scanf.nextLine();
        //TODO: Autenticarse en el servidor
        
    
    }

}
