import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
public class cancelFlight extends JFrame{
    private JPanel cancelPanel;
    private JButton cancelButton;

    private JTextField tfFlightID;
    private JButton backButton;
    User user;

    public cancelFlight(User user){
        this.user = user;
        setTitle("Cancel Flight");
        setContentPane(cancelPanel);
        setPreferredSize(new Dimension(600, 600));
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cancel();
            }
        });
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                customerMenu menu = new customerMenu(user);
                menu.setVisible(true);
                dispose();
            }
        });
    }

private void cancel(){
    int customerID = user.ID;
    String URL = "jdbc:sqlserver://192.168.1.10:1433;192.168.1.10=library;encrypt=true;trustservercertificate=true";
    String Username = "admin30";
    String Password = "20211054";
    Connection connection = null;
    String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
        String flightID = tfFlightID.getText();
        if (flightID.equals("")){
            JOptionPane.showMessageDialog(null, "Please fill all the fields!");
        } else {
            try {
                int flightIDInt = Integer.parseInt(flightID);
                if (flightIDInt <= 0) {
                    JOptionPane.showMessageDialog(null, "Please enter a valid flight ID!");
                } else {
                    try {
                        Class.forName(driver);
                        connection = DriverManager.getConnection(URL, Username, Password);
                        String sql = "DELETE FROM booking WHERE customerID = " + customerID + " AND flightID = " + flightIDInt;
                        String sql2 = "DELETE FROM Payment WHERE customerID = " + customerID + " AND flightID = " + flightIDInt;
                        Statement stmt = connection.createStatement();
                        stmt.executeUpdate(sql);
                        stmt.executeUpdate(sql2);
                        JOptionPane.showMessageDialog(null, "Flight deleted successfully!");
                    } catch (SQLException e) {
                        JOptionPane.showMessageDialog(null, "Error connecting to database!");
                    } catch (ClassNotFoundException e) {
                        throw new RuntimeException(e);
                    }
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Please enter a valid flight ID!");
            }
        }
    }

}
