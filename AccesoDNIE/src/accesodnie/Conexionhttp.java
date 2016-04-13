/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package accesodnie;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 *
 * @author windic
 */
public class Conexionhttp {
    
    /**
     * 
     * @param datos datos que se quieren enviar al servidor
     * @return devuelve el resultado de laconexion
     */
    public static String oPens(String datos){
        String res = "";
        try{
            URL url = new URL("http://localhost/dnie/autenticamac.php?datos="+datos);
            URLConnection conexion = url.openConnection();
            conexion.connect();
            InputStream is = conexion.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            char[] buffer = new char[1000];
            int leido;
            while ((leido = br.read(buffer)) > 0) {
                res = res + new String(buffer, 0, leido);
            }
        }catch(MalformedURLException e){
            e.printStackTrace();
        }catch(IOException e){
            e.printStackTrace();
        }
        return res;
    }
    public static String oPen(String user,String dni, String pass){
        String res = "";
        try{
            URL url = new URL("http://localhost/dnie/autentica.php?user="+user+"&dni="+dni+"&password="+pass);
            URLConnection conexion = url.openConnection();
            conexion.connect();
            InputStream is = conexion.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            char[] buffer = new char[1000];
            int leido;
            while ((leido = br.read(buffer)) > 0) {
                res = res + new String(buffer, 0, leido);
            }
        }catch(MalformedURLException e){
            e.printStackTrace();
        }catch(IOException e){
            e.printStackTrace();
        }
        res = res.substring(res.indexOf("<h4>")+4, res.indexOf("</h4>"));
        return res;
    }
}
