import java.awt.BorderLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import java.util.ArrayList;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;



public class Events extends JPanel{
    private static final long serialVersionUID = 1L;
    private ArrayList<Task> events; //ArrayList to store tasks
    
    public Events(){
        
        events = new ArrayList<>(); //Initialize the

        //Reading assignments and quizzes from text files//

        //FIRST METHOD FOR MORE THAN ONE FILES:
        //for(String fileName:filenames){
        //    readTasksFromFile(filename);
        //}

        //SECOND METHOD FOR TWO FILES. NAMELY ASSIGNMENTS AND QUIZZES
        //readTasksFromFile("assignments.txt");
        //readTasksFromFile("quizzes.txt");




        setLayout(new BorderLayout(20,20));
        setBackground(Color.white);
        setBorder(BorderFactory.createEmptyBorder(40,20,30,20));

        int rows = 4;
        if(events.size()>4) rows = events.size();


        JPanel list = new JPanel(new GridLayout(rows,1,10,10));
        list.setBackground(Color.white);

        JScrollPane sp = new JScrollPane(list);

        for(int i=0; i<events.size(); i++){
            JPanel event = new JPanel(new GridLayout(2,1));
        event.setBorder(BorderFactory.createCompoundBorder
        (BorderFactory.createEmptyBorder(10,10,10,10), BorderFactory.createMatteBorder(0,10, 0, 0, Color.decode("#D2C2A2"))));
        
        event.setBackground(Color.decode("#f0f0f0"));
        event.setCursor(new Cursor(Cursor.HAND_CURSOR));

        //Create checkbox for each event
        Task task = events.get(i);
        JCheckBox checkBox = new JCheckBox();
        checkBox.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e){
                if(e.getStateChange() == ItemEvent.SELECTED){
                    events.remove(task);
                    updateUI();
                }
            }
            
        });
        event.add(checkBox);

        JLabel title = new JLabel(events.get(i).getTitle());
        title.setBorder(BorderFactory.createEmptyBorder(0,15,0,15));
        title.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 18));
        title.setForeground(Color.black);
        event.add(title);

        JLabel time = new JLabel(events.get(i).getDateTimeToString());
        time.setBorder(BorderFactory.createEmptyBorder(5,15,4,15));
        time.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 14));
        time.setForeground(Color.darkGray);
        event.add(time);

        list.add(event);
    }

    

    add(sp, BorderLayout.CENTER);

    //Method reading tasks from a text file
    //private void readTasksFromFile(String filename){
    //    try(BufferedReader tasksReader = new BufferedReader(new FileReader(filename))){
    //        String line;
    //        while((line = tasksReader.readLine()) != null){
    //            String[] parts = line.split("\\|");
    //            if(parts.length == 3){
    //                String title = parts[0];
    //                String dateTime = parts[1];
    //                String description = parts[2];
//
    //                events.add(new Task(title,dateTime,description));
    //            }
    //        } 
    //    }catch(IOException e){
    //        e.printStackTrace();
    //    }
    //}
//
//
    //}
//
}

}