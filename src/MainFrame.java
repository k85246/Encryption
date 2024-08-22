import java.awt.*;
import javax.swing.*;

public class MainFrame extends JFrame {

    void initialize(){
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        setTitle("Main Frame");
        setSize(500,600);
        setMinimumSize(new Dimension(400,500));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create a SpringLayout
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new GridLayout(1,2,20,20));
        JPanel northPanel = new JPanel();
        northPanel.setLayout(new GridLayout(1,1,20,20));
        JPanel southPanel = new JPanel();
        southPanel.setLayout(new GridLayout(1,1,20,20));
        JPanel westPanel = new JPanel();
        westPanel.setLayout(new GridLayout(1,1,20,20));

        // Create buttons
        JButton button1 = new JButton("center 1");
        JButton button12 = new JButton("center 1");
        JButton button2 = new JButton("Button 2");
        JButton button3 = new JButton("Button 3");
        JButton button4 = new JButton("Button 4");

        centerPanel.add(button1);
        centerPanel.add(button12);
        northPanel.add(button2);
        southPanel.add(button3);
        westPanel.add(button4);
        mainPanel.add(centerPanel,BorderLayout.CENTER);
        mainPanel.add(northPanel,BorderLayout.NORTH);
        mainPanel.add(southPanel,BorderLayout.SOUTH);
        mainPanel.add(westPanel,BorderLayout.WEST);
        add(mainPanel);
        setVisible(true);
    }


}
