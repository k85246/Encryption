import java.awt.BorderLayout;
import java.io.File;
import java.io.FileNotFoundException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Scanner;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SpringLayout;

public class App {
    public void main(String[] args) throws Exception {
        //String[] data = FileReader("Data/data.txt");
        
         JFrame frame = new JFrame("Resizable Buttons");

        // Set the frame's size
        frame.setSize(400, 300);

        // Specify what happens when the frame is closed
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create a SpringLayout
        SpringLayout layout = new SpringLayout();
        frame.setLayout(layout);

        // Create buttons
        JButton button1 = new JButton("Button 1");
        JButton button2 = new JButton("Button 2");
        JButton button3 = new JButton("Button 3");
        JButton button4 = new JButton("Button 4");

        // Add buttons to the frame
        frame.add(button1);
        frame.add(button2);
        frame.add(button3);
        frame.add(button4);

        // Position the buttons
        layout.putConstraint(SpringLayout.WEST, button1, 20, SpringLayout.WEST, frame.getContentPane());
        layout.putConstraint(SpringLayout.NORTH, button1, 20, SpringLayout.NORTH, frame.getContentPane());

        layout.putConstraint(SpringLayout.WEST, button2, 20, SpringLayout.EAST, button1);
        layout.putConstraint(SpringLayout.NORTH, button2, 20, SpringLayout.NORTH, frame.getContentPane());

        layout.putConstraint(SpringLayout.WEST, button3, 20, SpringLayout.WEST, frame.getContentPane());
        layout.putConstraint(SpringLayout.NORTH, button3, 20, SpringLayout.SOUTH, button1);

        layout.putConstraint(SpringLayout.WEST, button4, 20, SpringLayout.EAST, button3);
        layout.putConstraint(SpringLayout.NORTH, button4, 20, SpringLayout.SOUTH, button2);

        // Set the frame to be visible
        frame.setVisible(true);
    
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
