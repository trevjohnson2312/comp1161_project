import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class EventAdjuster{
    public EventAdjuster(){
        JFrame frame = new JFrame("New Event / Task");
        frame.setSize(700,290);
        frame.setLocationRelativeTo(null);
        frame.getContentPane().setBackground(Color.white);

        JPanel primaryPanel = new JPanel(new BorderLayout(20,20));
        primaryPanel.setBorder(BorderFactory.createEmptyBorder(40, 20, 20, 20));
        primaryPanel.setBackground(Color.white);

        JPanel center = new JPanel(new GridLayout(3,2,20,20));

        JLabel labelTitle = new JLabel("Title: ");
        labelTitle.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 20));
        labelTitle.setHorizontalAlignment(JLabel.CENTER);
        center.add(labelTitle);

        JTextField title = new JTextField();
        title.setFont(new Font("Bodoni 72 Oldstyle", Font.PLAIN, 20));
        title.setHorizontalAlignment(JLabel.CENTER);
        center.add(title);

        JLabel labelTime = new JLabel("Time: ");
        labelTime.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 20));
        labelTime.setHorizontalAlignment(JLabel.CENTER);
        center.add(labelTime);

        JTextField time = new JTextField();
        time.setFont(new Font("Bodoni 72 Oldstyle", Font.PLAIN, 20));
        time.setHorizontalAlignment(JLabel.CENTER);
        center.add(time);

        JLabel descrip = new JLabel("Description: ");
        descrip.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 20));
        descrip.setHorizontalAlignment(JLabel.CENTER);
        center.add(descrip);

        JTextField description = new JTextField();
        description.setFont(new Font("Bodoni 72 Oldstyle", Font.PLAIN, 20));
        description.setHorizontalAlignment(JLabel.CENTER);
        center.add(description);

        primaryPanel.add(center, BorderLayout.CENTER);

        JPanel end = new JPanel(new GridLayout(1,2,20,20));
        end.setBackground(null);

        JButton delButton = new JButton("Delete");
        delButton.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 18));
        delButton.setBackground(Color.decode("#D8B587"));
        //delButton.setBorder(BorderFactory.createMatteBorder(2,2, 2, 2, Color.decode("#102A33")));
        end.add(delButton);
        
        JButton saveButton = new JButton("Save");
        saveButton.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 18));
        saveButton.setBackground(Color.decode("#D8B587"));
        //saveButton.setBorder(BorderFactory.createMatteBorder(2,2, 2, 2, Color.decode("#102A33")));
        end.add(saveButton);
        
        primaryPanel.add(end, BorderLayout.SOUTH);

        frame.getContentPane().add(primaryPanel);


        frame.setVisible(true);


    }
}