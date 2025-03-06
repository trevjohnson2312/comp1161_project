import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.table.DefaultTableModel;
import java.util.Comparator;
import java.util.Collections;
import java.awt.Color;


public class ViewStudents extends JPanel 
{
    private JButton     cmdAddStudent;
    private JButton     cmdUpdateStudent;
    private JButton     cmdClose;
    private JButton     cmdSortByID;
    private JButton     cmdSortByFirstName;
    private ArrayList<Student> stulist;
    private JScrollPane scrollPane;
    private JTable table;
    private DefaultTableModel model;

    public ViewStudents(JPanel TeacherMenu, JFrame first) 
    {

        stulist = loadPersons("students.dat");
        String[] columnNames =  {"First Name", "Last Name", "ID Number"};
        model = new DefaultTableModel(columnNames,0);
        table = new JTable(model);
        showTable(stulist);

        table.setPreferredScrollableViewportSize(new Dimension(400, 170));
        table.setFillsViewportHeight(true);

        scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(450, 220));
        add(scrollPane);

        cmdAddStudent = new JButton ("Add Student");
        cmdClose = new JButton ("Close");
        cmdSortByFirstName = new JButton ("Sort By First Name");
        cmdSortByID = new JButton ("Sort By Id");
        cmdUpdateStudent = new JButton ("Update Student");

       
        cmdAddStudent.setFont(new Font("Cooper Black",Font.PLAIN,16));
        cmdClose.setFont(new Font("Cooper Black",Font.PLAIN,16));
        cmdSortByFirstName.setFont(new Font("Cooper Black",Font.PLAIN,16));
        cmdSortByID.setFont(new Font("Cooper Black",Font.PLAIN,16));
        cmdUpdateStudent.setFont(new Font("Cooper Black",Font.PLAIN,16));

        cmdClose.setBackground(Color.decode("#F3EDE4"));
        cmdAddStudent.setBackground(Color.decode("#F3EDE4"));
        cmdSortByFirstName.setBackground(Color.decode("#F3EDE4"));
        cmdSortByID.setBackground(Color.decode("#F3EDE4"));
        cmdUpdateStudent.setBackground(Color.decode("#F3EDE4"));
        cmdAddStudent.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        cmdSortByFirstName.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        cmdSortByID.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        cmdUpdateStudent.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        cmdClose.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));

        cmdAddStudent.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                ViewStudents viewStudents = new ViewStudents(TeacherMenu, first);
                AddStudent addStudent = new AddStudent(viewStudents, ViewStudents.this, first);
                first.add(addStudent);
                first.remove(ViewStudents.this);
                first.revalidate();
                first.repaint();
            }
        });


        cmdUpdateStudent.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Get the selected row index
                int selectedRow = table.getSelectedRow();
                
                // If no row is selected, return
                if (selectedRow == -1) {
                    return;
                }
                
                // Get the student object from the selected row
                Student selectedStudent = stulist.get(selectedRow);
                
                // Prompt the user to update student information
                String Name = JOptionPane.showInputDialog(ViewStudents.this, "Enter new name:");
                String newName = Name.trim();
                //String[] fullname = Name.split(" ");
                int newId = Integer.parseInt(JOptionPane.showInputDialog(ViewStudents.this, "Enter new ID number:"));
                
                // Update the student object
                selectedStudent.setName(newName);
                selectedStudent.setId(newId);
        
                // Remove the old data from the file
                clearFileContents("students.dat");
        
                // Write the updated data to the file
                try (BufferedWriter writer = new BufferedWriter(new FileWriter("students.dat", true))) {
                    for (Student student : stulist) {
                        writer.write(student.getName() + " " + student.getId());
                        writer.newLine();
                    }
                } catch (IOException excep) {
                    excep.printStackTrace();
                }
                
                // Refresh the table
                model.setRowCount(0);
                showTable(stulist);
            }
        });


        cmdSortByFirstName.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                Collections.sort(stulist, new Comparator<Student>()
                {
                    public int compare(Student stu1, Student stu2)
                    {
                        return stu1.getName().compareTo(stu2.getName());
                    } 
                });
    
                model.setRowCount(0);
                showTable(stulist);
            }
        });


        cmdSortByID.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                Collections.sort(stulist, new Comparator<Student>()
                {
                    public int compare(Student stu1, Student stu2)
                    {
                        return Integer.compare(stu1.getId(), stu2.getId());
                    } 
                });
    
                model.setRowCount(0);
                showTable(stulist);
            }
        });


        cmdClose.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                first.remove(ViewStudents.this); // Remove the current instance of AddExam
                first.add(TeacherMenu); // Add the TeacherMenu panel back
                first.revalidate();
                first.repaint();
            
            }
    
        });
        
        cmdAddStudent.setBounds(25,450,170,25);
        cmdSortByFirstName.setBounds(220,450,200,25);
        cmdSortByID.setBounds(440,450,150,25);
        cmdUpdateStudent.setBounds(110,500,200,25);
        table.setBounds(50,5,400,400);
        cmdClose.setBounds(335, 500, 100, 25);
        scrollPane.setBounds(50, 20, 500, 400);
        
        //add(table);
        add(cmdAddStudent);
        add(cmdUpdateStudent);
        add(cmdSortByFirstName);
        add(cmdSortByID);
        add(cmdClose);
        add(scrollPane);
       
    
        setBounds(100,0,620,720);
        setLayout(null);
        setVisible(true);
    }

    
    // Method to clear the contents of a file
    private void clearFileContents(String fileName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            writer.write(""); // Writing an empty string truncates the file
        } catch (IOException excep) {
            excep.printStackTrace();
        }
    }
    
    

        

    
    

    private void showTable(ArrayList<Student> stulist)
    {

        model.setRowCount(0);

        for (Student stu : stulist)
        {
            addToTable(stu);
        }
    }

    private void addToTable(Student stu)
    {
        String[] name = stu.getName().split(" ");
        String[] item = {name[0],name[1],""+ stu.getId()};
        model.addRow(item);        

    }

    public void addStudent(Student stu)
    {
        stulist.add(stu);
        addToTable(stu);

    }

    private ArrayList<Student> loadPersons(String stufile) {
        Scanner pscan = null;
        ArrayList<Student> stulist = new ArrayList<Student>();
        try {
            pscan = new Scanner(new File(stufile));
            while (pscan.hasNext()) {
                String[] nextLine = pscan.nextLine().split(" ");
                String name = nextLine[0] + " " + nextLine[1];
                int id = Integer.parseInt(nextLine[2]);
                Student stu = new Student(name, id);
                stulist.add(stu);
            }
            pscan.close();
        } catch (IOException e) {
        }
        return stulist;
    }

    
    

}
