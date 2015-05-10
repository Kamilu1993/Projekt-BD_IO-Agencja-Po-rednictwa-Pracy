package com.company.Model;

import sun.misc.BASE64Encoder;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;
import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;

/**
 * Klasa zajmująca szyfrowaniem haseł, oraz sprawdzaniem ich poprawności.
 */
public class PasswordService {
    private static final char[] PASSWORD = "PiSkasldowpqsxxdPO".toCharArray();
    private static final byte[] SALT = { (byte) 0xde, (byte) 0x33, (byte) 0x10, (byte) 0x12,
                                        (byte) 0xde, (byte) 0x33, (byte) 0x10, (byte) 0x12, };

    //region Metoda sprawdzająca czy zaszyfrowane hasło zgadza się z tym które jest w bazie.
    public static void CheckPassword(String user_password, String user_passwordInDB) throws Exception{
        String EncryptedPassword = encrypt(user_password);
        if(EncryptedPassword.equals(user_passwordInDB))
            System.out.println("Hasła sie zgadzają!");
        else
            throw new Exception("Hasła się nie zgadzają!");
    }
    //endregion

    //region Konwersja hasła na hash MD5.
    public static String encrypt(String property) throws GeneralSecurityException, UnsupportedEncodingException {
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("PBEWithMD5AndDES");
        SecretKey key = keyFactory.generateSecret(new PBEKeySpec(PASSWORD));
        Cipher pbeCipher = Cipher.getInstance("PBEWithMD5AndDES");
        pbeCipher.init(Cipher.ENCRYPT_MODE, key, new PBEParameterSpec(SALT, 20));
        return base64Encode(pbeCipher.doFinal(property.getBytes("UTF-8")));
    }
    private static String base64Encode(byte[] bytes) {
        return new BASE64Encoder().encode(bytes);
    }
    //endregion
}
