import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class MainFrame extends JFrame {

    JButton runButton = new JButton("Run");
    JButton clearButton = new JButton("Clear");
    JTextArea text = new JTextArea();
    
    
    MainFrame(){
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        setTitle("Main Frame");
        setSize(500,600);
        setMinimumSize(new Dimension(400,500));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        JPanel centerPanel = new JPanel();
        centerPanel.setPreferredSize(new Dimension(50, 50));
        centerPanel.setLayout(new GridLayout(1,1,20,50));
        JPanel southPanel = new JPanel();
        southPanel.setLayout(new GridLayout(1,2,20,0));
        southPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        JPanel runPanel = new JPanel();
        runPanel.add(runButton);
        JPanel clearPanel = new JPanel();
        clearPanel.add(clearButton);
        clearButton.addActionListener(new ActionListener() {
            @Override
            public void  actionPerformed(ActionEvent e){
                text.setText("");
            }
        });
        runButton.setPreferredSize(new Dimension(100, 50)); 
        clearButton.setPreferredSize(new Dimension(100, 50));

        JPanel textPanel = new JPanel();
        textPanel.setBorder(BorderFactory.createEmptyBorder(300,0,0,0));
        text.setLineWrap(true);
        textPanel.add(text);
        text.setPreferredSize(new Dimension(380,200));
        centerPanel.add(textPanel);
        
        southPanel.add(runPanel);
        southPanel.add(clearPanel);
        mainPanel.add(centerPanel,BorderLayout.CENTER);
        mainPanel.add(southPanel,BorderLayout.SOUTH);
        add(mainPanel);
        setVisible(true);
    }


}
