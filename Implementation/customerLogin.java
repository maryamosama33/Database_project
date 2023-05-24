import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class customerLogin extends JFrame{
    private JTextField tfEmail;
    private JPasswordField pfPassword;
    private JButton btnLogin;
    private JButton btnCancel;
    private JPanel loginPanel;
    private JButton backButton;
    public User user=null;

    public customerLogin(){
        setTitle("Login");
        setContentPane(loginPanel);
        setPreferredSize(new Dimension(600, 600));
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String email = tfEmail.getText();
                String password = String.valueOf(pfPassword.getPassword());

                user = authenticateUser(email,password);

                if(user != null){
                    JOptionPane.showMessageDialog(customerLogin.this, "Welcome, " + user.firstName + "!", "Login Successful", JOptionPane.INFORMATION_MESSAGE);
                    customerMenu menu = new customerMenu(user);
                    menu.setVisible(true);
                    dispose();

                }else{
                    JOptionPane.showMessageDialog(customerLogin.this,"Email or Password invalid!!","Try again",JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        btnCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        pack();
        setVisible(true);
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                customerForm form = new customerForm();
                form.setVisible(true);
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
           String sql = "SELECT * from Customer WHERE email=? AND password=?";
           PreparedStatement preparedStatement = connection.prepareStatement(sql);
           preparedStatement.setString(1,email);
           preparedStatement.setString(2,password);

           ResultSet resultSet = preparedStatement.executeQuery();
           if(resultSet.next()){
               user = new User();
               user.ID = Integer.parseInt(resultSet.getString("customerID"));
               user.firstName = resultSet.getString("fName");
               user.secondName = resultSet.getString("sName");
               user.email = resultSet.getString("email");
               user.password = resultSet.getString("password");
               user.zipCode = resultSet.getString("zipCode");
               user.state = resultSet.getString("state");
               user.age = resultSet.getString("age");
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
        customerLogin login = new customerLogin();


    }
}
