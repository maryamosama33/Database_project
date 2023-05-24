import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UpdateFlight extends JFrame{
    private JButton sourceButton;
    private JButton destinationButton;
    private JButton availableSeatsButton;
    private JButton departureDateButton;
    private JButton arrivalDateButton;
    private JButton aircraftButton;
    private JPanel UpdateFlightPanel;
    private JButton backButton;
    String flightID;
    String updatedSource;
    String updatedDestination;
    String updatedAvailableSeats;
    String updatedDepartureDate;
    String updatedArrivalDate;
    String Aircraft;
User user;

    public UpdateFlight(String flightID,User user) {
        this.user = user;
        setTitle("Update Flight");
        setContentPane(UpdateFlightPanel);
        setPreferredSize(new Dimension(600, 600));
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.flightID = flightID;
    sourceButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            updatedSource = JOptionPane.showInputDialog(UpdateFlightPanel, "Enter updated source:", "UpdateSource", JOptionPane.PLAIN_MESSAGE);
            updateSource(updatedSource);
        }
    });
    destinationButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            updatedDestination = JOptionPane.showInputDialog(UpdateFlightPanel, "Enter updated destination:", "Update Destination", JOptionPane.PLAIN_MESSAGE);
            updateDestination(updatedDestination);
        }
    });
    availableSeatsButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            updatedAvailableSeats = JOptionPane.showInputDialog(UpdateFlightPanel, "Enter updated available seats:", "Update Available Seats", JOptionPane.PLAIN_MESSAGE);
            updateAvailableSeats(updatedAvailableSeats);
        }
    });
    departureDateButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            updatedDepartureDate = JOptionPane.showInputDialog(UpdateFlightPanel, "Enter updated departure date:", "Update Departure Date", JOptionPane.PLAIN_MESSAGE);
            updateDepartureDate(updatedDepartureDate);
        }
    });
    arrivalDateButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            updatedArrivalDate = JOptionPane.showInputDialog(UpdateFlightPanel, "Enter updated arrival date:", "Update Arrival Date", JOptionPane.PLAIN_MESSAGE);
            updateArrivalDate(updatedArrivalDate);
        }
    });
    aircraftButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            Aircraft = JOptionPane.showInputDialog(UpdateFlightPanel, "Enter updated aircraft:", "Update Aircraft", JOptionPane.PLAIN_MESSAGE);
            updateAircraftID(Aircraft);
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

    private void updateAircraftID(String aircraft) {
        String URL = "jdbc:sqlserver://192.168.1.10:1433;192.168.1.10=library;encrypt=true;trustservercertificate=true";
        String Username = "admin30";
        String Password = "20211054";
        Connection connection = null;
        String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
        try {
            Class.forName(driver);
            connection = DriverManager.getConnection(URL, Username, Password);
            String sql = "UPDATE Flight SET aircraftID = ? WHERE flightID = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, Aircraft);
            preparedStatement.setInt(2, Integer.parseInt(flightID));
            preparedStatement.executeUpdate();
            JOptionPane.showMessageDialog(UpdateFlight.this, "Aircraft updated successfully!", "Update Successful", JOptionPane.INFORMATION_MESSAGE);
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

    private void updateArrivalDate(String updatedArrivalDate) {
        String URL = "jdbc:sqlserver://192.168.1.10:1433;192.168.1.10=library;encrypt=true;trustservercertificate=true";
        String Username = "admin30";
        String Password = "20211054";
        Connection connection = null;
        String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
        try {
            Class.forName(driver);
            connection = DriverManager.getConnection(URL, Username, Password);
            String sql = "UPDATE Flight SET arrivalDate = ? WHERE flightID = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, updatedArrivalDate);
            preparedStatement.setInt(2, Integer.parseInt(flightID));
            preparedStatement.executeUpdate();
            JOptionPane.showMessageDialog(UpdateFlight.this, "Arrival Date updated successfully!", "Update Successful", JOptionPane.INFORMATION_MESSAGE);
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

    private void updateDepartureDate(String updatedDepartureDate) {
        String URL = "jdbc:sqlserver://192.168.1.10:1433;192.168.1.10=library;encrypt=true;trustservercertificate=true";
        String Username = "admin30";
        String Password = "20211054";
        Connection connection = null;
        String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
        try {
            Class.forName(driver);
            connection = DriverManager.getConnection(URL, Username, Password);
            String sql = "UPDATE Flight SET departureDate = ? WHERE flightID = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, updatedDepartureDate);
            preparedStatement.setInt(2, Integer.parseInt(flightID));
            preparedStatement.executeUpdate();
            JOptionPane.showMessageDialog(UpdateFlight.this, "Departure Date updated successfully!", "Update Successful", JOptionPane.INFORMATION_MESSAGE);
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

    private void updateAvailableSeats(String updatedAvailableSeats) {
        String URL = "jdbc:sqlserver://192.168.1.10:1433;192.168.1.10=library;encrypt=true;trustservercertificate=true";
        String Username = "admin30";
        String Password = "20211054";
        Connection connection = null;
        String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
        try {
            Class.forName(driver);
            connection = DriverManager.getConnection(URL, Username, Password);
            String sql = "UPDATE Flight SET availableSeats = ? WHERE flightID = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, updatedAvailableSeats);
            preparedStatement.setInt(2, Integer.parseInt(flightID));
            preparedStatement.executeUpdate();
            JOptionPane.showMessageDialog(UpdateFlight.this, "Available seats updated successfully!", "Update Successful", JOptionPane.INFORMATION_MESSAGE);
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

    private void updateDestination(String updatedDestination) {
        String URL = "jdbc:sqlserver://192.168.1.10:1433;192.168.1.10=library;encrypt=true;trustservercertificate=true";
        String Username = "admin30";
        String Password = "20211054";
        Connection connection = null;
        String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
        try {
            Class.forName(driver);
            connection = DriverManager.getConnection(URL, Username, Password);
            String sql = "UPDATE Flight SET destination = ? WHERE flightID = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, updatedDestination);
            preparedStatement.setInt(2, Integer.parseInt(flightID));
            preparedStatement.executeUpdate();
            JOptionPane.showMessageDialog(UpdateFlight.this, "Destination updated successfully!", "Update Successful", JOptionPane.INFORMATION_MESSAGE);
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

    private void updateSource(String updatedSource) {
        String URL = "jdbc:sqlserver://192.168.1.10:1433;192.168.1.10=library;encrypt=true;trustservercertificate=true";
        String Username = "admin30";
        String Password = "20211054";
        Connection connection = null;
        String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
        try {
            Class.forName(driver);
            connection = DriverManager.getConnection(URL, Username, Password);
            String sql = "UPDATE Flight SET source = ? WHERE flightID = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, updatedSource);
            preparedStatement.setInt(2, Integer.parseInt(flightID));
            preparedStatement.executeUpdate();
            JOptionPane.showMessageDialog(UpdateFlight.this, "Source updated successfully!", "Update Successful", JOptionPane.INFORMATION_MESSAGE);
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
