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
    
    private static String hash(String mensaje){
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
        mensaje = mensaje+clave;
        return bAse64(hash(mensaje));
    }
}
