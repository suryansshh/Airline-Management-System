package airlinemanagementsystem;
import javax.swing.*;

import javax.swing.text.AbstractDocument;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;
import javax.swing.text.AttributeSet;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.SQLException;

public class AddCustomer extends JFrame implements ActionListener {

    // Declare text fields as instance variables
    private JTextField nameField;
    private JTextField nationalityField;
    private JTextField aadhaarField;
    private JTextField addressField;
    private JTextField phoneField;
    private JRadioButton male;
    private JRadioButton female;

    public AddCustomer() {
        getContentPane().setBackground(Color.white);
        setLayout(null);

        JLabel heading = new JLabel("ADD CUSTOMER DETAILS");
        heading.setFont(new Font("Tahoma", Font.PLAIN, 32));
        heading.setForeground(Color.blue);
        heading.setBounds(150, 20, 500, 35); // Adjusted for centering
        add(heading);

        // Name Label and Text Field
        JLabel nameLabel = new JLabel("Name:");
        nameLabel.setBounds(50, 80, 150, 25);
        nameLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
        add(nameLabel);

        nameField = new JTextField();
        nameField.setBounds(250, 80, 200, 25);
        add(nameField);

        // Nationality Label and Text Field
        JLabel nationalityLabel = new JLabel("Nationality:");
        nationalityLabel.setBounds(50, 120, 150, 25);
        nationalityLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
        add(nationalityLabel);

        nationalityField = new JTextField();
        nationalityField.setBounds(250, 120, 200, 25);
        add(nationalityField);

        // Aadhaar Number Label and Text Field
        JLabel aadhaarLabel = new JLabel("Aadhaar Number:");
        aadhaarLabel.setBounds(50, 160, 150, 25);
        aadhaarLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
        add(aadhaarLabel);

        aadhaarField = new JTextField();
        aadhaarField.setBounds(250, 160, 200, 25);
        add(aadhaarField);

        // Address Label and Text Field
        JLabel addressLabel = new JLabel("Address:");
        addressLabel.setBounds(50, 200, 150, 25);
        addressLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
        add(addressLabel);

        addressField = new JTextField();
        addressField.setBounds(250, 200, 200, 75);
        add(addressField);

        // Phone Number Label and Text Field
        JLabel phoneLabel = new JLabel("Phone Number:");
        phoneLabel.setBounds(50, 290, 150, 25);
        phoneLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
        add(phoneLabel);

        phoneField = new JTextField("+91");
        phoneField.setBounds(250, 290, 200, 25);

        // Limit to numbers and 10 digits after +91
        ((AbstractDocument) phoneField.getDocument()).setDocumentFilter(new DocumentFilter() {
            @Override
            public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
                StringBuilder newText = new StringBuilder();
                for (char c : text.toCharArray()) {
                    if (Character.isDigit(c)) {
                        newText.append(c);
                    }
                }

                if (phoneField.getText().length() - length + newText.length() <= 13) { // "+91" + 10 digits
                    super.replace(fb, offset, length, newText.toString(), attrs);
                }
            }
        });

        add(phoneField);

        // Gender Label and Radio Buttons
        JLabel genderLabel = new JLabel("Gender:");
        genderLabel.setBounds(50, 350, 150, 25);
        genderLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
        add(genderLabel);

        male = new JRadioButton("Male");
        male.setBounds(250, 350, 70, 25);
        male.setBackground(Color.white);
        add(male);

        female = new JRadioButton("Female");
        female.setBounds(330, 350, 90, 25);
        female.setBackground(Color.white);
        add(female);

        ButtonGroup genderGroup = new ButtonGroup();
        genderGroup.add(male);
        genderGroup.add(female);

        // Save Button
        RoundedButton save = new RoundedButton("SAVE");
        save.setBackground(Color.black);
        save.setForeground(Color.white);
        save.setBounds(250, 390, 200, 30); // Adjusted position
        save.addActionListener(this); // Add action listener
        add(save);

        // Load and position the image
        ImageIcon image = new ImageIcon(ClassLoader.getSystemResource("airlinemanagementsystem/icons/emp.png"));
        JLabel lbimage = new JLabel(image);
        lbimage.setBounds(420, 80, image.getIconWidth(), image.getIconHeight()); // Adjusted for proper fit
        add(lbimage);

        // Frame settings
        setSize(750, 500); // Increased size to fit content
        setLocation(300, 150);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        // Retrieve and process the data from the fields
        String name = nameField.getText();
        String nationality = nationalityField.getText();
        String aadhaar = aadhaarField.getText();
        String address = addressField.getText();
        String phone = phoneField.getText();
        String gender = male.isSelected() ? "Male" : female.isSelected() ? "Female" : "Not Specified";

        // Process the data (e.g., save to a database, display a confirmation message)
        JOptionPane.showMessageDialog(this, """
                                            Customer details saved:
                                            Name: """ + name + "\n"
                + "Nationality: " + nationality + "\n"
                + "Aadhaar Number: " + aadhaar + "\n"
                + "Address: " + address + "\n"
                + "Phone Number: " + phone + "\n"
                + "Gender: " + gender);
        try {
            try ( // Database connection setup
                    Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/airlinemanagementsystem", "root", "8943")) {
                Statement stmt = conn.createStatement();
                
                // SQL query
                String query = "INSERT INTO passenger (name, nationality, aadhaar_number, address, phone_number, gender) VALUES ('"
                        + name + "', '"
                        + nationality + "', '"
                        + aadhaar + "', '"
                        + address + "', '"
                        + phone + "', '"
                        + gender + "')";
                
                // Execute query
                stmt.executeUpdate(query);
                
                // Close connection
                stmt.close();
                setVisible(false);
            }

            JOptionPane.showMessageDialog(null, "Customer Details Added Successfully");
            setVisible(false);
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error occurred while adding customer details: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(AddCustomer::new);
    }

    // Custom RoundedButton class
    static class RoundedButton extends JButton {

        private static final int ARC_WIDTH = 20;
        private static final int ARC_HEIGHT = 20;

        public RoundedButton(String text) {
            super(text);
            setFocusPainted(false);
            setContentAreaFilled(false);
            setBorderPainted(false);
        }

        @Override
        protected void paintComponent(Graphics g) {
            if (getModel().isArmed()) {
                g.setColor(Color.lightGray);
            } else {
                g.setColor(getBackground());
            }
            Graphics2D g2d = (Graphics2D) g;
            g2d.fillRoundRect(0, 0, getWidth(), getHeight(), ARC_WIDTH, ARC_HEIGHT);
            super.paintComponent(g);
        }

        @Override
        protected void paintBorder(Graphics g) {
            Graphics2D g2d = (Graphics2D) g;
            g2d.setColor(Color.black);
            g2d.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, ARC_WIDTH, ARC_HEIGHT);
        }
    }
}
