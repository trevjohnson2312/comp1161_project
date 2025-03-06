import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class Main{
    public static void main(String[] args)
    {
        JPanel menu = new JPanel();
        JButton back1 = new JButton("Return");
        JButton back2 = new JButton("Return");
        
        menu.setBackground(Color.decode("#D2C2A2"));
        menu.setBounds(0,0,100,720);

        back1.setBackground(Color.decode("#F3EDE4"));
        back1.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        back1.setFont(new Font("Cooper Black",Font.PLAIN,16));
        back2.setBackground(Color.decode("#F3EDE4"));
        back2.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        back2.setFont(new Font("Cooper Black",Font.PLAIN,16));

        JPanel suis = new JPanel();
        JLabel greet = new JLabel("WELCOME");
        JButton sib = new JButton("Sign In");
        JButton sub = new JButton("Sign Up");
        sib.setBounds(200,200,85,35);
        sub.setBounds(300,200,85,35);
        sib.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        sub.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        greet.setBounds(150,50,400,150);
        greet.setFont(new Font("Cooper Black",Font.PLAIN,55));
        suis.setBackground(Color.WHITE);
        sib.setBackground(Color.decode("#F3EDE4"));
        sub.setBackground(Color.decode("#F3EDE4"));
        suis.setBounds(100,0,620,720);
        suis.setLayout(null);
        suis.add(greet);
        suis.add(sib);
        suis.add(sub);
        
        JFrame first = new JFrame();
        first.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        first.setVisible(true);
        first.setLayout(null);
        first.setSize(720,720);
        first.add(menu);
        first.add(suis);

        SignUpScreen signUpScreen = new SignUpScreen(first, suis);
        SignInScreen signInScreen = new SignInScreen(first, menu, back2, suis);

        sub.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                first.remove(suis);
                first.add(signUpScreen);
                menu.add(back1);
                first.revalidate();
                first.repaint();
            }
        });

        sib.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                first.remove(suis);
                first.add(signInScreen);
                menu.add(back2);
                first.revalidate();
                first.repaint();
            }
        });

        back1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){

                first.remove(signUpScreen);
                first.add(suis);
                menu.remove(back1);
                first.revalidate();
                first.repaint();

            }
        });

        back2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){

                first.remove(signInScreen);
                first.add(suis);
                menu.remove(back2);
                first.revalidate();
                first.repaint();

            }
        });
    }
}