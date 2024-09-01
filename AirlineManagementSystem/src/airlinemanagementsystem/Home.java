package airlinemanagementsystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Home extends JFrame implements ActionListener {

    public Home() {
        // Set layout to null for absolute positioning
        setLayout(null);

        // Load and set background image
        ImageIcon backgroundImage = new ImageIcon(ClassLoader.getSystemResource("airlinemanagementsystem/icons/front.jpg"));
        JLabel imageLabel = new JLabel(backgroundImage);
        imageLabel.setBounds(0, 0, 1600, 800);
        add(imageLabel);

        // Create and configure heading label
        JLabel headingLabel = new JLabel("Air India welcomes you !");
        headingLabel.setBounds(500, 20, 1000, 40);
        headingLabel.setForeground(Color.yellow);
        headingLabel.setFont(new Font("Tahoma", Font.BOLD, 36));
        imageLabel.add(headingLabel);

        // Create and set up the menu bar
        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        // Create and configure 'Details' menu
        JMenu detailsMenu = new JMenu("Details");
        menuBar.add(detailsMenu);

        // Create and add menu items to 'Details' menu
        JMenuItem flightDetailsItem = new JMenuItem("Flight Details");
        flightDetailsItem.addActionListener(this);
        detailsMenu.add(flightDetailsItem);

        JMenuItem customerDetailsItem = new JMenuItem("Add Customer Details");
        customerDetailsItem.addActionListener(this);
        detailsMenu.add(customerDetailsItem);

        JMenuItem bookFlightItem = new JMenuItem("Book Flight");
        detailsMenu.add(bookFlightItem);

        JMenuItem journeyDetailsItem = new JMenuItem("Journey Details");
        journeyDetailsItem.addActionListener(this);
        detailsMenu.add(journeyDetailsItem);

        JMenuItem ticketCancellationItem = new JMenuItem("Cancel Ticket");
        ticketCancellationItem.addActionListener(this);
        detailsMenu.add(ticketCancellationItem);

        // Create and configure 'Ticket' menu
        JMenu ticketMenu = new JMenu("Ticket");
        menuBar.add(ticketMenu);

        // Create and add menu item to 'Ticket' menu
        JMenuItem boardingPassItem = new JMenuItem("Boarding Pass Details");
        boardingPassItem.addActionListener(this);
        ticketMenu.add(boardingPassItem);

        // Frame settings
        setExtendedState(JFrame.MAXIMIZED_BOTH); // Maximize the frame
        setLocation(600, 250); // Set the frame's location
        setVisible(true); // Make the frame visible
    }

    public static void main(String[] args) {
        new Home(); // Create and display the Home frame
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        // Handle action events here if needed
        String text = ae.getActionCommand();
        if(text.equals("Add Customer Details")){
            new AddCustomer();
        } else if(text.equals("Flight Details")){
            new FlightInfo();
        } else if(text.equals("Journey Details")){
            new JourneyDetail();
        }else if(text.equals("Cancel Ticket")){
        new Cancel();
        }else if(text.equals("Boarding Pass Details")){
            new BoardingPass();
        }
    }
}
