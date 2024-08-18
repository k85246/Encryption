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

public class App {
    public void main(String[] args) throws Exception {
        String[] data = FileReader("data.txt");
        String db = "jdbc:sqlserver://localhost:1433;databaseName=Dev;trustServerCertificate=true;integratedSecurity=true";  //  jdbc:sqlserver://ip:port;trustServerCertificate=true;integratedSecurity=true    this for windows authentication access
        Connection c = null;
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver"); //Driver: com.microsoft.sqlserver.jdbc.SQLServerDriver
            System.out.println("Connecting to the database...");
            c = DriverManager.getConnection(db);
            System.out.println("database connected!");
        } catch(ClassNotFoundException e){
            System.out.println("Driver error: "+e.getMessage());
            System.exit(0);
        } catch (SQLException e) {
            System.out.println("Database connection error: "+e.getMessage()) ;
            System.exit(0);
        }
        Statement selectStatement = c.createStatement();
        Statement updateStatement = c.createStatement();
        ResultSet QueryResult = null;
        try { 
            QueryResult = selectStatement.executeQuery("select * from Dev.dbo.Emp");
            while (QueryResult.next()) {
                if(QueryResult.getString("passwordEncrypted").isEmpty()){
                    String EncryptedPassword = HashSHA256(QueryResult.getString("password"));
                    updateStatement.executeUpdate("update Dev.dbo.Emp set passwordEncrypted = '"+EncryptedPassword+"' where ID = "+QueryResult.getString("ID"));
                }
            }
            QueryResult = selectStatement.executeQuery("select * from Dev.dbo.Emp");
            System.out.println('\t'+QueryResult.getMetaData().getColumnLabel(1)+'\t'+QueryResult.getMetaData().getColumnLabel(2)+'\t'+QueryResult.getMetaData().getColumnName(4));
            while (QueryResult.next()) {
                System.out.println("Query: "+QueryResult.getString("ID")+"\t"+QueryResult.getString("password")+"\t"+QueryResult.getString("passwordEncrypted"));
            }
        } catch (SQLException e) {
            System.out.println("Query error: "+e.getMessage());
        }
        c.close();
        
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
}
