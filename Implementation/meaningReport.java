import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.*;
public class meaningReport extends JFrame {
    private JPanel meaningReportPanel;
    private JTextArea textArea;
    private JButton btnExit;
    private JButton btnGoBack;
    private JButton btnGenerateReport;
    public meaningReport() {
        setTitle("Report");
        setContentPane(meaningReportPanel);
        setPreferredSize(new Dimension(600, 600));
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        btnGenerateReport.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String URL = "jdbc:sqlserver://192.168.1.10:1433;192.168.1.10=library;encrypt=true;trustservercertificate=true";
                String Username = "admin30";
                String Password = "20211054";
                Connection connection = null;
                String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";

                try{
                    Class.forName(driver);
                    connection = DriverManager.getConnection(URL, Username, Password);
                    //print number of Existing Flights
                    String sql = "SELECT COUNT(*) FROM Flight";
                    PreparedStatement preparedStatement = connection.prepareStatement(sql);
                    ResultSet resultSet = preparedStatement.executeQuery();
                    resultSet.next();
                    int numberOfBookings = resultSet.getInt(1);
                    textArea.append("Number of Existing Flights: " + numberOfBookings + "\n");


                    //print number of Bookings
                    sql = "SELECT COUNT(*) FROM Booking";
                    preparedStatement = connection.prepareStatement(sql);
                    resultSet = preparedStatement.executeQuery();
                    resultSet.next();
                    int numberOfFlights = resultSet.getInt(1);
                    textArea.append("Number of Bookings: " + numberOfFlights + "\n");

                    //print number of Aircrafts
                    sql = "SELECT COUNT(*) FROM Aircraft";
                    preparedStatement = connection.prepareStatement(sql);
                    resultSet = preparedStatement.executeQuery();
                    resultSet.next();
                    int numberOfAircrafts = resultSet.getInt(1);
                    textArea.append("Number of Aircrafts: " + numberOfAircrafts + "\n");

                    //print number of customers
                    sql = "SELECT COUNT(*) FROM Customer";
                    preparedStatement = connection.prepareStatement(sql);
                    resultSet = preparedStatement.executeQuery();
                    resultSet.next();
                    int numberOfCustomers = resultSet.getInt(1);
                    textArea.append("Number of Customers: " + numberOfCustomers + "\n");

                    //print flightID of the flight with the most bookings
                    sql = "SELECT MAX(flightID) , COUNT(*) AS numberOfBookings FROM Booking GROUP BY flightID ORDER BY numberOfBookings DESC";
                    preparedStatement = connection.prepareStatement(sql);
                    resultSet = preparedStatement.executeQuery();
                    resultSet.next();
                    int flightID = resultSet.getInt(1);
                    textArea.append("FlightID of the flight with the most bookings: " + flightID + "\n");

                    //print flightID of the flight with the least bookings
                    sql = "SELECT MIN(flightID) , COUNT(*) AS numberOfBookings FROM Booking GROUP BY flightID ORDER BY numberOfBookings ASC";
                    preparedStatement = connection.prepareStatement(sql);
                    resultSet = preparedStatement.executeQuery();
                    resultSet.next();
                    flightID = resultSet.getInt(1);
                    textArea.append("FlightID of the flight with the least bookings: " + flightID + "\n");

                    //print the aircraftID of the aircraft with the most flights
                    sql = "SELECT MAX(aircraftID) , COUNT(*) AS numberOfFlights FROM Flight GROUP BY aircraftID ORDER BY numberOfFlights DESC";
                    preparedStatement = connection.prepareStatement(sql);
                    resultSet = preparedStatement.executeQuery();
                    resultSet.next();
                    int aircraftID = resultSet.getInt(1);
                    textArea.append("AircraftID of the aircraft with the most flights: " + aircraftID + "\n");

                    //print the aircraftID of the aircraft with the least flights
                    sql = "SELECT MIN(aircraftID) , COUNT(*) AS numberOfFlights FROM Flight GROUP BY aircraftID ORDER BY numberOfFlights ASC";
                    preparedStatement = connection.prepareStatement(sql);
                    resultSet = preparedStatement.executeQuery();
                    resultSet.next();
                    aircraftID = resultSet.getInt(1);
                    textArea.append("AircraftID of the aircraft with the least flights: " + aircraftID + "\n");

                    //print number of maximum seatingCapacity
                    sql = "SELECT MAX(seatingCapacity) FROM Aircraft";
                    preparedStatement = connection.prepareStatement(sql);
                    resultSet = preparedStatement.executeQuery();
                    resultSet.next();
                    int seatingCapacity = resultSet.getInt(1);
                    textArea.append("Maximum seatingCapacity: " + seatingCapacity + "\n");

                    //print number of minimum seatingCapacity
                    sql = "SELECT MIN(seatingCapacity) FROM Aircraft";
                    preparedStatement = connection.prepareStatement(sql);
                    resultSet = preparedStatement.executeQuery();
                    resultSet.next();
                    seatingCapacity = resultSet.getInt(1);
                    textArea.append("Minimum seatingCapacity: " + seatingCapacity + "\n");


                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                } catch (ClassNotFoundException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        btnGoBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                adminForm adminForm = new adminForm();
                adminForm.setLocationRelativeTo(null);
                adminForm.setVisible(true);
                adminForm.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                dispose();
            }
        });
        btnExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }
}
