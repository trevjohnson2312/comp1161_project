/*import java.awt.Color;
import java.awt.GridLayout;
import java.time.LocalDate;

import javax.swing.JFrame;
import javax.swing.JPanel;


public class studentMenu {


    public static void main(String [] args){

        
        JFrame frame = new JFrame("Calendar");
        frame.setSize(900,500);
        frame.setLocationRelativeTo(null);
        frame.getContentPane().setBackground(Color.white);

        JPanel mainPanel = new JPanel(new GridLayout (1,2,0,0));

        LocalDate date = LocalDate.now();

        mainPanel.add(new Calendar(date.getYear(), date.getMonthValue(), date, mainPanel));
        mainPanel.add(new Events());

        frame.getContentPane().add(mainPanel);
        frame.setVisible(true);
    }
    
}*/

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;

public class studentMenu extends JPanel {

    public studentMenu() {
        setLayout(new GridLayout(1, 2, 0, 0));

        LocalDate date = LocalDate.now();

        add(new Calendar(date.getYear(), date.getMonthValue(), date, this));
        add(new Events());
    }
}


