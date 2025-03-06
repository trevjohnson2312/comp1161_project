import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;


public class DayLabel extends JLabel{
    public DayLabel(String text, Color background, Color foreground, boolean btn)
    {
        setText(text);
        setHorizontalAlignment(JLabel.CENTER);
        setFont(new Font("Big Caslon", Font.PLAIN, 20));
        setOpaque(true);
        setBackground(background);
        setForeground(Color.black);
        setCursor(new Cursor(Cursor.HAND_CURSOR));

        if(btn) setCursor(new Cursor(Cursor.HAND_CURSOR));


    }
    
}
