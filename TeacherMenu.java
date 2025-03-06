import javax.swing.*;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TeacherMenu extends JPanel {
    private JButton cmdAddExam;
    private JButton cmdAddAssignment;
    private JButton cmdAddQuiz;
    private JButton cmdClose;
    private JButton cmdViewStudents;
    private JLabel cmdLabel;
    JFrame first;
    JPanel suis;

    public TeacherMenu(JFrame first, JPanel suis) {

        this.first = first;
        this.suis = suis;
        
        setBounds(100,0,620,720);
        setLayout(null);

        cmdLabel = new JLabel("Teacher Menu");
        cmdLabel.setBounds(175,50,1000,25);
        cmdLabel.setFont(new Font("Cooper Black",Font.PLAIN,40));

        cmdAddExam = new JButton("Add Exam");
        cmdAddExam.setBounds(100, 125, 100, 25);
        cmdAddExam.setBackground(Color.decode("#F3EDE4"));
        cmdAddExam.setFont(new Font("Cooper Black",Font.PLAIN,13));
        cmdAddExam.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        
        cmdAddAssignment = new JButton("Add Assignment");
        cmdAddAssignment.setBounds(100, 175, 160, 25);
        cmdAddAssignment.setBackground(Color.decode("#F3EDE4"));
        cmdAddAssignment.setFont(new Font("Cooper Black",Font.PLAIN,13));
        cmdAddAssignment.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        
        cmdAddQuiz = new JButton("Add Quiz");
        cmdAddQuiz.setBounds(100, 225, 100, 25);
        cmdAddQuiz.setBackground(Color.decode("#F3EDE4"));
        cmdAddQuiz.setFont(new Font("Cooper Black",Font.PLAIN,13));
        cmdAddQuiz.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        
        cmdViewStudents = new JButton("View Students");
        cmdViewStudents.setBounds(100, 275, 150, 25);
        cmdViewStudents.setBackground(Color.decode("#F3EDE4"));
        cmdViewStudents.setFont(new Font("Cooper Black",Font.PLAIN,13));
        cmdViewStudents.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        
        cmdClose = new JButton("Close");
        cmdClose.setBounds(100, 325, 150, 25);
        cmdClose.setBackground(Color.decode("#F3EDE4"));
        cmdClose.setFont(new Font("Cooper Black",Font.PLAIN,13));
        cmdClose.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));

        add(cmdLabel);
        add(cmdClose);
        add(cmdViewStudents);
        add(cmdAddQuiz);
        add(cmdAddAssignment);
        add(cmdAddExam);
        setVisible(true);

        cmdAddExam.addActionListener(new AddExamButtonListener());
        cmdAddAssignment.addActionListener(new AddAssignmentButtonListener());
        cmdAddQuiz.addActionListener(new AddQuizButtonListener());
        cmdClose.addActionListener(new CloseButtonListener());
        cmdViewStudents.addActionListener(new ViewStudentsButtonListener());
    }

    private class AddExamButtonListener implements ActionListener 
    {
        @Override
        public void actionPerformed(ActionEvent e) 
        {
            AddExam addExam = new AddExam(TeacherMenu.this, first);
            first.add(addExam);
            first.remove(TeacherMenu.this);
            first.revalidate();
            first.repaint();
        }
    }

    private class AddAssignmentButtonListener implements ActionListener 
    {
        @Override
        public void actionPerformed(ActionEvent e) 
        {
            AddAssignment addAssignment = new AddAssignment(TeacherMenu.this, first);
            first.add(addAssignment);
            first.remove(TeacherMenu.this);
            first.revalidate();
            first.repaint();
        }
    }

    private class AddQuizButtonListener implements ActionListener 
    {
        @Override
        public void actionPerformed(ActionEvent e) 
        {
            AddQuiz addQuiz = new AddQuiz(TeacherMenu.this, first);
            first.add(addQuiz);
            first.remove(TeacherMenu.this);
            first.revalidate();
            first.repaint();
        }
    }

    private class ViewStudentsButtonListener implements ActionListener 
    {
        @Override
        public void actionPerformed(ActionEvent e) 
        {
            ViewStudents viewStudents = new ViewStudents(TeacherMenu.this, first);
            first.add(viewStudents);
            first.remove(TeacherMenu.this);
            first.revalidate();
            first.repaint();
        }
    }


    private class CloseButtonListener implements ActionListener 
    {
        public void actionPerformed(ActionEvent e) 
        {
            first.remove(TeacherMenu.this);
            first.add(suis);
            first.revalidate();
            first.repaint();
        }
    }

}