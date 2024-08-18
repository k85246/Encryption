import java.sql.Statement;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.SecureRandom;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Base64;
import java.util.Scanner;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;

public class App {
    public void main(String[] args) throws Exception {
        String[] data = FileReader("Data/data.txt");
        Database database = new Database();
        database.selectQuery();
        
        

        // KeyGenerator keyGen = KeyGenerator.getInstance("AES");
        // keyGen.init(256);
        // SecretKey key = keyGen.generateKey();
        // byte[] iv = new byte[16];
        // SecureRandom random = new SecureRandom();
        // random.nextBytes(iv);
        // IvParameterSpec IvParam = new IvParameterSpec(iv);
        // for (String plainText : data) {
        //     Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5padding");
        //     cipher.init(cipher.ENCRYPT_MODE, key,IvParam);
        //     byte[] cipherText = cipher.doFinal(plainText.getBytes());
        //     System.out.println("The encrypted Text: "+cipherText);
        //     cipher.init(cipher.DECRYPT_MODE, key,IvParam);
        //     byte[] originText = cipher.doFinal(cipherText);
        //     System.out.println("The origin Text : "+ originText);
        // }
    }

    String[] FileReader(String FileName) throws FileNotFoundException{
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
}
