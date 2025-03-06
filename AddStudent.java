import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.awt.event.ActionEvent;

public class AddStudent extends JPanel
{
    private JTextField  txtName = new JTextField(20);       
    private JTextField  txtId = new JTextField(3);        
    private JButton     cmdSave  = new JButton("Save");
    private JButton     cmdClose = new JButton("Close");
    private ViewStudents viewStudents;
  
    public AddStudent(ViewStudents viewStudents, JPanel ViewStudents, JFrame first)
    {
        this.viewStudents = viewStudents;
        JLabel name = new JLabel("Student Name: ");
        JLabel id = new JLabel("Student ID Number: "); 
        JLabel title = new JLabel("Add Student");

        name.setBounds(75,100,200,25);
        id.setBounds(75,150,200,25);
        title.setBounds(170,25,300,25);

        name.setFont(new Font("Cooper Black",Font.PLAIN,16));
        id.setFont(new Font("Cooper Black",Font.PLAIN,16));
        title.setFont(new Font("Cooper Black",Font.PLAIN,26));
        cmdSave.setFont(new Font("Cooper Black",Font.PLAIN,16));
        cmdClose.setFont(new Font("Cooper Black",Font.PLAIN,16));

        txtName.setBounds(200,100,200,25);
        txtId.setBounds(250,150,200,25);
        cmdSave.setBounds(150,200,120,25);
        cmdClose.setBounds(270,200,120,25);

        cmdClose.setBackground(Color.decode("#F3EDE4"));
        cmdSave.setBackground(Color.decode("#F3EDE4"));

        txtName.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        txtId.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        cmdClose.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        cmdSave.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));

        cmdSave.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                if (!txtName.getText().isEmpty() && !txtId.getText().isEmpty())
                {
                    String name = txtName.getText().trim();
                    int id = Integer.parseInt(txtId.getText().trim());
                    String[] fullName = name.split(" ");
                    Student stu = new Student(name, id);
                    viewStudents.addStudent(stu);
                    setVisible(false);
                

                    try(BufferedWriter writer = new BufferedWriter(new FileWriter("students.dat", true)))
                    {
                        writer.newLine();
                        writer.write(fullName[0] + " " + fullName[1] + " " + stu.getId());
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
                first.remove(AddStudent.this); // Remove the current instance of AddExam
                first.add(ViewStudents); // Add the TeacherMenu panel back
                first.revalidate();
                first.repaint();
            }
        });

        add(name);
        add(id);
        add(title);
        add(txtName);
        add(txtId);
        add(cmdSave);
        add(cmdClose);
        setBounds(100,0,620,720);
        setLayout(null);
        setVisible(true);

    }
}