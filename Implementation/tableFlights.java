import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class tableFlights extends JFrame {

    private JTable table;
    private JPanel flightsPanel;
    private JPanel buttonPanel; // New panel for the button
    private JButton backButton;
    String source;
    String destination;
    int seats;
    String departureDate;
    List<Object[]> data;
    User user;
    int flightID;

    public tableFlights(User user, String source, String destination, int seats, String departureDate) {
        this.user = user;
        this.source = source;
        this.destination = destination;
        this.seats = seats;
        this.departureDate = departureDate;
        createTable();
        setTitle("Showing a list of available flights");
        setContentPane(flightsPanel);
        setPreferredSize(new Dimension(800, 600));
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showFlights flights = new showFlights(user);
                flights.setVisible(true);
                dispose();
            }
        });
    }

    public void createTable() {
        String URL = "jdbc:sqlserver://192.168.1.10:1433;192.168.1.10=library;encrypt=true;trustservercertificate=true";
        String Username = "admin30";
        String Password = "20211054";
        Connection connection = null;
        String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";

        try {
            Class.forName(driver);
            connection = DriverManager.getConnection(URL, Username, Password);
            String sql = "SELECT f.flightID ,f.source, f.destination, f.availableSeats, f.departureDate, f.arrivalDate, a.name, a.maximunWeight\n" +
                    "FROM Flight f\n" +
                    "JOIN Aircraft a ON f.aircraftID = a.aircraftID\n" +
                    "WHERE f.source = ? AND f.destination = ? AND f.availableSeats >= ?\n AND f.departureDate = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, source);
            preparedStatement.setString(2, destination);
            preparedStatement.setString(3, String.valueOf(seats));
            preparedStatement.setString(4, departureDate);

            ResultSet resultSet = preparedStatement.executeQuery();

            data = new ArrayList<>();

            while (resultSet.next()) {
                Object[] row = new Object[9];
                row[0] = resultSet.getInt("flightID");
                row[1] = resultSet.getString("source");
                row[2] = resultSet.getString("destination");
                row[3] = resultSet.getInt("availableSeats");
                row[4] = resultSet.getString("departureDate");
                row[5] = resultSet.getString("arrivalDate");
                row[6] = resultSet.getString("name");
                row[7] = resultSet.getInt("maximunWeight");
                JButton btnBook = new JButton("Book");
                btnBook.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                    }
                });
                row[8] = btnBook;
                data.add(row);
            }

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


        DefaultTableModel tableModel = new DefaultTableModel(
                data.toArray(new Object[data.size()][]),
                new String[]{"flightID", "Source", "Destination", "Available Seats", "Departure Date", "Arrival Date", "Aircraft Name", "Weight", "Book"}
        ) {
            @Override
            public boolean isCellEditable(int row, int column) {
                // Make all columns except the "Book" column non-editable
                return column == 8;
            }
        };

        table = new JTable(tableModel) {
            @Override
            public TableCellRenderer getCellRenderer(int row, int column) {
                // Return a custom renderer for the "Book" column
                if (column == 8) {
                    return new JButtonRenderer();
                }
                return super.getCellRenderer(row, column);
            }

            @Override
            public TableCellEditor getCellEditor(int row, int column) {
                // Return a custom editor for the "Book" column
                if (column == 8) {
                    return new JButtonEditor();
                }
                return super.getCellEditor(row, column);
            }
        };

        TableColumnModel columns = table.getColumnModel();
        columns.getColumn(0).setMaxWidth(200);

        JScrollPane scrollPane = new JScrollPane(table);

        buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER)); // Create a panel for the button
        backButton = new JButton("Back");
        buttonPanel.add(backButton); // Add the button to the button panel

        flightsPanel = new JPanel(new BorderLayout());
        flightsPanel.add(scrollPane, BorderLayout.CENTER);
        flightsPanel.add(buttonPanel, BorderLayout.SOUTH); // Add the button panel below the scroll pane
    }

    public void bookFlight(int flightID) {
        int customerID = user.ID;
        String URL = "jdbc:sqlserver://192.168.1.10:1433;192.168.1.10=library;encrypt=true;trustservercertificate=true";
        String Username = "admin30";
        String Password = "20211054";
        Connection connection = null;
        String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
        try {
            Class.forName(driver);
            connection = DriverManager.getConnection(URL, Username, Password);
            String sql = "INSERT INTO Booking (customerID, flightID) VALUES (?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, String.valueOf(customerID));
            preparedStatement.setString(2, String.valueOf(flightID));
            preparedStatement.executeUpdate();
            JOptionPane.showMessageDialog(null, "Flight booked successfully!");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
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

    private class JButtonRenderer extends JButton implements TableCellRenderer {
        public JButtonRenderer() {
            setOpaque(true);
        }

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            setText("Book");
            return this;
        }
    }

    private class JButtonEditor extends DefaultCellEditor {
        private JButton button;
        private boolean isButtonClicked;
        private int clickedRow;

        public JButtonEditor() {
            super(new JTextField());
            setClickCountToStart(1);

            button = new JButton();
            button.setOpaque(true);

            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    fireEditingStopped();
                }
            });
        }

        @Override
        public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
            if (isSelected) {
                button.setForeground(table.getSelectionForeground());
                button.setBackground(table.getSelectionBackground());
            } else {
                button.setForeground(table.getForeground());
                button.setBackground(table.getBackground());
            }

            clickedRow = row;
            isButtonClicked = true;
            return button;
        }

        @Override
        public Object getCellEditorValue() {
            if (isButtonClicked) {
                flightID = Integer.parseInt(JOptionPane.showInputDialog(flightsPanel, "Enter flight ID to pay:", "Flight ID", JOptionPane.PLAIN_MESSAGE));
                bookFlight(flightID);
                System.out.println("Book button clicked for row: " + clickedRow);
            }
            isButtonClicked = false;
            return "";
        }

        @Override
        public boolean stopCellEditing() {
            isButtonClicked = false;
            return super.stopCellEditing();
        }

        @Override
        protected void fireEditingStopped() {
            super.fireEditingStopped();
        }
    }
}