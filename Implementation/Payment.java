import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
public class Payment extends JFrame {
    private JTextField tfPaymentAmount;
    private JTextField tfPaymentMethod;
    private JTextField tfPaymentStatus;
    private JTextField tfPaymentDate;
    private JTextField tfFlightID;
    private JButton btnPay;
    private JButton btnExit;
    private JButton btnGoBack;
    private JPanel paymentPanel;
    User user;

    public Payment(User user) {
        this.user = user;
        setTitle("Payment");
        setContentPane(paymentPanel);
        setPreferredSize(new Dimension(600, 600));
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        btnPay.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pay();
            }
        });

        btnExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        btnGoBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                customerMenu customerMenu = new customerMenu(user);
                customerMenu.setLocationRelativeTo(null);
                customerMenu.setVisible(true);
                customerMenu.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                dispose();
            }
        });
    }

    private void pay() {
        String paymentAmount = tfPaymentAmount.getText();
        String paymentMethod = tfPaymentMethod.getText();
        String paymentStatus = tfPaymentStatus.getText();
        String paymentDate = tfPaymentDate.getText();
        String flightID = tfFlightID.getText();
        int customerID = user.ID;

        String URL = "jdbc:sqlserver://192.168.1.10:1433;192.168.1.10=library;encrypt=true;trustservercertificate=true";
        String Username = "admin30";
        String Password = "20211054";
        Connection connection = null;
        String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";

        try{
            Class.forName(driver);
            connection = DriverManager.getConnection(URL,Username,Password);
            String sql = "INSERT INTO Payment (paymentAmount, paymentMethod, paymentStatus, paymentDate, customerID , flightID) VALUES (?,?,?,?,?,?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1,paymentAmount);
            statement.setString(2,paymentMethod);
            statement.setString(3,paymentStatus);
            statement.setString(4,paymentDate);
            statement.setInt(5,customerID);
            statement.setString(6,flightID);

            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(paymentPanel, "Payment added successfully.");
//                dispose();
            } else {
                JOptionPane.showMessageDialog(paymentPanel, "Failed to add payment. Please try again.");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }


    }
}