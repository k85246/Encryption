import java.sql.Statement;
import java.io.File;
import java.io.FileNotFoundException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Base64;
import java.util.Scanner;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.FileReader;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;

public class App {
    public void main(String[] args) throws Exception {
        String[] data = FileReader("Data/data.txt");
        Database database = new Database();
        database.selectQuery();
        database.close();
        // KeyGenerator keyGen = KeyGenerator.getInstance("AES");
        // keyGen.init(128);
        // SecretKey secretKey = keyGen.generateKey();
        // byte[] key = secretKey.getEncoded();
        // System.out.println("the key: "+Base64.getEncoder().encodeToString(key));
        // byte[] IV = new byte[16];
        // SecureRandom random = new SecureRandom();
        // random.nextBytes(IV);
        // IvParameterSpec IvParameterSpec = new IvParameterSpec(IV);
        // Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        // for (String plainText : data) {
        //     cipher.init(Cipher.ENCRYPT_MODE,secretKey,IvParameterSpec);
        //     byte[] cipherText = cipher.doFinal(plainText.getBytes());
        //     System.out.println("encrypted string: "+ Base64.getEncoder().encodeToString(cipherText));
        //     cipher.init(Cipher.DECRYPT_MODE, secretKey,IvParameterSpec);
        //     byte[] decryptedText = cipher.doFinal(cipherText);
        //     System.out.println("Decrypted string: " + new String(decryptedText));
        // }
    }

    static String[] FileReader(String FileName) throws FileNotFoundException{
        File file = new File(FileName);
        int LineCount = 0;
        String[] line;
        try (Scanner dataLineCount = new Scanner(file)) {
            while(dataLineCount.hasNextLine()){
                LineCount++;
                dataLineCount.nextLine();
            }
        }
            line = new String[LineCount];
            LineCount = 0;
        try (Scanner data = new Scanner(file)) {
            while(data.hasNextLine()){
                line[LineCount] = data.nextLine();
                LineCount++;
            }
        }
        return line;
    }

    static String HashSHA256(String data){
        String EncryptedPassword = null;
        try{
        MessageDigest message = MessageDigest.getInstance("SHA-256");
        byte[] bytes =message.digest(data.getBytes(StandardCharsets.UTF_8));
        EncryptedPassword = Base64.getEncoder().encodeToString(bytes);
        } catch(NoSuchAlgorithmException e){
            System.out.println("Algorithm Error: "+e.getMessage());
        }
        return EncryptedPassword;
    }


    String[] encryption(String[] data){
        StringBuilder tempEncryptred = new StringBuilder();
        String[] encryptedString = new String[data.length];
        try (Scanner input = new Scanner(System.in)) {
            System.out.print("enter the shift Required: ");
            int shift = input.nextInt();
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
        }catch(Exception e){
            encryptedString = null;
        }
        return encryptedString;
    }
}
