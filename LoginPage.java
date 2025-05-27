package loginsystem;
import loginsystem.Database;
import loginsystem.RegisterPage;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;


public class LoginPage implements ActionListener {

    private JFrame frame;
    private JButton loginButton;
    private JButton resetButton;
    private JTextField userIDField;
    private JPasswordField userPasswordField;
    private JLabel userIDLabel;
    private JLabel userPasswordLabel;
    private JLabel messageLabel;


    private HashMap<String, String> logininfo;


    public LoginPage() {
logininfo = Database.loadLoginInfo();

        // Set the look and feel to the system look and feel
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(420, 420);
        frame.setLayout(null);
        JLabel title = new JLabel("Log In");
        title.setBounds(100, 10, 200, 30);
        title.setFont(new Font("Arial", Font.BOLD, 20));
        title.setHorizontalAlignment(SwingConstants.CENTER);
        frame.add(title);
        frame.getContentPane().setBackground(new Color(230, 245, 255));

        userIDLabel = new JLabel("Username:");
        userIDLabel.setForeground(Color.black);
        userIDLabel.setBounds(50, 60, 100, 25);
        frame.add(userIDLabel);

        userPasswordLabel = new JLabel("Password:");
        userPasswordLabel.setForeground(Color.black);
        userPasswordLabel.setBounds(50, 100, 100, 25);
        frame.add(userPasswordLabel);

        JButton togglePasswordButton = new JButton("\uD83D\uDC41\uFE0F");
togglePasswordButton.setBounds(270, 100, 25, 25);
togglePasswordButton.setMargin(new Insets(0, 0, 0, 0));
togglePasswordButton.setFocusable(false);
togglePasswordButton.setBorder(null);
togglePasswordButton.setBackground(Color.white);
togglePasswordButton.addActionListener(e -> {
    if (userPasswordField.getEchoChar() == (char) 0) {
        userPasswordField.setEchoChar('•');
    }else {
        userPasswordField.setEchoChar((char) 0);
    }
});
frame.add(togglePasswordButton);

        userIDField = new JTextField();
        userIDField.setBounds(150, 60, 150, 25);
        frame.add(userIDField);

        userPasswordField = new JPasswordField();
        userPasswordField.setBounds(150, 100, 150, 25);
        userPasswordField.setEchoChar('•');
        frame.add(userPasswordField);

        loginButton = new JButton("Login");
        loginButton.setBounds(125, 140, 150, 30);
        loginButton.setFocusable(false);
        loginButton.addActionListener(this);
        frame.add(loginButton);

        resetButton = new JButton("Reset");
        resetButton.setBounds(125, 170, 150, 30);
        resetButton.setFocusable(false);
        resetButton.addActionListener(this);
        frame.add(resetButton);

        JButton goToRegisterButton = new JButton("Sign up");
        goToRegisterButton.setBounds(125, 200, 150, 25);
        goToRegisterButton.setFocusable(false);
        goToRegisterButton.addActionListener(e -> {
            new RegisterPage();
            frame.dispose();
        });
        frame.add(goToRegisterButton);
        messageLabel = new JLabel();
        messageLabel.setForeground(Color.WHITE);
        messageLabel.setBounds(50, 230, 300, 40);
        frame.add(messageLabel);



        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == resetButton) {

            userIDField.setText("");
            userPasswordField.setText("");
            // Clear the message label
            messageLabel.setText("");
        }

        // If the user clicked the "Login" button
        if (e.getSource() == loginButton) {
            logininfo = Database.loadLoginInfo();
            String userID = userIDField.getText();
            String password = String.valueOf(userPasswordField.getPassword());

            if (logininfo.containsKey(userID)) {
                if (logininfo.get(userID).equals(password)) {
                    if(!isValidPassword(password)){
                     JOptionPane.showMessageDialog(frame,"Password must  be at least 8 characters long and include:\n One uppercase letter\n  One lowercase letter\n  One number\n  One special character (@#$%^&+=!)\n",
                              "Invalid Password",
                              JOptionPane.WARNING_MESSAGE);
                      return;
                    }
                    messageLabel.setForeground(Color.green);
                    messageLabel.setText("Login Successful");
                    new WelcomePage();
                    frame.dispose();
                } else {
                    messageLabel.setForeground(Color.red);
                    messageLabel.setText("Invalid Password");
                }
            } else {
                // Set the message label to red text and display "Invalid User ID"
                messageLabel.setForeground(Color.red);
                messageLabel.setText("Invalid User ID");
            }
        }
    }
    private boolean isValidPassword(String password){
        return password.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%^&+=!]).{8,}$");
    }

}
