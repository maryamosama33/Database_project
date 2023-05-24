import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class mainForm extends JFrame{
    private JButton btnAdmin;
    private JButton btnCustomer;
    private JButton btnExit;
    private JPanel mainPanel;

    public mainForm() {
        setTitle("Main");
        setContentPane(mainPanel);
        setPreferredSize(new Dimension(600, 600));
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        btnCustomer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                customerForm customerForm = new customerForm();
                customerForm.setLocationRelativeTo(null);
                customerForm.setVisible(true);
                customerForm.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                dispose();
            }
        });
        btnAdmin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                adminForm adminForm = new adminForm();
                adminForm.setLocationRelativeTo(null);
                adminForm.setVisible(true);
                adminForm.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
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

    public static void main(String[] args) {
        mainForm myForm = new mainForm();
    }
}
