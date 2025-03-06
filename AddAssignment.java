import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.awt.event.ActionEvent;

public class AddAssignment extends JPanel
{
    private JTextField  courseName = new JTextField(20);  
    private JTextField  courseCode = new JTextField(10);
    private JTextField  Assignment = new JTextField(15);
    private JTextField  Date = new JTextField(10);
    private JButton     cmdSave = new JButton("Save");
    private JButton     cmdClose = new JButton("Close");
  
    public AddAssignment(JPanel TeacherMenu, JFrame first)
    {

        JLabel cname = new JLabel("Course Name: ");
        JLabel ccode = new JLabel("Course Code: ");
        JLabel assign = new JLabel("Assignment: ");
        JLabel ddate = new JLabel("Due Date: ");
        JLabel title = new JLabel("Add Assignment");
        
        cname.setBounds(75,100,200,25);
        ccode.setBounds(75,150,200,25);
        assign.setBounds(75,200,200,25);
        ddate.setBounds(75,250,200,25);
        title.setBounds(150,15,300,25);

        cname.setFont(new Font("Cooper Black",Font.PLAIN,16));
        ccode.setFont(new Font("Cooper Black",Font.PLAIN,16));
        assign.setFont(new Font("Cooper Black",Font.PLAIN,16));
        ddate.setFont(new Font("Cooper Black",Font.PLAIN,16));
        cmdSave.setFont(new Font("Cooper Black",Font.PLAIN,16));
        cmdClose.setFont(new Font("Cooper Black",Font.PLAIN,16));
        title.setFont(new Font("Cooper Black",Font.PLAIN,26));

        courseName.setBounds(200,100,200,25);
        courseCode.setBounds(200,150,200,25);
        Assignment.setBounds(200,200,200,25);
        Date.setBounds(200,250,200,25);
        cmdSave.setBounds(75,320,120,25);
        cmdClose.setBounds(220,320,120,25);

        cmdClose.setBackground(Color.decode("#F3EDE4"));
        cmdSave.setBackground(Color.decode("#F3EDE4"));

        courseName.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        courseCode.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        Assignment.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        Date.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        cmdClose.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        cmdSave.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));

        cmdSave.addActionListener(new ActionListener() 
        {

            @Override
            public void actionPerformed(ActionEvent e)
            {
                if(!courseName.getText().isEmpty() && !courseCode.getText().isEmpty() && !Assignment.getText().isEmpty() && !Date.getText().isEmpty())
                {
                    String coursename = courseName.getText().trim();
                    String coursecode = courseCode.getText().trim();
                    String AssignmentString = Assignment.getText().trim();
                    String DateString = Date.getText().trim();
                    setVisible(false);
                

                    try(BufferedWriter writer = new BufferedWriter(new FileWriter("Assignments.dat", true)))
                    {
                        writer.newLine();
                        writer.write(coursename + " " + coursecode + " " + "Task: " + AssignmentString + " " + "Date: " + DateString);
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
                first.remove(AddAssignment.this); // Remove the current instance of AddAssignment
                first.add(TeacherMenu); // Add the TeacherMenu panel back
                first.revalidate();
                first.repaint();
            }
        });

        add(cname);
        add(ccode);
        add(assign);
        add(ddate);
        add(title);
        add(courseName);
        add(courseCode);
        add(Assignment);
        add(Date);
        add(cmdSave);
        add(cmdClose);
        setBounds(100,0,620,720);
        setLayout(null);
        setVisible(true);

    }
}
