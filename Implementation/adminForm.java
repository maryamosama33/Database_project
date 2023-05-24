import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class adminForm extends JFrame {
    private JButton btnRegister;
    private JButton btnLogin;
    private JButton btnGoBack;
    private JButton btnExit;
    private JPanel adminPanel;

    public adminForm() {
        setTitle("Admin");
        setContentPane(adminPanel);
        setPreferredSize(new Dimension(600, 600));
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        btnRegister.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                adminRegister registerForm = new adminRegister();
                registerForm.setLocationRelativeTo(null);
                registerForm.setVisible(true);
                registerForm.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                dispose();
            }
        });
        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                adminLogin aLogin = new adminLogin();
                aLogin.setLocationRelativeTo(null);
                aLogin.setVisible(true);
                aLogin.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                dispose();
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
                mainForm form = new mainForm();
                form.setVisible(true);
                dispose();
            }
        });

    }
}
