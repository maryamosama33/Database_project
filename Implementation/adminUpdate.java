import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class adminUpdate extends JFrame{
    private JButton btnFname;
    private JButton btnSname;
    private JButton btnEmail;
    private JButton btnPassword;
    private JButton btnPhone;
    private JPanel updateAdminPanel;
    private JButton backButton;
    String updatedFirstName;
    String updatedSecondName;
    String updatedEmail;
    String updatedPassword;
    String updatedPhone;
    private User user;
public adminUpdate(User user) {
    this.user = user;
    setTitle("Update Profile");
    setContentPane(updateAdminPanel);
    setPreferredSize(new Dimension(600, 600));
    pack();
    setLocationRelativeTo(null);
    setVisible(true);
    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    btnFname.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            updatedFirstName = JOptionPane.showInputDialog(updateAdminPanel, "Enter updated first name:", "Update First Name", JOptionPane.PLAIN_MESSAGE);
            updateFirstName(updatedFirstName);
        }
    });
    btnSname.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            updatedSecondName = JOptionPane.showInputDialog(updateAdminPanel, "Enter updated second name:", "Update Second Name", JOptionPane.PLAIN_MESSAGE);
            updateSecondName(updatedSecondName);
        }
    });
    btnEmail.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            updatedEmail = JOptionPane.showInputDialog(updateAdminPanel, "Enter updated email:", "Update Email", JOptionPane.PLAIN_MESSAGE);
            updateEmail(updatedEmail);

        }
    });
    btnPassword.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            updatedPassword = JOptionPane.showInputDialog(updateAdminPanel, "Enter updated password:", "Update Password", JOptionPane.PLAIN_MESSAGE);
            updatePassword(updatedPassword);

        }
    });
    btnPhone.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            updatedPhone = JOptionPane.showInputDialog(updateAdminPanel, "Enter updated phone:", "Update Phone", JOptionPane.PLAIN_MESSAGE);
            updatePhone(updatedPhone);

        }
    });
    pack();
    setVisible(true);
    backButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            AdminMenu menu = new AdminMenu(user);
            menu.setVisible(true);
            dispose();
        }
    });
}

    private void updatePhone(String updatedPhone) {
        // Update the phone in the database
        String URL = "jdbc:sqlserver://192.168.1.10:1433;192.168.1.10=library;encrypt=true;trustservercertificate=true";
        String Username = "admin30";
        String Password = "20211054";
        Connection connection = null;
        String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
        try {
            Class.forName(driver);
            connection = DriverManager.getConnection(URL, Username, Password);
            String sql = "UPDATE Admin SET phone = ? WHERE adminID = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, updatedPhone);
            preparedStatement.setInt(2, user.ID);
            preparedStatement.executeUpdate();
            JOptionPane.showMessageDialog(adminUpdate.this, "Phone updated successfully!", "Update Successful", JOptionPane.INFORMATION_MESSAGE);
            user.phone = updatedPhone; // Update the user object with the new phone
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

    private void updatePassword(String updatedPassword) {
        // Update the password in the database
        String URL = "jdbc:sqlserver://192.168.1.10:1433;192.168.1.10=library;encrypt=true;trustservercertificate=true";
        String Username = "admin30";
        String Password = "20211054";
        Connection connection = null;
        String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
        try {
            Class.forName(driver);
            connection = DriverManager.getConnection(URL, Username, Password);
            String sql = "UPDATE Admin SET password = ? WHERE adminID = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, updatedPassword);
            preparedStatement.setInt(2, user.ID);
            preparedStatement.executeUpdate();
            JOptionPane.showMessageDialog(adminUpdate.this, "Password updated successfully!", "Update Successful", JOptionPane.INFORMATION_MESSAGE);
            user.password = updatedPassword; // Update the user object with the new password
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

    private void updateEmail(String updatedEmail) {
        // Update the Email in the database
        String URL = "jdbc:sqlserver://192.168.1.10:1433;192.168.1.10=library;encrypt=true;trustservercertificate=true";
        String Username = "admin30";
        String Password = "20211054";
        Connection connection = null;
        String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
        try {
            Class.forName(driver);
            connection = DriverManager.getConnection(URL, Username, Password);
            String sql = "UPDATE Admin SET email = ? WHERE adminID = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, updatedEmail);
            preparedStatement.setInt(2, user.ID);
            preparedStatement.executeUpdate();
            JOptionPane.showMessageDialog(adminUpdate.this, "Email updated successfully!", "Update Successful", JOptionPane.INFORMATION_MESSAGE);
            user.email = updatedEmail; // Update the user object with the new Email
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

    private void updateSecondName(String updatedSecondName) {
        // Update the second name in the database
        String URL = "jdbc:sqlserver://192.168.1.10:1433;192.168.1.10=library;encrypt=true;trustservercertificate=true";
        String Username = "admin30";
        String Password = "20211054";
        Connection connection = null;
        String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
        try {
            Class.forName(driver);
            connection = DriverManager.getConnection(URL, Username, Password);
            String sql = "UPDATE Admin SET sName = ? WHERE adminID = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, updatedSecondName);
            preparedStatement.setInt(2, user.ID);
            preparedStatement.executeUpdate();
            JOptionPane.showMessageDialog(adminUpdate.this, "Second name updated successfully!", "Update Successful", JOptionPane.INFORMATION_MESSAGE);
            user.secondName = updatedSecondName; // Update the user object with the new second name
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

    private void updateFirstName(String updatedFirstName) {
        // Update the first name in the database
        String URL = "jdbc:sqlserver://192.168.1.10:1433;192.168.1.10=library;encrypt=true;trustservercertificate=true";
        String Username = "admin30";
        String Password = "20211054";
        Connection connection = null;
        String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
        try {
            Class.forName(driver);
            connection = DriverManager.getConnection(URL, Username, Password);
            String sql = "UPDATE Admin SET fName = ? WHERE adminID = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, updatedFirstName);
            preparedStatement.setInt(2, user.ID);
            preparedStatement.executeUpdate();
            JOptionPane.showMessageDialog(adminUpdate.this, "First name updated successfully!", "Update Successful", JOptionPane.INFORMATION_MESSAGE);
            user.firstName = updatedFirstName; // Update the user object with the new first name
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
