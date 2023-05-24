import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class customerMenu extends JFrame{
    private JButton updateButton;
    private JPanel customerMenuPanel;
    private JButton showButton;
    private JButton cancelButton;
    private JButton backButton;
    private JButton btnPayment;
    User user;

    public customerMenu(User user) {
        this.user = user;
        setTitle("Customer Menu");
        setContentPane(customerMenuPanel);
        setPreferredSize(new Dimension(600, 600));
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    updateButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            customerUpdate update = new customerUpdate(user);
            update.setVisible(true);
            dispose();
        }
    });
        showButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                int userID = user.ID;
                showFlights showFlights = new showFlights(user);
                showFlights.setLocationRelativeTo(null);
                showFlights.setVisible(true);
                showFlights.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                dispose();
            }
        });

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cancelFlight cancelFlight = new cancelFlight(user);
                cancelFlight.setLocationRelativeTo(null);
                cancelFlight.setVisible(true);
                cancelFlight.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                dispose();
            }
        });

        btnPayment.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Payment payment = new Payment(user);
                payment.setLocationRelativeTo(null);
                payment.setVisible(true);
                payment.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                dispose();
            }
        });

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                customerLogin login = new customerLogin();
                login.setVisible(true);
                dispose();
            }
        });

    }
}
