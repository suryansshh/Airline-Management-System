package airlinemanagementsystem;

import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.awt.event.*;

public class Login extends JFrame implements ActionListener {

    // Declare buttons as class-level variables
    private JButton submit;
    private JButton reset;
    private JButton close;

    // Declare other components if needed
    private JTextField usernameField;
    private JPasswordField passwordField;

    public Login() {
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);

        // Username label and text field
        JLabel username = new JLabel("Username:");
        username.setBounds(20, 20, 100, 20);
        add(username);

        usernameField = new JTextField();
        usernameField.setBounds(130, 20, 200, 20);
        add(usernameField);

        // Password label and password field
        JLabel password = new JLabel("Password:");
        password.setBounds(20, 60, 100, 20);
        add(password);

        passwordField = new JPasswordField();
        passwordField.setBounds(130, 60, 200, 20);
        add(passwordField);

        // Initialize and add Submit button
        submit = new JButton("Submit");
        submit.setBounds(130, 100, 90, 25);
        submit.addActionListener(this);
        add(submit);

        // Initialize and add Reset button
        reset = new JButton("Reset");
        reset.setBounds(240, 100, 90, 25);
        reset.addActionListener(this);
        add(reset);

        // Initialize and add Close button
        close = new JButton("Close");
        close.setBounds(185, 140, 90, 25);
        close.addActionListener(this);
        add(close);

        // Frame settings
        setSize(400, 250);
        setLocation(600, 250);
        setVisible(true);
    }

    public static void main(String[] args) {
        new Login();
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        // Check which button was pressed
        if (ae.getSource() == submit) {
            // Handle submit button action
            String username = usernameField.getText();
            String password = passwordField.getText(); // Use getPassword() for JPasswordField

            try {
                Conn c = new Conn();
                String query = "select * from login where username = '" + username + "'and password = '" + password + "'";

                ResultSet rs = c.s.executeQuery(query);
                if (rs.next()) {
                    new Home();
                    setVisible(false); 
                } else {
                    JOptionPane.showMessageDialog(null , "invalid username or password");
                    setVisible(false);
                }
            } catch (Exception e) {
                e.printStackTrace(); // Print stack trace for debugging
            }
        } else if (ae.getSource() == reset) {
            // Handle reset button action
            usernameField.setText("");
            passwordField.setText("");
        } else if (ae.getSource() == close) {
            // Handle close button action
            System.exit(0);
        }
    }
}
