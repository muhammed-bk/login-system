package loginsystem;
import loginsystem.Database;
import loginsystem.RegisterPage;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.net.URI;
import java.awt.Desktop;
public class WelcomePage {
    public WelcomePage() {
        JFrame welcomeFrame = new JFrame("Welcome");
        welcomeFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        welcomeFrame.setSize(500, 200);
        welcomeFrame.getContentPane().setBackground(new Color(230, 245, 255));
        welcomeFrame.setLayout(null);

        JLabel greetingLabel = new JLabel("<html>Welcome! You have successfully logged in. <br><a href=''>here</a> to visit our university website</a></center></html>");
        greetingLabel.setForeground(Color.black);
        greetingLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        greetingLabel.setBounds(50, 30, 400, 60);
        greetingLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        greetingLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                    Desktop.getDesktop().browse(new URI("https://www.aydin.edu.tr/tr-tr/Pages/default.aspx"));
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
        welcomeFrame.add(greetingLabel);

        JButton closeButton = new JButton("Close");
        closeButton.setBounds(200, 110, 100, 30);
        closeButton.setFocusPainted(false);
        closeButton.setBackground(new Color(230, 245, 255));
       closeButton.addActionListener(e -> welcomeFrame.dispose());
        welcomeFrame.add(closeButton);

        welcomeFrame.setLocationRelativeTo(null);
        welcomeFrame.setVisible(true);
    }
}

