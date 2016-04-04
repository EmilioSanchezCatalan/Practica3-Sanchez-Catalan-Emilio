/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package accesodnie;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;


/**
 *
 * @author windic
 */
public class Cifrado {
    
    
    private static String hash(String datos, String clave){
        String [] vdatos = datos.split(" ");
        vdatos[1] = vdatos[1].substring(0, 1);
        vdatos[2] = vdatos[2].substring(0, 1);
        vdatos[3] = vdatos[3].substring(vdatos[3].length()-1, vdatos[3].length());
        String rels = vdatos[2] + vdatos[0] + vdatos[1] + vdatos[3]+ clave;
        return rels.toLowerCase();
    }
    private static String cifradoSHA(String mensaje){
        StringBuilder sb = new StringBuilder();
        try{
            MessageDigest mDigest = MessageDigest.getInstance("SHA-1");
            byte[] result = mDigest.digest(mensaje.getBytes());
            
            for (int i = 0; i < result.length; i++) {
                sb.append(Integer.toString((result[i] & 0xff) + 0x100, 16).substring(1));
            }
        }catch(NoSuchAlgorithmException e){
        }
        return sb.toString();
    }
    private static String bAse64(String codehas){
        Base64.Encoder encoder = Base64.getEncoder();
        
        return encoder.encodeToString(codehas.getBytes(StandardCharsets.UTF_8));
    }
    
    public static String cifrar(String mensaje, String clave){
        return bAse64(mensaje+ "."+cifradoSHA(hash(mensaje, clave)));
    }
}
