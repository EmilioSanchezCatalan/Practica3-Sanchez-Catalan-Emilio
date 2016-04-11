package accesodnie;

import java.util.Scanner;

/**
 * 
 * @author windic
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception{
        Scanner scanf = new Scanner(System.in);
        String clave;
        
        //Obtener los datos del DNIe
        ObtenerDatos od = new ObtenerDatos();
        String datosnif = od.oBtenerDatos();          
          
          
        System.out.println("Introduce la contraseña: ");
        clave = scanf.nextLine();
        String cifrado = Cifrado.cifrar(datosnif,datosnif,clave);
        //Autenticarse en el servidor
        System.out.println(Conexionhttp.oPen(cifrado));
    }

}
