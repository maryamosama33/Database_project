import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.*;

public class customerUpdate extends JFrame{
    private JButton btnFname;
    private JButton btnSname;
    private JButton btnEmail;
    private JButton btnPassword;
    private JButton btnState;
    private JButton btnCity;
    private JButton btnZip;
    private JButton btnPhone;
    private JButton btnAge;
    private JPanel updateCustomerPanel;
    private JButton backButton;
    String updatedFirstName;
    String updatedSecondName;
    String updatedEmail;
    String updatedPassword;
    String updatedState;
    String updatedCity;
    String updatedZipCode;
    String updatedPhone;
    String updatedAge;
    private User user;
    public customerUpdate(User user) {
    this.user = user;
    setTitle("Update Profile");
    setContentPane(updateCustomerPanel);
    setPreferredSize(new Dimension(600, 600));
    pack();
    setLocationRelativeTo(null);
    setVisible(true);
    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    btnFname.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            updatedFirstName = JOptionPane.showInputDialog(updateCustomerPanel, "Enter updated first name:", "Update First Name", JOptionPane.PLAIN_MESSAGE);
            updateFirstName(updatedFirstName);
        }
    });
    btnSname.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            updatedSecondName = JOptionPane.showInputDialog(updateCustomerPanel, "Enter updated second name:", "Update Second Name", JOptionPane.PLAIN_MESSAGE);
            updateSecondName(updatedSecondName);
        }
    });
    btnEmail.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            updatedEmail = JOptionPane.showInputDialog(updateCustomerPanel, "Enter updated email:", "Update Email", JOptionPane.PLAIN_MESSAGE);
            updateEmail(updatedEmail);
        }
    });
    btnPassword.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            updatedPassword = JOptionPane.showInputDialog(updateCustomerPanel, "Enter updated password:", "Update Password", JOptionPane.PLAIN_MESSAGE);
            updatePassword(updatedPassword);
        }
    });
    btnState.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            updatedState = JOptionPane.showInputDialog(updateCustomerPanel, "Enter updated state:", "Update State", JOptionPane.PLAIN_MESSAGE);
            updateState(updatedState);
        }
    });
    btnCity.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            updatedCity = JOptionPane.showInputDialog(updateCustomerPanel, "Enter updated city:", "Update City", JOptionPane.PLAIN_MESSAGE);
            updateCity(updatedCity);
        }
    });
    btnZip.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            updatedZipCode = JOptionPane.showInputDialog(updateCustomerPanel, "Enter updated zip code:", "Update Zip Code", JOptionPane.PLAIN_MESSAGE);
            updateZipCode(updatedZipCode);
        }
    });
    btnPhone.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            updatedPhone = JOptionPane.showInputDialog(updateCustomerPanel, "Enter updated phone:", "Update Phone", JOptionPane.PLAIN_MESSAGE);
            updatePhone(updatedPhone);
        }
    });
    btnAge.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            updatedAge = JOptionPane.showInputDialog(updateCustomerPanel, "Enter updated age:", "Update Age", JOptionPane.PLAIN_MESSAGE);
            updateAge(updatedAge);
        }
    });
    pack();
    setVisible(true);
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                customerMenu menu = new customerMenu(user);
                menu.setVisible(true);
                dispose();
            }
        });
    }

    private void updateAge(String updatedAge) {
        // Update the age in the database
        String URL = "jdbc:sqlserver://192.168.1.10:1433;192.168.1.10=library;encrypt=true;trustservercertificate=true";
        String Username = "admin30";
        String Password = "20211054";
        Connection connection = null;
        String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
        try {
            Class.forName(driver);
            connection = DriverManager.getConnection(URL, Username, Password);
            String sql = "UPDATE Customer SET age = ? WHERE customerID = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, updatedAge);
            preparedStatement.setInt(2, user.ID);
            preparedStatement.executeUpdate();
            JOptionPane.showMessageDialog(customerUpdate.this, "Age updated successfully!", "Update Successful", JOptionPane.INFORMATION_MESSAGE);
            user.age = updatedAge; // Update the user object with the new Age
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
            String sql = "UPDATE Customer SET phone = ? WHERE customerID = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, updatedPhone);
            preparedStatement.setInt(2, user.ID);
            preparedStatement.executeUpdate();
            JOptionPane.showMessageDialog(customerUpdate.this, "Phone updated successfully!", "Update Successful", JOptionPane.INFORMATION_MESSAGE);
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

    private void updateZipCode(String updatedZipCode) {
        // Update the zipcode in the database
        String URL = "jdbc:sqlserver://192.168.1.10:1433;192.168.1.10=library;encrypt=true;trustservercertificate=true";
        String Username = "admin30";
        String Password = "20211054";
        Connection connection = null;
        String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
        try {
            Class.forName(driver);
            connection = DriverManager.getConnection(URL, Username, Password);
            String sql = "UPDATE Customer SET zipCode = ? WHERE customerID = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, updatedZipCode);
            preparedStatement.setInt(2, user.ID);
            preparedStatement.executeUpdate();
            JOptionPane.showMessageDialog(customerUpdate.this, "Zip code updated successfully!", "Update Successful", JOptionPane.INFORMATION_MESSAGE);
            user.zipCode = updatedZipCode; // Update the user object with the new zip code
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

    private void updateCity(String updatedCity) {
        // Update the city in the database
        String URL = "jdbc:sqlserver://192.168.1.10:1433;192.168.1.10=library;encrypt=true;trustservercertificate=true";
        String Username = "admin30";
        String Password = "20211054";
        Connection connection = null;
        String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
        try {
            Class.forName(driver);
            connection = DriverManager.getConnection(URL, Username, Password);
            String sql = "UPDATE Customer SET city = ? WHERE customerID = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, updatedCity);
            preparedStatement.setInt(2, user.ID);
            preparedStatement.executeUpdate();
            JOptionPane.showMessageDialog(customerUpdate.this, "City updated successfully!", "Update Successful", JOptionPane.INFORMATION_MESSAGE);
            user.city = updatedCity; // Update the user object with the new city
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

    private void updateState(String updatedState) {
        // Update the State in the database
        String URL = "jdbc:sqlserver://192.168.1.10:1433;192.168.1.10=library;encrypt=true;trustservercertificate=true";
        String Username = "admin30";
        String Password = "20211054";
        Connection connection = null;
        String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
        try {
            Class.forName(driver);
            connection = DriverManager.getConnection(URL, Username, Password);
            String sql = "UPDATE Customer SET state = ? WHERE customerID = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, updatedState);
            preparedStatement.setInt(2, user.ID);
            preparedStatement.executeUpdate();
            JOptionPane.showMessageDialog(customerUpdate.this, "State updated successfully!", "Update Successful", JOptionPane.INFORMATION_MESSAGE);
            user.state = updatedState; // Update the user object with the new state
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
            String sql = "UPDATE Customer SET password = ? WHERE customerID = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, updatedPassword);
            preparedStatement.setInt(2, user.ID);
            preparedStatement.executeUpdate();
            JOptionPane.showMessageDialog(customerUpdate.this, "Password updated successfully!", "Update Successful", JOptionPane.INFORMATION_MESSAGE);
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
            String sql = "UPDATE Customer SET email = ? WHERE customerID = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, updatedEmail);
            preparedStatement.setInt(2, user.ID);
            preparedStatement.executeUpdate();
            JOptionPane.showMessageDialog(customerUpdate.this, "Email updated successfully!", "Update Successful", JOptionPane.INFORMATION_MESSAGE);
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
            String sql = "UPDATE Customer SET sName = ? WHERE customerID = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, updatedSecondName);
            preparedStatement.setInt(2, user.ID);
            preparedStatement.executeUpdate();
            JOptionPane.showMessageDialog(customerUpdate.this, "Second name updated successfully!", "Update Successful", JOptionPane.INFORMATION_MESSAGE);
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
            String sql = "UPDATE Customer SET fName = ? WHERE customerID = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, updatedFirstName);
            preparedStatement.setInt(2, user.ID);
            preparedStatement.executeUpdate();
            JOptionPane.showMessageDialog(customerUpdate.this, "First name updated successfully!", "Update Successful", JOptionPane.INFORMATION_MESSAGE);
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
