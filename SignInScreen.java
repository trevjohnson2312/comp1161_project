import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class SignInScreen extends JPanel {

    JTextField IDField = new JTextField(5);
        JPasswordField passwordField = new JPasswordField(5);
        JButton submitButton = new JButton("Submit");
        JLabel idnumber = new JLabel("User ID: ");
        JLabel password = new JLabel("Password: ");
        JLabel enter = new JLabel("Sign In Screen");
        JLabel succLabel = new JLabel("Sign in successful!");

    public SignInScreen(JFrame first, JPanel menu, JButton back2, JPanel suis){
        

        idnumber.setBounds(150,200,75,25);
        idnumber.setFont(new Font("Cooper Black",Font.PLAIN,13));
        password.setBounds(150,250,75,25);
        password.setFont(new Font("Cooper Black",Font.PLAIN,13));

        IDField.setBounds(225,200,200,25);
        IDField.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        passwordField.setBounds(225,250,200,25);
        passwordField.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));

        submitButton.setBounds(150,300,275,25);
        submitButton.setFont(new Font("Cooper Black",Font.PLAIN,14));
        submitButton.setBackground(Color.decode("#F3EDE4"));
        submitButton.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        enter.setBounds(225,20,200,25);
        enter.setFont(new Font("Cooper Black",Font.PLAIN,26));
        succLabel.setBounds(150,350,200,25);

        
        add(idnumber);
        add(password);
        add(IDField);
        add(passwordField);
        add(submitButton);
        add(enter);
        setSize(420,350);
        setBounds(100,0,620,720);
        setLayout(null);
        setVisible(true);


        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    BufferedReader reader = new BufferedReader(new FileReader("userInfo.txt"));
                    String line;
                    boolean found = false;
                    String targetID = "ID Number: " + IDField.getText();
                    String targetPassword = "Password: " + new String(passwordField.getPassword());
                    String userRole = "";
                    while ((line = reader.readLine()) != null) {
                        if (line.startsWith(targetID)) {
                            String passwordLine = reader.readLine();
                            if (passwordLine != null && passwordLine.startsWith(targetPassword)) {
                                while ((line = reader.readLine()) != null) {
                                    if (line.startsWith("Role: ")) {
                                        userRole = line.substring("Role: ".length()).trim();
                                        break;
                                    }
                                }
                                found = true;
                                break;
                            }
                        }
                    }
                    reader.close();
                    if (found) {
                        succLabel.setVisible(true);

                        /*if ("Student".equals(userRole)) {

                            
                             SwingUtilities.invokeLater(() -> {
                                add(succLabel);
                                revalidate();
                                repaint();
                            });
                        
                            new Thread(() -> {
                                try {
                                    Thread.sleep(1000); // 1000 milliseconds = 1 seconds
                                    studentMenu Studentmenu = new studentMenu();
                                    SwingUtilities.invokeLater(() -> {
                                        first.add(Studentmenu);
                                        first.remove(SignInScreen.this);
                                        first.revalidate();
                                        first.repaint();
                                    });
                                } catch (InterruptedException ex) {
                                    ex.printStackTrace();
                                }
                            }).start();
                             


                        }*/ 
                        
                        
                        if ("Student".equals(userRole)) {
                            JFrame studentFrame = new JFrame("Student Menu");
                            studentFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
                            studentMenu Studentmenu = new studentMenu();
                            studentFrame.add(Studentmenu);
                            studentFrame.pack();
                            studentFrame.setVisible(true);
                        }else if ("Teacher".equals(userRole)) {
                            SwingUtilities.invokeLater(() -> {
                                add(succLabel);
                                revalidate();
                                repaint();
                            });
                        
                            new Thread(() -> {
                                try {
                                    Thread.sleep(1000); // 1000 milliseconds = 1 seconds
                                    TeacherMenu teacherMenu = new TeacherMenu(first, suis);
                                    SwingUtilities.invokeLater(() -> {
                                        first.add(teacherMenu);
                                        first.remove(SignInScreen.this);
                                        first.revalidate();
                                        first.repaint();

                                        reset();

                                        menu.remove(back2);
                                        menu.revalidate();
                                        menu.repaint();
                                    });
                                } catch (InterruptedException ex) {
                                    ex.printStackTrace();
                                }
                            }).start();
                        }
                        
                    } else {
                        JOptionPane.showMessageDialog(null, "Invalid ID number or password.");
                    }
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }

        
        });
    }

    public void reset() {
        // Clear input fields
        IDField.setText("");
        passwordField.setText("");

        // Reset labels
        succLabel.setVisible(false);

        // Revalidate and repaint to reflect changes
        revalidate();
        repaint();
    } 
    
}
    
