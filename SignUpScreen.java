import javax.swing.*;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.io.FileNotFoundException;


public class SignUpScreen extends JPanel {

    JTextField IDField = new JTextField(15);
    JPasswordField passwordField = new JPasswordField(15);
    JPasswordField confirmPasswordField = new JPasswordField(15);
    JTextField nameField = new JTextField(15);
    JTextField ageField = new JTextField(15);
    JRadioButton teacherRadio = new JRadioButton("Teacher");
    JRadioButton studentRadio = new JRadioButton("Student");
    JComboBox genderComboBox = new JComboBox<>(new String[]{"Choose","Male", "Female", "Other"});
    JButton submitButton = new JButton("Submit");

    public SignUpScreen(JFrame first, JPanel suis) {

        //IDField = new JTextField(15);
        //passwordField = new JPasswordField(15);
        //confirmPasswordField = new JPasswordField(15);
        //nameField = new JTextField(15);
        //ageField = new JTextField(15);
        //teacherRadio = new JRadioButton("Teacher");
        //studentRadio = new JRadioButton("Student");
        //genderComboBox = new JComboBox<>(new String[]{"Choose","Male", "Female", "Other"});
        //submitButton = new JButton("Submit");
        JLabel name = new JLabel("Name: ");
        JLabel username = new JLabel("ID Number: ");
        JLabel age = new JLabel("Age: ");
        JLabel role = new JLabel("Role: ");
        JLabel gender = new JLabel("Gender: ");
        JLabel pword = new JLabel("Password: ");
        JLabel cpword = new JLabel("Confirm Password: ");
        JLabel enter = new JLabel("Sign Up Screen");

        name.setBounds(75,75,75,25);
        username.setBounds(75,125,100,25);
        age.setBounds(75,175,75,25);
        role.setBounds(75,225,75,25);
        gender.setBounds(75,300,75,25);
        pword.setBounds(75,350,75,25);
        cpword.setBounds(75,400,140,25);

        name.setFont(new Font("Cooper Black",Font.PLAIN,13));
        username.setFont(new Font("Cooper Black",Font.PLAIN,13));
        age.setFont(new Font("Cooper Black",Font.PLAIN,13));
        role.setFont(new Font("Cooper Black",Font.PLAIN,13));
        gender.setFont(new Font("Cooper Black",Font.PLAIN,13));
        pword.setFont(new Font("Cooper Black",Font.PLAIN,13));
        cpword.setFont(new Font("Cooper Black",Font.PLAIN,13));
        submitButton.setFont(new Font("Cooper Black",Font.PLAIN,14));
        teacherRadio.setFont(new Font("Cooper Black",Font.PLAIN,13));
        studentRadio.setFont(new Font("Cooper Black",Font.PLAIN,13));

        nameField.setBounds(150,75,200,25);
        IDField.setBounds(160,125,200,25);
        ageField.setBounds(150,175,200,25);
        teacherRadio.setBounds(150,225,200,25);
        studentRadio.setBounds(150,250,200,25);
        genderComboBox.setBounds(150,300,200,25);
        passwordField.setBounds(150,350,200,25);
        confirmPasswordField.setBounds(210,400,150,25);
        submitButton.setBounds(75,450,275,25);

        submitButton.setBackground(Color.decode("#F3EDE4"));

        nameField.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        IDField.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        ageField.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        genderComboBox.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        passwordField.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        confirmPasswordField.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        submitButton.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));

        enter.setBounds(150,10,200,25);
        enter.setFont(new Font("Cooper Black",Font.PLAIN,26));

        add(name);
        add(username);
        add(age);
        add(role);
        add(gender);
        add(pword);
        add(cpword);
        add(IDField);
        add(passwordField);
        add(confirmPasswordField);
        add(nameField);
        add(ageField);
        add(teacherRadio);
        add(studentRadio);
        add(genderComboBox);
        add(submitButton);
        setBounds(100,0,620,720);
        setLayout(null);
        setVisible(true);
        add(enter);

        ButtonGroup group = new ButtonGroup();
        group.add(teacherRadio);
        group.add(studentRadio);


        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!new String(passwordField.getPassword()).equals(new String(confirmPasswordField.getPassword()))) {
                    JOptionPane.showMessageDialog(null, "Passwords do not match.");
                } else if (IDField.getText().isEmpty() || new String(passwordField.getPassword()).isEmpty()) {
                    JOptionPane.showMessageDialog(null, "All fields must be filled.");
                    return;
                }
                 else {
                    // Check if the ID number already exists
                    try {
                        File file = new File("userInfo.txt");
                        Scanner scanner = new Scanner(file);
                        boolean idExists = false;

                        while (scanner.hasNextLine()) {
                            String line = scanner.nextLine();
                            if (line.startsWith("ID Number: ")) {
                                String existingID = line.substring("ID Number: ".length()).trim();
                                if (existingID.equals(IDField.getText())) {
                                    idExists = true;
                                    break;
                                }
                            }
                        }
                        scanner.close();

                        if (idExists) {
                            JOptionPane.showMessageDialog(null, "ID number already exists.");
                            return;
                        }
                    } catch (FileNotFoundException ex) {
                        ex.printStackTrace();
                    }

                    // Proceed with the sign-up process if the ID number does not exist
                    try {
                        File file = new File("userInfo.txt");
                        FileWriter fw = new FileWriter(file.getAbsoluteFile(), true);
                        BufferedWriter bw = new BufferedWriter(fw);
                        bw.write("ID Number: " + IDField.getText() + "\n");
                        bw.write("Password: " + new String(passwordField.getPassword()) + "\n");
                        bw.write("Name: " + nameField.getText() + "\n");
                        bw.write("Age: " + ageField.getText() + "\n");
                        bw.write("Gender: " + genderComboBox.getSelectedItem() + "\n");
                        bw.write("Role: " + (teacherRadio.isSelected() ? "Teacher" : "Student") + "\n");
                        bw.write("---------------------------------\n");
                        bw.close();
                        first.remove(SignUpScreen.this);
                        first.add(suis);
                        first.revalidate();
                        first.repaint();
                        reset();
                        JOptionPane.showMessageDialog(null, "Sign up successful!");
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });
    }

    public void reset() {
        // Clear input fields
        nameField.setText("");
        IDField.setText("");
        ageField.setText("");
        teacherRadio.setSelected(false);
        studentRadio.setSelected(false);
        genderComboBox.setSelectedIndex(0);
        passwordField.setText("");
        confirmPasswordField.setText("");
        revalidate();
        repaint();
    }
    
}



