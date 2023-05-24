import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class adminRegister extends JFrame{
    private JTextField tfFname;
    private JTextField tfSname;
    private JTextField tfEmail;
    private JPasswordField pfPassword;
    private JTextField tfPhone;
    private JButton btnRegister;
    private JButton btnExit;
    private JButton btnGoBack;
    private JPanel registerAdmin;


    public adminRegister() {
    setTitle("Create a new Admin");
    setContentPane(registerAdmin);
    setPreferredSize(new Dimension(600, 600));
    pack();
    setLocationRelativeTo(null);
    setVisible(true);
    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    btnRegister.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            registerAdmin();
            if(user != null){
                JOptionPane.showMessageDialog(adminRegister.this, "Register Successful Please Login", "Register Successful", JOptionPane.INFORMATION_MESSAGE);
                adminLogin aLogin = new adminLogin();
                aLogin.setLocationRelativeTo(null);
                aLogin.setVisible(true);
                aLogin.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                dispose();
            }else{
                JOptionPane.showMessageDialog(adminRegister.this,"Register failed","Try again",JOptionPane.ERROR_MESSAGE);
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
            dispose();
            adminForm adminForm = new adminForm();
            adminForm.setLocationRelativeTo(null);
            adminForm.setVisible(true);
            adminForm.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        }
    });
}

    private void registerAdmin() {
        String firstName = tfFname.getText();
        String secondName = tfSname.getText();
        String email = tfEmail.getText();
        String password = String.valueOf(pfPassword.getPassword());
        String phone = tfPhone.getText();
        if(firstName.isEmpty() || secondName.isEmpty() || email.isEmpty() || password.isEmpty()){
            JOptionPane.showMessageDialog(this,"Please enter all fields","Try again",JOptionPane.ERROR_MESSAGE);
            return;
        }
         user = addUserToDatabase(firstName,secondName,email,password,phone);
        if(user != null){
            dispose();
        }else{
            JOptionPane.showMessageDialog(this,"Failed to register new user","Try again",JOptionPane.ERROR_MESSAGE);
        }
    }
    public User user;
    private User addUserToDatabase(String firstName,String secondName, String email, String password, String phone){
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
            String sql = "INSERT INTO Admin (fName,sName,email,password,phone)" +
                          "VALUES(?,?,?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,firstName);
            preparedStatement.setString(2,secondName);
            preparedStatement.setString(3,email);
            preparedStatement.setString(4,password);
            preparedStatement.setString(5,phone);

            int addedRows = preparedStatement.executeUpdate();
            if(addedRows > 0){
                user = new User();
                user.firstName = firstName;
                user.secondName = secondName;
                user.email = email;
                user.password = password;
                user.phone = phone;
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
        adminRegister myForm = new adminRegister();
        User user = myForm.user;
        if(user != null){
            System.out.println("Successful registration of " + user.firstName);
        }else{
            System.out.println("Registration canceled");
        }
    }
}
