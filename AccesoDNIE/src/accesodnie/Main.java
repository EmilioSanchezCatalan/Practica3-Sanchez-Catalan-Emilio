package accesodnie;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
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
        String clave, res = "";
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
          String datosnif = "Sanchez Catalan, Emilio 26052698T";
          
          
          
        System.out.println("Introduce la contraseña: ");
        clave = scanf.nextLine();
        String cifrado = Cifrado.cifrar(datosnif,clave);
        //TODO: Autenticarse en el servidor
        try{
            URL url = new URL("http://localhost/dnie/autenticamac.php?datos="+cifrado);
            URLConnection conexion = url.openConnection();
            conexion.connect();
            InputStream is = conexion.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            char[] buffer = new char[1000];
            int leido;
            while ((leido = br.read(buffer)) > 0) {
                res = res + new String(buffer, 0, leido);
            }
            res = res.substring(res.indexOf("<h4>"), res.indexOf("</h4>")).substring(4);
        }catch(MalformedURLException e){
            e.printStackTrace();
        }catch(IOException e){
            e.printStackTrace();
        }
        System.out.println(res);
    }

}
