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
        String clave, res="";
        int op;
        
        //Obtener los datos del DNIe
        ObtenerDatos od = new ObtenerDatos();
        String datosnif = od.oBtenerDatos();          
          
        System.out.println("------------Menu------------");
        System.out.println("1. Sin autentificacion");
        System.out.println("2. Con autentificacion");
        System.out.print("?.> ");
        op = scanf.nextInt();
        System.out.println("Introduce la contraseña: ");
        scanf.nextLine();
        clave = scanf.nextLine();
        switch(op){
            case 1:
                Cifrado c = new Cifrado();
                c.sinCifrar(datosnif);
                res = Conexionhttp.oPen(c.getUser(), c.getDni(), clave);
                break;
            case 2:
                String cifrado = Cifrado.cifrar(datosnif,datosnif,clave);
                res = Conexionhttp.oPens(cifrado);
                break;
        }
        //Autenticarse en el servidor
        System.out.println(res);
    }

}
