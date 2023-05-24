import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class customerForm extends JFrame{
    private JButton btnRegister;
    private JButton btnLogin;
    private JButton btnGoBack;
    private JButton btnExit;
    private JPanel customerPanel;

    public customerForm(){
        setTitle("Customer");
        setContentPane(customerPanel);
        setPreferredSize(new Dimension(600, 600));
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        btnRegister.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                customerRegister registerForm = new customerRegister();
                registerForm.setLocationRelativeTo(null);
                registerForm.setVisible(true);
                registerForm.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                dispose();
            }
        });
        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                customerLogin cLogin = new customerLogin();
                cLogin.setLocationRelativeTo(null);
                cLogin.setVisible(true);
                cLogin.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                dispose();
            }
        });

        btnGoBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainForm form = new mainForm();
                form.setVisible(true);
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
