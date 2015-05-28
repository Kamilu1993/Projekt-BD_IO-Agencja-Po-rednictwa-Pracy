package com.company.Model;

import com.company.ErrorType;
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
            System.out.println("Autoryzacja zakończona pomyślnie!");
        else
            throw new Exception("Hasła się nie zgadzają!");
    }
    //endregion

    //region Konwersja hasła na hash MD5.
    public static String encrypt(String property) throws GeneralSecurityException, UnsupportedEncodingException {
        System.out.println("Rozpoczynam szyfrowanie hasła...");
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("PBEWithMD5AndDES");
        SecretKey key = keyFactory.generateSecret(new PBEKeySpec(PASSWORD));
        Cipher pbeCipher = Cipher.getInstance("PBEWithMD5AndDES");
        pbeCipher.init(Cipher.ENCRYPT_MODE, key, new PBEParameterSpec(SALT, 20));
        System.out.println("Trwa szyfrowanie...");
        return base64Encode(pbeCipher.doFinal(property.getBytes("UTF-8")));
    }
    private static String base64Encode(byte[] bytes) {
        return new BASE64Encoder().encode(bytes);
    }
    //endregion

    //region Sprawdzanie długości hasła.
    public static ErrorType.ErrTypes CheckPassLength(String password){
        /* --------- HASŁO --------- */
        if(password.length()<5)
            return ErrorType.ErrTypes.PASSWORD_TOO_SHORT;
        //  ZNAK ' " '
        int i = 34;
        char c = (char)i;
        String s = "" + c;
        if(password.contains(s))
            return ErrorType.ErrTypes.PASSWORD_CONTAINS_NOT_ALLOWED_CHARACTERS;
        // SPACJA
        s = " ";
        if(password.contains(s))
            return ErrorType.ErrTypes.PASSWORD_CONTAINS_NOT_ALLOWED_CHARACTERS;
        for(i=39;i<48;i++) {
            c = (char)i;
            s = "" + c;
            if(password.contains(s))
                return ErrorType.ErrTypes.PASSWORD_CONTAINS_NOT_ALLOWED_CHARACTERS;
        }
        for(i=58;i<64;i++) {
            c = (char)i;
            s = "" + c;
            if(password.contains(s))
                return ErrorType.ErrTypes.PASSWORD_CONTAINS_NOT_ALLOWED_CHARACTERS;
        }
        return ErrorType.ErrTypes.NO_ERRORS;
    }
    public ErrorType.ErrTypes CheckPassMatch(String pass, String pass2){
        if(pass.equals(pass2))
            return ErrorType.ErrTypes.NO_ERRORS;
        else
            return ErrorType.ErrTypes.PASSWORD_DOESNT_MATCH;
    }
    //endregion


}
