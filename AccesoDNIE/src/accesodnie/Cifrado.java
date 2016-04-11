
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
    
    /**
     * 
     * @param datos variables en la cual se manipularan para hacer el resumen. Formato var1 var2 var3 var4
     * @param clave password introducida por el usuario. Se sumara al resumen (resumen+clave)
     * @return Obtendremos el resumen de los datos sumados a la clave (var3.inicial1 + var2 + var1.inicial1 + var4.ultimainicial)
     */
    
    private static String hash(String datos, String clave){
        String [] vdatos = datos.split(" ");
        vdatos[1] = vdatos[1].substring(0, 1);
        vdatos[2] = vdatos[2].substring(0, 1);
        vdatos[3] = vdatos[3].substring(vdatos[3].length()-1, vdatos[3].length());
        String rels = vdatos[2] + vdatos[0] + vdatos[1] + vdatos[3]+ clave;
        return rels.toLowerCase();
    }
    /**
     * 
     * @param mensaje infromacion que queremos codificar en SHA-1
     * @return Devuelve la informacion y codificada en SHA-1
     */
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
    /**
     * 
     * @param codehas informacion a codificar en base64
     * @return devuelve la informacion ya codificada en base64
     */
    private static String bAse64(String codehas){
        Base64.Encoder encoder = Base64.getEncoder();
        return encoder.encodeToString(codehas.getBytes(StandardCharsets.UTF_8));
    }
    
    /**
     * 
     * @param mensaje mensaje que queremos enviar
     * @param datos informacion que identifica al usuario.
     * @param clave password propia del usuario.
     * @return devuelve toda la informacion ya codificada (Separacion del mensaje y  el hash mediante un '.'
     */
    public static String cifrar(String mensaje, String datos, String clave){
        return bAse64(mensaje+ "."+cifradoSHA(hash(datos, clave)));
    }
}
