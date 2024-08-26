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
        centerPanel.setPreferredSize(new Dimension(50, 50));
        centerPanel.setLayout(new GridLayout(1,1,20,50));
        JPanel northPanel = new JPanel();
        northPanel.setPreferredSize(new Dimension(50, 50));
        northPanel.setLayout(new GridLayout(1,1,50,0));
        JPanel southPanel = new JPanel();
        southPanel.setLayout(new GridLayout(1,2,20,0));
        southPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        JPanel westPanel = new JPanel();
        westPanel.setPreferredSize(new Dimension(50, 50));
        westPanel.setLayout(new GridLayout(1,1,20,20));
        JPanel eastPanel = new JPanel();
        eastPanel.setPreferredSize(new Dimension(50, 50));
        eastPanel.setLayout(new GridLayout(1,1,20,20));

        // Create buttons
        JButton runButton = new JButton("Run");
        JPanel runPanel = new JPanel();
        runPanel.add(runButton);
        JButton clearButton = new JButton("Clear");
        JPanel clearPanel = new JPanel();
        clearPanel.add(clearButton);

        runButton.setPreferredSize(new Dimension(100, 50)); 
        clearButton.setPreferredSize(new Dimension(200, 50));
        
        southPanel.add(runPanel);
        southPanel.add(clearPanel);
        mainPanel.add(centerPanel,BorderLayout.CENTER);
        mainPanel.add(northPanel,BorderLayout.NORTH);
        mainPanel.add(southPanel,BorderLayout.SOUTH);
        mainPanel.add(eastPanel,BorderLayout.EAST);
        mainPanel.add(westPanel,BorderLayout.WEST);
        add(mainPanel);
        setVisible(true);
    }


}
