import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.awt.event.ActionEvent;

public class AddExam extends JPanel
{
    private JTextField  courseName = new JTextField(20);  
    private JTextField  courseCode = new JTextField(10);
    private JTextField  dateTextField = new JTextField(20);
    private JTextField  strtTime = new JTextField(6);
    private JTextField  endTime = new JTextField(6);
    private JTextField  venueTextField = new JTextField(20);   
    private JButton     cmdSave  = new JButton("Save");
    private JButton     cmdClose = new JButton("Close");
  
    public AddExam(JPanel TeacherMenu, JFrame first)
    {

        JLabel cname = new JLabel("Course Name: ");
        JLabel ccode = new JLabel("Course Code: ");
        JLabel date = new JLabel("Date: ");
        JLabel stime = new JLabel("Start Time: ");
        JLabel etime = new JLabel("End Time: ");
        JLabel venue = new JLabel("Venue: ");
        JLabel title = new JLabel("Add Exam");
        
        cname.setBounds(75,100,200,25);
        ccode.setBounds(75,150,200,25);
        date.setBounds(75,200,200,25);
        stime.setBounds(75,250,200,25);
        etime.setBounds(75,300,200,25);
        venue.setBounds(75,350,200,25);
        title.setBounds(170,15,300,25);

        cname.setFont(new Font("Cooper Black",Font.PLAIN,16));
        ccode.setFont(new Font("Cooper Black",Font.PLAIN,16));
        date.setFont(new Font("Cooper Black",Font.PLAIN,16));
        stime.setFont(new Font("Cooper Black",Font.PLAIN,16));
        etime.setFont(new Font("Cooper Black",Font.PLAIN,16));
        venue.setFont(new Font("Cooper Black",Font.PLAIN,16));
        cmdSave.setFont(new Font("Cooper Black",Font.PLAIN,16));
        cmdClose.setFont(new Font("Cooper Black",Font.PLAIN,16));
        title.setFont(new Font("Cooper Black",Font.PLAIN,26));

        courseName.setBounds(200,100,200,25);
        courseCode.setBounds(200,150,200,25);
        dateTextField.setBounds(200,200,200,25);
        strtTime.setBounds(200,250,200,25);
        endTime.setBounds(200,300,200,25);
        venueTextField.setBounds(200,350,200,25);
        cmdSave.setBounds(75,420,120,25);
        cmdClose.setBounds(220,420,120,25);

        cmdClose.setBackground(Color.decode("#F3EDE4"));
        cmdSave.setBackground(Color.decode("#F3EDE4"));

        courseName.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        courseCode.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        dateTextField.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        strtTime.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        endTime.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        venueTextField.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        cmdClose.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        cmdSave.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));

        cmdSave.addActionListener(new ActionListener() 
        {

            @Override
            public void actionPerformed(ActionEvent e)
            {
                if(!courseName.getText().isEmpty() && !courseCode.getText().isEmpty() && !date.getText().isEmpty() && !strtTime.getText().isEmpty() && !endTime.getText().isEmpty() && !venue.getText().isEmpty())
                {
                    String coursename = courseName.getText().trim();
                    String coursecode = courseCode.getText().trim();
                    String dateString = date.getText().trim();
                    String strtTimesString = strtTime.getText().trim();
                    String endTimeString = endTime.getText().trim();
                    String venueString = venue.getText().trim();
                    setVisible(false);
                

                    try(BufferedWriter writer = new BufferedWriter(new FileWriter("Exams.dat", true)))
                    {
                        writer.write(coursename + " " + coursecode + " " + "Date: " + dateString + " " + "Start: " + strtTimesString + " " + "End: " + endTimeString + " " + "Venue: " + venueString);
                        writer.newLine();
                    }
                    catch(IOException excep)
                    {
                        excep.printStackTrace();
                    }
                }
            }
        });

        cmdClose.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                first.remove(AddExam.this); // Remove the current instance of AddExam
                first.add(TeacherMenu); // Add the TeacherMenu panel back
                first.revalidate();
                first.repaint();
            }
        });

        add(cname);
        add(ccode);
        add(date);
        add(stime);
        add(etime);
        add(venue);
        add(title);
        add(courseName);
        add(courseCode);
        add(dateTextField);
        add(strtTime);
        add(endTime);
        add(venueTextField);
        add(cmdSave);
        add(cmdClose);
        setBounds(100,0,620,720);
        setLayout(null);
        setVisible(true);

    }
}
