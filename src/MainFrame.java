import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class MainFrame extends JFrame {

    JButton runButton = new JButton("Run");
    JButton clearButton = new JButton("Clear");
    JTextArea text = new JTextArea();
    JLabel errorLabel = new JLabel();
    
    MainFrame(){
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        setTitle("Main Frame");
        setSize(500,600);
        setMinimumSize(new Dimension(400,500));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        errorLabel.setFont(new Font("Arial", Font.BOLD, 24));
        

        JPanel northPanel = new JPanel();
        //northPanel.setPreferredSize(new Dimension(50,50));
        JPanel centerPanel = new JPanel();
        //centerPanel.setPreferredSize(new Dimension(50,10));
        JPanel southPanel = new JPanel();
        southPanel.setLayout(new GridLayout(1,2,20,0));
        southPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        JPanel runPanel = new JPanel();
        runPanel.add(runButton);
        runButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                if(text.getText().isEmpty()){
                    errorLabel.setText("NULL");
                }else{
                    errorLabel.setText(text.getText());
                }
            }
        });
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
        textPanel.setBorder(BorderFactory.createEmptyBorder(100,0,0,0));
        text.setLineWrap(true);
        textPanel.add(text);
        text.setPreferredSize(new Dimension(380,200));
        centerPanel.add(textPanel);
        JPanel labelPanel = new JPanel();
        labelPanel.setBorder(BorderFactory.createEmptyBorder(50,0,0,0));
        labelPanel.add(errorLabel);
        northPanel.add(labelPanel);
        southPanel.add(runPanel);
        southPanel.add(clearPanel);
        mainPanel.add(northPanel,BorderLayout.NORTH);
        mainPanel.add(centerPanel,BorderLayout.CENTER);
        mainPanel.add(southPanel,BorderLayout.SOUTH);
        add(mainPanel);
        setVisible(true);
    }


}
