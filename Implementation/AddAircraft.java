import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.*;

public class AddAircraft extends JFrame{
    private JTextField tfName;
    private JTextField tfWeight;
    private JTextField tfCapacity;
    private JButton btnAdd;
    private JButton btnCancel;
    private JButton btnGoBack;
    private JPanel addAircraftPanel;
    User user;

    public AddAircraft(User user) {
        this.user = user;
    setTitle("Add aircraft");
    setContentPane(addAircraftPanel);
    setPreferredSize(new Dimension(600, 600));
    pack();
    setLocationRelativeTo(null);
    setVisible(true);
    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String aircraftName = tfName.getText();
                int weight = Integer.parseInt(tfWeight.getText());
                int capacity = Integer.parseInt(tfCapacity.getText());
                int userID = user.ID; // Assuming User class has a getID() method to retrieve the user ID

                // Perform database insertion logic here
                String URL = "jdbc:sqlserver://192.168.1.10:1433;192.168.1.10=library;encrypt=true;trustservercertificate=true";
                String Username = "admin30";
                String Password = "20211054";
                Connection connection = null;
                String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";

                try {
                    Class.forName(driver);
                    connection = DriverManager.getConnection(URL, Username, Password);
                    String sql = "INSERT INTO Aircraft (name, maximunWeight, seatingCapacity, adminID) VALUES (?, ?, ?, ?)";
                    PreparedStatement preparedStatement = connection.prepareStatement(sql);
                    preparedStatement.setString(1, aircraftName);
                    preparedStatement.setInt(2, weight);
                    preparedStatement.setInt(3, capacity);
                    preparedStatement.setInt(4, userID);

                    int rowsAffected = preparedStatement.executeUpdate();
                    if (rowsAffected > 0) {
                        JOptionPane.showMessageDialog(addAircraftPanel, "Aircraft added successfully.");
//                        dispose();
                    } else {
                        JOptionPane.showMessageDialog(addAircraftPanel, "Failed to add aircraft. Please try again.");
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

        btnCancel.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            dispose();
        }
    });

        btnGoBack.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            AdminMenu menu = new AdminMenu(user);
            menu.setVisible(true);
            dispose();
        }
    });
}
}
