import java.security.SecureRandom;
import java.util.Base64;
import java.util.Scanner;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;

abstract class Encryption {
    
    String cipherText;
    String PlainText;
    abstract String[] encrypt(String[] data);  

}

class cipher extends Encryption{
    String[] encryptedString;
    int shift;
    cipher(){
        try (Scanner input = new Scanner(System.in)) {
            System.out.print("enter the shift Required: ");
            shift = input.nextInt();
        } catch(Exception e){
            System.out.println("Error in entering shift input: "+e.getMessage());
        }
        
    }    
    @Override
    String[] encrypt(String[] data) {
        StringBuilder tempEncryptred = new StringBuilder();
        encryptedString = new String[data.length];
        int count = 0;
        for (String line : data) {
            for (char c : line.toCharArray()) {
                char encryptedChar;
                if(Character.isUpperCase(c)){
                    encryptedChar =  (char) ((((int) c) + shift - 65) % 26 +65);
                }
                else{
                    encryptedChar =  (char) ((((int) c) + shift - 97) % 26 +97);
                }
                tempEncryptred.append(encryptedChar);
            }
            encryptedString[count] = tempEncryptred.toString();
            count++;
            tempEncryptred.delete(0, tempEncryptred.length());
        }
        return encryptedString;
    }

}

class AES extends Encryption{

    @Override
    String[] encrypt(String[] data){
        String[] a = null;
        try{
            KeyGenerator keyGen = KeyGenerator.getInstance("AES");
            keyGen.init(128);
            SecretKey secretKey = keyGen.generateKey();
            byte[] key = secretKey.getEncoded();
            System.out.println("the key: "+Base64.getEncoder().encodeToString(key));
            byte[] IV = new byte[16];
            SecureRandom random = new SecureRandom();
            random.nextBytes(IV);
            IvParameterSpec IvParameterSpec = new IvParameterSpec(IV);    
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            for (String plainText : data) {
                cipher.init(Cipher.ENCRYPT_MODE,secretKey,IvParameterSpec);
                byte[] cipherText = cipher.doFinal(plainText.getBytes());
                System.out.println("encrypted string: "+ Base64.getEncoder().encodeToString(cipherText));
                cipher.init(Cipher.DECRYPT_MODE, secretKey,IvParameterSpec);
                byte[] decryptedText = cipher.doFinal(cipherText);
                System.out.println("Decrypted string: " + new String(decryptedText));
            }
        }catch(Exception e){
            System.out.println("error in cipher encryption: "+e.getMessage());
        }
        return a;
    }
    
}
