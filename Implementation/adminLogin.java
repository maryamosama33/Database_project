import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class adminLogin extends JFrame{
    private JTextField tfEmail;
    private JPasswordField tfPassword;
    private JButton btnLogin;
    private JButton btnExit;
    private JButton btnGoBack;
    private JPanel loginAdmin;
    public User user=null;

public adminLogin() {
    setTitle("Login");
    setContentPane(loginAdmin);
    setPreferredSize(new Dimension(600, 600));
    pack();
    setLocationRelativeTo(null);
    setVisible(true);
    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    btnLogin.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            String email = tfEmail.getText();
            String password = String.valueOf(tfPassword.getPassword());

            user = authenticateUser(email,password);

            if(user != null){
                JOptionPane.showMessageDialog(adminLogin.this, "Welcome, " + user.firstName + "!", "Login Successful", JOptionPane.INFORMATION_MESSAGE);
                AdminMenu menu = new AdminMenu(user);
                menu.setVisible(true);
                dispose();
            }else{
                JOptionPane.showMessageDialog(adminLogin.this,"Email or Password invalid!!","Try again",JOptionPane.ERROR_MESSAGE);
            }
        }
    });
    btnExit.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            dispose();
        }
    });
    pack();
    setVisible(true);

    btnGoBack.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            new adminForm();
            dispose();
        }
    });
}

    private User authenticateUser(String email, String password) {
        String URL = "jdbc:sqlserver://192.168.1.10:1433;192.168.1.10=library;encrypt=true;trustservercertificate=true";
        String Username = "admin30";
        String Password = "20211054";
        Connection connection = null;
        String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
        try {
            Class.forName(driver);
            connection = DriverManager.getConnection(URL, Username, Password);
            Statement stmt = connection.createStatement();
            String sql = "SELECT * from Admin WHERE email=? AND password=?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,email);
            preparedStatement.setString(2,password);

            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                user = new User();
                user.ID = Integer.parseInt(resultSet.getString("adminID"));
                user.firstName = resultSet.getString("fName");
                user.secondName = resultSet.getString("sName");
                user.email = resultSet.getString("email");
                user.password = resultSet.getString("password");
                user.phone = resultSet.getString("phone");

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
        if(user!= null){
            System.out.println("Successful Authentication of: "+ user.firstName);
        }else{
            System.out.println("Authentication canceled");
        }
        return user;
    }
    public static void main(String[] args) {
        adminLogin login = new adminLogin();
    }
}
