import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class updateAircraft extends JFrame{
    private JButton nameButton;
    private JButton maximumWeightButton;
    private JButton seatingCapacityButton;
    private JPanel updateAircraft;
    private JButton backButton;
    String updatedName;
    String AircraftID;
    String updatedWeight;
    String updatedCapacity;
    User user;

public updateAircraft(String AircraftID,User user) {
    this.user=user;
    setTitle("Update Aircraft");
    setContentPane(updateAircraft);
    setPreferredSize(new Dimension(600, 600));
    pack();
    setLocationRelativeTo(null);
    setVisible(true);
    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    this.AircraftID = AircraftID;
    nameButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            updatedName = JOptionPane.showInputDialog(updateAircraft, "Enter updated name:", "Update Name", JOptionPane.PLAIN_MESSAGE);
            updateName(updatedName);
        }
    });
    maximumWeightButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            updatedWeight = JOptionPane.showInputDialog(updateAircraft, "Enter updated max weight:", "Update Weight", JOptionPane.PLAIN_MESSAGE);
            updateWeight(updatedWeight);
        }
    });
    seatingCapacityButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            updatedCapacity = JOptionPane.showInputDialog(updateAircraft, "Enter updated seating capacity:", "Update Capacity", JOptionPane.PLAIN_MESSAGE);
            updateCapacity(updatedCapacity);
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

    private void updateCapacity(String updatedCapacity) {

        String URL = "jdbc:sqlserver://192.168.1.10:1433;192.168.1.10=library;encrypt=true;trustservercertificate=true";
        String Username = "admin30";
        String Password = "20211054";
        Connection connection = null;
        String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
        try {
            Class.forName(driver);
            connection = DriverManager.getConnection(URL, Username, Password);
            String sql = "UPDATE Aircraft SET seatingCapacity = ? WHERE aircraftID = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, updatedCapacity);
            preparedStatement.setInt(2, Integer.parseInt(AircraftID));
            preparedStatement.executeUpdate();
            JOptionPane.showMessageDialog(updateAircraft.this, "Seating capacity updated successfully!", "Update Successful", JOptionPane.INFORMATION_MESSAGE);
        } catch (ClassNotFoundException e) {
            System.out.println("Driver class not found: " + e.getMessage());
        } catch (SQLException e) {
            System.out.println("Database connection error: " + e.getMessage());
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    System.out.println("Failed to close the database connection: " + e.getMessage());
                }
            }
        }
    }

    private void updateWeight(String updatedWeight) {

        String URL = "jdbc:sqlserver://192.168.1.10:1433;192.168.1.10=library;encrypt=true;trustservercertificate=true";
        String Username = "admin30";
        String Password = "20211054";
        Connection connection = null;
        String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
        try {
            Class.forName(driver);
            connection = DriverManager.getConnection(URL, Username, Password);
            String sql = "UPDATE Aircraft SET maximunWeight = ? WHERE aircraftID = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, updatedWeight);
            preparedStatement.setInt(2, Integer.parseInt(AircraftID));
            preparedStatement.executeUpdate();
            JOptionPane.showMessageDialog(updateAircraft.this, "Weight updated successfully!", "Update Successful", JOptionPane.INFORMATION_MESSAGE);
        } catch (ClassNotFoundException e) {
            System.out.println("Driver class not found: " + e.getMessage());
        } catch (SQLException e) {
            System.out.println("Database connection error: " + e.getMessage());
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    System.out.println("Failed to close the database connection: " + e.getMessage());
                }
            }
        }
    }

    private void updateName(String updatedName) {

        String URL = "jdbc:sqlserver://192.168.1.10:1433;192.168.1.10=library;encrypt=true;trustservercertificate=true";
        String Username = "admin30";
        String Password = "20211054";
        Connection connection = null;
        String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
        try {
            Class.forName(driver);
            connection = DriverManager.getConnection(URL, Username, Password);
            String sql = "UPDATE Aircraft SET name = ? WHERE aircraftID = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, updatedName);
            preparedStatement.setInt(2, Integer.parseInt(AircraftID));
            preparedStatement.executeUpdate();
            JOptionPane.showMessageDialog(updateAircraft.this, "Name updated successfully!", "Update Successful", JOptionPane.INFORMATION_MESSAGE);
        } catch (ClassNotFoundException e) {
            System.out.println("Driver class not found: " + e.getMessage());
        } catch (SQLException e) {
            System.out.println("Database connection error: " + e.getMessage());
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    System.out.println("Failed to close the database connection: " + e.getMessage());
                }
            }
        }
    }
}
