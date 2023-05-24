import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.*;
public class AddFlight extends JFrame {
    private JTextField tfSource;
    private JTextField tfDestination;
    private JTextField tfSeats;
    private JTextField tfDepartureDate;
    private JTextField tfArrivalDate;
    private JButton addButton;
    private JButton cancelButton;
    private JPanel addFlightPanel;
    private JTextField tfAircraftID;
    private JButton backButton;

    User user;
    public AddFlight(User user) {
        this.user = user;
        setTitle("Add flight");
        setContentPane(addFlightPanel);
        setPreferredSize(new Dimension(600, 600));
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String source = tfSource.getText();
                String destination = tfDestination.getText();
                int seats = Integer.parseInt(tfSeats.getText());
                String departureDate = tfDepartureDate.getText();
                String arrivalDate = tfArrivalDate.getText();
                int aircraftID = Integer.parseInt(tfAircraftID.getText());
                int userID = user.ID;

                String URL = "jdbc:sqlserver://192.168.1.10:1433;192.168.1.10=library;encrypt=true;trustservercertificate=true";
                String Username = "admin30";
                String Password = "20211054";
                Connection connection = null;
                String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";

                try {
                    Class.forName(driver);
                    connection = DriverManager.getConnection(URL, Username, Password);
                    String sql = "INSERT INTO Flight (source, destination, availableSeats, departureDate, arrivalDate, aircraftID, adminID) VALUES (?, ?, ?, ?, ?, ?, ?)";
                    PreparedStatement preparedStatement = connection.prepareStatement(sql);
                    preparedStatement.setString(1, source);
                    preparedStatement.setString(2, destination);
                    preparedStatement.setInt(3, seats);
                    preparedStatement.setString(4, departureDate);
                    preparedStatement.setString(5, arrivalDate);
                    preparedStatement.setInt(6, aircraftID);
                    preparedStatement.setInt(7, userID);

                    int rowsAffected = preparedStatement.executeUpdate();
                    if (rowsAffected > 0) {
                        JOptionPane.showMessageDialog(addFlightPanel, "Flight added successfully.");
//                        dispose();
                    } else {
                        JOptionPane.showMessageDialog(addFlightPanel, "Failed to add flight. Please try again.");
                    }
                } catch (ClassNotFoundException ex) {
                    System.out.println("Driver class not found: " + ex.getMessage());
                } catch (SQLException ex) {
                    System.out.println("Database connection error: " + ex.getMessage());
                } finally {
                    if (connection != null) {
                        try {
                            connection.close();
                        } catch (SQLException ex) {
                            System.out.println("Failed to close the database connection: " + ex.getMessage());
                        }
                    }
                }
            }
        });

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AdminMenu menu = new AdminMenu(user);
                menu.setVisible(true);
                dispose();
            }
        });
    }

    public static void main(String[] args) {

    }
}