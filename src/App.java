import java.io.File;
import java.io.FileNotFoundException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Scanner;


public class App {
    public void main(String[] args) throws Exception {
        //String[] data = FileReader("Data/data.txt");
        MainFrame myFrame = new MainFrame();
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

    String HashSHA256(String data){
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
