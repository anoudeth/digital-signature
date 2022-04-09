package com.noh.ds.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.util.Base64;

@Component
@Slf4j
public class EncryptDecrypt {

//    @Value("${merchant-id}")
    private String merchant_id = "123456789012";
//    @Value("${secret-key}")
    private String secret_key = "15C6E148BE8F8293C40E720FB722DA8C";

    public static String encrypt(String Data, String secret) throws Exception {
        Key key = generateKey(secret);
        Cipher c = Cipher.getInstance("AES");
        c.init(1, key);
        byte[] encVal = c.doFinal(Data.getBytes());
        String encryptedValue = Base64.getEncoder().encodeToString(encVal);
        return encryptedValue;
    }
    public static String decrypt(String strToDecrypt, String secret) {
        try {
            System.out.println(strToDecrypt);
            Key key = generateKey(secret);
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(2, key);
            return new String(cipher.doFinal(Base64.getDecoder().decode(strToDecrypt)));
        } catch (Exception e) {
            log.error("Error while decrypting: " + e.toString());
            return null;
        }
    }
    public static String encodeKey(String str) {
        byte[] encoded = Base64.getEncoder().encode(str.getBytes());
//        log.info("encodeSecretKey: " + new String(encoded));
        return new String(encoded);
    }

    public static Key generateKey(String secret) throws Exception {
        byte[] decoded = Base64.getDecoder().decode(secret.getBytes());
//        System.out.println("decodeSecretKey: " + new String(decoded));
        Key key = new SecretKeySpec(decoded, "AES");
//        System.out.println("generateKey: " + key);
        return key;
    }


    public static void main(String[] args) throws ParseException, NoSuchAlgorithmException {
        System.out.println("--- test encrypt ---");
        String secretKey = "15C6E148BE8F8293C40E720FB7211111";

//        String originalString = "{\n" +
//                "\t\"instid\":\"IB\",              \n" +
//                "\t\"callbackurl\":\"https://ibcooluat.iblaos.com/ibecommerce/home\",        \n" +
//                "\t\"redirecturl\":\"https://merchant.com/\",\n" +
//                "\t\"merchrefno\":\"ref123456\",\n" +
//                "\t\"merchantid\":\"140920211000235\",\n" +
//                "\t\"txnamount\":\"100\",\n" +
//                "\t\"paymode\":\"ON\",\n" +
//                "\t\"txnccy\":\"418\",\n" +
//                "\t\"customer\":{\n" +
//                "\t\t\"mobile\":\"2059366665\",\n" +
//                "\t\t\"name\":\"noh der\",\n" +
//                "\t\t\"address\":\"vientiane\",\n" +
//                "\t\t\"email\":\"noh@gmail.com\"\n" +
//                "\t}\n" +
//                "}";
        String originalString = "{\"instid\":\"INCB\",\"callbackurl\":\"http://192.168.33.29:8894/core/api/callback/test\", \"redirecturl\":\"https://www.google.com\", \"merchrefno\":\"00000000001\",\"merchantid\":\"140920211000235\",\"txnamount\":\"10000\",\"paymode\":\"ON\",\"txnccy\":\"418\",\"customer\":{\"mobile\":\"2055555555\",\"name\":\"AnoudethDer\",\"address\":\"Vientiane, Laos\",\"email\":\"nohder@gmail.com\"}}";

        try {
            String encryptedString = encrypt(originalString, encodeKey(secretKey));
            String decryptedString = decrypt(encryptedString, encodeKey(secretKey));
            System.out.println("original message: " + originalString);
            System.out.println("after encrypted : " + encryptedString);
            System.out.println("after decrypted : " + decryptedString);
        } catch (Exception e) {
            System.out.println("error: " + e.toString());
            e.printStackTrace();
        }

        System.out.println("\n\n\n-- decrypt response msg");
        String msg = "pWQOtlaeSmzSqybvtz8EiVmiE++7dlauxBr49/F56G4OQOn6WTaqz25tS4eID9sGgPtNgu8bK6/HfLUWQtJjm9f8TNyaB6WoVOXaShWlh37WSSw15S7zvdQxjo0WeagjmDcB6R46bhL5rw1tcofZz+TSbLdf3GQRKP0pV4RM3O6iS8wXR4DwIS7taEWvOkbqviTp5FPTa7sF1/ZE+nXTIA==";
        System.out.println("response decrypt: " + decrypt(msg, encodeKey(secretKey)));


    }
}