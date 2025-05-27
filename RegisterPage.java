package loginsystem;
import java.awt.Color;
import loginsystem.Database;
import loginsystem.RegisterPage;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.SQLException;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class RegisterPage extends JFrame implements ActionListener {
    JTextField userField;
    JPasswordField passfield;
    JTextField emailField;
    JButton registerButton;
    JLabel message;
    public RegisterPage() {
        this.setTitle("Sign Up");
        this.setSize(400, 300);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);
        this.getContentPane().setBackground(new Color(230, 245, 255));

        JLabel title = new JLabel("Sign Up ");
        title.setBounds(100, 10, 200, 30);
        title.setFont(new Font("Arial", Font.BOLD, 20));
        title.setHorizontalAlignment(SwingConstants.CENTER);
        this.add(title);

        JLabel userLabel = new JLabel("Username");
        userLabel.setBounds(50, 60, 100, 25);
        this.add(userLabel);

        userField = new JTextField();
        userField.setBounds(150,60,150,25);
        this.add(userField);

        JLabel passLabel = new JLabel("Password");
        passLabel.setBounds(50, 140, 100, 25);
        this.add(passLabel);

        passfield = new JPasswordField();
        passfield.setBounds(150,140,150,25);
        this.add(passfield);

        JButton togglePasswordButton = new JButton("\uD83D\uDC41\uFE0F");
        togglePasswordButton.setBounds(295, 140, 25, 25);
        togglePasswordButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        togglePasswordButton.setFont(new Font("Apple Color Emoji", Font.PLAIN , 18));
        togglePasswordButton.setFocusPainted(false);
        togglePasswordButton.setBackground(new Color(7, 6, 6));
        togglePasswordButton.setOpaque(false);

        togglePasswordButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (passfield.getEchoChar() == (char) 0) {
                    passfield.setEchoChar('•');
                } else {
                    passfield.setEchoChar((char) 0);

                }
            }
        });
        this.add(togglePasswordButton);

        JLabel emailLabel = new JLabel("Email");
        emailLabel.setBounds(50, 100, 100, 25);
        this.add(emailLabel);
        emailField = new JTextField();
        emailField.setBounds(150,100,150,25);
        this.add(emailField);

        registerButton = new JButton("Sign up ");
        registerButton.setBounds(125,210,150,30);
        registerButton.addActionListener(this);
        this.add(registerButton);

        message = new JLabel("Please enter your username and password");
        message.setBounds(50,180,300,25);
        registerButton.setBackground(Color.green);
        registerButton.setForeground(Color.black);
        registerButton.setFocusPainted(false);

        this.add(message);
        JButton goToLoginButton = new JButton("log-in");
        goToLoginButton.setBounds(125,250,150,25);
        goToLoginButton.addActionListener(e -> {
            new LoginPage();
            this.dispose();
        });
        this.add(goToLoginButton);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == registerButton) {
            String username = userField.getText().trim();
            String password = new String(passfield.getPassword()).trim();
            if (username.isEmpty()|| password.isEmpty()) {
                message.setForeground(Color.RED);
                message.setText("Username or password cant be empty");
                return;
            }
            if (!isValidPassword(password)){
                JOptionPane.showMessageDialog(this,"Password must  be at least 8 characters long and include:\n One uppercase letter\n  One lowercase letter\n  One number\n  One special character (@#$%^&+=!)\n",
                        "Invalid Password",
                        JOptionPane.WARNING_MESSAGE);
                return;
            }
            try(Connection conn = Database.connect();
                PreparedStatement stmt = conn.prepareStatement("INSERT INTO muhammed_users(username, password)VALUES (?,?)")){
                stmt.setString(1,username);
                stmt.setString(2,password);
                stmt.executeUpdate();
                message.setForeground(Color.green);
                message.setText("Account created successfully");
                System.out.println("user registered with email: " + emailField.getText());}

            catch (SQLException ex){
                message.setForeground(Color.RED);
                message.setText("username already exists");
                ex.printStackTrace();
            }

        }
    }
    private boolean isValidPassword(String password){
        return password.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%^&+=!]).{8,}$");
    }
}
