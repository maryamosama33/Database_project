import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.*;

public class customerRegister extends JFrame{
    private JTextField tfFName;
    private JTextField tfSName;
    private JTextField tfEmail;
    private JPasswordField tfPassword;
    private JTextField tfState;
    private JTextField tfCity;
    private JTextField tfZip;
    private JTextField tfAge;
    private JTextField tfPhone;
    private JButton btnRegister;
    private JButton btnCancel;
    public JPanel registerPanel;
    private JButton backButton;

    public customerRegister(){
        setTitle("Create a new customer");
        setContentPane(registerPanel);
        setPreferredSize(new Dimension(600, 600));
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        btnRegister.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                registerUser();
                if(user != null){
                    JOptionPane.showMessageDialog(customerRegister.this, "Register Successful Please Login", "Register Successful", JOptionPane.INFORMATION_MESSAGE);
                    customerLogin aLogin = new customerLogin();
                    aLogin.setLocationRelativeTo(null);
                    aLogin.setVisible(true);
                    aLogin.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                    dispose();
                }else{
                    JOptionPane.showMessageDialog(customerRegister.this,"Register failed","Try again",JOptionPane.ERROR_MESSAGE);
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

    private void registerUser() {
        String firstName = tfFName.getText();
        String secondName = tfSName.getText();
        String email = tfEmail.getText();
        String password = String.valueOf(tfPassword.getPassword());
        String state = tfState.getText();
        String city = tfCity.getText();
        String zipCode = tfZip.getText();
        String phone = tfPhone.getText();
        String age = tfAge.getText();
        if(firstName.isEmpty() || secondName.isEmpty() || email.isEmpty() || password.isEmpty()){
            JOptionPane.showMessageDialog(this,"Please enter all fields","Try again",JOptionPane.ERROR_MESSAGE);
            return;
        }
        user = addUserToDatabase(firstName,secondName,email,password,state,city,zipCode,phone,age);
        if(user != null){
            dispose();
        }else{
            JOptionPane.showMessageDialog(this,"Failed to register new user","Try again",JOptionPane.ERROR_MESSAGE);
        }
    }
public User user;
    private User addUserToDatabase(String firstName, String secondName, String email,String password, String state, String city, String zipCode, String phone, String age) {
        User user = null;
        String URL = "jdbc:sqlserver://192.168.1.10:1433;192.168.1.10=library;encrypt=true;trustservercertificate=true";
        String Username = "admin30";
        String Password = "20211054";
        Connection connection = null;
        String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";

        try {
            Class.forName(driver);
            connection = DriverManager.getConnection(URL, Username, Password);
            Statement stmt = connection.createStatement();
            String sql = "INSERT INTO Customer (fName,sName,email,password,state,city,zipCode,age,phone)" +
                          "VALUES(?,?,?,?,?,?,?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,firstName);
            preparedStatement.setString(2,secondName);
            preparedStatement.setString(3,email);
            preparedStatement.setString(4,password);
            preparedStatement.setString(5,state);
            preparedStatement.setString(6,city);
            preparedStatement.setString(7,zipCode);
            preparedStatement.setString(8,age);
            preparedStatement.setString(9,phone);


            int addedRows = preparedStatement.executeUpdate();
            if(addedRows > 0){
                user = new User();
                user.firstName = firstName;
                user.secondName = secondName;
                user.email = email;
                user.password = password;
                user.state = state;
                user.city = city;
                user.zipCode = zipCode;
                user.phone = phone;
                user.age = age;
            }
            stmt.close();
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
        return user;
    }

    public static void main(String[] args) {
        customerRegister myForm = new customerRegister();
        User user = myForm.user;
        if(user != null){
            System.out.println("Successful registration of " + user.firstName);
        }else{
            System.out.println("Registration canceled");
        }
    }
}
